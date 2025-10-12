package logica;
import java.util.*;

import logica.datatypes.DataInstitucion;

public class Institucion {
    private String nombre;
    private String descripcion;
    private String url;
    private Set<Patrocinio> patrocinios;
    
    public Institucion(DataInstitucion dataIns) {
        this.nombre = dataIns.getNombre();
        this.descripcion = dataIns.getDescripcion();
        this.url = dataIns.getUrl();
        this.patrocinios = new HashSet<>();
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Set<Patrocinio> getPatrocinios() {
        return this.patrocinios;
    }
    
    public void añadirPatrocinio(Patrocinio patro) {
        this.patrocinios.add(patro);
    }
}