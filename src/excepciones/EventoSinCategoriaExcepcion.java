package excepciones;

/**
 * Excepción utilizada para indicar la falta de categoria al intentar crear un evento.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class EventoSinCategoriaExcepcion extends Exception {

    public EventoSinCategoriaExcepcion(String string) {
        super(string);
    }
}
