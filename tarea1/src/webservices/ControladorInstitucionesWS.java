package webservices;

import jakarta.jws.WebService;

import logica.Fabrica;
import logica.interfaces.IInstituciones;
import logica.datatypes.*;
import excepciones.*;

@WebService(endpointInterface = "webservices.IControladorInstitucionesWS")
public class ControladorInstitucionesWS implements IControladorInstitucionesWS {
    
    private IInstituciones controlador;
    
    public void ControladorInstitucionWS() {
        Fabrica fabrica = Fabrica.getInstance();
        this.controlador = fabrica.getIControladorInstituciones();
    }
    
    @Override
    public DataInstitucion[] listarInstituciones() {
        return controlador.listarInstituciones();
    }
    
    @Override
    public void nuevaInstitucion(DataInstitucion dataIns) throws InstitucionRepetidaException {
        controlador.nuevaInstitucion(dataIns);
    }
    
    @Override
    public void nuevoPatrocinio(DataPatrocinio dataP, String institucion, String evento, String edicion, String treg) throws PatrocinioRepetidoException {
        controlador.nuevoPatrocinio(dataP, institucion, evento, edicion, treg);
    }
}