package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;

import java.io.IOException;
import java.util.List;

import logica.datatypes.*;
import excepciones.*;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public interface IControladorEventoWS {

	@WebMethod
	void nuevoTipoRegistro(DataTRegistro dataTRegistro, String evento, String edicion) throws TipoDeRegistroRepetidoException;

	
	@WebMethod
	DataTRegistro getDataTRegistro(String evento, String edicion, String tipoRegistro);
	
	@WebMethod
	void nuevaEdicion(DataEdicion dataEdicion, String evento, String org) throws EdicionRepetidaExcepcion, LinkInvalidoExcepcion;
	
	@WebMethod
	DataEdicionWeb[] getEdicionesEventoOrganizadorWeb(String nickname);
	
    @WebMethod
    void nuevoEvento(DataEvento dataEvento, String[] cats) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion;
    
    @WebMethod
    String[] listarEventos();
    
    @WebMethod
    String[] listarCategorias();
    
    @WebMethod
    DataEventoCompleto[] getEventosConCategoria(String cat);
    
    @WebMethod
    DataEventoCompleto[] listarInfoEvento() throws EventoNoExisteExcepcion;
    
    @WebMethod
    boolean eventoFinalizado(String evento);
    
    @WebMethod
    void darDeBaja(String evento);
    
    @WebMethod
    String[] listarEdiciones(String eventoSeleccionado);
    
    @WebMethod
    DataEdicion obtenerEdicionEvento(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    String[] obtenerTipoRegistrosEdicion(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    DataPatrocinioCompleto[] obtenerPatrociniosEdicion(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    String[] obtenerRegistrosEdicion(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    String obtenerOrganizadorEdicion(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    Estado getEstado(String edicion, String evento);
    
    @WebMethod
    String[] listarPatrocinios(String evento, String edicion);
    
    @WebMethod
    DataPatrocinioCompleto obtenerDTOPatrocinioCompleto(String evento, String edicion, String codigo);
    
    @WebMethod
    String eventoTieneEdicion(String edicion);
    
    @WebMethod
    byte[] getFile(String nombre) throws IOException;
    
    @WebMethod
    void uploadFile(String nombre, byte[] imagen) throws IOException;
}