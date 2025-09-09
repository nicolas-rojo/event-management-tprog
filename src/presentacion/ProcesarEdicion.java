package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import excepciones.EdicionRepetidaExcepcion;
import logica.datatypes.DataEdicion;
import logica.datatypes.Estado;
import logica.interfaces.IEventos;

public class ProcesarEdicion extends JInternalFrame {
	
	private IEventos controlEvt;
	
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxEdiciones;
	
	private JButton btnAceptarEdicion;
    private JButton btnRechazarEdicion;
    
    private JLabel lblEventos;
    private JLabel lblEdicionesEvento;
    
    public ProcesarEdicion(IEventos controladorEventos) {
    		        
        this.controlEvt = controladorEventos;;
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta Edicion de Evento");
        setBounds(0, 10, 546, 366);
        
        getContentPane().setLayout(null);                     
        lblEventos = new JLabel("Evento :");
        lblEventos.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEventos.setBounds(30, 30, 80, 15);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox<String>();
        comboBoxEventos.setBounds(115, 27, 354, 20);
        getContentPane().add(comboBoxEventos);

        
        lblEdicionesEvento = new JLabel("Edicion de Evento :");
        lblEdicionesEvento.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdicionesEvento.setBounds(30, 60, 80, 15);
        getContentPane().add(lblEdicionesEvento);

        comboBoxEdiciones = new JComboBox<String>();
        comboBoxEdiciones.setBounds(115, 57, 354, 20);
        getContentPane().add(comboBoxEdiciones);
        
        Action Aceptar = new aceptarEdicion();
        btnAceptarEdicion = new JButton(Aceptar);
        btnAceptarEdicion.setBounds(251, 299, 95, 23);
        getContentPane().add(btnAceptarEdicion);                                                       
        btnAceptarEdicion.setText("Aceptar");
        
        Action Cancelar = new rechazarEdicion();
        btnRechazarEdicion = new JButton(Cancelar);
        btnRechazarEdicion.setBounds(381, 299, 90, 23);
        getContentPane().add(btnRechazarEdicion);
        btnRechazarEdicion.setText("Rechazar");
        
        
        
     
	}
    	
    
    
    
    
    private class aceptarEdicion extends AbstractAction {
        public aceptarEdicion() {
            putValue(NAME, "aceptarEdicion");
            putValue(SHORT_DESCRIPTION, "confimar el alta de una Edicion");
        }
        public void actionPerformed(ActionEvent e) {
            confirmoEdicion(e);
        }
    }
    
    private class rechazarEdicion extends AbstractAction {
        public rechazarEdicion() {
            putValue(NAME, "rechazarEdicion");
            putValue(SHORT_DESCRIPTION, "rechazar el alta de Edicion");
        }

        @Override
        public void actionPerformed(ActionEvent e) {       	
            rechazoEdicion(e);                                                 
        }
    }
    
    protected void confirmoEdicion(ActionEvent aceptar) {
    	String evento = (String) comboBoxEventos.getSelectedItem();
        String eventoEdicion = (String) comboBoxEventos.getSelectedItem();
        Estado estado = Estado.Confirmado;                    
        if (checkFormulario()) {
        	controlEvt.procesarEdicion(evento,eventoEdicion, estado);
        	comboBoxEdiciones.removeItem(eventoEdicion);
        } 
    }
    
    protected void rechazoEdicion(ActionEvent rechazar) {
    	String evento = (String) comboBoxEventos.getSelectedItem();
        String eventoEdicion = (String) comboBoxEventos.getSelectedItem();
        Estado estado = Estado.Rechazado;                    
        if (checkFormulario()) {
        	controlEvt.procesarEdicion(evento,eventoEdicion, estado);
        	comboBoxEdiciones.removeItem(eventoEdicion);
        } 
    }
    
    
    
    private boolean checkFormulario() {
    	if(comboBoxEventos.getSelectedItem().equals("No hay eventos") 
    		|| comboBoxEdiciones.getSelectedItem().equals("No hay ediciones"));
    	return false;
    }

    public void limpiarFormulario() {
    	comboBoxEventos.removeAllItems();
    	comboBoxEdiciones.removeAllItems();
   
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
	

}