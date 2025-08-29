package logica.datatypes;

import java.time.LocalDate;

public class DataAsistente extends DataUsuario{
	private String apellido;
	private LocalDate fechaNac;
	
	public DataAsistente(String nombre, String nickname, String email, String apellido, LocalDate fechaNac) {
		super(nombre, nickname, email);
		this.apellido = apellido;
		this.fechaNac = fechaNac;
	}
	
	public String getApellido() {
		return this.apellido;
	}

	public LocalDate getFechaNac() {
		return this.fechaNac;
	}
}