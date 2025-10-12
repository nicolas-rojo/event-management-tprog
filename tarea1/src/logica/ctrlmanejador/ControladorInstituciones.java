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
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion[] ins = mins.getInstituciones();
		if (ins == null)
			return null;
		else {
			DataInstitucion[] dataIns = new DataInstitucion[ins.length];
			for (int i = 0; i<ins.length; i++) {
				dataIns[i] = new DataInstitucion(ins[i].getNombre(), ins[i].getDescripcion(), ins[i].getUrl());
			}
		return dataIns;
		}

	}
	
	public void nuevaInstitucion(DataInstitucion dataIns) throws InstitucionRepetidaException {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion ins = mins.getInstitucion(dataIns.getNombre());
		if (ins != null) {
			throw new InstitucionRepetidaException("Ya existe esta institucion");
		}else {
			Institucion inst = new Institucion(dataIns);
			mins.addInstitucion(inst);
		}
	}
	
	public void nuevoPatrocinio(DataPatrocinio dataP, String institucion, String evento, String edicion, String nomTReg) throws PatrocinioRepetidoException {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Patrocinio patro = new Patrocinio(dataP);
		
		Institucion ins = mins.getInstitucion(institucion);
		patro.setInstitucion(ins);
		
		ManejadorEvento mev = ManejadorEvento.getInstance();
		Evento evt = mev.getEvento(evento);
		EdicionEvento edev = evt.getEdicion(edicion);
		patro.setEdicionEvento(edev);
		Set<Patrocinio> patrocinios = edev.getPatrocinios();
		for (Patrocinio pat : patrocinios) {
			if (pat.getInstitucion().getNombre().equals(institucion)) {
				throw new PatrocinioRepetidoException("Ya existe este patrocinio en esta edicion");
			}
		}
		TipoRegistro treg = edev.getTRegistro(nomTReg);
		patro.setTipoRegistro(treg);
		ins.agregarPatrocinio(patro);
		edev.agregarPatrocinio(patro);
	}

}
