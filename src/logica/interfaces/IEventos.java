package logica.interfaces;

import java.util.List;

import excepciones.CategoriaRepetidaException;
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

		public abstract void nuevoEvento(DataEvento dataEvento, List<String> cats) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion;
		
		public abstract void nuevaCategoria(String cat) throws CategoriaRepetidaException;
		
		public abstract List<String> listarEventos();
		
		public abstract List<String> listarCategorias();
		
		public abstract List<String> listarEdiciones(String eventoSeleccionado);
		
		public abstract List<String> listarTRegistros(String eventoSeleccionado, String edicionSeleccionada);
		
		public abstract void nuevaEdicion(DataEdicion dataEdicion, String evento, String org) throws EdicionRepetidaExcepcion;
		
		public abstract DTOEvento[] listarInfoEvento() throws EventoNoExisteExcepcion;
		
		public abstract void nuevoTipoRegistro(DataTRegistro dataTRegistro, String evento, String edicion) throws TipoDeRegistroRepetidoException;
		
		public abstract DataTRegistro getDataTRegistro(String evento, String edicion, String tipoRegistro);
		
		public abstract EdicionEvento obtenerEdicionEvento(String nombreEvento, String nombreEdicionEvento);
		
		public abstract DataEdicion getDataEdicion(String evento, String edicion);
		
		public abstract DataEdicionEvento[] getEdicionesEventoOrganizador(String nickname);
		
		public abstract boolean existeEvento(String eventoSeleccionado);
		
		public abstract boolean existeEdicion(String evento, String edicion);
		
		public abstract boolean existeTR(String evento, String edicion, String tr);
		
		public abstract String eventoTieneEdicion(String edicion);
}
