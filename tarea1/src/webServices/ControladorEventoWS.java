package webServices;

import javax.jws.WebService;
import java.util.List;

import logica.Fabrica;
import logica.interfaces.IEventos;
import logica.datatypes.*;
import excepciones.*;

@WebService(endpointInterface = "com.miseventos.webservices.IControladorEventoWS")
public class ControladorEventoWS implements IControladorEventoWS {
    
    private IEventos controlador;
    
    public ControladorEventoWS() {
        Fabrica fabrica = Fabrica.getInstance();
        this.controlador = fabrica.getIControladorEventos();
    }
    
    @Override
    public void nuevoEvento(DataEvento dataEvento, List<String> cats) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion {
        controlador.nuevoEvento(dataEvento, cats);
    }
    
    @Override
    public List<String> listarEventos() {
        return controlador.listarEventos();
    }
    
    @Override
    public List<String> listarCategorias() {
        return controlador.listarCategorias();
    }
    
    @Override
    public List<DataEventoCompleto> getEventosConCategoria(String cat) {
        return controlador.getEventosConCategoria(cat);
    }
    
    @Override
    public DataEventoCompleto[] listarInfoEvento() throws EventoNoExisteExcepcion {
        return controlador.listarInfoEvento();
    }
    
    @Override
    public boolean eventoFinalizado(String evento) {
        return controlador.eventoFinalizado(evento);
    }
    
    @Override
    public void darDeBaja(String evento) {
        controlador.darDeBaja(evento);
    }
    
    @Override
    public List<String> listarEdiciones(String eventoSeleccionado) {
        return controlador.listarEdiciones(eventoSeleccionado);
    }
    
    @Override
    public DataEdicion obtenerEdicionEvento(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerEdicionEvento(nombreEvento, nombreEdicionEvento);
    }
    
    @Override
    public List<String> obtenerTipoRegistrosEdicion(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerTipoRegistrosEdicion(nombreEvento, nombreEdicionEvento);
    }
    
    @Override
    public List<DataPatrocinioCompleto> obtenerPatrociniosEdicion(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerPatrociniosEdicion(nombreEvento, nombreEdicionEvento);
    }
    
    @Override
    public List<String> obtenerRegistrosEdicion(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerRegistrosEdicion(nombreEvento, nombreEdicionEvento);
    }
    
    @Override
    public String obtenerOrganizadorEdicion(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerOrganizadorEdicion(nombreEvento, nombreEdicionEvento);
    }
    
    @Override
    public Estado getEstado(String edicion, String evento) {
        return controlador.getEstado(edicion, evento);
    }
    
    @Override
    public List<String> listarPatrocinios(String evento, String edicion) {
        return controlador.listarPatrocinios(evento, edicion);
    }
    
    @Override
    public DataPatrocinioCompleto obtenerDTOPatrocinioCompleto(String evento, String edicion, String codigo) {
        return controlador.obtenerDTOPatrocinioCompleto(evento, edicion, codigo);
    }
    
    @Override
    public String eventoTieneEdicion(String edicion) {
        return controlador.eventoTieneEdicion(edicion);
    }
}