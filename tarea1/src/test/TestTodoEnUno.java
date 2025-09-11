package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import excepciones.*;
import logica.Asistente;
import logica.Fabrica;
import logica.ctrlmanejador.*;

import logica.datatypes.*;
import logica.interfaces.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTodoEnUno {
    
    private IUsuario controladorUsuario;
    private IEventos controladorEventos;
    private IInstituciones controladorInstituciones;
    
    @BeforeEach
    void setUp() {
        Fabrica fabrica = Fabrica.getInstance();
        controladorUsuario = fabrica.getIControladorUsuario();
        controladorEventos = fabrica.getIControladorEventos();
        controladorInstituciones = fabrica.getIControladorInstituciones();
        
        ManejadorEvento.resetInstance();
        ManejadorUsuario.resetInstance();
        ManejadorInstituciones.resetInstance();
    }
    
    @Test
    @Order(1)
    @DisplayName("Test completo de carga de datos - Similar al botón de la GUI")
    void testCargaCompletaDatos() {
        // Este test replica la funcionalidad del método cargarDatos() de Principal.java
        
        // 1. Crear categorías
        assertDoesNotThrow(() -> {
            controladorEventos.nuevaCategoria("Tecnologia");
            controladorEventos.nuevaCategoria("Innovacion");
            controladorEventos.nuevaCategoria("Literatura");
            controladorEventos.nuevaCategoria("Cultura");
            controladorEventos.nuevaCategoria("Musica");
            controladorEventos.nuevaCategoria("Deporte");
            controladorEventos.nuevaCategoria("Salud");
            controladorEventos.nuevaCategoria("Entretenimiento");
            controladorEventos.nuevaCategoria("Agro");
            controladorEventos.nuevaCategoria("Negocios");
            controladorEventos.nuevaCategoria("Moda");
            controladorEventos.nuevaCategoria("Investigacion");
        });
        
        // 2. Crear asistentes
        assertDoesNotThrow(() -> {
            controladorUsuario.registrarAsistente("Ana", "atorres", "atorres@gmail.com", "Torres", LocalDate.of(1990, 5, 12));
            controladorUsuario.registrarAsistente("Martin", "msilva", "martin.silva@fing.edu.uy", "Silva", LocalDate.of(1987, 8, 21)); // Pruebo los dos constructores
            controladorUsuario.registrarAsistente(new DataAsistente("Sofia", "sofirod", "srodriguez@outlook.com", "Rodriguez", LocalDate.of(1995, 2, 3)));
            controladorUsuario.registrarAsistente(new DataAsistente("Valentina", "vale23", "valentina.costa@mail.com", "Costa", LocalDate.of(1992, 12, 1)));
            controladorUsuario.registrarAsistente(new DataAsistente("Lucia", "luciag", "lucia.garcia@mail.com", "Garcia", LocalDate.of(1993, 11, 9)));
            controladorUsuario.registrarAsistente(new DataAsistente("Ana", "AnaG", "ana.gomez@hotmail.com", "Gomez", LocalDate.of(1998, 3, 15)));
            controladorUsuario.registrarAsistente(new DataAsistente("Javier", "JaviL", "javier.lopez@outlook.com", "Lopez", LocalDate.of(1995, 7, 22)));
            controladorUsuario.registrarAsistente(new DataAsistente("Maria", "MariR", "maria.rodriguez@gmail.com", "Rodriguez", LocalDate.of(2000, 11, 10)));
            controladorUsuario.registrarAsistente(new DataAsistente("Sofia", "SofiM", "sofia.martinez@yahoo.com", "Martinez", LocalDate.of(1997, 2, 5)));
            controladorUsuario.registrarAsistente(new DataAsistente("Andrea", "andrearod", "andrea.rod@mail.com", "Rodriguez", LocalDate.of(2000, 6, 10)));
        });
        
        // 3. Crear organizadores
        assertDoesNotThrow(() -> {
            controladorUsuario.registrarOrganizador("MisEventos", "miseventos", "contacto@miseventos.com", "Empresa de organizacion de eventos.", "https://miseventos.com/"); 
            controladorUsuario.registrarOrganizador("Corporación Tecnológica", "techcorp", "info@techcorp.com", "Empresa líder en tecnologías de la información", ""); // Pruebo los dos constructores
            controladorUsuario.registrarOrganizador(new DataOrganizador("Intendencia de Montevideo", "imm", "contacto@imm.gub.uy", "Gobierno departamental de Montevideo.", "https://montevideo.gub.uy/"));
            controladorUsuario.registrarOrganizador(new DataOrganizador("Universidad de la República", "udelar", "contacto@udelar.edu.uy", "Universidad publica de Uruguay.", "https://udelar.edu.uy/"));
            controladorUsuario.registrarOrganizador(new DataOrganizador("Ministerio de Educacion y Cultura", "mec", "mec@mec.gub.uy", "Institucion publica promotora de cultura", "https://mec.gub.uy/"));
        });
        
        // 4. Crear instituciones
        assertDoesNotThrow(() -> {
            controladorInstituciones.nuevaInstitucion(new DataInstitucion("Facultad de Ingenieria", "Facultad de Ingeniería de la Universidad de la República", "https://www.fing.edu.uy/"));
            controladorInstituciones.nuevaInstitucion(new DataInstitucion("ORT Uruguay", "Universidad privada enfocada en tecnologia y gestion", "https://ort.edu.uy"));
            controladorInstituciones.nuevaInstitucion(new DataInstitucion("Universidad Catolica del Uruguay", "Institucion de educacion superior privada", "https://ucu.edu.uy/"));
            controladorInstituciones.nuevaInstitucion(new DataInstitucion("Antel", "Empresa estatal de telecomunicaciones", "https://antel.com.uy/"));
            controladorInstituciones.nuevaInstitucion(new DataInstitucion("Agencia Nacional de Investigacion e Innovacion (ANII)", "Fomenta la investigacion y la innovacion en Uruguay", "https://anii.org.uy/"));
        });
        
        // 5. Crear eventos
        assertDoesNotThrow(() -> {
            controladorEventos.nuevoEvento(new DataEvento("Conferencia de Tecnologia", "CONFTEC", LocalDate.of(2025, 1, 10), "Evento sobre innovacion tecnologica"), Arrays.asList("Tecnologia", "Innovacion"));
            controladorEventos.nuevoEvento(new DataEvento("Feria del Libro", "FERLIB", LocalDate.of(2025, 2, 1), "Encuentro anual de literatura"), Arrays.asList("Literatura", "Cultura"));
            controladorEventos.nuevoEvento(new DataEvento("Montevideo Rock", "MONROCK", LocalDate.of(2023, 3, 15), "Festival de rock con artistas nacionales e internacionales"), Arrays.asList("Cultura", "Musica"));
            controladorEventos.nuevoEvento(new DataEvento("Maratón de Montevideo", "MARATON", LocalDate.of(2022, 1, 1), "Competencia deportiva anual en la capital"), Arrays.asList("Deporte", "Salud"));
            controladorEventos.nuevoEvento(new DataEvento("Montevideo Comics", "COMICS", LocalDate.of(2024, 4, 10), "Convencion de historietas, cine y cultura geek"), Arrays.asList("Cultura", "Entretenimiento"));
            controladorEventos.nuevoEvento(new DataEvento("Expointer Uruguay", "EXPOAGRO", LocalDate.of(2024, 12, 12), "Exposicion internacional agropecuaria y ganadera"), Arrays.asList("Agro", "Negocios"));
            controladorEventos.nuevoEvento(new DataEvento("Montevideo Fashion Week", "MFASHION", LocalDate.of(2025, 7, 20), "Pasarela de moda uruguaya e internacional"), Arrays.asList("Cultura", "Moda"));
        });
        
        // 6. Crear ediciones
        assertDoesNotThrow(() -> {
        	controladorEventos.nuevaEdicion(new DataEdicion("Montevideo Rock 2025", "MONROCK25", LocalDate.of(2025, 11, 20), LocalDate.of(2025, 11, 22), LocalDate.of(2025, 3, 12), "Montevideo", "Uruguay"),"Montevideo Rock","imm");
        	controladorEventos.nuevaEdicion(new DataEdicion("Maratón de Montevideo 2025", "MARATON25", LocalDate.of(2025, 9, 14), LocalDate.of(2025, 9, 14), LocalDate.of(2025, 2, 5), "Montevideo", "Uruguay"),"Maratón de Montevideo","imm");
        	controladorEventos.nuevaEdicion(new DataEdicion("Maratón de Montevideo 2024", "MARATON24", LocalDate.of(2024, 9, 14), LocalDate.of(2024, 9, 14), LocalDate.of(2024, 4, 21), "Montevideo", "Uruguay"),"Maratón de Montevideo","imm");
        	controladorEventos.nuevaEdicion(new DataEdicion("Maratón de Montevideo 2022", "MARATON22", LocalDate.of(2022, 9, 14), LocalDate.of(2022, 9, 14), LocalDate.of(2022, 5, 21), "Montevideo", "Uruguay"),"Maratón de Montevideo","imm");
        	controladorEventos.nuevaEdicion(new DataEdicion("Montevideo Comics 2024", "COMICS24", LocalDate.of(2024, 7, 18), LocalDate.of(2024, 7, 21), LocalDate.of(2024, 6, 20), "Montevideo", "Uruguay"),"Montevideo Comics","miseventos");
        	controladorEventos.nuevaEdicion(new DataEdicion("Montevideo Comics 2025", "COMICS25", LocalDate.of(2025, 8, 4), LocalDate.of(2025, 8, 6), LocalDate.of(2025, 7, 4), "Montevideo", "Uruguay"),"Montevideo Comics","miseventos");
        	controladorEventos.nuevaEdicion(new DataEdicion("Expointer Uruguay 2025", "EXPOAGRO25", LocalDate.of(2025, 9, 11), LocalDate.of(2025, 9, 17), LocalDate.of(2025, 2, 1), "Durazno", "Uruguay"),"Expointer Uruguay","miseventos");
        	controladorEventos.nuevaEdicion(new DataEdicion("Tecnología Punta del Este 2026", "CONFTECH26", LocalDate.of(2026, 4, 6), LocalDate.of(2026, 4, 10), LocalDate.of(2025, 8, 1), "Punta del Este", "Uruguay"),"Conferencia de Tecnologia","udelar");
        	controladorEventos.nuevaEdicion(new DataEdicion("Mobile World Congress 2025", "MWC", LocalDate.of(2025, 12, 12), LocalDate.of(2025, 12, 15), LocalDate.of(2025, 8, 21), "Barcelona", "España"),"Conferencia de Tecnologia","techcorp");
        	controladorEventos.nuevaEdicion(new DataEdicion("Web Summit 2026", "WS26", LocalDate.of(2026, 1, 13), LocalDate.of(2026, 2, 1), LocalDate.of(2025, 6, 4), "Lisboa", "Portugal"),"Conferencia de Tecnologia","techcorp");
        });
        
        // 7. Crear Tipos de Registro
        assertDoesNotThrow(() -> {
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("General","Acceso general a MontevideoRock (2 días)", 1500, 2000),"Montevideo Rock","Montevideo Rock 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("VIP","Incluye backstage + acceso preferencial", 4000, 200),"Montevideo Rock","Montevideo Rock 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Corredor 42K","Inscripción a la maratón completa", 1200, 499),"Maratón de Montevideo","Maratón de Montevideo 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Corredor 21K","Inscripción a la media maratón", 800, 700),"Maratón de Montevideo","Maratón de Montevideo 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Corredor 10K","Inscripción a la carrera 10K", 500, 1000),"Maratón de Montevideo","Maratón de Montevideo 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Corredor 42K","Inscripción a la maratón completa", 1000, 300),"Maratón de Montevideo","Maratón de Montevideo 2024");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Corredor 21K","Inscripción a la media maratón", 500, 500),"Maratón de Montevideo","Maratón de Montevideo 2024");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Corredor 42K","Inscripción a la maratón completa", 1100, 450),"Maratón de Montevideo","Maratón de Montevideo 2022");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Corredor 21K","Inscripción a la media maratón", 900, 750),"Maratón de Montevideo","Maratón de Montevideo 2022");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Corredor 10K","Inscripción a la carrera 10K", 650, 1400),"Maratón de Montevideo","Maratón de Montevideo 2022");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("General","Entrada para los 4 días de Montevideo Comics", 600, 1500),"Montevideo Comics","Montevideo Comics 2024");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Cosplayer","Entrada especial con acreditación para concurso cosplay", 300, 50),"Montevideo Comics","Montevideo Comics 2024");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("General","Entrada para los 4 días de Montevideo Comics", 800, 1000),"Montevideo Comics","Montevideo Comics 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Cosplayer","Entrada especial con acreditación para concurso cosplay", 500, 100),"Montevideo Comics","Montevideo Comics 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("General","Acceso a la exposición agropecuaria", 300, 5000),"Expointer Uruguay","Expointer Uruguay 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Empresarial","Acceso para empresas + networking", 2000, 5),"Expointer Uruguay","Expointer Uruguay 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 1800, 300),"Conferencia de Tecnologia","Tecnología Punta del Este 2026");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 1500, 500),"Conferencia de Tecnologia","Tecnología Punta del Este 2026");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 1000, 50),"Conferencia de Tecnologia","Tecnología Punta del Este 2026");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 750, 550),"Conferencia de Tecnologia","Mobile World Congress 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 500, 400),"Conferencia de Tecnologia","Mobile World Congress 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 250, 400),"Conferencia de Tecnologia","Mobile World Congress 2025");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 900, 30),"Conferencia de Tecnologia","Web Summit 2026");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("General","Acceso general", 650, 5),"Conferencia de Tecnologia","Web Summit 2026");
        	controladorEventos.nuevoTipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 300, 1),"Conferencia de Tecnologia","Web Summit 2026");
        });
        
        // 8. Crear patrocinios
        assertDoesNotThrow(() -> {
        	controladorInstituciones.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 8, 21), 20000 , Nivel.Oro, "TECHFING", 4), "Facultad de Ingenieria", "Conferencia de Tecnologia", "Tecnología Punta del Este 2026", "Estudiante");
        	controladorInstituciones.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 8, 20), 10000, Nivel.Plata, "TECHANII", 1), "Agencia Nacional de Investigacion e Innovacion (ANII)", "Conferencia de Tecnologia", "Tecnología Punta del Este 2026", "General");
        	controladorInstituciones.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 3, 4), 25000, Nivel.Platino, "CORREANTEL", 10), "Antel", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10k");
        	controladorInstituciones.nuevoPatrocinio(new DataPatrocinio(LocalDate.of(2025, 5, 5), 15000, Nivel.Bronce, "EXPOCAT", 10), "Universidad Catolica del Uruguay", "Expointer Uruguay", "Expointer Uruguay 2025", "General");
        });
        
        // 9. Crear Registros
        assertDoesNotThrow(() -> {
        	controladorUsuario.nuevoRegistro("sofirod", "Montevideo Rock", "Montevideo Rock 2025", "VIP", LocalDate.of(2025, 5, 14));
        	controladorUsuario.nuevoRegistro("sofirod", "Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 21K", LocalDate.of(2024, 7, 30));
        	controladorUsuario.nuevoRegistro("andrearod", "Conferencia de Tecnologia", "Web Summit 2026", "Estudiante", LocalDate.of(2025, 8, 21));
        	controladorUsuario.nuevoRegistro("sofirod", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K", LocalDate.of(2025, 3, 3));
        	controladorUsuario.nuevoRegistro("vale23", "Conferencia de Tecnologia", "Mobile World Congress 2025", "Full", LocalDate.of(2025, 8, 22));
        	controladorUsuario.nuevoRegistro("AnaG", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", LocalDate.of(2025, 4, 9));
        	controladorUsuario.nuevoRegistro("JaviL", "Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 21K", LocalDate.of(2025, 4, 10));
        	controladorUsuario.nuevoRegistro("MariR", "Montevideo Comics", "Montevideo Comics 2025", "Cosplayer", LocalDate.of(2025, 8, 3));
        	controladorUsuario.nuevoRegistro("SofiM", "Montevideo Comics", "Montevideo Comics 2024", "General", LocalDate.of(2024, 7, 16));
			
        });
        
        // Verificaciones después de la carga completa
        
        // Verificar categorías - usando listarCategorias() que devuelve List<String>
        List<String> categorias = controladorEventos.listarCategorias();
        assertEquals(12, categorias.size());
        assertTrue(categorias.contains("Tecnologia"));
        assertTrue(categorias.contains("Moda"));
        
        // Verificar usuarios
        assertTrue(existeUsuarioPorNickname("atorres"));
        assertTrue(existeUsuarioPorNickname("miseventos"));
        assertTrue(existeUsuarioPorNickname("techcorp"));
        
        // Verificar eventos - usando listarEventos() que devuelve List<String>
        List<String> eventos = controladorEventos.listarEventos();
        assertEquals(7, eventos.size());
        assertTrue(eventos.contains("Conferencia de Tecnologia"));
        assertTrue(eventos.contains("Montevideo Fashion Week"));
        
        // Verificar eventos - usando listarInfoEvento()
        try {
        	DataEventoCompleto[] evs = controladorEventos.listarInfoEvento();
        	assertEquals(7, evs.length);
        } catch (EventoNoExisteExcepcion e) {
            e.printStackTrace();
        }
        
        //Verificar ediciones
        List<String> eds = controladorEventos.listarEdiciones("Maratón de Montevideo");
        assertEquals(3, eds.size());
        assertTrue(eds.contains("Maratón de Montevideo 2025"));
        assertTrue(eds.contains("Maratón de Montevideo 2022"));
        
        //Verificar Tipos de Registro
        List<String> regs = controladorEventos.listarTRegistros("Maratón de Montevideo", "Maratón de Montevideo 2025");
        assertEquals(3, regs.size());
        assertTrue(regs.contains("Corredor 42K"));
        assertTrue(regs.contains("Corredor 21K"));
        assertTrue(regs.contains("Corredor 10K"));
        for (String s : regs) {
        	assertTrue(controladorEventos.getDataTRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K").getCupo() >= 0);
        }
        
        // Verificar instituciones - usando listarInstituciones()
        DataInstitucion[] instituciones = controladorInstituciones.listarInstituciones();
        assertEquals(5, instituciones.length);
        assertTrue(existeInstitucionPorNombre("Facultad de Ingenieria", instituciones));
        assertTrue(existeInstitucionPorNombre("Agencia Nacional de Investigacion e Innovacion (ANII)", instituciones));
    }
    
    // Métodos auxiliares para verificar existencia
    private boolean existeUsuarioPorNickname(String nickname) {
        try {
            DataUsuario[] usuarios = controladorUsuario.getUsuarios();
            for (DataUsuario usuario : usuarios) {
                if (usuario.getNickname().equals(nickname)) {
                    return true;
                }
            }
            return false;
        } catch (UsuarioNoExisteException e) {
            return false;
        }
    }
    
    private boolean existeInstitucionPorNombre(String nombre, DataInstitucion[] instituciones) {
        for (DataInstitucion institucion : instituciones) {
            if (institucion.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }
    
    @Test
    @Order(2)
    @DisplayName("Verificación de integridad de datos después de carga completa")
    void testIntegridadDatosCargados() {
        // Ejecutar primero la carga completa
        testCargaCompletaDatos();
        
        // Verificar que los datos están relacionados correctamente
        
        // Verificar que los eventos tienen las categorías correctas
        assertDoesNotThrow(() -> {
            // Necesitamos obtener el evento de alguna manera
            List<String> eventos = controladorEventos.listarEventos();
            assertTrue(eventos.contains("Conferencia de Tecnologia"));
        });
        
        // Verificar que los usuarios tienen los datos correctos
        assertDoesNotThrow(() -> {
            DataAsistente datosUsuario = controladorUsuario.getAsistente("atorres@gmail.com");
            assertNotNull(datosUsuario);
            assertEquals("Ana", datosUsuario.getNombre());
            assertEquals("atorres@gmail.com", datosUsuario.getEmail());
        });
        
        // Verificar que las instituciones están creadas correctamente
        assertDoesNotThrow(() -> {
            DataInstitucion[] instituciones = controladorInstituciones.listarInstituciones();
            boolean encontrada = false;
            for (DataInstitucion institucion : instituciones) {
                if (institucion.getNombre().equals("Facultad de Ingenieria") && 
                    institucion.getUrl().contains("fing.edu.uy")) {
                    encontrada = true;
                    break;
                }
            }
            assertTrue(encontrada);
        });
    }
    
    @Test
    @Order(3)
    @DisplayName("Test de consistencia - No permitir duplicados después de carga")
    void testConsistenciaDespuesCarga() {
        // Ejecutar carga completa
        testCargaCompletaDatos();
        
        // Intentar crear elementos duplicados debe fallar
        
        // Categoría duplicada
        assertThrows(CategoriaRepetidaException.class, () -> {
            controladorEventos.nuevaCategoria("Tecnologia");
        });
        
        // Usuario duplicado (mismo nickname)
        assertThrows(UsuarioRepetidoException.class, () -> {
            controladorUsuario.registrarAsistente(new DataAsistente("Pedro", "atorres", "pedro@gmail.com", "Perez", LocalDate.of(1990, 1, 1)));
        });
        
        // Evento duplicado
        assertThrows(EventoRepetidoExcepcion.class, () -> {
            controladorEventos.nuevoEvento(new DataEvento("Conferencia de Tecnologia", "CONF2", LocalDate.of(2025, 1, 1), "Otro evento"), Arrays.asList("Tecnologia"));
        });
        
        // Institución duplicada
        assertThrows(InstitucionRepetidaException.class, () -> {
            controladorInstituciones.nuevaInstitucion(new DataInstitucion("Facultad de Ingenieria", "Otra descripción", "http://otra.url"));
        });
    }
    
    @Test
    @Order(4)
    @DisplayName("Test de rendimiento - Carga de datos debe ser rápida")
    void testRendimientoCarga() {
        long startTime = System.currentTimeMillis();
        
        // Ejecutar carga completa
        testCargaCompletaDatos();
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        // La carga completa no debería tomar más de 5 segundos
        assertTrue(duration < 5000, "La carga de datos tomó demasiado tiempo: " + duration + "ms");
        
        System.out.println("Carga completa de datos completada en: " + duration + "ms");
    }
    
    @Test
    @Order(5)
    @DisplayName("Test de robustez - Sistema funcional después de múltiples operaciones")
    void testRobustezSistema() {
        // Carga inicial
        testCargaCompletaDatos();
        
        // Realizar múltiples operaciones adicionales
        assertDoesNotThrow(() -> {
            // Crear categoría adicional
            controladorEventos.nuevaCategoria("TestCategoria");
            
            // Crear usuario adicional
            controladorUsuario.registrarAsistente(new DataAsistente("Test", "testuser", "test@test.com", "Testero", LocalDate.of(1990, 1, 1)));
            
            // Crear institución adicional
            controladorInstituciones.nuevaInstitucion(new DataInstitucion("Test Institution", "Test description", "http://test.com"));
            
            // Crear evento adicional
            controladorEventos.nuevoEvento(new DataEvento("Test Event", "TEST", LocalDate.now(), "Test description"), Arrays.asList("TestCategoria"));
        });
        
        // Verificar que el sistema sigue funcionando correctamente
        List<String> categorias = controladorEventos.listarCategorias();
        assertTrue(categorias.contains("TestCategoria"));
        
        assertTrue(existeUsuarioPorNickname("testuser"));
        
        DataInstitucion[] instituciones = controladorInstituciones.listarInstituciones();
        assertTrue(existeInstitucionPorNombre("Test Institution", instituciones));
        
        List<String> eventos = controladorEventos.listarEventos();
        assertTrue(eventos.contains("Test Event"));
        
        // Verificar que los datos originales siguen intactos
        assertTrue(categorias.contains("Tecnologia"));
        assertTrue(existeUsuarioPorNickname("atorres"));
        assertTrue(eventos.contains("Conferencia de Tecnologia"));
    }
    
    @Test
    @Order(6)
    @DisplayName("Listar asistentes y organizadores")
    void testListadosUsuarios() {
        testCargaCompletaDatos();

        List<String> asistentes = controladorUsuario.listarAsistentes();
        List<String> organizadores = controladorUsuario.listarOrganizadores();

        assertTrue(asistentes.contains("atorres"));
        assertTrue(asistentes.contains("msilva"));
        assertTrue(organizadores.contains("miseventos"));
        assertTrue(organizadores.contains("techcorp"));
    }
    
    @Test
    @Order(7)
    @DisplayName("Modificar Asistente")
    void testModificarAsistente_exito() throws Exception {    	
    	// Arrange
        controladorUsuario.registrarAsistente("Ana ", "atorres", "atorres@gmail.com", "Torres", LocalDate.of(1990, 5, 12));

        // Act
        controladorUsuario.modificarAsistente("atorres@gmail.com", "NuevoNombre", "NuevoApellido");

        // Assert
        DataAsistente d = controladorUsuario.getAsistente("atorres@gmail.com");
        assertTrue(d instanceof DataAsistente);
        assertEquals("NuevoNombre", d.getNombre());
        assertEquals("NuevoApellido", d.getApellido());
    }

    @Test
    @Order(8)
    @DisplayName("Modificar Asistente - Usuario No Existe")
    void testModificarAsistente_usuarioNoExiste() {
        // Act & Assert
        assertThrows(UsuarioNoExisteException.class, () -> {
            controladorUsuario.modificarAsistente("noexiste@test.com", "Nombre", "Apellido");
        });
    }

    @Test
    @Order(9)
    @DisplayName("Modificar Organizador")
    void testModificarOrganizador_exito() throws Exception {
    	// Arrange
        controladorUsuario.registrarOrganizador("MisEventos", "miseventos", "contacto@miseventos.com", "Empresa de organizacion de eventos.", "https://miseventos.com/");

        // Act
        controladorUsuario.modificarOrganizador("contacto@miseventos.com", "NuevoNombre", "NuevaDescripcion", "NuevaURL");

        // Assert
        DataOrganizador d = controladorUsuario.getOrganizador("contacto@miseventos.com");
        assertTrue(d instanceof DataOrganizador);
        assertEquals("NuevoNombre", d.getNombre());
        assertEquals("NuevaDescripcion", d.getDescripcion());
    }

    @Test
    @Order(9)
    @DisplayName("Modificar Organizador - Organizador No Existe")
    void testModificarOrganizador_usuarioNoExiste() {
        // Act & Assert
        assertThrows(UsuarioNoExisteException.class, () -> {
            controladorUsuario.modificarOrganizador("noexiste@test.com", "Nombre", "Desc", "url");
        });
    }
    
    @Test
    @Order(10)
    @DisplayName("Verificar Registros")
    void testRegistro() {
        // Ejecutar carga completa
        testCargaCompletaDatos();
        
        List<ParEdicionRegistro> regs = controladorUsuario.getRegistrosAsistente("sofirod");
        assertTrue(regs.size() > 0);
        try {
        	DataDetalleRegistro r = controladorUsuario.getDetallesRegistro("sofirod", regs.getFirst());        	
        	assertTrue(r.getNombreEdicion().equals("Montevideo Rock 2025"));
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    @Test
    @Order(10)
    @DisplayName("Verificar Edicion")
    void testEdicionEvento() {
        // Ejecutar carga completa
        testCargaCompletaDatos();
        
        DataEdicion[] eds = controladorEventos.getEdicionesEventoOrganizador("imm");
        assertTrue(eds.length == 4);
    }
}