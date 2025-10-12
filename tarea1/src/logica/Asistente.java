package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.datatypes.ParEdicionRegistro;
import logica.datatypes.DataAsistente;
import logica.datatypes.DataDetalleRegistro;

public class Asistente extends Usuario{
	private String apellido;
	private LocalDate fechaNac;
	
	private List<Registro> regs;
	
	public Asistente(String nombre, String nickname, String email, String pass, String apellido, LocalDate fechaNac){
		super(nombre, nickname, email, pass);
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        
        this.regs = new ArrayList<>();
    }
	
	public Asistente(DataAsistente dataAsistente){
		super(dataAsistente.getNombre(), dataAsistente.getNickname(), dataAsistente.getEmail(), dataAsistente.getPass());
        this.apellido = dataAsistente.getApellido();
        this.fechaNac = dataAsistente.getFechaNac();
        
        this.regs = new ArrayList<>();
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }
    
    @Override
    public boolean esAsistente() {
    	return true;
    }
    
    @Override
    public boolean esOrganizador() {
    	return false;
    }
    
    
    
    public boolean estaRegistrado(String edicion) {
    	for (Registro r : regs) {
    		if (r.esEdicion(edicion))
    			return true;
    	}
    	return false;
    }
    
    public void agregarRegistro(Registro reg) {
    	this.regs.add(reg);
    }
    
    public List<ParEdicionRegistro> getEdicionesRegistros() {
    	List<ParEdicionRegistro> res = new ArrayList<ParEdicionRegistro>();
    	for (Registro r : regs) {
    		res.add(new ParEdicionRegistro(r.getNombreEdicion(), r.getFecha()));
    	}
    	return res;
    }
    
    public DataDetalleRegistro getDetallesRegistro(ParEdicionRegistro regEdicion) {
    	for (Registro r : regs) {
    		if (r.getNombreEdicion().equals(regEdicion.getNombreEdicion()) && r.getFecha().equals(regEdicion.getFechaRegistro())) {
    			return (new DataDetalleRegistro(regEdicion.getNombreEdicion(), r.getNombreTR(), r.getCostoTR(), regEdicion.getFechaRegistro()));
    		}
    	}
    	return null;
    }
    
    public Registro getRegistro(String edicion) {
    	for (Registro r : regs) {
    		if (r.getNombreEdicion().equals(edicion)) {
    			return r;
    		}
    	}
    	return null;
    }
}