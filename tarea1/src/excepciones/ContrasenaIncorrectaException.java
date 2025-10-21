package excepciones;

@SuppressWarnings("serial")
public class ContrasenaIncorrectaException extends Exception {
    public ContrasenaIncorrectaException(String mensaje) {
        super(mensaje);
    }
}
