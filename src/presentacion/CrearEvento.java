package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;
import logica.ctrlmanejador.ManejadorEvento;
import logica.interfaces.IEventos;
import logica.datatypes.DataEvento;
import java.time.LocalDate;
import java.time.ZoneId;

import com.toedter.calendar.JDateChooser; 

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

@SuppressWarnings("serial")
public class CrearEvento extends JInternalFrame {
	
	private IEventos controlEvt;
	private JTextField textFieldNombre;
    private JTextField textFieldDesc;
    private JTextField textFieldSigla;
    private JLabel lblIngresarNombre;
    private JLabel lblIngresarDesc;
    private JLabel lblIngresarSigla;
    private JLabel lblCategoria;
    private JComboBox<String> comboBoxCat;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JLabel lblFechaAlta;
    
    private JDateChooser dateChooser;
    

  
    
    
    public CrearEvento(IEventos ice) {
    	
    	ManejadorEvento me = ManejadorEvento.getInstance();
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Evento");
        setBounds(10, 40, 500, 252);
		controlEvt = ice;
		getContentPane().setLayout(null);
		
		lblIngresarNombre = new JLabel("Nombre:");
		lblIngresarNombre.setBounds(30, 30, 80, 15);
        lblIngresarNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblIngresarNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngresarNombre);
		
        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(120, 26, 327, 22);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);
        
        lblIngresarDesc = new JLabel("Descripcion :");
        lblIngresarDesc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngresarDesc.setBounds(30, 90, 80, 15);
        getContentPane().add(lblIngresarDesc);
        
        textFieldDesc = new JTextField();
        textFieldDesc.setBounds(120, 87, 327, 20);
        getContentPane().add(textFieldDesc);
        textFieldDesc.setColumns(10);
        
        lblIngresarSigla = new JLabel("Sigla :");
        lblIngresarSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngresarSigla.setBounds(30, 60, 80, 15);
        getContentPane().add(lblIngresarSigla);
        
        textFieldSigla = new JTextField();
        textFieldSigla.setBounds(120, 59, 327, 20);
        getContentPane().add(textFieldSigla);
        textFieldSigla.setColumns(10);
        
        lblCategoria = new JLabel("Categoria :");
        lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCategoria.setBounds(30, 120, 80, 15);
        getContentPane().add(lblCategoria);
        
        
      
        comboBoxCat = new JComboBox<String>();
        comboBoxCat.setBounds(120, 118, 327, 22);
        List<String> categorias = me.getCategorias();
        if (categorias != null) {
        	for (String cat : categorias)
        		comboBoxCat.addItem(cat);
        }
        getContentPane().add(comboBoxCat)
        
        ;

        JPanel panelFecha = new JPanel();
        panelFecha.setBounds(0, 0, 0, 0);
        panelFecha.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        cal.add(java.util.Calendar.YEAR, -20);
        getContentPane().add(panelFecha);
        
        Action Aceptar = new aceptarEvento();
        Action Cancelar = new cancelarEvento();
        
        lblFechaAlta = new JLabel("Fecha de Alta:");
        lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaAlta.setBounds(30, 156, 80, 15);
        getContentPane().add(lblFechaAlta);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(120, 151, 327, 20);
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(cal.getTime());
        getContentPane().add(dateChooser);
        btnAceptar = new JButton(Aceptar);
        btnAceptar.setBounds(253, 182, 94, 23);
        getContentPane().add(btnAceptar);
        
         btnAceptar.setText("Aceptar");
         btnCancelar = new JButton(Cancelar);
         btnCancelar.setBounds(353, 182, 94, 23);
         getContentPane().add(btnCancelar);
         
         btnCancelar.setText("Cancelar");
          
    }
    private class aceptarEvento extends AbstractAction {
        public aceptarEvento() {
            putValue(NAME, "aceptarEvento");
            putValue(SHORT_DESCRIPTION, "Dar de alta un evento");
        }
        public void actionPerformed(ActionEvent e) {
            altaDeEvento(e);
        }
    }
    private class cancelarEvento extends AbstractAction {
    	public cancelarEvento() {
    		putValue(NAME, "cancelarEvento");
    		putValue(SHORT_DESCRIPTION, "Cancelar el alta de evento");
    	}
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
    		limpiarFormulario();
    		setVisible(false);
    		
    		
    		dispose();  
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
	

    protected void altaDeEvento(ActionEvent aceptar) {
        String nombreEvento = textFieldNombre.getText();
        String descripcionEvento = textFieldDesc.getText();
        String siglaEvento = textFieldSigla.getText();
        String categoriaEvento = (String) comboBoxCat.getSelectedItem();
        LocalDate fechaEvento = toLocalDate(dateChooser.getDate());

        if (checkFormulario()) {
            try {
                
                DataEvento d = new DataEvento(nombreEvento, siglaEvento, fechaEvento, descripcionEvento);
                controlEvt.nuevoEvento(d, categoriaEvento);
                JOptionPane.showMessageDialog(this, "Evento registrado correctamente",
                        "Alta de Evento", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                setVisible(false);
            } catch (EventoRepetidoExcepcion e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error al registrar evento", JOptionPane.ERROR_MESSAGE);
            } catch (EventoSinCategoriaExcepcion e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error al registrar evento", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean checkFormulario() {
        String nombreEvento = textFieldNombre.getText();
        String descripcionEvento = textFieldDesc.getText();
        String siglaEvento = textFieldSigla.getText();
        String categoria = (String) comboBoxCat.getSelectedItem();

        if (nombreEvento.isEmpty() || descripcionEvento.isEmpty() || siglaEvento.isEmpty()
                || categoria == null || categoria.isEmpty() || dateChooser.getDate() == null)  {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos",
                    "Alta de Evento", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
         
            
       
        return true;
    }
    
    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldNombre.setText("");
        textFieldSigla.setText("");
        textFieldDesc.setText("");
        ;
    }
    
    public void cargarCategorias() {
    	comboBoxCat.removeAllItems();
        ManejadorEvento me = ManejadorEvento.getInstance();
        List<String> categorias = me.getCategorias();
        if (categorias != null) {
            for (String cat : categorias) {
                comboBoxCat.addItem(cat);
            }
        }
    }
        
    }
		

 
    