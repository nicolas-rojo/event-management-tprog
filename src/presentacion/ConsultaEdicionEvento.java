package presentacion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import logica.EdicionEvento;
import logica.interfaces.IEventos;

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
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panel central con listas (tipos de registros y patrocinios)
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 0));

     // Detalles en grid (etiquetas + campos) como pidio el profe
        JPanel panelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //Nombre:
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelCampos.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(); txtNombre.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtNombre, gbc);
        
        //Sigla
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelCampos.add(new JLabel("Sigla:"), gbc);
        txtSigla = new JTextField(); txtSigla.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtSigla, gbc);
        
        //Fecha Inicio:
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelCampos.add(new JLabel("Fecha Inicio:"), gbc);
        txtFechaIni = new JTextField(); txtFechaIni.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtFechaIni, gbc);
        
        //Fecha Fin:
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelCampos.add(new JLabel("Fecha Fin:"), gbc);
        txtFechaFin = new JTextField(); txtFechaFin.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtFechaFin, gbc);
        
        //Fecha Alta:
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        panelCampos.add(new JLabel("Fecha Alta:"), gbc);
        txtFechaAlta = new JTextField();txtFechaAlta.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtFechaAlta, gbc);
        
        //Ciudad:
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        panelCampos.add(new JLabel("Ciudad:"), gbc);
        txtCiudad = new JTextField(); txtCiudad.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtCiudad, gbc);
        
        //Pais:
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0;
        panelCampos.add(new JLabel("Pais:"), gbc);
        txtPais = new JTextField(); txtPais.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtPais, gbc);
        
        //Organizador:
        gbc.gridx = 0; gbc.gridy = 7; gbc.weightx = 0;
        panelCampos.add(new JLabel("Pais:"), gbc);
        txtOrganizador = new JTextField(); txtOrganizador.setEditable(false);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelCampos.add(txtOrganizador, gbc);
        
        // Lista de tipos de registros
        listTiposRegistros = new JList<>(edicion.getTRegistro().toArray(new String[0]));
        listTiposRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTiposRegistros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String tipo = listTiposRegistros.getSelectedValue();
                    if (tipo != null) {
                        abrirConsultaTipoRegistro(ICE);
                    }
                }
            }
        });
        panelCentral.add(new JScrollPane(listTiposRegistros));

        // Lista de patrocinios:
        listPatrocinios = new JList<>(edicion.getPatrocinios().toArray(new String[0]));
        listPatrocinios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPatrocinios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String patrocinio = listPatrocinios.getSelectedValue();
                    if (patrocinio != null) {
                        //abrirConsultaPatrocinio(patrocinio);
                    }
                }
            }
        });
        panelCentral.add(new JScrollPane(listPatrocinios));

        //Lista de Registros:
        listRegistros = new JList<>(edicion.getRegistrosInfo().toArray(new String[0]));
        listRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelCentral.add(new JScrollPane(listRegistros));

        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> limpiarYCerrar());
        getContentPane().add(btnCerrar, BorderLayout.SOUTH);
    }
    private void limpiarYCerrar() {
        setVisible(false);
    }
    private void abrirConsultaTipoRegistro(/*String tipo, */IEventos ICE) {
        ConsultaTipoRegistro ventanaTipo = new ConsultaTipoRegistro(ICE);
        getParent().add(ventanaTipo);
        ventanaTipo.setVisible(true);
    }
}