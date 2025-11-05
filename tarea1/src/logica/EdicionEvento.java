package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;

import logica.datatypes.DataEdicion;
import logica.datatypes.Estado;

@Entity
@Table(name = "ediciones")
public class EdicionEvento {
    
    @Id
    @Column(nullable = false, unique = true, length = 200)
    private String nombre;
    
    @Column(length = 50)
    private String sigla;
    
    @Column(name = "fecha_inicio")
    private LocalDate fechaIni;
    
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
    
    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;
    
    @Column(length = 200)
    private String ciudad;
    
    @Column(length = 200)
    private String pais;
    
    @Column(name = "video_url", length = 500)
    private String videoUrl;
    
    @Column(name = "fecha_archivo")
    private LocalDate fechaArchivo;
    
    @ManyToOne
    @JoinColumn(name = "organizador_nickname", nullable = false)
    private Organizador organizador;
    
    @Transient
    private Set<TipoRegistro> tipoRegistros;
    
    @Transient
    private Set<Patrocinio> patrocinios;
    
    @OneToMany(mappedBy = "edi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Registro> registros;
    
    @Transient
    private Estado estado;
    
    // Constructor sin parámetros requerido por JPA
    public EdicionEvento() {
        this.tipoRegistros = new HashSet<>();
        this.patrocinios = new HashSet<>();
        this.registros = new ArrayList<>();
        this.estado = Estado.Ingresada;
    }
    
    public EdicionEvento(DataEdicion dataEd) {
        this.estado = Estado.Ingresada;
        this.nombre = dataEd.getNombre();
        this.sigla = dataEd.getSigla();
        this.fechaIni = dataEd.getFechaIni();
        this.fechaFin = dataEd.getFechaFin();
        this.fechaAlta = dataEd.getFechaAlta();
        this.ciudad = dataEd.getCiudad();
        this.pais = dataEd.getPais();
        this.videoUrl = dataEd.getUrl();
        this.organizador = null;
        this.tipoRegistros = new HashSet<>();
        this.patrocinios = new HashSet<>();
        this.registros = new ArrayList<>();
    }
    
    // Getters y setters (igual que antes)
    public String getNombre() {
        return this.nombre;
    }
    
    public String getSigla() {
        return this.sigla;
    }
    
    public LocalDate getFechaIni() {
        return this.fechaIni;
    }
    
    public LocalDate getFechaFin() {
        return this.fechaFin;
    }
    
    public LocalDate getFechaAlta() {
        return this.fechaAlta;
    }
    
    public LocalDate getFechaArchivo() {
        return this.fechaArchivo;
    }
    
    public String getCuidad() {
        return this.ciudad;
    }
    
    public String getPais() {
        return this.pais;
    }
    
    public String getUrl() {
        return this.videoUrl;
    }
    
    public Organizador getOrganizador() {
        return this.organizador;
    }
    
    public Estado getEstado() {
        return this.estado;
    }
    
    public Set<Patrocinio> getPatrocinios(){
        return this.patrocinios;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public void setFechaIni(LocalDate fecha) {
        this.fechaIni = fecha;
    }
    
    public void setFechaAlta(LocalDate fecha) {
        this.fechaAlta = fecha;
    }
    
    public void setFechaFin(LocalDate fecha) {
        this.fechaFin = fecha;
    }
    
    public void setFechaArchivo(LocalDate fecha) {
        this.fechaArchivo = fecha;
    }
    
    public void setVideoUrl(String url) {
        this.videoUrl = url;
    }
    
    public void setOrganizador(Organizador org) {
        this.organizador = org;
    }
    
    public void setEstado(Estado est) {
        this.estado = est;
    }
    
    public List<String> getTRegistro() {
        List<String> res = new ArrayList<>();
        for (TipoRegistro e : this.tipoRegistros) {
            res.add(e.getNombre());
        }
        return res;
    }
    
    public void agregarTRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistros.add(tipoRegistro);
    }
    
    public TipoRegistro getTRegistro(String tReg) {
        for (TipoRegistro tr : this.tipoRegistros) {
            if (tr.getNombre().equals(tReg)) {
                return tr;
            }
        }
        return null;
    }
    
    public boolean cupoTRegistro(String tReg) {
        for (TipoRegistro tr : tipoRegistros) {
            if (tr.getNombre().equals(tReg)) {
                int cupo = tr.getCupo();
                return cupo > 0;
            }
        }
        System.out.println("NO ENCONTRE");
        return false;
    }
    
    public List<String> getRegistrosInfo() {
        List<String> info = new ArrayList<>();
        for (Registro r : this.registros) {
            String tipo = (r.getTipoRegistro() != null) ? r.getTipoRegistro().getNombre() : "Sin tipo";
            String fecha = (r.getFecha() != null) ? r.getFecha().toString() : "Sin fecha";
            info.add("Fecha: " + fecha + ", Tipo: " + tipo);
        }
        return info;
    }

    public List<Patrocinio> getPatrociniosLista() {
        List<Patrocinio> lista = new ArrayList<>(this.patrocinios);
        return lista;
    }

    public void agregarPatrocinio(Patrocinio patro) {
        this.patrocinios.add(patro);
    }

    public void agregarOrganizador(Organizador org) {
        this.organizador = org;
    }
    
    public void agregarRegistro(Registro reg) {
        if (reg != null) {
            this.registros.add(reg);
        }
    }
}