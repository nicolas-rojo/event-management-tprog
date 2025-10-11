package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import excepciones.AsistenteYaRegistrado;
import excepciones.CategoriaRepetidaException;
import excepciones.EdicionRepetidaExcepcion;
import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import excepciones.InstitucionRepetidaException;
import excepciones.NoHayCupoEdicionTRegistro;
import excepciones.TipoDeRegistroRepetidoException;
import excepciones.UsuarioRepetidoException;
import excepciones.PatrocinioRepetidoException;
import logica.Fabrica;

import logica.datatypes.*;
import logica.datatypes.Nivel;

import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;
import logica.interfaces.IInstituciones;


import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;


public class Principal {
    private IUsuario ICU;
    private IEventos ICE;
    private IInstituciones IIN;
    
    private JFrame frmGestionDeUsuarios;
    private CrearUsuario creUsrInternalFrame;
    private ConsultaUsuario lisUsrInternalFrame;
    private ModificarUsuario modUsrInternalFrame;
    private CrearEvento creEventoInternalFrame;
    private CrearEdicion crearEdicionInternalFrame;
    private CrearTipoRegistro creTRegistroInternalFrame;
    private RegistroEdicionEvento regEdEvInternalFrame;
    private ConsultaTipoRegistro consuTRegistroInternalFrame;
    private ConsultaRegistro consuRegistroInternalFrame;
    private ConsultaEvento consEventoInternalFrame;
    private ConsultaEdicionEvento consEdicionEventoInternalFrame;
    private ProcesarEdicion procesarEdicionInternalFrame;
    //Consulta Patrocinio
    private ConsultaPatrocinio consPatrocinioInternalFrame;
    
    // Nuevos InternalFrames para Instituciones
    private CrearInstitucion creInstitucionInternalFrame;
    private ConsultaInstitucion consInstitucionInternalFrame;
    private CrearPatrocinio crePatrocinioInternalFrame;
    
    private boolean cond;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();                   
                    window.frmGestionDeUsuarios.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        initialize();
        
        cond = false;
        
        // Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        ICU = fabrica.getIControladorUsuario();
        ICE = fabrica.getIControladorEventos();
        IIN = fabrica.getIControladorInstituciones();
        
        // Se crean los InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.
        creUsrInternalFrame = new CrearUsuario(ICU);
        creUsrInternalFrame.setVisible(false);

        // CORRECCIÓN: Pasar ambos parámetros al constructor
        lisUsrInternalFrame = new ConsultaUsuario(ICU, ICE);
        lisUsrInternalFrame.setVisible(false);
        
        modUsrInternalFrame = new ModificarUsuario(ICU);
        modUsrInternalFrame.setVisible(false);
        
        creEventoInternalFrame = new CrearEvento(ICE);
        creEventoInternalFrame.setVisible(false);

        consEventoInternalFrame = new ConsultaEvento(ICE);
        consEventoInternalFrame.setVisible(false);
        
        consEdicionEventoInternalFrame = new ConsultaEdicionEvento(ICE);
        consEdicionEventoInternalFrame.setVisible(false);
        
        creTRegistroInternalFrame = new CrearTipoRegistro(ICE);
        creTRegistroInternalFrame.setVisible(false);

        regEdEvInternalFrame = new RegistroEdicionEvento(ICU, ICE);
        regEdEvInternalFrame.setVisible(false);
        
        consuTRegistroInternalFrame = new ConsultaTipoRegistro(ICE);
        consuTRegistroInternalFrame.setVisible(false);
        
        consuRegistroInternalFrame = new ConsultaRegistro(ICU);
        consuRegistroInternalFrame.setVisible(false);
        
        crearEdicionInternalFrame = new CrearEdicion(ICE, ICU);
        crearEdicionInternalFrame.setVisible(false);
        
        procesarEdicionInternalFrame = new ProcesarEdicion(ICE);
        procesarEdicionInternalFrame.setVisible(false);
        
        // Nuevos InternalFrames para Instituciones
        creInstitucionInternalFrame = new CrearInstitucion(IIN);
        creInstitucionInternalFrame.setVisible(false);
        
