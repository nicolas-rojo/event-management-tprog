package presentacion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;

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

    public ConsultaEdicionEvento(String evento, String ed, IEventos ICE) {
        
        ctrlEventos = ICE;
        EdicionEvento edicion = ctrlEventos.obtenerEdicionEvento(evento, ed);
        setClosable(true);
        setTitle("Edición: " + edicion.getNombre());
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de detalles
        JPanel panelDetalles = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Llenar campos con datos
        // Nombre
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(edicion.getNombre());
        txtNombre.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtNombre, gbc);
        
        // Sigla
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Sigla:"), gbc);
        txtSigla = new JTextField(edicion.getSigla());
        txtSigla.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtSigla, gbc);
        
        // Fecha Inicio:
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Fecha Inicio:"), gbc);
        txtFechaIni = new JTextField(edicion.getFechaIni().format(DateTimeFormatter.ISO_LOCAL_DATE));
        txtFechaIni.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtFechaIni, gbc);
        
        // Fecha Fin:
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Fecha Fin:"), gbc);
        txtFechaFin = new JTextField(edicion.getFechaFin().format(DateTimeFormatter.ISO_LOCAL_DATE));
        txtFechaFin.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtFechaFin, gbc);
        
        // Fecha Alta:
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Fecha Alta:"), gbc);
        txtFechaAlta = new JTextField(edicion.getFechaAlta().format(DateTimeFormatter.ISO_LOCAL_DATE));
        txtFechaAlta.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtFechaAlta, gbc);
        
        // Ciudad:
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Ciudad:"), gbc);
        txtCiudad = new JTextField(edicion.getCuidad());
        txtCiudad.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtCiudad, gbc);
        
        // Pais:
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Pais:"), gbc);
        txtPais = new JTextField(edicion.getPais());
        txtPais.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtPais, gbc);
        
        // Organizador: (CORREGIDO - antes decía "Pais")
        gbc.gridx = 0; gbc.gridy = 7; gbc.weightx = 0;
        panelDetalles.add(new JLabel("Organizador:"), gbc);
        txtOrganizador = new JTextField(edicion.getOrganizador().getNickname());
        txtOrganizador.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelDetalles.add(txtOrganizador, gbc);

        panelPrincipal.add(panelDetalles, BorderLayout.NORTH);

        // Panel central con listas
        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 0));
        
        // Lista de tipos de registros
        DefaultListModel<String> modelTiposRegistros = new DefaultListModel<>();
        for (String tipo : edicion.getTRegistro()) {
            modelTiposRegistros.addElement(tipo);
        }
        listTiposRegistros = new JList<>(modelTiposRegistros);
        listTiposRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTiposRegistros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String tipoSeleccionado = listTiposRegistros.getSelectedValue();
                    if (tipoSeleccionado != null) {
                        abrirConsultaTipoRegistro(evento, ed, tipoSeleccionado);
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
        for (Patrocinio p : edicion.getPatrociniosLista()) {
            modelPatrocinios.addElement(p.getCod() + " - " + p.getNivel());
        }
        listPatrocinios = new JList<>(modelPatrocinios);
        listPatrocinios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPatrocinios = new JScrollPane(listPatrocinios);
        JPanel panelPatrocinios = new JPanel(new BorderLayout());
        panelPatrocinios.add(new JLabel("Patrocinios", SwingConstants.CENTER), BorderLayout.NORTH);
        panelPatrocinios.add(scrollPatrocinios, BorderLayout.CENTER);
        panelCentral.add(panelPatrocinios);

        // Lista de registros
        DefaultListModel<String> modelRegistros = new DefaultListModel<>();
        for (String registro : edicion.getRegistrosInfo()) {
            modelRegistros.addElement(registro);
        }
        listRegistros = new JList<>(modelRegistros);
        listRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollRegistros = new JScrollPane(listRegistros);
        JPanel panelRegistros = new JPanel(new BorderLayout());
        panelRegistros.add(new JLabel("Registros", SwingConstants.CENTER), BorderLayout.NORTH);
        panelRegistros.add(scrollRegistros, BorderLayout.CENTER);
        panelCentral.add(panelRegistros);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Botón cerrar
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> limpiarYCerrar());
        panelPrincipal.add(btnCerrar, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
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