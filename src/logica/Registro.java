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
	
	public void setFecha(LocalDate f) {
		this.fechaReg = f;
	}
	
	public String getNombreEdicion() {
		return this.ed.getNombre();
	}
	
	public String getNombreTR() {
		return this.tr.getNombre();
	}
	
	public float getCostoTR() {
		return this.tr.getCosto();
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