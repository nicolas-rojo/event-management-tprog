package logica;

import java.time.LocalDate;
import java.util.Set;
import logica.datatypes.DataEvento;
import logica.datatypes.DTOEvento;
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
	
	public EdicionEvento getEdicion(String nombre) {
		return (this.ediciones.get(nombre));
		
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
	
	public void agregarEdicion(EdicionEvento edicion) {
		this.ediciones.put(edicion.getNombre(), edicion);
	}
	
	public DTOEvento getDTOEvento() {
	    //categorías y ediciones a Sets de Strings
	    Set<String> catStrings = new HashSet<>();
	    for (Categoria c : categorias) {
	        catStrings.add(c.getNombre());
	    }
	    Set<String> edStrings = new HashSet<>(ediciones.keySet()); //denuevo lo hice con una copia pero como prefieran

	    return new DTOEvento(nombre, sigla, fechaAlta, descripcion, catStrings, edStrings);
	}

}

