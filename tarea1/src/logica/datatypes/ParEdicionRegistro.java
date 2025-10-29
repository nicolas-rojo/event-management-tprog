package logica.datatypes;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parEdicionRegistro", propOrder = {
    "nombreEdicion",
    "fechaRegistro"
})
public class ParEdicionRegistro {
    
    @XmlElement(required = true)
    protected String nombreEdicion;
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaRegistro;
    
    // Constructor vacío
    public ParEdicionRegistro() {}
    
    public ParEdicionRegistro(String nombreEdicion, LocalDate fechaRegistro) {
        this.nombreEdicion = nombreEdicion;
        this.fechaRegistro = fechaRegistro;
    }
    
    public ParEdicionRegistro(String nombreEvento, String nombreEdicion) {
        this.nombreEdicion = nombreEdicion;
        this.fechaRegistro = null;
    }
    
    // Getters y Setters
    public String getNombreEdicion() {
        return this.nombreEdicion;
    }
    
    public void setNombreEdicion(String nombreEdicion) {
        this.nombreEdicion = nombreEdicion;
    }

    public LocalDate getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    // Método adicional para compatibilidad con ConsultaUsuario
    public String getNombreEvento() {
        // Extraer el nombre del evento desde nombreEdicion si tiene formato "evento - edicion"
        if (nombreEdicion != null && nombreEdicion.contains(" - ")) {
            return nombreEdicion.split(" - ")[0];
        }
        return "N/A";
    }
    
    @Override
    public String toString() {
        return nombreEdicion + (fechaRegistro != null ? " - " + fechaRegistro : "");
    }
}