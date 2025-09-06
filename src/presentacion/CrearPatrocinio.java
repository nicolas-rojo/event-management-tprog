package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

import logica.interfaces.IEventos;
import logica.interfaces.IInstituciones;
import logica.datatypes.*;
import logica.TipoRegistro;

@SuppressWarnings("serial")
public class CrearPatrocinio extends JInternalFrame {

    private IEventos controlEventos;
    private IInstituciones controlInst;
    
    // Componentes de la interfaz
    private JComboBox<String> comboBoxEventos;
    private JComboBox<String> comboBoxEdiciones;
    private JComboBox<String> comboBoxTipoRegistro;
    private JComboBox<String> comboBoxInstitucion;
    private JComboBox<Nivel> comboBoxNivel;
    private JTextField textFieldMonto;
    private JTextField textFieldCantidadCupos;
    private JTextField textFieldCodigo;
    
    private JLabel lblEvento;
    private JLabel lblEdicion;
    private JLabel lblTipoRegistro;
    private JLabel lblInstitucion;
    private JLabel lblNivel;
    private JLabel lblMonto;
    private JLabel lblCantidadCupos;
    private JLabel lblCodigo;
    
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    // Datos cargados
    private List<String> eventos;
    private DataInstitucion[] instituciones;

    public CrearPatrocinio(IEventos iev, IInstituciones iin) {
        controlEventos = iev;
        controlInst = iin;
        
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Patrocinio");
        setBounds(10, 40, 531, 353);
        getContentPane().setLayout(null);

        // Evento
        lblEvento = new JLabel("Evento:");
        lblEvento.setBounds(0, 40, 125, 25);
        lblEvento.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblEvento);

