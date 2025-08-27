package logica;
import logica.datatypes.*;

public class TipoRegistro {
	private String nombre;
	private String descripcion;
	private float costo;
	private int cupo;
	
	public TipoRegistro(String nombre, String descripcion, float costo, int cupo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.cupo = cupo;
	}
	
	public boolean tieneCupo() {
		boolean cupo = false;
		if(this.cupo> 0) {
			cupo = true;
		}
		return cupo;
	}
	public DataTRegistro getDataTRegistro() {
		return new DataTRegistro(this.nombre, this.descripcion, this.costo, this.cupo);
	}
		
}
