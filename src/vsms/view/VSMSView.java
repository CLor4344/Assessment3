/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsms.view;

import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import vsms.model.Customer;
import vsms.model.Service;
import vsms.model.Vehicle;
import vsms.presenter.VSMSPresenter;

/**
 *
 * @author Colbey
 */
public class VSMSView extends JFrame implements IVSMSView {

    private VSMSPresenter presenter;

    //customer entry variables
    String fName = "";
    String lName = "";
    String phone = "";
    String address = "";

    //vehicle entry variables
    String rego = "";
    String make = "";
    String model = "";
    int year = 0;
    int odometer = 0;

    //service entry variables
    int seviceDay = 0;
    int serviceMonth = 0;
    int serviceYear = 0;
    int servicePrice = 0;
    String serviceDesc = "";

    private JPanel mainPanel;
    private JPanel mainPanelCard;
    private JPanel mainPanelGraphs;
    private JPanel bottomPanel;
    private JPanel topInfoPanel;
    private JPanel mainFramePanel;
    private JButton exitButton;

    //customer Panel components
    private JPanel custPanel;
    private JPanel minorCustPanel1;
    private JPanel minorCustPanel2;
    private JPanel minorCustPanel3;
    private JPanel minorCustPanel4;
    private JPanel minorCustPanel5;
    private JLabel custIdLabel;
    private JTextField custIdTextField;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel addressLabel;
    private JTextField addressTextField;
    private JButton addCustButton;
    private JButton updateCustButton;
    private JButton cancelCustButton;
    private JButton saveCustButton;

    private BorderPane testPane;

    //vehicle panel components
    private JPanel vehPanel;
    private JPanel minorVehPanel1;
    private JPanel minorVehPanel2;
    private JPanel minorVehPanel3;
    private JPanel minorVehPanel4;
    private JPanel minorVehPanel5;

    private JLabel regoLabel;
    private JTextField regoTextField;
    private JLabel makeLabel;
    private JTextField makeTextField;
    private JLabel modelLabel;
    private JTextField modelTextField;
    private JLabel carYearLabel;
    private JTextField carYearTextField;

    private JButton addVehButton;
    private JButton updateVehButton;
    private JButton cancelVehButton;
    private JButton saveVehButton;

    //service panel components
    private JPanel servPanel;
    private JPanel minorServPanel1;
    private JPanel minorServPanel2;
    private JPanel minorServPanel3;
    private JPanel minorServPanel4;
    private JPanel minorServPanel5;
    private JPanel minorServPanel6;
    private JPanel servDatePanel;

    private JButton addServButton;
    private JButton updateServButton;
    private JButton cancelServButton;

    private JLabel servIdLabel;
    private JTextField servIdTextField;
    private JLabel servDateLabel;
    private JTextField servYearTextField;
    private JLabel servYearLabel;
    private JLabel servMonthLabel;
    private JLabel servDayLabel;
    private JComboBox monthCombo = new JComboBox();
    private JComboBox dayCombo = new JComboBox();
    private JLabel servPriceLabel;
    private JTextField servPriceTextField;
    private JLabel servDescLabel;
    private JTextArea servDescTextField;
    private JScrollPane descScrollPane;

    //display panel components
    private JPanel displayPanel;
    private JTable displayTable;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane1;
    private JPanel tableButtonPanel;
    private JButton tableButton1;
    private JButton tableButton2;
    private JButton tableButton3;

    //query panel swing components
    private JPanel queryPanel;
    private JPanel queryLeftPanel;
    private JPanel queryRightPanel;
    private JLabel queryByNameLabel;
    private JTextField queryByNameTextField;
    private JLabel queryByPhoneLabel;
    private JTextField queryByPhoneTextField;
    private JButton queryByNameButton;
    private JButton queryByPhoneButton;
    JFXPanel fxPanel = new JFXPanel();

    private JPanel tablePanel;

