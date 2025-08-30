package logica;

import java.time.LocalDate;

public class Registro {
	private LocalDate fechaReg;
	private EdicionEvento ed;
	private TipoRegistro tr;
	
	public Registro(LocalDate f) {
		this.fechaReg = f;
	}
	
	public LocalDate getFecha() {
		return this.fechaReg;
	}
	
	public TipoRegistro getTipoRegistro() {
		return this.tr;
	}
	
	public void setFecha(LocalDate f) {
		this.fechaReg = f;
	}
	
	public boolean esEdicion(String edicion) {
		return (ed.getNombre().equals(edicion));
	}
	
	public void asociarEdicion(EdicionEvento ed) {
		this.ed = ed;
	}
	
	public void asociarTRegistro(TipoRegistro tr) {
		this.tr = tr;
	}
}