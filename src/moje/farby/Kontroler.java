//klasa pelniaca role Kontrolera
//jest odpowiednialna za komunikacje pomiedzy maszyna a GUI
//wszystkie funkcje nie robia nic wiecej oprocz uruchamiania odpowiednich 
//funkcji w masznie lub gui. 
package moje.farby;

public class Kontroler {
    private Maszyna maszyna;
    private FarbyUI gui;
    
    public void ustawMaszyna(Maszyna maszyna) {
        this.maszyna = maszyna;
    }
    
    public void ustawGui(FarbyUI gui) {
        this.gui = gui;
    }
    
    public void dodajFarbeDoGui(String nazwa) {
        gui.dodajFarbe(nazwa);
    }
    
    public void dodajPigmentDoGui(String nazwa) {
        gui.dodajPigment(nazwa);
    }
    
    public Farba dajFarbeOdMaszyny(String nazwa) {
        return maszyna.dajFarbe(nazwa);
    }
    
    public Pigment dajPigmentOdMaszyny(String nazwa) {
        return maszyna.dajPigment(nazwa);
    }
    
    public String dodajLosowaFarbeDoMaszyny() {
        Farba farba = maszyna.dodajLosowaFarbe();
        return farba.dajNazwa();
    }
    
    public String dodajLosowyPigmentDoMaszyny() {
        Pigment pigmet = maszyna.dodajLosowyPigment();
        return pigmet.dajNazwa();
    }
    
    public Farba dajMieszanaFrabeOdMaszyny() {
        return maszyna.dajMieszanaFarbe();
    }
    
    public void ustawMieszanaFarbeDoMaszyny(Farba farba) {
        maszyna.ustawMieszanaFarbe(farba);
    }
    
    public Boolean mieszajWMaszynie(Pigment pigment) {
        return maszyna.Mieszaj(pigment);
    }
    
    
    
    
}
