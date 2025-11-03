package logica;

import java.time.LocalDate;

public class Registro {
	private LocalDate fechaReg;
	private EdicionEvento edi;
	private TipoRegistro treg;
	private boolean asistio;
	
	public Registro(LocalDate fecha) {
		this.fechaReg = fecha;
		this.asistio = false;
	}
	
	public LocalDate getFecha() {
		return this.fechaReg;
	}
	
	public TipoRegistro getTipoRegistro() {
		return this.treg;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fechaReg = fecha;
	}
	
	public String getNombreEdicion() {
		return this.edi.getNombre();
	}
	
	public String getNombreTR() {
		return this.treg.getNombre();
	}
	
	public float getCostoTR() {
		return this.treg.getCosto();
	}
	
	public boolean getAsistencia() {
		return this.asistio;
	}
	
	public void setAsistencia(boolean asistencia) {
		this.asistio = asistencia;
	}
	
	public boolean esEdicion(String edicion) {
		return edi.getNombre().equals(edicion);
	}
	
	public void asociarEdicion(EdicionEvento edev) {
		this.edi = edev;
	}
	
	public void asociarTRegistro(TipoRegistro treg) {
		this.treg = treg;
	}
}