//klasa "obatulacja" liczbe calkowita trzymana w klasie Farba
package moje.farby;

public class IntFarba {
    private final static int DOLNA_GRANICA = 0;
    private final static int GORNA_GRANICA = 100;
    private int liczba; 

    private IntFarba(int liczba) {
        this.liczba = liczba;
    }
    
    public int dajWartosc() {
        return liczba; 
    }
    
    //jezeli liczba miesci sie w ustalonych granicach to tworzy nowy obiekt klasy 
    //IntFarba, jezeli nie to zwraca nulla 
    public static IntFarba dajNowaLiczbe(int liczba) {
        if (liczba < DOLNA_GRANICA || liczba > GORNA_GRANICA)
            return null;
        return new IntFarba(liczba);
    }
   
    public String wypisz() {
        return Integer.toString(liczba);
    }
}
