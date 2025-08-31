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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.util.List;

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

    // Nuevos componentes para ediciones y registros
    private JList<DataEdicionEvento> listEdiciones;
    private JList<ParEdicionRegistro> listRegistros;
    private JLabel lblEdicionesTitulo;
    private JLabel lblRegistrosTitulo;
    private JPanel panelEdiciones;
    private JPanel panelRegistros;
    private JTabbedPane tabbedPaneDetalles;
    
    // Controlador de eventos para obtener información de ediciones
    private logica.interfaces.IEventos controlEventos;

    public ConsultaUsuario(IUsuario icu, logica.interfaces.IEventos ice) {
        // Se inicializa con los controladores
        controlUsr = icu;
        controlEventos = ice;
        
        // Propiedades del JInternalFrame
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de Usuarios");
        setBounds(30, 30, 1000, 600);
        
        // Título principal centrado
        lblUsuarios = new JLabel("Usuarios Registrados");
        lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblUsuarios, BorderLayout.NORTH);

        // Panel principal para dividir la ventana en lista y detalles
        JSplitPane splitPanePrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPanePrincipal.setDividerLocation(300);
        getContentPane().add(splitPanePrincipal, BorderLayout.CENTER);

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
        
        splitPanePrincipal.setLeftComponent(panelLista);

        // Panel para los detalles del Usuario
        JPanel panelDetalles = new JPanel(new BorderLayout());
        
        // Título para los detalles
        lblDetallesTitulo = new JLabel("Detalles del Usuario:");
        lblDetallesTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDetallesTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelDetalles.add(lblDetallesTitulo, BorderLayout.NORTH);
        
        // Panel de pestañas para información básica, ediciones y registros
        tabbedPaneDetalles = new JTabbedPane();
        
        // Pestaña de información básica
        JPanel panelInfoBasica = new JPanel(new GridBagLayout());
        JScrollPane scrollInfoBasica = new JScrollPane(panelInfoBasica);
        tabbedPaneDetalles.addTab("Información Básica", scrollInfoBasica);
        
        // Inicializar componentes de detalles básicos
        inicializarComponentesDetalles(panelInfoBasica);
        
        // Pestaña para ediciones de organizador
        panelEdiciones = new JPanel(new BorderLayout());
        lblEdicionesTitulo = new JLabel("Ediciones del Organizador:");
        lblEdicionesTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEdicionesTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelEdiciones.add(lblEdicionesTitulo, BorderLayout.NORTH);
        
        listEdiciones = new JList<>();
        listEdiciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollEdiciones = new JScrollPane(listEdiciones);
        panelEdiciones.add(scrollEdiciones, BorderLayout.CENTER);
        
        // Listener para selección de edición
        listEdiciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetallesEdicion();
                }
            }
        });
        
        // Pestaña para registros de asistente
        panelRegistros = new JPanel(new BorderLayout());
        lblRegistrosTitulo = new JLabel("Registros del Asistente:");
        lblRegistrosTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRegistrosTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelRegistros.add(lblRegistrosTitulo, BorderLayout.NORTH);
        
        listRegistros = new JList<>();
        listRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollRegistros = new JScrollPane(listRegistros);
        panelRegistros.add(scrollRegistros, BorderLayout.CENTER);
        
        // Listener para selección de registro
        listRegistros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetallesRegistro();
                }
            }
        });
        
        tabbedPaneDetalles.addTab("Ediciones", panelEdiciones);
        tabbedPaneDetalles.addTab("Registros", panelRegistros);
        
        // Ocultar pestañas inicialmente
        tabbedPaneDetalles.setEnabledAt(1, false);
        tabbedPaneDetalles.setEnabledAt(2, false);
        
        panelDetalles.add(tabbedPaneDetalles, BorderLayout.CENTER);
        splitPanePrincipal.setRightComponent(panelDetalles);

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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        int row = 0;
        
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
        
        // Agregar componentes al panel
        // Nombre
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblNombre, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldNombre, gbc);
        
        // Nickname
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblNickname, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldNickname, gbc);
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblEmail, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldEmail, gbc);
        
        // Tipo
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblTipo, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldTipo, gbc);
        
        // Apellido (inicialmente oculto)
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblApellido, gbc);
        lblApellido.setVisible(false);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldApellido, gbc);
        textFieldApellido.setVisible(false);
        
        // Fecha de Nacimiento (inicialmente oculta)
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblFechaNac, gbc);
        lblFechaNac.setVisible(false);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldFechaNac, gbc);
        textFieldFechaNac.setVisible(false);
        
        // Descripción (inicialmente oculta)
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblDescripcion, gbc);
        lblDescripcion.setVisible(false);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldDescripcion, gbc);
        textFieldDescripcion.setVisible(false);
        
        // URL (inicialmente oculta)
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblUrl, gbc);
        lblUrl.setVisible(false);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldUrl, gbc);
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
            
            // Limpiar campos
            limpiarCamposDetalles();
            
            // Llenar campos básicos
            textFieldNombre.setText(usuarioSeleccionado.getNombre());
            textFieldNickname.setText(usuarioSeleccionado.getNickname());
            textFieldEmail.setText(usuarioSeleccionado.getEmail());
            textFieldTipo.setText(tipo);
            
            // Mostrar/ocultar campos específicos según el tipo de usuario
            if ("Asistente".equals(tipo)) {
                DataAsistente asistente = controlUsr.getAsistente(usuarioSeleccionado.getEmail());
                
                // Mostrar campos de asistente
                lblApellido.setVisible(true);
                textFieldApellido.setVisible(true);
                textFieldApellido.setText(asistente.getApellido());
                
                lblFechaNac.setVisible(true);
                textFieldFechaNac.setVisible(true);
                textFieldFechaNac.setText(asistente.getFechaNac().toString());
                
                // Ocultar campos de organizador
                lblDescripcion.setVisible(false);
                textFieldDescripcion.setVisible(false);
                lblUrl.setVisible(false);
                textFieldUrl.setVisible(false);
                
                // Cargar registros del asistente
                cargarRegistrosAsistente(usuarioSeleccionado.getNickname());
                
                // Configurar pestañas
                tabbedPaneDetalles.setEnabledAt(1, false); // Ediciones deshabilitada
                tabbedPaneDetalles.setEnabledAt(2, true);  // Registros habilitada
                tabbedPaneDetalles.setSelectedIndex(2);    // Ir a pestaña de registros
                
            } else if ("Organizador".equals(tipo)) {
                DataOrganizador organizador = controlUsr.getOrganizador(usuarioSeleccionado.getEmail());
                
                // Mostrar campos de organizador
                lblDescripcion.setVisible(true);
                textFieldDescripcion.setVisible(true);
                textFieldDescripcion.setText(organizador.getDescripcion());
                
                lblUrl.setVisible(true);
                textFieldUrl.setVisible(true);
                textFieldUrl.setText(organizador.getUrl());
                
                // Ocultar campos de asistente
                lblApellido.setVisible(false);
                textFieldApellido.setVisible(false);
                lblFechaNac.setVisible(false);
                textFieldFechaNac.setVisible(false);
                
                // Cargar ediciones del organizador
                cargarEdicionesOrganizador(usuarioSeleccionado.getEmail());
                
                // Configurar pestañas
                tabbedPaneDetalles.setEnabledAt(1, true);  // Ediciones habilitada
                tabbedPaneDetalles.setEnabledAt(2, false); // Registros deshabilitada
                tabbedPaneDetalles.setSelectedIndex(1);    // Ir a pestaña de ediciones
            }
            
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, 
                "Error: No se pudo obtener la información del usuario.\n" + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            limpiarCamposDetalles();
        }
    }
    
    private void cargarEdicionesOrganizador(String emailOrganizador) {
        try {
            // Obtener el nickname del organizador desde el email
            DataUsuario usuarioSeleccionado = listUsuarios.getSelectedValue();
            String nickname = usuarioSeleccionado.getNickname();
            
            // Usar el método de la interfaz IEventos
            DataEdicionEvento[] ediciones = controlEventos.getEdicionesEventoOrganizador(nickname);
            
            if (ediciones != null && ediciones.length > 0) {
                javax.swing.DefaultListModel<DataEdicionEvento> model = new javax.swing.DefaultListModel<>();
                for (DataEdicionEvento edicion : ediciones) {
                    model.addElement(edicion);
                }
                listEdiciones.setModel(model);
                
                // Configurar renderer para mejor visualización
                listEdiciones.setCellRenderer(new javax.swing.ListCellRenderer<DataEdicionEvento>() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(
                            javax.swing.JList<? extends DataEdicionEvento> list, 
                            DataEdicionEvento value, 
                            int index, 
                            boolean isSelected, 
                            boolean cellHasFocus) {
                        
                        JLabel label = new JLabel();
                        if (value != null) {
                            label.setText(value.getNombre() + " - " + value.getSigla());
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
            } else {
                javax.swing.DefaultListModel<DataEdicionEvento> model = new javax.swing.DefaultListModel<>();
                // Crear objeto temporal para mostrar mensaje
                DataEdicionEvento mensajeVacio = new DataEdicionEvento("No hay ediciones asociadas", "", 
                    null, null, null, "", "");
                model.addElement(mensajeVacio);
                listEdiciones.setModel(model);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar ediciones del organizador: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            
            javax.swing.DefaultListModel<DataEdicionEvento> model = new javax.swing.DefaultListModel<>();
            DataEdicionEvento mensajeError = new DataEdicionEvento("Error al cargar ediciones", "", 
                null, null, null, "", "");
            model.addElement(mensajeError);
            listEdiciones.setModel(model);
        }
    }
    
    private void cargarRegistrosAsistente(String nicknameAsistente) {
        try {
            List<ParEdicionRegistro> registros = controlUsr.getRegistrosAsistente(nicknameAsistente);
            
            if (registros != null && !registros.isEmpty()) {
                javax.swing.DefaultListModel<ParEdicionRegistro> model = new javax.swing.DefaultListModel<>();
                for (ParEdicionRegistro registro : registros) {
                    model.addElement(registro);
                }
                listRegistros.setModel(model);
                
                // Configurar renderer para mostrar información más detallada
                listRegistros.setCellRenderer(new javax.swing.ListCellRenderer<ParEdicionRegistro>() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(
                            javax.swing.JList<? extends ParEdicionRegistro> list, 
                            ParEdicionRegistro value, 
                            int index, 
                            boolean isSelected, 
                            boolean cellHasFocus) {
                        
                        JLabel label = new JLabel();
                        if (value != null) {
                            String evento = value.getNombreEvento();
                            String texto = "Edición: " + value.getNombreEdicion();
                            if (evento != null && !evento.equals("N/A")) {
                                texto = "Evento: " + evento + " | " + texto;
                            }
                            if (value.getFechaRegistro() != null) {
                                texto += " | Fecha: " + value.getFechaRegistro();
                            }
                            label.setText(texto);
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
            } else {
                javax.swing.DefaultListModel<ParEdicionRegistro> model = new javax.swing.DefaultListModel<>();
                model.addElement(new ParEdicionRegistro("No hay registros", ""));
                listRegistros.setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar registros del asistente: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            
            javax.swing.DefaultListModel<ParEdicionRegistro> model = new javax.swing.DefaultListModel<>();
            model.addElement(new ParEdicionRegistro("Error al cargar", "registros"));
            listRegistros.setModel(model);
        }
    }
    
    private void mostrarDetallesEdicion() {
        DataEdicionEvento edicionSeleccionada = listEdiciones.getSelectedValue();
        
        if (edicionSeleccionada == null || 
            "No hay ediciones asociadas".equals(edicionSeleccionada.getNombre()) ||
            "Error al cargar ediciones".equals(edicionSeleccionada.getNombre())) {
            return;
        }
        
        try {
            // Mostrar detalles completos de la edición
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("=== DETALLES DE LA EDICIÓN ===\n\n");
            mensaje.append("Nombre: ").append(edicionSeleccionada.getNombre()).append("\n");
            mensaje.append("Sigla: ").append(edicionSeleccionada.getSigla()).append("\n");
            if (edicionSeleccionada.getFechaIni() != null) {
                mensaje.append("Fecha Inicio: ").append(edicionSeleccionada.getFechaIni()).append("\n");
            }
            if (edicionSeleccionada.getFechaFin() != null) {
                mensaje.append("Fecha Fin: ").append(edicionSeleccionada.getFechaFin()).append("\n");
            }
            if (edicionSeleccionada.getFechaAlta() != null) {
                mensaje.append("Fecha Alta: ").append(edicionSeleccionada.getFechaAlta()).append("\n");
            }
            mensaje.append("Ciudad: ").append(edicionSeleccionada.getCiudad()).append("\n");
            mensaje.append("País: ").append(edicionSeleccionada.getPais()).append("\n");
            
            JOptionPane.showMessageDialog(this, 
                mensaje.toString(),
                "Detalles de Edición: " + edicionSeleccionada.getNombre(), 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al obtener detalles de la edición: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarDetallesRegistro() {
        ParEdicionRegistro registroSeleccionado = listRegistros.getSelectedValue();
        
        if (registroSeleccionado == null || 
            "No hay registros".equals(registroSeleccionado.getNombreEdicion()) ||
            "Error al cargar".equals(registroSeleccionado.getNombreEdicion())) {
            return;
        }
        
        try {
            DataUsuario usuarioSeleccionado = listUsuarios.getSelectedValue();
            DataDetalleRegistro detalles = controlUsr.getDetallesRegistro(
                usuarioSeleccionado.getNickname(), registroSeleccionado);
            
            // Mostrar detalles completos del registro
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("=== DETALLES DEL REGISTRO ===\n\n");
            mensaje.append("Edición: ").append(registroSeleccionado.getNombreEdicion()).append("\n");
            if (registroSeleccionado.getFechaRegistro() != null) {
                mensaje.append("Fecha de Registro: ").append(registroSeleccionado.getFechaRegistro()).append("\n");
            }
            mensaje.append("Costo: $").append(String.format("%.2f", detalles.getCosto())).append("\n");
            mensaje.append("Tipo de Registro: ").append(detalles.getTipoRegistro()).append("\n");
            
            JOptionPane.showMessageDialog(this, 
                mensaje.toString(),
                "Detalles de Registro", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al obtener detalles del registro: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCamposDetalles() {
        // Limpiar campos básicos
        textFieldNombre.setText("");
        textFieldNickname.setText("");
        textFieldEmail.setText("");
        textFieldTipo.setText("");
        textFieldApellido.setText("");
        textFieldFechaNac.setText("");
        textFieldDescripcion.setText("");
        textFieldUrl.setText("");
        
        // Limpiar y resetear listas
        javax.swing.DefaultListModel<DataEdicionEvento> modelEdiciones = new javax.swing.DefaultListModel<>();
        DataEdicionEvento mensajePlaceholder = new DataEdicionEvento("Seleccione un organizador para ver ediciones", "", 
            null, null, null, "", "");
        modelEdiciones.addElement(mensajePlaceholder);
        listEdiciones.setModel(modelEdiciones);
        
        javax.swing.DefaultListModel<ParEdicionRegistro> modelRegistros = new javax.swing.DefaultListModel<>();
        modelRegistros.addElement(new ParEdicionRegistro("Seleccione un asistente para ver registros", ""));
        listRegistros.setModel(modelRegistros);
        
        // Deshabilitar pestañas específicas hasta que se seleccione un usuario
        tabbedPaneDetalles.setEnabledAt(1, false); // Ediciones
        tabbedPaneDetalles.setEnabledAt(2, false); // Registros
        tabbedPaneDetalles.setSelectedIndex(0);    // Volver a pestaña de información básica
    }

    // Método para limpiar y cerrar la ventana
    private void limpiarYCerrar() {
        listUsuarios.clearSelection();
        limpiarCamposDetalles();
        setVisible(false);
    }
}