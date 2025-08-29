package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import excepciones.UsuarioNoExisteException;
import logica.datatypes.*;
import logica.interfaces.IUsuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ConsultaUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IUsuario controlUsr;
    
    // Los componentes gráficos se agregan como atributos de la clase
    private JList<DataUsuario> listUsuarios;
    private JLabel lblUsuarios;
    private JLabel lblListaTitulo;
    private JLabel lblDetallesTitulo;
    private JButton btnCerrar;
    private JScrollPane scrollPaneLista;
    private JScrollPane scrollPaneDetalles;
    
    // Campos para mostrar detalles (no editables)
    private JTextField textFieldNombre;
    private JTextField textFieldNickname;
    private JTextField textFieldEmail;
    private JTextField textFieldTipo;
    private JTextField textFieldApellido;
    private JTextField textFieldFechaNac;
    private JTextField textFieldDescripcion;
    private JTextField textFieldUrl;
    
    // Etiquetas para los campos
    private JLabel lblNombre;
    private JLabel lblNickname;
    private JLabel lblEmail;
    private JLabel lblTipo;
    private JLabel lblApellido;
    private JLabel lblFechaNac;
    private JLabel lblDescripcion;
    private JLabel lblUrl;

    public ConsultaUsuario(IUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        // Propiedades del JInternalFrame
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Lista de Usuarios");
        setBounds(30, 30, 800, 500);
        
        // Título principal centrado
        lblUsuarios = new JLabel("Usuarios Registrados");
        lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
        lblListaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
        lblDetallesTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDetallesTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelDetalles.add(lblDetallesTitulo, BorderLayout.NORTH);
        
        // Panel de contenido con scroll para los campos de detalles
        JPanel panelContenido = new JPanel(new GridBagLayout());
        scrollPaneDetalles = new JScrollPane(panelContenido);
        scrollPaneDetalles.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneDetalles.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetalles.add(scrollPaneDetalles, BorderLayout.CENTER);
        
        // Inicializar componentes de detalles
        inicializarComponentesDetalles(panelContenido);
        
        panelPrincipal.add(panelDetalles);

        // Botón para cerrar
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarYCerrar();
            }
        });
        getContentPane().add(btnCerrar, BorderLayout.SOUTH);
    }

    private void inicializarComponentesDetalles(JPanel panelContenido) {
        // Inicializar etiquetas
        lblNombre = new JLabel("Nombre:");
        lblNickname = new JLabel("Nickname:");
        lblEmail = new JLabel("Email:");
        lblTipo = new JLabel("Tipo:");
        lblApellido = new JLabel("Apellido:");
        lblFechaNac = new JLabel("Fecha de Nacimiento:");
        lblDescripcion = new JLabel("Descripción:");
        lblUrl = new JLabel("URL:");
        
        // Inicializar campos de texto (no editables)
        textFieldNombre = crearCampoNoEditable();
        textFieldNickname = crearCampoNoEditable();
        textFieldEmail = crearCampoNoEditable();
        textFieldTipo = crearCampoNoEditable();
        textFieldApellido = crearCampoNoEditable();
        textFieldFechaNac = crearCampoNoEditable();
        textFieldDescripcion = crearCampoNoEditable();
        textFieldUrl = crearCampoNoEditable();
        
        // Ocultar campos específicos inicialmente
        lblApellido.setVisible(false);
        textFieldApellido.setVisible(false);
        lblFechaNac.setVisible(false);
        textFieldFechaNac.setVisible(false);
        lblDescripcion.setVisible(false);
        textFieldDescripcion.setVisible(false);
        lblUrl.setVisible(false);
        textFieldUrl.setVisible(false);
    }
    
    private JTextField crearCampoNoEditable() {
        JTextField campo = new JTextField(20);
        campo.setEditable(false);
        campo.setBackground(getBackground());
        return campo;
    }

    // Método para cargar usuarios en la lista
    public void cargarUsuarios() {
        try {
            DataUsuario[] usuarios = controlUsr.getUsuarios();
            if (usuarios != null && usuarios.length > 0) {
                javax.swing.DefaultListModel<DataUsuario> model = new javax.swing.DefaultListModel<>();
                for (DataUsuario usuario : usuarios) {
                    model.addElement(usuario);
                }
                listUsuarios.setModel(model);
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
                                String tipo = controlUsr.getTipoUsuario(value.getEmail());
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
            JOptionPane.showMessageDialog(this, 
                "No hay usuarios registrados en el sistema.", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void mostrarDetallesUsuario() {
        DataUsuario usuarioSeleccionado = listUsuarios.getSelectedValue();
        
        if (usuarioSeleccionado == null) {
            limpiarCamposDetalles();
            return;
        }
        
        try {
            String tipo = controlUsr.getTipoUsuario(usuarioSeleccionado.getEmail());
            
            // Limpiar y recrear el panel de contenido
            JPanel panelContenido = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            
            int row = 0;
            
            // Campos comunes para todos los usuarios
            // Nombre
            gbc.gridx = 0;
            gbc.gridy = row;
            panelContenido.add(lblNombre, gbc);
            
            textFieldNombre.setText(usuarioSeleccionado.getNombre());
            gbc.gridx = 1;
            gbc.gridy = row++;
            panelContenido.add(textFieldNombre, gbc);
            
            // Nickname
            gbc.gridx = 0;
            gbc.gridy = row;
            panelContenido.add(lblNickname, gbc);
            
            textFieldNickname.setText(usuarioSeleccionado.getNickname());
            gbc.gridx = 1;
            gbc.gridy = row++;
            panelContenido.add(textFieldNickname, gbc);
            
            // Email
            gbc.gridx = 0;
            gbc.gridy = row;
            panelContenido.add(lblEmail, gbc);
            
            textFieldEmail.setText(usuarioSeleccionado.getEmail());
            gbc.gridx = 1;
            gbc.gridy = row++;
            panelContenido.add(textFieldEmail, gbc);
            
            // Tipo
            gbc.gridx = 0;
            gbc.gridy = row;
            panelContenido.add(lblTipo, gbc);
            
            textFieldTipo.setText(tipo);
            gbc.gridx = 1;
            gbc.gridy = row++;
            panelContenido.add(textFieldTipo, gbc);
            
            // Campos específicos según el tipo de usuario
            if ("Asistente".equals(tipo)) {
                DataAsistente asistente = controlUsr.getAsistente(usuarioSeleccionado.getEmail());
                
                // Apellido
                lblApellido.setVisible(true);
                textFieldApellido.setVisible(true);
                gbc.gridx = 0;
                gbc.gridy = row;
                panelContenido.add(lblApellido, gbc);
                
                textFieldApellido.setText(asistente.getApellido());
                gbc.gridx = 1;
                gbc.gridy = row++;
                panelContenido.add(textFieldApellido, gbc);
                
                // Fecha de Nacimiento
                lblFechaNac.setVisible(true);
                textFieldFechaNac.setVisible(true);
                gbc.gridx = 0;
                gbc.gridy = row;
                panelContenido.add(lblFechaNac, gbc);
                
                textFieldFechaNac.setText(asistente.getFechaNac().toString());
                gbc.gridx = 1;
                gbc.gridy = row++;
                panelContenido.add(textFieldFechaNac, gbc);
                
                // Ocultar campos de Organizador
                lblDescripcion.setVisible(false);
                textFieldDescripcion.setVisible(false);
                lblUrl.setVisible(false);
                textFieldUrl.setVisible(false);
                
            } else if ("Organizador".equals(tipo)) {
                DataOrganizador organizador = controlUsr.getOrganizador(usuarioSeleccionado.getEmail());
                
                // Descripción
                lblDescripcion.setVisible(true);
                textFieldDescripcion.setVisible(true);
                gbc.gridx = 0;
                gbc.gridy = row;
                panelContenido.add(lblDescripcion, gbc);
                
                textFieldDescripcion.setText(organizador.getDescripcion());
                gbc.gridx = 1;
                gbc.gridy = row++;
                panelContenido.add(textFieldDescripcion, gbc);
                
                // URL
                lblUrl.setVisible(true);
                textFieldUrl.setVisible(true);
                gbc.gridx = 0;
                gbc.gridy = row;
                panelContenido.add(lblUrl, gbc);
                
                textFieldUrl.setText(organizador.getUrl());
                gbc.gridx = 1;
                gbc.gridy = row++;
                panelContenido.add(textFieldUrl, gbc);
                
                // Ocultar campos de Asistente
                lblApellido.setVisible(false);
                textFieldApellido.setVisible(false);
                lblFechaNac.setVisible(false);
                textFieldFechaNac.setVisible(false);
            }
            
            // Reemplazar el contenido del scroll pane
            scrollPaneDetalles.setViewportView(panelContenido);
            
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, 
                "Error: No se pudo obtener la información del usuario.\n" + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            limpiarCamposDetalles();
        }
    }
    
    private void limpiarCamposDetalles() {
        textFieldNombre.setText("");
        textFieldNickname.setText("");
        textFieldEmail.setText("");
        textFieldTipo.setText("");
        textFieldApellido.setText("");
        textFieldFechaNac.setText("");
        textFieldDescripcion.setText("");
        textFieldUrl.setText("");
        
        // Ocultar todos los campos específicos
        lblApellido.setVisible(false);
        textFieldApellido.setVisible(false);
        lblFechaNac.setVisible(false);
        textFieldFechaNac.setVisible(false);
        lblDescripcion.setVisible(false);
        textFieldDescripcion.setVisible(false);
        lblUrl.setVisible(false);
        textFieldUrl.setVisible(false);
    }

    // Método para limpiar y cerrar la ventana
    private void limpiarYCerrar() {
        listUsuarios.clearSelection();
        limpiarCamposDetalles();
        setVisible(false);
    }
}