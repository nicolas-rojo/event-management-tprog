package logica.interfaces;

import logica.TipoRegistro;
import logica.datatypes.*;

public interface IInstituciones {
	public abstract DataInstitucion[] listarInstituciones();
	
	public abstract void nuevaInstitucion(DataInstitucion di);
	
	public abstract void nuevoPatrocinio(DataPatrocinio dp, String institucion, String evento, String edicion, TipoRegistro t);
}