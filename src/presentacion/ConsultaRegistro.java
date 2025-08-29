package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logica.datatypes.ParEdicionRegistro;
import logica.interfaces.IUsuario;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

public class ConsultaRegistro extends JInternalFrame {
	private IUsuario ctrlUsuarios;
	
	private JComboBox<String> comboBoxAsistentes;
	private JComboBox<String> comboBoxRegistros;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public ConsultaRegistro(IUsuario iCU) {
		
		ctrlUsuarios = iCU;

		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(null);
		setTitle("Consultar Registro");
		setBounds(100, 100, 415, 255);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Usuario:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 25, 115, 12);
		getContentPane().add(lblNewLabel);
		
		comboBoxAsistentes = new JComboBox();
		comboBoxAsistentes.setBounds(156, 18, 167, 20);
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
		lblSeleccionarRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarRegistro.setBounds(25, 60, 115, 12);
		getContentPane().add(lblSeleccionarRegistro);
		
		comboBoxRegistros = new JComboBox();
		comboBoxRegistros.setBounds(156, 56, 167, 20);
		getContentPane().add(comboBoxRegistros);
		
		JLabel lblSeleccionarRegistro_1 = new JLabel("Seleccionar Registro:");
		lblSeleccionarRegistro_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarRegistro_1.setBounds(115, 95, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1);
		
		JLabel lblSeleccionarRegistro_1_1 = new JLabel("Edicion:");
		lblSeleccionarRegistro_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarRegistro_1_1.setBounds(25, 120, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(156, 117, 167, 18);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSeleccionarRegistro_1_1_1 = new JLabel("Tipo de Registro:");
		lblSeleccionarRegistro_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarRegistro_1_1_1.setBounds(25, 155, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1_1_1);
		
		JLabel lblSeleccionarRegistro_1_1_2 = new JLabel("Costo:");
		lblSeleccionarRegistro_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarRegistro_1_1_2.setBounds(25, 190, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1_1_2);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(156, 152, 167, 18);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(156, 187, 167, 18);
		getContentPane().add(textField_2);
		
		JLabel lblSeleccionarRegistro_1_1_2_1 = new JLabel("Fecha de Registro:");
		lblSeleccionarRegistro_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarRegistro_1_1_2_1.setBounds(25, 225, 115, 12);
		getContentPane().add(lblSeleccionarRegistro_1_1_2_1);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(156, 222, 167, 18);
		getContentPane().add(textField_3);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBounds(239, 260, 84, 20);
		getContentPane().add(btnNewButton);
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
					comboBoxRegistros.addItem(p.toString());					
				}
			} else {
				comboBoxRegistros.addItem("No hay registros");
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxRegistros.removeAllItems();
			comboBoxRegistros.addItem("No hay asistentes");
		}
	}
}
