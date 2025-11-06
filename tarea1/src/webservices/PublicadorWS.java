package webservices;

import jakarta.xml.ws.Endpoint;

public class PublicadorWS {
    
    private Endpoint endpoint = null;
    public PublicadorWS() {}
    
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:8081/eventos", new ControladorEventoWS());
        System.out.println("Servicio 'eventos' publicado en http://localhost:8081/eventos");
        
        Endpoint.publish("http://localhost:8081/usuarios", new ControladorUsuarioWS());
        System.out.println("Servicio 'usuarios' publicado en http://localhost:8081/usuarios");
        
        Endpoint.publish("http://localhost:8081/instituciones", new ControladorInstitucionesWS());
        System.out.println("Servicio 'instituciones' publicado en http://localhost:8081/instituciones");
    }
    
    public void despublicar() {
        try {
            if (endpoint != null && endpoint.isPublished()) {
                endpoint.stop();
                System.out.println("Servicios despublicados");
            }
        } catch (Exception e) {
            System.err.println("Error al despublicar: " + e.getMessage());
        }
    }
    
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    // AGREGAR ESTE MÉTODO MAIN DENTRO DE PublicadorWS
    public static void main(String[] args) {
        PublicadorWS p = new PublicadorWS();
        p.publicar();
        
        System.out.println("Links: ");
        System.out.println("  Eventos:       http://localhost:8081/eventos?wsdl");
        System.out.println("  Usuarios:      http://localhost:8081/usuarios?wsdl");
        System.out.println("  Instituciones: http://localhost:8081/instituciones?wsdl");
    }
}