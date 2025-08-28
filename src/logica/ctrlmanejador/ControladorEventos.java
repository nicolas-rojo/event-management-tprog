package logica.ctrlmanejador;

import logica.interfaces.IEventos;

import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.AsistenteYaRegistrado;
import excepciones.EdicionRepetidaExcepcion;

import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicion;

import java.time.LocalDate;
import java.util.List;

import logica.Evento;
import logica.Registro;
import logica.TipoRegistro;
import logica.Asistente;
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
	
	public void nuevoRegistro(Asistente asist, String evento, String edicion, String tipoReg) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro {
		boolean c1 = asist.estaRegistrado(edicion);
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(evento);
		boolean c2 = e.cupoEdTRegistro(edicion, tipoReg);
		if (c1) {
			throw new AsistenteYaRegistrado("el asistente ya está registrado a la edicion seleccionada");
		} else if (!c2) {
			throw new NoHayCupoEdicionTRegistro("no hay cupos para el tipo de registro y edicion seleccionados");
		} else { //!c1 && c2
			Registro reg = new Registro(LocalDate.now());
			asist.agregarRegistro(reg);
			EdicionEvento ed = e.getEdicion(edicion);
			TipoRegistro tReg = ed.getTRegistro(tipoReg);
			reg.asociarEdicion(ed);
			reg.asociarTRegistro(tReg);
		}
	}
}














