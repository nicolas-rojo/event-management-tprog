package presentacion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import logica.datatypes.DTOEvento;
import logica.EdicionEvento;
import logica.interfaces.IEventos;
import excepciones.EventoNoExisteExcepcion;

@SuppressWarnings("serial")
public class ConsultaEvento extends JInternalFrame {

    // ---- Campos / Variables de instancia ----
    private IEventos controlEvento;

    private JList<DTOEvento> listEventos;
    private DefaultListModel<DTOEvento> modelEventos;
    private JScrollPane scrollPaneLista;

    // Detalles (derecha)
    private JTextField txtNombre;
    private JTextField txtSigla;
    private JTextField txtFechaAlta;
    private JTextArea txtDescripcion;

    private JList<String> listEdiciones;
    private DefaultListModel<String> modelEdiciones;

    private JLabel lblEventos;
    private JLabel lblListaTitulo;
    private JButton btnCerrar;

    // ---- Constructor ----
    public ConsultaEvento(IEventos ice) {
        //Inicializo
        controlEvento = ice;
        // JFrame
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Lista de Eventos");
        setBounds(30, 30, 800, 450);

        lblEventos = new JLabel("Eventos");
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos, BorderLayout.NORTH);

        JPanel panelPrincipal = new JPanel(new BorderLayout(8, 8));
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        lblListaTitulo = new JLabel("Seleccione un Evento:");
        lblListaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lblListaTitulo, BorderLayout.NORTH);
        
        modelEventos = new DefaultListModel<>();
        listEventos = new JList<>(modelEventos);
        listEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Lista de eventos (izquierda)
        listEventos.setCellRenderer(new ListCellRenderer<DTOEvento>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends DTOEvento> list, DTOEvento value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel();
                if (value != null) label.setText(value.getNombre()); // solo nombre
                label.setOpaque(true);
                label.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                label.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                return label;
            }
        });
        scrollPaneLista = new JScrollPane(listEventos);
        panelPrincipal.add(scrollPaneLista, BorderLayout.WEST);
        listEventos.setPreferredSize(new Dimension(320, 0));

        // Listener para abrir detalle de evento en el panel derecho
        listEventos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    DTOEvento eventoSeleccionado = listEventos.getSelectedValue();
                    if (eventoSeleccionado != null) {
                        actualizarDetalles(eventoSeleccionado);
                    } else {
                        actualizarDetalles(null);
                    }
                }
            }
        });

        // ----- DERECHA: Detalles del evento -----
        JPanel panelDer = new JPanel(new BorderLayout(6,6));
        panelDer.add(new JLabel("Detalles del Evento", SwingConstants.CENTER), BorderLayout.NORTH);

        // Detalles en grid (etiquetas + campos) como pidio el profe
        JPanel panelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelCampos.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(); txtNombre.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtNombre, gbc);

        // Sigla
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelCampos.add(new JLabel("Sigla:"), gbc);
        txtSigla = new JTextField(); txtSigla.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtSigla, gbc);

        // Fecha alta
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelCampos.add(new JLabel("Fecha alta:"), gbc);
        txtFechaAlta = new JTextField(); txtFechaAlta.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtFechaAlta, gbc);

        // Descripción (ocupando varias filas) no se bien como se ve pero es editable cuando haya eventos dados de alta
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; gbc.gridwidth = 2;
        panelCampos.add(new JLabel("Descripción:"), gbc);
        // JTextArea
        txtDescripcion = new JTextArea(6, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false);
        // Scrollpane
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        gbc.gridy = 4; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH; 
        panelCampos.add(scrollDescripcion, gbc);


        panelDer.add(panelCampos, BorderLayout.CENTER);

        // Lista de ediciones (abajo)
        modelEdiciones = new DefaultListModel<>();
        listEdiciones = new JList<>(modelEdiciones);
        listEdiciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEdiciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String ed = listEdiciones.getSelectedValue();
                    DTOEvento eventoSeleccionado = listEventos.getSelectedValue();
                    if (ed != null && eventoSeleccionado != null) {
                        try {
                            EdicionEvento edicion = controlEvento.obtenerEdicionEvento(eventoSeleccionado.getNombre(), ed);
                            abrirConsultaEdicion(eventoSeleccionado.getNombre(), ed, controlEvento);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,
                                "No se pudo cargar la edición: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        JPanel panelEdiciones = new JPanel(new BorderLayout());
        panelEdiciones.add(new JLabel("Ediciones", SwingConstants.CENTER), BorderLayout.NORTH);
        panelEdiciones.add(new JScrollPane(listEdiciones), BorderLayout.CENTER);
        panelEdiciones.setPreferredSize(new Dimension(0, 140));

        panelDer.add(panelEdiciones, BorderLayout.SOUTH);

        panelPrincipal.add(panelDer, BorderLayout.CENTER);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> limpiarYCerrar());
        getContentPane().add(btnCerrar, BorderLayout.SOUTH);
    }

    // ---- Métodos auxiliares ----
    private void actualizarDetalles(DTOEvento dto) {
        if (dto == null) {
            txtNombre.setText("");
            txtSigla.setText("");
            txtFechaAlta.setText("");
            txtDescripcion.setText("");
            modelEdiciones.clear();
            return;
        }

        txtNombre.setText(dto.getNombre() != null ? dto.getNombre() : "");
        txtSigla.setText(dto.getSigla() != null ? dto.getSigla() : "");
        txtFechaAlta.setText(dto.getFechaAlta() != null ? dto.getFechaAlta().format(DateTimeFormatter.ISO_LOCAL_DATE) : "");
        txtDescripcion.setText(dto.getDescripcion() != null ? dto.getDescripcion() : "");

        modelEdiciones.clear();
        Set<String> eds = dto.getEdiciones();
        if (eds != null) {
            for (String s : eds) modelEdiciones.addElement(s);
        }
    }

    private void abrirConsultaEdicion(String evento, String ed, IEventos ICE) {
        ConsultaEdicionEvento ventanaEdicion = new ConsultaEdicionEvento(ICE);
        ventanaEdicion.mostrarDetallesEdicion(evento, ed);
        getParent().add(ventanaEdicion);
        ventanaEdicion.setVisible(true);
        limpiarYCerrar();
    }

    public void limpiarYCerrar() {
        listEventos.clearSelection();
        modelEventos.clear(); // 
        actualizarDetalles(null);
        setVisible(false);
    }

    public void cargarEventos() {
        try {
            // Obtengo los eventos desde el controlador
            DTOEvento[] eventos = controlEvento.listarInfoEvento();

            // Limpio el modelo antes de cargar nuevos elementos
            modelEventos.clear();

            // Si hay eventos, los agrego al modelo
            if (eventos != null && eventos.length > 0) {
                for (DTOEvento dto : eventos) {
                    modelEventos.addElement(dto);
                }
            }

        } catch (EventoNoExisteExcepcion ex) {
            // No hay eventos cargados: limpio la lista y muestro mensaje
            modelEventos.clear();
            JOptionPane.showMessageDialog(this,
                    "No hay eventos registrados en el sistema.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            // Cualquier otro error
            JOptionPane.showMessageDialog(this,
                    "Error al cargar eventos: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

