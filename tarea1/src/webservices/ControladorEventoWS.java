package webservices;

import jakarta.jws.WebService;
import java.util.List;
import java.util.Arrays;

import logica.Fabrica;
import logica.interfaces.IEventos;
import logica.datatypes.*;
import excepciones.*;

@WebService(endpointInterface = "webservices.IControladorEventoWS")
public class ControladorEventoWS implements IControladorEventoWS {
    private IEventos controlador;
    
    public ControladorEventoWS() {
        Fabrica fabrica = Fabrica.getInstance();
        this.controlador = fabrica.getIControladorEventos();
    }
    
    @Override
	public void nuevoTipoRegistro(DataTRegistro dataTRegistro, String evento, String edicion) throws TipoDeRegistroRepetidoException{
    	controlador.nuevoTipoRegistro(dataTRegistro, evento, edicion);
    }

    
    @Override
    public DataTRegistro getDataTRegistro(String evento, String edicion, String tipoRegistro) {
    	return controlador.getDataTRegistro(evento, edicion, tipoRegistro);
    }
    
    @Override
    public void nuevaEdicion(DataEdicion dataEdicion, String evento, String org) throws EdicionRepetidaExcepcion{
    	try{controlador.nuevaEdicion(dataEdicion, evento, org);}
    	catch (Exception e) {}
    }
    
    @Override
    public DataEdicionWeb[] getEdicionesEventoOrganizadorWeb(String nickname) {
    	return controlador.getEdicionesEventoOrganizadorWeb(nickname);
    }
    
    @Override
    public void nuevoEvento(DataEvento dataEvento, String[] cats) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion {
        controlador.nuevoEvento(dataEvento, Arrays.asList(cats));
    }
    
    @Override
    public String[] listarEventos() {
        return controlador.listarEventos().toArray(new String[0]);
    }
    
    @Override
    public String[] listarCategorias() {
        return controlador.listarCategorias().toArray(new String[0]);
    }
    
    @Override
    public DataEventoCompleto[] getEventosConCategoria(String cat) {
        return controlador.getEventosConCategoria(cat).toArray(new DataEventoCompleto[0]);
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
    public String[] listarEdiciones(String eventoSeleccionado) {
        return controlador.listarEdiciones(eventoSeleccionado).toArray(new String[0]);
    }
    
    @Override
    public DataEdicion obtenerEdicionEvento(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerEdicionEvento(nombreEvento, nombreEdicionEvento);
    }
    
    @Override
    public String[] obtenerTipoRegistrosEdicion(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerTipoRegistrosEdicion(nombreEvento, nombreEdicionEvento).toArray(new String[0]);
    }
    
    @Override
    public DataPatrocinioCompleto[] obtenerPatrociniosEdicion(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerPatrociniosEdicion(nombreEvento, nombreEdicionEvento).toArray(new DataPatrocinioCompleto[0]);
    }
    
    @Override
    public String[] obtenerRegistrosEdicion(String nombreEvento, String nombreEdicionEvento) {
        return controlador.obtenerRegistrosEdicion(nombreEvento, nombreEdicionEvento).toArray(new String[0]);
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
    public String[] listarPatrocinios(String evento, String edicion) {
        return controlador.listarPatrocinios(evento, edicion).toArray(new String[0]);
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