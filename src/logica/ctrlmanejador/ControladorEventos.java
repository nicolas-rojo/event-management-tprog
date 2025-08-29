package logica.ctrlmanejador;

import logica.interfaces.IEventos;

import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.EdicionRepetidaExcepcion;
import excepciones.EventoNoExisteExcepcion;

import logica.datatypes.DataEvento;
import logica.datatypes.DTOEvento;
import logica.datatypes.DataEdicion;
import java.util.List;

import logica.Evento;
import logica.EdicionEvento;
import logica.Categoria;

public class ControladorEventos implements IEventos {
	
	public ControladorEventos() {
	}
	
	
	public void nuevoEvento(DataEvento dataEvento, String cat) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(dataEvento.getNombre());
		if (e != null) {
			throw new EventoRepetidoExcepcion("nombre de evento en uso");
		}
		else if(cat.equals(""))
			throw new EventoSinCategoriaExcepcion("falto ingresar una categoria");
		else {
			if (cat == null || cat.isEmpty()) {
	            throw new EventoSinCategoriaExcepcion("Faltó ingresar una categoría");
	        }
			e = new Evento(dataEvento);
			Categoria c = me.getCategoria(cat);
			if (c == null) {
	            throw new EventoSinCategoriaExcepcion(
	                "La categoría seleccionada no existe en el sistema: " + cat);
	        }
			e.agregarCategoria(c);
			me.addEvento(e);
		}
		
	}

	
	public List<String> listarEventos(){
		ManejadorEvento me = ManejadorEvento.getInstance();
		return me.getEventos();
	}
	
	public void nuevaEdicion(DataEdicion dataEdicion) throws EdicionRepetidaExcepcion {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(dataEdicion.getEvento());
		EdicionEvento ee = e.getEdicion(dataEdicion.getNombre()); 
		if (ee != null) {
			throw new EdicionRepetidaExcepcion("nombre de edicion en uso");
		}
		else {
			ee = new EdicionEvento(dataEdicion);
			e.agregarEdicion(ee);
		}
	}
	
	public void listarInfoEvento(String nombre) {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(nombre);
		DataEvento data = e.getDataEvento();
		
	}
	
	public DTOEvento[] listarInfoEvento() throws EventoNoExisteExcepcion {
	    ManejadorEvento me = ManejadorEvento.getInstance();
	    Evento[] eventos = me.getEventos();

	    if (eventos != null && eventos.length > 0) {
	        DTOEvento[] dtoEventos = new DTOEvento[eventos.length];
	        for (int i = 0; i < eventos.length; i++) {
	            dtoEventos[i] = eventos[i].getDTOEvento();
	        }
	        return dtoEventos; // devolvemos el arreglo
	    } else {
	        throw new EventoNoExisteExcepcion("No existen eventos registrados");
	    }
	}

}
