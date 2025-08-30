package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;
import excepciones.EdicionRepetidaExcepcion;
import java.time.LocalDate;
import java.time.ZoneId;

import logica.datatypes.DataEdicion;

import com.toedter.calendar.JDateChooser; 

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.GridBagLayout;

import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

@SuppressWarnings("serial")
public class CrearEdicion extends JInternalFrame {
	
	private IEventos controlEvt;
	private IUsuario controladorU;
	private JTextField textFieldNombre;
    private JTextField textFieldSigla;
    private JTextField textFieldCiudad;
    private JTextField textFieldPais;
     
    
    private JLabel lblIngresarNombre;
    private JLabel lblIngresarSigla;
    private JLabel lblIngresarCiudad;
    private JLabel lblIngresarPais;
    private JLabel lblIngresarOrg;
    private JLabel lblIngresarEvento;
    private JLabel lblIngresarFechaIni;
    private JLabel lblingresarFechaFin;
    private JLabel lblingresarFechaAlta;
    
    private JComboBox<String> comboBoxOrgs;
    private JComboBox<String> comboBoxEventos;
    
    private JDateChooser dateChooser;
    private JDateChooser dateChooser1;
    private JDateChooser dateChooser2;
    
    
    private JPanel panelFecha;
    
    private JButton btnAceptar;
    private JButton btnCancelar;
     
	public CrearEdicion(IEventos ice, IUsuario ive) {
		
		this.controladorU = ive;	        
        this.controlEvt = ice;
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta Edicion de Evento");
        setBounds(0, 10, 546, 366);
       
        getContentPane().setLayout(null);                     
        lblIngresarEvento = new JLabel("Evento :");
        lblIngresarEvento.setBounds(30, 30, 80, 15);
        getContentPane().add(lblIngresarEvento);

        comboBoxEventos = new JComboBox<String>();
        comboBoxEventos.setBounds(115, 27, 354, 20);
        getContentPane().add(comboBoxEventos);

        
        lblIngresarOrg = new JLabel("Organizador :");
        lblIngresarOrg.setBounds(30, 60, 80, 15);
        getContentPane().add(lblIngresarOrg);

        comboBoxOrgs = new JComboBox<String>();
        comboBoxOrgs.setBounds(115, 57, 354, 20);
        getContentPane().add(comboBoxOrgs);

        
        lblIngresarNombre = new JLabel("Nombre :");
        lblIngresarNombre.setBounds(30, 90, 80, 15);
        getContentPane().add(lblIngresarNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(115, 88, 354, 20);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        
        lblIngresarSigla = new JLabel("Sigla :");
        lblIngresarSigla.setBounds(30, 120, 80, 15);
        getContentPane().add(lblIngresarSigla);

        textFieldSigla = new JTextField();
        textFieldSigla.setBounds(115, 119, 354, 20);
        getContentPane().add(textFieldSigla);
        textFieldSigla.setColumns(10);

       
        lblIngresarCiudad = new JLabel("Ciudad :");
        lblIngresarCiudad.setBounds(30, 150, 80, 15);
        getContentPane().add(lblIngresarCiudad);

        textFieldCiudad = new JTextField();
        textFieldCiudad.setBounds(115, 150, 354, 20);
        getContentPane().add(textFieldCiudad);
        textFieldCiudad.setColumns(10);

        
        lblIngresarPais = new JLabel("País :");
        lblIngresarPais.setBounds(30, 180, 80, 15);
        getContentPane().add(lblIngresarPais);

        textFieldPais = new JTextField();
        textFieldPais.setBounds(115, 181, 354, 20);
        getContentPane().add(textFieldPais);
        textFieldPais.setColumns(10);

    

        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        java.util.Calendar cal2 = java.util.Calendar.getInstance();
        
        cal.add(java.util.Calendar.YEAR, -20);
        cal1.add(java.util.Calendar.YEAR, -20);
        cal2.add(java.util.Calendar.YEAR, -20);

       
        lblIngresarFechaIni = new JLabel("Fecha de Inicio :");
        lblIngresarFechaIni.setBounds(30, 210, 80, 15);
        getContentPane().add(lblIngresarFechaIni);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(115, 206, 354, 20);
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(cal.getTime());
        getContentPane().add(dateChooser);

        
        lblingresarFechaFin = new JLabel("Fecha de Fin :");
        lblingresarFechaFin.setBounds(30, 240, 80, 15);
        getContentPane().add(lblingresarFechaFin);

        dateChooser1 = new JDateChooser();
        dateChooser1.setBounds(115, 237, 354, 20);
        dateChooser1.setDateFormatString("dd/MM/yyyy");
        dateChooser1.setDate(cal1.getTime());
        getContentPane().add(dateChooser1);

       
        lblingresarFechaAlta = new JLabel("Fecha de Alta :");
        lblingresarFechaAlta.setBounds(30, 270, 80, 15);
        getContentPane().add(lblingresarFechaAlta);

        dateChooser2 = new JDateChooser();
        dateChooser2.setBounds(115, 268, 354, 20);
        dateChooser2.setDateFormatString("dd/MM/yyyy");
        dateChooser2.setDate(cal2.getTime());
        getContentPane().add(dateChooser2);

       
                        
        panelFecha = new JPanel(new GridBagLayout());
        panelFecha.setBounds(0, 0, 0, 0);
        getContentPane().add(panelFecha);
                 
        Action Aceptar = new aceptarEdicion();
        btnAceptar = new JButton(Aceptar);
        btnAceptar.setBounds(251, 299, 95, 23);
        getContentPane().add(btnAceptar);                                                       
        btnAceptar.setText("Aceptar");
        
        Action Cancelar = new cancelarEdicion();
        btnCancelar = new JButton(Cancelar);
        btnCancelar.setBounds(381, 299, 90, 23);
        getContentPane().add(btnCancelar);
        btnCancelar.setText("Cancelar");                                                 
}          
	
		private class aceptarEdicion extends AbstractAction {
        public aceptarEdicion() {
            putValue(NAME, "aceptarEdicion");
            putValue(SHORT_DESCRIPTION, "Dar de alta una Edicion");
        }
        public void actionPerformed(ActionEvent e) {
            altaDeEdicion(e);
        }
    }
	
	private class cancelarEdicion extends AbstractAction {
        public cancelarEdicion() {
            putValue(NAME, "cancelarEdicion");
            putValue(SHORT_DESCRIPTION, "Cancelar el alta de la Edicion");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
          
            limpiarFormulario();
            setVisible(false);                                                 
        }
    }
	
	public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate();
    }
	
