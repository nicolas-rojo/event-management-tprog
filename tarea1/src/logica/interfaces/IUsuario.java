package logica.interfaces;

import java.time.LocalDate;
import java.util.List;

import excepciones.AsistenteYaRegistrado;
import excepciones.ErrorDetallesRegistroException;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.datatypes.*;

public interface IUsuario {
    
	public abstract void registrarAsistente(String nombre, String nickname, String email, String pass, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException;
	
	public abstract void registrarAsistente(DataAsistente dataAsistente) throws UsuarioRepetidoException; //pass 
	
	public abstract void registrarOrganizador(String nombre, String nickname, String email, String pass, String descripcion, String url) throws UsuarioRepetidoException;
	
	public abstract void registrarOrganizador(DataOrganizador dataOrg) throws UsuarioRepetidoException; //pass

    public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
    
    public abstract DataAsistente getAsistente(String email) throws UsuarioNoExisteException;
    
    public abstract DataOrganizador getOrganizador(String email) throws UsuarioNoExisteException;
    
    public abstract void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException;
    
    public abstract void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException;
    
    public abstract String getTipoUsuario(String email) throws UsuarioNoExisteException;
    
    public abstract List<String> listarAsistentes();
    
    public abstract List<String> listarOrganizadores();
    
    public abstract void nuevoRegistro(String asistenteSeleccionado, String evento, String edicion, String tipoReg, LocalDate fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro;
    
    public abstract List<ParEdicionRegistro> getRegistrosAsistente(String asistenteSeleccionado);
    
    public abstract DataDetalleRegistro getDetallesRegistro(String asistenteSeleccionado, ParEdicionRegistro regEdicion)  throws ErrorDetallesRegistroException;
    
    public abstract ResLogin login(String nickmail, String pass);
}