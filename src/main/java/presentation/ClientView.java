package presentation;

import business.IDeliveryServiceProcessing;
import business.MenuItem;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientView extends JFrame {
    private JTextField titleTextField;
    private JTextField ratingTextField;
    private JTextField caloriesTextField;
    private JTextField proteinTextField;
    private JTextField fatTextField;
    private JTextField sodiumTextField;
    private JTextField priceTextField;

    private JLabel titleLabel;
    private JLabel ratingLabel;
    private JLabel caloriesLabel;
    private JLabel proteinLabel;
    private JLabel fatLabel;
    private JLabel sodiumLabel;
    private JLabel priceLabel;

    private JButton searchButton;
    private JButton orderButton;

    private JPanel tablePanel;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel searchPanel;
    private JPanel upperPanel;

    private JScrollPane scroll;

    private JTable table;

    private JFrame frame;

    private IDeliveryServiceProcessing iDeliveryServiceProcessing;

    private List<MenuItem> menuItems;

    public ClientView(IDeliveryServiceProcessing deliveryServiceProcessing){
        searchButton = new JButton("Search");
        orderButton = new JButton("Order");

        titleLabel = new JLabel("Title:");
        ratingLabel = new JLabel("Rating:");
        caloriesLabel = new JLabel("Calories:");
        proteinLabel = new JLabel("Protein:");
        fatLabel = new JLabel("Fat:");
        sodiumLabel = new JLabel("Sodium:");
        priceLabel = new JLabel("Price:");

        titleTextField = new JTextField("");
        ratingTextField = new JTextField("");
        caloriesTextField = new JTextField("");
        proteinTextField = new JTextField("");
        fatTextField = new JTextField("");
        sodiumTextField = new JTextField("");
        priceTextField = new JTextField("");

        iDeliveryServiceProcessing = deliveryServiceProcessing;

        menuItems = iDeliveryServiceProcessing.list();

        table = new JTable(new AbstractTableModel() {
            public String getColumnName(int col) {
                return "Menu";
            }
            public int getRowCount() { return menuItems.size(); }
            public int getColumnCount() { return 1; }
            public Object getValueAt(int row, int col) {
                MenuItem menuItem = menuItems.get(row);
                return menuItem;
            }
            public boolean isCellEditable(int row, int col)
            { return false; }
        });

        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setPreferredScrollableViewportSize(new Dimension(820, 450));
        table.setFillsViewportHeight(true);

        tablePanel = new JPanel();
        tablePanel.add(scroll);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(searchButton);
        buttonPanel.add(orderButton);

        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 2, 15, 15));
        searchPanel.add(titleLabel);
        searchPanel.add(titleTextField);
        searchPanel.add(ratingLabel);
        searchPanel.add(ratingTextField);
        searchPanel.add(caloriesLabel);
        searchPanel.add(caloriesTextField);
        searchPanel.add(proteinLabel);
        searchPanel.add(proteinTextField);
        searchPanel.add(fatLabel);
        searchPanel.add(fatTextField);
        searchPanel.add(sodiumLabel);
        searchPanel.add(sodiumTextField);
        searchPanel.add(priceLabel);
        searchPanel.add(priceTextField);

        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout());
        upperPanel.add(tablePanel);
        upperPanel.add(searchPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(upperPanel);
        mainPanel.add(buttonPanel);

        frame = new JFrame("Client");
        frame.setSize(1200, 800);
        frame.pack();
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addSearchButtonListener(ActionListener actionListener){
        this.searchButton.addActionListener(actionListener);
    }

    public void addOrderButtonListener(ActionListener actionListener){
        this.orderButton.addActionListener(actionListener);
    }

    public String getTitle() {
        return titleTextField.getText();
    }

    public String getRating() {
        return ratingTextField.getText();
    }

    public String getCalories(){
        return caloriesTextField.getText();
    }

    public String getProtein(){
        return proteinTextField.getText();
    }

    public String getFat(){
        return fatTextField.getText();
    }

    public String getSodium(){
        return sodiumTextField.getText();
    }

    public String getPrice(){
        return priceTextField.getText();
    }

    public void updateTable(List<MenuItem> menu){
        menuItems = menu;
        AbstractTableModel abstractTableModel = (AbstractTableModel)table.getModel();
        abstractTableModel.fireTableDataChanged();
    }

    public List<MenuItem>  getSelectedMenuItems(){
        int[] row = table.getSelectedRows();
        List<MenuItem> selectedMenu = new ArrayList<>();
        for(int i = 0; i < row.length; i++){
            MenuItem menuItem = menuItems.get(row[i]);
            selectedMenu.add(menuItem);
        }
        return selectedMenu;
    }
}

