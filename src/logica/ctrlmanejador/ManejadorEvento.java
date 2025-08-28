package logica.ctrlmanejador;

import java.util.HashMap;
import java.util.List;

import logica.Evento;
import logica.Usuario;

import java.util.ArrayList;
import java.util.Collection;

public class ManejadorEvento {
	private static ManejadorEvento instancia = null;
	private HashMap<String, Evento> eventos;
	
	private ManejadorEvento() {
		eventos = new HashMap<String, Evento>();
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
	
	public Evento[] getEventos() {
		if (eventos.isEmpty())
            return null;
        else {
            Collection<Evento> eventos = eventos.values();
            Object[] o = eventos.toArray();
            Evento[] eventosArray = new Evento[o.length];
            for (int i = 0; i < o.length; i++) {
                eventosArray[i] = (Evento) o[i];
            }
            return eventosArray;
        }
	}
	

	
	public List<String> getEventos(){
		List<String> eventos = new ArrayList<>();
		for (Evento e : this.eventos.values()) {
			eventos.add(e.getNombreEvento());
		}
		return eventos;
		
	}
}
