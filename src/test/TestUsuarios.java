package test;

import java.time.LocalDate;
import java.util.List;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.Fabrica;
import logica.ctrlmanejador.ManejadorUsuario; // ← Importar ManejadorUsuario
import logica.datatypes.DataAsistente;
import logica.datatypes.DataOrganizador;
import logica.datatypes.DataUsuario;
import logica.interfaces.IUsuario;

import junit.framework.TestCase;

public class TestUsuarios extends TestCase {
    
    private IUsuario controladorUsuario;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Resetear el Singleton antes de cada test
        ManejadorUsuario.resetInstance(); // ← Añadir esta línea
        
        Fabrica fabrica = Fabrica.getInstance();
        controladorUsuario = fabrica.getIControladorUsuario();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        // Limpiar después de cada test (opcional pero recomendado)
        ManejadorUsuario.resetInstance(); // ← Añadir esta línea
    }
    
    private boolean existeUsuario(String nickname) {
        try {
            DataUsuario[] usuarios = controladorUsuario.getUsuarios();
            for (int i = 0; i < usuarios.length; i++) {
                DataUsuario usuario = usuarios[i];
                if (usuario.getNickname().equals(nickname)) {
                    return true;
                }
            }
            return false;
        } catch (UsuarioNoExisteException e) {
            return false;
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
            return false;
        }
    }
    
    private DataUsuario obtenerDatosUsuario(String nickname) throws UsuarioNoExisteException {
        DataUsuario[] usuarios = controladorUsuario.getUsuarios();
        for (int i = 0; i < usuarios.length; i++) {
            DataUsuario usuario = usuarios[i];
            if (usuario.getNickname().equals(nickname)) {
                return usuario;
            }
        }
        throw new UsuarioNoExisteException("Usuario no encontrado: " + nickname);
    }
    
    public void testRegistrarAsistenteExitoso() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        DataAsistente asistenteData = new DataAsistente(
            "Ana", 
            "atorres_" + uniqueId, 
            "atorres_" + uniqueId + "@gmail.com", 
            "Torres", 
            LocalDate.of(1990, 5, 12)
        );
        
        try {
            controladorUsuario.registrarAsistente(asistenteData);
            assertTrue("El usuario debería existir", existeUsuario("atorres_" + uniqueId));
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testRegistrarOrganizadorExitoso() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        DataOrganizador organizadorData = new DataOrganizador(
            "MisEventos",
            "miseventos_" + uniqueId, 
            "contacto_" + uniqueId + "@miseventos.com", 
            "Empresa de organizacion de eventos.", 
            "https://miseventos.com/"
        );
        
        try {
            controladorUsuario.registrarOrganizador(organizadorData);
            assertTrue("El organizador debería existir", existeUsuario("miseventos_" + uniqueId));
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testRegistrarAsistenteNicknameRepetido() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        DataAsistente asistente1 = new DataAsistente(
            "Ana", 
            "atorres_" + uniqueId, 
            "atorres_" + uniqueId + "@gmail.com", 
            "Torres", 
            LocalDate.of(1990, 5, 12)
        );
        
        DataAsistente asistente2 = new DataAsistente(
            "Pedro", 
            "atorres_" + uniqueId, // Mismo nickname
            "pedro_" + uniqueId + "@gmail.com", 
            "Perez", 
            LocalDate.of(1985, 3, 10)
        );
        
        try {
            controladorUsuario.registrarAsistente(asistente1);
            controladorUsuario.registrarAsistente(asistente2);
            fail("Debería haber lanzado UsuarioRepetidoException");
        } catch (UsuarioRepetidoException e) {
            // Test exitoso
            assertTrue("Se esperaba UsuarioRepetidoException", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    public void testRegistrarAsistenteEmailRepetido() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        DataAsistente asistente1 = new DataAsistente(
            "Ana", 
            "atorres_" + uniqueId, 
            "email_" + uniqueId + "@gmail.com", 
            "Torres", 
            LocalDate.of(1990, 5, 12)
        );
        
        DataAsistente asistente2 = new DataAsistente(
            "Pedro", 
            "pedro_" + uniqueId,
            "email_" + uniqueId + "@gmail.com", // Mismo email
            "Perez", 
            LocalDate.of(1985, 3, 10)
        );
        
        try {
            controladorUsuario.registrarAsistente(asistente1);
            controladorUsuario.registrarAsistente(asistente2);
            fail("Debería haber lanzado UsuarioRepetidoException");
        } catch (UsuarioRepetidoException e) {
            // Test exitoso
            assertTrue("Se esperaba UsuarioRepetidoException", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    public void testRegistrarOrganizadorNicknameRepetido() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        DataOrganizador org1 = new DataOrganizador(
            "MisEventos",
            "miseventos_" + uniqueId, 
            "contacto1_" + uniqueId + "@miseventos.com", 
            "Empresa de organizacion de eventos.", 
            "https://miseventos.com/"
        );
        
        DataOrganizador org2 = new DataOrganizador(
            "OtraEmpresa",
            "miseventos_" + uniqueId, // Mismo nickname
            "contacto2_" + uniqueId + "@empresa.com", 
            "Otra empresa de eventos.", 
            "https://otra.com/"
        );
        
        try {
            controladorUsuario.registrarOrganizador(org1);
            controladorUsuario.registrarOrganizador(org2);
            fail("Debería haber lanzado UsuarioRepetidoException");
        } catch (UsuarioRepetidoException e) {
            // Test exitoso
            assertTrue("Se esperaba UsuarioRepetidoException", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    public void testCargarDatosUsuarios() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            // Registrar asistentes
            controladorUsuario.registrarAsistente(new DataAsistente("Ana", "atorres_" + uniqueId, "atorres_" + uniqueId + "@gmail.com", "Torres", LocalDate.of(1990, 5, 12)));
            controladorUsuario.registrarAsistente(new DataAsistente("Martin", "msilva_" + uniqueId, "martin.silva_" + uniqueId + "@fing.edu.uy", "Silva", LocalDate.of(1987, 8, 21)));
            controladorUsuario.registrarAsistente(new DataAsistente("Sofia", "sofirod_" + uniqueId, "srodriguez_" + uniqueId + "@outlook.com", "Rodriguez", LocalDate.of(1995, 2, 3)));
            controladorUsuario.registrarAsistente(new DataAsistente("Valentina", "vale23_" + uniqueId, "valentina.costa_" + uniqueId + "@mail.com", "Costa", LocalDate.of(1992, 12, 1)));
            controladorUsuario.registrarAsistente(new DataAsistente("Lucia", "luciag_" + uniqueId, "lucia.garcia_" + uniqueId + "@mail.com", "Garcia", LocalDate.of(1993, 11, 9)));
            
            // Registrar organizadores
            controladorUsuario.registrarOrganizador(new DataOrganizador("MisEventos", "miseventos_" + uniqueId, "contacto_" + uniqueId + "@miseventos.com", "Empresa de organizacion de eventos.", "https://miseventos.com/"));
            controladorUsuario.registrarOrganizador(new DataOrganizador("Corporación Tecnológica", "techcorp_" + uniqueId, "info_" + uniqueId + "@techcorp.com", "Empresa líder en tecnologías de la información", ""));
            controladorUsuario.registrarOrganizador(new DataOrganizador("Intendencia de Montevideo", "imm_" + uniqueId, "contacto_" + uniqueId + "@imm.gub.uy", "Gobierno departamental de Montevideo.", "https://montevideo.gub.uy/"));
            
            // Verificar que todos los usuarios existen
            assertTrue("atorres debería existir", existeUsuario("atorres_" + uniqueId));
            assertTrue("msilva debería existir", existeUsuario("msilva_" + uniqueId));
            assertTrue("sofirod debería existir", existeUsuario("sofirod_" + uniqueId));
            assertTrue("vale23 debería existir", existeUsuario("vale23_" + uniqueId));
            assertTrue("luciag debería existir", existeUsuario("luciag_" + uniqueId));
            assertTrue("miseventos debería existir", existeUsuario("miseventos_" + uniqueId));
            assertTrue("techcorp debería existir", existeUsuario("techcorp_" + uniqueId));
            assertTrue("imm debería existir", existeUsuario("imm_" + uniqueId));
            
            // Verificar que se registraron 8 usuarios en total
            DataUsuario[] usuarios = controladorUsuario.getUsuarios();
            assertTrue("Debería haber al menos 8 usuarios", usuarios.length >= 8);
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testDatosAsistenteRegistrado() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String nickname = "atorres_datos_" + uniqueId;
        String email = nickname + "@gmail.com";
        
        DataAsistente asistenteData = new DataAsistente(
            "Ana", 
            nickname, 
            email, 
            "Torres", 
            LocalDate.of(1990, 5, 12)
        );
        
        try {
            controladorUsuario.registrarAsistente(asistenteData);
            
            DataUsuario datosUsuario = obtenerDatosUsuario(nickname);
            assertNotNull("Datos usuario no debería ser null", datosUsuario);
            assertEquals("Nombre incorrecto", "Ana", datosUsuario.getNombre());
            assertEquals("Nickname incorrecto", nickname, datosUsuario.getNickname());
            assertEquals("Email incorrecto", email, datosUsuario.getEmail());
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testListadoUsuarios() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            // Crear usuarios específicos para este test
            controladorUsuario.registrarAsistente(new DataAsistente("Ana", "ana_list_" + uniqueId, "ana_" + uniqueId + "@test.com", "Torres", LocalDate.of(1990, 5, 12)));
            controladorUsuario.registrarAsistente(new DataAsistente("Martin", "martin_list_" + uniqueId, "martin_" + uniqueId + "@test.com", "Silva", LocalDate.of(1987, 8, 21)));
            controladorUsuario.registrarOrganizador(new DataOrganizador("TestEventos", "test_eventos_" + uniqueId, "test_" + uniqueId + "@eventos.com", "Empresa de test.", "https://test.com/"));
            
            DataUsuario[] usuarios = controladorUsuario.getUsuarios();
            assertNotNull("Array de usuarios no debería ser null", usuarios);
            assertTrue("Debería haber al menos 3 usuarios", usuarios.length >= 3);
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testListarAsistentesYOrganizadores() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            // Crear usuarios específicos para este test
            controladorUsuario.registrarAsistente(new DataAsistente("Ana", "ana_listas_" + uniqueId, "ana_listas_" + uniqueId + "@test.com", "Torres", LocalDate.of(1990, 5, 12)));
            controladorUsuario.registrarAsistente(new DataAsistente("Martin", "martin_listas_" + uniqueId, "martin_listas_" + uniqueId + "@test.com", "Silva", LocalDate.of(1987, 8, 21)));
            controladorUsuario.registrarOrganizador(new DataOrganizador("ListEventos", "list_eventos_" + uniqueId, "list_" + uniqueId + "@eventos.com", "Empresa de list.", "https://list.com/"));
            
            List<String> asistentes = controladorUsuario.listarAsistentes();
            List<String> organizadores = controladorUsuario.listarOrganizadores();
            
            assertNotNull("Lista de asistentes no debería ser null", asistentes);
            assertNotNull("Lista de organizadores no debería ser null", organizadores);
            assertTrue("Debería haber al menos 2 asistentes", asistentes.size() >= 2);
            assertTrue("Debería haber al menos 1 organizador", organizadores.size() >= 1);
            
            // Verificar contenido específico
            assertTrue("Debería contener ana_listas_" + uniqueId, asistentes.contains("ana_listas_" + uniqueId));
            assertTrue("Debería contener martin_listas_" + uniqueId, asistentes.contains("martin_listas_" + uniqueId));
            assertTrue("Debería contener list_eventos_" + uniqueId, organizadores.contains("list_eventos_" + uniqueId));
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testGetAsistenteExistente() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String email = "test_asistente_" + uniqueId + "@gmail.com";
        
        DataAsistente asistenteOriginal = new DataAsistente(
            "TestAsistente", 
            "test_asist_" + uniqueId, 
            email, 
            "Apellido", 
            LocalDate.of(1990, 1, 1)
        );
        
        try {
            controladorUsuario.registrarAsistente(asistenteOriginal);
            
            DataAsistente asistenteObtenido = controladorUsuario.getAsistente(email);
            
            assertNotNull("Asistente obtenido no debería ser null", asistenteObtenido);
            assertEquals("Nombre incorrecto", "TestAsistente", asistenteObtenido.getNombre());
            assertEquals("Email incorrecto", email, asistenteObtenido.getEmail());
            assertEquals("Apellido incorrecto", "Apellido", asistenteObtenido.getApellido());
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testGetOrganizadorExistente() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String email = "test_org_" + uniqueId + "@empresa.com";
        
        DataOrganizador organizadorOriginal = new DataOrganizador(
            "TestOrganizador", 
            "test_org_" + uniqueId, 
            email, 
            "Descripción de prueba", 
            "https://test.com"
        );
        
        try {
            controladorUsuario.registrarOrganizador(organizadorOriginal);
            
            DataOrganizador organizadorObtenido = controladorUsuario.getOrganizador(email);
            
            assertNotNull("Organizador obtenido no debería ser null", organizadorObtenido);
            assertEquals("Nombre incorrecto", "TestOrganizador", organizadorObtenido.getNombre());
            assertEquals("Email incorrecto", email, organizadorObtenido.getEmail());
            assertEquals("Descripción incorrecta", "Descripción de prueba", organizadorObtenido.getDescripcion());
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testGetTipoUsuario() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String emailAsistente = "asistente_tipo_" + uniqueId + "@test.com";
        String emailOrganizador = "organizador_tipo_" + uniqueId + "@test.com";
        
        try {
            controladorUsuario.registrarAsistente(new DataAsistente("Ana", "ana_tipo_" + uniqueId, emailAsistente, "Torres", LocalDate.of(1990, 5, 12)));
            controladorUsuario.registrarOrganizador(new DataOrganizador("Org", "org_tipo_" + uniqueId, emailOrganizador, "Descripción", ""));
            
            String tipoAsistente = controladorUsuario.getTipoUsuario(emailAsistente);
            String tipoOrganizador = controladorUsuario.getTipoUsuario(emailOrganizador);
            
            assertEquals("Tipo de asistente incorrecto", "Asistente", tipoAsistente);
            assertEquals("Tipo de organizador incorrecto", "Organizador", tipoOrganizador);
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
}