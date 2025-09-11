package excepciones;

/**
 * Excepción utilizada para indicar existencia previa de una en el sistema con el nombre elegido.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class NoHayRegistrosAsistente extends Exception {

    public NoHayRegistrosAsistente(String string) {
        super(string);
    }
}
