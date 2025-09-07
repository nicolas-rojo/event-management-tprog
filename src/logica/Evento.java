package logica;

import java.time.LocalDate;
import java.util.Set;
import logica.datatypes.DataEvento;
import logica.datatypes.DataEventoCompleto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Evento {
	private String nombre;
	private String sigla;
	private LocalDate fechaAlta;
	private String descripcion;
	
	private HashMap<String, EdicionEvento> ediciones;
	private Set<Categoria> categorias;
	
	public Evento(DataEvento dataevento) {
		this.nombre = dataevento.getNombre();
		this.sigla = dataevento.getSigla();
		this.fechaAlta = dataevento.getFechaAlta();
		this.descripcion = dataevento.getDesc();
		
		this.ediciones = new HashMap<>();
		this.categorias = new HashSet<>();
	}
	
	public String getNombreEvento() {
		return this.nombre;
	}
	
	public String getSigla() {
		return this.sigla;
	}
	
	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}
	
	public String getDesc() {
		return this.descripcion;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public void setFechaAlta(LocalDate f) {
		this.fechaAlta = f;
	}
	
	public void setDesc(String desc) {
		this.descripcion = desc;
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
	
	public DataEventoCompleto getDTOEvento() {
	    Set<String> catStrings = new HashSet<>();
	    for (Categoria c : categorias) {
	        catStrings.add(c.getNombre());
	    }
	    Set<String> edStrings = new HashSet<>(ediciones.keySet());
	    return new DataEventoCompleto(nombre, sigla, fechaAlta, descripcion, catStrings, edStrings);
	}
	
	public List<String> getTRegistroEdicion(String edSeleccionada) {
	    EdicionEvento ed = this.getEdicion(edSeleccionada);
	    return ed.getTRegistro();
	}
	
	public boolean cupoEdTRegistro(String edicion, String tReg) {
	    EdicionEvento ed = this.ediciones.get(edicion);
	    return ed.cupoTRegistro(tReg);
	}
	
	public void agregarCategoria(Categoria c) {
		this.categorias.add(c);
	}
}