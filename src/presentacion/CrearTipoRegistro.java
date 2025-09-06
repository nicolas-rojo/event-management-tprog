package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import excepciones.TipoDeRegistroRepetidoException;
import logica.datatypes.DataTRegistro;
import logica.interfaces.IEventos;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

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
        setBounds(10, 40, 493, 506);
		getContentPane().setLayout(null);
		
		lblSeleccionarEvento = new JLabel("Seleccionar Evento:");
		lblSeleccionarEvento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarEvento.setBounds(10, 20, 128, 14);
		getContentPane().add(lblSeleccionarEvento);
		
		comboBoxEventos = new JComboBox<String>();
		comboBoxEventos.setBounds(148, 16, 319, 22);
		getContentPane().add(comboBoxEventos);
		
		lblSeleccionarEdicion = new JLabel("Seleccionar Edicion:");
		lblSeleccionarEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarEdicion.setBounds(10, 80, 128, 14);
		getContentPane().add(lblSeleccionarEdicion);
		
		comboBoxEdiciones = new JComboBox<String>();
		comboBoxEdiciones.setBounds(148, 76, 319, 22);
		getContentPane().add(comboBoxEdiciones);
		
		JLabel lblIngresarDato = new JLabel("Ingresar Datos");
		lblIngresarDato.setBounds(225, 131, 85, 14);
		getContentPane().add(lblIngresarDato);
		
		lblIngreseNombre = new JLabel("Ingresar Nombre:");
		lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngreseNombre.setBounds(10, 172, 128, 14);
		getContentPane().add(lblIngreseNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(148, 169, 319, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblIngreseDescripcion = new JLabel("Descripcion:");
		lblIngreseDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngreseDescripcion.setBounds(25, 230, 113, 14);
		getContentPane().add(lblIngreseDescripcion);
		
		textAreaDescripcion = new JTextArea(5, 20); 
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textAreaDescripcion);
		scrollPane.setBounds(148, 224, 319, 99);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		getContentPane().add(scrollPane);
		
		
		lblIngreseCosto = new JLabel("Ingresar Costo:");
		lblIngreseCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngreseCosto.setBounds(16, 361, 122, 14);
		getContentPane().add(lblIngreseCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setBounds(148, 358, 319, 20);
		getContentPane().add(textFieldCosto);
		textFieldCosto.setColumns(10);
		
		lblIngreseCupo = new JLabel("Ingresar Cupo:");
		lblIngreseCupo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngreseCupo.setBounds(33, 416, 105, 14);
		getContentPane().add(lblIngreseCupo);
		
		textFieldCupo = new JTextField();
		textFieldCupo.setBounds(148, 413, 319, 20);
		textFieldCupo.setColumns(10);
		getContentPane().add(textFieldCupo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(256, 444, 85, 23);
		getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventoBtnAceptar) {
                cmdAltaRegistroActionPerformed(eventoBtnAceptar);
            }
        });
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(351, 444, 88, 23);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
				
			}
		});
		
		
		//RELLENO LOS COMBOBOX
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
				if(eventoSeleccionado != null) {
					limpiarFormulario();
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

        if (nombreTR.isEmpty() || descripcionTR.isEmpty() || costoTR.isEmpty() || cupoTR.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta Tipo Registro",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(evento == null || edicion == null || edicion.equals("No hay ediciones")){
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
        if(!(Integer.parseInt(cupoTR) > 0) & !(Float.parseFloat(costoTR) >= 0)) {
        	JOptionPane.showMessageDialog(this, "El cupo y el costo ingresados no son valido", "Alta Tipo Registro", JOptionPane.ERROR_MESSAGE);
        	return false;
        }else if(!(Integer.parseInt(cupoTR) > 0)) {
        	JOptionPane.showMessageDialog(this, "El cupo ingresado no es valido", "Alta Tipo Registro", JOptionPane.ERROR_MESSAGE);
        	return false;
        } else if(!(Float.parseFloat(costoTR) >= 0)) {
        	JOptionPane.showMessageDialog(this, "El costo ingresado no es valido", "Alta Tipo Registro", JOptionPane.ERROR_MESSAGE);
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
	        	comboBoxEventos.setSelectedItem(-1);	
	        	comboBoxEdiciones.setEnabled(false);				//Si no se selecciona un evento se bloquea el combo de ediciones
	        }
	        
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	        comboBoxEventos.removeAllItems();
	        comboBoxEventos.addItem("No hay eventos");
	    }
	}
	
	//Se cargan las ediciones del evento correspondiente
	
	
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
