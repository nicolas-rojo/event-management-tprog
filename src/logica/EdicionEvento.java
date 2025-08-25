package logica;

import java.time.LocalDate;
import java.util.Set;
import logica.datatypes.DataEdicion;

public class EdicionEvento {
	private String nombre;
	private String sigla;
	private LocalDate fechaIni;
	private LocalDate FechaFin;
	private LocalDate FechaAlta;
	private Organizador organizador;
	/*private Set<TipoRegistro> tipoRegistros;
	private Set<Patrocinio> patrocinios; va a tener estos atributos, pero no implemento todavia*/
	private String ciudad;
	private String pais;
	private Evento evento;
	
	public EdicionEvento(DataEdicion dataEvento) {
		this.nombre = dataEvento.getNombre();
		this.sigla = dataEvento.getSigla();
		this.FechaAlta = dataEvento.getFechaAlta();
		this.fechaIni = dataEvento.getFechaIni();
		this.FechaFin = dataEvento.getFechaFin();
		this.organizador = organizador;
		this.ciudad = dataEvento.getCiudad();
		this.pais = dataEvento.getPais();
		this.evento = dataEvento.getEvento();
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getSigla() {
		return this.sigla;
	}
	
	public LocalDate getFechaIni() {
		return this.fechaIni;
	}
	
	public LocalDate getFechaFin() {
		return this.FechaFin;
	}
	
	public LocalDate getFechaAlta() {
		return this.FechaAlta;
	
	}
	
	public Organizador getOrganizador() {
		return this.organizador;
	}
	
	public Evento getEvento(){
		return this.evento;
	}
	
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public String getPais() {
		return this.pais;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public void setFechaIni(LocalDate fecha) {
		this.fechaIni = fecha;
	}
	
	public void setFechaAlta(LocalDate fecha) {
		this.FechaAlta = fecha;
	}
	
	public void setFechaFin(LocalDate fecha) {
		this.FechaFin = fecha;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}
