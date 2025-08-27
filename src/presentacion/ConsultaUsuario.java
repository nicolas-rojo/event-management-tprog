package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import excepciones.UsuarioNoExisteException;
import logica.IControladorUsuario;
import logica.datatypes.DataUsuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;
import java.awt.Dimension;

/**
 * JInternalFrame que permite listar todos los usuarios del sistema.
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class ListaUsuarios extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorUsuario controlUsr;
    
    // Los componentes gráficos se agregan como atributos de la clase
    private JList<DataUsuario> listUsuarios;
    private JLabel lblUsuarios;
    private JLabel lblListaTitulo;
    private JLabel lblDetallesTitulo;
    private JButton btnCerrar;
    private JTextArea textAreaDetalles;
    private JScrollPane scrollPaneLista;
    private JScrollPane scrollPaneDetalles;

    /**
     * Create the frame.
     */
    public ListaUsuarios(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        // Propiedades del JInternalFrame
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Lista de Usuarios");
        setBounds(30, 30, 600, 400);
        
        // Título principal centrado
        lblUsuarios = new JLabel("Usuarios Registrados");
        lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblUsuarios, BorderLayout.NORTH);

        // Panel principal para dividir la ventana en lista y detalles
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2, 10, 0));
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        // Panel para la lista de usuarios
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setPreferredSize(new Dimension(250, 0));
        
        // Título para la lista de usuarios
        lblListaTitulo = new JLabel("Seleccione un usuario:");
        lblListaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelLista.add(lblListaTitulo, BorderLayout.NORTH);
        
        // Lista de usuarios con scroll
        listUsuarios = new JList<>();
        listUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneLista = new JScrollPane(listUsuarios);
        panelLista.add(scrollPaneLista, BorderLayout.CENTER);
        
        // Añadir listener para mostrar detalles al seleccionar un usuario
        listUsuarios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetallesUsuario();
                }
            }
        });
        
        panelPrincipal.add(panelLista);

        // Panel para los detalles del usuario
        JPanel panelDetalles = new JPanel(new BorderLayout());
        
        // Título para los detalles
        lblDetallesTitulo = new JLabel("Detalles del Usuario:");
        lblDetallesTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelDetalles.add(lblDetallesTitulo, BorderLayout.NORTH);
        
        // Área de texto para mostrar detalles (no editable)
        textAreaDetalles = new JTextArea();
        textAreaDetalles.setEditable(false);
        textAreaDetalles.setLineWrap(true);
        textAreaDetalles.setWrapStyleWord(true);
        scrollPaneDetalles = new JScrollPane(textAreaDetalles);
        panelDetalles.add(scrollPaneDetalles, BorderLayout.CENTER);
        
        panelPrincipal.add(panelDetalles);

        // Botón para cerrar
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarYCerrar();
            }
        });
        getContentPane().add(btnCerrar, BorderLayout.SOUTH);
    }

    // Método para cargar usuarios en la lista
    public void cargarUsuarios() {
        try {
            DataUsuario[] usuarios = controlUsr.getUsuarios();
            if (usuarios != null && usuarios.length > 0) {
                // Crear un modelo de lista personalizado que muestre nombre, nickname y tipo
                javax.swing.DefaultListModel<DataUsuario> model = new javax.swing.DefaultListModel<>();
                for (DataUsuario usuario : usuarios) {
                    model.addElement(usuario);
                }
                listUsuarios.setModel(model);
                
                // Configurar renderizador personalizado para mostrar información adicional
                listUsuarios.setCellRenderer(new javax.swing.ListCellRenderer<DataUsuario>() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(
                            javax.swing.JList<? extends DataUsuario> list, 
                            DataUsuario value, 
                            int index, 
                            boolean isSelected, 
                            boolean cellHasFocus) {
                        
                        JLabel label = new JLabel();
                        if (value != null) {
                            try {
                                String tipo = controlUsr.obtenerTipoUsuario(value.getEmail());
                                label.setText(value.getNombre() + " (" + value.getNickname() + ") - " + tipo);
                            } catch (UsuarioNoExisteException e) {
                                label.setText(value.getNombre() + " (" + value.getNickname() + ")");
                            }
                        }
                        
                        if (isSelected) {
                            label.setBackground(list.getSelectionBackground());
                            label.setForeground(list.getSelectionForeground());
                        } else {
                            label.setBackground(list.getBackground());
                            label.setForeground(list.getForeground());
                        }
                        label.setOpaque(true);
                        return label;
                    }
                });
            }
        } catch (UsuarioNoExisteException e) {
            // No hay usuarios registrados
            textAreaDetalles.setText("No hay usuarios registrados en el sistema.");
        }
    }

    // Método para mostrar detalles del usuario seleccionado
    private void mostrarDetallesUsuario() {
        DataUsuario usuarioSeleccionado = listUsuarios.getSelectedValue();
        
        if (usuarioSeleccionado == null) {
            textAreaDetalles.setText("Seleccione un usuario para ver sus detalles.");
            return;
        }
        
        try {
            String tipo = controlUsr.obtenerTipoUsuario(usuarioSeleccionado.getEmail());
            StringBuilder detalles = new StringBuilder();
            
            detalles.append("INFORMACIÓN DEL USUARIO:\n\n");
            detalles.append("Nombre: ").append(usuarioSeleccionado.getNombre()).append("\n");
            detalles.append("Nickname: ").append(usuarioSeleccionado.getNickname()).append("\n");
            detalles.append("Email: ").append(usuarioSeleccionado.getEmail()).append("\n");
            detalles.append("Tipo: ").append(tipo).append("\n\n");
            
            // Mostrar información específica según el tipo de usuario
            if ("Asistente".equals(tipo)) {
                logica.Asistente asistente = controlUsr.getAsistente(usuarioSeleccionado.getEmail());
                detalles.append("INFORMACIÓN ESPECÍFICA:\n");
                detalles.append("Apellido: ").append(asistente.getApellido()).append("\n");
                detalles.append("Fecha de Nacimiento: ").append(asistente.getFechaNac()).append("\n");
            } else if ("Organizador".equals(tipo)) {
                logica.Organizador organizador = controlUsr.getOrganizador(usuarioSeleccionado.getEmail());
                detalles.append("INFORMACIÓN ESPECÍFICA:\n");
                detalles.append("Descripción: ").append(organizador.getDescripcion()).append("\n");
                detalles.append("URL: ").append(organizador.getUrl()).append("\n");
            }
            
            textAreaDetalles.setText(detalles.toString());
            
        } catch (UsuarioNoExisteException e) {
            textAreaDetalles.setText("Error: No se pudo obtener la información del usuario.\n" + e.getMessage());
        }
    }

    // Método para limpiar y cerrar la ventana
    private void limpiarYCerrar() {
        listUsuarios.clearSelection();
        textAreaDetalles.setText("");
        setVisible(false);
    }
}