        consInstitucionInternalFrame = new ConsultaInstitucion(IIN);
        consInstitucionInternalFrame.setVisible(false);
        
        crePatrocinioInternalFrame = new CrearPatrocinio(ICE, IIN);
        crePatrocinioInternalFrame.setVisible(false);
        
        //Consulta Patrocinio
        consPatrocinioInternalFrame = new ConsultaPatrocinio(ICE);
        consPatrocinioInternalFrame.setVisible(false);
        
         
        frmGestionDeUsuarios.getContentPane().setLayout(null);

        frmGestionDeUsuarios.getContentPane().add(creUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(lisUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(modUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creEventoInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(consEventoInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(consEdicionEventoInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creTRegistroInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(regEdEvInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(consuTRegistroInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(consuRegistroInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(crearEdicionInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(procesarEdicionInternalFrame);
        
        // Agregar nuevos InternalFrames al contenido
        frmGestionDeUsuarios.getContentPane().add(creInstitucionInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(consInstitucionInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(crePatrocinioInternalFrame);
        
        //Consulta Patrocinio
        frmGestionDeUsuarios.getContentPane().add(consPatrocinioInternalFrame);
    }

    private void initialize() {
        
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Eventos.uy");
        frmGestionDeUsuarios.setBounds(100, 100, 1000, 700);
        frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frmGestionDeUsuarios.setJMenuBar(menuBar);

        JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                frmGestionDeUsuarios.setVisible(false);
                frmGestionDeUsuarios.dispose();
            }
        });
        menuSistema.add(menuSalir);

        JMenuItem menuCargarDatos = new JMenuItem("Cargar Datos");
        menuCargarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if(!cond) {
            		ICU.cargarDatos();
            		cond = true;
            	}else {
            		JOptionPane.showMessageDialog(frmGestionDeUsuarios, "Los datos ya fueron cargados", "Gestion de Usuarios", JOptionPane.ERROR_MESSAGE);
            	}
            }
    	});
        menuSistema.add(menuCargarDatos);
        
        JMenu menuUsuarios = new JMenu("Usuarios");
        menuBar.add(menuUsuarios);

        JMenuItem menuItemRegistrar = new JMenuItem("Alta de Usuario");
        menuItemRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un usuario
                creUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemRegistrar);

        JMenuItem mntmListaUsuarios = new JMenuItem("Consultar Usuario");
        mntmListaUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver la lista de todos los usuarios,
                // cargando previamente la lista
                lisUsrInternalFrame.cargarUsuarios();
                lisUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(mntmListaUsuarios);
        
        JMenuItem menuModificarUsuario = new JMenuItem("Modificar Datos de Usuario");
        menuModificarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cargar usuarios antes de mostrar la ventana y luego mostrar el InternalFrame
                modUsrInternalFrame.cargarUsuarios();
                modUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuModificarUsuario);
        
        JMenuItem menuRegistroEdicionEvento = new JMenuItem("Registrar a Edicion de Evento");
        menuRegistroEdicionEvento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un asistente a una edicion de evento
                regEdEvInternalFrame.limpiarFormularios();
            	regEdEvInternalFrame.cargarEventos();
                regEdEvInternalFrame.cargarAsistentes();
            	regEdEvInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuRegistroEdicionEvento);
        
        JMenuItem menuItemConsultaRegistro = new JMenuItem("Consulta de Registro");
        menuItemConsultaRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para consulta de registro
            	consuRegistroInternalFrame.limpiarYCerrar();
            	consuRegistroInternalFrame.cargarAsistentes();
            	consuRegistroInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemConsultaRegistro);
        
        JMenu menuEventos = new JMenu("Eventos y Ediciones");
        menuBar.add(menuEventos);
        
        JMenuItem menuItemAltaEvento = new JMenuItem("Alta de Evento");
        menuItemAltaEvento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de evento
            	creEventoInternalFrame.cargarCategorias();
                creEventoInternalFrame.setVisible(true);
            }
        });
        
        
        JMenuItem menuItemConsultaEvento = new JMenuItem("Consulta Evento");
        menuItemConsultaEvento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de evento
            	consEventoInternalFrame.cargarEventos();
                consEventoInternalFrame.setVisible(true);
            }
        });
        
        
        JMenuItem menuItemConsultaEdicionEvento = new JMenuItem("Consulta Edicion Evento");
        menuItemConsultaEdicionEvento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de evento
            	consEdicionEventoInternalFrame.cargarEventos();
            	consEdicionEventoInternalFrame.setVisible(true);
            }
        });
        
        JMenuItem menuItemAltaTRegistro = new JMenuItem("Alta Tipo de Registro");
        menuItemAltaTRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de tipo de registro
            	creTRegistroInternalFrame.cargarEventos();
            	creTRegistroInternalFrame.setVisible(true);
            	
            }
        });
        
        
        JMenuItem menuItemConsultaTRegistro = new JMenuItem("Consulta Tipo Registro");
        menuItemConsultaTRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para consulta de tipo de registro
            	consuTRegistroInternalFrame.limpiarFormulario();
            	consuTRegistroInternalFrame.cargarEventos();
            	consuTRegistroInternalFrame.setVisible(true);
            }
        });
        
        
        JMenuItem menuItemAltaEdicion = new JMenuItem("Alta de Edicion");
        menuItemAltaEdicion.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		crearEdicionInternalFrame.limpiarFormulario();
        		crearEdicionInternalFrame.cargarEventos();
        		crearEdicionInternalFrame.cargarOrganizadores();        		
        		crearEdicionInternalFrame.setVisible(true);
        	}
        });
        
        JMenuItem menuItemProcesarEdicion = new JMenuItem("Procesar Edicion");
        menuItemProcesarEdicion.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		procesarEdicionInternalFrame.limpiarFormulario();
        		procesarEdicionInternalFrame.cargarEventos();
        		
        		procesarEdicionInternalFrame.setVisible(true);
        	}
        });
        		
        menuEventos.add(menuItemAltaEvento);
        menuEventos.add(menuItemAltaEdicion);
        menuEventos.add(menuItemAltaTRegistro);
        menuEventos.add(menuItemConsultaEvento);
        menuEventos.add(menuItemConsultaEdicionEvento);
        menuEventos.add(menuItemConsultaTRegistro);
        menuEventos.add(menuItemProcesarEdicion);
        
        
        // Nueva pestaña para Instituciones
        JMenu menuInstituciones = new JMenu("Instituciones");
        menuBar.add(menuInstituciones);
        
        JMenuItem menuItemAltaInstitucion = new JMenuItem("Alta de Institución");
        menuItemAltaInstitucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de institución
                creInstitucionInternalFrame.limpiarFormulario();
                creInstitucionInternalFrame.setVisible(true);
            }
        });
        menuInstituciones.add(menuItemAltaInstitucion);
        
        JMenuItem menuItemConsultaInstitucion = new JMenuItem("Consulta de Institución");
        menuItemConsultaInstitucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para consulta de instituciones
                consInstitucionInternalFrame.cargarInstituciones();
                consInstitucionInternalFrame.setVisible(true);
            }
        });
        menuInstituciones.add(menuItemConsultaInstitucion);
        
        JMenuItem menuItemAltaPatrocinio = new JMenuItem("Alta de Patrocinio");
        menuItemAltaPatrocinio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de patrocinio
                crePatrocinioInternalFrame.limpiarFormulario();
                crePatrocinioInternalFrame.cargarDatos();
                crePatrocinioInternalFrame.setVisible(true);
            }
        });
        menuInstituciones.add(menuItemAltaPatrocinio);
        
        JMenuItem menuItemConsultaPatrocinio = new JMenuItem("Consulta de Patrocinio");
        menuItemConsultaPatrocinio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Muestro el InternalFrame para Consulta de Patrocinio
                consPatrocinioInternalFrame.cargarEventos();
                consPatrocinioInternalFrame.setVisible(true);
            }
        });
        menuInstituciones.add(menuItemConsultaPatrocinio);
    }
}