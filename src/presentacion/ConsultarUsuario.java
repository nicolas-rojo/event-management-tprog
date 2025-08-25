package presentacion;

import javax.swing.JInternalFrame;

import excepciones.UsuarioNoExisteException;
import logica.Asistente;
import logica.IControladorUsuario;
import logica.Organizador;
import logica.datatypes.DataUsuario;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JInternalFrame que permite consultar la información de un usuario del sistema.
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class ConsultarUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorUsuario controlUsr;
    
    // Los componentes gráficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes métodos de la misma.
    private JTextField textFieldNickname;
    private JTextField textFieldNombre;
    private JTextField textFieldEmail;
    private JLabel lblIngresoNickname;
    private JButton btnBuscar;
    private JLabel lblNombre;
    private JLabel lblEmail;
    private JButton btnCerrar;
    private JLabel lblInfoUsuario;
    private JLabel lblTipoUsuario;
    private JTextField textFieldTipoUsuario;
    private JLabel lblApellidoDescripcion;
    private JTextField textFieldApellidoDescripcion;
    private JLabel lblUrl;
    private JTextField textFieldUrl;

    /**
     * Create the frame.
     */
    public ConsultarUsuario(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 450, 380);

        // En este caso usaremos el Absolute Layout y deberemos indicar
        // la posición absoluta de todos los componentes
        getContentPane().setLayout(null);

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // el nickname del usuario.
        lblIngresoNickname = new JLabel("Ingrese Nickname:");
        lblIngresoNickname.setBounds(10, 24, 120, 14);
        getContentPane().add(lblIngresoNickname);

        // Una campo de texto (JTextField) para ingresar el nickname de un usuario. 
        textFieldNickname = new JTextField();
        textFieldNickname.setBounds(120, 17, 140, 30);
        getContentPane().add(textFieldNickname);

        // Un botón (JButton) con un evento asociado que permite buscar un usuario.
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdBuscarUsuarioActionPerformed(e);
            }
        });
        btnBuscar.setBounds(270, 16, 95, 30);
        getContentPane().add(btnBuscar);
        
        // Una etiqueta (JLabel) indicando que a continuación se verá la 
        // información del usuario buscado.
        lblInfoUsuario = new JLabel("Información de Usuario");
        lblInfoUsuario.setBounds(150, 70, 180, 14);
        getContentPane().add(lblInfoUsuario);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se verá 
        // el nombre del usuario encontrado.
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 114, 65, 14);
        getContentPane().add(lblNombre);

        // Una campo de texto (JTextField) para mostrar el nombre del usuario. 
        textFieldNombre = new JTextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setBounds(120, 107, 245, 30);
        getContentPane().add(textFieldNombre);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se verá 
        // el email del usuario encontrado.
        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 156, 65, 14);
        getContentPane().add(lblEmail);
        
        // Una campo de texto (JTextField) para mostrar el email del usuario. 
        textFieldEmail = new JTextField();
        textFieldEmail.setEditable(false);
        textFieldEmail.setBounds(120, 149, 245, 30);
        getContentPane().add(textFieldEmail);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se verá 
        // el tipo de usuario encontrado.
        lblTipoUsuario = new JLabel("Tipo:");
        lblTipoUsuario.setBounds(10, 198, 65, 14);
        getContentPane().add(lblTipoUsuario);
        
        // Una campo de texto (JTextField) para mostrar el tipo de usuario. 
        textFieldTipoUsuario = new JTextField();
        textFieldTipoUsuario.setEditable(false);
        textFieldTipoUsuario.setBounds(120, 191, 245, 30);
        getContentPane().add(textFieldTipoUsuario);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se verá 
        // el apellido o descripción del usuario encontrado.
        lblApellidoDescripcion = new JLabel("Apellido/Desc:");
        lblApellidoDescripcion.setBounds(10, 240, 100, 14);
        getContentPane().add(lblApellidoDescripcion);
        
        // Una campo de texto (JTextField) para mostrar el apellido o descripción del usuario. 
        textFieldApellidoDescripcion = new JTextField();
        textFieldApellidoDescripcion.setEditable(false);
        textFieldApellidoDescripcion.setBounds(120, 233, 245, 30);
        getContentPane().add(textFieldApellidoDescripcion);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se verá 
        // la URL del usuario (solo para organizadores).
        lblUrl = new JLabel("URL:");
        lblUrl.setBounds(10, 282, 65, 14);
        getContentPane().add(lblUrl);
        
        // Una campo de texto (JTextField) para mostrar la URL del usuario. 
        textFieldUrl = new JTextField();
        textFieldUrl.setEditable(false);
        textFieldUrl.setBounds(120, 275, 245, 30);
        getContentPane().add(textFieldUrl);

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario.
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(320, 316, 89, 23);
        getContentPane().add(btnCerrar);
    }

    // Este método es invocado al querer buscar un usuario, funcionalidad
    // provista por la operación del sistema verInfoUsuario().
    protected void cmdBuscarUsuarioActionPerformed(ActionEvent e) {
        String nickname = textFieldNickname.getText().trim();
        
        if (nickname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nickname", "Buscar Usuario", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            DataUsuario du = controlUsr.verInfoUsuario(nickname);
            textFieldNombre.setText(du.getNombre());
            textFieldEmail.setText(du.getEmail());
            
            // Determinar el tipo de usuario y mostrar campos específicos
            String tipoUsuario = controlUsr.obtenerTipoUsuario(du.getEmail());
            textFieldTipoUsuario.setText(tipoUsuario);
            
            if ("Asistente".equals(tipoUsuario)) {
                lblApellidoDescripcion.setText("Apellido:");
                Asistente a = (Asistente) controlUsr.getAsistente(du.getEmail());
                String apellido = a.getApellido();
                textFieldApellidoDescripcion.setText(apellido);
                lblUrl.setVisible(false);
                textFieldUrl.setVisible(false);
            } else if ("Organizador".equals(tipoUsuario)) {
                lblApellidoDescripcion.setText("Descripción:");
                Organizador o = controlUsr.getOrganizador(du.getEmail());
                String descripcion = o.getDescripcion();
                textFieldApellidoDescripcion.setText(descripcion);
                
                String url = o.getUrl();
                textFieldUrl.setText(url);
                lblUrl.setVisible(true);
                textFieldUrl.setVisible(true);
            }
            
        } catch (UsuarioNoExisteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Buscar Usuario", JOptionPane.ERROR_MESSAGE);
            limpiarFormulario();
        }
    }
    
    private void limpiarFormulario() {
        textFieldNickname.setText("");
        textFieldNombre.setText("");
        textFieldEmail.setText("");
        textFieldTipoUsuario.setText("");
        textFieldApellidoDescripcion.setText("");
        textFieldUrl.setText("");
        lblUrl.setVisible(false);
        textFieldUrl.setVisible(false);
    }
}