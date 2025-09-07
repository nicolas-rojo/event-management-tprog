package logica.datatypes;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DataEventoCompleto {
	 private final String nombre;
	 private final String sigla;
	 private final LocalDate fechaAlta;
	 private final String descripcion;
	 private final Set<String> categorias;
	 private final Set<String> ediciones;
	 
	 public DataEventoCompleto(String nombre, String sigla, LocalDate fechaAlta, String descripcion, Set<String> categorias, Set<String> ediciones ) {
			this.nombre = nombre;
		    this.sigla = sigla;
		    this.fechaAlta = fechaAlta;
		    this.descripcion = descripcion;
		    this.categorias = new HashSet<>(categorias); //puse que se copie pa que no joda el otro si prefieren que pase el original (NICO)
	        this.ediciones = new HashSet<>(ediciones);
			}
	 //Todos los getters aunque en un data transfer object no se si se usaran mucho
	 public String getNombre() { return nombre; }
	 public String getSigla() { return sigla; }
	 public LocalDate getFechaAlta() { return fechaAlta; }
	 public String getDescripcion() { return descripcion; }
	 public Set<String> getCategorias() { return new HashSet<>(categorias); }
	 public Set<String> getEdiciones() { return new HashSet<>(ediciones); }
}