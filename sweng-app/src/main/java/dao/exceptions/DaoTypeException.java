package dao.exceptions;

/**
 * DaoTypeException
 */
public class DaoTypeException extends DaoGenericException {

    public DaoTypeException() {
    }

    public DaoTypeException(String message) {
        super(message);
    }

    public DaoTypeException(Throwable cause) {
        super(cause);
    }

    public DaoTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    
}
