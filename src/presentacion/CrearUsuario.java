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
        setBounds(10, 40, 450, 314);
        getContentPane().setLayout(null);

        // Tipo de Usuario
        JLabel lblTipoUsuario = new JLabel("Tipo de Usuario:");
        lblTipoUsuario.setBounds(0, 12, 115, 25);
        lblTipoUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipoUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblTipoUsuario);

        comboBoxTipoUsuario = new JComboBox<String>();
        comboBoxTipoUsuario.setBounds(120, 12, 300, 25);
        comboBoxTipoUsuario.addItem("Asistente");
        comboBoxTipoUsuario.addItem("Organizador");
        comboBoxTipoUsuario.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                actualizarCamposEspecificos();
            }
        });
        getContentPane().add(comboBoxTipoUsuario);

        // Nombre
        lblIngreseNombre = new JLabel("Nombre:");
        lblIngreseNombre.setBounds(0, 48, 115, 25);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(120, 48, 300, 25);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        // Nickname
        lblIngreseNickname = new JLabel("Nickname:");
        lblIngreseNickname.setBounds(0, 84, 115, 25);
        lblIngreseNickname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngreseNickname.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNickname);

        textFieldNickname = new JTextField();
        textFieldNickname.setBounds(120, 84, 300, 25);
        getContentPane().add(textFieldNickname);
        textFieldNickname.setColumns(10);

        // Panel para campos de Asistente
        panelAsistente = new JPanel();
        panelAsistente.setBounds(0, 120, 420, 60);
        panelAsistente.setLayout(null);
        
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(22, 38, 88, 14);
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        panelAsistente.add(lblApellido);
        
        textFieldApellido = new JTextField();
        textFieldApellido.setBounds(120, 33, 300, 25);
        panelAsistente.add(textFieldApellido);
        textFieldApellido.setColumns(10);
        // Establecer fecha por defecto: 20 años atrás
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.YEAR, -20);
        getContentPane().add(panelAsistente);
        
                textFieldEmail = new JTextField();
                textFieldEmail.setBounds(120, 0, 300, 25);
                panelAsistente.add(textFieldEmail);
                textFieldEmail.setColumns(10);
                
                        // Email
                        lblIngreseEmail = new JLabel("Email:");
                        lblIngreseEmail.setBounds(-5, 2, 115, 25);
                        panelAsistente.add(lblIngreseEmail);
                        lblIngreseEmail.setHorizontalAlignment(SwingConstants.RIGHT);
                        lblIngreseEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        panelAsistente.setVisible(true);

        // Panel para campos de Organizador
        panelOrganizador = new JPanel();
        panelOrganizador.setBounds(0, 120, 420, 60);
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
        getContentPane().add(panelOrganizador);
        panelOrganizador.setVisible(false);

        // Botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(209, 232, 100, 30);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroUsuarioActionPerformed(arg0);
            }
        });
        getContentPane().add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 232, 100, 30);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        getContentPane().add(btnCancelar);
        
        // Usar JDateChooser en lugar de los combobox separados
        dateChooser = new JDateChooser();
        dateChooser.setBounds(120, 191, 300, 30);
        getContentPane().add(dateChooser);
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(cal.getTime());
        
        JLabel lblFechaNac = new JLabel("Fecha Nacimiento:");
        lblFechaNac.setBounds(0, 200, 115, 14);
        getContentPane().add(lblFechaNac);
        lblFechaNac.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaNac.setHorizontalAlignment(SwingConstants.RIGHT);
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