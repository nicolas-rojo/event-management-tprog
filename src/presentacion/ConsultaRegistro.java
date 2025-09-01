package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import excepciones.ErrorDetallesRegistroException;
import logica.datatypes.DataDetalleRegistro;
import logica.datatypes.ParEdicionRegistro;
import logica.interfaces.IUsuario;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ConsultaRegistro extends JInternalFrame {
	private IUsuario ctrlUsuarios;
	
	private JComboBox<String> comboBoxAsistentes;
	private JComboBox<ParEdicionRegistro> comboBoxRegistros;
	private JTextField textFieldEdicion;
	private JTextField textFieldTR;
	private JTextField textFieldCosto;
	private JTextField textFieldFecha;
	public ConsultaRegistro(IUsuario iCU) {
		
		ctrlUsuarios = iCU;

		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(null);
		setTitle("Consultar Registro");
		setBounds(100, 100, 468, 323);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Usuario:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(16, 22, 130, 12);
		getContentPane().add(lblNewLabel);
		
		comboBoxAsistentes = new JComboBox();
		comboBoxAsistentes.setBounds(156, 18, 286, 20);
		getContentPane().add(comboBoxAsistentes);
		comboBoxAsistentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String asistenteSeleccionado = (String) comboBoxAsistentes.getSelectedItem();
				if (asistenteSeleccionado == null || asistenteSeleccionado.equals("No hay asistentes")) {
					comboBoxRegistros.setEnabled(false);
					return;
				}
				
				comboBoxRegistros.setEnabled(true);
				cargarRegistros(ctrlUsuarios.getRegistrosAsistente(asistenteSeleccionado));
			}
		});
		
		JLabel lblSeleccionarRegistro = new JLabel("Seleccionar Registro:");
		lblSeleccionarRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarRegistro.setBounds(16, 60, 130, 12);
		getContentPane().add(lblSeleccionarRegistro);
		
		comboBoxRegistros = new JComboBox();
		comboBoxRegistros.setBounds(156, 56, 286, 20);
		getContentPane().add(comboBoxRegistros);
		comboBoxRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParEdicionRegistro regSeleccionado = (ParEdicionRegistro) comboBoxRegistros.getSelectedItem();
				if (regSeleccionado != null) {
					try {
						cargarDetalles(ctrlUsuarios.getDetallesRegistro((String) comboBoxAsistentes.getSelectedItem(), regSeleccionado));						
					} catch (ErrorDetallesRegistroException er) {
						JOptionPane.showMessageDialog(ConsultaRegistro.this, er.getMessage(), "Consulta Registro", JOptionPane.ERROR_MESSAGE);
					} catch (Exception er) {
						JOptionPane.showMessageDialog(ConsultaRegistro.this, er.getMessage(), "Consulta Registro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JLabel lblSeleccionarRegistro_1 = new JLabel("Datos Registro:");
		lblSeleccionarRegistro_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarRegistro_1.setBounds(176, 94, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1);
		
		JLabel lblSeleccionarRegistro_1_1 = new JLabel("Edicion:");
		lblSeleccionarRegistro_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarRegistro_1_1.setBounds(31, 120, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1_1);
		
		textFieldEdicion = new JTextField();
		textFieldEdicion.setEditable(false);
		textFieldEdicion.setBounds(156, 117, 286, 18);
		getContentPane().add(textFieldEdicion);
		textFieldEdicion.setColumns(10);
		
		JLabel lblSeleccionarRegistro_1_1_1 = new JLabel("Tipo de Registro:");
		lblSeleccionarRegistro_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarRegistro_1_1_1.setBounds(31, 155, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1_1_1);
		
		JLabel lblSeleccionarRegistro_1_1_2 = new JLabel("Costo:");
		lblSeleccionarRegistro_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarRegistro_1_1_2.setBounds(31, 190, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1_1_2);
		
		textFieldTR = new JTextField();
		textFieldTR.setEditable(false);
		textFieldTR.setColumns(10);
		textFieldTR.setBounds(156, 152, 286, 18);
		getContentPane().add(textFieldTR);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setEditable(false);
		textFieldCosto.setColumns(10);
		textFieldCosto.setBounds(156, 187, 286, 18);
		getContentPane().add(textFieldCosto);
		
		JLabel lblSeleccionarRegistro_1_1_2_1 = new JLabel("Fecha de Registro:");
		lblSeleccionarRegistro_1_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarRegistro_1_1_2_1.setBounds(31, 221, 115, 20);
		getContentPane().add(lblSeleccionarRegistro_1_1_2_1);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(156, 222, 286, 18);
		getContentPane().add(textFieldFecha);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBounds(349, 262, 93, 20);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        limpiarYCerrar();
		    }
		});
	}
	
	public void cargarAsistentes() {
		try {
			comboBoxAsistentes.removeAllItems();
			List<String> asistentes = ctrlUsuarios.listarAsistentes();
			if (asistentes != null && !asistentes.isEmpty()) {
				for (String a : asistentes) {
					comboBoxAsistentes.addItem(a);
				}
			} else {
				comboBoxRegistros.setEnabled(false);
				comboBoxAsistentes.addItem("No hay asistentes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxAsistentes.removeAllItems();
			comboBoxAsistentes.addItem("No hay asistentes");
		}
	}
	
	public void cargarRegistros(List<ParEdicionRegistro> regs) {
		try {
			comboBoxRegistros.removeAllItems();
			if (regs != null && !regs.isEmpty()) {
				for(ParEdicionRegistro p : regs) {
					comboBoxRegistros.addItem(p);					
				}
			} else {
				comboBoxRegistros.addItem(null);
				limpiarDatos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxRegistros.removeAllItems();
			comboBoxRegistros.addItem(null);
		}
	}
	
	public void cargarDetalles(DataDetalleRegistro datos) {
		textFieldEdicion.setText(datos.getNombreEdicion());
	    textFieldTR.setText(datos.getNombreTR());
	    textFieldCosto.setText(String.valueOf(datos.getCosto()));
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    textFieldFecha.setText(datos.getFecha().format(formatter)); 
	}
	
	public void limpiarYCerrar() {
		textFieldEdicion.setText("");
		textFieldTR.setText("");
	    textFieldCosto.setText("");
	    textFieldFecha.setText("");
	    
	    comboBoxAsistentes.removeAllItems();
	    comboBoxRegistros.removeAllItems();
	    comboBoxRegistros.setEnabled(false);
	    
	    setVisible(false);
	}
	
	public void limpiarDatos() {
		comboBoxRegistros.removeAllItems();
		textFieldEdicion.setText("");
		textFieldTR.setText("");
	    textFieldCosto.setText("");
	    textFieldFecha.setText("");
	}
}
