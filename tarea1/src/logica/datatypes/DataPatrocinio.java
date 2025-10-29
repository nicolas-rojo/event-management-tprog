package logica.datatypes;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPatrocinio", propOrder = {
    "fecha",
    "monto",
    "nivel",
    "cod",
    "ctdCupo"
})
public class DataPatrocinio {
    
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fecha;
    
    @XmlElement
    protected float monto;
    
    @XmlElement
    protected Nivel nivel;
    
    @XmlElement(required = true)
    protected String cod;
    
    @XmlElement
    protected int ctdCupo;
    
    // Constructor vacío
    public DataPatrocinio() {}
    
    public DataPatrocinio(LocalDate fecha, float monto, Nivel nivel, String cod, int ctdCupo) {
        this.fecha = fecha;
        this.monto = monto;
        this.nivel = nivel;
        this.cod = cod;
        this.ctdCupo = ctdCupo;
    }
    
    // Getters y Setters
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public float getMonto() {
        return monto;
    }
    
    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    public Nivel getNivel() {
        return nivel;
    }
    
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    public String getCod() {
        return cod;
    }
    
    public void setCod(String cod) {
        this.cod = cod;
    }
    
    public int getCtdCupo() {
        return ctdCupo;
    }
    
    public void setCupo(int cupo) {
        this.ctdCupo = cupo;
    }
}