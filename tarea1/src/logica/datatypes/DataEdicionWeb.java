package logica.datatypes;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataEdicionWeb", propOrder = {
    "nombre",
    "sigla",
    "fechaIni",
    "fechaFin",
    "fechaAlta",
    "ciudad",
    "pais",
    "estado"
})
public class DataEdicionWeb {
    
    @XmlElement(required = true)
    protected String nombre;
    
    @XmlElement(required = true)
    protected String sigla;
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaIni;
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaFin;
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaAlta;
    
    @XmlElement(required = true)
    protected String ciudad;
    
    @XmlElement(required = true)
    protected String pais;
    
    @XmlElement
    protected Estado estado;
    
    // Constructor vacío
    public DataEdicionWeb() {}
         
    public DataEdicionWeb(String nombre, String sigla, LocalDate fechaIni, LocalDate fechaFin, 
                         LocalDate fechaAlta, String ciudad, String pais, Estado estado) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.fechaAlta = fechaAlta;
        this.ciudad = ciudad;
        this.pais = pais;
        this.estado = estado;
    }
    
    // Getters y Setters
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
    public String getSigla() {
        return this.sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
        
    public LocalDate getFechaIni() {
        return this.fechaIni;
    }
    
    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }
        
    public LocalDate getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
        
    public LocalDate getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public Estado getEstado() {
        return this.estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}