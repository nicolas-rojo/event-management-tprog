package logica.ctrlmanejador;

import logica.interfaces.IEventos;
import logica.interfaces.IInstituciones;
import logica.interfaces.IUsuario;

import excepciones.UsuarioRepetidoException;
import excepciones.AsistenteYaRegistrado;
import excepciones.CategoriaRepetidaException;
import excepciones.EdicionRepetidaExcepcion;
import excepciones.ErrorDetallesRegistroException;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.InstitucionRepetidaException;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.PatrocinioRepetidoException;
import excepciones.TipoDeRegistroRepetidoException;
import excepciones.UsuarioNoExisteException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import logica.Asistente;
import logica.Fabrica;
import logica.Organizador;
import logica.Usuario;
import logica.datatypes.*;



public class ControladorUsuario implements IUsuario {

    public ControladorUsuario() {
    }

    public void registrarAsistente(String nombre, String nickname, String email, String pass, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.getUsuarioNickname(nickname);
        if (u != null)
            throw new UsuarioRepetidoException("Nickname ya en uso");
        u = mu.getUsuarioEmail(email);
        if (u != null)
            throw new UsuarioRepetidoException("Email ya en uso");
        Asistente a = new Asistente(nombre, nickname, email, pass, apellido, fechaNac);
        mu.addUsuario(a);
    }
    
