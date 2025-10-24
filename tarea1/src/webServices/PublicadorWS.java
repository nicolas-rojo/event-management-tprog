package webServices;

import jakarta.xml.ws.Endpoint;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PublicadorWS {
    
    public static void main(String[] args) {
        Properties config = cargarConfiguracion();
        String ip = config.getProperty("webservices.ip", "localhost");
        int puerto = Integer.parseInt(config.getProperty("webservices.puerto", "8081"));
        String urlBase = "http://" + ip + ":" + puerto + "/";       
        Endpoint.publish(urlBase + "eventos", new ControladorEventoWS());
        System.out.println("Debuggin: Servicio 'eventos' publicado");
        
        Endpoint.publish(urlBase + "usuarios", new ControladorUsuarioWS());
        System.out.println("Debuggin:Servicio 'usuarios' publicado");
        
        Endpoint.publish(urlBase + "instituciones", new ControladorInstitucionesWS());
        System.out.println("Debuggin:Servicio 'instituciones' publicado");
        System.out.println("\n");
        System.out.println("Debuggin:Eventos:       " + urlBase + "eventos?wsdl");
        System.out.println("Debuggin:Usuarios:      " + urlBase + "usuarios?wsdl");
        System.out.println("Debuggin: Instituciones: " + urlBase + "instituciones?wsdl");
    }
    
    private static Properties cargarConfiguracion() {
        Properties props = new Properties();
        String userHome = System.getProperty("user.home");
        String configPath = userHome + "/.eventosUy/servidor.properties";
        
        try {
            FileInputStream fis = new FileInputStream(configPath);
            props.load(fis);
            fis.close();
            System.out.println("Debuggin: Configuración cargada: " + configPath + "\n");
        } catch (IOException e) {
            System.out.println("Debuggin: Error");

        }
        
        return props;
    }
}