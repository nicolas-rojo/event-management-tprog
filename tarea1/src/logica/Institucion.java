package logica;
import java.util.*;

import logica.datatypes.DataInstitucion;

public class Institucion {
    private String nombre;
    private String descripcion;
    private String url;
    private Set<Patrocinio> patrocinios;
    
    public Institucion(DataInstitucion di) {
        this.nombre = di.getNombre();
        this.descripcion = di.getDescripcion();
        this.url = di.getUrl();
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
    
    public void añadirPatrocinio(Patrocinio p) {
        this.patrocinios.add(p);
    }
}