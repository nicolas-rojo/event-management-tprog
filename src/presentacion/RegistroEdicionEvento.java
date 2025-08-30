package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import excepciones.AsistenteYaRegistrado;
import excepciones.NoHayCupoEdicionTRegistro;
import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;

import javax.swing.JButton;

public class RegistroEdicionEvento extends JInternalFrame {
	private IEventos ctrlEventos;
	private IUsuario ctrlUsuarios;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxEdiciones;
	private JComboBox<String> comboBoxAsistentes;
	private JComboBox<String> comboBoxTRegistros;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JDateChooser dateChooser;
	
	public RegistroEdicionEvento(IUsuario iCU, IEventos iEV) {
		ctrlEventos = iEV;
		ctrlUsuarios = iCU;
		
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Registrar a Edición de Evento");
		setBounds(100, 100, 415, 255);
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
				if (eventoSeleccionado == null || eventoSeleccionado.equals("No hay eventos")) {
					comboBoxEdiciones.setEnabled(false);
					return;
				}
				comboBoxEdiciones.setEnabled(true);
				cargarEdicionesEvento(ctrlEventos.listarEdiciones(eventoSeleccionado));
			}
		});
		
		
		comboBoxEdiciones = new JComboBox<String>();
		comboBoxEdiciones.setBounds(201, 46, 188, 20);
		getContentPane().add(comboBoxEdiciones);
		comboBoxEdiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
				String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
				if (edicionSeleccionada == null || edicionSeleccionada.equals("No hay ediciones")) {
					comboBoxTRegistros.setEnabled(false);
					return;
				}
				comboBoxTRegistros.setEnabled(true);
				cargarTRegistros(ctrlEventos.listarTRegistros(eventoSeleccionado, edicionSeleccionada));
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Seleccionar Edición:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 50, 181, 12);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccionar Tipo de Registro:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 85, 181, 12);
		getContentPane().add(lblNewLabel_2);
		
		comboBoxTRegistros = new JComboBox<String>();
		comboBoxTRegistros.setBounds(201, 81, 188, 20);
		getContentPane().add(comboBoxTRegistros);
		comboBoxTRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tRegSeleccionado = (String) comboBoxTRegistros.getSelectedItem();
				if (tRegSeleccionado == null || tRegSeleccionado.equals("No hay tipos de registro")) {
					btnAceptar.setEnabled(false);
					return;
				}
				btnAceptar.setEnabled(true);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Seleccionar Asistente:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 120, 181, 12);
		getContentPane().add(lblNewLabel_3);
		
		comboBoxAsistentes = new JComboBox<String>();
		comboBoxAsistentes.setBounds(201, 116, 188, 20);
		getContentPane().add(comboBoxAsistentes);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(201, 192, 84, 20);
		getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LocalDate fechaSeleccionada = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					if (verificarFecha(fechaSeleccionada)) {
						ctrlUsuarios.nuevoRegistro((String) comboBoxAsistentes.getSelectedItem(), (String) comboBoxEventos.getSelectedItem(), (String) comboBoxEdiciones.getSelectedItem(), (String) comboBoxTRegistros.getSelectedItem(), fechaSeleccionada);											
					}
				} catch (AsistenteYaRegistrado ex) {
					JOptionPane.showMessageDialog(RegistroEdicionEvento.this, ex.getMessage(), "Nuevo Registro", JOptionPane.ERROR_MESSAGE);
				} catch (NoHayCupoEdicionTRegistro ex) {
					JOptionPane.showMessageDialog(RegistroEdicionEvento.this, ex.getMessage(), "Nuevo Registro", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(RegistroEdicionEvento.this, "Error", "Nuevo Registro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(305, 192, 84, 20);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormularios();
				setVisible(false);
			}
		});
		
		JLabel lblNewLabel_3_1 = new JLabel("Ingresar Fecha:");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(10, 155, 181, 12);
		getContentPane().add(lblNewLabel_3_1);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(201, 152, 188, 20);
		getContentPane().add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
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
				btnAceptar.setEnabled(false);
				comboBoxEdiciones.setEnabled(false);
				comboBoxTRegistros.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxEventos.removeAllItems();
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
				btnAceptar.setEnabled(false);
				comboBoxTRegistros.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxEdiciones.removeAllItems();
			comboBoxEdiciones.addItem("No hay ediciones");
		}
	}
	
	public void cargarTRegistros(List<String> tRegEdicion) {
		try {
			comboBoxTRegistros.removeAllItems();
			if (tRegEdicion != null && !tRegEdicion.isEmpty()) {
				for (String e : tRegEdicion)
					comboBoxTRegistros.addItem(e);
			} else {
				comboBoxTRegistros.addItem("No hay tipos de registro");
				btnAceptar.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxTRegistros.removeAllItems();
			comboBoxTRegistros.addItem("No hay tipos de registro");
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
				comboBoxAsistentes.addItem("No hay asistentes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxAsistentes.removeAllItems();
			comboBoxAsistentes.addItem("No hay asistentes");
		}
	}
	
	public boolean verificarFecha(LocalDate fechaSeleccionada) {
		
		if (fechaSeleccionada == null) {
			JOptionPane.showMessageDialog(this, "Seleccione una fecha", "Nuevo Registro", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (fechaSeleccionada.isAfter(LocalDate.now())) {
			JOptionPane.showMessageDialog(this, "Seleccione una fecha válida", "Nuevo Registro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void limpiarFormularios() {
		comboBoxEventos.removeAllItems();
		comboBoxEdiciones.removeAllItems();
		comboBoxTRegistros.removeAllItems();
		comboBoxAsistentes.removeAllItems();
	}
}
