//Klasa pelniaca funkcje modelu. Odpowiada za dzialanie calej maszyny 
package moje.farby;

import java.io.*;
import java.util.*;

/**
 *
 * @author piotr
 */
public class Maszyna {
    private Kontroler kontroler;
    //zakladamy, ze nie mozna edytowan nazwy raz wybranej farby, dlatego 
    //HashMap wystarcza w zupelnosci 
    private HashMap<String, Farba> farby;
    private HashMap<String, Pigment> pigmenty;
    private Farba aktualnieMieszana;
    
    Maszyna(FileReader strumien, Kontroler kontroler) throws ReadingException, IOException {
        this.kontroler = kontroler;
        farby = new HashMap<>();
        pigmenty = new HashMap<>();
        File f;
        
        kontroler.ustawMaszyna(this);  
        BufferedReader br = new BufferedReader(strumien);
        String linia;
        //sprawdza czy da przeczytac pierwsza linijke pliku, potem testuje 
        //czy zawiera ona sciezke do pliku, nastepnie probuje wczytac znajdujace
        //sie tam farby 
        if ((linia = br.readLine()) == null) {
            throw new ReadingException();
        }
        f = new File(linia);
        if (!f.isFile())
            throw new ReadingException();
        zapiszFarby(f);
        
        //analogicznie jak z farbami 
        if ((linia = br.readLine()) == null) {
            throw new ReadingException();
        }
        
        f = new File(linia);
        if (!f.isFile())
            throw new ReadingException();
        zapiszPigmenty(f);               
    }
    
