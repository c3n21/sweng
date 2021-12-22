package dao;

/**
 * DaoUnknownTypeException
 */
public class DaoUnknownTypeException extends DaoGenericException {

    public DaoUnknownTypeException() {
    }

    public DaoUnknownTypeException(String message) {
        super(message);
    }

    public DaoUnknownTypeException(Throwable cause) {
        super(cause);
    }

    public DaoUnknownTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoUnknownTypeException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
