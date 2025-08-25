package logica;

import java.time.LocalDate;

public class Asistente extends Usuario{
	private String apellido;
	private LocalDate fechaNac;
	
	public Asistente(String nombre, String nickname, String email, String apellido, LocalDate fechaNac){
		super(nombre, nickname, email);
        this.apellido = apellido;
        this.fechaNac = fechaNac;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }
}