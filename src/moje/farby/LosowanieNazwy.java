//klasa zajmujaca sie losowaniem nazwy obiektu (farba/pigment) w zaleznosci od
//podanych ktryteriow

//UWAGA: w przypadku edycji kryteriow potrzebna jest edycja sprawdzania poprawnosci
//nazwy obiektu przy jej tworzeniu
//jezeli zostanie to zapomniane program wypisze komunikat na wyjscie bledu
package moje.farby;

import java.util.Random;

public class LosowanieNazwy {
    //klucze to stringi znakow z ktorych losujemy litery farby lub pigmentu 
    private static final int maxDlugoscLosowanejFarby = 15;
    private static String kluczFarby;
    private static String kluczPoczatkuFarby;
    private static final int maxDlugoscLosowanegoPigmentu = 8;
    private static String kluczPigmentu;
    private static Random random;
    
    //definicja kluczy 
    static {
        String znaki = new String();
        StringBuffer sb = new StringBuffer(znaki);
        for (int i = 'a'; i <= 'z'; i++)
            sb.append((char) i);
        for (int i = 'A'; i <= 'Z'; i++)
            sb.append((char) i);
        kluczPoczatkuFarby = sb.toString();
        for (int i = '0'; i <= '9'; i++)
            sb.append((char) i);
        kluczPigmentu = sb.toString();
        kluczFarby = kluczPigmentu + "-";
        
        random = new Random();
    }
    
    //zwraca losowa nazwe farby
    public static String dajFarbe() {
        //losujemy dlugosc farby
        int dlugosc = random.nextInt(maxDlugoscLosowanejFarby);
        StringBuilder sb = new StringBuilder();
        //dzieki temu zawsze wylosujemy co najmniej 1 litere 
        sb.append(kluczPoczatkuFarby.charAt(random.nextInt(kluczPoczatkuFarby.length())));
        //a potem odpowiednio jej parametry
        for (int i = 0; i < dlugosc ; i++) {
            sb.append(kluczFarby.charAt(random.nextInt(kluczFarby.length())));
        }
        return sb.toString();
    }
    
    //analogicznie jak z farba
    public static String dajPigment() {
        //tutaj nie losujemy osobno pierwszej litery dlatego zapewniamy, ze
        //dlugosc > 0
        int dlugosc = random.nextInt(maxDlugoscLosowanegoPigmentu) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dlugosc ; i++) {
            sb.append(kluczPigmentu.charAt(random.nextInt(kluczPigmentu.length())));
        }
        return sb.toString();
    }
}
