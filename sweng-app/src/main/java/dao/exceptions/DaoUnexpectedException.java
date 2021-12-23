package dao.exceptions;

/**
 * DaoUnexpectedException
 */
public class DaoUnexpectedException extends DaoGenericException {

    public DaoUnexpectedException() {
    }

    public DaoUnexpectedException(String message) {
        super(message);
    }

    public DaoUnexpectedException(Throwable cause) {
        super(cause);
    }

    public DaoUnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoUnexpectedException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    
}
