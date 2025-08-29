package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import logica.ctrlmanejador.ManejadorEvento;
import logica.interfaces.IEventos;
import logica.datatypes.DataEvento;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

@SuppressWarnings("serial")
public class CrearEvento extends JInternalFrame {
	
	private IEventos controlEvt;
	private JTextField textFieldNombre;
    private JTextField textFieldDesc;
    private JTextField textFieldSigla;
    private JLabel lblIngresarNombre;
    private JLabel lblIngresarDesc;
    private JLabel lblIngresarSigla;
    private JLabel lblCategoria;
    private JComboBox<String> comboBoxCat;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private JComboBox<Integer> cmbDiaAlta;
    private JComboBox<String> cmbMesAlta;
    private JComboBox<Integer> cmbAnioAlta;
    private JLabel lblFechaAlta;
    

  
    
    
    public CrearEvento(IEventos ice) {
    	
    	ManejadorEvento me = ManejadorEvento.getInstance();
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Evento");
        setBounds(10, 40, 450, 400);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{120, 200, 100, 0};
        gridBagLayout.rowHeights = new int[]{45, 45, 45, 45, 45, 45, 10, 30, 30, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
		controlEvt = ice;
		
		lblIngresarNombre = new JLabel("Nombre:");
        lblIngresarNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngresarNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 0;
        gbc_lblIngreseNombre.gridy = 1;
        getContentPane().add(lblIngresarNombre, gbc_lblIngreseNombre);
		
        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 1;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);
        
        lblIngresarDesc = new JLabel("Descripcion :");
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 2;
        getContentPane().add(lblIngresarDesc, gbc_lblDescripcion);
        
        textFieldDesc = new JTextField();
        GridBagConstraints gbc_textFieldDesc = new GridBagConstraints();
        gbc_textFieldDesc.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldDesc.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldDesc.gridx = 1;
        gbc_textFieldDesc.gridy = 2;
        getContentPane().add(textFieldDesc, gbc_textFieldDesc);
        textFieldDesc.setColumns(10);
        
        lblIngresarSigla = new JLabel("Sigla :");
        GridBagConstraints gbc_lblSigla = new GridBagConstraints();
        gbc_lblSigla.anchor = GridBagConstraints.EAST;
        gbc_lblSigla.insets = new Insets(0, 0, 5, 5);
        gbc_lblSigla.gridx = 0;
        gbc_lblSigla.gridy = 3;
        getContentPane().add(lblIngresarSigla, gbc_lblSigla);
        
        textFieldSigla = new JTextField();
        GridBagConstraints gbc_textFieldSigla = new GridBagConstraints();
        gbc_textFieldSigla.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldSigla.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldSigla.gridx = 1;
        gbc_textFieldSigla.gridy = 3;
        getContentPane().add(textFieldSigla, gbc_textFieldSigla);
        textFieldSigla.setColumns(10);
        
        lblCategoria = new JLabel("Categoria :");
        GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
        gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
        gbc_lblCategoria.anchor = GridBagConstraints.EAST;
        gbc_lblCategoria.gridx = 0;
        gbc_lblCategoria.gridy = 4;
        getContentPane().add(lblCategoria, gbc_lblCategoria);
        
        
      
        comboBoxCat = new JComboBox<String>();
        for (String claveCat : me.getCategoriasClave()) {
            comboBoxCat.addItem(claveCat);
        }
        GridBagConstraints gbc_comboBoxCat = new GridBagConstraints();
        gbc_comboBoxCat.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxCat.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxCat.gridx = 1;
        gbc_comboBoxCat.gridy = 4;
        getContentPane().add(comboBoxCat, gbc_comboBoxCat)
        
        ;
        
        lblFechaAlta = new JLabel("Fecha de Alta:");
        GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
        gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
        gbc_lblFechaAlta.insets = new Insets(0,0,5,5);
        gbc_lblFechaAlta.gridx = 0;
        gbc_lblFechaAlta.gridy = 5;
        getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

        JPanel panelFecha = new JPanel();
        panelFecha.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        cmbDiaAlta = new JComboBox<>();
        cmbMesAlta = new JComboBox<>();
        cmbAnioAlta = new JComboBox<>();

        for (int d = 1; d <= 31; d++) cmbDiaAlta.addItem(d);

        String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio",
                          "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        
        for (String m : meses) cmbMesAlta.addItem(m);


        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = currentYear; i <= currentYear + 5; i++) cmbAnioAlta.addItem(i);

  
        panelFecha.add(cmbDiaAlta);
        panelFecha.add(cmbMesAlta);
        panelFecha.add(cmbAnioAlta);

 
        GridBagConstraints gbc_panelFecha = new GridBagConstraints();
        gbc_panelFecha.insets = new Insets(0, 0, 5, 5);
        gbc_panelFecha.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelFecha.gridx = 1;
        gbc_panelFecha.gridy = 5;
        getContentPane().add(panelFecha, gbc_panelFecha);
        
      
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));
        
        Action Aceptar = new aceptarEvento();
        Action Cancelar = new cancelarEvento();
        btnAceptar = new JButton(Aceptar);
       
        btnAceptar.setText("Aceptar");
        btnCancelar = new JButton(Cancelar);
        
        btnCancelar.setText("Cancelar");
        
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        
        GridBagConstraints gbc_panelBotones = new GridBagConstraints();
        gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
        gbc_panelBotones.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelBotones.gridx = 1;
        gbc_panelBotones.gridy = 6;
        getContentPane().add(panelBotones, gbc_panelBotones);
          
    }
    private class aceptarEvento extends AbstractAction {
        public aceptarEvento() {
            putValue(NAME, "aceptarEvento");
            putValue(SHORT_DESCRIPTION, "Dar de alta un evento");
        }
        public void actionPerformed(ActionEvent e) {
            altaDeEvento(e);
        }
    }

    protected void altaDeEvento(ActionEvent aceptar) {
        String nombreEvento = textFieldNombre.getText();
        String descripcionEvento = textFieldDesc.getText();
        String siglaEvento = textFieldSigla.getText();
        String categoriaEvento = (String) comboBoxCat.getSelectedItem();
        int dia = (Integer) cmbDiaAlta.getSelectedItem();
        int mes = cmbMesAlta.getSelectedIndex() + 1;
        int anio = (Integer) cmbAnioAlta.getSelectedItem();

        if (checkFormulario()) {
            try {
                LocalDate fechaEvento = LocalDate.of(anio, mes, dia);
                DataEvento d = new DataEvento(nombreEvento, siglaEvento, fechaEvento, descripcionEvento);
                controlEvt.nuevoEvento(d, categoriaEvento);
                JOptionPane.showMessageDialog(this, "Evento registrado correctamente",
                        "Alta de Evento", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                setVisible(false);
            } catch (EventoRepetidoExcepcion e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error al registrar evento", JOptionPane.ERROR_MESSAGE);
            } catch (EventoSinCategoriaExcepcion e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error al registrar evento", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class cancelarEvento extends AbstractAction {
        public cancelarEvento() {
            putValue(NAME, "cancelarEvento");
            putValue(SHORT_DESCRIPTION, "Cancelar el alta de evento");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
          
            limpiarFormulario();
            setVisible(false);
            
            
            dispose();  
        }
    }
    private boolean checkFormulario() {
        String nombreEvento = textFieldNombre.getText();
        String descripcionEvento = textFieldDesc.getText();
        String siglaEvento = textFieldSigla.getText();
        String categoria = (String) comboBoxCat.getSelectedItem();

        if (nombreEvento.isEmpty() || descripcionEvento.isEmpty() || siglaEvento.isEmpty()
                || categoria == null || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos",
                    "Alta de Evento", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            int dia = (Integer) cmbDiaAlta.getSelectedItem();
            int mes = cmbMesAlta.getSelectedIndex() + 1;
            int anio = (Integer) cmbAnioAlta.getSelectedItem();
            
            
            LocalDate.of(anio, mes, dia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "La fecha ingresada no es válida", "Alta de Evento",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldNombre.setText("");
        textFieldSigla.setText("");
        textFieldDesc.setText("");
        if (cmbDiaAlta.getItemCount() > 0) cmbDiaAlta.setSelectedIndex(0);
        if (cmbMesAlta.getItemCount() > 0) cmbMesAlta.setSelectedIndex(0);
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        if (cmbAnioAlta.getItemCount() > 0) cmbAnioAlta.setSelectedItem(currentYear);
        if (comboBoxCat.getItemCount() > 0) comboBoxCat.setSelectedIndex(0);
    }
    
    public void cargarCategorias() {
    	comboBoxCat.removeAllItems();
        ManejadorEvento me = ManejadorEvento.getInstance();
        List<String> categorias = me.getCategorias();
        if (categorias != null) {
            for (String cat : categorias) {
                comboBoxCat.addItem(cat);
            }
        }
    }
        
}