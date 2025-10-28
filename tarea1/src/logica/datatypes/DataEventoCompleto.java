package logica.datatypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataEventoCompleto", propOrder = {
    "nombre",
    "sigla",
    "fechaAlta",
    "descripcion",
    "categorias",
    "ediciones"
})
public class DataEventoCompleto {
    
    @XmlElement(required = true)
    protected String nombre;
    
    @XmlElement(required = true)
    protected String sigla;
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaAlta;
    
    @XmlElement
    protected String descripcion;
    
    @XmlElement(nillable = true)
    protected List<String> categorias;
    
    @XmlElement(nillable = true)
    protected List<String> ediciones;

    public DataEventoCompleto() {}

    public DataEventoCompleto(String nombre, String sigla, LocalDate fechaAlta, 
                             String descripcion, Set<String> categorias, Set<String> ediciones) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.fechaAlta = fechaAlta;
        this.descripcion = descripcion;
        this.categorias = new ArrayList<>(categorias);
        this.ediciones = new ArrayList<>(ediciones);
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

    public String getDescripcion() { 
        return descripcion; 
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<String> getCategorias() { 
        return categorias != null ? new HashSet<>(categorias) : new HashSet<>(); 
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public void setCategoriasSet(Set<String> categorias) {
        this.categorias = new ArrayList<>(categorias);
    }

    public Set<String> getEdiciones() { 
        return ediciones != null ? new HashSet<>(ediciones) : new HashSet<>(); 
    }

    public void setEdiciones(List<String> ediciones) {
        this.ediciones = ediciones;
    }

    public void setEdicionesSet(Set<String> ediciones) {
        this.ediciones = new ArrayList<>(ediciones);
    }
}