package excepciones;

/**
 * Excepción utilizada para indicar existencia previa de una en el sistema con el nombre elegido.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class AsistenteYaRegistrado extends Exception {

    public AsistenteYaRegistrado(String string) {
        super(string);
    }
}
