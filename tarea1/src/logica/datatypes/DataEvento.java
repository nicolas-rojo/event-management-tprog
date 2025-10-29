package logica.datatypes;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataEvento", propOrder = {
    "nombre",
    "sigla",
    "fechaAlta",
    "descripcion"
})
public class DataEvento {
    
    @XmlElement(required = true)
    protected String nombre;
    
    @XmlElement(required = true)
    protected String sigla;
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaAlta;
    
    @XmlElement
    protected String descripcion;

    // Constructor vacío requerido por JAXB
    public DataEvento() {}

    public DataEvento(String nombre, String sigla, LocalDate fechaAlta, String descripcion) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.fechaAlta = fechaAlta;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDesc() {
        return descripcion;
    }

    public void setDesc(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre + " - " + sigla + " " + fechaAlta + " " + descripcion;
    }
}