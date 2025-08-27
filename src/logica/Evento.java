package logica;

import java.time.LocalDate;
import java.util.Set;
import logica.datatypes.DataEvento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Evento {
	private String nombre;
	private String sigla;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private LocalDate fechaAlta;
	private String ciudad;
	private String pais;
	
	private HashMap<String, EdicionEvento> ediciones;
	private Set<Categoria> categorias;
	
	public Evento(DataEvento dataevento) {
		this.nombre = dataevento.getNombre();
		this.sigla = dataevento.getSigla();
		this.fechaIni = dataevento.getFechaIni();
		this.fechaFin = dataevento.getFechaFin();
		this.fechaAlta = dataevento.getFechaAlta();
		this.ciudad = dataevento.getCiudad();
		this.ciudad = dataevento.getPais();
		
		this.ediciones = new HashMap<>();
		this.categorias = new HashSet<>();
	}
	
	public String getNombreEvento() {
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
	
	public void setFechaIni(LocalDate f) {
		this.fechaIni = f;
	}
	
	public void setFechaFin(LocalDate f) {
		this.fechaFin = f;
	}
	
	public void setFechaAlta(LocalDate f) {
		this.fechaAlta = f;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public List<String> getEdiciones(){
		List<String> res = new ArrayList<>();
		for(EdicionEvento e : this.ediciones.values()) {
			res.add(e.getNombre());
		}
		return res;
	}
	
	public EdicionEvento getEdicion(String nombre) {
		return (this.ediciones.get(nombre));
	}
	
	public void agregarEdicion(EdicionEvento edicion) {
		this.ediciones.put(edicion.getNombre(), edicion);
	}
	
	public List<String> getTRegistroEdicion(String edSeleccionada) {
		EdicionEvento ed = this.getEdicion(edSeleccionada);
		return ed.getTRegistro();
	}
}

