package excepciones;

import jakarta.xml.ws.WebFault;

/**
 * Excepción utilizada para indicar la inexistencia de un usuario en el sistema.
 *
 * @author TProg2017
 *
 */
@WebFault(name = "UsuarioNoExisteException", targetNamespace = "http://excepciones/")
public class UsuarioNoExisteException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsuarioNoExisteException() {
        super();
    }

    public UsuarioNoExisteException(String string) {
        super(string);
    }
}