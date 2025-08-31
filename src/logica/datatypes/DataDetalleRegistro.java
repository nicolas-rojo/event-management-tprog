package logica.datatypes;

import java.time.LocalDate;

public class DataDetalleRegistro {
    private String nombreEdicion;
    private String nombreTR;
    private String tipoRegistro;
    private float costo;
    private LocalDate fecha;
    private LocalDate fechaRegistro;
    

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
    
    public String getNombreEdicion() {
        return this.nombreEdicion;
    }
    
    public String getNombreTR() {
        return this.nombreTR;
    }
    
    public String getTipoRegistro() {
        return this.tipoRegistro != null ? this.tipoRegistro : this.nombreTR;
    }
    
    public float getCosto() {
        return this.costo;
    }
    
    public LocalDate getFecha() {
        return this.fecha;
    }
    
    public LocalDate getFechaRegistro() {
        return this.fechaRegistro != null ? this.fechaRegistro : this.fecha;
    }
}