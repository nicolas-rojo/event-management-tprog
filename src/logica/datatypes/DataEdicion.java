package logica.datatypes;

import java.time.LocalDate;
import java.util.Set;

import logica.Evento;
import logica.Organizador;


public class DataEdicion {
	private String nombre;
	private String sigla;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private LocalDate fechaAlta;
		
	public DataEdicion(String nombre, String sigla, LocalDate fechaIni, LocalDate fechaFin, LocalDate fechaAlta) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.fechaAlta = fechaAlta;
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
}
