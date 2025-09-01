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
		setBounds(10, 40, 653, 576);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Evento:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxEventos = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEventos = new GridBagConstraints();
		gbc_comboBoxEventos.gridwidth = 11;
		gbc_comboBoxEventos.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEventos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEventos.gridx = 6;
		gbc_comboBoxEventos.gridy = 1;
		getContentPane().add(comboBoxEventos, gbc_comboBoxEventos);
		
		JLabel lblSeleccionarEdicion = new JLabel("Seleccionar Edicion:");
		GridBagConstraints gbc_lblSeleccionarEdicion = new GridBagConstraints();
		gbc_lblSeleccionarEdicion.gridwidth = 3;
		gbc_lblSeleccionarEdicion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarEdicion.gridx = 1;
		gbc_lblSeleccionarEdicion.gridy = 3;
		getContentPane().add(lblSeleccionarEdicion, gbc_lblSeleccionarEdicion);
		
		comboBoxEdiciones = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEdiciones = new GridBagConstraints();
		gbc_comboBoxEdiciones.gridwidth = 11;
		gbc_comboBoxEdiciones.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEdiciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEdiciones.gridx = 6;
		gbc_comboBoxEdiciones.gridy = 3;
		getContentPane().add(comboBoxEdiciones, gbc_comboBoxEdiciones);
		
		JLabel lblSeleccionarTR = new JLabel("Seleccionar Tipo de Registro:");
		GridBagConstraints gbc_lblSeleccionarTR = new GridBagConstraints();
		gbc_lblSeleccionarTR.gridwidth = 3;
		gbc_lblSeleccionarTR.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarTR.gridx = 1;
		gbc_lblSeleccionarTR.gridy = 5;
		getContentPane().add(lblSeleccionarTR, gbc_lblSeleccionarTR);
		
		comboBoxTR = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxTR = new GridBagConstraints();
		gbc_comboBoxTR.gridwidth = 11;
		gbc_comboBoxTR.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTR.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTR.gridx = 6;
		gbc_comboBoxTR.gridy = 5;
		getContentPane().add(comboBoxTR, gbc_comboBoxTR);
		
		JLabel lblNewLabel_1 = new JLabel("Datos Tipo Registro");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 5;
		gbc_lblNewLabel_1.gridy = 7;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 9;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 7;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 5;
		gbc_textNombre.gridy = 9;
		getContentPane().add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 3;
		gbc_lblNewLabel_2_1.gridy = 11;
		getContentPane().add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		textDesc = new JTextArea();
		textDesc.setEditable(false);
		GridBagConstraints gbc_textDesc = new GridBagConstraints();
		gbc_textDesc.gridheight = 3;
		gbc_textDesc.gridwidth = 7;
		gbc_textDesc.insets = new Insets(0, 0, 5, 5);
		gbc_textDesc.fill = GridBagConstraints.BOTH;
		gbc_textDesc.gridx = 5;
		gbc_textDesc.gridy = 11;
		getContentPane().add(textDesc, gbc_textDesc);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Costo:");
		GridBagConstraints gbc_lblNewLabel_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1.gridx = 3;
		gbc_lblNewLabel_2_1_1.gridy = 14;
		getContentPane().add(lblNewLabel_2_1_1, gbc_lblNewLabel_2_1_1);
		
		textCosto = new JTextField();
		textCosto.setEditable(false);
		textCosto.setColumns(10);
		GridBagConstraints gbc_textCosto = new GridBagConstraints();
		gbc_textCosto.gridwidth = 7;
		gbc_textCosto.insets = new Insets(0, 0, 5, 5);
		gbc_textCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCosto.gridx = 5;
		gbc_textCosto.gridy = 14;
		getContentPane().add(textCosto, gbc_textCosto);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Cupo:");
		GridBagConstraints gbc_lblNewLabel_2_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1_1.gridx = 3;
		gbc_lblNewLabel_2_1_1_1.gridy = 16;
		getContentPane().add(lblNewLabel_2_1_1_1, gbc_lblNewLabel_2_1_1_1);
		
		textCupo = new JTextField();
		textCupo.setEditable(false);
		textCupo.setColumns(10);
		GridBagConstraints gbc_textCupo = new GridBagConstraints();
		gbc_textCupo.gridwidth = 7;
		gbc_textCupo.insets = new Insets(0, 0, 5, 5);
		gbc_textCupo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCupo.gridx = 5;
		gbc_textCupo.gridy = 16;
		getContentPane().add(textCupo, gbc_textCupo);
		
		btnConfirmar = new JButton("Confirmar");
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConfirmar.gridwidth = 4;
		gbc_btnConfirmar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirmar.gridx = 6;
		gbc_btnConfirmar.gridy = 17;
		getContentPane().add(btnConfirmar, gbc_btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
				
			}
		});
		
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
				if(eventoSeleccionado != null && !comboBoxEventos.getSelectedItem().equals("No hay eventos")) {
					cargarEdicionesEvento(ctrlEventos.listarEdiciones(eventoSeleccionado));
					comboBoxEdiciones.setEnabled(true);
				}
			}
		});
		
		comboBoxEdiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cargandoEdiciones) {
					String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
					if(edicionSeleccionada != null) {
					
					cargarTiposRegistros((String) comboBoxEventos.getSelectedItem(), (String) comboBoxEdiciones.getSelectedItem());
					comboBoxTR.setEnabled(true);
					}
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
            } else {
                comboBoxEdiciones.addItem("No hay ediciones");
            }
            comboBoxEdiciones.setSelectedIndex(-1);
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
	                for (String tr : TiposRegistros)
	                    comboBoxTR.addItem(tr);
	            } else {
	                comboBoxTR.addItem("No hay tipos de registro");
	            }
	            comboBoxTR.setSelectedIndex(-1);
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

}
