package dao.exceptions;

/**
 * DaoRecoverableException
 */
public class DaoRecoverableException extends Exception {

    public DaoRecoverableException() {
    }

    public DaoRecoverableException(String message) {
        super(message);
    }

    public DaoRecoverableException(Throwable cause) {
        super(cause);
    }

    public DaoRecoverableException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoRecoverableException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
