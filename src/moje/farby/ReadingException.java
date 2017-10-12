//klasa deklaruje wlasny wyjatek dziedziczacy po IOException
//glownie dla czytelnosci kodu 
package moje.farby;

import java.io.IOException;

public class ReadingException extends IOException {
    @Override 
    public String getMessage() {
        return "Błąd wczytywania danych z pliku";
    }
}
