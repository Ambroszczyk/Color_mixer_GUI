//klasa w ktorej trzymamy informacje o pigmencie 
package moje.farby;

import java.util.regex.Pattern;

public class Pigment {
    
    private String nazwa;
    private String pierwszaFarba;
    private String drugaFarba;
    private ParametrPigmentu toksycznosc;
    private ParametrPigmentu jakosc;
   
    //analogiczny konstruktor jak w klasie farba
    //jezeli jakas z operacji sie nie powiedzie to rzuca ReadingException
    Pigment(String nazwa, String pierwszaFarba, String drugaFarba, String toksycznoscSkompresowana, 
            String jakoscSkompresowana) throws ReadingException {
        if(!ustawNazwa(nazwa) || !ustawPierwszaFarba(pierwszaFarba) || !ustawDrugaFarba(drugaFarba) ||
        !ustawToksycznosc(toksycznoscSkompresowana) || !ustawJakosc(jakoscSkompresowana))
            throw new ReadingException();
    }
    
    //ponizesz funkcje ustawiaja odpowiednie atrybuty pigmentu 
    //jezeli ustawienie sie nie powiedzie to zwracaja false, jezeli natomiast 
    //sie powiedzie to je ustawiaja i zwracaja true 
    
    public Boolean ustawNazwa(String nazwa) {
        Pattern wzorzec = Pattern.compile("[A-Za-z0-9]+");
        if (!wzorzec.matcher(nazwa).matches()) {
            return false; 
        }
        this.nazwa = nazwa;
        return true;
    }
    
    public Boolean ustawPierwszaFarba(String pierwszaFarba) {
        Pattern wzorzec = Pattern.compile("[A-Za-z][a-zA-Z0-9-]*");
        if (!wzorzec.matcher(pierwszaFarba).matches()) {
            return false;
        }
        this.pierwszaFarba = pierwszaFarba;
        return true;
    }
    
     public Boolean ustawDrugaFarba(String drugaFarba) {
        Pattern wzorzec = Pattern.compile("[A-Za-z][a-zA-Z0-9-]*");
        if (!wzorzec.matcher(drugaFarba).matches()) {
            return false;
        }
        this.drugaFarba = drugaFarba;
        return true;
    }
    
    public Boolean ustawToksycznosc(String toksycznoscSkompresowana) {
        ParametrPigmentu pomPig = ParametrPigmentu.dajNowyPigment(toksycznoscSkompresowana);
        if (pomPig == null)
            return false;
        this.toksycznosc = pomPig;
        return true;
    }
    
     public Boolean ustawJakosc(String jakoscSkompresowana) {
        ParametrPigmentu pomPig = ParametrPigmentu.dajNowyPigment(jakoscSkompresowana);
        if (pomPig == null)
            return false;
        this.jakosc = pomPig;
        return true;
    }
    
    //zwykle gettery 
     
    public String dajNazwa() {
        return this.nazwa;
    }
    
    public String dajPierwszaFarbe() {
        return this.pierwszaFarba;
    }
    
    public String dajDrugaFarbe() {
        return this.drugaFarba;
    }
    
    public ParametrPigmentu dajToksycznosc() {
        return this.toksycznosc;
    }
    
    public ParametrPigmentu dajJakosc() {
        return this.jakosc;
    }
    
    
   
    
}
