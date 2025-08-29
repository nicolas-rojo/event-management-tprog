package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import excepciones.UsuarioNoExisteException;
import logica.datatypes.*;
import logica.interfaces.IUsuario;
import java.awt.Font;

@SuppressWarnings("serial")
public class ModificarUsuario extends JInternalFrame {

    // Componentes de la interfaz
    private JList<DataUsuario> listUsuarios;
    private JLabel lblTitulo;
    private JLabel lblListaTitulo;
    private JLabel lblDetallesTitulo;
    private JScrollPane scrollPaneLista;
    
    // Panel de detalles y edición
    private JPanel panelDetalles;
    private JScrollPane scrollPaneDetalles;
    
    // Campos de edición comunes
    private JTextField textFieldNombre;
    private JTextField textFieldNickname;
    private JTextField textFieldEmail;
    private JLabel lblEmail;
    private JLabel lblNombre;
    private JLabel lblNickname;
    
    // Campos específicos para Asistente
    private JTextField textFieldApellido;
    private JTextField textFieldFechaNac;
    private JLabel lblApellido;
    private JLabel lblFechaNac;
    
    // Campos específicos para Organizador
    private JTextField textFieldDescripcion;
    private JTextField textFieldUrl;
    private JLabel lblDescripcion;
    private JLabel lblUrl;
    
    // Botones
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnCerrar;
    
    // Controlador y variables de estado
    private IUsuario controlUsr;
    private DataUsuario usuarioSeleccionado;
    private String tipoUsuario;
    private boolean editandoUsuario = false;

