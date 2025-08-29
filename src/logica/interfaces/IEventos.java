package logica.interfaces;

import java.util.List;

import excepciones.EdicionRepetidaExcepcion;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.TipoDeRegistroRepetidoException;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTRegistro;

public interface IEventos {

		public abstract void nuevoEvento(DataEvento dataEvento, String cat) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion;
		
		public abstract List<String> listarEventos();
		
		public abstract List<String> listarEdiciones(String eventoSeleccionado);
		
		public abstract List<String> listarTRegistros(String eventoSeleccionado, String edicionSeleccionada);
		
		public abstract void nuevaEdicion(DataEdicion dataEdicion) throws EdicionRepetidaExcepcion;
		
		public abstract void nuevoTipoRegistro(DataTRegistro dataTRegistro, String evento, String edicion) throws TipoDeRegistroRepetidoException;
}
