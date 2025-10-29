package logica.datatypes;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataDetalleRegistro", propOrder = {
    "nombreEdicion",
    "nombreTR",
    "tipoRegistro",
    "costo",
    "fecha",
    "fechaRegistro"
})
public class DataDetalleRegistro {
    
    @XmlElement(required = true)
    protected String nombreEdicion;
    
    @XmlElement(required = true)
    protected String nombreTR;
    
    @XmlElement
    protected String tipoRegistro;
    
    @XmlElement
    protected float costo;
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fecha;
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaRegistro;
    
    // Constructor vacío
    public DataDetalleRegistro() {}

    public DataDetalleRegistro(String nombreEd, String nombreTR, float costo, LocalDate fecha) {
        this.nombreEdicion = nombreEd;
        this.nombreTR = nombreTR;
        this.costo = costo;
        this.fecha = fecha;
        this.fechaRegistro = fecha;
        this.tipoRegistro = nombreTR;
    }
    
    public DataDetalleRegistro(String nombreEdicion, String nombreTR, String tipoRegistro, 
                              float costo, LocalDate fecha, LocalDate fechaRegistro) {
        this.nombreEdicion = nombreEdicion;
        this.nombreTR = nombreTR;
        this.tipoRegistro = tipoRegistro;
        this.costo = costo;
        this.fecha = fecha;
        this.fechaRegistro = fechaRegistro;
    }
    
    // Getters y Setters
    public String getNombreEdicion() {
        return this.nombreEdicion;
    }
    
    public void setNombreEdicion(String nombreEdicion) {
        this.nombreEdicion = nombreEdicion;
    }
    
    public String getNombreTR() {
        return this.nombreTR;
    }
    
    public void setNombreTR(String nombreTR) {
        this.nombreTR = nombreTR;
    }
    
    public String getTipoRegistro() {
        return this.tipoRegistro != null ? this.tipoRegistro : this.nombreTR;
    }
    
    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
    
    public float getCosto() {
        return this.costo;
    }
    
    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    public LocalDate getFecha() {
        return this.fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public LocalDate getFechaRegistro() {
        return this.fechaRegistro != null ? this.fechaRegistro : this.fecha;
    }
    
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}