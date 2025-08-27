package excepciones;

/**
 * Excepción utilizada para indicar existencia previa de un evento en el sistema.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class EventoRepetidoExcepcion extends Exception {

    public EventoRepetidoExcepcion(String string) {
        super(string);
    }
}
