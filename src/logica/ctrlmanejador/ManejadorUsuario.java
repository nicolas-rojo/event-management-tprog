package logica.ctrlmanejador;

import java.util.Collection;
import java.util.HashMap;
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

    public static ManejadorUsuario getinstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }

    public void addUsuario(Usuario usu) {
        String nick = usu.getNickname();
        usuariosNickname.put(nick, usu);
        
        String email = usu.getEmail();
        usuariosEmail.put(email, usu);
    }

    public Usuario obtenerUsuarioNickname(String nick) {
        return ((Usuario) usuariosNickname.get(nick));
    }
    
    public Usuario obtenerUsuarioEmail(String email) {
        return ((Usuario) usuariosEmail.get(email));
    }

    public Usuario[] getUsuarios() {
        if (usuariosNickname.isEmpty())
            return null;
        else {
            Collection<Usuario> usrs = usuariosNickname.values();
            Object[] o = usrs.toArray();
            Usuario[] usuarios = new Usuario[o.length];
            for (int i = 0; i < o.length; i++) {
                usuarios[i] = (Usuario) o[i];
            }
            return usuarios;
        }
    }

}
