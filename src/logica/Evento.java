package logica;

import java.time.LocalDate;
import java.util.Set;
import logica.datatypes.DataEvento;
import java.util.HashMap;
import java.util.HashSet;

public class Evento {
	private String nombre;
	private String descripcion;
	private LocalDate fechaAlta;
	private String sigla;
	private HashMap<String, EdicionEvento> ediciones;
	private Set<Categoria> categorias;
	
	public Evento(DataEvento dataevento) {
		this.nombre = dataevento.getNombre();
		this.descripcion = dataevento.getDescripcion();
		this.fechaAlta = dataevento.getFechaAlta();
		this.sigla = dataevento.getSigla();
		this.ediciones = new HashMap<>();
		this.categorias = new HashSet<>();
		
	}
	
	public String getNombreEvento() {
		return this.nombre;
	}
	
	public String getDescripcionEvento() {
		return this.descripcion;
	}
	
	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}
	
	public String getSigla() {
		return this.sigla;
	}
	
	public HashMap<String,EdicionEvento> getEdiciones(){
		return this.ediciones;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setFechaAlta(LocalDate fecha) {
		this.fechaAlta = fecha;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
}

