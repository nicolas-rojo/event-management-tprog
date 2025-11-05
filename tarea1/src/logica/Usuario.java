package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String nickname;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(nullable = false, unique = true, length = 200)
    private String email;
    
    @Column(nullable = false, length = 200)
    private String pass;
    
    @Transient
    private Map<String, Usuario> seguidos;
    
    @Transient
    private Map<String, Usuario> seguidores;
    
    // Constructor sin parámetros requerido por JPA
    public Usuario() {
        this.seguidos = new HashMap<>();
        this.seguidores = new HashMap<>();
    }

    public Usuario(String nombre, String nickname, String email, String pass) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
        this.seguidos = new HashMap<>();
        this.seguidores = new HashMap<>();        
    }

    // Getters y setters (sin cambios)
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