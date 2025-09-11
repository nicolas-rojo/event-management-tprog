package logica.interfaces;

import excepciones.InstitucionRepetidaException;
import excepciones.PatrocinioRepetidoException;
import logica.datatypes.*;

public interface IInstituciones {
	public abstract DataInstitucion[] listarInstituciones();
	
	public abstract void nuevaInstitucion(DataInstitucion di) throws InstitucionRepetidaException;
	
	public abstract void nuevoPatrocinio(DataPatrocinio dp, String institucion, String evento, String edicion, String t) throws PatrocinioRepetidoException;
}