    public VSMSView() {
        super("Vehical Service Management System");

        mainFramePanel = new JPanel();
        mainPanel = new JPanel();
        mainPanelGraphs = new JPanel();
        mainPanelCard = new JPanel(new CardLayout());

        custPanel = new JPanel();
        minorCustPanel1 = new JPanel();
        minorCustPanel2 = new JPanel();
        minorCustPanel3 = new JPanel();
        minorCustPanel4 = new JPanel();
        minorCustPanel5 = new JPanel();

        vehPanel = new JPanel();
        minorVehPanel1 = new JPanel();
        minorVehPanel2 = new JPanel();
        minorVehPanel3 = new JPanel();
        minorVehPanel4 = new JPanel();
        minorVehPanel5 = new JPanel();
        testPane = new BorderPane();

        servPanel = new JPanel();
        servDatePanel = new JPanel();
        minorServPanel1 = new JPanel();
        minorServPanel2 = new JPanel();
        minorServPanel3 = new JPanel();
        minorServPanel4 = new JPanel();
        minorServPanel5 = new JPanel();
        minorServPanel6 = new JPanel();

        topInfoPanel = new JPanel();

        tablePanel = new JPanel();
        bottomPanel = new JPanel();
        exitButton = new JButton();
        exitButton.setText("EXIT");
        bottomPanel.add(exitButton);

        //customer panel parts
        custIdLabel = new JLabel("Customer ID:");
        custIdTextField = new JTextField(2);
        custIdTextField.setEditable(false);
        firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new JTextField(3);
        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField(3);
        phoneLabel = new JLabel("Phone:");
        phoneTextField = new JTextField(3);
        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField(5);

        addCustButton = new JButton();
        addCustButton.setText("Add");
        updateCustButton = new JButton();
        updateCustButton.setText("Update");
        cancelCustButton = new JButton();
        cancelCustButton.setText("Cancel");
        saveCustButton = new JButton();
        saveCustButton.setText("Save");

        TitledBorder custPanelTitle;
        custPanelTitle = BorderFactory.createTitledBorder("Customer");
        custPanel.setBorder(custPanelTitle);
        custPanel.setLayout(new BoxLayout(custPanel, BoxLayout.PAGE_AXIS));
        minorCustPanel1.add(custIdLabel);
        minorCustPanel1.add(custIdTextField);
        minorCustPanel2.add(firstNameLabel);
        minorCustPanel2.add(firstNameTextField);
        minorCustPanel2.add(lastNameLabel);
        minorCustPanel2.add(lastNameTextField);
        minorCustPanel3.add(phoneLabel);
        minorCustPanel3.add(phoneTextField);
        minorCustPanel4.add(addressLabel);
        minorCustPanel4.add(addressTextField);
        minorCustPanel5.add(addCustButton);
        minorCustPanel5.add(updateCustButton);
        minorCustPanel5.add(cancelCustButton);

        custPanel.add(minorCustPanel1);
        custPanel.add(minorCustPanel2);
        custPanel.add(minorCustPanel3);
        custPanel.add(minorCustPanel4);
        custPanel.add(minorCustPanel5);

        //vehicle panel parts
        regoLabel = new JLabel("Registration:");
        regoTextField = new JTextField(4);
        makeLabel = new JLabel("Make:");
        makeTextField = new JTextField(4);
        modelLabel = new JLabel("Model:");
        modelTextField = new JTextField(4);
        carYearLabel = new JLabel("Year:");
        carYearTextField = new JTextField(4);

        addVehButton = new JButton();
        addVehButton.setText("Add");
        updateVehButton = new JButton();
        updateVehButton.setText("Update");
        cancelVehButton = new JButton();
        cancelVehButton.setText("Cancel");
        saveVehButton = new JButton();
        saveVehButton.setText("Save");

        minorVehPanel1.add(regoLabel);
        minorVehPanel1.add(regoTextField);
        minorVehPanel2.add(makeLabel);
        minorVehPanel2.add(makeTextField);
        minorVehPanel3.add(modelLabel);
        minorVehPanel3.add(modelTextField);
        minorVehPanel4.add(carYearLabel);
        minorVehPanel4.add(carYearTextField);
        minorVehPanel5.add(addVehButton);
        minorVehPanel5.add(updateVehButton);
        minorVehPanel5.add(cancelVehButton);

        TitledBorder vehPanelTitle;
        vehPanelTitle = BorderFactory.createTitledBorder("Vehicle");
        vehPanel.setBorder(vehPanelTitle);
        vehPanel.setLayout(new BoxLayout(vehPanel, BoxLayout.PAGE_AXIS));
        vehPanel.add(minorVehPanel1);
        vehPanel.add(minorVehPanel2);
        vehPanel.add(minorVehPanel3);
        vehPanel.add(minorVehPanel4);
        vehPanel.add(minorVehPanel5);

        //vehPanel.add();
        //vehPanel.add();
        //vehPanel.add();
        //service panel parts
        servIdLabel = new JLabel("Service ID:");
        servPriceLabel = new JLabel("Price:");
        servDateLabel = new JLabel("Date:");
        servYearLabel = new JLabel("Year");
        servMonthLabel = new JLabel("Month");
        servDayLabel = new JLabel("Day");
        servIdTextField = new JTextField(3);
        servDescLabel = new JLabel("Description:");
        servDescTextField = new JTextArea(4, 15);
        JScrollPane descScrollPane = new JScrollPane(servDescTextField);
        servDescTextField.setLineWrap(true);
        servDatePanel.setLayout(
                new GridLayout(2, 1));
        servYearTextField = new JTextField(3);
        servPriceTextField = new JTextField(3);
        //minorServPanel2.setBackground(Color.red);

        addServButton = new JButton();
        addServButton.setText("Add");
        updateServButton = new JButton();
        updateServButton.setText("Update");
        cancelServButton = new JButton();
        cancelServButton.setText("Cancel");

        minorServPanel1.add(servIdLabel);
        minorServPanel1.add(servIdTextField);
        minorServPanel2.add(servYearLabel);
        minorServPanel2.add(servMonthLabel);
        minorServPanel2.add(servDayLabel);
        minorServPanel3.add(servDateLabel);
        minorServPanel3.add(servYearTextField);
        minorServPanel3.add(monthCombo);
        minorServPanel3.add(dayCombo);
        monthCombo.setSelectedIndex(-1);

        servDatePanel.setLayout(new BoxLayout(servDatePanel, BoxLayout.PAGE_AXIS));
        servDatePanel.add(minorServPanel2);
        servDatePanel.add(minorServPanel3);
        minorServPanel4.add(servPriceLabel);
        minorServPanel4.add(servPriceTextField);
        minorServPanel5.add(servDescLabel);
        minorServPanel5.add(descScrollPane);
        minorServPanel6.add(addServButton);
        minorServPanel6.add(updateServButton);
        minorServPanel6.add(cancelServButton);

        TitledBorder servPanelTitle;
        servPanelTitle = BorderFactory.createTitledBorder("Services");

        servPanel.setBorder(servPanelTitle);
        servPanel.setBorder(vehPanelTitle);
        servPanel.setLayout(new BoxLayout(servPanel, BoxLayout.PAGE_AXIS));
        servPanel.add(minorServPanel1);
        servPanel.add(Box.createVerticalStrut(25));
        servPanel.add(servDatePanel);
        servPanel.add(Box.createVerticalStrut(25));
        servPanel.add(minorServPanel4);
        servPanel.add(minorServPanel5);
        servPanel.add(minorServPanel6);
        servPanel.add(Box.createVerticalStrut(25));

        //display table and panel
        displayPanel = new JPanel();
        tableButtonPanel = new JPanel();
        tableButton1 = new JButton();
        tableButton1.setText("View all");
        tableButton2 = new JButton();
        tableButton2.setText("Statistics");
        tableButton3 = new JButton();
        tableButton3.setText("Clear all");
        tableButtonPanel.add(tableButton1);
        tableButtonPanel.add(tableButton2);
        tableButtonPanel.add(tableButton3);

        //current table
        displayTable = new JTable();
        DefaultTableModel myModel = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "First Name", "Last Name", "Phone", "Address", "REGO", "PRice"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        displayTable.setModel(myModel);
        displayTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        displayTable.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(displayTable);

        displayPanel.add(scrollPane);

        //query panel
        queryPanel = new JPanel();
        queryLeftPanel = new JPanel();
        queryRightPanel = new JPanel();

        TitledBorder phoneQueryTitle;
        phoneQueryTitle = BorderFactory.createTitledBorder("Search by Phone");
        queryLeftPanel.setBorder(phoneQueryTitle);
        TitledBorder nameQueryTitle;
        nameQueryTitle = BorderFactory.createTitledBorder("Search by Name");
        queryRightPanel.setBorder(nameQueryTitle);

        queryByNameLabel = new JLabel("Last Name");
        queryByNameTextField = new JTextField(10);
        queryByPhoneLabel = new JLabel("Phone:");
        queryByPhoneTextField = new JTextField(10);
        queryByNameButton = new JButton("Search");
        queryByPhoneButton = new JButton("Search");

        queryLeftPanel.add(queryByPhoneLabel);
        queryLeftPanel.add(queryByPhoneTextField);
        queryLeftPanel.add(queryByPhoneButton);

        queryRightPanel.add(queryByNameLabel);
        queryRightPanel.add(queryByNameTextField);
        queryRightPanel.add(queryByNameButton);

        queryPanel.add(queryLeftPanel);
        queryPanel.add(queryRightPanel);

        //top information panel
        topInfoPanel.setLayout(
                new GridLayout(0, 3));
        topInfoPanel.setPreferredSize(
                new Dimension(800, 350));
        topInfoPanel.add(custPanel);

        topInfoPanel.add(vehPanel);

        topInfoPanel.add(servPanel);

        mainPanel.setLayout(
                new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(
                new Dimension(900, 750));
        mainPanel.add(topInfoPanel);

        mainPanel.add(displayPanel);
        mainPanel.add(tableButtonPanel);
        mainPanel.add(queryPanel);

        mainPanel.add(bottomPanel);
        TitledBorder dataTitleMain;
        dataTitleMain = BorderFactory.createTitledBorder("Data Entry, Browse & Query");

        mainFramePanel.setBorder(dataTitleMain);

        mainFramePanel.add(Box.createHorizontalStrut(40));
        mainFramePanel.add(mainPanel);
        //scrollPane1 = new JScrollPane(mainPanelGraphs);
        mainPanelGraphs.add(fxPanel);
        mainFramePanel.add(Box.createHorizontalStrut(40));
        mainPanelCard.add(mainFramePanel, "a");
        mainPanelCard.add(mainPanelGraphs, "b");

        add(mainPanelCard);

        setLayout(
                new FlowLayout(FlowLayout.CENTER, 10, 10));
        setSize(
                1200, 855);
        setResizable(
                true);
        setVisible(
                true);

        tableButton2.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                testButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        exitButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                exitButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        tableButton1.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                viewAllButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        tableButton3.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                viewVehicleButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        addCustButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                enterCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
    }

    private void exitButtonActionPerformed(ActionEvent evt) {
        presenter.exitWindow();
    }

    //temp button to test opening graph on new panel
    private void testButtonActionPerformed(ActionEvent evt) {
        presenter.testingCount();
        CardLayout cL1 = (CardLayout) mainPanelCard.getLayout();
        cL1.show(mainPanelCard, "b");
    }

    //temp button to show get all data with sql joins
    private void viewAllButtonActionPerformed(ActionEvent evt) {
        presenter.getServices();
    }

    private void enterCustomerButtonActionPerformed(ActionEvent evt) {
        entryCustomerFields();
        if (customerEntryValidation()) {
            presenter.addCustomer(fName, lName, phone, address);
            clearCustomerField();
        }

    }

    private void viewVehicleButtonActionPerformed(ActionEvent evt) {
        presenter.getAllVehicles();
    }

    public void bind(VSMSPresenter p) {
        presenter = p;
    }

    public void displayMessage(String m) {
        JOptionPane.showMessageDialog(this, m);
    }

    //displaying customers on the table
    public void displayCustomers(List<Customer> c) {

        DefaultTableModel currentModel = (DefaultTableModel) this.displayTable.getModel();
        //TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) displayTable.getModel()));

        //sorter.setRowFilter(RowFilter.regexFilter("(?i)"));
        //displayTable.setRowSorter(sorter);
        int size = c.size();

        //adding data from presenter to the table   
        for (int i = 0; i < size; i++) {
            Object[] data = new Object[4];
            data[0] = c.get(i).getId();
            data[1] = c.get(i).getFirstName();
            data[2] = c.get(i).getLastName();
            data[3] = c.get(i).getPhone();
            data[4] = c.get(i).getAddress();
            currentModel.setRowCount(i);
            currentModel.addRow(data);
        }

    }

    //displaying services on the table
    public void displayServices(List<Service> c) {

        DefaultTableModel currentModel = (DefaultTableModel) this.displayTable.getModel();
        //TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) displayTable.getModel()));

        //sorter.setRowFilter(RowFilter.regexFilter("(?i)"));
        //displayTable.setRowSorter(sorter);
        int size = c.size();
        //HUH
        displayMessage("this is adding and testing");
        for (int i = 0; i < size; i++) {
            Object[] data = new Object[7];
            data[0] = c.get(i).getCarRego().getCustomer().getId();
            data[1] = c.get(i).getCarRego().getCustomer().getFirstName();
            data[2] = c.get(i).getCarRego().getCustomer().getLastName();
            data[3] = c.get(i).getCarRego().getCustomer().getPhone();
            data[4] = c.get(i).getCarRego().getCustomer().getAddress();
            data[5] = c.get(i).getCarRego().getRegistration();
            data[6] = c.get(i).getPrice();
            currentModel.setRowCount(i);
            currentModel.addRow(data);
        }

    }

    //displaying vehicles on the table
    public void displayVehicles(List<Vehicle> v) {

        DefaultTableModel currentModel = (DefaultTableModel) this.displayTable.getModel();
        //TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) displayTable.getModel()));

        //sorter.setRowFilter(RowFilter.regexFilter("(?i)"));
        //displayTable.setRowSorter(sorter);
        int size = v.size();
        //HUH
        displayMessage("this is adding and testing");
        for (int i = 0; i < size; i++) {
            Object[] data = new Object[7];
            data[0] = v.get(i).getRegistration();
            data[1] = v.get(i).getMake();
            data[2] = v.get(i).getModel();
            data[3] = v.get(i).getYear() + "test";
            data[4] = v.get(i).getCustomer().getFirstName();
            data[5] = v.get(i).getOdometer();
            data[6] = v.get(i).getCustomer().getId();
            currentModel.setRowCount(i);
            currentModel.addRow(data);
        }
    }

    public void testing(List<String> ls) {
        String makeOne = "";
        int countOne = 0;
        String makeTwo = "";
        int countTwo = 0;
        String makeThree = "";
        int countThree = 0;
        if (ls.size() == 3) {

            String[] split = ls.get(0).toString().split(":", -1);
            makeOne = split[0];
            countOne = Integer.parseInt(split[1]);

            split = ls.get(1).toString().split(":", -1);
            makeTwo = split[0];
            countTwo = Integer.parseInt(split[1]);

            split = ls.get(2).toString().split(":", -1);
            makeThree = split[0];
            countThree = Integer.parseInt(split[1]);

            displayMessage(makeOne+"test"+countOne);
            displayMessage(makeTwo+"test"+countTwo );
            displayMessage( "test" + makeThree + countThree);
        }

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Make");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number Of Services");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series data = new XYChart.Series();
        data.setName("Serviced Makes");

        data.getData().add(new XYChart.Data(makeOne, countOne));
        data.getData().add(new XYChart.Data(makeTwo, countTwo));
        data.getData().add(new XYChart.Data(makeThree, countThree));
        barChart.getData().add(data);
        //scrollPane1.add(barChart);
        //Scene scene = createScene();
        testPane.setCenter(barChart);

        fxPanel.setScene(new Scene(testPane));

    }

