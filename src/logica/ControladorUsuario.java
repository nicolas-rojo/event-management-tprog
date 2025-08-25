package logica;

import java.time.LocalDate;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

public class ControladorUsuario implements IControladorUsuario {

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

    public DataUsuario verInfoUsuario(String nickname) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
        Usuario u = mu.obtenerUsuarioNickname(nickname);
        if (u != null)
            return new DataUsuario(u.getNombre(), u.getNickname(), u.getEmail());
        else
            throw new UsuarioNoExisteException("El usuario " + nickname + " no existe");

    }

    public Asistente getAsistente(String email){
    	ManejadorUsuario mu = ManejadorUsuario.getinstance();
    	Asistente a = (Asistente) mu.obtenerUsuarioEmail(email);
    	return a;
    }
    
    public Organizador getOrganizador(String email){
    	ManejadorUsuario mu = ManejadorUsuario.getinstance();
    	Organizador o = (Organizador) mu.obtenerUsuarioEmail(email);
    	return o;
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