package logica.ctrlmanejador;

import logica.interfaces.IEventos;
import excepciones.TipoDeRegistroRepetidoException;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.FechaRegistroInvalidaException;
import excepciones.LinkInvalidoExcepcion;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.AsistenteYaRegistrado;
import excepciones.CategoriaRepetidaException;
import excepciones.EdicionRepetidaExcepcion;
import excepciones.EventoNoExisteExcepcion;

import logica.datatypes.DataEvento;
import logica.datatypes.DataEventoCompleto;
import logica.datatypes.DataTRegistro;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEdicionWeb;
import logica.datatypes.DataPatrocinioCompleto;
import logica.datatypes.Estado;
import logica.datatypes.EstadoEvento;

import java.util.Set;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.Evento;
import logica.Organizador;
import logica.Registro;
import logica.TipoRegistro;
import logica.Asistente;
import logica.Categoria;
import logica.EdicionEvento;
import logica.Patrocinio;

public class ControladorEventos implements IEventos {
    
    public ControladorEventos() {
    }
    
    public void nuevoTipoRegistro(DataTRegistro dataTRegistro, String evento, String edicion) throws TipoDeRegistroRepetidoException {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento eventoTRegistro = mev.getEvento(evento);
        EdicionEvento edicionTRegistro = eventoTRegistro.getEdicion(edicion);
        if (edicionTRegistro.getTRegistro().contains(dataTRegistro.getNombre())) {
            throw new TipoDeRegistroRepetidoException("El Tipo de Registro ingresado ya existe en el sistema");
        }else {
            edicionTRegistro.agregarTRegistro(new TipoRegistro(dataTRegistro));
        }
    }
    
    
    public void nuevoEvento(DataEvento dataEvento, List<String> cats) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(dataEvento.getNombre());
        if (evt != null) {
            throw new EventoRepetidoExcepcion("nombre de evento en uso");
        } else {
            if (cats == null || cats.isEmpty()) {
                throw new EventoSinCategoriaExcepcion("Faltó ingresar una categoría");
            }
            evt = new Evento(dataEvento);
            for (String c : cats) {
                Categoria categoria = mev.getCategoria(c);
                if (categoria == null) {
                    throw new EventoSinCategoriaExcepcion(
                        "La categoría seleccionada no existe en el sistema: " + c);
                }
                evt.agregarCategoria(categoria);
                categoria.addEvento(evt);
            }
            mev.addEvento(evt);
        }
        
    }
    
    public void darDeBaja(String evento) {
    	ManejadorEvento mev = ManejadorEvento.getInstance();
    	Evento evt = mev.getEvento(evento);
    	evt.setEstado(EstadoEvento.finalizado);
    	
    }
    public List<DataEventoCompleto> getEventosConCategoria(String cat){
    	 List<DataEventoCompleto> res = new ArrayList<>();
    	 ManejadorEvento mev = ManejadorEvento.getInstance();
         Set<Evento> evt =  mev.getEventosConCategoria(cat);
         for (Evento ev : evt) {
        	 DataEventoCompleto dtc = ev.getDTOEvento();
        	 res.add(dtc);
         }
         return res;
    }
    
    public boolean eventoFinalizado(String evento) {
    	ManejadorEvento mev = ManejadorEvento.getInstance();
    	Evento evt = mev.getEvento(evento);
    	if (evt.getEstado() == EstadoEvento.finalizado) {
    		return true;}
    	else {
    		return false;
    		}
    }
    
    public List<String> listarEventos(){
        ManejadorEvento mev = ManejadorEvento.getInstance();
        return mev.getEventos();
    }
    
    public List<String> listarCategorias(){
        ManejadorEvento mev = ManejadorEvento.getInstance();
        return mev.getCategorias();
    }
    
    public void nuevaEdicion(DataEdicion dataEdicion, String evento, String org) throws EdicionRepetidaExcepcion, LinkInvalidoExcepcion {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        ManejadorUsuario musr = ManejadorUsuario.getInstance();
        
        //Me fijo que no existe una edicion con ese nombre en TODOS los eventos
        
        List<String> eventos = mev.getEventos();
        for (String e : eventos) {
        	Evento evt = mev.getEvento(e);
        	List<String> ediciones = evt.getEdiciones();
        	for (String ed : ediciones) {
        		if (ed.equals(dataEdicion.getNombre())) {
        			throw new EdicionRepetidaExcepcion("Nombre de edicion en uso");
        		}
        	}
        }
//        EdicionEvento edev = evt.getEdicion(dataEdicion.getNombre());
        // Si tiene url, la convierto a url embebida.
        
        String url = dataEdicion.getUrl();
        if (url != null && !url.isEmpty() && !url.equals("")) {
        	if (url.contains("youtube.com/watch?v=") || url.contains("youtu.be/")) {
        		String videoId = "";
        		if (url.contains("youtube.com/watch?v=")) {
        			videoId = url.split("v=")[1].split("&")[0];
        		} else if (url.contains("youtu.be/")) {
        			videoId = url.split("youtu.be/")[1].split("\\?")[0];
        		}
        		String embedUrl = "https://www.youtube.com/embed/" + videoId;
        		dataEdicion.setUrl(embedUrl);
        	} else {
    			throw new LinkInvalidoExcepcion("El link ingresado no es un link de YouTube válido");
        	}
        }
        
        Organizador orga = (Organizador) musr.getUsuarioNickname(org);
        Evento evt = mev.getEvento(evento);
        EdicionEvento edev = new EdicionEvento(dataEdicion);
        evt.agregarEdicion(edev);
        orga.agregarEdicion(edev);
        edev.agregarOrganizador(orga);
    }
    
    public DataEventoCompleto[] listarInfoEvento() throws EventoNoExisteExcepcion {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento[] eventos = mev.getEventosTipoEvento();

        if (eventos != null && eventos.length > 0) {
            DataEventoCompleto[] dtoEventos = new DataEventoCompleto[eventos.length];
            for (int i = 0; i < eventos.length; i++) {
                dtoEventos[i] = eventos[i].getDTOEvento();
            }
            return dtoEventos; // devolvemos el arreglo
        } else {
            throw new EventoNoExisteExcepcion("No existen eventos registrados");
        }
    }
    public List<String> listarEdiciones(String eventoSeleccionado) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(eventoSeleccionado);
        if (evt==null) {
            return new ArrayList<>();
        }
        return evt.getEdiciones();            
    }
    
    public List<String> listarTRegistros(String eventoSeleccionado, String edicionSeleccionada) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(eventoSeleccionado);
        if (evt ==null) {
            return new ArrayList<>();
        }
        return evt.getTRegistroEdicion(edicionSeleccionada);
    }
    
    public void nuevoRegistro(Asistente asist, String evento, String edicion, String tipoReg, LocalDate fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro, FechaRegistroInvalidaException {
    	// VALIDACIÓN 1: Fecha no puede ser nula
        if (fecha == null) {
            throw new FechaRegistroInvalidaException("Debe seleccionar una fecha");
        }
        // VALIDACIÓN 2: Verificar si ya está registrado
        if (asist.estaRegistrado(edicion)) {
            throw new AsistenteYaRegistrado("El asistente ya está registrado a la edición seleccionada");
        }
        // Obtener evento y edición
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(evento);
        EdicionEvento edi = evt.getEdicion(edicion);
        // VALIDACIÓN 3: Fecha no puede ser posterior a la fecha fin del evento
        if (fecha.isAfter(edi.getFechaFin())) {
            throw new FechaRegistroInvalidaException("La fecha de registro no puede ser posterior a la fecha de fin del evento");
        }
        // VALIDACIÓN 4: Verificar cupo disponible
        if (!evt.cupoEdTRegistro(edicion, tipoReg)) {
            throw new NoHayCupoEdicionTRegistro("No hay cupos disponibles para el tipo de registro seleccionado");
        }
        // Crear y asociar el registro
        Registro reg = new Registro(fecha);
        TipoRegistro tReg = edi.getTRegistro(tipoReg);
        
        reg.asociarEdicion(edi);
        reg.asociarTRegistro(tReg);
        
        asist.agregarRegistro(reg);
        edi.agregarRegistro(reg);
        
        // Decrementar el cupo
        tReg.bajarCupo();
    }
    
    public DataTRegistro getDataTRegistro(String evento, String edicion, String tipoRegistro) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(evento);
        EdicionEvento edi = evt.getEdicion(edicion);
        TipoRegistro treg =  edi.getTRegistro(tipoRegistro);
        return new DataTRegistro(treg.getNombre(), treg.getDescripcion(), treg.getCosto(), treg.getCupo());
    }
    
    public DataEdicion obtenerEdicionEvento(String nombreEvento, String nombreEdicionEvento) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(nombreEvento);
        if (evt == null) return null;
        EdicionEvento edi = evt.getEdicion(nombreEdicionEvento);
        if (edi == null) return null;
        return new DataEdicion(edi.getNombre(), edi.getSigla(), edi.getFechaIni(), edi.getFechaFin(), edi.getFechaAlta(), edi.getCuidad(), edi.getPais(), edi.getUrl());
    }
    
    public List<String> obtenerTipoRegistrosEdicion(String nombreEvento, String nombreEdicionEvento) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(nombreEvento);
        if (evt == null) return new ArrayList<>();
        EdicionEvento edev = evt.getEdicion(nombreEdicionEvento);
        if (edev == null) return new ArrayList<>();
        return edev.getTRegistro();
    }
    
    public List<DataPatrocinioCompleto> obtenerPatrociniosEdicion(String nombreEvento, String nombreEdicionEvento) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(nombreEvento);
        if (evt == null) return new ArrayList<>();
        
        EdicionEvento edev = evt.getEdicion(nombreEdicionEvento);
        if (edev == null) return new ArrayList<>();
        
        List<Patrocinio> patrocinios = edev.getPatrociniosLista();
        List<DataPatrocinioCompleto> result = new ArrayList<>();
        
        for (Patrocinio p : patrocinios) {
            String institucionNombre = p.getInstitucion() != null ? p.getInstitucion().getNombre() : "Sin institución";
            String tipoRegistroNombre = p.getTipoRegistro() != null ? p.getTipoRegistro().getNombre() : "Sin tipo de registro";
            
            result.add(new DataPatrocinioCompleto(
                p.getFecha(),
                p.getMonto(),
                p.getNivel(),
                p.getCod(),
                p.getCtdCupo(),
                institucionNombre,
                tipoRegistroNombre
            ));
        }
        
        return result;
    }
    
    public List<String> obtenerRegistrosEdicion(String nombreEvento, String nombreEdicionEvento) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(nombreEvento);
        if (evt == null) return new ArrayList<>();
        
        EdicionEvento edi = evt.getEdicion(nombreEdicionEvento);
        if (edi == null) return new ArrayList<>();
        
        return edi.getRegistrosInfo();
    }
    
    public String obtenerOrganizadorEdicion(String nombreEvento, String nombreEdicionEvento) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(nombreEvento);
        if (evt == null) return "";
        
        EdicionEvento edi = evt.getEdicion(nombreEdicionEvento);
        if (edi == null) return "";
        
        Organizador org = edi.getOrganizador();
        return org != null ? org.getNickname() : "";
    }
    
    public DataEdicion[] getEdicionesEventoOrganizador(String nickname) {
        ManejadorUsuario musr = ManejadorUsuario.getInstance();
        Organizador org = (Organizador) musr.getUsuarioNickname(nickname);
        EdicionEvento[] edev = org.getEdiciones();
        DataEdicion[] res = new DataEdicion[edev.length];
        for (int i = 0; i < edev.length; i++) {
            res[i] = new DataEdicion(edev[i].getNombre(), edev[i].getSigla(), edev[i].getFechaIni(), edev[i].getFechaFin(), edev[i].getFechaAlta(), edev[i].getCuidad(), edev[i].getPais(), edev[i].getUrl());
        }
        return res;
    }
    
    public DataEdicionWeb[] getEdicionesEventoOrganizadorWeb(String nickname) {
        ManejadorUsuario musr = ManejadorUsuario.getInstance();
        Organizador org = (Organizador) musr.getUsuarioNickname(nickname);
        EdicionEvento[] edev = org.getEdiciones();
        DataEdicionWeb[] res = new DataEdicionWeb[edev.length];
        for (int i = 0; i < edev.length; i++) {
            res[i] = new DataEdicionWeb(edev[i].getNombre(), edev[i].getSigla(), edev[i].getFechaIni(), edev[i].getFechaFin(), 
                    edev[i].getFechaAlta(), edev[i].getCuidad(), edev[i].getPais(), edev[i].getEstado());
        }
        return res;
    }

    public void nuevaCategoria(String cat) throws CategoriaRepetidaException {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Categoria cate = mev.getCategoria(cat);
        if (cate != null) {
            throw new CategoriaRepetidaException("Ya existe esta categoria");
        }else {
            Categoria categoria = new Categoria(cat);
            mev.agregarCategoria(cat, categoria);
        }        
    }
    
    public boolean existeEvento(String eventoSeleccionado) {
    	ManejadorEvento mev = ManejadorEvento.getInstance();
    	Evento evt = mev.getEvento(eventoSeleccionado);
    	return evt.getNombreEvento().equals(eventoSeleccionado);
    }
    
    public boolean existeEdicion(String evento, String edicion) {
    	ManejadorEvento mev = ManejadorEvento.getInstance();
    	Evento evt = mev.getEvento(evento);
    	if (evt == null) {
    		return false;
    	}
    	EdicionEvento edi = evt.getEdicion(edicion);
    	if (edi == null) {
    		return false;
    	}
    	return true;
    }
    
    public boolean existeTR(String evento, String edicion, String treg) {
    	ManejadorEvento mev = ManejadorEvento.getInstance();
    	Evento evt = mev.getEvento(evento);
    	if (evt == null) {
    		return false;
    	}
    	EdicionEvento edi = evt.getEdicion(edicion);
    	if (edi == null) {
    		return false;
    	}
    	TipoRegistro tipo = edi.getTRegistro(treg);
    	if (tipo == null) {
    		return false;
    	}
    	return true;
    }
    
    public String eventoTieneEdicion(String edicion) {
    	ManejadorEvento mev = ManejadorEvento.getInstance();
    	return mev.eventoTieneEdicion(edicion);
    }
    
    
    //NUEVOS PARA CONSULTA PATROCINIO
    @Override
    public List<String> listarPatrocinios(String evento, String edicion) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(evento);
        if (evt == null) return new ArrayList<>();
        EdicionEvento edev = evt.getEdicion(edicion);
        if (edev == null) return new ArrayList<>();
        List<Patrocinio> patrocinios = edev.getPatrociniosLista();
        List<String> res = new ArrayList<>();
        for (Patrocinio p : patrocinios) {
            // Formatear como "código - nivel"
            res.add(p.getCod() + " - " + p.getNivel());
        }
        return res;
    }

    @Override
    public DataPatrocinioCompleto obtenerDTOPatrocinioCompleto(String evento, String edicion, String codigo) {
        ManejadorEvento mev = ManejadorEvento.getInstance();
        Evento evt = mev.getEvento(evento);
        if (evt == null) return null;
        EdicionEvento edev = evt.getEdicion(edicion);
        if (edev == null) return null;
        List<Patrocinio> patrocinios = edev.getPatrociniosLista();
        for (Patrocinio p : patrocinios) {
            if (p.getCod().equals(codigo)) {
                String nombreInstitucion = p.getInstitucion().getNombre();
                String nombreTipoRegistro = p.getTipoRegistro().getNombre();
                return new DataPatrocinioCompleto(p.getFecha(), p.getMonto(), p.getNivel(), 
                                                p.getCod(), p.getCtdCupo(), nombreInstitucion, nombreTipoRegistro);
            }
        }
        return null;
    }
    
    public void procesarEdicion(String evento, String edicion, Estado estado){
    	ManejadorEvento manejadorE = ManejadorEvento.getInstance();
    	Evento eventoSeleccionado = manejadorE.getEvento(evento);
    	EdicionEvento edicionE = eventoSeleccionado.getEdicion(edicion);
    	edicionE.setEstado(estado);
    }
    
    public Estado getEstado(String edicion, String evento) {
    	ManejadorEvento manejadorE = ManejadorEvento.getInstance();
    	Evento eventoSeleccionado = manejadorE.getEvento(evento);
    	EdicionEvento edicionE = eventoSeleccionado.getEdicion(edicion);
    	return edicionE.getEstado();
    }
}