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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
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
    private JLabel lblFechaNac;
    
    // Campos específicos para Organizador
    private JTextArea textAreaDescripcion;
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
        setBounds(10, 40, 460, 350);
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

        // Email (campo común para ambos tipos de usuario)
        lblIngreseEmail = new JLabel("Email:");
        lblIngreseEmail.setBounds(0, 120, 115, 25);
        lblIngreseEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(120, 120, 300, 25);
        textFieldEmail.setColumns(10);
        getContentPane().add(textFieldEmail);

        // Panel para campos de Asistente
        panelAsistente = new JPanel();
        panelAsistente.setBounds(0, 155, 420, 30);
        panelAsistente.setLayout(null);
        
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(0, 5, 115, 25);
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        panelAsistente.add(lblApellido);
        
        textFieldApellido = new JTextField();
        textFieldApellido.setBounds(120, 5, 300, 25);
        panelAsistente.add(textFieldApellido);
        textFieldApellido.setColumns(10);
        
        getContentPane().add(panelAsistente);
        panelAsistente.setVisible(true);

        // Fecha de nacimiento (solo para Asistente)
        lblFechaNac = new JLabel("Fecha Nacimiento:");
        lblFechaNac.setBounds(0, 195, 115, 25);
        lblFechaNac.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblFechaNac);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(120, 195, 300, 25);
        dateChooser.setDateFormatString("dd/MM/yyyy");
        // Establecer fecha por defecto: 20 años atrás
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.YEAR, -20);
        dateChooser.setDate(cal.getTime());
        getContentPane().add(dateChooser);

        // Panel para campos de Organizador
        panelOrganizador = new JPanel();
        panelOrganizador.setBounds(0, 155, 420, 90);
        panelOrganizador.setLayout(null);
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(0, 5, 115, 25);
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        panelOrganizador.add(lblDescripcion);
        
        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textAreaDescripcion);
        scrollPane.setBounds(120, 5, 300, 50);
        panelOrganizador.add(scrollPane);
        
        JLabel lblUrl = new JLabel("URL:");
        lblUrl.setBounds(0, 60, 115, 25);
        lblUrl.setHorizontalAlignment(SwingConstants.RIGHT);
        panelOrganizador.add(lblUrl);
        
        textFieldUrl = new JTextField();
        textFieldUrl.setBounds(120, 60, 300, 25);
        panelOrganizador.add(textFieldUrl);
        textFieldUrl.setColumns(10);
        
        getContentPane().add(panelOrganizador);
        panelOrganizador.setVisible(false);

        // Botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(209, 250, 100, 30);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroUsuarioActionPerformed(arg0);
                limpiarFormulario();
            }
        });
        getContentPane().add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 250, 100, 30);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        getContentPane().add(btnCancelar);
        
        // Esto es para que el boton cerrar borre el contenido
        this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent e) {
                limpiarFormulario(); // limpia todos los campos
            }
        });

        // Inicializar la visibilidad de los campos
        actualizarCamposEspecificos();
    }

    private void actualizarCamposEspecificos() {
        String tipoUsuario = (String) comboBoxTipoUsuario.getSelectedItem();
        
        if ("Asistente".equals(tipoUsuario)) {
            panelAsistente.setVisible(true);
            panelOrganizador.setVisible(false);
            lblFechaNac.setVisible(true);
            dateChooser.setVisible(true);
        } else if ("Organizador".equals(tipoUsuario)) {
            panelAsistente.setVisible(false);
            panelOrganizador.setVisible(true);
            lblFechaNac.setVisible(false);
            dateChooser.setVisible(false);
        }
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
                    String descripcionU = this.textAreaDescripcion.getText();
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

        // Datos comunes obligatorios
        if (nombreU.isEmpty() || nicknameU.isEmpty() || emailU.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Nombre, nickname y correo electrónico son obligatorios", 
                "Registrar Usuario", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("Asistente".equals(tipoUsuario)) {
            String apellidoU = this.textFieldApellido.getText();

            if (apellidoU.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "El apellido es obligatorio para asistentes", 
                    "Registrar Usuario", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, 
                    "Debe seleccionar una fecha de nacimiento válida", 
                    "Registrar Usuario", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } else if ("Organizador".equals(tipoUsuario)) {
            String descripcionU = this.textAreaDescripcion.getText();

            if (descripcionU.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "La descripción es obligatoria para organizadores", 
                    "Registrar Usuario", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // urlU puede ser vacío porque el caso de uso dice que es opcional
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
        
        textAreaDescripcion.setText("");
        textFieldUrl.setText("");
        comboBoxTipoUsuario.setSelectedIndex(0);
        actualizarCamposEspecificos();
    }
}