    public void registrarAsistente(DataAsistente datos) throws UsuarioRepetidoException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.getUsuarioNickname(datos.getNickname());
        if (u != null)
            throw new UsuarioRepetidoException("Nickname ya en uso");
        u = mu.getUsuarioEmail(datos.getEmail());
        if (u != null)
            throw new UsuarioRepetidoException("Email ya en uso");
        Asistente a = new Asistente(datos.getNombre(), datos.getNickname(), datos.getEmail(), datos.getPass(), datos.getApellido(), datos.getFechaNac());
        mu.addUsuario(a);
    }
    
    public void registrarOrganizador(String nombre, String nickname, String email, String pass, String descripcion, String url) throws UsuarioRepetidoException {
    	ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.getUsuarioNickname(nickname);
        if (u != null)
            throw new UsuarioRepetidoException("Nickname ya en uso");
        u = mu.getUsuarioEmail(email);
        if (u != null)
            throw new UsuarioRepetidoException("Email ya en uso");
        Organizador o = new Organizador(nombre, nickname, email, pass, descripcion, url);
        mu.addUsuario(o);
    }
    
    public void registrarOrganizador(DataOrganizador datos) throws UsuarioRepetidoException {
    	ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.getUsuarioNickname(datos.getNickname());
        if (u != null)
            throw new UsuarioRepetidoException("Nickname ya en uso");
        u = mu.getUsuarioEmail(datos.getEmail());
        if (u != null)
            throw new UsuarioRepetidoException("Email ya en uso");
        Organizador o = new Organizador(datos.getNombre(), datos.getNickname(), datos.getEmail(), datos.getPass(), datos.getDescripcion(), datos.getUrl());
        mu.addUsuario(o);
    }

    public DataAsistente getAsistente(String email) throws UsuarioNoExisteException{
    	ManejadorUsuario mu = ManejadorUsuario.getInstance();
    	Asistente a = (Asistente) mu.getUsuarioEmail(email);
    	if (a != null)
    		return new DataAsistente(a.getNombre(), a.getNickname(), a.getEmail(), "", a.getApellido(), a.getFechaNac());
    	else
    		throw new UsuarioNoExisteException("No existe usuario con dicho email");
    }
    
    public DataOrganizador getOrganizador(String nickmail) throws UsuarioNoExisteException{
    	ManejadorUsuario mu = ManejadorUsuario.getInstance();
    	Organizador o = (Organizador) mu.getUsuarioEmail(nickmail);
    	if (o != null)
    		return new DataOrganizador(o.getNombre(), o.getNickname(), o.getEmail(), "", o.getDescripcion(), o.getUrl());
    	else {
    		o = (Organizador) mu.getUsuarioNickname(nickmail);
    		if (o != null) {
    			return new DataOrganizador(o.getNombre(), o.getNickname(), o.getEmail(), "", o.getDescripcion(), o.getUrl());    			
    		} else {
    			throw new UsuarioNoExisteException("No existe usuario con dicho email");    			
    		}    		
    	}
    }
    
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario[] usrs = mu.getUsuarios();

        if (usrs != null) {
            DataUsuario[] du = new DataUsuario[usrs.length];
            Usuario usuario;

            for (int i = 0; i < usrs.length; i++) {
                usuario = usrs[i];
                du[i] = new DataUsuario(usuario.getNombre(), usuario.getNickname(), usuario.getEmail(), "");
            }

            return du;
        } else
            throw new UsuarioNoExisteException("No existen usuarios registrados");

    }

	public void modificarAsistente(String email, String nuevoNombre, String nuevoApellido) throws UsuarioNoExisteException {
	    ManejadorUsuario mu = ManejadorUsuario.getInstance();
	    Usuario u = mu.getUsuarioEmail(email);
	    if (u == null) {
	        throw new UsuarioNoExisteException("Error, no hay usuario con el email ingresdo");
	    }
	    Asistente a = (Asistente) u;
	    a.setNombre(nuevoNombre);
	    a.setApellido(nuevoApellido);
	}
	
	public void modificarOrganizador(String email, String nuevoNombre, String descripcion, String url) throws UsuarioNoExisteException {
	    ManejadorUsuario mu = ManejadorUsuario.getInstance();
	    Usuario u = mu.getUsuarioEmail(email);
	    if (u == null) {
	        throw new UsuarioNoExisteException("Error, no hay usuario con el email ingresdo");
	    }
	    Organizador o = (Organizador) u;
	    o.setNombre(nuevoNombre);
	    o.setDescripcion(descripcion);
	    o.setUrl(url);
	}
	
	public String getTipoUsuario(String email) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.getUsuarioEmail(email);
        
        if (u == null) {
            throw new UsuarioNoExisteException("No existe usuario con email: " + email);
        }
        
        if (u instanceof Asistente) {
            return "Asistente";
        } else if (u instanceof Organizador) {
            return "Organizador";
        } else {
            throw new UsuarioNoExisteException("Tipo de usuario desconocido para email: " + email);
        }
    }
	
	public List<String> listarAsistentes() {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		return mu.getNombreAsist();
	}
	
	public List<String> listarOrganizadores() {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		return mu.getOrganizadores();
	}
	
	public void nuevoRegistro(String asistenteSeleccionado, String evento, String edicion, String tipoReg, LocalDate fecha) throws AsistenteYaRegistrado, NoHayCupoEdicionTRegistro {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Usuario u = mu.getUsuarioNickname(asistenteSeleccionado);
		Asistente asistente = (Asistente) u;
		ControladorEventos ce = new ControladorEventos();
		ce.nuevoRegistro(asistente, evento, edicion, tipoReg, fecha);
	}
	
	public List<ParEdicionRegistro> getRegistrosAsistente(String asistenteSeleccionado) {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Asistente a = (Asistente) mu.getUsuarioNickname(asistenteSeleccionado);
		return a.getEdicionesRegistros();
	}
	
	public ParEdicionRegistro estaRegistrado(String asistente, String edicion) {
		List<ParEdicionRegistro> registros = getRegistrosAsistente(asistente);
		ParEdicionRegistro res = null;
		for (ParEdicionRegistro registro : registros) {
			if (registro.getNombreEdicion().equals(edicion))
				res = registro;
				break;
		}
		return res;
	}
	
	public DataDetalleRegistro getDetallesRegistro(String asistenteSeleccionado, ParEdicionRegistro regEdicion) throws ErrorDetallesRegistroException {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Asistente a = (Asistente) mu.getUsuarioNickname(asistenteSeleccionado);
		DataDetalleRegistro res = a.getDetallesRegistro(regEdicion);
		if (res == null) {
			throw new ErrorDetallesRegistroException("Hubo un error al recuperar los datos del registro seleccionado");
		}
		return res;
	}
	
	public DataUsuario login(String nickmail, String pass) {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Usuario u = mu.getUsuarioNickname(nickmail);

		if (u == null)
			u = mu.getUsuarioEmail(nickmail);

		if (u == null)
			return null;

		if (!u.getPass().equals(pass))
			return null;

		return (new DataUsuario(u.getNombre(), u.getNickname(), u.getEmail(), ""));
	}
	
	public void cargarDatos() {
		Fabrica fabrica = Fabrica.getInstance();
		IEventos IEV = fabrica.getIControladorEventos();
		IUsuario ICU = fabrica.getIControladorUsuario();
		IInstituciones II = fabrica.getIControladorInstituciones();
		
		try {
    		IEV.nuevaCategoria("Tecnologia");
    		IEV.nuevaCategoria("Innovacion");
    		IEV.nuevaCategoria("Literatura");
    		IEV.nuevaCategoria("Cultura");
    		IEV.nuevaCategoria("Musica");
    		IEV.nuevaCategoria("Deporte");
    		IEV.nuevaCategoria("Salud");
    		IEV.nuevaCategoria("Entretenimiento");
    		IEV.nuevaCategoria("Agro");
    		IEV.nuevaCategoria("Negocios");
    		IEV.nuevaCategoria("Moda");
    		IEV.nuevaCategoria("Investigacion");
        	
        	ICU.registrarAsistente(new DataAsistente("Ana", "atorres", "atorres@gmail.com", "123.torres", "Torres", LocalDate.of(1990, 5, 12)));
        	ICU.registrarAsistente(new DataAsistente("Martin", "msilva", "martin.silva@fing.edu.uy", "msilva2025", "Silva",  LocalDate.of(1987, 8, 21)));
        	ICU.registrarAsistente(new DataAsistente("Sofia", "sofirod", "srodriguez@outlook.com", "srod.abc1", "Rodriguez",  LocalDate.of(1995, 2, 3)));
        	ICU.registrarAsistente(new DataAsistente("Valentina", "vale23", "valentina.costa@mail.com", "valen11c", "Costa",  LocalDate.of(1992, 12, 1)));
        	ICU.registrarAsistente(new DataAsistente("Lucía", "luciag", "lucia.garcia@mail.com", "garcia.221", "García",  LocalDate.of(1993, 11, 9)));
        	ICU.registrarAsistente(new DataAsistente("Andrea", "andrearod", "andrea.rod@mail.com", "rod77and", "Rodriguez", LocalDate.of(2000, 6, 10)));
        	ICU.registrarAsistente(new DataAsistente("Ana", "AnaG", "ana.gomez@hotmail.com", "gomez88a", "Gómez",  LocalDate.of(1998, 3, 15)));
        	ICU.registrarAsistente(new DataAsistente("Javier", "JaviL", "javier.lopez@outlook.com", "jl99lopez", "López",  LocalDate.of(1995, 7, 22)));
        	ICU.registrarAsistente(new DataAsistente("María", "MariR", "maria.rodriguez@gmail.com", "maria55r", "Rodríguez",  LocalDate.of(2000, 11, 10)));
        	ICU.registrarAsistente(new DataAsistente("Sofía", "SofiM", "sofia.martinez@yahoo.com", "smarti99z", "Martínez",  LocalDate.of(1997, 2, 5)));
        	
        	ICU.registrarOrganizador(new DataOrganizador("MisEventos", "miseventos", "contacto@miseventos.com", "22miseventos", "Empresa de organización de eventos.", "https://miseventos.com"));
        	ICU.registrarOrganizador(new DataOrganizador("Corporación Tecnológica", "techcorp", "info@techcorp.com", "tech25corp", "Empresa líder en tecnologías de la información", ""));
        	ICU.registrarOrganizador(new DataOrganizador("Intendencia de Montevideo", "imm", "contacto@imm.gub.uy", "imm2025", "Gobierno departamental de Montevideo.", "https://montevideo.gub.uy"));
        	ICU.registrarOrganizador(new DataOrganizador("Universidad de la República", "udelar", "contacto@udelar.edu.uy", "25udelar", "Universidad pública de Uruguay.", "https://udelar.edu.uy"));
        	ICU.registrarOrganizador(new DataOrganizador("Ministerio de Educación y Cultura", "mec", "mec@mec.gub.uy", "mec2025ok", "Institución pública promotora de cultura", "https://mec.gub.uy"));
        	
        	II.nuevaInstitucion(new DataInstitucion("Facultad de Ingeniería", "Facultad de Ingeniería de la Universidad de la República", "https://www.fing.edu.uy"));
        	II.nuevaInstitucion(new DataInstitucion("ORT Uruguay", "Universidad privada enfocada en tecnología y gestiín", "https://ort.edu.uy"));
        	II.nuevaInstitucion(new DataInstitucion("Universidad Católica del Uruguay", "Institución de educación superior privada", "https://ucu.edu.uy"));
        	II.nuevaInstitucion(new DataInstitucion("Antel", "Empresa estatal de telecomunicaciones", "https://antel.com.uy"));
        	II.nuevaInstitucion(new DataInstitucion("Agencia Nacional de Investigación e Innovación (ANII)", "Fomenta la investigación y la innovación en Uruguay", "https://anii.org.uy"));
        	
        }catch(UsuarioRepetidoException | CategoriaRepetidaException | InstitucionRepetidaException e){
        	e.printStackTrace();
        }
    	try {
			IEV.nuevoEvento(new DataEvento("Conferencia de Tecnología", "CONFTEC", LocalDate.of(2025, 1, 10), "Evento sobre innovación tecnológica"), Arrays.asList("Tecnologia", "Innovacion"));
			IEV.nuevoEvento(new DataEvento("Feria del Libro", "FERLIB", LocalDate.of(2025, 2, 01), "Encuentro anual de literatura"), Arrays.asList("Literatura", "Cultura"));
			IEV.nuevoEvento(new DataEvento("Montevideo Rock", "MONROCK", LocalDate.of(2023, 3, 15), "Festival de rock con artistas nacionales e internacionales"), Arrays.asList("Cultura", "Musica"));
			IEV.nuevoEvento(new DataEvento("Maratón de Montevideo", "MARATON", LocalDate.of(2022, 1, 01), "Competencia deportiva anual en la capital"), Arrays.asList("Deporte","Salud"));
			IEV.nuevoEvento(new DataEvento("Montevideo Comics", "COMICS", LocalDate.of(2024, 4, 10), "Convención de historietas, cine y cultura geek"), Arrays.asList("Cultura", "Entretenimiento"));
			IEV.nuevoEvento(new DataEvento("Expointer Uruguay", "EXPOAGRO", LocalDate.of(2024, 12, 12), "Exposición internacional agropecuaria y ganadera"), Arrays.asList("Agro", "Negocios"));
			IEV.nuevoEvento(new DataEvento("Montevideo Fashion Week", "MFASHION", LocalDate.of(2025, 7, 20), "Pasarela de moda uruguaya e internacional"), Arrays.asList("Cultura", "Moda"));
			
    	}catch(EventoSinCategoriaExcepcion | EventoRepetidoExcepcion e) {
    		e.printStackTrace();
    	}
    	try {
			IEV.nuevaEdicion(new DataEdicion("Montevideo Rock 2025", "MONROCK25", LocalDate.of(2025, 11, 20), LocalDate.of(2025, 11, 22), LocalDate.of(2025, 3, 12), "Montevideo", "Uruguay"),"Montevideo Rock","imm");
			IEV.nuevaEdicion(new DataEdicion("Maratón de Montevideo 2025", "MARATON25", LocalDate.of(2025, 9, 14), LocalDate.of(2025, 9, 14), LocalDate.of(2025, 2, 5), "Montevideo", "Uruguay"),"Maratón de Montevideo","imm");
			IEV.nuevaEdicion(new DataEdicion("Maratón de Montevideo 2024", "MARATON24", LocalDate.of(2024, 9, 14), LocalDate.of(2024, 9, 14), LocalDate.of(2024, 4, 21), "Montevideo", "Uruguay"),"Maratón de Montevideo","imm");
			IEV.nuevaEdicion(new DataEdicion("Maratón de Montevideo 2022", "MARATON22", LocalDate.of(2022, 9, 14), LocalDate.of(2022, 9, 14), LocalDate.of(2022, 5, 21), "Montevideo", "Uruguay"),"Maratón de Montevideo","imm");
			IEV.nuevaEdicion(new DataEdicion("Montevideo Comics 2024", "COMICS24", LocalDate.of(2024, 7, 18), LocalDate.of(2024, 7, 21), LocalDate.of(2024, 6, 20), "Montevideo", "Uruguay"),"Montevideo Comics","miseventos");
			IEV.nuevaEdicion(new DataEdicion("Montevideo Comics 2025", "COMICS25", LocalDate.of(2025, 8, 4), LocalDate.of(2025, 8, 6), LocalDate.of(2025, 7, 4), "Montevideo", "Uruguay"),"Montevideo Comics","miseventos");
			IEV.nuevaEdicion(new DataEdicion("Expointer Uruguay 2025", "EXPOAGRO25", LocalDate.of(2025, 9, 11), LocalDate.of(2025, 9, 17), LocalDate.of(2025, 2, 1), "Durazno", "Uruguay"),"Expointer Uruguay","miseventos");
			IEV.nuevaEdicion(new DataEdicion("Tecnología Punta del Este 2026", "CONFTECH26", LocalDate.of(2026, 4, 6), LocalDate.of(2026, 4, 10), LocalDate.of(2025, 8, 1), "Punta del Este", "Uruguay"),"Conferencia de Tecnología","udelar");
			IEV.nuevaEdicion(new DataEdicion("Mobile World Congress 2025", "MWC", LocalDate.of(2025, 12, 12), LocalDate.of(2025, 12, 15), LocalDate.of(2025, 8, 21), "Barcelona", "España"),"Conferencia de Tecnología","techcorp");
			IEV.nuevaEdicion(new DataEdicion("Web Summit 2026", "WS26", LocalDate.of(2026, 1, 13), LocalDate.of(2026, 2, 1), LocalDate.of(2025, 6, 4), "Lisboa", "Portugal"),"Conferencia de Tecnología","techcorp");
			IEV.nuevaEdicion(new DataEdicion("Montevideo Fashion Week 2026", "MFW26", LocalDate.of(2026, 2, 16), LocalDate.of(2026, 2, 20), LocalDate.of(2025, 10, 2), "Nueva York", "Estados Unidos"), "Montevideo Fashion Week", "mec");
			
			IEV.procesarEdicion("Montevideo Rock", "Montevideo Rock 2025", Estado.Confirmado);
			IEV.procesarEdicion("Maratón de Montevideo", "Maratón de Montevideo 2025", Estado.Confirmado);
			IEV.procesarEdicion("Maratón de Montevideo", "Maratón de Montevideo 2024", Estado.Confirmado);
			IEV.procesarEdicion("Maratón de Montevideo", "Maratón de Montevideo 2022", Estado.Rechazado);
			IEV.procesarEdicion("Montevideo Comics", "Montevideo Comics 2024", Estado.Confirmado);
			IEV.procesarEdicion("Montevideo Comics", "Montevideo Comics 2025", Estado.Confirmado);
			IEV.procesarEdicion("Expointer Uruguay", "Expointer Uruguay 2025", Estado.Ingresada);
			IEV.procesarEdicion("Conferencia de Tecnología", "Tecnología Punta del Este 2026", Estado.Confirmado);
			IEV.procesarEdicion("Conferencia de Tecnología", "Mobile World Congress 2025", Estado.Confirmado);
			IEV.procesarEdicion("Conferencia de Tecnología", "Web Summit 2026", Estado.Confirmado);
			IEV.procesarEdicion("Montevideo Fashion Week", "Montevideo Fashion Week 2026", Estado.Ingresada);
    	}catch(EdicionRepetidaExcepcion e) {
			e.printStackTrace();
		}
		try {
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso general a MontevideoRock (2 días)", 1500, 2000),"Montevideo Rock","Montevideo Rock 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("VIP","Incluye backstage + acceso preferencial", 4000, 200),"Montevideo Rock","Montevideo Rock 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Corredor 42K","Inscripción a la maratón completa", 1200, 499),"Maratón de Montevideo","Maratón de Montevideo 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Corredor 21K","Inscripción a la media maratón", 800, 700),"Maratón de Montevideo","Maratón de Montevideo 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Corredor 10K","Inscripción a la carrera 10K", 500, 1000),"Maratón de Montevideo","Maratón de Montevideo 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Corredor 42K","Inscripción a la maratón completa", 1000, 300),"Maratón de Montevideo","Maratón de Montevideo 2024");
			IEV.nuevoTipoRegistro(new DataTRegistro("Corredor 21K","Inscripción a la media maratón", 500, 500),"Maratón de Montevideo","Maratón de Montevideo 2024");
			IEV.nuevoTipoRegistro(new DataTRegistro("Corredor 42K","Inscripción a la maratón completa", 1100, 450),"Maratón de Montevideo","Maratón de Montevideo 2022");
			IEV.nuevoTipoRegistro(new DataTRegistro("Corredor 21K","Inscripción a la media maratón", 900, 750),"Maratón de Montevideo","Maratón de Montevideo 2022");
			IEV.nuevoTipoRegistro(new DataTRegistro("Corredor 10K","Inscripción a la carrera 10K", 650, 1400),"Maratón de Montevideo","Maratón de Montevideo 2022");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Entrada para los 4 días de Montevideo Comics", 600, 1500),"Montevideo Comics","Montevideo Comics 2024");
			IEV.nuevoTipoRegistro(new DataTRegistro("Cosplayer","Entrada especial con acreditación para concurso cosplay", 300, 50),"Montevideo Comics","Montevideo Comics 2024");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Entrada para los 4 días de Montevideo Comics", 800, 1000),"Montevideo Comics","Montevideo Comics 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Cosplayer","Entrada especial con acreditación para concurso cosplay", 500, 100),"Montevideo Comics","Montevideo Comics 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso a la exposición agropecuaria", 300, 5000),"Expointer Uruguay","Expointer Uruguay 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Empresarial","Acceso para empresas + networking", 2000, 5),"Expointer Uruguay","Expointer Uruguay 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 1800, 300),"Conferencia de Tecnología","Tecnología Punta del Este 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 1500, 500),"Conferencia de Tecnología","Tecnología Punta del Este 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 1000, 50),"Conferencia de Tecnología","Tecnología Punta del Este 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 750, 550),"Conferencia de Tecnología","Mobile World Congress 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 500, 400),"Conferencia de Tecnología","Mobile World Congress 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 250, 400),"Conferencia de Tecnología","Mobile World Congress 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 900, 30),"Conferencia de Tecnología","Web Summit 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 650, 5),"Conferencia de Tecnología","Web Summit 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 300, 1),"Conferencia de Tecnología","Web Summit 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("Full","Acceso a todos los eventos de la semana", 450, 50),"Montevideo Fashion Week","Montevideo Fashion Week 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("Visitante","Acceso parcial a os eventos de la semana", 150, 25),"Montevideo Fashion Week","Montevideo Fashion Week 2026");
			
			II.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 8, 21), 20000 , Nivel.Oro, "TECHUDELAR", 4), "Facultad de Ingeniería", "Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Estudiante");
			II.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 8, 20), 10000, Nivel.Plata, "TECHANII", 1), "Agencia Nacional de Investigación e Innovación (ANII)", "Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General");
			II.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 3, 4), 25000, Nivel.Platino, "CORREANTEL", 10), "Antel", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10k");
			II.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 5, 5), 15000, Nivel.Bronce, "EXPOCAT", 10), "Universidad Católica del Uruguay", "Expointer Uruguay", "Expointer Uruguay 2025", "General");
			
			ICU.nuevoRegistro("sofirod", "Montevideo Rock", "Montevideo Rock 2025", "VIP", LocalDate.of(2025, 5, 14));
			ICU.nuevoRegistro("sofirod", "Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 21K", LocalDate.of(2024, 7, 30));
			ICU.nuevoRegistro("andrearod", "Conferencia de Tecnología", "Web Summit 2026", "Estudiante", LocalDate.of(2025, 8, 21));
			ICU.nuevoRegistro("sofirod", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K", LocalDate.of(2025, 3, 3));
			ICU.nuevoRegistro("vale23", "Conferencia de Tecnología", "Mobile World Congress 2025", "Full", LocalDate.of(2025, 8, 22));
			ICU.nuevoRegistro("AnaG", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", LocalDate.of(2025, 4, 9));
			ICU.nuevoRegistro("JaviL", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 21K", LocalDate.of(2025, 4, 10));
			ICU.nuevoRegistro("MariR", "Montevideo Comics", "Montevideo Comics 2025", "Cosplayer", LocalDate.of(2025, 8, 3));
			ICU.nuevoRegistro("SofiM", "Montevideo Comics", "Montevideo Comics 2024", "General", LocalDate.of(2024, 7, 16));
			ICU.nuevoRegistro("msilva", "Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Estudiante", LocalDate.of(2025, 10, 1));
			ICU.nuevoRegistro("andrearod", "Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", LocalDate.of(2025, 10, 6));
			ICU.nuevoRegistro("MariR", "Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", LocalDate.of(2025, 10, 10));
			
			
    	}catch(TipoDeRegistroRepetidoException | NoHayCupoEdicionTRegistro |  AsistenteYaRegistrado | PatrocinioRepetidoException e){
    		e.printStackTrace();
    	}
	}
}









