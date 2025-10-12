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
        
    	assertDoesNotThrow(() -> {
    		controladorUsuario.cargarDatos();
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
        assertTrue(eventos.contains("Conferencia de Tecnología"));
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
        assertTrue(existeInstitucionPorNombre("Facultad de Ingeniería", instituciones));
        assertTrue(existeInstitucionPorNombre("Agencia Nacional de Investigación e Innovación (ANII)", instituciones));
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
            assertTrue(eventos.contains("Conferencia de Tecnología"));
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
                if (institucion.getNombre().equals("Facultad de Ingeniería") && 
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
            controladorUsuario.registrarAsistente(new DataAsistente("Pedro", "atorres", "pedro@gmail.com", "123", "Perez", LocalDate.of(1990, 1, 1)));
        });
        
        // Evento duplicado
        assertThrows(EventoRepetidoExcepcion.class, () -> {
            controladorEventos.nuevoEvento(new DataEvento("Conferencia de Tecnología", "CONF2", LocalDate.of(2025, 1, 1), "Otro evento"), Arrays.asList("Tecnología"));
        });
        
        // Institución duplicada
        assertThrows(InstitucionRepetidaException.class, () -> {
            controladorInstituciones.nuevaInstitucion(new DataInstitucion("Facultad de Ingeniería", "Otra descripción", "http://otra.url"));
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
            controladorUsuario.registrarAsistente(new DataAsistente("Test", "testuser", "test@test.com", "123", "Testero", LocalDate.of(1990, 1, 1)));
            
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
        assertTrue(eventos.contains("Conferencia de Tecnología"));
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
        controladorUsuario.registrarAsistente("Ana ", "atorres", "atorres@gmail.com", "123", "Torres", LocalDate.of(1990, 5, 12));

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
        controladorUsuario.registrarOrganizador("MisEventos", "miseventos", "contacto@miseventos.com", "123", "Empresa de organizacion de eventos.", "https://miseventos.com/");

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