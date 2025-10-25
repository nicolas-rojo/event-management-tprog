package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public abstract class Usuario {

	private String nickname;
    private String nombre;
    private String email;
    private String pass;
    private Map<String, Usuario> seguidos;
    private Map<String, Usuario> seguidores;
    

    public Usuario(String nombre, String nickname, String email, String pass) {
    	this.nombre = nombre;
    	this.nickname = nickname;
        this.email = email;
        this.pass = pass;
        
        this.seguidos = new HashMap<String, Usuario>();
        this.seguidores = new HashMap<String, Usuario>();        
    }

    public String getNickname() {
    	return nickname;
    }

    public String getNombre() {
    	return nombre;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPass() {
        return pass;
    }
    
    public List<String> getSeguidos() {
        return new ArrayList<>(seguidos.keySet());
    }

    public List<String> getSeguidores() {
        return new ArrayList<>(seguidores.keySet());
    }

    public void setNickname(String nickname) {
    	this.nickname = nickname;
    }

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public void setCedulaIdentidad(String email) {
        this.email = email;
    }
    
    public void setPass(String pass) {
    	this.pass = pass;
    }
    
    public void seguir(Usuario usr) {
    	this.seguidos.put(usr.getNickname(), usr);
    }
    
    public void nuevoSeguidor(Usuario usr) {
    	this.seguidores.put(usr.getNickname(), usr);
    }
    
    public abstract boolean esAsistente();
    
    public abstract boolean esOrganizador();
    
    public boolean esSeguidor(String seguidor) {
    	return (this.seguidores.containsKey(seguidor));
    }
    
    public void eliminarFollow(String nick) {
    	this.seguidos.remove(nick);
    }

    public void eliminarFollower(String nick) {
    	this.seguidores.remove(nick);
    }
}
	