package logica;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "registros")
public class Registro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaReg;
    
    @ManyToOne
    @JoinColumn(name = "edicion_nombre", nullable = false)
    private EdicionEvento edi;
    
    @ManyToOne
    @JoinColumn(name = "asistente_nickname", nullable = false)
    private Asistente asistente;
    
    @Transient
    private TipoRegistro treg;
    
    @Column(nullable = false)
    private boolean asistio;
    
    // Constructor sin parámetros requerido por JPA
    public Registro() {
        this.asistio = false;
    }
    
    public Registro(LocalDate fecha) {
        this.fechaReg = fecha;
        this.asistio = false;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public LocalDate getFecha() {
        return this.fechaReg;
    }
    
    public TipoRegistro getTipoRegistro() {
        return this.treg;
    }
    
    public Asistente getAsistente() {
        return this.asistente;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fechaReg = fecha;
    }
    
    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }
    
    public String getNombreEdicion() {
        return this.edi.getNombre();
    }
    
    public String getNombreTR() {
        return this.treg.getNombre();
    }
    
    public float getCostoTR() {
        return this.treg.getCosto();
    }
    
    public boolean getAsistencia() {
        return this.asistio;
    }
    
    public void setAsistencia(boolean asistencia) {
        this.asistio = asistencia;
    }
    
    public boolean esEdicion(String edicion) {
        return edi.getNombre().equals(edicion);
    }
    
    public void asociarEdicion(EdicionEvento edev) {
        this.edi = edev;
    }
    
    public void asociarTRegistro(TipoRegistro treg) {
        this.treg = treg;
    }
}