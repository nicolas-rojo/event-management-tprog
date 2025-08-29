package logica.datatypes;

import java.time.LocalDate;

public class DataPatrocinio {
	private LocalDate fecha;
	private float monto;
	private Nivel nivel;
	private int cod;
	private int ctdCupo;
	
	public DataPatrocinio(LocalDate fecha, float monto, Nivel nivel, int cod, int ctdCupo) {
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
	
	public int getCod() {
		return cod;
	}
	
	public int getCtdCupo() {
		return ctdCupo;
	}
	
	public void setFecha(LocalDate f) {
		this.fecha = f;
	}
	
	public void setMonto(float m) {
		this.monto = m;
	}
	
	public void setNivel(Nivel n) {
		this.nivel = n;
	}
	
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public void setCupo(int cupo) {
		this.ctdCupo = cupo;
	}
}
