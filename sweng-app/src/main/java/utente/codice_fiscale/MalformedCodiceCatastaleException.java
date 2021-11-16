package utente.codice_fiscale;

public class MalformedCodiceCatastaleException extends RuntimeException {

    public MalformedCodiceCatastaleException() {
    }

    public MalformedCodiceCatastaleException(String message) {
        super(message);
    }

    public MalformedCodiceCatastaleException(Throwable cause) {
        super(cause);
    }

    public MalformedCodiceCatastaleException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedCodiceCatastaleException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