    public void enableCustomerField() {

    }

    public void enableVehicleField() {

    }

    public void enableServiceField() {

    }

    public void clearCustomerField() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        phoneTextField.setText("");
        addressTextField.setText("");
    }

    public void clearVehicleField() {

    }

    public void clearServiceField() {

    }

    public void entryCustomerFields() {
        fName = StringUtils.capitalize(firstNameTextField.getText().trim().toLowerCase());
        lName = StringUtils.capitalize(lastNameTextField.getText().trim().toLowerCase());
        phone = phoneTextField.getText();
        address = addressTextField.getText();
    }

    public Boolean customerEntryValidation() {
        boolean test = false;
        if (fName.length() == 0 || lName.length() == 0 || address.length() == 0 || phone.length() == 0) {
            displayMessage("Please fill all customer fields");
            return test;
        } else if (!fName.matches("\\D+") || !lName.matches("\\D+")) {
            displayMessage("Names must not contain digits");
            return test;
        } else if (phone.matches("\\D+")) {
            displayMessage("Phone number must contain only digits");
            phoneTextField.requestFocus();
            return test;
        } else if (address.matches("\\D+")) {
            displayMessage("Address must contain a number");
            addressTextField.requestFocus();
            return test;
        } else {
            test = true;
        }
        return test;
    }

    public static void failedConnect(String m) {
        JOptionPane.showMessageDialog(null, "Connection to database failed: " + m);
    }
}
