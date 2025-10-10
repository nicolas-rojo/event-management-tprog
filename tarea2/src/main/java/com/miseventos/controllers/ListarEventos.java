package com.miseventos.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

@WebServlet("/home")
public class ListarEventos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEventos IEV;
	private IUsuario ICU;
	private IInstituciones II;
    
    @Override
    public void init() throws ServletException {  
    	IEV = Fabrica.getInstance().getIControladorEventos();
    	ICU = Fabrica.getInstance().getIControladorUsuario();
    	II = Fabrica.getInstance().getIControladorInstituciones();
    	cargarDatos(ICU, IEV, II);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Obtener el parámetro de categoría si existe
			String categoriaFiltro = request.getParameter("categoria");
			
			// Cargar todas las categorías para el filtro
			List<String> categorias = IEV.listarCategorias();
			request.setAttribute("categorias", categorias);
			request.setAttribute("categoriaSeleccionada", categoriaFiltro);
			
			// Obtener eventos filtrados o todos
			List<DataEventoCompleto> lista;
			if (categoriaFiltro != null && !categoriaFiltro.trim().isEmpty()) {
				// Filtrar eventos por categoría
				lista = IEV.getEventosConCategoria(categoriaFiltro);
			} else {
				// Mostrar todos los eventos
				DataEventoCompleto[] aux = IEV.listarInfoEvento();
				lista = new ArrayList<>(Arrays.asList(aux));
			}
			
			request.setAttribute("eventos", lista);
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (EventoNoExisteExcepcion e) {
			request.setAttribute("error", "Error al cargar los datos");
            request.getRequestDispatcher("/WEB-INF/errorPages/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void cargarDatos(IUsuario ICU, IEventos IEV, IInstituciones II) {
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
        	
        	ICU.registrarAsistente(new DataAsistente("Ana ", "atorres", "atorres@gmail.com", "123", "Torres", LocalDate.of(1990, 5, 12)));
        	ICU.registrarAsistente(new DataAsistente("Martin", "msilva", "martin.silva@fing.edu.uy", "123", "Silva",  LocalDate.of(1987, 8, 21)));
        	ICU.registrarAsistente(new DataAsistente("Sofia", "sofirod", "srodriguez@outlook.com", "123", "Rodriguez",  LocalDate.of(1995, 2, 3)));
        	ICU.registrarAsistente(new DataAsistente("Valentina", "vale23", "valentina.costa@mail.com", "123", "Costa",  LocalDate.of(1992, 12, 1)));
        	ICU.registrarAsistente(new DataAsistente("Lucia", "luciag", "lucia.garcia@mail.com", "123", "Garcia",  LocalDate.of(1993, 11, 9)));
        	ICU.registrarAsistente(new DataAsistente("Ana", "AnaG", "ana.gomez@hotmail.com", "123", "Gomez",  LocalDate.of(2000, 6, 10)));
        	ICU.registrarAsistente(new DataAsistente("Javier", "JaviL", "javier.lopez@outlook.com", "123", "Lopez",  LocalDate.of(1998, 3, 15)));
        	ICU.registrarAsistente(new DataAsistente("Mariıa", "MariR", "maria.rodriguez@gmail.com", "123", "Rodriguez",  LocalDate.of(1995, 7, 22)));
        	ICU.registrarAsistente(new DataAsistente("Sofia", "SofiM", "sofia.martinez@yahoo.com", "123", "Martinez",  LocalDate.of(2000, 11, 10)));
        	ICU.registrarAsistente(new DataAsistente("Andrea", "andrearod", "andrea.rod@mail.com", "123", "Rodriguez", LocalDate.of(1997, 2, 5)));
        	
        	
        	ICU.registrarOrganizador(new DataOrganizador("MisEventos", "miseventos", "contacto@miseventos.com", "123", "Empresa de organizacion de eventos.", "https://miseventos.com/"));
        	ICU.registrarOrganizador(new DataOrganizador("Corporaci´on Tecnol´ogica", "techcorp", "info@techcorp.com", "123", "Empresa l´ıder en tecnolog´ıas de la informaci´on", ""));
        	ICU.registrarOrganizador(new DataOrganizador("Intendencia de Montevideo", "imm", "contacto@imm.gub.uy", "123", "Gobierno departamental de Montevideo.", "https://montevideo.gub.uy/"));
        	ICU.registrarOrganizador(new DataOrganizador("Universidad de la Rep´ublica", "udelar", "contacto@udelar.edu.uy", "123", "Universidad publica de Uruguay.", "https://udelar.edu.uy/"));
        	ICU.registrarOrganizador(new DataOrganizador("Ministerio de Educacion y Cultura", "mec", "mec@mec.gub.uy", "123", "Institucion publica promotora de cultura", "https://mec.gub.uy/"));
        	
        	II.nuevaInstitucion(new DataInstitucion("Facultad de Ingenieria", "Facultad de Ingenier´ıa de la Universidad de la Rep´ublica", "https://www.fing.edu.uy/"));
        	II.nuevaInstitucion(new DataInstitucion("ORT Uruguay", "Universidad privada enfocada en tecnologia y gestion", "https://ort.edu.uy"));
        	II.nuevaInstitucion(new DataInstitucion("Universidad Catolica del Uruguay", "Institucion de educacion superior privada", "https://ucu.edu.uy/"));
        	II.nuevaInstitucion(new DataInstitucion("Antel", "Empresa estatal de telecomunicaciones", "https://antel.com.uy/"));
        	II.nuevaInstitucion(new DataInstitucion("Agencia Nacional de Investigacion e Innovacion (ANII)", "Fomenta la investigacion y la innovacion en Uruguay", "https://anii.org.uy/"));
        	
        }catch(UsuarioRepetidoException | CategoriaRepetidaException | InstitucionRepetidaException e){
        	e.printStackTrace();
        }
    	try {
			IEV.nuevoEvento(new DataEvento("Conferencia de Tecnologia", "CONFTEC", LocalDate.of(2025, 01, 10), "Evento sobre innovacion tecnol´ogica"), Arrays.asList("Tecnologia", "Innovacion"));
			IEV.nuevoEvento(new DataEvento("Feria del Libro", "FERLIB", LocalDate.of(2025, 02, 01), "Encuentro anual de literatura"), Arrays.asList("Literatura", "Cultura"));
			IEV.nuevoEvento(new DataEvento("Montevideo Rock", "MONROCK", LocalDate.of(2023, 03, 15), "Festival de rock con artistas nacionales e internacionales"), Arrays.asList("Cultura", "Musica"));
			IEV.nuevoEvento(new DataEvento("Maratón de Montevideo", "MARATON", LocalDate.of(2022, 01, 01), "Competencia deportiva anual en la capital"), Arrays.asList("Deporte","Salud"));
			IEV.nuevoEvento(new DataEvento("Montevideo Comics", "COMICS", LocalDate.of(2024, 04, 10), "Convencion de historietas, cine y cultura geek"), Arrays.asList("Cultura", "Entretenimiento"));
			IEV.nuevoEvento(new DataEvento("Expointer Uruguay", "EXPOAGRO", LocalDate.of(2024, 12, 12), "Exposicion internacional agropecuaria y ganadera"), Arrays.asList("Agro", "Negocios"));
			IEV.nuevoEvento(new DataEvento("Montevideo Fashion Week", "MFASHION", LocalDate.of(2025, 07, 20), "Pasarela de moda uruguaya e internacional"), Arrays.asList("Cultura", "Moda"));
			
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
			IEV.nuevaEdicion(new DataEdicion("Tecnología Punta del Este 2026", "CONFTECH26", LocalDate.of(2026, 4, 6), LocalDate.of(2026, 4, 10), LocalDate.of(2025, 8, 1), "Punta del Este", "Uruguay"),"Conferencia de Tecnologia","udelar");
			IEV.nuevaEdicion(new DataEdicion("Mobile World Congress 2025", "MWC", LocalDate.of(2025, 12, 12), LocalDate.of(2025, 12, 15), LocalDate.of(2025, 8, 21), "Barcelona", "España"),"Conferencia de Tecnologia","techcorp");
			IEV.nuevaEdicion(new DataEdicion("Web Summit 2026", "WS26", LocalDate.of(2026, 1, 13), LocalDate.of(2026, 2, 1), LocalDate.of(2025, 6, 4), "Lisboa", "Portugal"),"Conferencia de Tecnologia","techcorp");
    	}catch(EdicionRepetidaExcepcion e) {
			e.printStackTrace();
		}
		try {
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso general a MontevideoRock (2 días)", 1500, 2000),"Montevideo Rock","Montevideo Rock 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("VIP","Incluye backstage + acceso preferencial", 4000, 2),"Montevideo Rock","Montevideo Rock 2025");
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
			IEV.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 1800, 300),"Conferencia de Tecnologia","Tecnología Punta del Este 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 1500, 500),"Conferencia de Tecnologia","Tecnología Punta del Este 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 1000, 50),"Conferencia de Tecnologia","Tecnología Punta del Este 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 750, 550),"Conferencia de Tecnologia","Mobile World Congress 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 500, 400),"Conferencia de Tecnologia","Mobile World Congress 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 250, 400),"Conferencia de Tecnologia","Mobile World Congress 2025");
			IEV.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 900, 30),"Conferencia de Tecnologia","Web Summit 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 650, 5),"Conferencia de Tecnologia","Web Summit 2026");
			IEV.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 300, 1),"Conferencia de Tecnologia","Web Summit 2026");
			
			II.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 8, 21), 20000 , Nivel.Oro, "TECHFING", 4), "Facultad de Ingenieria", "Conferencia de Tecnologia", "Tecnología Punta del Este 2026", "Estudiante");
			II.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 8, 20), 10000, Nivel.Plata, "TECHANII", 1), "Agencia Nacional de Investigacion e Innovacion (ANII)", "Conferencia de Tecnologia", "Tecnología Punta del Este 2026", "General");
			II.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 3, 4), 25000, Nivel.Platino, "CORREANTEL", 10), "Antel", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10k");
			II.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 5, 5), 15000, Nivel.Bronce, "EXPOCAT", 10), "Universidad Catolica del Uruguay", "Expointer Uruguay", "Expointer Uruguay 2025", "General");
			
			ICU.nuevoRegistro("sofirod", "Montevideo Rock", "Montevideo Rock 2025", "VIP", LocalDate.of(2025, 5, 14));
			ICU.nuevoRegistro("sofirod", "Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 21K", LocalDate.of(2024, 7, 30));
			ICU.nuevoRegistro("andrearod", "Conferencia de Tecnologia", "Web Summit 2026", "Estudiante", LocalDate.of(2025, 8, 21));
			ICU.nuevoRegistro("sofirod", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K", LocalDate.of(2025, 3, 3));
			ICU.nuevoRegistro("vale23", "Conferencia de Tecnologia", "Mobile World Congress 2025", "Full", LocalDate.of(2025, 8, 22));
			ICU.nuevoRegistro("AnaG", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", LocalDate.of(2025, 4, 9));
			ICU.nuevoRegistro("JaviL", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 21K", LocalDate.of(2025, 4, 10));
			ICU.nuevoRegistro("MariR", "Montevideo Comics", "Montevideo Comics 2025", "Cosplayer", LocalDate.of(2025, 8, 3));
			ICU.nuevoRegistro("SofiM", "Montevideo Comics", "Montevideo Comics 2024", "General", LocalDate.of(2024, 7, 16));
			
			
    	}catch(TipoDeRegistroRepetidoException | NoHayCupoEdicionTRegistro |  AsistenteYaRegistrado | PatrocinioRepetidoException e){
    		e.printStackTrace();
    	}		
    }
}