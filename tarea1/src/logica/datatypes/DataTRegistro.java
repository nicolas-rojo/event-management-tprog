package logica.datatypes;

public class DataTRegistro {
	private String nombre;
	private String descripcion;
	private float costo;
	private int cupo;
	
	public DataTRegistro(String nombre, String descripcion, float costo, int cupo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.cupo = cupo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescr() {
		return this.descripcion;
	}
	
	public float getCosto() {
		return this.costo;
	}
	
	public int getCupo() {
		return this.cupo;
	}
}

	