    //probuje wczytac definicje farb ktore znajduja sie w pliku f
    private void zapiszFarby(File f) throws ReadingException, IOException, FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linia;
        String[] wczytanaLinia;
        Farba farba_pom;
        while ((linia = br.readLine()) != null) {
            wczytanaLinia = linia.split(" ");
            if (wczytanaLinia.length != 3)
              throw new ReadingException();
            //rzuca ReadingExpection jezeli cos poszlo nie tak z wczytywaniem
            farba_pom = new Farba(wczytanaLinia[0], wczytanaLinia[1], wczytanaLinia[2]);
            dodajFarbe(farba_pom);
            
        }
    }
    
    //analogicznie jak z farbami 
    private void zapiszPigmenty(File f) throws ReadingException, IOException, FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linia;
        String[] wczytanaLinia;
        Pigment pigment_pom;
        while ((linia = br.readLine()) != null) {
            wczytanaLinia = linia.split(" ");
            if (wczytanaLinia.length != 5) {
                throw new ReadingException();
            }   
            pigment_pom = new Pigment(wczytanaLinia[0], wczytanaLinia[1], 
                    wczytanaLinia[2], wczytanaLinia[3], wczytanaLinia[4]);
            if (czyJestPigment(pigment_pom.dajNazwa()))
                    throw new ReadingException();
            dodajPigment(pigment_pom);
        }
    }
    
    //dodaje farbe do listy farb 
    public void dodajFarbe(Farba farba) {
        farby.put(farba.dajNazwa(), farba);
        kontroler.dodajFarbeDoGui(farba.dajNazwa());
    }
    
    //analogicznie jak z farba
    public void dodajPigment(Pigment pigment) {
        pigmenty.put(pigment.dajNazwa(), pigment);
        kontroler.dodajPigmentDoGui(pigment.dajNazwa());
    }
    
    public Farba dodajLosowaFarbe() {
        String nazwa = LosowanieNazwy.dajFarbe();
        Farba farba = null;
        //jezeli nazwa juz istnieje losujemy jeszcze raz 
        while (czyJestFarba(nazwa)) {
            nazwa = LosowanieNazwy.dajFarbe();
        }
        try {
             farba = new Farba(nazwa, "1", "1");
             dodajFarbe(farba);
        }
        //ten wyjątek nie powinien nigdy wystąpic, pojawi się jezeli ktoś zle zmieni
        //atrybuty LosowanieNazwy nie zmiając przy tym definicji sprawdzania poprawnosc
        //nazwy w klasie Farba lub jesli zmienil ograniczenia toksycznosc farby na takie
        //ktore nie rozwazaja "1". 
        catch(ReadingException ignorowany) {
            System.err.print("Błąd zmiany ograniczeń parametrów w implementacji");
            System.exit(1);
        }
        return farba;
    }
    
    //analogicznie jak z farba
    public Pigment dodajLosowyPigment() {
        String nazwa = LosowanieNazwy.dajPigment();
        Pigment pigment = null;
        while (czyJestPigment(nazwa)) {
            nazwa = LosowanieNazwy.dajPigment();
        }
        try {
             pigment = new Pigment(nazwa, "PoczatkowaFarba", "KoncowaFarba", "x1" , "x1");
             dodajPigment(pigment);
        }
        //analogiczna sytuacja jak przy dajLosowaFarbe
        catch(ReadingException ignorowany) {
            System.err.print("Błąd zmiany ograniczeń parametrów w implementacji");
            System.exit(1);
        }
        return pigment;
    }
    
    //zaklady, ze pole aktualnaFarba jest zainicjalizowane!
    public Boolean Mieszaj(Pigment pigment) {
        //jezeli pigment nie zawiera informacji o mieszaniu aktualnej farby
        if (!aktualnieMieszana.dajNazwa().equals(pigment.dajPierwszaFarbe())) {
            return false;            
        }
        String nowaToksycznosc = String.valueOf(zmianaParametruFarby(
                aktualnieMieszana.dajToksycznosc(), pigment.dajToksycznosc()));
        String nowaJakosc = String.valueOf(zmianaParametruFarby(
                aktualnieMieszana.dajJakosc(), pigment.dajJakosc()));
        
        try {
            aktualnieMieszana = new Farba(pigment.dajDrugaFarbe(), nowaToksycznosc, nowaJakosc);
        }
        catch (ReadingException zlyParametrFarby) {
            //farba niespelnia warunkow w tresci zadania
            //np. toksycznosc wychodzi ponad 100
            return false;
        }
        return true;
    }
    
    //funkcja pomocnicza zmianiajaca w odpowiedni sposob parametr farby po
    //zastosowaniu pewnego ParametruPigmentu
    //jezeli w wyniku wykonywania operacji dostaniemy liczbe niecalkowita 
    //to zaokraglamy ja za pomoca metody intValue
    private int zmianaParametruFarby(int poczParametr, ParametrPigmentu zmiennik) {
        Znak znak = zmiennik.dajZnak();
        Double wynik = 0.0;
        switch (znak) {
            case PLUS:
                wynik = poczParametr + zmiennik.dajLiczba();
                break;
            case MINUS:
                wynik = poczParametr - zmiennik.dajLiczba();
                break;
            case RAZY:
                wynik = poczParametr * zmiennik.dajLiczba();
                break;
        }
        return wynik.intValue();
    }
    
    //sprawdza czy w liscie jest farba o podanej nazwie 
    private Boolean czyJestFarba(String farba) {
        if (farby.get(farba) == null)
            return false;
        return true;
    }
    
    //analogicznie jak z farba
    private Boolean czyJestPigment(String pigment) {
        if (farby.get(pigment) == null)
            return false;
        return true;
    }
    
    //ponizsze funkcje to zwykle gettery i jeden setter 
    
    public Farba dajFarbe(String nazwa) {
        return farby.get(nazwa);
    }
    
    public Pigment dajPigment(String nazwa) {
        return pigmenty.get(nazwa);
    }
    
    public Farba dajMieszanaFarbe() {
        return aktualnieMieszana;
    }
    
    public void ustawMieszanaFarbe(Farba farba) {
        aktualnieMieszana = farba;
    }

    
    
}
