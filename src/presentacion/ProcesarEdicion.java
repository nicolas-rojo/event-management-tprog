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
    private JButton btnCerrar;
    
    private JLabel lblEventos;
    private JLabel lblEdicionesEvento;
    
    private boolean cargandoEdiciones;
    
    public ProcesarEdicion(IEventos controladorEventos) {
    		        
        this.controlEvt = controladorEventos;;
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Procesar Edicion");
        setBounds(0, 10, 546, 366);
        
        getContentPane().setLayout(null);                     
        lblEventos = new JLabel("Evento :");
        lblEventos.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEventos.setBounds(30, 30, 80, 15);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox<String>();
        comboBoxEventos.setBounds(120, 27, 349, 20);
        getContentPane().add(comboBoxEventos);

        
        lblEdicionesEvento = new JLabel("Edicion de Evento :");
        lblEdicionesEvento.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdicionesEvento.setBounds(0, 105, 111, 15);
        getContentPane().add(lblEdicionesEvento);

        comboBoxEdiciones = new JComboBox<String>();
        comboBoxEdiciones.setBounds(121, 102, 348, 20);
        getContentPane().add(comboBoxEdiciones);
        
        Action Aceptar = new aceptarEdicion();
        btnAceptarEdicion = new JButton(Aceptar);
        btnAceptarEdicion.setBounds(261, 164, 95, 23);
        getContentPane().add(btnAceptarEdicion);                                                       
        btnAceptarEdicion.setText("Aceptar");
        
        Action Cancelar = new rechazarEdicion();
        btnRechazarEdicion = new JButton(Cancelar);
        btnRechazarEdicion.setBounds(375, 164, 90, 23);
        getContentPane().add(btnRechazarEdicion);
        btnRechazarEdicion.setText("Rechazar");
        
        Action Cerrar = new cerrar();
        btnCerrar = new JButton(Cerrar);
        btnCerrar.setBounds(30, 299, 490, 23);
        getContentPane().add(btnCerrar);
        btnCerrar.setText("Cerrar");
        
        comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
				if(eventoSeleccionado == null || comboBoxEventos.getSelectedItem().equals("No hay eventos") ) {
					comboBoxEdiciones.setEnabled(false);
					return;
				}
				comboBoxEdiciones.setEnabled(true);
				cargarEdicionesEvento(controlEvt.listarEdiciones(eventoSeleccionado));
				
			}
		});
		    
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
        String eventoEdicion = (String) comboBoxEdiciones.getSelectedItem();
        Estado estado = Estado.Confirmado;                    
        if (checkFormulario()) {    
            controlEvt.procesarEdicion(evento, eventoEdicion, estado);
            JOptionPane.showMessageDialog(this, "Edición Aceptada","Procesar Edición", JOptionPane.INFORMATION_MESSAGE);
            comboBoxEdiciones.removeItem(eventoEdicion);
            if (comboBoxEdiciones.getItemCount() == 0) {
                comboBoxEdiciones.addItem("No hay ediciones a procesar");
                comboBoxEdiciones.setEnabled(false);
            }
        } 
    }

    protected void rechazoEdicion(ActionEvent rechazar) {
        String evento = (String) comboBoxEventos.getSelectedItem();
        String eventoEdicion = (String) comboBoxEdiciones.getSelectedItem();
        Estado estado = Estado.Rechazado;                    
        if (checkFormulario()) {
            controlEvt.procesarEdicion(evento, eventoEdicion, estado);
            JOptionPane.showMessageDialog(this, "Edición Rechazada","Procesar Edición", JOptionPane.INFORMATION_MESSAGE);
            comboBoxEdiciones.removeItem(eventoEdicion);
            if (comboBoxEdiciones.getItemCount() == 0) {
                comboBoxEdiciones.addItem("No hay ediciones a procesar");
                comboBoxEdiciones.setEnabled(false);
            }
        } 
    }
    
    private class cerrar extends AbstractAction {
        public cerrar() {
            putValue(NAME, "cerrar");
            putValue(SHORT_DESCRIPTION, "cerrar ventana");
        }

        @Override
        public void actionPerformed(ActionEvent e) {       	
            setVisible(false);
            limpiarFormulario();
        }
    }
      
    private boolean checkFormulario() {
    	if(comboBoxEventos.getSelectedItem().equals("No hay eventos") 
    			   ||comboBoxEdiciones.getItemCount() == 0 || comboBoxEventos.getItemCount() == 0 || comboBoxEdiciones.getSelectedItem().equals("No hay ediciones a procesar")) {
    			    JOptionPane.showMessageDialog(this, "No puede haber campos vacíos","Alta de Edicion", JOptionPane.ERROR_MESSAGE);
    			    return false;
    			}
    			return true;
    }
    
    public void cargarEventos() {
        try {
            comboBoxEventos.removeAllItems();
            List<String> eventos = controlEvt.listarEventos();
            if (eventos != null && !eventos.isEmpty()) {
                for (String e : eventos)
                    comboBoxEventos.addItem(e);
                comboBoxEventos.setSelectedIndex(0);
            }
            else {
            	comboBoxEventos.addItem("No hay eventos");
                comboBoxEdiciones.addItem("No hay ediciones a procesar");
            }
            comboBoxEventos.setSelectedIndex(0);
            comboBoxEdiciones.setSelectedIndex(0);           
            comboBoxEdiciones.setEnabled(false);
        	}
            catch (Exception e) {
            e.printStackTrace();
            comboBoxEventos.removeAllItems();
        	}
    	}
	
	public void cargarEdicionesEvento(List<String> edicionesEvento) {
        try {
        	String evento = (String) comboBoxEventos.getSelectedItem();
        	cargandoEdiciones = true;
            comboBoxEdiciones.removeAllItems();
            if (edicionesEvento != null && !edicionesEvento.isEmpty()) {
                for (String ed : edicionesEvento) 
                	if (controlEvt.getEstado(ed, evento) == Estado.Ingresada) {
                    comboBoxEdiciones.addItem(ed);
                	}
                if(comboBoxEdiciones.getItemCount() == 0){
                	comboBoxEdiciones.addItem("No hay ediciones a procesar");
                	comboBoxEdiciones.setEnabled(false);
                comboBoxEdiciones.setSelectedIndex(0);
                	}
            } 
           else {
            	comboBoxEdiciones.addItem("No hay ediciones a procesar");
                comboBoxEdiciones.setSelectedIndex(0);
                comboBoxEdiciones.setEnabled(false);
            }
            cargandoEdiciones = false;
        } catch (Exception e) {
            e.printStackTrace();
            comboBoxEdiciones.removeAllItems();
        }
    }
	
	public void limpiarFormulario() {
		comboBoxEventos.removeAllItems();
		comboBoxEdiciones.removeAllItems();	
	}
}