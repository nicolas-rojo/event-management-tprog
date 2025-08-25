package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;

import excepciones.UsuarioNoExisteException;
import logica.Asistente;
import logica.DataUsuario;
import logica.IControladorUsuario;
import logica.Organizador;

@SuppressWarnings("serial")
public class ModificarUsuario extends JInternalFrame {

    private JTextField textFieldNombre;
    private JTextField textFieldNickname;
    private JTextField textFieldEmail;
    private JButton btnFinalizar;
    private JComboBox<DataUsuario> comboBoxUsuarios;
    private JLabel lblSeleccionarUsuario;
    private JLabel lblDatosUsuario;
    private JLabel lblEmail;
    private JLabel lblNombre;
    private JLabel lblNickname;
    
    // Campos específicos para Asistente
    private JTextField textFieldApellido;
    private JLabel lblApellido;
    private JPanel panelAsistente;
    
    // Campos específicos para Organizador
    private JTextField textFieldDescripcion;
    private JTextField textFieldUrl;
    private JLabel lblDescripcion;
    private JLabel lblUrl;
    private JPanel panelOrganizador;
    
    // Componentes para la lista lateral
    private JList<DataUsuario> listUsuarios;
    private JScrollPane scrollPane;
    
    // Panel para campos de edición
    private JPanel panelEdicion;
    private JPanel panelContenedor;
    
    private IControladorUsuario controlUsr;
    private boolean modoSeleccion = true;
    private DataUsuario usuarioOriginal;
    private String tipoUsuario;

