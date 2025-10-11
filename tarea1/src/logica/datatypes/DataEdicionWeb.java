package logica.datatypes;

import java.time.LocalDate;

public class DataEdicionWeb {
	private String nombre;
	private String sigla;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private LocalDate fechaAlta;
	private String ciudad;
	private String pais;
	private Estado estado;
		 
	public DataEdicionWeb(String nombre, String sigla, LocalDate fechaIni, LocalDate fechaFin, LocalDate fechaAlta, String ciudad, String pais, Estado estado) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.fechaAlta = fechaAlta;
		this.ciudad = ciudad;
		this.pais = pais;
		this.estado = estado;
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
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public String getPais() {
		return this.pais;
	}
	
	public Estado getEstado() {
		return this.estado;
	}
}