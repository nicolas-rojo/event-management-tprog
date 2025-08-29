package logica.ctrlmanejador;

import java.util.HashMap;
import java.util.List;

import logica.Evento;
import logica.Usuario;
import logica.Categoria;

import java.util.ArrayList;
import java.util.Collection;

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
	
	public List<String> getCategoriasClave() {
	    return new ArrayList<>(this.categorias.keySet());
	}

	public void agregarCategoria(String cat, Categoria c) {
		this.categorias.put(cat, c);
	}

	
	public Evento[] getEventos() {
		if (eventos.isEmpty())
            return null;
        else {
            Collection<Evento> allEventos = eventos.values();
            Object[] o = allEventos.toArray();
            Evento[] eventosArray = new Evento[o.length];
            for (int i = 0; i < o.length; i++) {
                eventosArray[i] = (Evento) o[i];
            }
            return eventosArray;
        }
	}
	

	
	public List<String> getEventosNombre(){
		List<String> eventos = new ArrayList<>();
		for (Evento e : this.eventos.values()) {
			eventos.add(e.getNombreEvento());
		}
		return eventos;
		
	}
}
