package logica.ctrlmanejador;

import java.util.*;
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
	
	public void addInstitucion(Institucion i) {
		this.instituciones.put(i.getNombre(), i);
	}
	
	public Institucion getInstitucion(String nombre) {
		return instituciones.get(nombre);
	}
	
	public Institucion[] getInstituciones() {
        if (instituciones.isEmpty())
            return null;
        else {
            Collection<Institucion> ins = instituciones.values();
            Object[] o = ins.toArray();
            Institucion[] res = new Institucion[o.length];
            for (int i = 0; i < o.length; i++) {
                res[i] = (Institucion) o[i];
            }
            return res;
        }
    }
}
