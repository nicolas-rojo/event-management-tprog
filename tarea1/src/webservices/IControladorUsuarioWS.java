package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.time.LocalDate;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import java.util.List;

import logica.datatypes.*;
import excepciones.*;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public interface IControladorUsuarioWS {
    
	@WebMethod
	void cargarDatos();
	
    @WebMethod
    void registrarAsistente(DataAsistente dataAsistente) throws UsuarioRepetidoException;
    
    @WebMethod
    void registrarOrganizador(DataOrganizador dataOrg) throws UsuarioRepetidoException;
    
    @WebMethod
    DataAsistente getAsistente(String email) throws UsuarioNoExisteException;
    
    @WebMethod
    DataOrganizador getOrganizador(String nickmail) throws UsuarioNoExisteException;
    
    @WebMethod
    void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException;
    
    @WebMethod
    void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException;
    
    @WebMethod
    void modificarAsistenteConPassword(String email, String nuevoNombre, String nuevoApellido, String passActual, String passNueva) throws UsuarioNoExisteException, ContrasenaIncorrectaException;
    
    @WebMethod
    void modificarOrganizadorConPassword(String email, String nuevoNombre, String descripcion, String url, String passActual, String passNueva) throws UsuarioNoExisteException, ContrasenaIncorrectaException;
    
    @WebMethod
    String getTipoUsuario(String email) throws UsuarioNoExisteException;
    
    @WebMethod
    ParEdicionRegistro[] getRegistrosAsistente(String asistenteSeleccionado);
    
    @WebMethod
    void nuevoRegistro(String asistenteSeleccionado, String evento, String edicion, String tipoReg, LocalDate fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro, FechaRegistroInvalidaException;
    
    @WebMethod
    DataUsuario login(String nickmail, String pass);
    
    @WebMethod
    ParEdicionRegistro estaRegistrado(String asistente, String edicion);
    
    @WebMethod
    String[] getUsuariosRegistrados(String edicion);
    
    @WebMethod
    DataEdicion[] getEdicionesEventoOrganizador(String nickname);
    
    @WebMethod
    DataEdicionWeb[] getEdicionesEventoOrganizadorWeb(String nickname);
}