package webservices;

import jakarta.xml.ws.Endpoint;
import java.io.File;             // Necesario para usar File
import java.io.FileInputStream;  // Necesario para leer el archivo
import java.io.IOException; 
import java.util.Properties;

public class PublicadorWS {
    
    private Endpoint endpoint = null;
    private static String baseURI = null;

    public PublicadorWS() {}
    
    /**
     * Carga la base URI buscando el .properties en el directorio actual de ejecución (user.dir).
     */
    private static String getBaseURI() {
        if (baseURI != null) {
            return baseURI;
        }
        
        String defaultURI = "http://localhost:8081";
        Properties properties = new Properties();
        
        try {
            // --- LÓGICA DE CARGA POR DIRECTORIO ACTUAL (user.dir) ---
            
            // Construye la ruta asumiendo que el archivo está en el directorio donde se ejecuta el proceso
            String configPath = System.getProperty("user.dir") 
                              + File.separator 
                              + "servidor_central.properties";
            
            // Usamos FileInputStream para leer el archivo desde el sistema de archivos
            properties.load(new FileInputStream(new File(configPath)));
            
            // Si la carga fue exitosa:
            String protocol = properties.getProperty("Protocol", "http");
            String host = properties.getProperty("Host", "localhost");
            String port = properties.getProperty("Port", "8081");
                
            baseURI = protocol + "://" + host + ":" + port;
            return baseURI; // Retorna la URI cargada
            
        } catch (IOException e) {
            // El error persiste si el archivo no está en el root de ejecución
            String currentDir = System.getProperty("user.dir");
            System.err.println("Advertencia: Error al cargar servidor_central.properties. ");
            System.err.println("Asegúrese de que el archivo está en la raíz del proyecto, en: " + currentDir);
            System.err.println("Usando la URL por defecto: " + defaultURI);
            // e.printStackTrace(); // Opcional: Descomentar para ver la traza de error completa
            baseURI = defaultURI;
        }
        
        return baseURI;
    }

    public void publicar() {
        String uri = getBaseURI();
        
        endpoint = Endpoint.publish(uri + "/eventos", new ControladorEventoWS());
        System.out.println("Servicio 'eventos' publicado en " + uri + "/eventos");
        
        Endpoint.publish(uri + "/usuarios", new ControladorUsuarioWS());
        System.out.println("Servicio 'usuarios' publicado en " + uri + "/usuarios");
        
        Endpoint.publish(uri + "/instituciones", new ControladorInstitucionesWS());
        System.out.println("Servicio 'instituciones' publicado en " + uri + "/instituciones");
    }
    
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    public static void main(String[] args) {
        PublicadorWS p = new PublicadorWS();
        p.publicar();
        
        String uri = getBaseURI();
        
        System.out.println("Links WSDL: ");

        System.out.println("  Eventos:       " + uri + "/eventos?wsdl");
        System.out.println("  Usuarios:      " + uri + "/usuarios?wsdl");
        System.out.println("  Instituciones: " + uri + "/instituciones?wsdl");
    }
}