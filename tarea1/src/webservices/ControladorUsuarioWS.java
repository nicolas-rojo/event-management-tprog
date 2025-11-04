package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.time.LocalDate;
import logica.Fabrica;
import logica.interfaces.IUsuario;
import logica.interfaces.IEventos;
import logica.datatypes.*;
import excepciones.*;

@WebService(endpointInterface = "webservices.IControladorUsuarioWS")
public class ControladorUsuarioWS implements IControladorUsuarioWS {
    
    private IUsuario controladorUsuario;
    private IEventos controladorEventos;
    
    public ControladorUsuarioWS() {
        Fabrica fabrica = Fabrica.getInstance();
        this.controladorUsuario = fabrica.getIControladorUsuario();
        this.controladorEventos = fabrica.getIControladorEventos();
    }
    
    @Override
    public DataDetalleRegistro getDetallesRegistro(String asistenteSeleccionado, ParEdicionRegistro regEdicion)  throws ErrorDetallesRegistroException{
    	return controladorUsuario.getDetallesRegistro(asistenteSeleccionado, regEdicion);
    }

    
    @Override
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException{
    	return controladorUsuario.getUsuarios();
    }
    
    @Override
    public void cargarDatos() {
		controladorUsuario.cargarDatos();
    } 
    
    @Override
    public void registrarAsistente(DataAsistente dataAsistente) throws UsuarioRepetidoException {
        controladorUsuario.registrarAsistente(dataAsistente);
    }
    
    @Override
    public void registrarOrganizador(DataOrganizador dataOrg) throws UsuarioRepetidoException {
        controladorUsuario.registrarOrganizador(dataOrg);
    }
    
    @Override
    public DataAsistente getAsistente(String email) throws UsuarioNoExisteException {
        return controladorUsuario.getAsistente(email);
    }
    
    @Override
    public DataOrganizador getOrganizador(String nickmail) throws UsuarioNoExisteException {
        return controladorUsuario.getOrganizador(nickmail);
    }
    
    @Override
    public void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException {
        controladorUsuario.modificarAsistente(email, nuevoNombre, nuevoApellido);
    }
    
    @Override
    public void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException {
        controladorUsuario.modificarOrganizador(email, nuevoNombre, descripcion, url);
    }
    
    @Override
    public void modificarAsistenteConPassword(String email, String nuevoNombre, String nuevoApellido, String passActual, String passNueva) throws UsuarioNoExisteException, ContrasenaIncorrectaException {
        controladorUsuario.modificarAsistenteConPassword(email, nuevoNombre, nuevoApellido, passActual, passNueva);
    }
    
    @Override
    public void modificarOrganizadorConPassword(String email, String nuevoNombre, String descripcion, String url, String passActual, String passNueva) throws UsuarioNoExisteException, ContrasenaIncorrectaException {
        controladorUsuario.modificarOrganizadorConPassword(email, nuevoNombre, descripcion, url, passActual, passNueva);
    }
    
    @Override
    public String getTipoUsuario(String email) throws UsuarioNoExisteException {
        return controladorUsuario.getTipoUsuario(email);
    }
    
    @Override
    public ParEdicionRegistro[] getRegistrosAsistente(String asistenteSeleccionado) {
        return controladorUsuario.getRegistrosAsistente(asistenteSeleccionado).toArray(new ParEdicionRegistro[0]);
    }
    
    @Override
    public void nuevoRegistro(String asistenteSeleccionado, String evento, String edicion, String tipoReg, String fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro, FechaRegistroInvalidaException {
        controladorUsuario.nuevoRegistro(asistenteSeleccionado, evento, edicion, tipoReg, LocalDate.parse(fecha));
    }
    
    @Override
    public DataUsuario login(String nickmail, String pass) {
        return controladorUsuario.login(nickmail, pass);
    }
    
    @Override
    public ParEdicionRegistro estaRegistrado(String asistente, String edicion) {
    	ParEdicionRegistro reg = controladorUsuario.estaRegistrado(asistente, edicion);
    	if (reg == null) {
    		return new ParEdicionRegistro("", (LocalDate) null);
    	}
    	return reg;
    }
    
    @Override
    public String[] getUsuariosRegistrados(String edicion) {
        return controladorUsuario.getUsuariosRegistrados(edicion).toArray(new String[0]);
    }
    
    @Override
    public DataEdicion[] getEdicionesEventoOrganizador(String nickname) {
        return controladorEventos.getEdicionesEventoOrganizador(nickname);
    }
    
    @Override
    public DataEdicionWeb[] getEdicionesEventoOrganizadorWeb(String nickname) {
        return controladorEventos.getEdicionesEventoOrganizadorWeb(nickname);
    }
    
    
    // Funciones para la parte 3
    
    @Override
    public void seguirUsuario(String seguidor, String aSeguir) {
    	controladorUsuario.seguirUsuario(seguidor, aSeguir);
    }
    
    @Override
    public String[] getSeguidos(String usr) {
    	return controladorUsuario.getSeguidos(usr).toArray(new String[0]);
    }
    
    @Override
    public String[] getSeguidores(String usr) {
    	return controladorUsuario.getSeguidores(usr).toArray(new String[0]);
    }
    
    @Override
    public Boolean esSeguidor(String usr, String seguidor) {
    	return controladorUsuario.esSeguidor(usr, seguidor);
    }
    
    @Override
    public void dejarDeSeguir(String seguidor, String seguido) {
    	controladorUsuario.dejarDeSeguir(seguidor, seguido);
    }
    
    @Override
    public Boolean verificarAsistencia(String edicion, String usuario) {
    	return controladorUsuario.verificarAsistencia(edicion, usuario);
    }
    
    @Override
    public void setAsistencia(String edicion, String usuario) {
    	controladorUsuario.setAsistencia(edicion, usuario);
    }
}