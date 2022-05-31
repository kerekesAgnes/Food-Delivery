package presentation;

import javax.swing.*;
import java.awt.*;

public class EmployeeView extends JFrame {
    private JTextArea productTextArea;
    private JFrame frame;
    private JPanel panel;

    public EmployeeView(String product){
        productTextArea = new JTextArea("New order:\n");
        productTextArea.setSize(500, 500);
        productTextArea.append(product);
        productTextArea.setLineWrap(true);
        productTextArea.setWrapStyleWord(true);
        productTextArea.setEditable(false);

        JScrollPane areaScrollPane = new JScrollPane(productTextArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));

        panel = new JPanel();
        panel.setSize(300, 300);
        panel.add(productTextArea);

        frame = new JFrame("Employee");
        frame.pack();
        frame.add(panel);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
}
