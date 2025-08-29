package logica.datatypes;

import java.time.LocalDate;

public class ParEdicionRegistro{
	private String nombreEdicion;
	private LocalDate fechaRegistro;
	
	public ParEdicionRegistro(String nombreEdicion, LocalDate fechaRegistro) {
		this.nombreEdicion = nombreEdicion;
		this.fechaRegistro = fechaRegistro;
	}
	
	public String getNombreEdicion() {
		return this.nombreEdicion;
	}

	public LocalDate getFechaRegistro() {
		return this.fechaRegistro;
	}
	
	@Override
	public String toString() {
		return nombreEdicion + " - " + fechaRegistro;
	}
}