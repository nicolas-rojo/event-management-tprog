package logica;

public class Organizador extends Usuario{
	private String descripcion;
	private String url;
	
	public Organizador(String nombre, String nickname, String email, String descripcion, String url){
		super(nombre, nickname, email);
        this.descripcion = descripcion;
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public String getUrl() {
        return url;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public boolean esAsistente() {
    	return false;
    }

	@Override
    public boolean esOrganizador() {
    	return true;
    }
}