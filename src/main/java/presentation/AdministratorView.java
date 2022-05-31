package presentation;

import business.IDeliveryServiceProcessing;
import business.MenuItem;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdministratorView {
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel tablePanel;
    private JPanel productPanel;
    private JPanel mainPanel;
    private JPanel mPanel;

    private JTable table;

    private JScrollPane scroll;

    private JLabel titleLabel;
    private JLabel ratingLabel;
    private JLabel caloriesLabel;
    private JLabel proteinLabel;
    private JLabel fatLabel;
    private JLabel sodiumLabel;
    private JLabel priceLabel;
    private JLabel startHourLabel;
    private JLabel endHourLabel;
    private JLabel nrOrdersLabel;
    private JLabel valueLabel;
    private JLabel dateLabel;

    private JTextField titleTextField;
    private JTextField ratingTextField;
    private JTextField caloriesTextField;
    private JTextField proteinTextField;
    private JTextField fatTextField;
    private JTextField sodiumTextField;
    private JTextField priceTextField;
    private JTextField startHourTextField;
    private JTextField endHourTextField;
    private JTextField nrOrdersTextField;
    private JTextField valueTextField;
    private JTextField dateTextField;

    private JButton importButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private JButton reportOneButton;
    private JButton reportTwoButton;
    private JButton reportThreeButton;
    private JButton reportFourButton;

    private IDeliveryServiceProcessing iDeliveryServiceProcessing;

    private List<MenuItem> menuItems;

    public AdministratorView(IDeliveryServiceProcessing deliveryServiceProcessing){
        importButton = new JButton("Import products");
        addButton = new JButton("Add new product");
        deleteButton = new JButton("Delete");
        editButton = new JButton("Modify");
        createButton = new JButton("Create new product");
        reportOneButton = new JButton("Report 1");
        reportTwoButton = new JButton("Report 2");
        reportThreeButton = new JButton("Report 3");
        reportFourButton = new JButton("Report 4");

        titleLabel = new JLabel("Title:");
        ratingLabel = new JLabel("Rating:");
        caloriesLabel = new JLabel("Calories:");
        proteinLabel = new JLabel("Protein:");
        fatLabel = new JLabel("Fat:");
        sodiumLabel = new JLabel("Sodium:");
        priceLabel = new JLabel("Price:");
        startHourLabel = new JLabel("Start hour:");
        endHourLabel = new JLabel("End hour: ");
        nrOrdersLabel = new JLabel("Ordered more than:");
        valueLabel = new JLabel("Value: ");
        dateLabel = new JLabel("Date: ");

        titleTextField = new JTextField("");
        ratingTextField = new JTextField("");
        caloriesTextField = new JTextField("");
        proteinTextField = new JTextField("");
        fatTextField = new JTextField("");
        sodiumTextField = new JTextField("");
        priceTextField = new JTextField("");
        startHourTextField = new JTextField("");
        endHourTextField = new JTextField("");
        nrOrdersTextField = new JTextField("");
        valueTextField = new JTextField("");
        dateTextField = new JTextField("");

        iDeliveryServiceProcessing = deliveryServiceProcessing;

        menuItems = iDeliveryServiceProcessing.list();

        table = new JTable(new AbstractTableModel() {
            public String getColumnName(int col) {
                return "Menu";
            }
            public int getRowCount() { return iDeliveryServiceProcessing.list().size(); }
            public int getColumnCount() { return 1; }
            public Object getValueAt(int row, int col) {
                return iDeliveryServiceProcessing.list().get(row);
            }
            public boolean isCellEditable(int row, int col)
            { return false; }
        });


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(importButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(createButton);
        buttonPanel.add(reportOneButton);
        buttonPanel.add(reportTwoButton);
        buttonPanel.add(reportThreeButton);
        buttonPanel.add(reportFourButton);

        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setPreferredScrollableViewportSize(new Dimension(850, 450));
        table.setFillsViewportHeight(true);

        tablePanel = new JPanel();
        tablePanel.add(scroll);

        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(13, 2, 15, 15));
        productPanel.add(titleLabel);
        productPanel.add(titleTextField);
        productPanel.add(ratingLabel);
        productPanel.add(ratingTextField);
        productPanel.add(caloriesLabel);
        productPanel.add(caloriesTextField);
        productPanel.add(proteinLabel);
        productPanel.add(proteinTextField);
        productPanel.add(fatLabel);
        productPanel.add(fatTextField);
        productPanel.add(sodiumLabel);
        productPanel.add(sodiumTextField);
        productPanel.add(priceLabel);
        productPanel.add(priceTextField);
        productPanel.add(startHourLabel);
        productPanel.add(startHourTextField);
        productPanel.add(endHourLabel);
        productPanel.add(endHourTextField);
        productPanel.add(nrOrdersLabel);
        productPanel.add(nrOrdersTextField);
        productPanel.add(valueLabel);
        productPanel.add(valueTextField);
        productPanel.add(dateLabel);
        productPanel.add(dateTextField);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(tablePanel);
        mainPanel.add(productPanel);

        mPanel = new JPanel();
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.PAGE_AXIS));
        mPanel.add(mainPanel);
        mPanel.add(buttonPanel);

        frame = new JFrame("Administrator");
        frame.setSize(1200, 1000);
        frame.pack();
        frame.add(mPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addImportButtonListener(ActionListener actionListener){
        this.importButton.addActionListener(actionListener);
    }

    public void addAddButtonListener(ActionListener actionListener){
        this.addButton.addActionListener(actionListener);
    }

    public void addDeleteButtonListener(ActionListener actionListener){
        this.deleteButton.addActionListener(actionListener);
    }

    public void addEditButtonListener(ActionListener actionListener){
        this.editButton.addActionListener(actionListener);
    }

    public void addCreateButtonListener(ActionListener actionListener){
        this.createButton.addActionListener(actionListener);
    }

    public void addReport1ButtonListener(ActionListener actionListener){
        this.reportOneButton.addActionListener(actionListener);
    }

    public void addReport2ButtonListener(ActionListener actionListener){
        this.reportTwoButton.addActionListener(actionListener);
    }

    public void addReport3ButtonListener(ActionListener actionListener){
        this.reportThreeButton.addActionListener(actionListener);
    }

    public void addReport4ButtonListener(ActionListener actionListener){
        this.reportFourButton.addActionListener(actionListener);
    }

    public void updateTable(){
        AbstractTableModel abstractTableModel = (AbstractTableModel)table.getModel();
        abstractTableModel.fireTableDataChanged();
    }

    public MenuItem getSelectedMenuItem(){
        int row = table.getSelectedRow();
        if(row == -1){
            return null;
        }
        return iDeliveryServiceProcessing.list().get(row);
    }

    public List<MenuItem> getSelectedMenuItems(){
        int[] row = table.getSelectedRows();
        List<MenuItem> selectedMenu = new ArrayList<>();
        for(int i = 0; i < row.length; i++){
            MenuItem menuItem = menuItems.get(row[i]);
            selectedMenu.add(menuItem);
        }
        return selectedMenu;
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

    public String getStartHour(){
        return startHourTextField.getText();
    }

    public String getEndHour(){
        return endHourTextField.getText();
    }

    public String getnrOrders(){
        return nrOrdersTextField.getText();
    }

    public String getValue(){
        return valueTextField.getText();
    }

    public String getDate(){
        return dateTextField.getText();
    }

}

