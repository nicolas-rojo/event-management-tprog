package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logica.datatypes.DataEdicion;
import logica.datatypes.Estado;

public class EdicionEvento {
	private String nombre;
	private String sigla;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private LocalDate fechaAlta;
	private String ciudad;
	private String pais;
	private Organizador organizador;
	private Set<TipoRegistro> tipoRegistros;
	private Set<Patrocinio> patrocinios;
	private List<Registro> registros;
	private Estado estado;
	
	public EdicionEvento(DataEdicion dataEd) {
		this.estado = Estado.Confirmado;
		this.nombre = dataEd.getNombre();
		this.sigla = dataEd.getSigla();
		this.fechaIni = dataEd.getFechaIni();
		this.fechaFin = dataEd.getFechaFin();
		this.fechaAlta = dataEd.getFechaAlta();
		this.ciudad = dataEd.getCiudad();
		this.pais = dataEd.getPais();
		this.organizador = null;
		this.tipoRegistros = new HashSet<>();
		this.patrocinios = new HashSet<>();
		this.registros = new ArrayList<>();
	}
	
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
	
	public String getCuidad() {
		return this.ciudad;
	}
	
	public String getPais() {
		return this.pais;
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
	
	public void setOrganizador(Organizador o) {
		this.organizador = o;
	}
	
	public void setEstado(Estado e) {
		this.estado = e;
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
				return (cupo > 0);
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

	public void agregarPatrocinio(Patrocinio p) {
	    this.patrocinios.add(p);
	}

	public void agregarOrganizador(Organizador org) {
	    this.organizador = org;
	}
	
	public void agregarRegistro(Registro r) {
		if(r != null) {
			this.registros.add(r);
		}
	}
}
