package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import excepciones.TipoDeRegistroRepetidoException;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTRegistro;

//--------------------------------------------------//Eliminar despues de las pruebas
import logica.interfaces.IEventos;
import logica.EdicionEvento;
import logica.Evento;
import logica.ctrlmanejador.ManejadorEvento;
//--------------------------------------------------
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")

public class CrearTipoRegistro extends JInternalFrame {
	
	private IEventos ctrlEventos;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxEdiciones;
    private JTextField textFieldNombre;
    private JTextArea textAreaDescripcion;
    private JTextField textFieldCosto;
    private JTextField textFieldCupo;
    private JLabel lblSeleccionarEvento;
    private JLabel lblSeleccionarEdicion;
    private JLabel lblIngreseNombre;
    private JLabel lblIngreseDescripcion;
    private JLabel lblIngreseCosto;
    private JLabel lblIngreseCupo;
    private JButton btnAceptar;
    private JButton btnCancelar;
	
    
	public CrearTipoRegistro(IEventos ie) {
		
		ctrlEventos = ie;
		
		setTitle("Alta Tipo Registro");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
        setBounds(10, 40, 493, 543);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblSeleccionarEvento = new JLabel("Seleccionar Evento:");
		GridBagConstraints gbc_lblSeleccionarEvento = new GridBagConstraints();
		gbc_lblSeleccionarEvento.gridheight = 2;
		gbc_lblSeleccionarEvento.gridwidth = 4;
		gbc_lblSeleccionarEvento.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarEvento.gridx = 0;
		gbc_lblSeleccionarEvento.gridy = 0;
		getContentPane().add(lblSeleccionarEvento, gbc_lblSeleccionarEvento);
		
		comboBoxEventos = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEventos_1 = new GridBagConstraints();
		gbc_comboBoxEventos_1.gridheight = 2;
		gbc_comboBoxEventos_1.gridwidth = 7;
		gbc_comboBoxEventos_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEventos_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEventos_1.gridx = 4;
		gbc_comboBoxEventos_1.gridy = 0;
		getContentPane().add(comboBoxEventos, gbc_comboBoxEventos_1);
		
		lblSeleccionarEdicion = new JLabel("Seleccionar Edicion:");
		GridBagConstraints gbc_lblSeleccionarEdicion = new GridBagConstraints();
		gbc_lblSeleccionarEdicion.gridheight = 2;
		gbc_lblSeleccionarEdicion.gridwidth = 4;
		gbc_lblSeleccionarEdicion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarEdicion.gridx = 0;
		gbc_lblSeleccionarEdicion.gridy = 2;
		getContentPane().add(lblSeleccionarEdicion, gbc_lblSeleccionarEdicion);
		
		comboBoxEdiciones = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 7;
		gbc_comboBox_1.gridheight = 2;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 4;
		gbc_comboBox_1.gridy = 2;
		getContentPane().add(comboBoxEdiciones, gbc_comboBox_1);
		
		JLabel lblIngresarDato = new JLabel("Ingresar Datos");
		GridBagConstraints gbc_lblIngresarDato = new GridBagConstraints();
		gbc_lblIngresarDato.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngresarDato.gridx = 4;
		gbc_lblIngresarDato.gridy = 4;
		getContentPane().add(lblIngresarDato, gbc_lblIngresarDato);
		
		lblIngreseNombre = new JLabel("Ingresar Nombre:");
		GridBagConstraints gbc_lblIngresarNombre = new GridBagConstraints();
		gbc_lblIngresarNombre.anchor = GridBagConstraints.NORTH;
		gbc_lblIngresarNombre.gridwidth = 3;
		gbc_lblIngresarNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngresarNombre.gridx = 1;
		gbc_lblIngresarNombre.gridy = 6;
		getContentPane().add(lblIngreseNombre, gbc_lblIngresarNombre);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 6;
		getContentPane().add(textFieldNombre, gbc_textField);
		textFieldNombre.setColumns(10);
		
		lblIngreseDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblIngresarNombre_1 = new GridBagConstraints();
		gbc_lblIngresarNombre_1.gridheight = 2;
		gbc_lblIngresarNombre_1.gridwidth = 3;
		gbc_lblIngresarNombre_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngresarNombre_1.gridx = 1;
		gbc_lblIngresarNombre_1.gridy = 8;
		getContentPane().add(lblIngreseDescripcion, gbc_lblIngresarNombre_1);
		
		textAreaDescripcion = new JTextArea(5, 20); 
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textAreaDescripcion);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		GridBagConstraints gbc_scroll = new GridBagConstraints();
		gbc_scroll.gridheight = 3;
		gbc_scroll.gridwidth = 7;
		gbc_scroll.insets = new Insets(0, 0, 5, 5);
		gbc_scroll.fill = GridBagConstraints.BOTH;
		gbc_scroll.gridx = 4;
		gbc_scroll.gridy = 8;
		
		getContentPane().add(scrollPane, gbc_scroll);
		
		
		lblIngreseCosto = new JLabel("Ingresar Costo:");
		GridBagConstraints gbc_lblIngresarNombre_2 = new GridBagConstraints();
		gbc_lblIngresarNombre_2.gridwidth = 3;
		gbc_lblIngresarNombre_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngresarNombre_2.gridx = 1;
		gbc_lblIngresarNombre_2.gridy = 12;
		getContentPane().add(lblIngreseCosto, gbc_lblIngresarNombre_2);
		
