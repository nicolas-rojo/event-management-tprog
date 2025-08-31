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
    private DataInstitucion[] instituciones; // Cambiado a DataInstitucion[]

    public CrearPatrocinio(IEventos iev, IInstituciones iin) {
        controlEventos = iev;
        controlInst = iin;
        
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Patrocinio");
        setBounds(10, 40, 500, 450);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{130, 250, 100, 0};
        gridBagLayout.rowHeights = new int[]{40, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        // Título
        JLabel lblTitulo = new JLabel("Registro de Nuevo Patrocinio");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
        gbc_lblTitulo.gridwidth = 3;
        gbc_lblTitulo.insets = new Insets(0, 0, 15, 0);
        gbc_lblTitulo.gridx = 0;
        gbc_lblTitulo.gridy = 0;
        getContentPane().add(lblTitulo, gbc_lblTitulo);

        // Evento
        lblEvento = new JLabel("Evento:");
        lblEvento.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblEvento = new GridBagConstraints();
        gbc_lblEvento.fill = GridBagConstraints.BOTH;
        gbc_lblEvento.insets = new Insets(0, 0, 5, 5);
        gbc_lblEvento.gridx = 0;
        gbc_lblEvento.gridy = 1;
        getContentPane().add(lblEvento, gbc_lblEvento);

        comboBoxEventos = new JComboBox<>();
        comboBoxEventos.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    cargarEdiciones();
                }
            }
        });
        GridBagConstraints gbc_comboBoxEventos = new GridBagConstraints();
        gbc_comboBoxEventos.gridwidth = 2;
        gbc_comboBoxEventos.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxEventos.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxEventos.gridx = 1;
        gbc_comboBoxEventos.gridy = 1;
        getContentPane().add(comboBoxEventos, gbc_comboBoxEventos);

        // Edición
        lblEdicion = new JLabel("Edición:");
        lblEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
        gbc_lblEdicion.fill = GridBagConstraints.BOTH;
        gbc_lblEdicion.insets = new Insets(0, 0, 5, 5);
        gbc_lblEdicion.gridx = 0;
        gbc_lblEdicion.gridy = 2;
        getContentPane().add(lblEdicion, gbc_lblEdicion);

        comboBoxEdiciones = new JComboBox<>();
        comboBoxEdiciones.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    cargarTiposRegistro();
                }
            }
        });
        GridBagConstraints gbc_comboBoxEdiciones = new GridBagConstraints();
        gbc_comboBoxEdiciones.gridwidth = 2;
        gbc_comboBoxEdiciones.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxEdiciones.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxEdiciones.gridx = 1;
        gbc_comboBoxEdiciones.gridy = 2;
        getContentPane().add(comboBoxEdiciones, gbc_comboBoxEdiciones);

        // Tipo de Registro
        lblTipoRegistro = new JLabel("Tipo de Registro:");
        lblTipoRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblTipoRegistro = new GridBagConstraints();
        gbc_lblTipoRegistro.fill = GridBagConstraints.BOTH;
        gbc_lblTipoRegistro.insets = new Insets(0, 0, 5, 5);
        gbc_lblTipoRegistro.gridx = 0;
        gbc_lblTipoRegistro.gridy = 3;
        getContentPane().add(lblTipoRegistro, gbc_lblTipoRegistro);

        comboBoxTipoRegistro = new JComboBox<>();
        GridBagConstraints gbc_comboBoxTipoRegistro = new GridBagConstraints();
        gbc_comboBoxTipoRegistro.gridwidth = 2;
        gbc_comboBoxTipoRegistro.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxTipoRegistro.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxTipoRegistro.gridx = 1;
        gbc_comboBoxTipoRegistro.gridy = 3;
        getContentPane().add(comboBoxTipoRegistro, gbc_comboBoxTipoRegistro);

        // Institución
        lblInstitucion = new JLabel("Institución:");
        lblInstitucion.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblInstitucion = new GridBagConstraints();
        gbc_lblInstitucion.fill = GridBagConstraints.BOTH;
        gbc_lblInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_lblInstitucion.gridx = 0;
        gbc_lblInstitucion.gridy = 4;
        getContentPane().add(lblInstitucion, gbc_lblInstitucion);

        comboBoxInstitucion = new JComboBox<>();
        GridBagConstraints gbc_comboBoxInstitucion = new GridBagConstraints();
        gbc_comboBoxInstitucion.gridwidth = 2;
        gbc_comboBoxInstitucion.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxInstitucion.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxInstitucion.gridx = 1;
        gbc_comboBoxInstitucion.gridy = 4;
        getContentPane().add(comboBoxInstitucion, gbc_comboBoxInstitucion);

        // Nivel de Patrocinio
        lblNivel = new JLabel("Nivel de Patrocinio:");
        lblNivel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblNivel = new GridBagConstraints();
        gbc_lblNivel.fill = GridBagConstraints.BOTH;
        gbc_lblNivel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNivel.gridx = 0;
        gbc_lblNivel.gridy = 5;
        getContentPane().add(lblNivel, gbc_lblNivel);

        comboBoxNivel = new JComboBox<>(Nivel.values());
        GridBagConstraints gbc_comboBoxNivel = new GridBagConstraints();
        gbc_comboBoxNivel.gridwidth = 2;
        gbc_comboBoxNivel.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxNivel.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxNivel.gridx = 1;
        gbc_comboBoxNivel.gridy = 5;
        getContentPane().add(comboBoxNivel, gbc_comboBoxNivel);

        // Monto del Aporte
        lblMonto = new JLabel("Aporte Económico:");
        lblMonto.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblMonto = new GridBagConstraints();
        gbc_lblMonto.fill = GridBagConstraints.BOTH;
        gbc_lblMonto.insets = new Insets(0, 0, 5, 5);
        gbc_lblMonto.gridx = 0;
        gbc_lblMonto.gridy = 6;
        getContentPane().add(lblMonto, gbc_lblMonto);

        textFieldMonto = new JTextField();
        GridBagConstraints gbc_textFieldMonto = new GridBagConstraints();
        gbc_textFieldMonto.gridwidth = 2;
        gbc_textFieldMonto.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldMonto.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldMonto.gridx = 1;
        gbc_textFieldMonto.gridy = 6;
        getContentPane().add(textFieldMonto, gbc_textFieldMonto);

        // Cantidad de Cupos Gratuitos
        lblCantidadCupos = new JLabel("Cantidad de Cupos:");
        lblCantidadCupos.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblCantidadCupos = new GridBagConstraints();
        gbc_lblCantidadCupos.fill = GridBagConstraints.BOTH;
        gbc_lblCantidadCupos.insets = new Insets(0, 0, 5, 5);
        gbc_lblCantidadCupos.gridx = 0;
        gbc_lblCantidadCupos.gridy = 7;
        getContentPane().add(lblCantidadCupos, gbc_lblCantidadCupos);

        textFieldCantidadCupos = new JTextField();
        GridBagConstraints gbc_textFieldCantidadCupos = new GridBagConstraints();
        gbc_textFieldCantidadCupos.gridwidth = 2;
        gbc_textFieldCantidadCupos.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldCantidadCupos.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldCantidadCupos.gridx = 1;
        gbc_textFieldCantidadCupos.gridy = 7;
        getContentPane().add(textFieldCantidadCupos, gbc_textFieldCantidadCupos);

        // Código de Patrocinio
        lblCodigo = new JLabel("Código de Patrocinio:");
        lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
        gbc_lblCodigo.fill = GridBagConstraints.BOTH;
        gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
        gbc_lblCodigo.gridx = 0;
        gbc_lblCodigo.gridy = 8;
        getContentPane().add(lblCodigo, gbc_lblCodigo);

        textFieldCodigo = new JTextField();
        GridBagConstraints gbc_textFieldCodigo = new GridBagConstraints();
        gbc_textFieldCodigo.gridwidth = 2;
        gbc_textFieldCodigo.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldCodigo.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldCodigo.gridx = 1;
        gbc_textFieldCodigo.gridy = 8;
        getContentPane().add(textFieldCodigo, gbc_textFieldCodigo);

        // Botones
        btnAceptar = new JButton("Crear Patrocinio");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdCrearPatrocinioActionPerformed(e);
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(10, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 9;
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
        gbc_btnCancelar.insets = new Insets(10, 0, 0, 0);
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 9;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        
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
            // Cambiado para usar DataInstitucion[] en lugar de List<String>
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
                
                //String tipoRegistro = obtenerTipoRegistroCompleto(evento, edicion, tipoRegistroNombre);
                
                // Crear el objeto DataPatrocinio
                DataPatrocinio patrocinio = new DataPatrocinio(
                    LocalDate.now(), // Fecha actual
                    monto,
                    nivel,
                    codigo,
                    cantidadCupos
                );
                
                // Crear el patrocinio usando el controlador
                // Ahora pasamos TipoRegistro en lugar de DataTRegistro
                controlInst.nuevoPatrocinio(patrocinio, institucionNombre, evento, edicion, tipoRegistroNombre);
                
                JOptionPane.showMessageDialog(this, 
                    "El patrocinio se ha registrado exitosamente",
                    "Alta de Patrocinio", JOptionPane.INFORMATION_MESSAGE);
                
                limpiarFormulario();
                setVisible(false);
                
            } catch (NumberFormatException ex) {
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
            
            if (montoVal < 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un valor positivo",
                    "Validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (cuposVal < 0) {
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