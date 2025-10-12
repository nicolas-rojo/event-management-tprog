package logica;

import java.time.LocalDate;

import logica.datatypes.DataPatrocinio;
import logica.datatypes.Nivel;

public class Patrocinio {
	private LocalDate fecha;
	private float monto;
	private Nivel nivel;
	private String cod;
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
	
	public String getCod() {
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
	
	public void setTipoRegistro(TipoRegistro treg) {
		this.tipoRegistro = treg;
	}
	
	public void setInstitucion(Institucion ins) {
		this.institucion = ins;
	}
	
	public void setEdicionEvento(EdicionEvento edev) {
		this.edicion = edev;
	}
}
