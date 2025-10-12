package excepciones;

/**
 * Excepción utilizada para indicar existencia previa de un tipo de registro para una edicion.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class TipoDeRegistroRepetidoException extends Exception {
	public TipoDeRegistroRepetidoException(String string) {
		super(string);
	}
	
}


