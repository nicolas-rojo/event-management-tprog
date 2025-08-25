package logica;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ManejadorEvento {
	private static ManejadorEvento instancia = null;
	private HashMap<String, Evento> eventos;
	
	private ManejadorEvento() {
		eventos = new HashMap<String, Evento>();
	}
	
	private ManejadorEvento getInstance() {
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
	
	public List<String> getEventos(){
		List<String> eventos = new ArrayList<>();
		for (Evento e : this.eventos.values()) {
			eventos.add(e.getNombreEvento());
		}
		return eventos;
		
	}
}
