package logica;
import logica.datatypes.*;

public class TipoRegistro {
	private String nombre;
	private String descripcion;
	private float costo;
	private int cupo;
	
	public TipoRegistro(DataTRegistro dataTRegistro) {
		this.nombre = dataTRegistro.getNombre();
		this.descripcion = dataTRegistro.getDescr();
		this.costo = dataTRegistro.getCosto();
		this.cupo = dataTRegistro.getCupo();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public int getCupo() {
		return cupo;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String desc) {
		this.descripcion = desc;
	}
	
	public void setCosto(float c) {
		this.costo = c;
	}
	
	public void setCupo(int c) {
		this.cupo = c;
	}
	
	public boolean tieneCupo() {
		return (cupo > 0);
	}
	
	public DataTRegistro getDataTRegistro() {
		return new DataTRegistro(this.nombre, this.descripcion, this.costo, this.cupo);
	}
	
	public void bajarCupo() {
		this.cupo -= 1;
	}
		
}
