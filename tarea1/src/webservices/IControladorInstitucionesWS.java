package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;

import logica.datatypes.*;
import excepciones.*;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public interface IControladorInstitucionesWS {
    
    @WebMethod
    DataInstitucion[] listarInstituciones();
    
    @WebMethod
    void nuevaInstitucion(DataInstitucion dataIns) throws InstitucionRepetidaException;
    
    @WebMethod
    void nuevoPatrocinio(DataPatrocinio dataP, String institucion, String evento, String edicion, String treg) throws PatrocinioRepetidoException;
}