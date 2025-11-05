package logica.interfaces;

import java.time.LocalDate;
import java.util.List;

import excepciones.AsistenteYaRegistrado;
import excepciones.FechaRegistroInvalidaException;
import excepciones.ErrorDetallesRegistroException;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.datatypes.DataAsistente;
import logica.datatypes.DataDetalleRegistro;
import logica.datatypes.DataOrganizador;
import logica.datatypes.DataUsuario;
import logica.datatypes.ParEdicionRegistro;
import excepciones.ContrasenaIncorrectaException;

public interface IUsuario {
    
	public abstract void registrarAsistente(String nombre, String nickname, String email, String pass, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException;
	
	public abstract void registrarAsistente(DataAsistente dataAsistente) throws UsuarioRepetidoException; //pass 
	
	public abstract void registrarOrganizador(String nombre, String nickname, String email, String pass, String descripcion, String url) throws UsuarioRepetidoException;
	
	public abstract void registrarOrganizador(DataOrganizador dataOrg) throws UsuarioRepetidoException; //pass

    public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
    
    public abstract DataAsistente getAsistente(String nickmail) throws UsuarioNoExisteException;
    
    public abstract DataOrganizador getOrganizador(String nickmail) throws UsuarioNoExisteException;
    
    public abstract void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException;
    
    public abstract void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException;
    
    public abstract String getTipoUsuario(String email) throws UsuarioNoExisteException;
    
    public abstract List<String> listarAsistentes();
    
    public abstract List<String> listarOrganizadores();
    
    public abstract void nuevoRegistro(String asistenteSeleccionado, String evento, String edicion, String tipoReg, LocalDate fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro, FechaRegistroInvalidaException;
    
    public abstract List<ParEdicionRegistro> getRegistrosAsistente(String asistenteSeleccionado);
    
    public abstract ParEdicionRegistro estaRegistrado(String asistente, String edicion);
    
    public abstract DataDetalleRegistro getDetallesRegistro(String asistenteSeleccionado, ParEdicionRegistro regEdicion)  throws ErrorDetallesRegistroException;
    
    public abstract DataUsuario login(String nickmail, String pass) throws UsuarioNoExisteException;
    
    public abstract void cargarDatos();
    
    public List<String> getUsuariosRegistrados(String edicion);
    
    public abstract void modificarAsistenteConPassword(String email, String nuevoNombre, String nuevoApellido, String passActual, String passNueva) throws UsuarioNoExisteException, ContrasenaIncorrectaException;

    public abstract void modificarOrganizadorConPassword(String email, String nuevoNombre, String descripcion, String url, String passActual, String passNueva) throws UsuarioNoExisteException, ContrasenaIncorrectaException;
    
    public abstract void seguirUsuario(String seguidor, String aSeguir);
    
    public abstract List<String> getSeguidos(String usr);
    
    public abstract List<String> getSeguidores(String usr);
    
    public abstract Boolean esSeguidor(String usr, String seguidor);
    
    public abstract void dejarDeSeguir(String seguidor, String seguido);
    
    public abstract Boolean verificarAsistencia(String edicion, String usuario);
    
    public abstract void setAsistencia(String edicion, String usuario);
}