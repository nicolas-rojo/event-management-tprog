package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import excepciones.EventoRepetidoExcepcion;
import excepciones.EventoSinCategoriaExcepcion;

import logica.interfaces.IEventos;
import logica.datatypes.DataEvento;

import logica.datatypes.comboBoxMultiple;

import java.time.LocalDate;
import java.time.ZoneId;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class CrearEvento extends JInternalFrame {
	
	private IEventos controlEvt;
	private JTextField textFieldNombre;
	private JTextArea textAreaDescripcion; 
    private JTextField textFieldSigla;
    
    private JLabel lblIngresarNombre;
    private JLabel lblIngresarDesc;
    private JLabel lblIngresarSigla;
    private JLabel lblCategoria;
    private JLabel lblFechaAlta;
    
    private comboBoxMultiple comboBoxCat;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private JDateChooser dateChooser;
             
    public CrearEvento(IEventos ice) {
    	controlEvt = ice;
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Evento");
        setBounds(30, 30, 500, 322);
		controlEvt = ice;
		getContentPane().setLayout(null);
		
		lblIngresarNombre = new JLabel("Nombre:");
		lblIngresarNombre.setBounds(30, 44, 80, 15);
        lblIngresarNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblIngresarNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngresarNombre);
		
        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(120, 40, 327, 22);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);
        
        lblIngresarDesc = new JLabel("Descripcion :");
        lblIngresarDesc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngresarDesc.setBounds(30, 104, 80, 15);
        getContentPane().add(lblIngresarDesc);

        // Lo metemos en un JScrollPane
        JScrollPane scrollDescripcion = new JScrollPane();
        scrollDescripcion.setBounds(120, 104, 327, 59); // más alto que 22 px
        getContentPane().add(scrollDescripcion);
        
        textAreaDescripcion = new JTextArea();
        scrollDescripcion.setViewportView(textAreaDescripcion);
        textAreaDescripcion.setColumns(10);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);

        
        lblIngresarSigla = new JLabel("Sigla :");
        lblIngresarSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngresarSigla.setBounds(30, 76, 80, 15);
        getContentPane().add(lblIngresarSigla);
        
        textFieldSigla = new JTextField();
        textFieldSigla.setBounds(120, 73, 327, 20);
        getContentPane().add(textFieldSigla);
        textFieldSigla.setColumns(10);
        
        lblCategoria = new JLabel("Categorias :");
        lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCategoria.setBounds(30, 178, 80, 15);
        getContentPane().add(lblCategoria);
               
        String[] categorias1 = {};
        List<String> categorias = ice.listarCategorias();
        if (categorias != null) {
        	categorias1 = categorias.toArray(new String[0]);
        	}
        comboBoxCat = new comboBoxMultiple(categorias1);
        comboBoxCat.setBounds(120, 174, 327, 22);
        getContentPane().add(comboBoxCat);
        

        JPanel panelFecha = new JPanel();
        panelFecha.setBounds(0, 0, 0, 0);
        panelFecha.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        cal.add(java.util.Calendar.YEAR, 0);
        getContentPane().add(panelFecha);
        
        
        lblFechaAlta = new JLabel("Fecha de Alta:");
        lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaAlta.setBounds(30, 212, 80, 15);
        getContentPane().add(lblFechaAlta);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(120, 207, 327, 20);
        dateChooser.setDateFormatString("dd/MM/yyyy");        
        dateChooser.setDate(cal.getTime());
        getContentPane().add(dateChooser);
        
        Action Aceptar = new aceptarEvento();
        btnAceptar = new JButton(Aceptar);
        btnAceptar.setBounds(249, 249, 94, 23);
        getContentPane().add(btnAceptar);
        btnAceptar.setText("Aceptar");
        
        Action Cancelar = new cancelarEvento();
        btnCancelar = new JButton(Cancelar);
        btnCancelar.setBounds(353, 249, 94, 23);
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
        String descripcionEvento = textAreaDescripcion.getText();
        String siglaEvento = textFieldSigla.getText();
        List<String> categoriaEvento = comboBoxCat.getSelectedItems();
        LocalDate fechaEvento = toLocalDate(dateChooser.getDate());

        if (checkFormulario()) {
            try {               
                DataEvento d = new DataEvento(nombreEvento, siglaEvento, fechaEvento, descripcionEvento);
                controlEvt.nuevoEvento(d, categoriaEvento);
                JOptionPane.showMessageDialog(this, "Evento registrado correctamente", "Alta de Evento", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                setVisible(false);
            } catch (EventoRepetidoExcepcion e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error al registrar evento", JOptionPane.ERROR_MESSAGE);
            } catch (EventoSinCategoriaExcepcion e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error al registrar evento", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean checkFormulario() {
        String nombreEvento = textFieldNombre.getText();
        String descripcionEvento = textAreaDescripcion.getText();
        String siglaEvento = textFieldSigla.getText();
        List<String> categoria = comboBoxCat.getSelectedItems();

        if (nombreEvento.isEmpty() || descripcionEvento.isEmpty() || siglaEvento.isEmpty() || categoria == null || comboBoxCat.getSelectedItem().equals("No hay categorias") || dateChooser.getDate() == null)  {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de Evento", JOptionPane.ERROR_MESSAGE);
            return false;
        }
                                            
        return true;
    }
    
    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldSigla.setText("");
        textAreaDescripcion.setText("");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.YEAR,0);
        dateChooser.setDate(cal.getTime());
        comboBoxCat.removeAllItems();
    }
          
    public void cargarCategorias() {
    	try {
			comboBoxCat.removeAllItems();
			List<String> categorias = controlEvt.listarCategorias();
			if (categorias != null && !categorias.isEmpty()) {
				for (String c : categorias) {
					comboBoxCat.addItem(c);
				}
			} else {
				
				comboBoxCat.addItem("No hay categorias");
			}
		} catch (Exception e) {
			e.printStackTrace();
			comboBoxCat.removeAllItems();
			comboBoxCat.addItem("No hay categorias");
		}
    }
        
}