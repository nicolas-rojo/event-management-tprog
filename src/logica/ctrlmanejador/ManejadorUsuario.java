package logica.ctrlmanejador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.Asistente;
import logica.Organizador;
import logica.Usuario;

public class ManejadorUsuario {
    private Map<String, Usuario> usuariosNickname;
    private Map<String, Usuario> usuariosEmail;
    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario() {
        usuariosNickname = new HashMap<String, Usuario>();
        usuariosEmail = new HashMap<String, Usuario>();
    }

    public static ManejadorUsuario getInstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }

    // Método para limpiar todos los datos (útil para testing)
    public void clear() {
        usuariosNickname.clear();
        usuariosEmail.clear();
    }
    
    // Método para resetear completamente la instancia (útil para testing)
    public static void resetInstance() {
        instancia = null;
    }

    public void addUsuario(Usuario usu) {
        String nick = usu.getNickname();
        usuariosNickname.put(nick, usu);
        
        String email = usu.getEmail();
        usuariosEmail.put(email, usu);
    }

    public Usuario getUsuarioNickname(String nick) {
        return usuariosNickname.get(nick);
    }
    
    public Usuario getUsuarioEmail(String email) {
        return usuariosEmail.get(email);
    }

    public Usuario[] getUsuarios() {
        if (usuariosNickname.isEmpty())
            return new Usuario[0]; // Retornar array vacío en lugar de null
        else {
            Collection<Usuario> usrs = usuariosNickname.values();
            return usrs.toArray(new Usuario[0]);
        }
    }
    
    public List<String> getNombreAsist() {
        List<String> res = new ArrayList<>();
        for (Usuario usuario : usuariosNickname.values()) {
            if (usuario instanceof Asistente) {
                res.add(usuario.getNickname());
            }
        }
        return res;
    }
    
    public List<String> getOrganizadores() {
        List<String> res = new ArrayList<>();
        for (Usuario usuario : usuariosNickname.values()) {
            if (usuario instanceof Organizador) {
                res.add(usuario.getNickname());
            }
        }
        return res;
    }
    
    // Método para obtener el número total de usuarios (útil para debugging)
    public int getTotalUsuarios() {
        return usuariosNickname.size();
    }
    
    // Método para verificar si existe un usuario por nickname
    public boolean existeUsuario(String nickname) {
        return usuariosNickname.containsKey(nickname);
    }
    
    // Método para verificar si existe un usuario por email
    public boolean existeUsuarioEmail(String email) {
        return usuariosEmail.containsKey(email);
    }
}