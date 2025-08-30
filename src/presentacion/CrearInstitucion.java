package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.interfaces.IInstituciones;
import logica.datatypes.DataInstitucion;

@SuppressWarnings("serial")
public class CrearInstitucion extends JInternalFrame {

    private IInstituciones controlInst;
    private JTextField textFieldNombre;
    private JTextField textFieldSitioWeb;
    private JTextArea textAreaDescripcion;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public CrearInstitucion(IInstituciones iin) {
        controlInst = iin;
        
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Institución");
        setBounds(10, 40, 450, 300);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{120, 200, 100, 0};
        gridBagLayout.rowHeights = new int[]{30, 30, 100, 30, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        // Campo Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.fill = GridBagConstraints.BOTH;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 0;
        getContentPane().add(lblNombre, gbc_lblNombre);

        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 2;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 0;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);

        // Campo Sitio Web
        JLabel lblSitioWeb = new JLabel("Sitio Web:");
        lblSitioWeb.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblSitioWeb = new GridBagConstraints();
        gbc_lblSitioWeb.fill = GridBagConstraints.BOTH;
        gbc_lblSitioWeb.insets = new Insets(0, 0, 5, 5);
        gbc_lblSitioWeb.gridx = 0;
        gbc_lblSitioWeb.gridy = 1;
        getContentPane().add(lblSitioWeb, gbc_lblSitioWeb);

        textFieldSitioWeb = new JTextField();
        GridBagConstraints gbc_textFieldSitioWeb = new GridBagConstraints();
        gbc_textFieldSitioWeb.gridwidth = 2;
        gbc_textFieldSitioWeb.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldSitioWeb.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldSitioWeb.gridx = 1;
        gbc_textFieldSitioWeb.gridy = 1;
        getContentPane().add(textFieldSitioWeb, gbc_textFieldSitioWeb);
        textFieldSitioWeb.setColumns(10);

        // Campo Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.fill = GridBagConstraints.BOTH;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 2;
        getContentPane().add(lblDescripcion, gbc_lblDescripcion);

        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setRows(4);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textAreaDescripcion);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 2;
        getContentPane().add(scrollPane, gbc_scrollPane);

        // Botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdCrearInstitucionActionPerformed(e);
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 3;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 3;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
    }

    protected void cmdCrearInstitucionActionPerformed(ActionEvent e) {
        String nombre = this.textFieldNombre.getText();
        String sitioWeb = this.textFieldSitioWeb.getText();
        String descripcion = this.textAreaDescripcion.getText();

        if (checkFormulario()) {
            try {
                // Crear el objeto DataInstitucion
                DataInstitucion di = new DataInstitucion(nombre, descripcion, sitioWeb);
                
                // Llamar al controlador para crear la institución
                controlInst.nuevaInstitucion(di);

                JOptionPane.showMessageDialog(this, "La Institución se ha creado con éxito", 
                    "Alta de Institución", JOptionPane.INFORMATION_MESSAGE);

                limpiarFormulario();
                setVisible(false);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al crear la institución: " + ex.getMessage(), 
                    "Alta de Institución", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean checkFormulario() {
        String nombre = this.textFieldNombre.getText().trim();
        String sitioWeb = this.textFieldSitioWeb.getText().trim();
        String descripcion = this.textAreaDescripcion.getText().trim();

        if (nombre.isEmpty() || sitioWeb.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", 
                "Alta de Institución", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldSitioWeb.setText("");
        textAreaDescripcion.setText("");
    }
}