        comboBoxEventos = new JComboBox<>();
        comboBoxEventos.setBounds(130, 41, 354, 22);
        comboBoxEventos.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    cargarEdiciones();
                }
            }
        });
        getContentPane().add(comboBoxEventos);

        // Edición
        lblEdicion = new JLabel("Edición:");
        lblEdicion.setBounds(0, 70, 125, 25);
        lblEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblEdicion);

        comboBoxEdiciones = new JComboBox<>();
        comboBoxEdiciones.setBounds(130, 71, 354, 22);
        comboBoxEdiciones.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    cargarTiposRegistro();
                }
            }
        });
        getContentPane().add(comboBoxEdiciones);

        // Tipo de Registro
        lblTipoRegistro = new JLabel("Tipo de Registro:");
        lblTipoRegistro.setBounds(0, 100, 125, 25);
        lblTipoRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblTipoRegistro);

        comboBoxTipoRegistro = new JComboBox<>();
        comboBoxTipoRegistro.setBounds(130, 101, 354, 22);
        getContentPane().add(comboBoxTipoRegistro);

        // Institución
        lblInstitucion = new JLabel("Institución:");
        lblInstitucion.setBounds(0, 130, 125, 25);
        lblInstitucion.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblInstitucion);

        comboBoxInstitucion = new JComboBox<>();
        comboBoxInstitucion.setBounds(130, 131, 354, 22);
        getContentPane().add(comboBoxInstitucion);

        // Nivel de Patrocinio
        lblNivel = new JLabel("Nivel de Patrocinio:");
        lblNivel.setBounds(0, 160, 125, 25);
        lblNivel.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblNivel);

        comboBoxNivel = new JComboBox<>(Nivel.values());
        comboBoxNivel.setBounds(130, 162, 354, 20);
        getContentPane().add(comboBoxNivel);

        // Monto del Aporte
        lblMonto = new JLabel("Aporte Económico:");
        lblMonto.setBounds(0, 190, 125, 25);
        lblMonto.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblMonto);

        textFieldMonto = new JTextField();
        textFieldMonto.setBounds(130, 192, 354, 20);
        getContentPane().add(textFieldMonto);

        // Cantidad de Cupos Gratuitos
        lblCantidadCupos = new JLabel("Cantidad de Cupos:");
        lblCantidadCupos.setBounds(0, 220, 125, 25);
        lblCantidadCupos.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblCantidadCupos);

        textFieldCantidadCupos = new JTextField();
        textFieldCantidadCupos.setBounds(130, 222, 354, 20);
        getContentPane().add(textFieldCantidadCupos);

        // Código de Patrocinio
        lblCodigo = new JLabel("Código de Patrocinio:");
        lblCodigo.setBounds(0, 250, 125, 25);
        lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblCodigo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setBounds(130, 252, 354, 20);
        getContentPane().add(textFieldCodigo);

        // Botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(279, 290, 100, 23);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdCrearPatrocinioActionPerformed(e);
            }
        });
        getContentPane().add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(384, 290, 100, 23);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        getContentPane().add(btnCancelar);
        
        // Inicializar combos vacíos
        limpiarCombos();
    }

    public void cargarDatos() {
        cargarEventos();
        cargarInstituciones();
    }

    private void cargarEventos() {
        try {
            // Obtener eventos del controlador
            eventos = controlEventos.listarEventos();
            
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("-- Seleccionar Evento --");
            
            if (eventos != null) {
                for (String evento : eventos) {
                    model.addElement(evento);
                }
            }
            
            comboBoxEventos.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar eventos: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEdiciones() {
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Seleccionar Edición --");
        
        if (eventoSeleccionado != null && !eventoSeleccionado.equals("-- Seleccionar Evento --")) {
            try {
                // Obtener ediciones del evento seleccionado
                List<String> ediciones = controlEventos.listarEdiciones(eventoSeleccionado);
                
                for (String edicion : ediciones) {
                    model.addElement(edicion);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al cargar ediciones: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        comboBoxEdiciones.setModel(model);
        
        // Limpiar tipos de registro cuando cambia la edición
        limpiarComboTiposRegistro();
    }

    private void cargarTiposRegistro() {
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Seleccionar Tipo de Registro --");
        
        if (eventoSeleccionado != null && !eventoSeleccionado.equals("-- Seleccionar Evento --") &&
            edicionSeleccionada != null && !edicionSeleccionada.equals("-- Seleccionar Edición --")) {
            try {
                // Obtener tipos de registro de la edición seleccionada
                List<String> tiposRegistro = controlEventos.listarTRegistros(eventoSeleccionado, edicionSeleccionada);
                
                for (String tipo : tiposRegistro) {
                    model.addElement(tipo);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al cargar tipos de registro: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        comboBoxTipoRegistro.setModel(model);
    }

    private void cargarInstituciones() {
        try {
            instituciones = controlInst.listarInstituciones();
            
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("-- Seleccionar Institución --");
            
            if (instituciones != null) {
                for (DataInstitucion institucion : instituciones) {
                    model.addElement(institucion.getNombre());
                }
            }
            
            comboBoxInstitucion.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar instituciones: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void cmdCrearPatrocinioActionPerformed(ActionEvent e) {
        if (checkFormulario()) {
            try {
                // Obtener valores del formulario
                String evento = (String) comboBoxEventos.getSelectedItem();
                String edicion = (String) comboBoxEdiciones.getSelectedItem();
                String tipoRegistroNombre = (String) comboBoxTipoRegistro.getSelectedItem();
                String institucionNombre = (String) comboBoxInstitucion.getSelectedItem();
                Nivel nivel = (Nivel) comboBoxNivel.getSelectedItem();
                
                float monto = Float.parseFloat(textFieldMonto.getText().trim());
                int cantidadCupos = Integer.parseInt(textFieldCantidadCupos.getText().trim());
                String codigo = (textFieldCodigo.getText().trim());
                
                // Crear el objeto DataPatrocinio
                DataPatrocinio patrocinio = new DataPatrocinio(
                    LocalDate.now(), // Fecha actual
                    monto,
                    nivel,
                    codigo,
                    cantidadCupos
                );
                controlInst.nuevoPatrocinio(patrocinio, institucionNombre, evento, edicion, tipoRegistroNombre);
                JOptionPane.showMessageDialog(this, 
                    "El patrocinio se ha registrado exitosamente",
                    "Alta de Patrocinio", JOptionPane.INFORMATION_MESSAGE);
                
                limpiarFormulario();
                setVisible(false);
                
            } catch (NumberFormatException ex) { //No se si esta excepsión hay que agregarla o se supone que ya viene incluida ¿?
                JOptionPane.showMessageDialog(this, 
                    "Error en los datos numéricos. Verifique el monto, cantidad de cupos y código.",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al crear el patrocinio: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método auxiliar para obtener el TipoRegistro completo
    private TipoRegistro obtenerTipoRegistroCompleto(String evento, String edicion, String tipoRegistroNombre) {
        try {
            // Obtener DataTRegistro primero
            DataTRegistro dataTRegistro = controlEventos.getDataTRegistro(evento, edicion, tipoRegistroNombre);
            return new TipoRegistro(dataTRegistro);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el tipo de registro: " + e.getMessage(), e);
        }
    }

    private boolean checkFormulario() {
        if (comboBoxEventos.getSelectedItem() == null || comboBoxEventos.getSelectedItem().equals("-- Seleccionar Evento --")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un evento",
                "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (comboBoxEdiciones.getSelectedItem() == null || comboBoxEdiciones.getSelectedItem().equals("-- Seleccionar Edición --")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una edición",
                "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (comboBoxTipoRegistro.getSelectedItem() == null || comboBoxTipoRegistro.getSelectedItem().equals("-- Seleccionar Tipo de Registro --")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de registro",
                "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (comboBoxInstitucion.getSelectedItem() == null || comboBoxInstitucion.getSelectedItem().equals("-- Seleccionar Institución --")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una institución",
                "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (comboBoxNivel.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un nivel de patrocinio",
                "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String monto = textFieldMonto.getText().trim();
        String cantidadCupos = textFieldCantidadCupos.getText().trim();
        String codigo = textFieldCodigo.getText().trim();
        
        if (monto.isEmpty() || cantidadCupos.isEmpty() || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos",
                "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            float montoVal = Float.parseFloat(monto);
            int cuposVal = Integer.parseInt(cantidadCupos);
            String codigoVal = codigo;
            
            if (montoVal <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un valor positivo",
                    "Validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (cuposVal <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad de cupos debe ser un valor positivo",
                    "Validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (codigoVal.equals("")) {
                JOptionPane.showMessageDialog(this, "El código debe ser un valor positivo",
                    "Validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los valores numéricos no son válidos",
                "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }

    private void limpiarCombos() {
        comboBoxEventos.setModel(new DefaultComboBoxModel<String>());
        comboBoxEdiciones.setModel(new DefaultComboBoxModel<String>());
        comboBoxTipoRegistro.setModel(new DefaultComboBoxModel<String>());
        comboBoxInstitucion.setModel(new DefaultComboBoxModel<String>());
    }

    private void limpiarComboTiposRegistro() {
        comboBoxTipoRegistro.setModel(new DefaultComboBoxModel<String>());
    }

    public void limpiarFormulario() {
        limpiarCombos();
        cargarDatos(); // Recargar los datos
        comboBoxNivel.setSelectedIndex(0);
        textFieldMonto.setText("");
        textFieldCantidadCupos.setText("");
        textFieldCodigo.setText("");
    }
}