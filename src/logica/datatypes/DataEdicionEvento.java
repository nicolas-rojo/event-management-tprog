package logica.datatypes;

import java.time.LocalDate;

public class DataEdicionEvento {
    private String nombre;
    private String sigla;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private LocalDate fechaAlta;
    private String ciudad;
    private String pais;

    public DataEdicionEvento(String nombre, String sigla, LocalDate fechaIni, LocalDate fechaFin,
                              LocalDate fechaAlta, String ciudad, String pais) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.fechaAlta = fechaAlta;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }
}
