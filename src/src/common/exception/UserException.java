package common.exception;

/**
 * Exceptions for User subsystem
 */
public class UserException extends Exception {

    /**
     * Void constructor
     */
    public UserException() {
        super();
    }

    /**
     * Constructor with a message
     * 
     * @param message message string
     */
    public UserException(String message) {
        super(message);
    }

    /**
     * Constructor with a message and a cause for the exception
     * 
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with a cause for the exception
     * 
     * @param cause cause of the exception
     */
    public UserException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with a message and a cause for the exception
     * 
     * @param message            message of the exception
     * @param cause              cause of the exception
     * @param enableSuppresion   whether or not supresion is enable
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
