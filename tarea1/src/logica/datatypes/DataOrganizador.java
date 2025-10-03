package logica.datatypes;

public class DataOrganizador extends DataUsuario{
	private String descripcion;
	private String url;
	
	public DataOrganizador(String nombre, String nickname, String email, String pass, String desc, String url) {
		super(nombre, nickname, email, pass);
		this.descripcion = desc;
		this.url = url;
	}

	public String getDescripcion() {
        return descripcion;
    }
	
	public String getUrl() {
        return url;
    }
}