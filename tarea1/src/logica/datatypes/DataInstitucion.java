package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataInstitucion", propOrder = {
    "nombre",
    "descripcion",
    "url"
})
public class DataInstitucion {
    
    @XmlElement(required = true)
    protected String nombre;
    
    @XmlElement
    protected String descripcion;
    
    @XmlElement
    protected String url;
    
    // Constructor vacío
    public DataInstitucion() {}
    
    public DataInstitucion(String nombre, String desc, String url) {
        this.nombre = nombre;
        this.descripcion = desc;
        this.url = url;
    }
    
    // Getters y Setters
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}