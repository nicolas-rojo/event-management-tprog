package logica.ctrlmanejador;

import logica.interfaces.IUsuario;

import excepciones.UsuarioRepetidoException;
import excepciones.UsuarioNoExisteException;
import java.time.LocalDate;
import logica.Asistente;
import logica.Organizador;
import logica.Usuario;
import logica.datatypes.*;



public class ControladorUsuario implements IUsuario {

    public ControladorUsuario() {
    }

    public void registrarAsistente(String nombre, String nickname, String email, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
        Usuario u = mu.obtenerUsuarioNickname(nickname);
        if (u != null)
            throw new UsuarioRepetidoException("Nickname ya en uso");
        u = mu.obtenerUsuarioEmail(email);
        if (u != null)
            throw new UsuarioRepetidoException("Email ya en uso");
        Asistente a = new Asistente(nombre, nickname, email, apellido, fechaNac);
        mu.addUsuario(a);
    }
    
    public void registrarOrganizador(String nombre, String nickname, String email, String descripcion, String url) throws UsuarioRepetidoException {
    	ManejadorUsuario mu = ManejadorUsuario.getinstance();
        Usuario u = mu.obtenerUsuarioNickname(nickname);
        if (u != null)
            throw new UsuarioRepetidoException("Nickname ya en uso");
        u = mu.obtenerUsuarioEmail(email);
        if (u != null)
            throw new UsuarioRepetidoException("Email ya en uso");
        Organizador o = new Organizador(nombre, nickname, email, descripcion, url);
        mu.addUsuario(o);
    }

    public DataAsistente getAsistente(String email) throws UsuarioNoExisteException{
    	ManejadorUsuario mu = ManejadorUsuario.getinstance();
    	Asistente a = (Asistente) mu.obtenerUsuarioEmail(email);
    	if (a != null)
    		return new DataAsistente(a.getNombre(), a.getNickname(), a.getEmail(), a.getApellido(), a.getFechaNac());
    	else
    		throw new UsuarioNoExisteException("No existe usuario con dicho email");
    }
    
    public DataOrganizador getOrganizador(String email) throws UsuarioNoExisteException{
    	ManejadorUsuario mu = ManejadorUsuario.getinstance();
    	Organizador o = (Organizador) mu.obtenerUsuarioEmail(email);
    	if (o != null)
    		return new DataOrganizador(o.getNombre(), o.getNickname(), o.getEmail(), o.getDescripcion(), o.getUrl());
    	else 
    		throw new UsuarioNoExisteException("No existe usuario con dicho email");    		
    }
    
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
        Usuario[] usrs = mu.getUsuarios();

        if (usrs != null) {
            DataUsuario[] du = new DataUsuario[usrs.length];
            Usuario usuario;

            for (int i = 0; i < usrs.length; i++) {
                usuario = usrs[i];
                du[i] = new DataUsuario(usuario.getNombre(), usuario.getNickname(), usuario.getEmail());
            }

            return du;
        } else
            throw new UsuarioNoExisteException("No existen usuarios registrados");

    }

	public void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException {
	    ManejadorUsuario mu = ManejadorUsuario.getinstance();
	    Usuario u = mu.obtenerUsuarioEmail(email);
	    if (u == null) {
	        throw new UsuarioNoExisteException("Error, no hay usuario con el email ingresdo");
	    }
	    Asistente a = (Asistente) u;
	    a.setNombre(nuevoNombre);
	    a.setApellido(nuevoApellido);
	}
	
	public void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException {
	    ManejadorUsuario mu = ManejadorUsuario.getinstance();
	    Usuario u = mu.obtenerUsuarioEmail(email);
	    if (u == null) {
	        throw new UsuarioNoExisteException("Error, no hay usuario con el email ingresdo");
	    }
	    Organizador o = (Organizador) u;
	    o.setNombre(nuevoNombre);
	    o.setDescripcion(descripcion);
	    o.setUrl(url);
	}
	
	public String obtenerTipoUsuario(String email) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
        Usuario u = mu.obtenerUsuarioEmail(email);
        
        if (u == null) {
            throw new UsuarioNoExisteException("No existe usuario con email: " + email);
        }
        
        if (u instanceof Asistente) {
            return "Asistente";
        } else if (u instanceof Organizador) {
            return "Organizador";
        } else {
            throw new UsuarioNoExisteException("Tipo de usuario desconocido para email: " + email);
        }
    }
}