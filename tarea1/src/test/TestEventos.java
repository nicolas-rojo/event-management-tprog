package test;

import excepciones.CategoriaRepetidaException;
import excepciones.EdicionRepetidaExcepcion;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.TipoDeRegistroRepetidoException;
import logica.Fabrica;
import logica.ctrlmanejador.*;
import logica.datatypes.*;
import logica.interfaces.*;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.Arrays;

public class TestEventos extends TestCase {
    
    private IEventos controladorEventos;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ManejadorEvento.resetInstance();
        ManejadorUsuario.resetInstance();
        
        Fabrica fabrica = Fabrica.getInstance();
        controladorEventos = fabrica.getIControladorEventos();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        ManejadorEvento.resetInstance();
    }
    
    private boolean existeEvento(String nombre) {
        try {
            return (controladorEventos.existeEvento(nombre));
        } catch (Exception e) {
            return false;
        }
    }
    
    public void testNuevaCategoriaExitosa() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String categoria = "Tecnologia_" + uniqueId;
        
        try {
            controladorEventos.nuevaCategoria(categoria);
            assertTrue("Categoría debería existir", true);
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testCategoriaRepetida() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String categoria = "Tecnologia_" + uniqueId;
        
        try {
            controladorEventos.nuevaCategoria(categoria);
            controladorEventos.nuevaCategoria(categoria);
            fail("Debería haber lanzado CategoriaRepetidaException");
        } catch (CategoriaRepetidaException e) {
            // Test exitoso
            assertTrue("Se esperaba CategoriaRepetidaException", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    public void testNuevoEventoExitoso() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            // Primero crear categorías necesarias
            controladorEventos.nuevaCategoria("Tecnologia_" + uniqueId);
            controladorEventos.nuevaCategoria("Innovacion_" + uniqueId);
            
            DataEvento eventoData = new DataEvento(
                "Conferencia de Tecnologia_" + uniqueId,
                "CONFTEC_" + uniqueId,
                LocalDate.of(2025, 1, 10),
                "Evento sobre innovacion tecnológica"
            );
            
            controladorEventos.nuevoEvento(eventoData, Arrays.asList("Tecnologia_" + uniqueId, "Innovacion_" + uniqueId));
            assertTrue("Evento debería existir", existeEvento("Conferencia de Tecnologia_" + uniqueId));
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testEventoRepetido() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            // Primero crear categorías necesarias
            controladorEventos.nuevaCategoria("Tecnologia_" + uniqueId);
            
            DataEvento evento1 = new DataEvento(
                "Conferencia de Tecnologia_" + uniqueId,
                "CONFTEC_" + uniqueId,
                LocalDate.of(2025, 1, 10),
                "Evento sobre innovacion tecnológica"
            );
            
            DataEvento evento2 = new DataEvento(
                "Conferencia de Tecnologia_" + uniqueId, // Mismo nombre
                "CONFTEC2_" + uniqueId,
                LocalDate.of(2025, 1, 10),
                "Otro evento"
            );
            
            controladorEventos.nuevoEvento(evento1, Arrays.asList("Tecnologia_" + uniqueId));
            controladorEventos.nuevoEvento(evento2, Arrays.asList("Tecnologia_" + uniqueId));
            fail("Debería haber lanzado EventoRepetidoExcepcion");
            
        } catch (EventoRepetidoExcepcion e) {
            // Test exitoso
            assertTrue("Se esperaba EventoRepetidoExcepcion", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    public void testEventoSinCategoria() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            DataEvento eventoData = new DataEvento(
                "Conferencia de Tecnologia_" + uniqueId,
                "CONFTEC_" + uniqueId,
                LocalDate.of(2025, 1, 10),
                "Evento sobre innovacion tecnológica"
            );
            
            controladorEventos.nuevoEvento(eventoData, Arrays.asList()); // Lista vacía
            fail("Debería haber lanzado EventoSinCategoriaExcepcion");
            
        } catch (EventoSinCategoriaExcepcion e) {
            // Test exitoso
            assertTrue("Se esperaba EventoSinCategoriaExcepcion", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    public void testNuevaEdicionExitosa() {
        String uniqueId = String.valueOf(System.currentTimeMillis());

        try {
            // PRIMERO: Crear un organizador (necesitas acceso al controlador de usuarios)
            Fabrica fabrica = Fabrica.getInstance();
            IUsuario controladorUsuario = fabrica.getIControladorUsuario();
            
            // Crear organizador de prueba
            DataOrganizador organizadorData = new DataOrganizador(
                "Test Organizador_" + uniqueId,
                "testorg_" + uniqueId,
                "testorg_" + uniqueId + "@test.com",
                "pass" + uniqueId,
                "Organizador de prueba",
                "https://test.com"
            );
            controladorUsuario.registrarOrganizador(organizadorData);

            // SEGUNDO: Crear categorías y evento
            controladorEventos.nuevaCategoria("Tecnologia_" + uniqueId);

            DataEvento eventoData = new DataEvento(
                "Conferencia de Tecnologia_" + uniqueId,
                "CONFTEC_" + uniqueId,
                LocalDate.of(2025, 1, 10),
                "Evento sobre innovacion tecnológica"
            );
            controladorEventos.nuevoEvento(eventoData, Arrays.asList("Tecnologia_" + uniqueId));

            // TERCERO: Crear edición usando el organizador creado
            DataEdicion edicionData = new DataEdicion(
                "Tecnología Punta del Este 2026_" + uniqueId,
                "CONFTECH26_" + uniqueId,
                LocalDate.of(2026, 4, 6),
                LocalDate.of(2026, 4, 10),
                LocalDate.of(2025, 8, 1),
                "Punta del Este",
                "Uruguay"
            );
            
            controladorEventos.nuevaEdicion(
                edicionData, 
                "Conferencia de Tecnologia_" + uniqueId, 
                "testorg_" + uniqueId  // Usar el nickname del organizador creado
            );
            
            assertTrue("Edición debería existir", 
                controladorEventos.existeEdicion(
                    "Conferencia de Tecnologia_" + uniqueId, 
                    "Tecnología Punta del Este 2026_" + uniqueId
                )
            );

        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage() + " - " + e.getClass().getSimpleName());
        }
    }
    
    public void testNuevoTipoRegistroExitoso() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            Fabrica fabrica = Fabrica.getInstance();
            IUsuario controladorUsuario = fabrica.getIControladorUsuario();
            
            DataOrganizador organizadorData = new DataOrganizador(
                "Test Organizador_" + uniqueId,
                "testorg_" + uniqueId,
                "testorg_" + uniqueId + "@test.com",
                "pass" + uniqueId,
                "Organizador de prueba",
                "https://test.com"
            );
            controladorUsuario.registrarOrganizador(organizadorData);

            controladorEventos.nuevaCategoria("Tecnologia_" + uniqueId);
            
            DataEvento eventoData = new DataEvento(
                "Conferencia de Tecnologia_" + uniqueId,
                "CONFTEC_" + uniqueId,
                LocalDate.of(2025, 1, 10),
                "Evento sobre innovacion tecnológica"
            );
            
            controladorEventos.nuevoEvento(eventoData, Arrays.asList("Tecnologia_" + uniqueId));
            
            DataEdicion edicionData = new DataEdicion(
                "Tecnología Punta del Este 2026_" + uniqueId,
                "CONFTECH26_" + uniqueId,
                LocalDate.of(2026, 4, 6),
                LocalDate.of(2026, 4, 10),
                LocalDate.of(2025, 8, 1),
                "Punta del Este",
                "Uruguay"
            );
           
            controladorEventos.nuevaEdicion(
                edicionData, 
                "Conferencia de Tecnologia_" + uniqueId, 
                "testorg_" + uniqueId
            );
            
            DataTRegistro tipoRegistroData = new DataTRegistro(
                "General_" + uniqueId,
                "Acceso general",
                1500,
                500
            );
            
            controladorEventos.nuevoTipoRegistro(
                tipoRegistroData,
                "Conferencia de Tecnologia_" + uniqueId,
                "Tecnología Punta del Este 2026_" + uniqueId
            );
            
            assertTrue("Tipo de registro debería existir", 
            	    controladorEventos.existeTR(
            	        "Conferencia de Tecnologia_" + uniqueId,
            	        "Tecnología Punta del Este 2026_" + uniqueId,
            	        "General_" + uniqueId
            	    )
            );
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage() + " - " + e.getClass().getSimpleName());
        }
    }
    
    public void testCargarDatosEventos() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            // Crear categorías como en Principal.java
            controladorEventos.nuevaCategoria("Tecnologia_" + uniqueId);
            controladorEventos.nuevaCategoria("Innovacion_" + uniqueId);
            controladorEventos.nuevaCategoria("Literatura_" + uniqueId);
            controladorEventos.nuevaCategoria("Cultura_" + uniqueId);
            controladorEventos.nuevaCategoria("Musica_" + uniqueId);
            controladorEventos.nuevaCategoria("Deporte_" + uniqueId);
            controladorEventos.nuevaCategoria("Salud_" + uniqueId);
            controladorEventos.nuevaCategoria("Entretenimiento_" + uniqueId);
            controladorEventos.nuevaCategoria("Agro_" + uniqueId);
            controladorEventos.nuevaCategoria("Negocios_" + uniqueId);
            controladorEventos.nuevaCategoria("Moda_" + uniqueId);
            controladorEventos.nuevaCategoria("Investigacion_" + uniqueId);
            
            // Crear eventos como en Principal.java
            controladorEventos.nuevoEvento(new DataEvento(
                "Conferencia de Tecnologia_" + uniqueId,
                "CONFTEC_" + uniqueId,
                LocalDate.of(2025, 1, 10),
                "Evento sobre innovacion tecnológica"
            ), Arrays.asList("Tecnologia_" + uniqueId, "Innovacion_" + uniqueId));
            
            controladorEventos.nuevoEvento(new DataEvento(
                "Feria del Libro_" + uniqueId,
                "FERLIB_" + uniqueId,
                LocalDate.of(2025, 2, 1),
                "Encuentro anual de literatura"
            ), Arrays.asList("Literatura_" + uniqueId, "Cultura_" + uniqueId));
            
            controladorEventos.nuevoEvento(new DataEvento(
                "Montevideo Rock_" + uniqueId,
                "MONROCK_" + uniqueId,
                LocalDate.of(2023, 3, 15),
                "Festival de rock con artistas nacionales e internacionales"
            ), Arrays.asList("Cultura_" + uniqueId, "Musica_" + uniqueId));
            
            // Verificar que los eventos fueron creados
            assertTrue("Conferencia de Tecnologia debería existir", existeEvento("Conferencia de Tecnologia_" + uniqueId));
            assertTrue("Feria del Libro debería existir", existeEvento("Feria del Libro_" + uniqueId));
            assertTrue("Montevideo Rock debería existir", existeEvento("Montevideo Rock_" + uniqueId));
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testTipoDeRegistroRepetido() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            // PRIMERO: Crear un organizador
            Fabrica fabrica = Fabrica.getInstance();
            IUsuario controladorUsuario = fabrica.getIControladorUsuario();
            
            DataOrganizador organizadorData = new DataOrganizador(
                "Test Organizador_" + uniqueId,
                "testorg_" + uniqueId,
                "testorg_" + uniqueId + "@test.com",
                "pass" + uniqueId,
                "Organizador de prueba",
                "https://test.com"
            );
            controladorUsuario.registrarOrganizador(organizadorData);

            // SEGUNDO: Configuración inicial
            controladorEventos.nuevaCategoria("Tecnologia_" + uniqueId);
            
            DataEvento eventoData = new DataEvento(
                "Conferencia de Tecnologia_" + uniqueId,
                "CONFTEC_" + uniqueId,
                LocalDate.of(2025, 1, 10),
                "Evento sobre innovacion tecnológica"
            );
            
            controladorEventos.nuevoEvento(eventoData, Arrays.asList("Tecnologia_" + uniqueId));
            
            DataEdicion edicionData = new DataEdicion(
                "Tecnología Punta del Este 2026_" + uniqueId,
                "CONFTECH26_" + uniqueId,
                LocalDate.of(2026, 4, 6),
                LocalDate.of(2026, 4, 10),
                LocalDate.of(2025, 8, 1),
                "Punta del Este",
                "Uruguay"
            );
            
            // Usar el organizador creado
            controladorEventos.nuevaEdicion(
                edicionData, 
                "Conferencia de Tecnologia_" + uniqueId, 
                "testorg_" + uniqueId
            );
            
            // Crear mismo tipo de registro dos veces
            DataTRegistro tipoRegistroData = new DataTRegistro(
                "General",
                "Acceso general",
                1500,
                500
            );
            
            controladorEventos.nuevoTipoRegistro(
                tipoRegistroData,
                "Conferencia de Tecnologia_" + uniqueId,
                "Tecnología Punta del Este 2026_" + uniqueId
            );
            
            controladorEventos.nuevoTipoRegistro(
                tipoRegistroData,
                "Conferencia de Tecnologia_" + uniqueId,
                "Tecnología Punta del Este 2026_" + uniqueId
            );
            
            fail("Debería haber lanzado TipoDeRegistroRepetidoException");
            
        } catch (TipoDeRegistroRepetidoException e) {
            // Test exitoso
            assertTrue("Se esperaba TipoDeRegistroRepetidoException", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}