		textFieldCosto = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 7;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 12;
		getContentPane().add(textFieldCosto, gbc_textField_1);
		textFieldCosto.setColumns(10);
		
		lblIngreseCupo = new JLabel("Ingresar Cupo:");
		GridBagConstraints gbc_lblIngresarNombre_3 = new GridBagConstraints();
		gbc_lblIngresarNombre_3.gridwidth = 3;
		gbc_lblIngresarNombre_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngresarNombre_3.gridx = 1;
		gbc_lblIngresarNombre_3.gridy = 14;
		getContentPane().add(lblIngreseCupo, gbc_lblIngresarNombre_3);
		
		textFieldCupo = new JTextField();
		textFieldCupo.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 7;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 14;
		getContentPane().add(textFieldCupo, gbc_textField_2);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.gridwidth = 6;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 15;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
				
			}
		});
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventoBtnAceptar) {
                cmdAltaRegistroActionPerformed(eventoBtnAceptar);
            }
        });
		
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.gridheight = 3;
		gbc_btnAceptar.gridwidth = 2;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 0;
		panel.add(btnAceptar, gbc_btnAceptar);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridheight = 3;
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 0;
		panel.add(btnCancelar, gbc_btnCancelar);
		
		
		//RELLENO LOS COMBOBOX
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
				if(eventoSeleccionado != null) {
					comboBoxEdiciones.setEnabled(true);
					cargarEdicionesEvento(ctrlEventos.listarEdiciones(eventoSeleccionado));
				}
			}
		});
	}
	
	protected void cmdAltaRegistroActionPerformed(ActionEvent eventoBtnAceptar) {
		String evento = (String) this.comboBoxEventos.getSelectedItem();
		String edicion = (String) this.comboBoxEdiciones.getSelectedItem();
        String nombreTR = this.textFieldNombre.getText();
        String descripcionTR = this.textAreaDescripcion.getText();
        String costoTR = this.textFieldCosto.getText();
        String cupoTR = this.textFieldCupo.getText();

        if (checkFormulario()) {
            try {
            	DataTRegistro datosTRegistro = new DataTRegistro(nombreTR, descripcionTR, Float.parseFloat(costoTR), Integer.parseInt(cupoTR));
                ctrlEventos.nuevoTipoRegistro(datosTRegistro, evento, edicion);

                JOptionPane.showMessageDialog(this, "El Tipo de Registro se dio de alta correctamente", "Alta Tipo Registro",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (TipoDeRegistroRepetidoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Alta Tipo Registro", JOptionPane.ERROR_MESSAGE);
            } 

            limpiarFormulario();
            setVisible(false);
        }
	}
    
	
	private boolean checkFormulario() {
		String evento = (String) this.comboBoxEventos.getSelectedItem();
		String edicion = (String) this.comboBoxEdiciones.getSelectedItem();
        String nombreTR = this.textFieldNombre.getText();
        String descripcionTR = this.textAreaDescripcion.getText();
        String costoTR = this.textFieldCosto.getText().trim();
        String cupoTR = this.textFieldCupo.getText().trim();

        if (nombreTR.isEmpty() || descripcionTR.isEmpty() || costoTR.isEmpty() || cupoTR.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta Tipo Registro",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(evento.isBlank() || edicion.isBlank()){
        	 JOptionPane.showMessageDialog(this, "Seleccione un Evento y una Edicion", "Alta Tipo Registro",
                     JOptionPane.ERROR_MESSAGE);
             return false;
        }
        // Validar costo(float)
        try {
            Float.parseFloat(costoTR);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El costo debe ser un número válido", 
                    "Alta Tipo Registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar cupo (int)
        try {
            Integer.parseInt(cupoTR);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El cupo debe ser un número entero válido", 
                    "Alta Tipo Registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       
        return true;
    }
	
	
	//Se cargan los eventos en el comboBoxEventos
	
	
	public void cargarEventos() {
		
	    try {
	    	
	    	comboBoxEventos.removeAllItems();
	        List<String> eventos = ctrlEventos.listarEventos(); // <-- esta es la lista que discutíamos
	        
	        if (eventos != null && !eventos.isEmpty()) {

	            for (String evento : eventos) {
	                comboBoxEventos.addItem(evento);
	            }
	            
	        } else {
	            comboBoxEventos.addItem("No hay eventos");;
	        }
	        comboBoxEventos.setSelectedItem(null);	
	        comboBoxEdiciones.setEnabled(false);				//Si no se selecciona un evento se bloquea el combo de ediciones
	        
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	        comboBoxEventos.removeAllItems();
	        comboBoxEventos.addItem("No hay eventos");
	    }
	}
	
	
	public void cargarEdicionesEvento(List<String> edicionesEvento) {
		
		comboBoxEdiciones.removeAllItems();
		if (edicionesEvento != null && !edicionesEvento.isEmpty()) {
            
            for (String edicion : edicionesEvento) {
                comboBoxEdiciones.addItem(edicion);
            }
        }else{
        	comboBoxEdiciones.addItem("No hay ediciones");
        }
	

	}
	
	private void limpiarFormulario() {
		comboBoxEventos.setSelectedItem(0);
		comboBoxEdiciones.setSelectedItem(0);
        textFieldNombre.setText("");
        textAreaDescripcion.setText("");
        textFieldCosto.setText("");
        textFieldCupo.setText("");
    }
}
