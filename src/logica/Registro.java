package logica;

import java.time.LocalDate;

public class Registro {
	private LocalDate fechaReg;
	
	public Registro(LocalDate f) {
		this.fechaReg = f;
	}
	
	public LocalDate getFecha() {
		return this.fechaReg;
	}
	
	public void setFecha(LocalDate f) {
		this.fechaReg = f;
	}
}