package logica;

public abstract class Usuario {

    private String nombre;
    private String nickname;
    private String email;

    public Usuario(String nombre, String nickname, String email) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.email = email;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCedulaIdentidad(String email) {
        this.email = email;
    }

}
