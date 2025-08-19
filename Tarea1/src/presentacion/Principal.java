package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Fabrica;
import logica.IControladorUsuario;

import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase principal (Frame) con el método Main.
 * @author TProg2017
 *
 */
public class Principal {

    private JFrame frmGestionDeUsuarios;
    private IControladorUsuario ICU;
    private CrearUsuario creUsrInternalFrame;
    private ConsultarUsuario conUsrInternalFrame;
    private ListaUsuarios lisUsrInternalFrame;

    /**
     * Launch the application.
     */
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

    /**
     * Create the application.
     */
    public Principal() {
        initialize();

        // Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        ICU = fabrica.getIControladorUsuario();
        
        // Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.
        // Cada InternalFrame usa un layout diferente, simplemente para mostrar distintas opciones.
        creUsrInternalFrame = new CrearUsuario(ICU);
        creUsrInternalFrame.setVisible(false);

        conUsrInternalFrame = new ConsultarUsuario(ICU);
        conUsrInternalFrame.setVisible(false);

        lisUsrInternalFrame = new ListaUsuarios(ICU);
        lisUsrInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().setLayout(null);

        frmGestionDeUsuarios.getContentPane().add(conUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(lisUsrInternalFrame);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        // Se crea el Frame con las dimensiones indicadas.
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Gestion de Usuarios 1.0");
        frmGestionDeUsuarios.setBounds(100, 100, 450, 400);
        frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
        // Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un 
        // evento asociado que permite realizar una acción una vez se seleccionan. 
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

        JMenuItem menuItemVerInfo = new JMenuItem("Consulta de Usuario");
        menuItemVerInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver información de un usuario
                conUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemVerInfo);

        JMenuItem mntmListaUsuarios = new JMenuItem("ListarUsuarios");
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
        //Falta actionListener
        menuUsuarios.add(menuModificarUsuario);
        
        JMenu menuEventos = new JMenu("Eventos y Ediciones");
        menuBar.add(menuEventos);
        
        JMenuItem menuItemAltaEvento = new JMenuItem("Alta de Evento");
        //Falta el actionListener
        menuEventos.add(menuItemAltaEvento);
        
        JMenuItem menuItemAltaEdEvento = new JMenuItem("Alta Edición de Evento");
        //Falta el actionListener
        menuEventos.add(menuItemAltaEdEvento);
        
        JMenuItem menuItemConsultaEdEvento = new JMenuItem("Consulta Edición de Evento");
        //Falta el actionListener
        menuEventos.add(menuItemConsultaEdEvento);
        
        JMenuItem menuItemAltaPatrocinio = new JMenuItem("Alta de Patrocinio");
        //Falta el actionListener
        menuEventos.add(menuItemAltaPatrocinio);
        
        JMenuItem menuItemConsultaPatrocinio = new JMenuItem("Consulta Patrocinio de Evento");
        //Falta el actionListener
        menuEventos.add(menuItemConsultaPatrocinio);
        
        JMenu menuInstituciones = new JMenu("Instituciones");
        menuBar.add(menuInstituciones);
        
        JMenuItem menuItemAltaInstitucion = new JMenuItem("Alta de Institución");
        //Falta el actionListener
        menuInstituciones.add(menuItemAltaInstitucion);
        
        JMenu menuRegistros = new JMenu("Registros y asistencia");
        menuBar.add(menuRegistros);
        
        JMenuItem menuItemAltaTipoRegistro = new JMenuItem("Alta de Tipo de Registro");
        //Falta el actionListener
        menuRegistros.add(menuItemAltaTipoRegistro);
        
        JMenuItem menuItemConsultaTipoRegistro = new JMenuItem("Consulta de Tipo de Registro");
        //Falta el actionListener
        menuRegistros.add(menuItemConsultaTipoRegistro);
        
        JMenuItem menuItemRegistroEdición = new JMenuItem("Registro a Edición de Evento");
        //Falta el actionListener
        menuRegistros.add(menuItemRegistroEdición);
        
        JMenuItem menuItemConsultaRegistro = new JMenuItem("Consulta de Registros");
        //Falta el actionListener
        menuRegistros.add(menuItemConsultaRegistro);
    }
}
