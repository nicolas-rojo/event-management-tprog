package com.miseventos.utils;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL; 
import java.net.MalformedURLException;
import java.util.Properties;

import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.instituciones.ControladorInstitucionesWSService;
import cliente.ws.instituciones.IControladorInstitucionesWS;

public class fabricaWS {

    private static final String CONFIG_FILE = "movil.properties";
    private static Properties properties;
    private static IControladorUsuarioWS controladorUsuarioWS;
    private static IControladorEventoWS controladorEventoWS;
    private static IControladorInstitucionesWS controladorInstitucionesWS;

    private fabricaWS() { }

    /**
     * Carga el archivo de propiedades solo una vez usando el classpath.
     */
    private static synchronized void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            // Busca el archivo en el classpath (es decir, en src/main/resources)
            try (InputStream input = fabricaWS.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
                if (input == null) {
                    System.err.println("ERROR CRÍTICO: No se pudo encontrar el archivo " + CONFIG_FILE + " en el classpath. Revise que esté en src/main/resources.");
                    // Podría lanzar una RuntimeException si es crítico para detener la aplicación
                } else {
                    properties.load(input);
                    System.out.println("INFO: Propiedades de WebServices cargadas correctamente.");
                }
            } catch (IOException ex) {
                System.err.println("ERROR al leer el archivo de propiedades: " + CONFIG_FILE);
                ex.printStackTrace();
            }
        }
    }

    public static IControladorUsuarioWS getControladorUsuarioWS() {
        if (controladorUsuarioWS == null) {
            loadProperties();
            
            String urlWsdl = properties != null ? properties.getProperty("URLControladorUsuario") : null;
            if (urlWsdl == null) { 
                System.err.println("ERROR: Propiedad 'URLControladorUsuario' no encontrada o propiedades no cargadas.");
                return null;
            }
            
            try {
                URL url = new URL(urlWsdl);
                ControladorUsuarioWSService service = new ControladorUsuarioWSService(url);
                
                controladorUsuarioWS = service.getControladorUsuarioWSPort();
                System.out.println("INFO: Proxy de ControladorUsuarioWS creado con URL: " + urlWsdl);

            } catch (MalformedURLException e) {
                System.err.println("ERROR: La URL leída ('" + urlWsdl + "') es inválida.");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("ERROR: Fallo al inicializar/conectar el servicio de Usuarios.");
                e.printStackTrace();
            }
        }
        return controladorUsuarioWS;
    }

    public static IControladorEventoWS getControladorEventoWS() {
        if (controladorEventoWS == null) {
            loadProperties();
            
            String urlWsdl = properties != null ? properties.getProperty("URLControladorEvento") : null;
            if (urlWsdl == null) { 
                System.err.println("ERROR: Propiedad 'URLControladorEvento' no encontrada o propiedades no cargadas.");
                return null;
            }
            
            try {
                URL url = new URL(urlWsdl);
                ControladorEventoWSService service = new ControladorEventoWSService(url);
                
                controladorEventoWS = service.getControladorEventoWSPort();
                System.out.println("INFO: Proxy de ControladorEventoWS creado con URL: " + urlWsdl);
                
            } catch (MalformedURLException e) {
                System.err.println("ERROR: La URL leída ('" + urlWsdl + "') es inválida.");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("ERROR: Fallo al inicializar/conectar el servicio de Eventos.");
                e.printStackTrace();
            }
        }
        return controladorEventoWS;
    }

    public static IControladorInstitucionesWS getControladorInstitucionesWS() {
        if (controladorInstitucionesWS == null) {
            loadProperties();
            
            String urlWsdl = properties != null ? properties.getProperty("URLControladorInstituciones") : null;
            if (urlWsdl == null) { 
                System.err.println("ERROR: Propiedad 'URLControladorInstituciones' no encontrada o propiedades no cargadas.");
                return null;
            }
            
            try {
                URL url = new URL(urlWsdl);
                ControladorInstitucionesWSService service = new ControladorInstitucionesWSService(url);
                
                controladorInstitucionesWS = service.getControladorInstitucionesWSPort();
                System.out.println("INFO: Proxy de ControladorInstitucionesWS creado con URL: " + urlWsdl);
                
            } catch (MalformedURLException e) {
                System.err.println("ERROR: La URL leída ('" + urlWsdl + "') es inválida.");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("ERROR: Fallo al inicializar/conectar el servicio de Instituciones.");
                e.printStackTrace();
            }
        }
        return controladorInstitucionesWS;
    }
}