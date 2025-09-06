package logica.datatypes;

import java.time.LocalDate;

public class DTOPatrocinioCompleto {
    private LocalDate fecha;
    private float monto;
    private Nivel nivel;
    private String cod;
    private int ctdCupo;
    private String institucion;
    private String tipoRegistro;

    public DTOPatrocinioCompleto(LocalDate fecha, float monto, Nivel nivel, String cod, 
                                 int ctdCupo, String institucion, String tipoRegistro) {
        this.fecha = fecha;
        this.monto = monto;
        this.nivel = nivel;
        this.cod = cod;
        this.ctdCupo = ctdCupo;
        this.institucion = institucion;
        this.tipoRegistro = tipoRegistro;
    }

    // Getters
    public LocalDate getFecha() { return fecha; }
    public float getMonto() { return monto; }
    public Nivel getNivel() { return nivel; }
    public String getCod() { return cod; }
    public int getCtdCupo() { return ctdCupo; }
    public String getInstitucion() { return institucion; }
    public String getTipoRegistro() { return tipoRegistro; }
}