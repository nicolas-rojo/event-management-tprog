package logica.interfaces;

import java.time.LocalDate;
import java.util.List;

import excepciones.AsistenteYaRegistrado;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.datatypes.DataUsuario;

public interface IUsuario {
    
	public abstract void registrarAsistente(String nombre, String nickname, String email, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException;
	
	public abstract void registrarOrganizador(String nombre, String nickname, String email, String descripcion, String url) throws UsuarioRepetidoException;

    public abstract DataUsuario verInfoUsuario(String ci) throws UsuarioNoExisteException;

    public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
    
    public abstract void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException;
    
    public abstract void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException;
    
    public abstract String getTipoUsuario(String email) throws UsuarioNoExisteException;
    
    public abstract List<String> listarAsistentes();
    
    public abstract void nuevoRegistro(String asistenteSeleccionado, String evento, String edicion, String tipoReg) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro;
}