    public ModificarUsuario(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Usuario");
        setBounds(10, 40, 600, 400);

        // Panel contenedor principal
        panelContenedor = new JPanel();
        panelContenedor.setLayout(new GridBagLayout());
        setContentPane(panelContenedor);

        // Título centrado
        lblSeleccionarUsuario = new JLabel("Seleccionar Usuario");
        lblSeleccionarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccionarUsuario.setFont(lblSeleccionarUsuario.getFont().deriveFont(16f));
        GridBagConstraints gbc_lblSeleccionar = new GridBagConstraints();
        gbc_lblSeleccionar.insets = new Insets(15, 5, 15, 5);
        gbc_lblSeleccionar.gridx = 0;
        gbc_lblSeleccionar.gridy = 0;
        gbc_lblSeleccionar.gridwidth = 2;
        gbc_lblSeleccionar.fill = GridBagConstraints.HORIZONTAL;
        panelContenedor.add(lblSeleccionarUsuario, gbc_lblSeleccionar);

        // Creamos la lista centrada con scroll
        listUsuarios = new JList<>();
        listUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listUsuarios.setVisibleRowCount(8);
        
        // Añadimos el listener para selección automática al hacer clic
        listUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Un solo clic
                    int index = listUsuarios.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        listUsuarios.setSelectedIndex(index);
                        cambiarAModoEdicion();
                    }
                }
            }
        });
        
        scrollPane = new JScrollPane(listUsuarios);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(5, 50, 15, 50);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.weightx = 1.0;
        gbc_scrollPane.weighty = 1.0;
        panelContenedor.add(scrollPane, gbc_scrollPane);

        // Panel de edición (inicialmente oculto)
        panelEdicion = new JPanel();
        panelEdicion.setLayout(new GridBagLayout());
        GridBagConstraints gbc_panelEdicion = new GridBagConstraints();
        gbc_panelEdicion.insets = new Insets(5, 50, 5, 50);
        gbc_panelEdicion.fill = GridBagConstraints.BOTH;
        gbc_panelEdicion.gridx = 0;
        gbc_panelEdicion.gridy = 2;
        gbc_panelEdicion.gridwidth = 2;
        gbc_panelEdicion.weightx = 1.0;
        gbc_panelEdicion.weighty = 1.0;
        panelContenedor.add(panelEdicion, gbc_panelEdicion);
        panelEdicion.setVisible(false);

        // Eliminamos el comboBox ya que no lo usaremos más
        comboBoxUsuarios = new JComboBox<>();
        comboBoxUsuarios.setVisible(false);

        // Inicializamos los componentes de edición
        lblDatosUsuario = new JLabel("Modificar Datos del Usuario");
        lblDatosUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatosUsuario.setFont(lblDatosUsuario.getFont().deriveFont(14f));
        
        lblEmail = new JLabel("Email:");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldEmail = new JTextField();
        textFieldEmail.setEditable(false);
        textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldNombre = new JTextField();
        textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblNickname = new JLabel("Nickname:");
        lblNickname.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldNickname = new JTextField();
        textFieldNickname.setEditable(false);
        textFieldNickname.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel para campos de Asistente
        panelAsistente = new JPanel();
        panelAsistente.setLayout(new GridBagLayout());
        
        lblApellido = new JLabel("Apellido:");
        lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
        
        textFieldApellido = new JTextField();
        textFieldApellido.setColumns(15);
        textFieldApellido.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel para campos de Organizador
        panelOrganizador = new JPanel();
        panelOrganizador.setLayout(new GridBagLayout());
        
        lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        
        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setColumns(15);
        textFieldDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblUrl = new JLabel("URL:");
        lblUrl.setHorizontalAlignment(SwingConstants.CENTER);
        
        textFieldUrl = new JTextField();
        textFieldUrl.setColumns(15);
        textFieldUrl.setHorizontalAlignment(SwingConstants.CENTER);

        // Botón Guardar Cambios
        btnFinalizar = new JButton("Guardar Cambios");
        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!modoSeleccion) {
                    guardarCambios();
                }
            }
        });
        GridBagConstraints gbc_btnFinalizar = new GridBagConstraints();
        gbc_btnFinalizar.insets = new Insets(15, 5, 15, 5);
        gbc_btnFinalizar.gridx = 0;
        gbc_btnFinalizar.gridy = 3;
        gbc_btnFinalizar.gridwidth = 2;
        panelContenedor.add(btnFinalizar, gbc_btnFinalizar);
        btnFinalizar.setVisible(false);
    }

    // Método para cargar usuarios desde el controlador
    public void cargarUsuarios() {
        DefaultListModel<DataUsuario> model = new DefaultListModel<>();
        try {
            DataUsuario[] usuarios = controlUsr.getUsuarios();
            for (DataUsuario usuario : usuarios) {
                model.addElement(usuario);
            }
            listUsuarios.setModel(model);
            
            // Centrar el texto en la lista
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
                        label.setHorizontalAlignment(SwingConstants.CENTER);
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
            
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, 
                "No hay usuarios registrados en el sistema", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
    }

    private void cambiarAModoEdicion() {
        modoSeleccion = false;
        
        // Obtener el usuario seleccionado
        DataUsuario usuarioSeleccionado = listUsuarios.getSelectedValue();
        
        if (usuarioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, 
                "No se ha seleccionado ningún usuario", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Guardar referencia del usuario original
        usuarioOriginal = usuarioSeleccionado;
        
        try {
            // Determinar el tipo de usuario
            tipoUsuario = controlUsr.obtenerTipoUsuario(usuarioSeleccionado.getEmail());
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Ocultar elementos de selección
        lblSeleccionarUsuario.setVisible(false);
        scrollPane.setVisible(false);
        
        // Mostrar panel de edición
        panelEdicion.setVisible(true);
        
        // Limpiar panel de edición
        panelEdicion.removeAll();
        
        // Configurar layout para panel de edición centrado
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 200, 0}; // Columnas centradas
        gbl.rowHeights = new int[]{40, 30, 30, 30, 30, 30, 0};
        gbl.columnWeights = new double[]{1.0, 0.0, 1.0}; // Columnas laterales para centrar
        gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelEdicion.setLayout(gbl);
        
        // Añadir título centrado
        GridBagConstraints gbc_lblDatos = new GridBagConstraints();
        gbc_lblDatos.insets = new Insets(10, 5, 20, 5);
        gbc_lblDatos.gridx = 0;
        gbc_lblDatos.gridy = 0;
        gbc_lblDatos.gridwidth = 3;
        gbc_lblDatos.fill = GridBagConstraints.HORIZONTAL;
        panelEdicion.add(lblDatosUsuario, gbc_lblDatos);
        
        // Añadir campos comunes centrados
        int row = 1;
        
        // Email
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.insets = new Insets(5, 5, 5, 5);
        gbc_lblEmail.gridx = 1;
        gbc_lblEmail.gridy = row;
        panelEdicion.add(lblEmail, gbc_lblEmail);
        
        GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
        gbc_textFieldEmail.insets = new Insets(5, 5, 5, 5);
        gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldEmail.gridx = 1;
        gbc_textFieldEmail.gridy = row + 1;
        panelEdicion.add(textFieldEmail, gbc_textFieldEmail);
        row += 2;
        
        // Nombre
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(5, 5, 5, 5);
        gbc_lblNombre.gridx = 1;
        gbc_lblNombre.gridy = row;
        panelEdicion.add(lblNombre, gbc_lblNombre);
        
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.insets = new Insets(5, 5, 5, 5);
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = row + 1;
        panelEdicion.add(textFieldNombre, gbc_textFieldNombre);
        row += 2;
        
        // Nickname
        GridBagConstraints gbc_lblNickname = new GridBagConstraints();
        gbc_lblNickname.insets = new Insets(5, 5, 5, 5);
        gbc_lblNickname.gridx = 1;
        gbc_lblNickname.gridy = row;
        panelEdicion.add(lblNickname, gbc_lblNickname);
        
        GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
        gbc_textFieldNickname.insets = new Insets(5, 5, 5, 5);
        gbc_textFieldNickname.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNickname.gridx = 1;
        gbc_textFieldNickname.gridy = row + 1;
        panelEdicion.add(textFieldNickname, gbc_textFieldNickname);
        row += 2;
        
        // Añadir campos específicos según el tipo de usuario
        if ("Asistente".equals(tipoUsuario)) {
            // Panel de asistente
            panelAsistente.removeAll();
            panelAsistente.setLayout(new GridBagLayout());
            
            GridBagConstraints gbc_lblApellido = new GridBagConstraints();
            gbc_lblApellido.insets = new Insets(5, 5, 5, 5);
            gbc_lblApellido.gridx = 0;
            gbc_lblApellido.gridy = 0;
            panelAsistente.add(lblApellido, gbc_lblApellido);
            
            GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
            gbc_textFieldApellido.insets = new Insets(5, 5, 5, 5);
            gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldApellido.gridx = 0;
            gbc_textFieldApellido.gridy = 1;
            panelAsistente.add(textFieldApellido, gbc_textFieldApellido);
            
            GridBagConstraints gbc_panelAsistente = new GridBagConstraints();
            gbc_panelAsistente.insets = new Insets(10, 5, 5, 5);
            gbc_panelAsistente.fill = GridBagConstraints.HORIZONTAL;
            gbc_panelAsistente.gridx = 1;
            gbc_panelAsistente.gridy = row;
            panelEdicion.add(panelAsistente, gbc_panelAsistente);
            
            cargarDatosAsistente(usuarioSeleccionado.getEmail());
        } else if ("Organizador".equals(tipoUsuario)) {
            // Panel de organizador
            panelOrganizador.removeAll();
            panelOrganizador.setLayout(new GridBagLayout());
            
            // Descripción
            GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
            gbc_lblDescripcion.insets = new Insets(5, 5, 5, 5);
            gbc_lblDescripcion.gridx = 0;
            gbc_lblDescripcion.gridy = 0;
            panelOrganizador.add(lblDescripcion, gbc_lblDescripcion);
            
            GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
            gbc_textFieldDescripcion.insets = new Insets(5, 5, 5, 5);
            gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldDescripcion.gridx = 0;
            gbc_textFieldDescripcion.gridy = 1;
            panelOrganizador.add(textFieldDescripcion, gbc_textFieldDescripcion);
            
            // URL
            GridBagConstraints gbc_lblUrl = new GridBagConstraints();
            gbc_lblUrl.insets = new Insets(5, 5, 5, 5);
            gbc_lblUrl.gridx = 0;
            gbc_lblUrl.gridy = 2;
            panelOrganizador.add(lblUrl, gbc_lblUrl);
            
            GridBagConstraints gbc_textFieldUrl = new GridBagConstraints();
            gbc_textFieldUrl.insets = new Insets(5, 5, 5, 5);
            gbc_textFieldUrl.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldUrl.gridx = 0;
            gbc_textFieldUrl.gridy = 3;
            panelOrganizador.add(textFieldUrl, gbc_textFieldUrl);
            
            GridBagConstraints gbc_panelOrganizador = new GridBagConstraints();
            gbc_panelOrganizador.insets = new Insets(10, 5, 5, 5);
            gbc_panelOrganizador.fill = GridBagConstraints.HORIZONTAL;
            gbc_panelOrganizador.gridx = 1;
            gbc_panelOrganizador.gridy = row;
            panelEdicion.add(panelOrganizador, gbc_panelOrganizador);
            
            cargarDatosOrganizador(usuarioSeleccionado.getEmail());
        }
        
        // Cargar datos comunes
        textFieldEmail.setText(usuarioSeleccionado.getEmail());
        textFieldNombre.setText(usuarioSeleccionado.getNombre());
        textFieldNickname.setText(usuarioSeleccionado.getNickname());
        
        // Mostrar botón de guardar
        btnFinalizar.setVisible(true);
        
        // Centrar el botón
        GridBagConstraints gbc_btnFinalizar = new GridBagConstraints();
        gbc_btnFinalizar.insets = new Insets(15, 5, 15, 5);
        gbc_btnFinalizar.gridx = 1;
        gbc_btnFinalizar.gridy = row + 2;
        panelContenedor.add(btnFinalizar, gbc_btnFinalizar);
        
        // Actualizar panel
        panelEdicion.revalidate();
        panelEdicion.repaint();
    }

    private void cargarDatosAsistente(String email) {
        Asistente a = controlUsr.getAsistente(email);
        String apellido = a.getApellido();
        textFieldApellido.setText(apellido);
    }

    private void cargarDatosOrganizador(String email) {
        Organizador o = controlUsr.getOrganizador(email);
        String descripcion = o.getDescripcion();
        String url = o.getUrl();
        textFieldDescripcion.setText(descripcion);
        textFieldUrl.setText(url);       
    }

    private void cambiarAModoSeleccion() {
        modoSeleccion = true;
        
        // Mostrar elementos de selección
        lblSeleccionarUsuario.setVisible(true);
        scrollPane.setVisible(true);
        
        // Ocultar panel de edición
        panelEdicion.setVisible(false);
        
        // Ocultar botón de guardar
        btnFinalizar.setVisible(false);
        
        // Limpiar campos
        limpiarCampos();
        
        // Deseleccionar usuario en la lista
        listUsuarios.clearSelection();
    }

    private void guardarCambios() {
        // Validar campos comunes
        if (textFieldNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El nombre no puede estar vacío", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar campos específicos según el tipo de usuario
        if ("Asistente".equals(tipoUsuario)) {
            if (textFieldApellido.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "El apellido no puede estar vacío", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if ("Organizador".equals(tipoUsuario)) {
            if (textFieldDescripcion.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "La descripción no puede estar vacía", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (textFieldUrl.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "La URL no puede estar vacía", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        try {
            // Llamar al método correspondiente según el tipo de usuario
            if ("Asistente".equals(tipoUsuario)) {
                controlUsr.modificarAsistente(
                    usuarioOriginal.getEmail(),
                    textFieldNombre.getText().trim(),
                    textFieldApellido.getText().trim()
                );
            } else if ("Organizador".equals(tipoUsuario)) {
                controlUsr.modificarOrganizador(
                    usuarioOriginal.getEmail(),
                    textFieldNombre.getText().trim(),
                    textFieldDescripcion.getText().trim(),
                    textFieldUrl.getText().trim()
                );
            }
            
            JOptionPane.showMessageDialog(this, 
                "Usuario modificado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarYCerrar();
            
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        textFieldEmail.setText("");
        textFieldNombre.setText("");
        textFieldNickname.setText("");
        textFieldApellido.setText("");
        textFieldDescripcion.setText("");
        textFieldUrl.setText("");
        usuarioOriginal = null;
        tipoUsuario = null;
    }

    private void limpiarYCerrar() {
        limpiarCampos();
        cambiarAModoSeleccion();
        setVisible(false);
    }
}