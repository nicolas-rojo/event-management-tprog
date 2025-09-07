package presentacion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import logica.interfaces.IEventos;
import logica.datatypes.DataPatrocinioCompleto;
import logica.datatypes.Nivel;

@SuppressWarnings("serial")
public class ConsultaPatrocinio extends JInternalFrame {
    
    private IEventos ctrlEventos;
    
    // Componentes de selección
    private JComboBox<String> comboBoxEventos;
    private JComboBox<String> comboBoxEdiciones;
    
    // Componentes de lista y detalles
    private JList<String> listPatrocinios;
    private DefaultListModel<String> modelPatrocinios;
    
    // Campos de detalles
    private JTextField txtCodigo;
    private JTextField txtFecha;
    private JTextField txtMonto;
    private JTextField txtNivel;
    private JTextField txtCupo;
    private JTextField txtInstitucion;
    private JTextField txtTipoRegistro;
    
    private JButton btnCerrar;
    
    private boolean cargandoEdiciones;

    public ConsultaPatrocinio(IEventos ICE) {
        ctrlEventos = ICE;
        setClosable(true);
        setTitle("Consulta de Patrocinio");
        setSize(800, 500);
        setLayout(new BorderLayout(10, 10));
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de selección (arriba)
        JPanel panelSeleccion = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Evento
        gbc.gridx = 0; gbc.gridy = 0;
        panelSeleccion.add(new JLabel("Evento:"), gbc);
        
        comboBoxEventos = new JComboBox<>();
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        panelSeleccion.add(comboBoxEventos, gbc);
        
        // Edición
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelSeleccion.add(new JLabel("Edición:"), gbc);
        
        comboBoxEdiciones = new JComboBox<>();
        comboBoxEdiciones.setEnabled(false);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        panelSeleccion.add(comboBoxEdiciones, gbc);
        
        panelPrincipal.add(panelSeleccion, BorderLayout.NORTH);
        
        // Panel central dividido en lista y detalles
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 0));
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        
        // Panel de lista de patrocinios (izquierda)
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setPreferredSize(new Dimension(250, 0));
        
        JLabel lblLista = new JLabel("Patrocinios de la Edición");
        lblLista.setHorizontalAlignment(SwingConstants.CENTER);
        lblLista.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelLista.add(lblLista, BorderLayout.NORTH);
        
        modelPatrocinios = new DefaultListModel<>();
        listPatrocinios = new JList<>(modelPatrocinios);
        listPatrocinios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPatrocinios = new JScrollPane(listPatrocinios);
        panelLista.add(scrollPatrocinios, BorderLayout.CENTER);
        
        panelCentral.add(panelLista);
        
        // Panel de detalles (derecha)
        JPanel panelDetalles = new JPanel(new BorderLayout());
        
        JLabel lblDetalles = new JLabel("Detalles del Patrocinio");
        lblDetalles.setHorizontalAlignment(SwingConstants.CENTER);
        lblDetalles.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelDetalles.add(lblDetalles, BorderLayout.NORTH);
        
        JPanel panelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCampos = new GridBagConstraints();
        gbcCampos.insets = new Insets(5, 5, 5, 5);
        gbcCampos.anchor = GridBagConstraints.WEST;
        gbcCampos.fill = GridBagConstraints.HORIZONTAL;
        
        // Código
        gbcCampos.gridx = 0; gbcCampos.gridy = 0; gbcCampos.weightx = 0;
        panelCampos.add(new JLabel("Código:"), gbcCampos);
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        gbcCampos.gridx = 1; gbcCampos.weightx = 1.0;
        panelCampos.add(txtCodigo, gbcCampos);
        
        // Fecha
        gbcCampos.gridx = 0; gbcCampos.gridy = 1; gbcCampos.weightx = 0;
        panelCampos.add(new JLabel("Fecha:"), gbcCampos);
        txtFecha = new JTextField();
        txtFecha.setEditable(false);
        gbcCampos.gridx = 1; gbcCampos.weightx = 1.0;
        panelCampos.add(txtFecha, gbcCampos);
        
        // Monto
        gbcCampos.gridx = 0; gbcCampos.gridy = 2; gbcCampos.weightx = 0;
        panelCampos.add(new JLabel("Monto:"), gbcCampos);
        txtMonto = new JTextField();
        txtMonto.setEditable(false);
        gbcCampos.gridx = 1; gbcCampos.weightx = 1.0;
        panelCampos.add(txtMonto, gbcCampos);
        
        // Nivel
        gbcCampos.gridx = 0; gbcCampos.gridy = 3; gbcCampos.weightx = 0;
        panelCampos.add(new JLabel("Nivel:"), gbcCampos);
        txtNivel = new JTextField();
        txtNivel.setEditable(false);
        gbcCampos.gridx = 1; gbcCampos.weightx = 1.0;
        panelCampos.add(txtNivel, gbcCampos);
        
        // Cupo
        gbcCampos.gridx = 0; gbcCampos.gridy = 4; gbcCampos.weightx = 0;
        panelCampos.add(new JLabel("Cupo:"), gbcCampos);
        txtCupo = new JTextField();
        txtCupo.setEditable(false);
        gbcCampos.gridx = 1; gbcCampos.weightx = 1.0;
        panelCampos.add(txtCupo, gbcCampos);
        
        // Institución
        gbcCampos.gridx = 0; gbcCampos.gridy = 5; gbcCampos.weightx = 0;
        panelCampos.add(new JLabel("Institución:"), gbcCampos);
        txtInstitucion = new JTextField();
        txtInstitucion.setEditable(false);
        gbcCampos.gridx = 1; gbcCampos.weightx = 1.0;
        panelCampos.add(txtInstitucion, gbcCampos);
        
        // Tipo de Registro
        gbcCampos.gridx = 0; gbcCampos.gridy = 6; gbcCampos.weightx = 0;
        panelCampos.add(new JLabel("Tipo Registro:"), gbcCampos);
        txtTipoRegistro = new JTextField();
        txtTipoRegistro.setEditable(false);
        gbcCampos.gridx = 1; gbcCampos.weightx = 1.0;
        panelCampos.add(txtTipoRegistro, gbcCampos);
        
        JScrollPane scrollCampos = new JScrollPane(panelCampos);
        panelDetalles.add(scrollCampos, BorderLayout.CENTER);
        
        panelCentral.add(panelDetalles);
        
        // Botón cerrar
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> limpiarYCerrar());
        panelPrincipal.add(btnCerrar, BorderLayout.SOUTH);
        
        getContentPane().add(panelPrincipal);
        
        // Listeners
        comboBoxEventos.addActionListener(e -> {
            String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
            if (eventoSeleccionado != null && !eventoSeleccionado.equals("No hay eventos")) {
                cargarEdicionesEvento(ctrlEventos.listarEdiciones(eventoSeleccionado));
                comboBoxEdiciones.setEnabled(true);
                limpiarDatosTotal();
            }
        });
        
        comboBoxEdiciones.addActionListener(e -> {
            if (!cargandoEdiciones) {
                String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
                String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
                if (eventoSeleccionado != null && edicionSeleccionada != null && 
                    !edicionSeleccionada.equals("No hay ediciones")) {
                    cargarPatrocinios(eventoSeleccionado, edicionSeleccionada);
                }
            }
        });
        
        listPatrocinios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    try {
                        String patrocinioSeleccionado = listPatrocinios.getSelectedValue();
                        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
                        String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
                        if (patrocinioSeleccionado != null && eventoSeleccionado != null && edicionSeleccionada != null) {
                            // Extraer el código del patrocinio (formato: "codigo - nivel")
                            String codigo = patrocinioSeleccionado.split(" - ")[0];
                            DataPatrocinioCompleto patrocinio = ctrlEventos.obtenerDTOPatrocinioCompleto(
                                eventoSeleccionado, edicionSeleccionada, codigo);
                            if (patrocinio != null) {
                                mostrarDetallesPatrocinio(patrocinio);
                            } else {
                                limpiarDatos();
                                JOptionPane.showMessageDialog(ConsultaPatrocinio.this, 
                                    "No se encontró información del patrocinio seleccionado", 
                                    "Error", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ConsultaPatrocinio.this, 
                            "Error al cargar detalles del patrocinio: " + ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                        limpiarDatos();
                    }
                }
            }
        });
    }
    
    public void cargarEventos() {
        try {
            comboBoxEventos.removeAllItems();
            List<String> eventos = ctrlEventos.listarEventos();
            if (eventos != null && !eventos.isEmpty()) {
                for (String e : eventos) {
                    comboBoxEventos.addItem(e);
                }
            } else {
                comboBoxEventos.addItem("No hay eventos");
            }
            comboBoxEventos.setSelectedIndex(-1);
            comboBoxEdiciones.setSelectedIndex(-1);
            comboBoxEdiciones.setEnabled(false);
            limpiarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            comboBoxEventos.removeAllItems();
        }
    }
    
    public void cargarEdicionesEvento(List<String> edicionesEvento) {
        try {
            cargandoEdiciones = true;
            comboBoxEdiciones.removeAllItems();
            if (edicionesEvento != null && !edicionesEvento.isEmpty()) {
                for (String ed : edicionesEvento) {
                    comboBoxEdiciones.addItem(ed);
                }
            } else {
                comboBoxEdiciones.addItem("No hay ediciones");
            }
            comboBoxEdiciones.setSelectedIndex(-1);
            cargandoEdiciones = false;
            limpiarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            comboBoxEdiciones.removeAllItems();
        }
    }
    
    public void cargarPatrocinios(String evento, String edicion) {
        try {
            modelPatrocinios.clear();
            
            List<String> patrocinios = ctrlEventos.listarPatrocinios(evento, edicion);
            if (patrocinios != null && !patrocinios.isEmpty()) {
                for (String p : patrocinios) {
                    modelPatrocinios.addElement(p);
                }
            }
            limpiarDatos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar patrocinios: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cargarDatosPatrocinio(String evento, String edicion, String codigo) {
        try {
            DataPatrocinioCompleto patrocinio = ctrlEventos.obtenerDTOPatrocinioCompleto(evento, edicion, codigo);
            if (patrocinio != null) {
                txtCodigo.setText(patrocinio.getCod());
                txtFecha.setText(patrocinio.getFecha().format(DateTimeFormatter.ISO_LOCAL_DATE));
                txtMonto.setText(String.valueOf(patrocinio.getMonto()));
                txtNivel.setText(patrocinio.getNivel().toString());
                txtCupo.setText(String.valueOf(patrocinio.getCtdCupo()));
                txtInstitucion.setText(patrocinio.getInstitucion());
                txtTipoRegistro.setText(patrocinio.getTipoRegistro());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos del patrocinio: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarDetallesPatrocinio(DataPatrocinioCompleto patrocinio) {
        // Llenar campos directamente con el DTO
        txtCodigo.setText(patrocinio.getCod());
        txtFecha.setText(patrocinio.getFecha().format(DateTimeFormatter.ISO_LOCAL_DATE));
        txtMonto.setText(String.valueOf(patrocinio.getMonto()));
        txtNivel.setText(patrocinio.getNivel().toString());
        txtCupo.setText(String.valueOf(patrocinio.getCtdCupo()));
        txtInstitucion.setText(patrocinio.getInstitucion());
        txtTipoRegistro.setText(patrocinio.getTipoRegistro());
        
        setTitle("Consulta Patrocinio: " + patrocinio.getCod());
    }

    // Método auxiliar para cargar y seleccionar patrocinio en la lista
    public void seleccionarPatrocinioEnLista(String evento, String edicion, String codigoPatrocinio) {
        cargarEventos();
        comboBoxEventos.setSelectedItem(evento);
        comboBoxEdiciones.setSelectedItem(edicion);
        cargarPatrocinios(evento, edicion);
        
        // Buscar y seleccionar el patrocinio en la lista
        for (int i = 0; i < modelPatrocinios.getSize(); i++) {
            String item = modelPatrocinios.getElementAt(i);
            if (item.startsWith(codigoPatrocinio + " - ")) {
                listPatrocinios.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void limpiarDatos() {
        txtCodigo.setText("");
        txtFecha.setText("");
        txtMonto.setText("");
        txtNivel.setText("");
        txtCupo.setText("");
        txtInstitucion.setText("");
        txtTipoRegistro.setText("");
    }
    
    private void limpiarDatosTotal() {
    	txtCodigo.setText("");
        txtFecha.setText("");
        txtMonto.setText("");
        txtNivel.setText("");
        txtCupo.setText("");
        txtInstitucion.setText("");
        txtTipoRegistro.setText("");
        modelPatrocinios.clear();
    }
    
    private void limpiarYCerrar() {
        setVisible(false);
        dispose();
    }
}