package logica.ctrlmanejador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return ((Usuario) usuariosNickname.get(nick));
    }
    
    public Usuario getUsuarioEmail(String email) {
        return ((Usuario) usuariosEmail.get(email));
    }

    public Usuario[] getUsuarios() {
        if (usuariosNickname.isEmpty())
            return null;
        else {
            Collection<Usuario> usrs = usuariosNickname.values();
            Object[] obj = usrs.toArray();
            Usuario[] usuarios = new Usuario[obj.length];
            for (int i = 0; i < obj.length; i++) {
                usuarios[i] = (Usuario) obj[i];
            }
            return usuarios;
        }
    }
    
    public List<String> getNombreAsist() {
    	List<String> res = new ArrayList<>();
    	for (Usuario e: this.usuariosNickname.values()) {
    		if (e.esAsistente())
    			res.add(e.getNickname());
    	}
    	return res;
    }
    
    public List<String> getOrganizadores() {
    	List<String> res = new ArrayList<>();
    	for (Usuario e: this.usuariosNickname.values()) {
    		if (e.esOrganizador())
    			res.add(e.getNickname());
    	}
    	return res;
    }

}
