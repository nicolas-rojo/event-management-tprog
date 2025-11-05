package logica;

import java.util.HashMap;
import java.util.Map;
import jakarta.persistence.*;

import logica.datatypes.DataOrganizador;

@Entity
@Table(name = "organizadores")
public class Organizador extends Usuario {
    
    @Column(length = 1000)
    private String descripcion;
    
    @Column(length = 500)
    private String url;
    
    @Transient
    private Map<String, EdicionEvento> ediciones;
    
    // Constructor sin parámetros requerido por JPA
    public Organizador() {
        super();
        this.ediciones = new HashMap<>();
    }
    
    public Organizador(String nombre, String nickname, String email, String pass, String descripcion, String url){
        super(nombre, nickname, email, pass);
        this.descripcion = descripcion;
        this.url = url;
        this.ediciones = new HashMap<>();
    }
    
    public Organizador(DataOrganizador dataOrg){
        super(dataOrg.getNombre(), dataOrg.getNickname(), dataOrg.getEmail(), dataOrg.getPass());
        this.descripcion = dataOrg.getDescripcion();
        this.url = dataOrg.getUrl();
        this.ediciones = new HashMap<>();
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public String getUrl() {
        return url;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrl(String url) {
        this.url = url;
    }
     
    public EdicionEvento[] getEdiciones() {
        if (ediciones == null) {
            return null;
        }
        
        EdicionEvento[] arrayEdiciones = new EdicionEvento[ediciones.size()];
        int i = 0;
        
        for (EdicionEvento edicion : ediciones.values()) {
            arrayEdiciones[i] = edicion;
            i++;
        }
        
        return arrayEdiciones;
    }
    
    @Override
    public boolean esAsistente() {
        return false;
    }
    
    @Override
    public boolean esOrganizador() {
        return true;
    }
    
    public void agregarEdicion(EdicionEvento edicion) {
        this.ediciones.put(edicion.getNombre(), edicion);
    }
}