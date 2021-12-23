package dao.exceptions;

/**
 * DaoGenericException
 */
public class DaoGenericException extends Exception {

    public DaoGenericException() {
    }

    public DaoGenericException(String message) {
        super(message);
    }

    public DaoGenericException(Throwable cause) {
        super(cause);
    }

    public DaoGenericException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoGenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
