package logica;

public abstract class Usuario {

	private String nickname;
    private String nombre;
    private String email;
    private String pass;

    public Usuario(String nombre, String nickname, String email, String pass) {
    	this.nombre = nombre;
    	this.nickname = nickname;
        this.email = email;
        this.pass = pass;
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
    
    public abstract boolean esAsistente();
    
    public abstract boolean esOrganizador();
}
	