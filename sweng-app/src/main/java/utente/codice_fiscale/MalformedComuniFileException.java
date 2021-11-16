package utente.codice_fiscale;

public class MalformedComuniFileException extends RuntimeException{

    public MalformedComuniFileException() {
    }

    public MalformedComuniFileException(Throwable cause) {
        super(cause);
    }

    public MalformedComuniFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedComuniFileException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MalformedComuniFileException(String message) {
        super(message);
    }
}
