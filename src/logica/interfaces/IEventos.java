package logica.interfaces;

import java.util.List;

import excepciones.EdicionRepetidaExcepcion;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.EventoNoExisteExcepcion;
import excepciones.TipoDeRegistroRepetidoException;

import logica.datatypes.DTOEvento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicionEvento;
import logica.datatypes.DataTRegistro;
import logica.EdicionEvento;

public interface IEventos {

		public abstract void nuevoEvento(DataEvento dataEvento, String cat) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion;
		
		public abstract List<String> listarEventos();
		
		public abstract List<String> listarEdiciones(String eventoSeleccionado);
		
		public abstract List<String> listarTRegistros(String eventoSeleccionado, String edicionSeleccionada);
		
		public abstract void nuevaEdicion(DataEdicion dataEdicion) throws EdicionRepetidaExcepcion;
		
		public abstract DTOEvento[] listarInfoEvento() throws EventoNoExisteExcepcion;
		
		public abstract void nuevoTipoRegistro(DataTRegistro dataTRegistro, String evento, String edicion) throws TipoDeRegistroRepetidoException;
		
		public abstract DataTRegistro getDataTRegistro(String evento, String edicion, String tipoRegistro);
		
		public abstract EdicionEvento obtenerEdicionEvento(String nombreEvento, String nombreEdicionEvento);
		
		public abstract DataEdicionEvento[] getEdicionesEventoOrganizador(String nickname);
}
