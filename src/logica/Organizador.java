package logica;

import java.util.HashMap;

import logica.datatypes.DataOrganizador;

public class Organizador extends Usuario{
	private String descripcion;
	private String url;
	private HashMap<String, EdicionEvento> ediciones;
	
	public Organizador(String nombre, String nickname, String email, String descripcion, String url){
		super(nombre, nickname, email);
        this.descripcion = descripcion;
        this.url = url;
        
        ediciones = new HashMap<String, EdicionEvento>();
    }
	
	public Organizador(DataOrganizador dataOrg){
		super(dataOrg.getNombre(), dataOrg.getNickname(), dataOrg.getEmail());
        this.descripcion = dataOrg.getDescripcion();
        this.url = dataOrg.getUrl();
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public String getUrl() {
        return url;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrl(String url) {
        this.url = url;
    }
     
    public EdicionEvento[] getEdiciones() {
        if (ediciones == null) {
            return null;
        }
        
        EdicionEvento[] arrayEdiciones = new EdicionEvento[ediciones.size()];
        int i = 0;
        
        for (EdicionEvento edicion : ediciones.values()) {
            arrayEdiciones[i] = edicion;
            i++;
        }
        
        return arrayEdiciones;
    }
    
    @Override
    public boolean esAsistente() {
    	return false;
    }
    
    @Override
    public boolean esOrganizador() {
    	return true;
    }
    
    public void agregarEdicion(EdicionEvento edicion) {
    	this.ediciones.put(edicion.getNombre(), edicion);
    }
}