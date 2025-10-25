package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import java.util.List;

import logica.datatypes.*;
import excepciones.*;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public interface IControladorEventoWS {
    
    @WebMethod
    void nuevoEvento(DataEvento dataEvento, List<String> cats) throws EventoRepetidoExcepcion, EventoSinCategoriaExcepcion;
    
    @WebMethod
    List<String> listarEventos();
    
    @WebMethod
    List<String> listarCategorias();
    
    @WebMethod
    List<DataEventoCompleto> getEventosConCategoria(String cat);
    
    @WebMethod
    DataEventoCompleto[] listarInfoEvento() throws EventoNoExisteExcepcion;
    
    @WebMethod
    boolean eventoFinalizado(String evento);
    
    @WebMethod
    void darDeBaja(String evento);
    
    @WebMethod
    List<String> listarEdiciones(String eventoSeleccionado);
    
    @WebMethod
    DataEdicion obtenerEdicionEvento(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    List<String> obtenerTipoRegistrosEdicion(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    List<DataPatrocinioCompleto> obtenerPatrociniosEdicion(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    List<String> obtenerRegistrosEdicion(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    String obtenerOrganizadorEdicion(String nombreEvento, String nombreEdicionEvento);
    
    @WebMethod
    Estado getEstado(String edicion, String evento);
    
    @WebMethod
    List<String> listarPatrocinios(String evento, String edicion);
    
    @WebMethod
    DataPatrocinioCompleto obtenerDTOPatrocinioCompleto(String evento, String edicion, String codigo);
    
    @WebMethod
    String eventoTieneEdicion(String edicion);
}