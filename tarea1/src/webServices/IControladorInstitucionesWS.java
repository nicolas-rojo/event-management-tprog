package com.miseventos.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import logica.datatypes.*;
import excepciones.*;

@WebService
public interface IControladorInstitucionWS {
    
    @WebMethod
    DataInstitucion[] listarInstituciones();
    
    @WebMethod
    void nuevaInstitucion(DataInstitucion dataIns) throws InstitucionRepetidaException;
    
    @WebMethod
    void nuevoPatrocinio(DataPatrocinio dataP, String institucion, String evento, String edicion, String treg) throws PatrocinioRepetidoException;
}