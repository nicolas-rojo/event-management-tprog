package logica.datatypes;

import java.time.LocalDate;

public class DataDetalleRegistro {
	private String nombreEdicion;
	private String nombreTR;
	private float costo;
	private LocalDate fecha;
	
	public DataDetalleRegistro(String nombreEd, String nombreTR, float costo, LocalDate fecha) {
		this.nombreEdicion = nombreEd;
		this.nombreTR = nombreTR;
		this.costo = costo;
		this.fecha = fecha;
	}
	
	public String getNombreEdicion() {
		return this.nombreEdicion;
	}
	
	public String getNombreTR() {
		return this.nombreTR;
	}
	
	public float getCosto() {
		return this.costo;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
}

