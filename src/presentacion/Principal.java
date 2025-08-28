package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Fabrica;
import logica.interfaces.IUsuario;
import logica.interfaces.IEventos;

import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Principal {

    private JFrame frmGestionDeUsuarios;
    private IUsuario ICU;
    private IEventos ICE;
    private CrearUsuario creUsrInternalFrame;
    private ConsultaUsuario lisUsrInternalFrame;
    private ModificarUsuario modUsrInternalFrame;
    private CrearEvento creEventoInternalFrame;

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	
	                    Principal window = new Principal();	                  	                    	                    	                 	                                        window.frmGestionDeUsuarios.setVisible(true);
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
        ICE = fabrica.getIEventos();
        // Se crean los InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.
        creUsrInternalFrame = new CrearUsuario(ICU);
        creUsrInternalFrame.setVisible(false);

        lisUsrInternalFrame = new ConsultaUsuario(ICU);
        lisUsrInternalFrame.setVisible(false);
        
        modUsrInternalFrame = new ModificarUsuario(ICU);
        modUsrInternalFrame.setVisible(false);
        
        creEventoInternalFrame = new CrearEvento(ICE);
        creEventoInternalFrame.setVisible(false);
        
       
        
        
        frmGestionDeUsuarios.getContentPane().setLayout(null);

        frmGestionDeUsuarios.getContentPane().add(creUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(lisUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(modUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creEventoInternalFrame);
    }

    private void initialize() {
        
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Gestion de Usuarios 1.0");
        frmGestionDeUsuarios.setBounds(100, 100, 450, 400);
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
        menuEventos.add(menuItemAltaEvento);
    }
}