	protected void altaDeEdicion(ActionEvent aceptar) {
        String nombreEdicion = textFieldNombre.getText();
        String siglaEdicion = textFieldSigla.getText();
        String ciudadEdicion = textFieldCiudad.getText();
        String paisEdicion = textFieldPais.getText();
        LocalDate fechaIni = toLocalDate(dateChooser.getDate());
        LocalDate fechaFin = toLocalDate(dateChooser1.getDate());
        LocalDate fechaAlta = toLocalDate(dateChooser2.getDate());
        String eventoEdicion = (String) comboBoxEventos.getSelectedItem();	
                             
        if (checkFormulario()) {
            try {
                
                DataEdicion d = new DataEdicion(nombreEdicion, siglaEdicion, fechaIni, fechaFin, fechaAlta, paisEdicion, ciudadEdicion);
                controlEvt.nuevaEdicion(d, eventoEdicion);
                JOptionPane.showMessageDialog(this, "Edicion registrada correctamente", "Alta de Ediciion", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                setVisible(false);
            } catch (EdicionRepetidaExcepcion e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error al registrar edicion", JOptionPane.ERROR_MESSAGE);
            } 
        }
    }
	
	private boolean checkFormulario() {
		 String nombreEdicion = textFieldNombre.getText();
		 String siglaEdicion = textFieldSigla.getText();
	     String ciudadEdicion = textFieldCiudad.getText();
	     String paisEdicion = textFieldPais.getText();
	     LocalDate fechaIni = toLocalDate(dateChooser.getDate());
	     LocalDate fechaFin = toLocalDate(dateChooser1.getDate());
	     LocalDate fechaAlta = toLocalDate(dateChooser2.getDate());	     
	     if (nombreEdicion.isEmpty() || siglaEdicion.isEmpty() || ciudadEdicion.isEmpty() || ciudadEdicion.isEmpty() 
              || paisEdicion.isEmpty() ||dateChooser.getDate() == null || dateChooser1.getDate() == null || dateChooser2.getDate() == null
              || comboBoxOrgs.getSelectedItem() == null || comboBoxEventos.getSelectedItem() == null)  {
	    	 	JOptionPane.showMessageDialog(this, "No puede haber campos vacíos","Alta de Edicion", JOptionPane.ERROR_MESSAGE);
	    	 	return false;
	     }
	    
	     else if(fechaIni.isAfter(fechaFin) || fechaAlta.isAfter(fechaIni)) {
	    	 JOptionPane.showMessageDialog(this, "Fecha Invalida","Alta de Edicion", JOptionPane.ERROR_MESSAGE);
	    	 return false;
	     }
	    	 	                
        return true;
    }
		   	       	
	public void cargarOrganizadores() {
		try {
			comboBoxOrgs.removeAllItems();
			List<String> organizadores = controladorU.listarOrganizadores();
			if (organizadores != null && !organizadores.isEmpty()) {
				for (String o : organizadores) {
					comboBoxOrgs.addItem(o);
				}
			} else {
				comboBoxOrgs.addItem("No hay organizadores");
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxOrgs.removeAllItems();
			comboBoxOrgs.addItem("No hay organizadores");
		}
	}
	
	public void cargarEventos() {
	
		try {
			comboBoxEventos.removeAllItems();
			List<String> eventos = controlEvt.listarEventos();
			if (eventos != null && !eventos.isEmpty()) {
				for (String e : eventos) {
					comboBoxEventos.addItem(e);
				}
			} else {
				comboBoxEventos.addItem("No hay eventos");
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxEventos.removeAllItems();
			comboBoxEventos.addItem("No hay eventos");
		}
	}	
	public void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldSigla.setText("");
		textFieldCiudad.setText("");
		textFieldPais.setText("");
		comboBoxEventos.removeAllItems();
		comboBoxOrgs.removeAllItems();
		java.util.Calendar cal = java.util.Calendar.getInstance();
	    cal.add(java.util.Calendar.YEAR, -20);
	    dateChooser.setDate(cal.getTime());
	    dateChooser1.setDate(cal.getTime());
	    dateChooser2.setDate(cal.getTime());		
    }
}


