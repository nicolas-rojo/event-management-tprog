package logica.ctrlmanejador;

import logica.Evento;
import logica.Institucion;

import java.util.Set;

import excepciones.InstitucionRepetidaException;
import excepciones.PatrocinioRepetidoException;
import logica.EdicionEvento;
import logica.Patrocinio;
import logica.datatypes.DataInstitucion;
import logica.datatypes.DataPatrocinio;
import logica.interfaces.IInstituciones;
import logica.TipoRegistro;

public class ControladorInstituciones implements IInstituciones{
	
	public ControladorInstituciones() {}
	
	public DataInstitucion[] listarInstituciones() {
		ManejadorInstituciones mi = ManejadorInstituciones.getInstance();
		Institucion[] ins = mi.getInstituciones();
		if (ins == null)
			return null;
		else {
			DataInstitucion[] di = new DataInstitucion[ins.length];
			for (int i = 0; i<ins.length; i++) {
				di[i] = new DataInstitucion(ins[i].getNombre(), ins[i].getDescripcion(), ins[i].getUrl());
			}
		return di;
		}

	}
	
	public void nuevaInstitucion(DataInstitucion di) throws InstitucionRepetidaException {
		ManejadorInstituciones mi = ManejadorInstituciones.getInstance();
		Institucion ins = mi.getInstitucion(di.getNombre());
		if(ins != null) {
			throw new InstitucionRepetidaException("Ya existe esta institucion");
		}else {
			Institucion i = new Institucion(di);
			mi.addInstitucion(i);
		}
	}
	
	public void nuevoPatrocinio(DataPatrocinio dp, String institucion, String evento, String edicion, String t) throws PatrocinioRepetidoException {
		ManejadorInstituciones mi = ManejadorInstituciones.getInstance();
		Patrocinio p = new Patrocinio(dp);
		
		Institucion i = mi.getInstitucion(institucion);
		p.setInstitucion(i);
		
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(evento);
		EdicionEvento ee = e.getEdicion(edicion);
		p.setEdicionEvento(ee);
		Set<Patrocinio> patrocinios = ee.getPatrocinios();
		for(Patrocinio pat : patrocinios)
			if(pat.getInstitucion().getNombre().equals(institucion)) {
				throw new PatrocinioRepetidoException("Ya existe este patrocinio en esta edicion");
			}
		
		TipoRegistro tr = ee.getTRegistro(t);
		
		p.setTipoRegistro(tr);
		i.añadirPatrocinio(p);
		ee.agregarPatrocinio(p);
	}

}
