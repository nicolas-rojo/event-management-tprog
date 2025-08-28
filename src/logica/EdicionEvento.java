package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logica.datatypes.DataEdicion;

public class EdicionEvento {
	private String nombre;
	private String sigla;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private LocalDate fechaAlta;
	private String ciudad;
	private String pais;
	
	private Organizador organizador;
	private Set<TipoRegistro> tipoRegistros;
	private Set<Patrocinio> patrocinios;
	private List<Registro> registros;
	
	public EdicionEvento(DataEdicion dataEd) {
		this.nombre = dataEd.getNombre();
		this.sigla = dataEd.getSigla();
		this.fechaIni = dataEd.getFechaIni();
		this.fechaFin = dataEd.getFechaFin();
		this.fechaAlta = dataEd.getFechaAlta();
		this.ciudad = dataEd.getCiudad();
		this.pais = dataEd.getPais();
		
		this.tipoRegistros = new HashSet<>();
		this.patrocinios = new HashSet<>();
		this.registros = new ArrayList<>();
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
		return this.fechaFin;
	}
	
	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	
	}
	
	public Organizador getOrganizador() {
		return this.organizador;
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
		this.fechaAlta = fecha;
	}
	
	public void setFechaFin(LocalDate fecha) {
		this.fechaFin = fecha;
	}
	
	public List<String> getTRegistro() {
		List<String> res = new ArrayList<>();
		for (TipoRegistro e : this.tipoRegistros) {
			res.add(e.getNombre());
		}
		return res;
	}
}
