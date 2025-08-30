package logica;

import java.time.LocalDate;

import logica.datatypes.*;

public class Patrocinio {
	private LocalDate fecha;
	private float monto;
	private Nivel nivel;
	private int cod;
	private int ctdCupo;
	
	private TipoRegistro tipoRegistro;
	private Institucion institucion;
	private EdicionEvento edicion;
	
	public Patrocinio(DataPatrocinio datos) {
		this.fecha = datos.getFecha();
		this.monto = datos.getMonto();
		this.nivel = datos.getNivel();
		this.cod = datos.getCod();
		this.ctdCupo = datos.getCtdCupo();	
		this.tipoRegistro = null;
		this.institucion = null;
		this.edicion = null;
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
	
	public TipoRegistro getTipoRegistro() {
		return this.tipoRegistro;
	}
	
	public Institucion getInstitucion() {
		return this.institucion;
	}
	
	public EdicionEvento getEdicionEvento() {
		return this.edicion;
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
	
	public void setTipoRegistro(TipoRegistro t) {
		this.tipoRegistro = t;
	}
	
	public void setInstitucion(Institucion i) {
		this.institucion = i;
	}
	
	public void setEdicionEvento(EdicionEvento ee) {
		this.edicion = ee;
	}
}
