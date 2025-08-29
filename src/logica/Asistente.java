package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Asistente extends Usuario{
	private String apellido;
	private LocalDate fechaNac;
	
	private List<Registro> regs;
	
	public Asistente(String nombre, String nickname, String email, String apellido, LocalDate fechaNac){
		super(nombre, nickname, email);
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        
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
}