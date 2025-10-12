package logica.ctrlmanejador;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logica.Institucion;

public class ManejadorInstituciones {
	private static ManejadorInstituciones instancia = null;
	private Map<String, Institucion> instituciones;
	
	private ManejadorInstituciones() {
		this.instituciones = new HashMap<String, Institucion>();
    }
	
	public static ManejadorInstituciones getInstance() {
        if (instancia == null)
            instancia = new ManejadorInstituciones();
        return instancia;
    }
	
	public static void resetInstance() {
    	instancia = null;
    }
	
	public void addInstitucion(Institucion ins) {
		this.instituciones.put(ins.getNombre(), ins);
	}
	
	public Institucion getInstitucion(String nombre) {
		return instituciones.get(nombre);
	}
	
	public Institucion[] getInstituciones() {
        if (instituciones.isEmpty())
            return null;
        else {
            Collection<Institucion> ins = instituciones.values();
            Object[] obj = ins.toArray();
            Institucion[] res = new Institucion[obj.length];
            for (int i = 0; i < obj.length; i++) {
                res[i] = (Institucion) obj[i];
            }
            return res;
        }
    }
}
