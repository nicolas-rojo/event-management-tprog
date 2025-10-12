package logica.ctrlmanejador;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import logica.Evento;
import logica.Categoria;
import java.util.ArrayList;
import java.util.Collection;

public class ManejadorEvento {
    private static ManejadorEvento instancia = null;
    private Map<String, Evento> eventos;
    private Map<String, Categoria> categorias;
    
    private ManejadorEvento() {
        eventos = new HashMap<String, Evento>();
        categorias = new HashMap<String, Categoria>();
    }
    
    public static ManejadorEvento getInstance() {
        if (instancia == null) {
            instancia = new ManejadorEvento();
        }
        return instancia;
    }
    
    public static void resetInstance() {
    	instancia = null;
    }
    
    public Set<Evento> getEventosConCategoria(String cat){
    	Categoria cate = categorias.get(cat);
    	return cate.getEventos();
    }
    
    public void addEvento(Evento evento) {
        String nombre = evento.getNombreEvento();
        eventos.put(nombre, evento);
    }
    
    public Evento getEvento(String nombre) {
        return eventos.get(nombre);
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
    
    //VERSION NUESTRA, NO ESTABA EN DEVELOP (CREO)
    public List<String> getCategoriasClave() {
        return new ArrayList<>(this.categorias.keySet());
    }

    public void agregarCategoria(String cat, Categoria categoria) {
        this.categorias.put(cat, categoria);
    }
    
    public Evento[] getEventosTipoEvento() {
        if (eventos.isEmpty())
            return null;
        else {
            Collection<Evento> allEventos = eventos.values();
            Object[] obj = allEventos.toArray();
            Evento[] eventosArray = new Evento[obj.length];
            for (int i = 0; i < obj.length; i++) {
                eventosArray[i] = (Evento) obj[i];
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
    
    public String eventoTieneEdicion(String edicion) {
    	for (Evento e : this.eventos.values()) {
    		if (e.getEdicion(edicion) != null)
    			return e.getNombreEvento();
    	}
    	return "";
    }
}