package logica;

import java.time.LocalDate;

public class Registro {
	private LocalDate fechaReg;
	private EdicionEvento edi;
	private TipoRegistro treg;
	
	public Registro(LocalDate fecha) {
		this.fechaReg = fecha;
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
	
	public boolean esEdicion(String edicion) {
		return (edi.getNombre().equals(edicion));
	}
	
	public void asociarEdicion(EdicionEvento edev) {
		this.edi = edev;
	}
	
	public void asociarTRegistro(TipoRegistro treg) {
		this.treg = treg;
	}
}