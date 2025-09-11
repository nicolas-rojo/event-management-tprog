package logica;

import logica.ctrlmanejador.ControladorEventos;
import logica.ctrlmanejador.ControladorUsuario;
import logica.ctrlmanejador.ControladorInstituciones;

import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;
import logica.interfaces.IInstituciones;

/**
 * Fábrica para la construcción de un controlador de usuarios (uno distinto para cada invocación).
 * Se implementa en base al patrón Singleton.
 * @author TProg2017
 *
 */
public class Fabrica {

    private static Fabrica instancia;

    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IUsuario getIControladorUsuario() {
        return new ControladorUsuario();
    }
    
    public IEventos getIControladorEventos() {
    	return new ControladorEventos();
    }
    
    public IInstituciones getIControladorInstituciones() {
    	return new ControladorInstituciones();
    }
}
