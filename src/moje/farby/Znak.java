//enum znaku jaki moze miec ParametrPigmentu
package moje.farby;


public enum Znak {
    PLUS("+"),
    MINUS("-"),
    RAZY("x");
    
    private final String znak;
    
    private Znak(String znak) {
        this.znak = znak;
    }
    
    public String dajWartosc() {
        return znak;
    }
    
    //mozna to ulepszyc za pomoca HashMap, ale tutaj są tylko 3 znaki więc
    //switch jest wystarczajcy
    public static Znak dajZnak(String znak) {
        switch (znak) {
            case "+":
                return PLUS; 
            case "-":
                return MINUS;
            case "x":
                return RAZY;
            default:
                return null;
        }
    }
}
