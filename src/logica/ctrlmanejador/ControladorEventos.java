package logica.ctrlmanejador;

import logica.interfaces.IEventos;
import excepciones.TipoDeRegistroRepetidoException;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.EdicionRepetidaExcepcion;

import logica.datatypes.DataEvento;
import logica.datatypes.DataTRegistro;
import logica.datatypes.DataEdicion;
import java.util.List;

import logica.Evento;
import logica.TipoRegistro;
import logica.EdicionEvento;


public class ControladorEventos implements IEventos {
	
	public ControladorEventos() {
		
	}
	
	public void nuevoTipoRegistro(DataTRegistro dataTRegistro, String evento, String edicion) throws TipoDeRegistroRepetidoException {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento eventoTRegistro = me.getEvento(evento);
		EdicionEvento edicionTRegistro = eventoTRegistro.getEdicion(edicion);
		if(edicionTRegistro.getTRegistro().contains(dataTRegistro.getNombre())) {
			throw new TipoDeRegistroRepetidoException("El Tipo de Registro ingresado ya existe en el sistema");
		}else {
			edicionTRegistro.agregarTRegistro(new TipoRegistro(dataTRegistro));
		}
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
	
	
	public DataTRegistro getDataTRegistro(String evento, String edicion, String tipoRegistro) {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(evento);
		EdicionEvento ed = e.getEdicion(edicion);
		TipoRegistro tr =  ed.getTRegistro(tipoRegistro);
		return new DataTRegistro(tr.getNombre(), tr.getDescripcion(), tr.getCosto(), tr.getCupo());
	}
}














