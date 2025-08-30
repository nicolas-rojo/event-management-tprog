package logica.ctrlmanejador;

import java.util.HashMap;
import java.util.List;

import logica.Evento;
import logica.Categoria;

import java.util.ArrayList;

public class ManejadorEvento {
	private static ManejadorEvento instancia = null;
	private HashMap<String, Evento> eventos;
	private HashMap<String, Categoria> categorias;
	
	private ManejadorEvento() {
		eventos = new HashMap<String, Evento>();
		categorias = new HashMap<String, Categoria>();
	}
	
	public static ManejadorEvento getInstance() {
		if(instancia == null) {
			instancia = new ManejadorEvento();
		}
		return instancia;
	}
	
	public void addEvento(Evento evento) {
		String nombre = evento.getNombreEvento();
		eventos.put(nombre, evento);
	}
	
	
	public Evento getEvento(String nombre) {
		return (eventos.get(nombre));
	}
	
	public Categoria getCategoria(String nombre) {
		return this.categorias.get(nombre);
	}
	
	public List<String> getCategorias(){
		List<String> categorias = new ArrayList<>();
		if (this.categorias != null) {
			for (Categoria c : this.categorias.values()) {
				categorias.add(c.getNombre());
			}
		
	}
		return categorias;
	}
	
	public void agregarCategoria(String cat, Categoria c) {
		this.categorias.put(cat, c);
	}
	
	public List<String> getEventos(){
		List<String> eventos = new ArrayList<>();
		for (Evento e : this.eventos.values()) {
			eventos.add(e.getNombreEvento());
		}
		return eventos;
		
	}
}
