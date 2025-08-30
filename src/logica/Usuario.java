package logica;

public abstract class Usuario {

	private String nickname;
    private String nombre;
    private String email;

    public Usuario(String nombre, String nickname, String email) {
    	this.nombre = nombre;
    	this.nickname = nickname;
        this.email = email;
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

    public void setNickname(String nickname) {
    	this.nickname = nickname;
    }

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public void setCedulaIdentidad(String email) {
        this.email = email;
    }
    
    public abstract boolean esAsistente();
}