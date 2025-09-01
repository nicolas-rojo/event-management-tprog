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
        setBounds(10, 40, 452, 345);
        getContentPane().setLayout(null);

        // Campo Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 11, 61, 25);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(91, 13, 323, 20);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        // Campo Sitio Web
        JLabel lblSitioWeb = new JLabel("Sitio Web:");
        lblSitioWeb.setBounds(20, 42, 61, 25);
        lblSitioWeb.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblSitioWeb);

        textFieldSitioWeb = new JTextField();
        textFieldSitioWeb.setBounds(91, 44, 323, 20);
        getContentPane().add(textFieldSitioWeb);
        textFieldSitioWeb.setColumns(10);

        // Campo Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(-10, 78, 91, 14);
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
        getContentPane().add(lblDescripcion);

        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setRows(4);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textAreaDescripcion);
        scrollPane.setBounds(91, 81, 323, 182);
        getContentPane().add(scrollPane);

        // Botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(201, 274, 100, 30);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdCrearInstitucionActionPerformed(e);
            }
        });
        getContentPane().add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(308, 274, 100, 30);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        getContentPane().add(btnCancelar);
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