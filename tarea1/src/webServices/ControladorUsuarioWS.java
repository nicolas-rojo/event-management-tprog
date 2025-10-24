package webServices;

import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

import logica.Fabrica;
import logica.interfaces.IUsuario;
import logica.interfaces.IEventos;
import logica.datatypes.*;
import excepciones.*;

@WebService(endpointInterface = "com.miseventos.webservices.IControladorUsuarioWS")
public class ControladorUsuarioWS implements IControladorUsuarioWS {
    
    private IUsuario controladorUsuario;
    private IEventos controladorEventos;
    
    public ControladorUsuarioWS() {
        Fabrica fabrica = Fabrica.getInstance();
        this.controladorUsuario = fabrica.getIControladorUsuario();
        this.controladorEventos = fabrica.getIControladorEventos();
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
    public List<ParEdicionRegistro> getRegistrosAsistente(String asistenteSeleccionado) {
        return controladorUsuario.getRegistrosAsistente(asistenteSeleccionado);
    }
    
    @Override
    public void nuevoRegistro(String asistenteSeleccionado, String evento, String edicion, String tipoReg, LocalDate fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro, FechaRegistroInvalidaException {
        controladorUsuario.nuevoRegistro(asistenteSeleccionado, evento, edicion, tipoReg, fecha);
    }
    
    @Override
    public DataUsuario login(String nickmail, String pass) {
        return controladorUsuario.login(nickmail, pass);
    }
    
    @Override
    public ParEdicionRegistro estaRegistrado(String asistente, String edicion) {
        return controladorUsuario.estaRegistrado(asistente, edicion);
    }
    
    @Override
    public List<String> getUsuariosRegistrados(String edicion) {
        return controladorUsuario.getUsuariosRegistrados(edicion);
    }
    
    @Override
    public DataEdicion[] getEdicionesEventoOrganizador(String nickname) {
        return controladorEventos.getEdicionesEventoOrganizador(nickname);
    }
    
    @Override
    public DataEdicionWeb[] getEdicionesEventoOrganizadorWeb(String nickname) {
        return controladorEventos.getEdicionesEventoOrganizadorWeb(nickname);
    }
}