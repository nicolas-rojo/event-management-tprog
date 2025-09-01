package presentacion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import logica.EdicionEvento;
import logica.interfaces.IEventos;
import logica.TipoRegistro;
import logica.Patrocinio;

@SuppressWarnings("serial")
public class ConsultaEdicionEvento extends JInternalFrame {
    
    private IEventos ctrlEventos;
    private JTextField txtNombre;
    private JTextField txtSigla;
    private JTextField txtFechaIni;
    private JTextField txtFechaFin;
    private JTextField txtFechaAlta;
    private JTextField txtCiudad;
    private JTextField txtPais;
    private JTextField txtOrganizador;
    private JList<String> listTiposRegistros;
    private JList<String> listRegistros;
    private JList<String> listPatrocinios;
    private JButton btnCerrar;
    
    // Nuevos componentes para selección
    private JComboBox<String> comboBoxEventos;
    private JComboBox<String> comboBoxEdiciones;
    private boolean cargandoEdiciones;

    public ConsultaEdicionEvento(IEventos ICE) {
        ctrlEventos = ICE;
        setClosable(true);
        setTitle("Consulta de Edición de Evento");
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de selección (arriba)
        JPanel panelSeleccion = new JPanel(new GridBagLayout());
        GridBagConstraints gbcSelec = new GridBagConstraints();
        gbcSelec.insets = new Insets(5, 5, 5, 5);
        gbcSelec.anchor = GridBagConstraints.WEST;
        
        // Selección de evento
        gbcSelec.gridx = 0; gbcSelec.gridy = 0;
        panelSeleccion.add(new JLabel("Evento:"), gbcSelec);
        
        comboBoxEventos = new JComboBox<>();
        gbcSelec.gridx = 1; gbcSelec.fill = GridBagConstraints.HORIZONTAL; gbcSelec.weightx = 1.0;
        panelSeleccion.add(comboBoxEventos, gbcSelec);
        
        // Selección de edición
        gbcSelec.gridx = 0; gbcSelec.gridy = 1; gbcSelec.weightx = 0;
        panelSeleccion.add(new JLabel("Edición:"), gbcSelec);
        
        comboBoxEdiciones = new JComboBox<>();
        comboBoxEdiciones.setEnabled(false);
        gbcSelec.gridx = 1; gbcSelec.fill = GridBagConstraints.HORIZONTAL; gbcSelec.weightx = 1.0;
        panelSeleccion.add(comboBoxEdiciones, gbcSelec);
        
        panelPrincipal.add(panelSeleccion, BorderLayout.NORTH);

        // Panel de detalles (centro)
        JPanel panelDetalles = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campos de texto (inicialmente vacíos)
        // Nombre
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtNombre, gbc);
        
        // Sigla
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Sigla:"), gbc);
        txtSigla = new JTextField();
        txtSigla.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtSigla, gbc);
        
        // Fecha Inicio:
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Fecha Inicio:"), gbc);
        txtFechaIni = new JTextField();
        txtFechaIni.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtFechaIni, gbc);
        
        // Fecha Fin:
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Fecha Fin:"), gbc);
        txtFechaFin = new JTextField();
        txtFechaFin.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtFechaFin, gbc);
        
        // Fecha Alta:
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Fecha Alta:"), gbc);
        txtFechaAlta = new JTextField();
        txtFechaAlta.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtFechaAlta, gbc);
        
        // Ciudad:
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Ciudad:"), gbc);
        txtCiudad = new JTextField();
        txtCiudad.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtCiudad, gbc);
        
        // Pais:
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Pais:"), gbc);
        txtPais = new JTextField();
        txtPais.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtPais, gbc);
        
        // Organizador:
        gbc.gridx = 0; gbc.gridy = 7; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Organizador:"), gbc);
        txtOrganizador = new JTextField();
        txtOrganizador.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtOrganizador, gbc);

