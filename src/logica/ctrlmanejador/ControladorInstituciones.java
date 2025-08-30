package logica.ctrlmanejador;

import logica.Evento;
import logica.Institucion;
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
	
	public void nuevaInstitucion(DataInstitucion di) {
		ManejadorInstituciones mi = ManejadorInstituciones.getInstance();
		Institucion i = new Institucion(di);
		mi.addInstitucion(i);
	}
	
	public void nuevoPatrocinio(DataPatrocinio dp, String institucion, String evento, String edicion, TipoRegistro t) {
		ManejadorInstituciones mi = ManejadorInstituciones.getInstance();
		Patrocinio p = new Patrocinio(dp);
		
		Institucion i = mi.getInstitucion(institucion);
		p.setInstitucion(i);
		
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento e = me.getEvento(evento);
		EdicionEvento ee = e.getEdicion(edicion);
		p.setEdicionEvento(ee);
		
		p.setTipoRegistro(t);
		
		i.añadirPatrocinio(p);
		ee.agregarPatrocinio(p);
	}

}
