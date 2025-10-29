package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataTRegistro", propOrder = {
    "nombre",
    "descripcion",
    "costo",
    "cupo"
})
public class DataTRegistro {
    
    @XmlElement(required = true)
    protected String nombre;
    
    @XmlElement
    protected String descripcion;
    
    @XmlElement
    protected float costo;
    
    @XmlElement
    protected int cupo;
    
    // Constructor vacío
    public DataTRegistro() {}
    
    public DataTRegistro(String nombre, String descripcion, float costo, int cupo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.cupo = cupo;
    }
    
    // Getters y Setters
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescr() {
        return this.descripcion;
    }
    
    public void setDescr(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public float getCosto() {
        return this.costo;
    }
    
    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    public int getCupo() {
        return this.cupo;
    }
    
    public void setCupo(int cupo) {
        this.cupo = cupo;
    }
}