//klasa w ktorej trzymany informacje o farbie
package moje.farby;

import java.util.regex.Pattern;

public class Farba {
    private String nazwa;
    private IntFarba toksycznosc;
    private IntFarba jakosc;
    
    //rzuca ReadingException bo jedyny moment w ktorym nie wiemy, czy dane sa poprawne
    //i tworzymy nowa farbe to wczytywanie z plikow
    Farba(String nazwa, String toksycznosc, String jakosc) throws
            ReadingException {
        if (!ustawNazwa(nazwa) || !ustawToksycznosc(toksycznosc) || !ustawJakosc(jakosc))
               throw new ReadingException();
    }
    
    //jezeli nazwa pasuje do wzorca to ja ustawia i zwraca true
    //w przeciwnym wypadku zwraca false 
    public Boolean ustawNazwa(String nazwa) {
        Pattern wzorzec = Pattern.compile("[A-Za-z][a-zA-Z0-9-]*");
        if (!wzorzec.matcher(nazwa).matches()) {
            return false;
        }
        this.nazwa = nazwa;
        return true;
    }
    
    //analogicznie jak z farba
    public Boolean ustawToksycznosc(int toksycznosc) {
        IntFarba pomIntFarba =IntFarba.dajNowaLiczbe(toksycznosc);
        if (pomIntFarba == null)
            return false;
        this.toksycznosc = pomIntFarba;
        return true;
    }
    
    //sprawdza czy podana jakosc lezy w odpowiednich przedzialach
    //jezeli tak to ustawia ja na jakosc i zwraca true
    //w przeciwnym wypadku zwraca false
    public Boolean ustawJakosc(int jakosc) {
        IntFarba pomIntFarba = IntFarba.dajNowaLiczbe(jakosc);
        if (pomIntFarba == null)
            return false;
        this.jakosc = pomIntFarba;
        return true;
    }
    
    //analogicznie jak z jakoscia
    public Boolean ustawToksycznosc(String toksycznosc) {
        Integer intPom;
        try {
            intPom = Integer.valueOf(toksycznosc);
            return ustawToksycznosc(intPom);
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    //probuje ustawić string na jakosc, jezeli mu sie nie uda zwraca false 
    public Boolean ustawJakosc(String jakosc) {
        Integer intPom;
        try {
            intPom = Integer.valueOf(jakosc);
            return ustawJakosc(intPom);
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    //ponizej sa zwykle gettery
    
    public String dajNazwa() {
        return this.nazwa;
    }
    
    public int dajToksycznosc() {
        return this.toksycznosc.dajWartosc();
    }
     
    public int dajJakosc() {
        return this.jakosc.dajWartosc();
    }
    
    //zwraca String toksycznosci 
    public String wypiszToksycznosc() {
        return this.toksycznosc.wypisz();
    }
    
    //zwraca String jakosci
    public String wypiszJakosc() {
        return this.jakosc.wypisz();
    }
    
}
