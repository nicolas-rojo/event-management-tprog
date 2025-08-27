package logica.interfaces;

import java.util.List;

import excepciones.EdicionRepetidaExcepcion;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;

public interface IEventos {

		public abstract void nuevoEvento(DataEvento dataEvento, String cat) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion;
		
		public abstract List<String> listarEventos();
		
		public abstract void nuevaEdicion(DataEdicion dataEdicion) throws EdicionRepetidaExcepcion;
		
}
