package logica.datatypes;

public class DataInstitucion {
	private String nombre;
	private String descripcion;
	private String url;
	
	public DataInstitucion(String nombre, String desc, String url) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.url = url;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getUrl() {
		return this.url;
	}
}
