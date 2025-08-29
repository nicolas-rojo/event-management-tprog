package presentacion;

import javax.swing.JInternalFrame;
import excepciones.UsuarioRepetidoException;
import logica.interfaces.IUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.time.ZoneId;

@SuppressWarnings("serial")
public class CrearUsuario extends JInternalFrame {

    private IUsuario controlUsr;
    
    private JTextField textFieldNombre;
    private JTextField textFieldNickname;
    private JTextField textFieldEmail;
    private JLabel lblIngreseNombre;
    private JLabel lblIngreseNickname;
    private JLabel lblIngreseEmail;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JComboBox<String> comboBoxTipoUsuario;
    
    // Campos específicos para Asistente
    private JTextField textFieldApellido;
    private JDateChooser dateChooser;
    private JPanel panelAsistente;
    
    // Campos específicos para Organizador
    private JTextField textFieldDescripcion;
    private JTextField textFieldUrl;
    private JPanel panelOrganizador;

    public CrearUsuario(IUsuario icu) {
        controlUsr = icu;

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar un Usuario");
        setBounds(10, 40, 450, 400);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 120, 200, 100, 0 };
        gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);

        // Tipo de Usuario
        JLabel lblTipoUsuario = new JLabel("Tipo de Usuario:");
        lblTipoUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipoUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblTipoUsuario = new GridBagConstraints();
        gbc_lblTipoUsuario.fill = GridBagConstraints.BOTH;
        gbc_lblTipoUsuario.insets = new Insets(0, 0, 5, 5);
        gbc_lblTipoUsuario.gridx = 0;
        gbc_lblTipoUsuario.gridy = 0;
        getContentPane().add(lblTipoUsuario, gbc_lblTipoUsuario);

        comboBoxTipoUsuario = new JComboBox<String>();
        comboBoxTipoUsuario.addItem("Asistente");
        comboBoxTipoUsuario.addItem("Organizador");
        comboBoxTipoUsuario.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                actualizarCamposEspecificos();
            }
        });
        GridBagConstraints gbc_comboBoxTipoUsuario = new GridBagConstraints();
        gbc_comboBoxTipoUsuario.gridwidth = 2;
        gbc_comboBoxTipoUsuario.fill = GridBagConstraints.BOTH;
        gbc_comboBoxTipoUsuario.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxTipoUsuario.gridx = 1;
        gbc_comboBoxTipoUsuario.gridy = 0;
        getContentPane().add(comboBoxTipoUsuario, gbc_comboBoxTipoUsuario);

        // Nombre
        lblIngreseNombre = new JLabel("Nombre:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 0;
        gbc_lblIngreseNombre.gridy = 1;
        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 2;
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 1;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);

        // Nickname
        lblIngreseNickname = new JLabel("Nickname:");
        lblIngreseNickname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngreseNickname.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNickname = new GridBagConstraints();
        gbc_lblIngreseNickname.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNickname.gridx = 0;
        gbc_lblIngreseNickname.gridy = 2;
        getContentPane().add(lblIngreseNickname, gbc_lblIngreseNickname);

        textFieldNickname = new JTextField();
        GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
        gbc_textFieldNickname.gridwidth = 2;
        gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
        gbc_textFieldNickname.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNickname.gridx = 1;
        gbc_textFieldNickname.gridy = 2;
        getContentPane().add(textFieldNickname, gbc_textFieldNickname);
        textFieldNickname.setColumns(10);

        // Email
        lblIngreseEmail = new JLabel("Email:");
        lblIngreseEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngreseEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseEmail = new GridBagConstraints();
        gbc_lblIngreseEmail.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseEmail.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseEmail.gridx = 0;
        gbc_lblIngreseEmail.gridy = 3;
        getContentPane().add(lblIngreseEmail, gbc_lblIngreseEmail);

        textFieldEmail = new JTextField();
        GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
        gbc_textFieldEmail.gridwidth = 2;
        gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
        gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldEmail.gridx = 1;
        gbc_textFieldEmail.gridy = 3;
        getContentPane().add(textFieldEmail, gbc_textFieldEmail);
        textFieldEmail.setColumns(10);

        // Panel para campos de Asistente
        panelAsistente = new JPanel();
        GridBagLayout gbl_panelAsistente = new GridBagLayout();
        gbl_panelAsistente.columnWidths = new int[]{120, 200, 100, 0};
        gbl_panelAsistente.rowHeights = new int[]{30, 30, 0};
        gbl_panelAsistente.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelAsistente.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panelAsistente.setLayout(gbl_panelAsistente);
        
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblApellido = new GridBagConstraints();
        gbc_lblApellido.anchor = GridBagConstraints.EAST;
        gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellido.gridx = 0;
        gbc_lblApellido.gridy = 0;
        panelAsistente.add(lblApellido, gbc_lblApellido);
        
        textFieldApellido = new JTextField();
        GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
        gbc_textFieldApellido.gridwidth = 2;
        gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
        gbc_textFieldApellido.gridx = 1;
        gbc_textFieldApellido.gridy = 0;
        panelAsistente.add(textFieldApellido, gbc_textFieldApellido);
        textFieldApellido.setColumns(10);
        
        JLabel lblFechaNac = new JLabel("Fecha Nacimiento:");
        lblFechaNac.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaNac.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblFechaNac = new GridBagConstraints();
        gbc_lblFechaNac.anchor = GridBagConstraints.EAST;
        gbc_lblFechaNac.insets = new Insets(0, 0, 0, 5);
        gbc_lblFechaNac.gridx = 0;
        gbc_lblFechaNac.gridy = 1;
        panelAsistente.add(lblFechaNac, gbc_lblFechaNac);
        
        // Usar JDateChooser en lugar de los combobox separados
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        // Establecer fecha por defecto: 20 años atrás
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.YEAR, -20);
        dateChooser.setDate(cal.getTime());
        
        GridBagConstraints gbc_dateChooser = new GridBagConstraints();
        gbc_dateChooser.gridwidth = 2;
        gbc_dateChooser.fill = GridBagConstraints.BOTH;
        gbc_dateChooser.gridx = 1;
        gbc_dateChooser.gridy = 1;
        panelAsistente.add(dateChooser, gbc_dateChooser);
        
        GridBagConstraints gbc_panelAsistente = new GridBagConstraints();
        gbc_panelAsistente.gridwidth = 3;
        gbc_panelAsistente.insets = new Insets(0, 0, 5, 0);
        gbc_panelAsistente.fill = GridBagConstraints.BOTH;
        gbc_panelAsistente.gridx = 0;
        gbc_panelAsistente.gridy = 4;
        getContentPane().add(panelAsistente, gbc_panelAsistente);
        panelAsistente.setVisible(true);

        // Panel para campos de Organizador
        panelOrganizador = new JPanel();
        GridBagLayout gbl_panelOrganizador = new GridBagLayout();
        gbl_panelOrganizador.columnWidths = new int[]{120, 200, 100, 0};
        gbl_panelOrganizador.rowHeights = new int[]{30, 30, 0};
        gbl_panelOrganizador.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelOrganizador.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panelOrganizador.setLayout(gbl_panelOrganizador);
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 0;
        panelOrganizador.add(lblDescripcion, gbc_lblDescripcion);
        
        textFieldDescripcion = new JTextField();
        GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
        gbc_textFieldDescripcion.gridwidth = 2;
        gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldDescripcion.fill = GridBagConstraints.BOTH;
        gbc_textFieldDescripcion.gridx = 1;
        gbc_textFieldDescripcion.gridy = 0;
        panelOrganizador.add(textFieldDescripcion, gbc_textFieldDescripcion);
        textFieldDescripcion.setColumns(10);
        
        JLabel lblUrl = new JLabel("URL:");
        lblUrl.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUrl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblUrl = new GridBagConstraints();
        gbc_lblUrl.anchor = GridBagConstraints.EAST;
        gbc_lblUrl.insets = new Insets(0, 0, 0, 5);
        gbc_lblUrl.gridx = 0;
        gbc_lblUrl.gridy = 1;
        panelOrganizador.add(lblUrl, gbc_lblUrl);
        
        textFieldUrl = new JTextField();
        GridBagConstraints gbc_textFieldUrl = new GridBagConstraints();
        gbc_textFieldUrl.gridwidth = 2;
        gbc_textFieldUrl.fill = GridBagConstraints.BOTH;
        gbc_textFieldUrl.gridx = 1;
        gbc_textFieldUrl.gridy = 1;
        panelOrganizador.add(textFieldUrl, gbc_textFieldUrl);
        textFieldUrl.setColumns(10);
        
        GridBagConstraints gbc_panelOrganizador = new GridBagConstraints();
        gbc_panelOrganizador.gridwidth = 3;
        gbc_panelOrganizador.insets = new Insets(0, 0, 5, 0);
        gbc_panelOrganizador.fill = GridBagConstraints.BOTH;
        gbc_panelOrganizador.gridx = 0;
        gbc_panelOrganizador.gridy = 4;
        getContentPane().add(panelOrganizador, gbc_panelOrganizador);
        panelOrganizador.setVisible(false);

        // Botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroUsuarioActionPerformed(arg0);
            }
        });

        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 8;
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
        gbc_btnCancelar.gridy = 8;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
    }

    private void actualizarCamposEspecificos() {
        String tipoUsuario = (String) comboBoxTipoUsuario.getSelectedItem();
        
        if ("Asistente".equals(tipoUsuario)) {
            panelAsistente.setVisible(true);
            panelOrganizador.setVisible(false);
        } else if ("Organizador".equals(tipoUsuario)) {
            panelAsistente.setVisible(false);
            panelOrganizador.setVisible(true);
        }
        
        // Ajustar el tamaño del internal frame según los campos visibles
        pack();
    }

    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
        String nombreU = this.textFieldNombre.getText();
        String nicknameU = this.textFieldNickname.getText();
        String emailU = this.textFieldEmail.getText();
        String tipoUsuario = (String) comboBoxTipoUsuario.getSelectedItem();

        if (checkFormulario()) {
            try {
                if ("Asistente".equals(tipoUsuario)) {
                    String apellidoU = this.textFieldApellido.getText();
                    
                    // Obtener la fecha del JDateChooser y convertirla a LocalDate
                    Date fechaNacDate = dateChooser.getDate();
                    LocalDate fechaNac = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    
                    controlUsr.registrarAsistente(nombreU, nicknameU, emailU, apellidoU, fechaNac);
                } else if ("Organizador".equals(tipoUsuario)) {
                    String descripcionU = this.textFieldDescripcion.getText();
                    String urlU = this.textFieldUrl.getText();
                    
                    controlUsr.registrarOrganizador(nombreU, nicknameU, emailU, descripcionU, urlU);
                }

                JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (UsuarioRepetidoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: Fecha inválida. " + e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            }

            limpiarFormulario();
            setVisible(false);
        }
    }

    private boolean checkFormulario() {
        String nombreU = this.textFieldNombre.getText();
        String nicknameU = this.textFieldNickname.getText();
        String emailU = this.textFieldEmail.getText();
        String tipoUsuario = (String) comboBoxTipoUsuario.getSelectedItem();

        if (nombreU.isEmpty() || nicknameU.isEmpty() || emailU.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("Asistente".equals(tipoUsuario)) {
            String apellidoU = this.textFieldApellido.getText();
            
            if (apellidoU.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Validación de fecha
            if (dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de nacimiento válida", "Registrar Usuario",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } else if ("Organizador".equals(tipoUsuario)) {
            String descripcionU = this.textFieldDescripcion.getText();
            String urlU = this.textFieldUrl.getText();
            
            if (descripcionU.isEmpty() || urlU.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldNickname.setText("");
        textFieldEmail.setText("");
        textFieldApellido.setText("");
        
        // Restablecer la fecha a 20 años atrás
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.YEAR, -20);
        dateChooser.setDate(cal.getTime());
        
        textFieldDescripcion.setText("");
        textFieldUrl.setText("");
        comboBoxTipoUsuario.setSelectedIndex(0);
        actualizarCamposEspecificos();
    }
}