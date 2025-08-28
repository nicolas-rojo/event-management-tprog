package presentacion; //NICOLÁS

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.datatypes.DataEvento;
import logica.datatypes.DTOEvento;

public class ConsultaEvento extends JInternalFrame{
	
	private JList<DataEvento> listEventos;
	private JLabel lblEventos;
    private JLabel lblListaTitulo;
    private JLabel lblDetallesTitulo;
    private JButton btnCerrar;
    private JTextArea textAreaDetalles;
    private JScrollPane scrollPaneLista;
    private JScrollPane scrollPaneDetalles;
	
	
	public ConsultaEvento() {
		
		//JFrame
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Lista de Eventos");
        setBounds(30, 30, 600, 400);
		
        
        lblEventos = new JLabel("Eventos ");
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos, BorderLayout.NORTH);
        
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2, 10, 0));
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setPreferredSize(new Dimension(250, 0));
        
        lblListaTitulo = new JLabel("Seleccione un Evento:");
        lblListaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelLista.add(lblListaTitulo, BorderLayout.NORTH);
        
		listEventos = new JList<>();
        listEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneLista = new JScrollPane(listEventos);
        panelLista.add(scrollPaneLista, BorderLayout.CENTER);
        
        //El listener es para mostrar los detalles de un evento al seleccionar uno
        listEventos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetallesEvento();
                }
            }
        });
        
        panelPrincipal.add(panelLista);
	
	JPanel panelDetalles = new JPanel(new BorderLayout());
    
    // Título para los detalles
    lblDetallesTitulo = new JLabel("Detalles del Evento:");
    lblDetallesTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    panelDetalles.add(lblDetallesTitulo, BorderLayout.NORTH);
    
    // Área de texto para mostrar detalles (no editable)
    textAreaDetalles = new JTextArea();
    textAreaDetalles.setEditable(false);
    textAreaDetalles.setLineWrap(true);
    textAreaDetalles.setWrapStyleWord(true);
    scrollPaneDetalles = new JScrollPane(textAreaDetalles);
    panelDetalles.add(scrollPaneDetalles, BorderLayout.CENTER);
    
    panelPrincipal.add(panelDetalles);
    
    btnCerrar = new JButton("Cerrar");
    btnCerrar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            limpiarYCerrar();
        }
    });
    getContentPane().add(btnCerrar, BorderLayout.SOUTH);
    
	}
	
	public void mostrarDetallesEvento() {
		try {
			DTOEvento[] eventos = c
		}
		
	}
	
	// Método para limpiar y cerrar la ventana (Igual al consultar usuario)
    private void limpiarYCerrar() {
        listEventos.clearSelection();
        textAreaDetalles.setText("");
        setVisible(false);
    }
}