package common.exception;

/**
 * Excepciones para el subs cuentas
 */
public class CuentaException extends Exception {

    /**
     * Void constructor
     */
    public CuentaException() {
    }

    /**
     * Constructor with a message
     * 
     * @param message message string
     */
    public CuentaException(String message) {
        super(message);
    }

    /**
     * Constructor with a cause for the exception
     * 
     * @param cause cause of the exception
     */
    public CuentaException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with a message and a cause for the exception
     * 
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public CuentaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with a message and a cause for the exception
     * 
     * @param message            message of the exception
     * @param cause              cause of the exception
     * @param enableSuppresion   whether or not supresion is enable
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public CuentaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