//        panelPrincipal.add(panelDetalles, BorderLayout.CENTER);

        // Panel central con listas
        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 0));
        
        // Lista de tipos de registros
        DefaultListModel<String> modelTiposRegistros = new DefaultListModel<>();
        listTiposRegistros = new JList<>(modelTiposRegistros);
        listTiposRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTiposRegistros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String tipoSeleccionado = listTiposRegistros.getSelectedValue();
                    String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
                    String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
                    if (tipoSeleccionado != null && eventoSeleccionado != null && edicionSeleccionada != null) {
                        abrirConsultaTipoRegistro(eventoSeleccionado, edicionSeleccionada, tipoSeleccionado);
                    }
                }
            }
        });
        JScrollPane scrollTiposRegistros = new JScrollPane(listTiposRegistros);
        JPanel panelTiposRegistros = new JPanel(new BorderLayout());
        panelTiposRegistros.add(new JLabel("Tipos de Registro", SwingConstants.CENTER), BorderLayout.NORTH);
        panelTiposRegistros.add(scrollTiposRegistros, BorderLayout.CENTER);
        panelCentral.add(panelTiposRegistros);

        // Lista de patrocinios
        DefaultListModel<String> modelPatrocinios = new DefaultListModel<>();
        listPatrocinios = new JList<>(modelPatrocinios);
        listPatrocinios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPatrocinios = new JScrollPane(listPatrocinios);
        JPanel panelPatrocinios = new JPanel(new BorderLayout());
        panelPatrocinios.add(new JLabel("Patrocinios", SwingConstants.CENTER), BorderLayout.NORTH);
        panelPatrocinios.add(scrollPatrocinios, BorderLayout.CENTER);
        panelCentral.add(panelPatrocinios);

        // Lista de registros
        DefaultListModel<String> modelRegistros = new DefaultListModel<>();
        listRegistros = new JList<>(modelRegistros);
        listRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollRegistros = new JScrollPane(listRegistros);
        JPanel panelRegistros = new JPanel(new BorderLayout());
        panelRegistros.add(new JLabel("Registros", SwingConstants.CENTER), BorderLayout.NORTH);
        panelRegistros.add(scrollRegistros, BorderLayout.CENTER);
        panelCentral.add(panelRegistros);

        panelPrincipal.add(panelCentral, BorderLayout.SOUTH);

        // Botón cerrar
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> limpiarYCerrar());
        panelPrincipal.add(btnCerrar, BorderLayout.SOUTH);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.add(panelDetalles, BorderLayout.NORTH);
        panelCentro.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        getContentPane().add(panelPrincipal);
        
        // Listeners para los combobox
        comboBoxEventos.addActionListener(e -> {
            String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
            if (eventoSeleccionado != null && !eventoSeleccionado.equals("No hay eventos")) {
                cargarEdicionesEvento(ctrlEventos.listarEdiciones(eventoSeleccionado));
                comboBoxEdiciones.setEnabled(true);
            }
        });
        
        comboBoxEdiciones.addActionListener(e -> {
            if (!cargandoEdiciones) {
                String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
                String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
                if (eventoSeleccionado != null && edicionSeleccionada != null && 
                    !edicionSeleccionada.equals("No hay ediciones")) {
                    cargarDatosEdicion(eventoSeleccionado, edicionSeleccionada);
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
    
    public void cargarDatosEdicion(String evento, String edicion) {
        try {
            EdicionEvento ed = ctrlEventos.obtenerEdicionEvento(evento, edicion);
            if (ed != null) {
                txtNombre.setText(ed.getNombre());
                txtSigla.setText(ed.getSigla());
                txtFechaIni.setText(ed.getFechaIni().format(DateTimeFormatter.ISO_LOCAL_DATE));
                txtFechaFin.setText(ed.getFechaFin().format(DateTimeFormatter.ISO_LOCAL_DATE));
                txtFechaAlta.setText(ed.getFechaAlta().format(DateTimeFormatter.ISO_LOCAL_DATE));
                txtCiudad.setText(ed.getCuidad());
                txtPais.setText(ed.getPais());
                txtOrganizador.setText(ed.getOrganizador().getNickname());
                
                // Cargar tipos de registro
                DefaultListModel<String> modelTiposRegistros = (DefaultListModel<String>) listTiposRegistros.getModel();
                modelTiposRegistros.clear();
                for (String tipo : ed.getTRegistro()) {
                    modelTiposRegistros.addElement(tipo);
                }
                
                // Cargar patrocinios
                DefaultListModel<String> modelPatrocinios = (DefaultListModel<String>) listPatrocinios.getModel();
                modelPatrocinios.clear();
                for (Patrocinio p : ed.getPatrociniosLista()) {
                    modelPatrocinios.addElement(p.getCod() + " - " + p.getNivel());
                }
                
                // Cargar registros
                DefaultListModel<String> modelRegistros = (DefaultListModel<String>) listRegistros.getModel();
                modelRegistros.clear();
                for (String registro : ed.getRegistrosInfo()) {
                    modelRegistros.addElement(registro);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de edición: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarDetallesEdicion(String evento, String edicion) {
        limpiarDatos();
        cargarEventos();
        
        comboBoxEventos.setSelectedItem(evento);
        comboBoxEdiciones.setSelectedItem(edicion);
        
        // Cargar directamente los datos
        cargarDatosEdicion(evento, edicion);
        
        setTitle("Consulta Edición: " + edicion);
    }
    
    public void mostrarDetallesEdicion(String edicion) {
        limpiarDatos();
//        cargarEventos();
        
        String evento = ctrlEventos.eventoTieneEdicion(edicion);
        
        comboBoxEventos.addItem(evento);
        comboBoxEdiciones.addItem(edicion);
        comboBoxEventos.setSelectedItem(evento);
        comboBoxEdiciones.setSelectedItem(edicion);
        
        // Cargar directamente los datos
        cargarDatosEdicion(evento, edicion);
        
        setTitle("Consulta Edición: " + edicion);
    }
    
    private void limpiarDatos() {
        txtNombre.setText("");
        txtSigla.setText("");
        txtFechaIni.setText("");
        txtFechaFin.setText("");
        txtFechaAlta.setText("");
        txtCiudad.setText("");
        txtPais.setText("");
        txtOrganizador.setText("");
        
        DefaultListModel<String> modelTiposRegistros = (DefaultListModel<String>) listTiposRegistros.getModel();
        modelTiposRegistros.clear();
        
        DefaultListModel<String> modelPatrocinios = (DefaultListModel<String>) listPatrocinios.getModel();
        modelPatrocinios.clear();
        
        DefaultListModel<String> modelRegistros = (DefaultListModel<String>) listRegistros.getModel();
        modelRegistros.clear();
    }
    
    private void abrirConsultaTipoRegistro(String evento, String edicion, String tipoRegistro) {
        ConsultaTipoRegistro ventanaTipo = new ConsultaTipoRegistro(ctrlEventos);
        ventanaTipo.mostrarDetallesTipoRegistro(evento, edicion, tipoRegistro);
        getParent().add(ventanaTipo);
        ventanaTipo.setVisible(true);
        limpiarYCerrar();
    }
    
    private void limpiarYCerrar() {
        setVisible(false);
        dispose();
    }
}