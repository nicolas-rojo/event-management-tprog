package excepciones;

/**
 * Excepción utilizada para indicar existencia previa de una en el sistema con el nombre elegido.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class ErrorDetallesRegistroException extends Exception {

    public ErrorDetallesRegistroException(String string) {
        super(string);
    }
}
