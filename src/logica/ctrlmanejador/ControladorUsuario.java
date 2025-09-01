package logica.ctrlmanejador;

import logica.interfaces.IUsuario;
import excepciones.UsuarioRepetidoException;
import excepciones.AsistenteYaRegistrado;
import excepciones.ErrorDetallesRegistroException;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.UsuarioNoExisteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.Asistente;
import logica.Organizador;
import logica.Usuario;
import logica.datatypes.*;

public class ControladorUsuario implements IUsuario {

    public ControladorUsuario() {
    }

    public void registrarAsistente(String nombre, String nickname, String email, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException {
        registrarAsistente(new DataAsistente(nombre, nickname, email, apellido, fechaNac));
    }
    
    public void registrarAsistente(DataAsistente datos) throws UsuarioRepetidoException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        
        // Verificar si ya existe usuario con mismo nickname
        if (mu.getUsuarioNickname(datos.getNickname()) != null) {
            throw new UsuarioRepetidoException("Nickname ya en uso: " + datos.getNickname());
        }
        
        // Verificar si ya existe usuario con mismo email
        if (mu.getUsuarioEmail(datos.getEmail()) != null) {
            throw new UsuarioRepetidoException("Email ya en uso: " + datos.getEmail());
        }
        
        Asistente a = new Asistente(datos.getNombre(), datos.getNickname(), datos.getEmail(), datos.getApellido(), datos.getFechaNac());
        mu.addUsuario(a);
    }
    
    public void registrarOrganizador(String nombre, String nickname, String email, String descripcion, String url) throws UsuarioRepetidoException {
        registrarOrganizador(new DataOrganizador(nombre, nickname, email, descripcion, url));
    }
    
    public void registrarOrganizador(DataOrganizador datos) throws UsuarioRepetidoException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        
        // Verificar si ya existe usuario con mismo nickname
        if (mu.getUsuarioNickname(datos.getNickname()) != null) {
            throw new UsuarioRepetidoException("Nickname ya en uso: " + datos.getNickname());
        }
        
        // Verificar si ya existe usuario con mismo email
        if (mu.getUsuarioEmail(datos.getEmail()) != null) {
            throw new UsuarioRepetidoException("Email ya en uso: " + datos.getEmail());
        }
        
        Organizador o = new Organizador(datos.getNombre(), datos.getNickname(), datos.getEmail(), datos.getDescripcion(), datos.getUrl());
        mu.addUsuario(o);
    }

    public DataAsistente getAsistente(String email) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario usuario = mu.getUsuarioEmail(email);
        
        if (usuario == null) {
            throw new UsuarioNoExisteException("No existe usuario con email: " + email);
        }
        
        if (!(usuario instanceof Asistente)) {
            throw new UsuarioNoExisteException("El usuario con email " + email + " no es un asistente");
        }
        
        Asistente a = (Asistente) usuario;
        return new DataAsistente(a.getNombre(), a.getNickname(), a.getEmail(), a.getApellido(), a.getFechaNac());
    }
    
    public DataOrganizador getOrganizador(String email) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario usuario = mu.getUsuarioEmail(email);
        
        if (usuario == null) {
            throw new UsuarioNoExisteException("No existe usuario con email: " + email);
        }
        
        if (!(usuario instanceof Organizador)) {
            throw new UsuarioNoExisteException("El usuario con email " + email + " no es un organizador");
        }
        
        Organizador o = (Organizador) usuario;
        return new DataOrganizador(o.getNombre(), o.getNickname(), o.getEmail(), o.getDescripcion(), o.getUrl());
    }
    
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario[] usuarios = mu.getUsuarios();

        if (usuarios.length == 0) {
            throw new UsuarioNoExisteException("No existen usuarios registrados");
        }

        DataUsuario[] dataUsuarios = new DataUsuario[usuarios.length];
        for (int i = 0; i < usuarios.length; i++) {
            Usuario usuario = usuarios[i];
            dataUsuarios[i] = new DataUsuario(usuario.getNombre(), usuario.getNickname(), usuario.getEmail());
        }

        return dataUsuarios;
    }

    public void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario usuario = mu.getUsuarioEmail(email);
        
        if (usuario == null) {
            throw new UsuarioNoExisteException("No existe usuario con email: " + email);
        }
        
        if (!(usuario instanceof Asistente)) {
            throw new UsuarioNoExisteException("El usuario con email " + email + " no es un asistente");
        }
        
        Asistente a = (Asistente) usuario;
        a.setNombre(nuevoNombre);
        a.setApellido(nuevoApellido);
    }
    
    public void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario usuario = mu.getUsuarioEmail(email);
        
        if (usuario == null) {
            throw new UsuarioNoExisteException("No existe usuario con email: " + email);
        }
        
        if (!(usuario instanceof Organizador)) {
            throw new UsuarioNoExisteException("El usuario con email " + email + " no es un organizador");
        }
        
        Organizador o = (Organizador) usuario;
        o.setNombre(nuevoNombre);
        o.setDescripcion(descripcion);
        o.setUrl(url);
    }
    
    public String getTipoUsuario(String email) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario usuario = mu.getUsuarioEmail(email);
        
        if (usuario == null) {
            throw new UsuarioNoExisteException("No existe usuario con email: " + email);
        }
        
        if (usuario instanceof Asistente) {
            return "Asistente";
        } else if (usuario instanceof Organizador) {
            return "Organizador";
        } else {
            return "Desconocido";
        }
    }
    
    public List<String> listarAsistentes() {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        List<String> asistentes = mu.getNombreAsist();
        return asistentes != null ? asistentes : new ArrayList<>();
    }
    
    public List<String> listarOrganizadores() {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        List<String> organizadores = mu.getOrganizadores();
        return organizadores != null ? organizadores : new ArrayList<>();
    }
    
    // Métodos pendientes de implementación (mantenidos por compatibilidad)
    public void nuevoRegistro(String asistenteSeleccionado, String evento, String edicion, String tipoReg, LocalDate fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro {
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    public List<ParEdicionRegistro> getRegistrosAsistente(String asistenteSeleccionado) {
        return new ArrayList<>();
    }
    
    public DataDetalleRegistro getDetallesRegistro(String asistenteSeleccionado, ParEdicionRegistro regEdicion) throws ErrorDetallesRegistroException {
        throw new UnsupportedOperationException("Método no implementado aún");
    }
}