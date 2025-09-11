package excepciones;

/**
 * Excepción utilizada para indicar la inexistencia de un evento en el sistema.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class EventoNoExisteExcepcion extends Exception {

    public EventoNoExisteExcepcion(String string) {
        super(string);
    }
}
