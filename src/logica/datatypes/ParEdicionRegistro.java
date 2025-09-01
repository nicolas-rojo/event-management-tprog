package logica.datatypes;

import java.time.LocalDate;

public class ParEdicionRegistro {
    private String nombreEdicion;
    private LocalDate fechaRegistro;
    
    // Constructor original (según el archivo que me mostraste)
    public ParEdicionRegistro(String nombreEdicion, LocalDate fechaRegistro) {
        this.nombreEdicion = nombreEdicion;
        this.fechaRegistro = fechaRegistro;
    }
    
    // Constructor adicional para casos especiales en ConsultaUsuario
    public ParEdicionRegistro(String nombreEvento, String nombreEdicion) {
        this.nombreEdicion = nombreEdicion;
        this.fechaRegistro = null;
    }
    
    public String getNombreEdicion() {
        return this.nombreEdicion;
    }

    public LocalDate getFechaRegistro() {
        return this.fechaRegistro;
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