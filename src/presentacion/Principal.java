package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Asistente;
import logica.EdicionEvento;
import logica.Evento;
import logica.Fabrica;

import logica.datatypes.*;

import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;
import logica.interfaces.IInstituciones;

import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Principal {

    private JFrame frmGestionDeUsuarios;
    private IUsuario ICU;
    private IEventos IEV;
    private IInstituciones IIN;
    private CrearUsuario creUsrInternalFrame;
    private ConsultaUsuario lisUsrInternalFrame;
    private ModificarUsuario modUsrInternalFrame;
    private CrearEvento creEventoInternalFrame;
    private CrearTipoRegistro creTRegistroInternalFrame;
    private RegistroEdicionEvento regEdEvInternalFrame;
    private ConsultaTipoRegistro consuTRegistroInternalFrame;
    private CrearInstitucion creInstitucionInternalFrame;
    private ConsultaInstitucion consultaInstitucionInternalFrame;
    private CrearPatrocinio crePatrocinioInternalFrame; // Nuevo InternalFrame para patrocinios

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

        // Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        ICU = fabrica.getIControladorUsuario();
        IEV = fabrica.getIControladorEventos();
        IIN = fabrica.getIControladorInstituciones();
        
        // Se crean los InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.
        creUsrInternalFrame = new CrearUsuario(ICU);
        creUsrInternalFrame.setVisible(false);

        lisUsrInternalFrame = new ConsultaUsuario(ICU);
        lisUsrInternalFrame.setVisible(false);
        
        modUsrInternalFrame = new ModificarUsuario(ICU);
        modUsrInternalFrame.setVisible(false);
        
        creEventoInternalFrame = new CrearEvento();
        creEventoInternalFrame.setVisible(false);
        
        creTRegistroInternalFrame = new CrearTipoRegistro(IEV);
        creTRegistroInternalFrame.setVisible(false);

        regEdEvInternalFrame = new RegistroEdicionEvento(ICU, IEV);
        regEdEvInternalFrame.setVisible(false);
        
        consuTRegistroInternalFrame = new ConsultaTipoRegistro(IEV);
        consuTRegistroInternalFrame.setVisible(false);
        
        creInstitucionInternalFrame = new CrearInstitucion(IIN);
        creInstitucionInternalFrame.setVisible(false);
        
        consultaInstitucionInternalFrame = new ConsultaInstitucion(IIN);
        consultaInstitucionInternalFrame.setVisible(false);
        
        // Nuevo InternalFrame para patrocinios
        crePatrocinioInternalFrame = new CrearPatrocinio(IEV, IIN);
        crePatrocinioInternalFrame.setVisible(false);
        
        frmGestionDeUsuarios.getContentPane().setLayout(null);

        frmGestionDeUsuarios.getContentPane().add(creUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(lisUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(modUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creEventoInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creTRegistroInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(regEdEvInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(consuTRegistroInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creInstitucionInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(consultaInstitucionInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(crePatrocinioInternalFrame); // Agregar el nuevo InternalFrame

    }

    private void initialize() {
        
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Gestion de Usuarios 1.0");
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
        
        JMenu menuEventos = new JMenu("Eventos y Ediciones");
        menuBar.add(menuEventos);
        
        JMenuItem menuItemAltaEvento = new JMenuItem("Alta de Evento");
        menuItemAltaEvento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de evento
                creEventoInternalFrame.setVisible(true);
            }
        });
        menuEventos.add(menuItemAltaEvento);
        
        JMenuItem menuItemAltaTRegistro = new JMenuItem("Alta Tipo de Registro");
        menuItemAltaTRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de tipo de registro
            	creTRegistroInternalFrame.cargarEventos();
            	creTRegistroInternalFrame.setVisible(true);
            	
            }
        });
        menuEventos.add(menuItemAltaTRegistro);
        
        JMenuItem menuItemConsultaTRegistro = new JMenuItem("Consulta Tipo Registro");
        menuItemConsultaTRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para consulta de tipo de registro
            	consuTRegistroInternalFrame.limpiarFormulario();
            	consuTRegistroInternalFrame.cargarEventos();
            	consuTRegistroInternalFrame.setVisible(true);
            }
        });
        menuEventos.add(menuItemConsultaTRegistro);
        
        // NUEVO MENÚ DE INSTITUCIONES
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
        
        JMenuItem menuItemConsultaInstitucion = new JMenuItem("Consultar Institución");
        menuItemConsultaInstitucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para consulta de instituciones
                consultaInstitucionInternalFrame.cargarInstituciones();
                consultaInstitucionInternalFrame.setVisible(true);
            }
        });
        menuInstituciones.add(menuItemConsultaInstitucion);
        
        // NUEVO ITEM PARA ALTA DE PATROCINIO
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
    }
}