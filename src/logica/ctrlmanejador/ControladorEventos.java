package logica.ctrlmanejador;

import logica.interfaces.IEventos;

import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.EdicionRepetidaExcepcion;

import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicion;
import java.util.List;

import logica.Evento;
import logica.EdicionEvento;


public class ControladorEventos implements IEventos {
	
	public ControladorEventos() {
		
	}
	
	
	public void nuevoEvento(DataEvento dataEvento, String cat) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(dataEvento.getNombre());
		if (e != null) {
			throw new EventoRepetidoExcepcion("nombre de evento en uso");
		}
		else if(dataEvento.getCategoria() == null)
			throw new EventoSinCategoriaExcepcion("falto ingresar una categoria");
		else {
			e = new Evento(dataEvento);
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
	
	public List<String> listarEdiciones(String eventoSeleccionado) {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(eventoSeleccionado);
		return e.getEdiciones();
	}
	
	public List<String> listarTRegistros(String eventoSeleccionado, String edicionSeleccionada) {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(eventoSeleccionado);
		return e.getTRegistroEdicion(edicionSeleccionada);
	}
}














