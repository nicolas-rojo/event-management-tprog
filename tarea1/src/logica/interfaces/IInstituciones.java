package logica.interfaces;

import excepciones.InstitucionRepetidaException;
import excepciones.PatrocinioRepetidoException;
import logica.datatypes.DataInstitucion;
import logica.datatypes.DataPatrocinio;

public interface IInstituciones {
	public abstract DataInstitucion[] listarInstituciones();
	
	public abstract void nuevaInstitucion(DataInstitucion dataIns) throws InstitucionRepetidaException;
	
	public abstract void nuevoPatrocinio(DataPatrocinio dataP, String institucion, String evento, String edicion, String treg) throws PatrocinioRepetidoException;
}