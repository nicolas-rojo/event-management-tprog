package excepciones;

/**
 * Excepción utilizada para indicar la invalidez a la hora de dar de alta un registro en el campo de la fecha.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class FechaRegistroInvalidaException extends Exception {
    public FechaRegistroInvalidaException(String mensaje) {
        super(mensaje);
    }
}