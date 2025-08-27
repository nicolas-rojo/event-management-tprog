package logica;

import java.time.LocalDate;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.datatypes.DataUsuario;

public interface IControladorUsuario {
    
	public abstract void registrarAsistente(String nombre, String nickname, String email, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException;
	
	public abstract void registrarOrganizador(String nombre, String nickname, String email, String descripcion, String url) throws UsuarioRepetidoException;

    public abstract DataUsuario verInfoUsuario(String ci) throws UsuarioNoExisteException;
    
    public abstract Organizador getOrganizador(String email);
    
    public abstract Asistente getAsistente(String email);

    public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
    
    public abstract void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException;
    
    public abstract void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException;
    
    public abstract String obtenerTipoUsuario(String email) throws UsuarioNoExisteException;
}