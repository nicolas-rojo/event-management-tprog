package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;

import javax.swing.JButton;

public class RegistroEdicionEvento extends JInternalFrame {
	private IEventos ctrlEventos;
	private IUsuario ctrlUsuarios;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxEdiciones;
	private JComboBox<String> comboBoxAsistentes;
	
	public RegistroEdicionEvento(IUsuario iCU, IEventos iEV) {
		ctrlEventos = iEV;
		ctrlUsuarios = iCU;
		
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Registrar a Edición de Evento");
		setBounds(100, 100, 415, 210);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Evento:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setBounds(10, 15, 181, 12);
		getContentPane().add(lblNewLabel);
		
		comboBoxEventos = new JComboBox<String>();
		comboBoxEventos.setBounds(201, 10, 188, 20);
		getContentPane().add(comboBoxEventos);
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
				if (eventoSeleccionado != null) {
					comboBoxEdiciones.setEnabled(true);
					cargarEdicionesEvento(ctrlEventos.listarEdiciones(eventoSeleccionado));
				}
			}
		});
		
		
		comboBoxEdiciones = new JComboBox();
		comboBoxEdiciones.setBounds(201, 46, 188, 20);
		getContentPane().add(comboBoxEdiciones);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccionar Edición:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 50, 181, 12);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccionar Tipo de Registro:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 85, 181, 12);
		getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(201, 81, 188, 20);
		getContentPane().add(comboBox_2);
		
		JLabel lblNewLabel_3 = new JLabel("Seleccionar Asistente:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 120, 181, 12);
		getContentPane().add(lblNewLabel_3);
		
		comboBoxAsistentes = new JComboBox();
		comboBoxAsistentes.setBounds(201, 116, 188, 20);
		getContentPane().add(comboBoxAsistentes);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(201, 150, 84, 20);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(305, 150, 84, 20);
		getContentPane().add(btnCancelar);

		
		
	}
	
	
	public void cargarEventos() {
		try {
			comboBoxEventos.removeAllItems();
			List<String> eventos = ctrlEventos.listarEventos();
			System.out.println(eventos);
			if (eventos != null && !eventos.isEmpty()) {
				for (String e : eventos)
					comboBoxEventos.addItem(e);
			} else {
				comboBoxEventos.addItem("No hay eventos");
			}
			comboBoxEventos.setSelectedItem(null);
			comboBoxEdiciones.setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxEventos.removeAllItems();
			comboBoxEventos.addItem("No hay eventos");
		}
	}
	
	public void cargarEdicionesEvento(List<String> edicionesEvento) {
		try {
			comboBoxEdiciones.removeAllItems();
			if (edicionesEvento != null && !edicionesEvento.isEmpty()) {
				for (String e : edicionesEvento)
					comboBoxEdiciones.addItem(e);
			} else {
				comboBoxEdiciones.addItem("No hay ediciones");
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxEdiciones.removeAllItems();
			comboBoxEdiciones.addItem("No hay ediciones");
		}
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
				comboBoxEdiciones.addItem("No hay ediciones");
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxEdiciones.removeAllItems();
			comboBoxEdiciones.addItem("No hay ediciones");
		}
	}
}