    public ModificarUsuario(IUsuario icu) {
        controlUsr = icu;
        
        // Propiedades del JInternalFrame
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Usuario");
        setBounds(30, 30, 900, 600);
        
        // Título principal centrado
        lblTitulo = new JLabel("Modificar Datos de Usuario");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        getContentPane().add(lblTitulo, BorderLayout.NORTH);

        // Panel principal para dividir la ventana en lista y detalles
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2, 15, 0));
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        // Panel para la lista de usuarios (lado izquierdo)
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setPreferredSize(new Dimension(300, 0));
        
        // Título para la lista de usuarios
        lblListaTitulo = new JLabel("Seleccione un usuario:");
        lblListaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblListaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
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

        // Panel para los detalles del usuario (lado derecho)
        panelDetalles = new JPanel(new BorderLayout());
        
        // Título para los detalles
        lblDetallesTitulo = new JLabel("Datos del Usuario:");
        lblDetallesTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblDetallesTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelDetalles.add(lblDetallesTitulo, BorderLayout.NORTH);
        
        // Panel de contenido con scroll para los campos de edición
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridBagLayout());
        scrollPaneDetalles = new JScrollPane(panelContenido);
        scrollPaneDetalles.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneDetalles.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetalles.add(scrollPaneDetalles, BorderLayout.CENTER);
        
        // Inicializar componentes de edición
        inicializarComponentesEdicion(panelContenido);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
        btnGuardar.setEnabled(false);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelarEdicion();
            }
        });
        btnCancelar.setEnabled(false);
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        panelDetalles.add(panelBotones, BorderLayout.SOUTH);
        
        panelPrincipal.add(panelDetalles);

        // Botón para cerrar la ventana
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarYCerrar();
            }
        });
        getContentPane().add(btnCerrar, BorderLayout.SOUTH);
    }

    private void inicializarComponentesEdicion(JPanel panelContenido) {
        // Solo inicializar los componentes, no agregarlos al panel todavía
        lblEmail = new JLabel("Email:");
        textFieldEmail = new JTextField(20);
        textFieldEmail.setEditable(false);
        textFieldEmail.setBackground(getBackground());
        
        lblNombre = new JLabel("Nombre:");
        textFieldNombre = new JTextField(20);
        
        lblNickname = new JLabel("Nickname:");
        textFieldNickname = new JTextField(20);
        textFieldNickname.setEditable(false);
        textFieldNickname.setBackground(getBackground());
        
        // Campos específicos para Asistente
        lblApellido = new JLabel("Apellido:");
        textFieldApellido = new JTextField(20);
        
        lblFechaNac = new JLabel("Fecha de Nacimiento:");
        textFieldFechaNac = new JTextField(20);
        textFieldFechaNac.setEditable(false);
        textFieldFechaNac.setBackground(getBackground());
        
        // Campos específicos para Organizador
        lblDescripcion = new JLabel("Descripción:");
        textFieldDescripcion = new JTextField(20);
        
        lblUrl = new JLabel("URL:");
        textFieldUrl = new JTextField(20);
    }
    
    // Método para cargar usuarios en la lista
    public void cargarUsuarios() {
        // Desactivar listeners temporalmente
        ListSelectionListener[] listeners = listUsuarios.getListSelectionListeners();
        for (ListSelectionListener listener : listeners) {
            listUsuarios.removeListSelectionListener(listener);
        }
        
        try {
            DataUsuario[] usuarios = controlUsr.getUsuarios();
            if (usuarios != null && usuarios.length > 0) {
                DefaultListModel<DataUsuario> model = new DefaultListModel<>();
                for (DataUsuario usuario : usuarios) {
                    model.addElement(usuario);
                }
                listUsuarios.setModel(model);
                
                // Configurar renderizador personalizado
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
            JOptionPane.showMessageDialog(this, 
                "No hay usuarios registrados en el sistema.", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        } finally {
            // Reactivar listeners
            for (ListSelectionListener listener : listeners) {
                listUsuarios.addListSelectionListener(listener);
            }
        }
    }

    // Método para mostrar detalles del usuario seleccionado
    private void mostrarDetallesUsuario() {
        usuarioSeleccionado = listUsuarios.getSelectedValue();
        
        // Verificar si hay un usuario seleccionado
        if (usuarioSeleccionado == null) {
            // Limpiar panel de detalles si no hay selección
            scrollPaneDetalles.setViewportView(new JPanel());
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            editandoUsuario = false;
            return;
        }
        
        try {
            tipoUsuario = controlUsr.getTipoUsuario(usuarioSeleccionado.getEmail());
            
            // Recrear el panel de contenido con los campos apropiados
            JPanel panelContenido = new JPanel(new GridBagLayout());
            
            int row = 0;
            
            // Campos comnes
            // Email
            GridBagConstraints gbcLabelEmail = new GridBagConstraints();
            gbcLabelEmail.insets = new Insets(10, 10, 5, 10);
            gbcLabelEmail.anchor = GridBagConstraints.WEST;
            gbcLabelEmail.gridx = 0;
            gbcLabelEmail.gridy = row;
            panelContenido.add(lblEmail, gbcLabelEmail);
            
            textFieldEmail.setText(usuarioSeleccionado.getEmail());
            GridBagConstraints gbcFieldEmail = new GridBagConstraints();
            gbcFieldEmail.insets = new Insets(10, 10, 5, 10);
            gbcFieldEmail.anchor = GridBagConstraints.WEST;
            gbcFieldEmail.gridx = 1;
            gbcFieldEmail.gridy = row++;
            gbcFieldEmail.fill = GridBagConstraints.HORIZONTAL;
            gbcFieldEmail.weightx = 1.0;
            panelContenido.add(textFieldEmail, gbcFieldEmail);
            
            // Nombre
            GridBagConstraints gbcLabelNombre = new GridBagConstraints();
            gbcLabelNombre.insets = new Insets(10, 10, 5, 10);
            gbcLabelNombre.anchor = GridBagConstraints.WEST;
            gbcLabelNombre.gridx = 0;
            gbcLabelNombre.gridy = row;
            panelContenido.add(lblNombre, gbcLabelNombre);
            
            textFieldNombre.setText(usuarioSeleccionado.getNombre());
            GridBagConstraints gbcFieldNombre = new GridBagConstraints();
            gbcFieldNombre.insets = new Insets(10, 10, 5, 10);
            gbcFieldNombre.anchor = GridBagConstraints.WEST;
            gbcFieldNombre.gridx = 1;
            gbcFieldNombre.gridy = row++;
            gbcFieldNombre.fill = GridBagConstraints.HORIZONTAL;
            gbcFieldNombre.weightx = 1.0;
            panelContenido.add(textFieldNombre, gbcFieldNombre);
            
            // Nickname
            GridBagConstraints gbcLabelNickname = new GridBagConstraints();
            gbcLabelNickname.insets = new Insets(10, 10, 5, 10);
            gbcLabelNickname.anchor = GridBagConstraints.WEST;
            gbcLabelNickname.gridx = 0;
            gbcLabelNickname.gridy = row;
            panelContenido.add(lblNickname, gbcLabelNickname);
            
            textFieldNickname.setText(usuarioSeleccionado.getNickname());
            GridBagConstraints gbcFieldNickname = new GridBagConstraints();
            gbcFieldNickname.insets = new Insets(10, 10, 5, 10);
            gbcFieldNickname.anchor = GridBagConstraints.WEST;
            gbcFieldNickname.gridx = 1;
            gbcFieldNickname.gridy = row++;
            gbcFieldNickname.fill = GridBagConstraints.HORIZONTAL;
            gbcFieldNickname.weightx = 1.0;
            panelContenido.add(textFieldNickname, gbcFieldNickname);
            
            // Campos específicos según el tipo de usuario
            if ("Asistente".equals(tipoUsuario)) {
                DataAsistente asistente = controlUsr.getAsistente(usuarioSeleccionado.getEmail());
                
                // Apellido
                GridBagConstraints gbcLabelApellido = new GridBagConstraints();
                gbcLabelApellido.insets = new Insets(10, 10, 5, 10);
                gbcLabelApellido.anchor = GridBagConstraints.WEST;
                gbcLabelApellido.gridx = 0;
                gbcLabelApellido.gridy = row;
                panelContenido.add(lblApellido, gbcLabelApellido);
                
                textFieldApellido.setText(asistente.getApellido());
                GridBagConstraints gbcFieldApellido = new GridBagConstraints();
                gbcFieldApellido.insets = new Insets(10, 10, 5, 10);
                gbcFieldApellido.anchor = GridBagConstraints.WEST;
                gbcFieldApellido.gridx = 1;
                gbcFieldApellido.gridy = row++;
                gbcFieldApellido.fill = GridBagConstraints.HORIZONTAL;
                gbcFieldApellido.weightx = 1.0;
                panelContenido.add(textFieldApellido, gbcFieldApellido);
                
                // Fecha de Nacimiento (no editable)
                GridBagConstraints gbcLabelFecha = new GridBagConstraints();
                gbcLabelFecha.insets = new Insets(10, 10, 5, 10);
                gbcLabelFecha.anchor = GridBagConstraints.WEST;
                gbcLabelFecha.gridx = 0;
                gbcLabelFecha.gridy = row;
                panelContenido.add(lblFechaNac, gbcLabelFecha);
                
                textFieldFechaNac.setText(asistente.getFechaNac().toString());
                GridBagConstraints gbcFieldFecha = new GridBagConstraints();
                gbcFieldFecha.insets = new Insets(10, 10, 5, 10);
                gbcFieldFecha.anchor = GridBagConstraints.WEST;
                gbcFieldFecha.gridx = 1;
                gbcFieldFecha.gridy = row++;
                gbcFieldFecha.fill = GridBagConstraints.HORIZONTAL;
                gbcFieldFecha.weightx = 1.0;
                panelContenido.add(textFieldFechaNac, gbcFieldFecha);
                
            } else if ("Organizador".equals(tipoUsuario)) {
                DataOrganizador organizador = controlUsr.getOrganizador(usuarioSeleccionado.getEmail());
                
                // Descripción
                GridBagConstraints gbcLabelDesc = new GridBagConstraints();
                gbcLabelDesc.insets = new Insets(10, 10, 5, 10);
                gbcLabelDesc.anchor = GridBagConstraints.WEST;
                gbcLabelDesc.gridx = 0;
                gbcLabelDesc.gridy = row;
                panelContenido.add(lblDescripcion, gbcLabelDesc);
                
                textFieldDescripcion.setText(organizador.getDescripcion());
                GridBagConstraints gbcFieldDesc = new GridBagConstraints();
                gbcFieldDesc.insets = new Insets(10, 10, 5, 10);
                gbcFieldDesc.anchor = GridBagConstraints.WEST;
                gbcFieldDesc.gridx = 1;
                gbcFieldDesc.gridy = row++;
                gbcFieldDesc.fill = GridBagConstraints.HORIZONTAL;
                gbcFieldDesc.weightx = 1.0;
                panelContenido.add(textFieldDescripcion, gbcFieldDesc);
                
                // URL
                GridBagConstraints gbcLabelUrl = new GridBagConstraints();
                gbcLabelUrl.insets = new Insets(10, 10, 5, 10);
                gbcLabelUrl.anchor = GridBagConstraints.WEST;
                gbcLabelUrl.gridx = 0;
                gbcLabelUrl.gridy = row;
                panelContenido.add(lblUrl, gbcLabelUrl);
                
                textFieldUrl.setText(organizador.getUrl());
                GridBagConstraints gbcFieldUrl = new GridBagConstraints();
                gbcFieldUrl.insets = new Insets(10, 10, 5, 10);
                gbcFieldUrl.anchor = GridBagConstraints.WEST;
                gbcFieldUrl.gridx = 1;
                gbcFieldUrl.gridy = row++;
                gbcFieldUrl.fill = GridBagConstraints.HORIZONTAL;
                gbcFieldUrl.weightx = 1.0;
                panelContenido.add(textFieldUrl, gbcFieldUrl);
            }
            
            // Reemplazar el contenido del scroll pane
            scrollPaneDetalles.setViewportView(panelContenido);
            
            // Habilitar botones
            btnGuardar.setEnabled(true);
            btnCancelar.setEnabled(true);
            editandoUsuario = true;
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, 
                "Error: No se pudo obtener la información del usuario.\n" + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambios() {
        if (usuarioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, 
                "No hay ningún usuario seleccionado", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar campos comunes
        if (textFieldNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El nombre no puede estar vacío", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Guardar cambios según el tipo de usuario
            if ("Asistente".equals(tipoUsuario)) {
                if (textFieldApellido.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "El apellido no puede estar vacío", 
                        "Error de validación", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                controlUsr.modificarAsistente(
                    usuarioSeleccionado.getEmail(),
                    textFieldNombre.getText().trim(),
                    textFieldApellido.getText().trim()
                );
                
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
                
                controlUsr.modificarOrganizador(
                    usuarioSeleccionado.getEmail(),
                    textFieldNombre.getText().trim(),
                    textFieldDescripcion.getText().trim(),
                    textFieldUrl.getText().trim()
                );
            }
            
            JOptionPane.showMessageDialog(this, 
                "Usuario modificado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Recargar la lista de usuarios para reflejar los cambios
            cargarUsuarios();
            
            // Mostrar nuevamente los datos actualizados
            mostrarDetallesUsuario();
            
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelarEdicion() {
        // Volver a cargar los datos originales
        mostrarDetallesUsuario();
    }

    private void limpiarYCerrar() {
        listUsuarios.clearSelection();
        usuarioSeleccionado = null;
        tipoUsuario = null;
        editandoUsuario = false;
        setVisible(false);
    }
}