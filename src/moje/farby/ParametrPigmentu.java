//klasa trzymaja informacje o parametrze pigmentu 
//czyli jego znak oraz poziom zmiany toksycznosci
package moje.farby;

import java.util.regex.Pattern;

public class ParametrPigmentu {
    private double liczba;
    private Znak znak;

    //ustawia parametr pigmentu, jezeli nie jest to mozliwe to zwraca false
    public Boolean ustawParametrPigmentu(double liczba, String znak) {
        if (czyMoznaUstawicParametrPigmentu(liczba, znak)) {
            this.liczba = liczba;
            this.znak = Znak.dajZnak(znak);
            return true;
        }
        return false;
    }
    
    //sprawdza czy parametr pigmentu pasuje do wzorca
    private Boolean czyMoznaUstawicParametrPigmentu(double liczba, String znak) {
        if (liczba <= 0)
            return false;
        Pattern wzorzec = Pattern.compile("[x+-]");
        if (!wzorzec.matcher(znak).matches())
            return false;
        return true;
    }
    
    //dostaje skompresowanego stringa z informacja o parametrze pigmentu np.x0.4
    //jezeli skompresowany parametr nie mozna ustawic jako pigment to zwraca null
    public static ParametrPigmentu dajNowyPigment(String parametrSkompresowany) {
        double liczba;
        String znak;
        ParametrPigmentu pomPig;
        //jesli ma za malo znakow
        if (parametrSkompresowany.length() < 2)
            return null;
        try {
            liczba = Double.parseDouble(parametrSkompresowany.substring(1));
        }
        //jezeli nie jest Doublem
        catch (NumberFormatException ex) {
            return null;
        }
        znak = String.valueOf(parametrSkompresowany.charAt(0));
        pomPig =  new ParametrPigmentu();
        //jezeli nie da sie ustawic
        if (!pomPig.ustawParametrPigmentu(liczba, znak))
            return null;
        return pomPig;
    }
    
    public Znak dajZnak() {
        return znak;
    }
    
    //zwykly setter
    public double dajLiczba() {
        return liczba;
    }
    
    //zwraca stringa zanku
    public String wypiszZnak() {
        return znak.dajWartosc();
    }
    
    //zwraca stringa liczby 
    public String wypiszLiczba() {
        return String.valueOf(liczba);
    }
    
    //zwraca stringa ktory zawiera na poczatku znak a potem liczbe 
    public String wypiszSkompresowane() {
        return wypiszZnak() + wypiszLiczba();
    }
}
