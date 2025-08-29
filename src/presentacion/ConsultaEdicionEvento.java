package presentacion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import logica.datatypes.DataEdicion;

public class ConsultaEdicionEvento extends JInternalFrame {

    private JTextArea textDetalles;
    private JList<String> listTiposRegistros;
    private JList<String> listPatrocinios;

    public ConsultaEdicionEvento(DataEdicion edicion) {
        setClosable(true);
        setTitle("Edición: " + edicion.getNombre());
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panel superior con detalles generales de la edición
        textDetalles = new JTextArea();
        textDetalles.setEditable(false);
        textDetalles.setLineWrap(true);
        textDetalles.setWrapStyleWord(true);
        //textDetalles.setText(formatearDetalles(edicion));
        add(new JScrollPane(textDetalles), BorderLayout.NORTH);

        // Panel central con listas (tipos de registros y patrocinios)
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 0));

        // Lista de tipos de registros
        /* ---------------------------------------------------------------------------------------
        listTiposRegistros = new JList<>(edicion.getTiposRegistro().toArray(new String[0]));
        listTiposRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTiposRegistros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String tipo = listTiposRegistros.getSelectedValue();
                    if (tipo != null) {
                        abrirConsultaTipoRegistro(tipo);
                    }
                }
            }
        });
        panelCentral.add(new JScrollPane(listTiposRegistros));

        // Lista de patrocinios
        listPatrocinios = new JList<>(edicion.getPatrocinios().toArray(new String[0]));
        listPatrocinios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPatrocinios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String patrocinio = listPatrocinios.getSelectedValue();
                    if (patrocinio != null) {
                        abrirConsultaPatrocinio(patrocinio);
                    }
                }
            }
        });
        panelCentral.add(new JScrollPane(listPatrocinios));

        add(panelCentral, BorderLayout.CENTER);
    -------------------------------------------------------------------------------------------*/
    }
    /*private String formatearDetalles(DataEdicion edicion) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(edicion.getNombre()).append("\n");
        sb.append("Fecha Inicio: ").append(edicion.getFechaInicio()).append("\n");
        sb.append("Fecha Fin: ").append(edicion.getFechaFin()).append("\n");
        sb.append("Organizador: ").append(edicion.getOrganizador()).append("\n");
        return sb.toString();
    }
    
    private void abrirConsultaTipoRegistro(String tipo) {
        ConsultaTipoRegistro ventanaTipo = new ConsultaTipoRegistro(tipo);
        getParent().add(ventanaTipo);
        ventanaTipo.setVisible(true);
    }
    
    private void abrirConsultaPatrocinio(String patrocinio) {
        ConsultaPatrocinio ventanaPatrocinio = new ConsultaPatrocinio(patrocinio);
        getParent().add(ventanaPatrocinio);
        ventanaPatrocinio.setVisible(true);
    }*/
}