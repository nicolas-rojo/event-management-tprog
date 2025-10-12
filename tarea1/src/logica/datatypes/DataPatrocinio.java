package logica.datatypes;

import java.time.LocalDate;

public class DataPatrocinio {
	private LocalDate fecha;
	private float monto;
	private Nivel nivel;
	private String cod;
	private int ctdCupo;
	
	public DataPatrocinio(LocalDate fecha, float monto, Nivel nivel, String cod, int ctdCupo) {
		this.fecha = fecha;
		this.monto = monto;
		this.nivel = nivel;
		this.cod = cod;
		this.ctdCupo = ctdCupo;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public float getMonto() {
		return monto;
	}
	
	public Nivel getNivel() {
		return nivel;
	}
	
	public String getCod() {
		return cod;
	}
	
	public int getCtdCupo() {
		return ctdCupo;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	public void setCupo(int cupo) {
		this.ctdCupo = cupo;
	}
}
