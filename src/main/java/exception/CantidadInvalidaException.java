package exception;

public class CantidadInvalidaException extends RuntimeException {

    // Constructor: recibe el mensaje de error y lo pasa a la clase padre
    public CantidadInvalidaException(String mensaje) {
        super(mensaje);
    }
}