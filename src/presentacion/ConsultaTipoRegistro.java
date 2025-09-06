package presentacion;

import javax.swing.JInternalFrame;

import logica.datatypes.DataTRegistro;
import logica.interfaces.IEventos;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")

public class ConsultaTipoRegistro extends JInternalFrame {
	
	private IEventos ctrlEventos;
	private JTextField textNombre;
	private JTextField textCosto;
	private JTextField textCupo;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxEdiciones;
	private JComboBox<String> comboBoxTR;
	private JButton btnConfirmar;
	private JTextArea textDesc;
	private boolean cargandoEdiciones;
	private boolean cargandoTR;
	
	
	public ConsultaTipoRegistro(IEventos ie) {
		
		ctrlEventos = ie;
		
		setTitle("Consulta Tipo Registro");
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(10, 40, 475, 576);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Evento:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(27, 34, 139, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxEventos = new JComboBox<String>();
		comboBoxEventos.setBounds(176, 30, 256, 22);
		getContentPane().add(comboBoxEventos);
		
		JLabel lblSeleccionarEdicion = new JLabel("Seleccionar Edicion:");
		lblSeleccionarEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarEdicion.setBounds(27, 91, 139, 14);
		getContentPane().add(lblSeleccionarEdicion);
		
		comboBoxEdiciones = new JComboBox<String>();
		comboBoxEdiciones.setBounds(176, 87, 256, 22);
		getContentPane().add(comboBoxEdiciones);
		
		JLabel lblSeleccionarTR = new JLabel("Seleccionar Tipo de Registro:");
		lblSeleccionarTR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarTR.setBounds(-150, 148, 316, 14);
		getContentPane().add(lblSeleccionarTR);
		
		comboBoxTR = new JComboBox<String>();
		comboBoxTR.setBounds(176, 144, 256, 22);
		getContentPane().add(comboBoxTR);
		
		JLabel lblNewLabel_1 = new JLabel("Datos Tipo Registro");
		lblNewLabel_1.setBounds(207, 204, 168, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(0, 253, 166, 14);
		getContentPane().add(lblNewLabel_2);
		
		textNombre = new JTextField();
		textNombre.setBounds(176, 250, 256, 20);
		textNombre.setEditable(false);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Descripcion:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(0, 305, 166, 19);
		getContentPane().add(lblNewLabel_2_1);
		
		textDesc = new JTextArea();
		textDesc.setBounds(176, 305, 256, 84);
		textDesc.setEditable(false);
		getContentPane().add(textDesc);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Costo:");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setBounds(12, 421, 154, 14);
		getContentPane().add(lblNewLabel_2_1_1);
		
		textCosto = new JTextField();
		textCosto.setBounds(176, 418, 256, 20);
		textCosto.setEditable(false);
		textCosto.setColumns(10);
		getContentPane().add(textCosto);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Cupo:");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setBounds(10, 466, 156, 14);
		getContentPane().add(lblNewLabel_2_1_1_1);
		
		textCupo = new JTextField();
		textCupo.setBounds(176, 463, 256, 20);
		textCupo.setEditable(false);
		textCupo.setColumns(10);
		getContentPane().add(textCupo);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(281, 512, 151, 23);
		getContentPane().add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
				
			}
		});
		
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
				comboBoxEdiciones.removeAllItems();
				comboBoxTR.removeAllItems();
				if(eventoSeleccionado == null || comboBoxEventos.getSelectedItem().equals("No hay eventos") ) {
					comboBoxEdiciones.setEnabled(false);
					comboBoxTR.setEnabled(false);
					return;
				}
				comboBoxEdiciones.setEnabled(true);
				cargarEdicionesEvento(ctrlEventos.listarEdiciones(eventoSeleccionado));
				limpiarDatos();
			}
		});
		
		comboBoxEdiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cargandoEdiciones) {
					String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
					String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
					if(edicionSeleccionada == null || edicionSeleccionada.equals("No hay ediciones")) {
						comboBoxTR.setEnabled(false);
						return;
					}
					cargarTiposRegistros(eventoSeleccionado, edicionSeleccionada);					
				}
			}
		});
		
		comboBoxTR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cargandoTR) {
					String TipoRegistroSeleccionado = (String) comboBoxTR.getSelectedItem();
					String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
			        String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
					if(TipoRegistroSeleccionado != null && eventoSeleccionado!= null && edicionSeleccionada != null && !comboBoxEdiciones.getSelectedItem().equals("No hay ediciones")) {
						if(comboBoxEdiciones.isEnabled()) {
							cargarDatosTR(eventoSeleccionado, edicionSeleccionada, TipoRegistroSeleccionado);
						}
					}
				}
			}
		});
		
		
	}

	
	public void cargarEventos() {
        try {
            comboBoxEventos.removeAllItems();
            List<String> eventos = ctrlEventos.listarEventos();
            if (eventos != null && !eventos.isEmpty()) {
                for (String e : eventos)
                    comboBoxEventos.addItem(e);
            } else {
                comboBoxEventos.addItem("No hay eventos");
            }
            comboBoxEventos.setSelectedIndex(-1);
            comboBoxEdiciones.setSelectedIndex(-1);
            comboBoxTR.setSelectedIndex(-1);
            comboBoxEdiciones.setEnabled(false);
            comboBoxTR.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
            comboBoxEventos.removeAllItems();
        }
    }
	
	public void mostrarDetallesTipoRegistro(String evento, String edicion, String tipoRegistro) {
	    limpiarFormulario();
	    cargarEventos();

	    comboBoxEventos.setSelectedItem(evento);
	    comboBoxEdiciones.setSelectedItem(edicion);
	    comboBoxTR.setSelectedItem(tipoRegistro);

	    // cargar directamente los datos sin esperar
	    cargarDatosTR(evento, edicion, tipoRegistro);

	    setTitle("Consulta Tipo Registro: " + tipoRegistro);
	}

	
	public void cargarEdicionesEvento(List<String> edicionesEvento) {
        try {
        	cargandoEdiciones = true;
            comboBoxEdiciones.removeAllItems();
            if (edicionesEvento != null && !edicionesEvento.isEmpty()) {
                for (String ed : edicionesEvento) {
                    comboBoxEdiciones.addItem(ed);
                }
                comboBoxEdiciones.setSelectedIndex(-1);
            } else {
                comboBoxEdiciones.addItem("No hay ediciones");
                comboBoxTR.addItem("No hay tipos de registro");
                comboBoxEdiciones.setEnabled(false);
            }
            cargandoEdiciones = false;
            comboBoxTR.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
            comboBoxEdiciones.removeAllItems();
        }
    }
	
	
	public void cargarTiposRegistros(String evento, String edicion) {
		 try {
	            comboBoxTR.removeAllItems();
	            cargandoTR = true;
	            List<String> TiposRegistros = ctrlEventos.listarTRegistros(evento, edicion);
	            if (TiposRegistros != null && !TiposRegistros.isEmpty()) {
	                for (String tr : TiposRegistros) {
	                    comboBoxTR.addItem(tr);
	                }
	                comboBoxTR.setSelectedIndex(-1);
	                comboBoxTR.setEnabled(true);
	            } else {
	            	comboBoxTR.addItem("No hay tipos de registro");
	                comboBoxTR.setEnabled(false);
	                
	            }
	            cargandoTR = false;
	        } catch (Exception e) {
	            e.printStackTrace();
	            comboBoxTR.removeAllItems();
	        }
	}
	
	
	public void cargarDatosTR(String evento, String edicion, String tipoRegistro){
		DataTRegistro datosTRegistro = ctrlEventos.getDataTRegistro(evento, edicion, tipoRegistro);
		textNombre.setText(datosTRegistro.getNombre());
		textDesc.setText(datosTRegistro.getDescr());
		textCosto.setText(String.valueOf(datosTRegistro.getCosto()));
		textCupo.setText(String.valueOf(datosTRegistro.getCupo()));
	}
	
	public void limpiarFormulario() {
		comboBoxEventos.removeAllItems();
		comboBoxEdiciones.removeAllItems();
		comboBoxTR.removeAllItems();
        textNombre.setText("");
        textDesc.setText("");
        textCosto.setText("");
        textCupo.setText("");
    }
	
	public void limpiarDatos() {
		comboBoxTR.setSelectedItem(-1);
        textNombre.setText("");
        textDesc.setText("");
        textCosto.setText("");
        textCupo.setText("");
    }

}
