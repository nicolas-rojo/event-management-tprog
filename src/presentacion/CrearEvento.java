package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CrearEvento extends JInternalFrame {

    private JTextField textFieldNombre;
    private JTextField textFieldSigla;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public CrearEvento() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Evento");
        setBounds(10, 40, 360, 150);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{100, 120, 120, 0};
        gridBagLayout.rowHeights = new int[]{30, 30, 30, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        JLabel lblNombre = new JLabel("Nombre:");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 0;
        getContentPane().add(lblNombre, gbc_lblNombre);

        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 2;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 0;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblSigla = new JLabel("Sigla:");
        GridBagConstraints gbc_lblSigla = new GridBagConstraints();
        gbc_lblSigla.insets = new Insets(0, 0, 5, 5);
        gbc_lblSigla.gridx = 0;
        gbc_lblSigla.gridy = 1;
        getContentPane().add(lblSigla, gbc_lblSigla);

        textFieldSigla = new JTextField();
        GridBagConstraints gbc_textFieldSigla = new GridBagConstraints();
        gbc_textFieldSigla.gridwidth = 2;
        gbc_textFieldSigla.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldSigla.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldSigla.gridx = 1;
        gbc_textFieldSigla.gridy = 1;
        getContentPane().add(textFieldSigla, gbc_textFieldSigla);
        textFieldSigla.setColumns(10);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 2;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 2;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
    }
}