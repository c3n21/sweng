package dao.exceptions;

/**
 * DaoUnrecoverableException
 */
public class DaoUnrecoverableException extends RuntimeException {

    public DaoUnrecoverableException() {
    }

    public DaoUnrecoverableException(String message) {
        super(message);
    }

    public DaoUnrecoverableException(Throwable cause) {
        super(cause);
    }

    public DaoUnrecoverableException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoUnrecoverableException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
