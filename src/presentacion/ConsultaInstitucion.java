package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.datatypes.*;
import logica.interfaces.IInstituciones;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ConsultaInstitucion extends JInternalFrame {

    // Controlador de instituciones que se utilizará para las acciones del JFrame
    private IInstituciones controlInst;
    
    // Los componentes gráficos se agregan como atributos de la clase
    private JList<DataInstitucion> listInstituciones;
    private JLabel lblInstituciones;
    private JLabel lblListaTitulo;
    private JLabel lblDetallesTitulo;
    private JButton btnCerrar;
    private JScrollPane scrollPaneLista;
    private JScrollPane scrollPaneDetalles;
    
    // Campos para mostrar detalles (no editables)
    private JTextField textFieldNombre;
    private JTextField textFieldUrl;
    private JTextArea textAreaDescripcion;
    
    // Etiquetas para los campos
    private JLabel lblNombre;
    private JLabel lblUrl;
    private JLabel lblDescripcion;

    public ConsultaInstitucion(IInstituciones iin) {
        // Se inicializa con el controlador de instituciones
        controlInst = iin;
        
        // Propiedades del JInternalFrame
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Lista de Instituciones");
        setBounds(30, 30, 800, 500);
        
        // Título principal centrado
        lblInstituciones = new JLabel("Instituciones Registradas");
        lblInstituciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblInstituciones.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblInstituciones, BorderLayout.NORTH);

        // Panel principal para dividir la ventana en lista y detalles
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2, 10, 0));
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        // Panel para la lista de instituciones
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setPreferredSize(new Dimension(250, 0));
        
        // Título para la lista de instituciones
        lblListaTitulo = new JLabel("Seleccione una institución:");
        lblListaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblListaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelLista.add(lblListaTitulo, BorderLayout.NORTH);
        
        // Lista de instituciones con scroll
        listInstituciones = new JList<>();
        listInstituciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneLista = new JScrollPane(listInstituciones);
        panelLista.add(scrollPaneLista, BorderLayout.CENTER);
        
        // Añadir listener para mostrar detalles al seleccionar una institución
        listInstituciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetallesInstitucion();
                }
            }
        });
        
        panelPrincipal.add(panelLista);

        // Panel para los detalles de la institución
        JPanel panelDetalles = new JPanel(new BorderLayout());
        
        // Título para los detalles
        lblDetallesTitulo = new JLabel("Detalles de la Institución:");
        lblDetallesTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDetallesTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelDetalles.add(lblDetallesTitulo, BorderLayout.NORTH);
        
        // Panel de contenido con scroll para los campos de detalles
        JPanel panelContenido = new JPanel(new GridBagLayout());
        scrollPaneDetalles = new JScrollPane(panelContenido);
        scrollPaneDetalles.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneDetalles.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetalles.add(scrollPaneDetalles, BorderLayout.CENTER);
        
        // Inicializar componentes de detalles
        inicializarComponentesDetalles(panelContenido);
        
        panelPrincipal.add(panelDetalles);

        // Botón para cerrar
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarYCerrar();
            }
        });
        getContentPane().add(btnCerrar, BorderLayout.SOUTH);
    }

    private void inicializarComponentesDetalles(JPanel panelContenido) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        // Inicializar etiquetas
        lblNombre = new JLabel("Nombre:");
        lblUrl = new JLabel("Sitio Web:");
        lblDescripcion = new JLabel("Descripción:");
        
        // Inicializar campos (no editables)
        textFieldNombre = crearCampoNoEditable();
        textFieldUrl = crearCampoNoEditable();
        
        textAreaDescripcion = new JTextArea(4, 20);
        textAreaDescripcion.setEditable(false);
        textAreaDescripcion.setBackground(getBackground());
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        
        // Agregar componentes al panel (inicialmente vacíos)
        int row = 0;
        
        // Nombre
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblNombre, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldNombre, gbc);
        
        // URL
        gbc.gridx = 0;
        gbc.gridy = row;
        panelContenido.add(lblUrl, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelContenido.add(textFieldUrl, gbc);
        
        // Descripción
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelContenido.add(lblDescripcion, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        JScrollPane scrollDescripcion = new JScrollPane(textAreaDescripcion);
        panelContenido.add(scrollDescripcion, gbc);
    }
    
    private JTextField crearCampoNoEditable() {
        JTextField campo = new JTextField(20);
        campo.setEditable(false);
        campo.setBackground(getBackground());
        return campo;
    }

    // Método para cargar instituciones en la lista
    public void cargarInstituciones() {
        try {
            DataInstitucion[] instituciones = controlInst.listarInstituciones();
            if (instituciones != null && instituciones.length > 0) {
                javax.swing.DefaultListModel<DataInstitucion> model = new javax.swing.DefaultListModel<>();
                for (DataInstitucion institucion : instituciones) {
                    model.addElement(institucion);
                }
                listInstituciones.setModel(model);
                listInstituciones.setCellRenderer(new javax.swing.ListCellRenderer<DataInstitucion>() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(
                            javax.swing.JList<? extends DataInstitucion> list, 
                            DataInstitucion value, 
                            int index, 
                            boolean isSelected, 
                            boolean cellHasFocus) {
                        
                        JLabel label = new JLabel();
                        if (value != null) {
                            label.setText(value.getNombre());
                        }
                        
                        if (isSelected) {
                            label.setBackground(list.getSelectionBackground());
                            label.setForeground(list.getSelectionForeground());
                        } else {
                            label.setBackground(list.getBackground());
                            label.setForeground(list.getForeground());
                        }
                        label.setOpaque(true);
                        return label;
                    }
                });
            } else {
                // No hay instituciones registradas
                JOptionPane.showMessageDialog(this, 
                    "No hay instituciones registradas en el sistema.", 
                    "Información", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar las instituciones: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDetallesInstitucion() {
        DataInstitucion institucionSeleccionada = listInstituciones.getSelectedValue();
        
        if (institucionSeleccionada == null) {
            limpiarCamposDetalles();
            return;
        }
        
        // Mostrar los detalles de la institución seleccionada
        textFieldNombre.setText(institucionSeleccionada.getNombre());
        textFieldUrl.setText(institucionSeleccionada.getUrl());
        textAreaDescripcion.setText(institucionSeleccionada.getDescripcion());
    }
    
    private void limpiarCamposDetalles() {
        textFieldNombre.setText("");
        textFieldUrl.setText("");
        textAreaDescripcion.setText("");
    }

    // Método para limpiar y cerrar la ventana
    private void limpiarYCerrar() {
        listInstituciones.clearSelection();
        limpiarCamposDetalles();
        setVisible(false);
    }
}