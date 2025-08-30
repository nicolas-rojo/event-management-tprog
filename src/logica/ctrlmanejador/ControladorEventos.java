package logica.ctrlmanejador;

import logica.interfaces.IEventos;
import excepciones.TipoDeRegistroRepetidoException;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.AsistenteYaRegistrado;
import excepciones.EdicionRepetidaExcepcion;

import logica.datatypes.DataEvento;
import logica.datatypes.DataTRegistro;
import logica.datatypes.DataEdicion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.Evento;
import logica.Registro;
import logica.TipoRegistro;
import logica.Asistente;
import logica.EdicionEvento;
import logica.Categoria;


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
		else if(cat.equals(""))
			throw new EventoSinCategoriaExcepcion("falto ingresar una categoria");
		else {
			e = new Evento(dataEvento);
			Categoria c = me.getCategoria(cat);
			e.agregarCategoria(c);
			me.addEvento(e);
		}
		
	}
	
	public List<String> listarEventos(){
		ManejadorEvento me = ManejadorEvento.getInstance();
		return me.getEventos();
	}
	
	public void nuevaEdicion(DataEdicion dataEdicion, String evento) throws EdicionRepetidaExcepcion {
		ManejadorEvento me = ManejadorEvento.getInstance();
		
		Evento e = me.getEvento(evento);
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
		if(e==null) {
			return new ArrayList<>();
		}
		return e.getEdiciones();			
	}
	
	public List<String> listarTRegistros(String eventoSeleccionado, String edicionSeleccionada) {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(eventoSeleccionado);
		if(e ==null) {
			return new ArrayList<>();
		}
		return e.getTRegistroEdicion(edicionSeleccionada);
	}
	
	public void nuevoRegistro(Asistente asist, String evento, String edicion, String tipoReg, LocalDate fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro {
		boolean c1 = asist.estaRegistrado(edicion);
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(evento);
		boolean c2 = e.cupoEdTRegistro(edicion, tipoReg);
		if (c1) {
			throw new AsistenteYaRegistrado("el asistente ya está registrado a la edicion seleccionada");
		} else if (!c2) {
			throw new NoHayCupoEdicionTRegistro("no hay cupos para el tipo de registro y edicion seleccionados");
		} else { //!c1 && c2
			Registro reg = new Registro(fecha);
			asist.agregarRegistro(reg);
			EdicionEvento ed = e.getEdicion(edicion);
			TipoRegistro tReg = ed.getTRegistro(tipoReg);
			tReg.bajarCupo();
			reg.asociarEdicion(ed);
			reg.asociarTRegistro(tReg);
		}
	}


	
	public DataTRegistro getDataTRegistro(String evento, String edicion, String tipoRegistro) {
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(evento);
		EdicionEvento ed = e.getEdicion(edicion);
		TipoRegistro tr =  ed.getTRegistro(tipoRegistro);
		return new DataTRegistro(tr.getNombre(), tr.getDescripcion(), tr.getCosto(), tr.getCupo());
	}
}

