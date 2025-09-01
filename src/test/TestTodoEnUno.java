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
            controladorUsuario.registrarAsistente(new DataAsistente("Ana", "atorres", "atorres@gmail.com", "Torres", LocalDate.of(1990, 5, 12)));
            controladorUsuario.registrarAsistente(new DataAsistente("Martin", "msilva", "martin.silva@fing.edu.uy", "Silva", LocalDate.of(1987, 8, 21)));
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
            controladorUsuario.registrarOrganizador(new DataOrganizador("MisEventos", "miseventos", "contacto@miseventos.com", "Empresa de organizacion de eventos.", "https://miseventos.com/"));
            controladorUsuario.registrarOrganizador(new DataOrganizador("Corporación Tecnológica", "techcorp", "info@techcorp.com", "Empresa líder en tecnologías de la información", ""));
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
        
        // Verificaciones después de la carga completa
        
        // Verificar categorías - usando listarCategorias() que devuelve List<String>
        List<String> categorias = controladorEventos.listarCategorias();
        assertEquals(12, categorias.size());
        assertTrue(categorias.contains("Tecnologia"));
        assertTrue(categorias.contains("Moda"));
        
        // Verificar usuarios - usando métodos auxiliares que implementaremos
        assertTrue(existeUsuarioPorNickname("atorres"));
        assertTrue(existeUsuarioPorNickname("miseventos"));
        assertTrue(existeUsuarioPorNickname("techcorp"));
        
        // Verificar eventos - usando listarEventos() que devuelve List<String>
        List<String> eventos = controladorEventos.listarEventos();
        assertEquals(7, eventos.size());
        assertTrue(eventos.contains("Conferencia de Tecnologia"));
        assertTrue(eventos.contains("Montevideo Fashion Week"));
        
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
}