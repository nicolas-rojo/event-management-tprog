package logica.datatypes;

public class DataUsuario {
    private String nombre;
    private String nickname;
    private String email;
    private String pass;

    public DataUsuario(String nombre, String nickname, String email, String pass) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return nickname + " - " + nombre + " (" + email + ")";
    }
}