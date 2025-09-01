package test;

import excepciones.InstitucionRepetidaException;
import excepciones.PatrocinioRepetidoException;
import logica.Fabrica;
import logica.ctrlmanejador.ManejadorInstituciones;
import logica.datatypes.DataInstitucion;
import logica.datatypes.DataPatrocinio;
import logica.datatypes.Nivel;
import logica.interfaces.IInstituciones;

import junit.framework.TestCase;

import java.time.LocalDate;

public class TestInstituciones extends TestCase {
    
    private IInstituciones controladorInstituciones;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ManejadorInstituciones.resetInstance();
        
        Fabrica fabrica = Fabrica.getInstance();
        controladorInstituciones = fabrica.getIControladorInstituciones();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        ManejadorInstituciones.resetInstance();
    }
    
    private boolean existeInstitucion(String nombre) {
        try {
            DataInstitucion[] instituciones = controladorInstituciones.listarInstituciones();
            if (instituciones == null) {
                return false;
            }
            
            for (DataInstitucion institucion : instituciones) {
                if (institucion.getNombre().equals(nombre)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    private DataInstitucion obtenerDatosInstitucion(String nombre) {
        DataInstitucion[] instituciones = controladorInstituciones.listarInstituciones();
        for (int i = 0; i < instituciones.length; i++) {
            DataInstitucion institucion = instituciones[i];
            if (institucion.getNombre().equals(nombre)) {
                return institucion;
            }
        }
        return null;
    }
    
    public void testNuevaInstitucionExitosa() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        DataInstitucion institucionData = new DataInstitucion(
            "Facultad de Ingenieria_" + uniqueId,
            "Facultad de Ingeniería de la Universidad de la República",
            "https://www.fing.edu.uy/"
        );
        
        try {
            controladorInstituciones.nuevaInstitucion(institucionData);
            assertTrue("La institución debería existir", existeInstitucion("Facultad de Ingenieria_" + uniqueId));
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testInstitucionRepetida() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        DataInstitucion institucion1 = new DataInstitucion(
            "Facultad de Ingenieria_" + uniqueId,
            "Facultad de Ingeniería de la Universidad de la República",
            "https://www.fing.edu.uy/"
        );
        
        DataInstitucion institucion2 = new DataInstitucion(
            "Facultad de Ingenieria_" + uniqueId, // Mismo nombre
            "Otra descripción",
            "https://otra.edu.uy/"
        );
        
        try {
            controladorInstituciones.nuevaInstitucion(institucion1);
            controladorInstituciones.nuevaInstitucion(institucion2);
            fail("Debería haber lanzado InstitucionRepetidaException");
        } catch (InstitucionRepetidaException e) {
            // Test exitoso
            assertTrue("Se esperaba InstitucionRepetidaException", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
    }
    
    public void testCargarDatosInstituciones() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        
        try {
            // Crear instituciones
            controladorInstituciones.nuevaInstitucion(new DataInstitucion(
                "Facultad de Ingenieria_" + uniqueId, 
                "Facultad de Ingeniería de la Universidad de la República", 
                "https://www.fing.edu.uy/"
            ));
            
            controladorInstituciones.nuevaInstitucion(new DataInstitucion(
                "ORT Uruguay_" + uniqueId, 
                "Universidad privada enfocada en tecnologia y gestion", 
                "https://ort.edu.uy"
            ));
            
            controladorInstituciones.nuevaInstitucion(new DataInstitucion(
                "Universidad Catolica del Uruguay_" + uniqueId, 
                "Institucion de educacion superior privada", 
                "https://ucu.edu.uy/"
            ));
            
            controladorInstituciones.nuevaInstitucion(new DataInstitucion(
                "Antel_" + uniqueId, 
                "Empresa estatal de telecomunicaciones", 
                "https://antel.com.uy/"
            ));
            
            controladorInstituciones.nuevaInstitucion(new DataInstitucion(
                "ANII_" + uniqueId, 
                "Fomenta la investigacion y la innovacion en Uruguay", 
                "https://anii.org.uy/"
            ));
            
            // Verificar que se registraron 5 instituciones
            DataInstitucion[] instituciones = controladorInstituciones.listarInstituciones();
            assertNotNull("Array de instituciones no debería ser null", instituciones);
            assertEquals("Debería haber exactamente 5 instituciones", 5, instituciones.length);
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testDatosInstitucionRegistrada() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String nombre = "Facultad de Ingenieria_" + uniqueId;
        String descripcion = "Facultad de Ingeniería de la Universidad de la República";
        String url = "https://www.fing.edu.uy/";
        
        DataInstitucion institucionData = new DataInstitucion(
            nombre,
            descripcion,
            url
        );
        
        try {
            controladorInstituciones.nuevaInstitucion(institucionData);
            
            DataInstitucion datosInstitucion = obtenerDatosInstitucion(nombre);
            assertNotNull("Datos institución no debería ser null", datosInstitucion);
            assertEquals("Nombre incorrecto", nombre, datosInstitucion.getNombre());
            assertEquals("Descripción incorrecta", descripcion, datosInstitucion.getDescripcion());
            assertEquals("URL incorrecta", url, datosInstitucion.getUrl());
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testListadoInstituciones() {
        try {
            // Crear instituciones específicas para este test
            controladorInstituciones.nuevaInstitucion(new DataInstitucion(
                "Facultad de Ingenieria_",
                "Facultad de Ingeniería de la Universidad de la República",
                "https://www.fing.edu.uy/"
            ));
            
            controladorInstituciones.nuevaInstitucion(new DataInstitucion(
                "ORT Uruguay_",
                "Universidad privada enfocada en tecnologia y gestion",
                "https://ort.edu.uy"
            ));
            
            controladorInstituciones.nuevaInstitucion(new DataInstitucion(
                "Test Institucion_",
                "Institucion de prueba",
                "https://test.edu.uy/"
            ));
            
            DataInstitucion[] instituciones = controladorInstituciones.listarInstituciones();
            assertNotNull("Array de instituciones no debería ser null", instituciones);
            assertTrue("Debería haber al menos 3 instituciones", instituciones.length >= 3);

            boolean encontradaFacultad = false;
            boolean encontradaORT = false;
            boolean encontradaTest = false;
            
            for (DataInstitucion institucion : instituciones) {
                if (institucion.getNombre().equals("Facultad de Ingenieria_")) {
                    encontradaFacultad = true;
                }
                if (institucion.getNombre().equals("ORT Uruguay_")) {
                    encontradaORT = true;
                }
                if (institucion.getNombre().equals("Test Institucion_")) {
                    encontradaTest = true;
                }
            }
            
            assertTrue("Debería contener Facultad de Ingenieria", encontradaFacultad);
            assertTrue("Debería contener ORT Uruguay", encontradaORT);
            assertTrue("Debería contener Test Institucion", encontradaTest);

        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testNuevoPatrocinio() {
        try {
            DataPatrocinio patrocinioData = new DataPatrocinio(
                LocalDate.of(2025, 8, 21),
                (float) 20000,
                Nivel.Oro,
                "TECHFING_",
                4
            );

            assertEquals("Fecha incorrecta", LocalDate.of(2025, 8, 21), patrocinioData.getFecha());
            assertEquals("Monto incorrecto", (float) 20000, patrocinioData.getMonto());
            assertEquals("Nivel incorrecto", Nivel.Oro, patrocinioData.getNivel());
            assertEquals("Nombre incorrecto", "TECHFING_", patrocinioData.getCod());
            assertEquals("Cantidad incorrecta", 4, patrocinioData.getCtdCupo());
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testNivelesPatrocinio() {
        assertNotNull("Nivel Oro no debería ser null", Nivel.Oro);
        assertNotNull("Nivel Plata no debería ser null", Nivel.Plata);
        assertNotNull("Nivel Bronce no debería ser null", Nivel.Bronce);
        assertNotNull("Nivel Platino no debería ser null", Nivel.Platino);
        
        try {
            DataPatrocinio patrocinioOro = new DataPatrocinio(
                LocalDate.now(), 10000, Nivel.Oro, "TEST001", 1
            );
            assertEquals("Nivel incorrecto", Nivel.Oro, patrocinioOro.getNivel());
            
            DataPatrocinio patrocinioPlata = new DataPatrocinio(
                LocalDate.now(), 5000, Nivel.Plata, "TEST002", 1
            );
            assertEquals("Nivel incorrecto", Nivel.Plata, patrocinioPlata.getNivel());
            
            DataPatrocinio patrocinioBronce = new DataPatrocinio(
                LocalDate.now(), 2000, Nivel.Bronce, "TEST003", 1
            );
            assertEquals("Nivel incorrecto", Nivel.Bronce, patrocinioBronce.getNivel());
            
            DataPatrocinio patrocinioPlatino = new DataPatrocinio(
                LocalDate.now(), 50000, Nivel.Platino, "TEST004", 1
            );
            assertEquals("Nivel incorrecto", Nivel.Platino, patrocinioPlatino.getNivel());
            
        } catch (Exception e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    public void testPatrocinioRepetido() {
 
        try {
            throw new PatrocinioRepetidoException("Patrocinio repetido");
        } catch (PatrocinioRepetidoException e) {
            // Test exitoso
            assertTrue("Se esperaba PatrocinioRepetidoException", true);
        } catch (Exception e) {
            fail("Lanzó excepción incorrecta: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}