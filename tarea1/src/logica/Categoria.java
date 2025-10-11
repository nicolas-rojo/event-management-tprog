package logica;
import java.util.Set;
import java.util.HashSet;

public class Categoria {
    private String nombre;
    private Set<Evento> eventos;
    
    public Categoria(String nombre) {
        this.nombre = nombre;
        this.eventos = new HashSet<Evento>();
    }
    
    public void addEvento(Evento evento) {
        this.eventos.add(evento);
    }
    
    public Set<Evento> getEventos() {
        return eventos;
    }
    
    public String getNombre() {
        return nombre;
    }
}