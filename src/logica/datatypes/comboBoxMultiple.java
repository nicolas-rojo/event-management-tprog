package logica.datatypes;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class comboBoxMultiple extends JComboBox<String> {
    private List<String> selectedItems = new ArrayList<>();

    public comboBoxMultiple(String[] items) {
        super(items);
        setEditable(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) getSelectedItem();
                if (item != null) {
                    if (selectedItems.contains(item)) {
                        selectedItems.remove(item);
                    } else {
                        selectedItems.add(item);
                    }                   
                    repaint();
                }
            }
        });
       
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                if (index == -1) { 
                    String text = String.join(", ", selectedItems);
                    return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
                } else {
                    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            }
        });
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }
}