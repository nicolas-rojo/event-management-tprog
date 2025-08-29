package logica.datatypes;
import java.time.LocalDate;

public class DataEvento {
	private String nombre;
	private String sigla;
	private LocalDate fechaAlta;
	private String descripcion;

	public DataEvento(String nombre, String sigla, LocalDate fechaAlta, String descripcion) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.fechaAlta = fechaAlta;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSigla() {
		return sigla;
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	    
	public String getDesc() {
		return descripcion;
	}

	@Override
	public String toString() {
		return nombre + " - " + sigla + " " + fechaAlta + "" + "descripcion";
	}
}