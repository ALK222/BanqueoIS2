package common.exception;

/**
 * Excepciones para el sub usuarios
 */
public class UserException extends Exception {

    /**
     * Constructor vacío
     */
    public UserException() {
    }

    /**
     * Contructor con un mensaje
     * 
     * @param message String con el mensaje
     */
    public UserException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     * 
     * @param cause Causa de la excepción
     */
    public UserException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con un mensaje y una causa
     * 
     * @param message Mensaje de la excepción
     * @param cause   Causa de la excepcion
     */
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor con un mensaje, una causa y dos flags
     * 
     * @param message            Mensaje de la excepción
     * @param cause              Causa de la excepción
     * @param enableSuppresion   Activar o no la supresión
     * @param writableStackTrace Escribir o no en la pila
     */
    public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
