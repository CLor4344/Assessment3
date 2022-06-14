/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsms.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javafx.application.Platform;
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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    int serviceDay = 0;
    int serviceMonth = 0;
    int serviceYear = 0;
    double servicePrice = 0;
    String serviceDesc = "";

    private JPanel mainPanel;
    private JPanel mainPanelCard;
    private JPanel mainPanelGraphs;
    private JPanel bottomPanel;
    private JPanel topInfoPanel;
    private JPanel mainFramePanel;
    private JButton exitButton;
    private JPanel queryRightRightPanel;
    private JLabel queryByRegoLabel;
    private JTextField queryByRegoTextField;
    private JButton queryByRegoButton;

    //customer Panel components
    private JPanel custPanel;
    private JPanel minorCustPanel1;
    private JPanel minorCustPanel2;
    private JPanel minorCustPanel3;
    private JPanel minorCustPanel4;
    private JPanel minorCustPanel5;
    private JPanel minorCustPanel6;
    private JLabel custIdLabel;
    private JTextField custIdTextField;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel addressLabel;
    private JTextArea addressTextField;
    private JScrollPane addressPane;
    private JButton addCustButton;
    private JButton updateCustButton;
    private JButton cancelCustButton;
    private JButton backButton;
    private JButton saveCustButton;
    private JButton saveCustUpdButton;
    private JPanel custButtonCard;

    private BorderPane testPane;

    //vehicle panel components
    private JPanel vehPanel;
    private JPanel minorVehPanel1;
    private JPanel minorVehPanel2;
    private JPanel minorVehPanel3;
    private JPanel minorVehPanel4;
    private JPanel minorVehPanel5;
    private JPanel minorVehPanel6;

    private JLabel regoLabel;
    private JTextField regoTextField;
    private JLabel makeLabel;
    private JTextField makeTextField;
    private JLabel modelLabel;
    private JTextField modelTextField;
    private JLabel carYearLabel;
    private JTextField carYearTextField;
    private JLabel vehOdometerLabel;
    private JTextField vehOdometerTextField;

    private JButton addVehButton;
    private JButton updateVehButton;
    private JButton cancelVehButton;
    private JButton saveVehButton;
    private JButton saveVehUpdButton;
    private JPanel vehButtonCard;

    //veh cycle panel
    private JPanel vehBrowsePanel;
    private JTextField vehNumTextField;
    private JLabel vehOfLabel;
    private JTextField vehDenTextField;
    private JButton vehNextButton;
    private JButton vehPrevButton;

    //service panel components
    private JPanel servPanel;
    private JPanel minorServPanel1;
    private JPanel minorServPanel2;
    private JPanel minorServPanel3;
    private JPanel minorServPanel4;
    private JPanel minorServPanel5;
    private JPanel minorServPanel6;
    private JPanel servDatePanel;
    //  private JButton servNextButton;
    //  private JButton servPrevButton;

    private JButton addServButton;
    private JButton deleteServButton;
    private JButton cancelServButton;
    private JButton saveServButton;
    private JPanel servButtonCard;

    private JLabel servIdLabel;
    private JTextField servIdTextField;
    private JLabel servDateLabel;
    private JTextField servYearTextField;
    private JLabel servYearLabel;
    private JLabel servMonthLabel;
    private JLabel servDayLabel;

    String[] months = new DateFormatSymbols().getMonths();
    String[] actualMonth = Arrays.copyOf(months, months.length - 1);
    private JComboBox<String> monthCombo = new JComboBox(actualMonth);
    private JComboBox dayCombo = new JComboBox();
    private JLabel servPriceLabel;
    private JTextField servPriceTextField;
    private JLabel servDescLabel;
    private JTextArea servDescTextField;
    private JScrollPane descScrollPane;

    private JPanel servBrowsePanel;
    private JTextField servNumTextField;
    private JLabel servOfLabel;
    private JTextField servDenTextField;
    private JButton servNextButton;
    private JButton servPrevButton;

    //display panel components
    private JPanel displayCustPanel;
    private JPanel displayVehPanel;
    private JPanel displayServPanel;
    private JPanel displayCard;
    private JTable displayCustTable;
    private JTable displayVehTable;
    private JTable displayServTable;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private JPanel tableButtonPanel;
    private JButton tableButton1;
    private JButton tableButton11;
    private JButton tableButton111;
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

    private JPanel tablePanel;

    //statics panel elements
    JFXPanel fxPanel = new JFXPanel();
    private JTable displayMinMaxTable;
    private JTable displayMakeTable;
    private JScrollPane scrollPane4;
    private JScrollPane scrollPane5;
    private JPanel statMainPanel;
    private JPanel statLeftPanel;
    private JPanel statRightPanel;

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
        minorCustPanel6 = new JPanel();

        vehPanel = new JPanel();
        minorVehPanel1 = new JPanel();
        minorVehPanel2 = new JPanel();
        minorVehPanel3 = new JPanel();
        minorVehPanel4 = new JPanel();
        minorVehPanel5 = new JPanel();
        minorVehPanel6 = new JPanel();
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
        backButton = new JButton();
        backButton.setText("Back");
        exitButton = new JButton();
        exitButton.setText("EXIT");
        bottomPanel.add(exitButton);

        //customer panel parts
        custIdLabel = new JLabel("Customer ID:");
        custIdTextField = new JTextField(3);
        custIdTextField.setEditable(false);
        firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new JTextField(6);
        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField(6);
        phoneLabel = new JLabel("Phone:");
        phoneTextField = new JTextField(8);
        addressLabel = new JLabel("Address:");

        addressTextField = new JTextArea(3, 15);
        JScrollPane addressPane = new JScrollPane(addressTextField);
        addressTextField.setLineWrap(true);

        addCustButton = new JButton();
        addCustButton.setText("Add");
        updateCustButton = new JButton();
        updateCustButton.setText("Update");
        cancelCustButton = new JButton();
        cancelCustButton.setText("Clear");
        saveCustButton = new JButton();
        saveCustButton.setText("Save");
        saveCustUpdButton = new JButton();
        saveCustUpdButton.setText("Save");

        custButtonCard = new JPanel(new CardLayout());

        custButtonCard.add(addCustButton, "a");
        custButtonCard.add(saveCustButton, "b");
        custButtonCard.add(saveCustUpdButton, "c");

        TitledBorder custPanelTitle;
        custPanelTitle = BorderFactory.createTitledBorder("Customer");
        custPanel.setBorder(custPanelTitle);
        custPanel.setLayout(new BoxLayout(custPanel, BoxLayout.PAGE_AXIS));
        minorCustPanel1.add(custIdLabel);
        minorCustPanel1.add(custIdTextField);
        minorCustPanel1.add(Box.createHorizontalStrut(125));
        minorCustPanel2.add(firstNameLabel);
        minorCustPanel2.add(firstNameTextField);
        minorCustPanel2.add(Box.createHorizontalStrut(125));
        minorCustPanel3.add(lastNameLabel);
        minorCustPanel3.add(lastNameTextField);
        minorCustPanel3.add(Box.createHorizontalStrut(125));
        minorCustPanel4.add(phoneLabel);
        minorCustPanel4.add(Box.createHorizontalStrut(12));
        minorCustPanel4.add(phoneTextField);
        minorCustPanel4.add(Box.createHorizontalStrut(100));
        minorCustPanel5.add(addressLabel);
        minorCustPanel5.add(addressPane);
        minorCustPanel5.add(Box.createHorizontalStrut(105));
        minorCustPanel6.add(custButtonCard);
        minorCustPanel6.add(updateCustButton);
        minorCustPanel6.add(cancelCustButton);

        custPanel.add(minorCustPanel1);
        custPanel.add(minorCustPanel2);
        custPanel.add(minorCustPanel3);
        custPanel.add(minorCustPanel4);
        custPanel.add(minorCustPanel5);
        custPanel.add(minorCustPanel6);

        //vehicle panel parts
        regoLabel = new JLabel("Registration:");
        regoTextField = new JTextField(5);
        makeLabel = new JLabel("Make:");
        makeTextField = new JTextField(6);
        modelLabel = new JLabel("Model:");
        modelTextField = new JTextField(6);
        carYearLabel = new JLabel("Year:");
        carYearTextField = new JTextField(4);
        vehOdometerLabel = new JLabel("Odometer:");
        vehOdometerTextField = new JTextField(6);

        vehBrowsePanel = new JPanel();
        vehNumTextField = new JTextField(2);
        vehNumTextField.setEditable(false);
        vehOfLabel = new JLabel("Of");
        vehDenTextField = new JTextField(2);
        vehDenTextField.setEditable(false);
        vehNextButton = new JButton(">>");
        vehPrevButton = new JButton("<<");
        vehNextButton.setEnabled(false);
        vehPrevButton.setEnabled(false);
        vehBrowsePanel.add(vehPrevButton);
        vehBrowsePanel.add(vehNumTextField);
        vehBrowsePanel.add(vehOfLabel);
        vehBrowsePanel.add(vehDenTextField);
        vehBrowsePanel.add(vehNextButton);

        vehButtonCard = new JPanel(new CardLayout());
        addVehButton = new JButton();
        saveVehUpdButton = new JButton();
        addVehButton.setText("Add");
        updateVehButton = new JButton();
        updateVehButton.setText("Update");
        cancelVehButton = new JButton();
        cancelVehButton.setText("Clear");
        saveVehButton = new JButton();
        saveVehButton.setText("Save");
        saveVehUpdButton.setText("Save");
        addVehButton.setEnabled(false);
        vehButtonCard.add(addVehButton, "a");
        vehButtonCard.add(saveVehButton, "b");
        vehButtonCard.add(saveVehUpdButton, "c");

        minorVehPanel1.add(regoLabel);
        minorVehPanel1.add(Box.createHorizontalStrut(5));
        minorVehPanel1.add(regoTextField);
        minorVehPanel1.add(Box.createHorizontalStrut(125));
        minorVehPanel2.add(makeLabel);
        minorVehPanel2.add(Box.createHorizontalStrut(5));
        minorVehPanel2.add(makeTextField);
        minorVehPanel2.add(Box.createHorizontalStrut(145));
        minorVehPanel3.add(modelLabel);
        minorVehPanel3.add(Box.createHorizontalStrut(5));
        minorVehPanel3.add(modelTextField);
        minorVehPanel3.add(Box.createHorizontalStrut(150));
        minorVehPanel4.add(carYearLabel);
        minorVehPanel4.add(Box.createHorizontalStrut(5));
        minorVehPanel4.add(carYearTextField);
        minorVehPanel4.add(Box.createHorizontalStrut(30));
        minorVehPanel4.add(vehOdometerLabel);
        minorVehPanel4.add(vehOdometerTextField);
        minorVehPanel5.add(vehButtonCard);
        minorVehPanel5.add(updateVehButton);
        minorVehPanel5.add(cancelVehButton);

        TitledBorder vehPanelTitle;
        vehPanelTitle = BorderFactory.createTitledBorder("Vehicle");
        vehPanel.setBorder(vehPanelTitle);
        vehPanel.setLayout(new BoxLayout(vehPanel, BoxLayout.PAGE_AXIS));
        vehPanel.add(minorVehPanel1);
        vehPanel.add(Box.createVerticalStrut(25));
        vehPanel.add(minorVehPanel2);
        vehPanel.add(Box.createVerticalStrut(25));
        vehPanel.add(minorVehPanel3);
        vehPanel.add(Box.createVerticalStrut(25));
        vehPanel.add(minorVehPanel4);
        vehPanel.add(Box.createVerticalStrut(25));
        vehPanel.add(vehBrowsePanel);
        vehPanel.add(minorVehPanel5);
        vehPanel.add(Box.createVerticalStrut(22));

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
        servPriceTextField = new JTextField(6);

        addServButton = new JButton();
        addServButton.setText("Add");
        addServButton.setEnabled(false);
        deleteServButton = new JButton();
        deleteServButton.setText("Delete");
        cancelServButton = new JButton();
        cancelServButton.setText("Clear");

        minorServPanel1.add(servIdLabel);
        minorServPanel1.add(servIdTextField);
        minorServPanel1.add(Box.createHorizontalStrut(155));
        minorServPanel2.add(Box.createHorizontalStrut(40));
        minorServPanel2.add(servYearLabel);
        minorServPanel2.add(Box.createHorizontalStrut(25));
        minorServPanel2.add(servMonthLabel);
        minorServPanel2.add(Box.createHorizontalStrut(40));
        minorServPanel2.add(servDayLabel);
        minorServPanel2.add(Box.createHorizontalStrut(40));
        minorServPanel3.add(servDateLabel);
        minorServPanel3.add(servYearTextField);

        dayCombo.addItem("Day");
        dayCombo.setEnabled(false);
        dayCombo.setToolTipText("Choose a month and year first.");
        dayCombo.setPrototypeDisplayValue("XXX");

        minorServPanel3.add(monthCombo);
        minorServPanel3.add(dayCombo);
        monthCombo.setSelectedIndex(-1);
        monthCombo.setPrototypeDisplayValue("SEPTEMBER");

        servButtonCard = new JPanel(new CardLayout());
        saveServButton = new JButton();
        saveServButton.setText("Save");
        servButtonCard.add(addServButton, "a");
        servButtonCard.add(saveServButton, "b");

        servDatePanel.setLayout(new BoxLayout(servDatePanel, BoxLayout.PAGE_AXIS));
        servDatePanel.add(minorServPanel2);
        servDatePanel.add(minorServPanel3);
        minorServPanel4.add(servPriceLabel);
        minorServPanel4.add(servPriceTextField);
        minorServPanel4.add(Box.createHorizontalStrut(115));
        minorServPanel5.add(servDescLabel);
        minorServPanel5.add(descScrollPane);
        minorServPanel6.add(servButtonCard);
        minorServPanel6.add(deleteServButton);
        minorServPanel6.add(cancelServButton);

        servBrowsePanel = new JPanel();
        servNumTextField = new JTextField(2);
        servNumTextField.setEditable(false);
        servOfLabel = new JLabel("Of");
        servDenTextField = new JTextField(2);
        servDenTextField.setEditable(false);
        servNextButton = new JButton(">>");
        servPrevButton = new JButton("<<");
        servNextButton.setEnabled(false);
        servPrevButton.setEnabled(false);
        servBrowsePanel.add(servPrevButton);
        servBrowsePanel.add(servNumTextField);
        servBrowsePanel.add(servOfLabel);
        servBrowsePanel.add(servDenTextField);
        servBrowsePanel.add(servNextButton);

        TitledBorder servPanelTitle;
        servPanelTitle = BorderFactory.createTitledBorder("Services");
        servPanel.setBorder(servPanelTitle);
        servPanel.setBorder(servPanelTitle);
        servPanel.setLayout(new BoxLayout(servPanel, BoxLayout.PAGE_AXIS));
        servPanel.add(minorServPanel1);
        //servPanel.add(Box.createVerticalStrut(25));
        servPanel.add(servDatePanel);
        //servPanel.add(Box.createVerticalStrut(25));
        servPanel.add(minorServPanel4);
        servPanel.add(minorServPanel5);

        servPanel.add(servBrowsePanel);
        servPanel.add(minorServPanel6);
        servPanel.add(Box.createVerticalStrut(17));

        //display table and panel
        displayCard = new JPanel(new CardLayout());
        tableButtonPanel = new JPanel();
        tableButton1 = new JButton();
        tableButton1.setText("View cust");
        tableButton11 = new JButton();
        tableButton11.setText("View veh");
        tableButton111 = new JButton();
        tableButton111.setText("View serv");
        tableButton2 = new JButton();
        tableButton2.setText("Statistics");
        tableButton3 = new JButton();
        tableButton3.setText("Clear all");
        tableButtonPanel.add(tableButton1);
        tableButtonPanel.add(tableButton11);
        tableButtonPanel.add(tableButton111);
        tableButtonPanel.add(tableButton2);
        tableButtonPanel.add(tableButton3);

        //current table
        displayCustTable = new JTable();
        DefaultTableModel myModel = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "First Name", "Last Name", "Phone", "Address"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        displayVehTable = new JTable();
        DefaultTableModel myVehModel = new DefaultTableModel(new Object[][]{}, new String[]{"CustomerID", "Owner", "Registration", "Make", "Model", "Year", "Odometer",}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //displayVehTable.getColumn(1).setMinWidth(0);
        //displayVehTable.getColumn(1).setMaxWidth(0);
        // displayVehTable.getColumn(1).setWidth(0);
        displayServTable = new JTable();
        DefaultTableModel myServModel = new DefaultTableModel(new Object[][]{}, new String[]{"CustomerID", "ServiceID", "Owner", "Date", "Registration", "Description", "Price"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        displayCustTable.setModel(myModel);
        displayCustTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        displayCustTable.setFillsViewportHeight(true);

        displayVehTable.setModel(myVehModel);
        displayVehTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        displayVehTable.setFillsViewportHeight(true);

        displayServTable.setModel(myServModel);
        displayServTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        displayServTable.setFillsViewportHeight(true);

        displayCustTable.getTableHeader().setReorderingAllowed(false);
        displayCustTable.getTableHeader().setEnabled(false);
        displayCustTable.setAutoCreateRowSorter(false);
        displayVehTable.getTableHeader().setReorderingAllowed(false);
        displayVehTable.getTableHeader().setEnabled(false);
        displayVehTable.setAutoCreateRowSorter(false);
        displayServTable.getTableHeader().setReorderingAllowed(false);
        displayServTable.getTableHeader().setEnabled(false);
        displayServTable.setAutoCreateRowSorter(false);
        //hide dummy info
        displayVehTable.getColumnModel().getColumn(0).setMinWidth(0);
        displayVehTable.getColumnModel().getColumn(0).setMaxWidth(0);
        displayVehTable.getColumnModel().getColumn(0).setWidth(0);
        displayServTable.getColumnModel().getColumn(0).setMinWidth(0);
        displayServTable.getColumnModel().getColumn(0).setMaxWidth(0);
        displayServTable.getColumnModel().getColumn(0).setWidth(0);

        scrollPane1 = new JScrollPane(displayCustTable);
        scrollPane2 = new JScrollPane(displayVehTable);
        scrollPane3 = new JScrollPane(displayServTable);

        displayCard.add(scrollPane1, "a");
        displayCard.add(scrollPane2, "b");
        displayCard.add(scrollPane3, "c");

        //query panel
        queryPanel = new JPanel();
        queryLeftPanel = new JPanel();
        queryRightPanel = new JPanel();
        queryRightRightPanel = new JPanel();

        TitledBorder phoneQueryTitle;
        phoneQueryTitle = BorderFactory.createTitledBorder("Search Customer by Phone");
        queryLeftPanel.setBorder(phoneQueryTitle);
        TitledBorder nameQueryTitle;
        nameQueryTitle = BorderFactory.createTitledBorder("Search Customer by Name");
        queryRightPanel.setBorder(nameQueryTitle);
        TitledBorder regoQueryTitle;
        regoQueryTitle = BorderFactory.createTitledBorder("Search Service by Rego");
        queryRightRightPanel.setBorder(regoQueryTitle);

        queryByNameLabel = new JLabel("Last Name:");
        queryByNameTextField = new JTextField(10);
        queryByPhoneLabel = new JLabel("Phone:");
        queryByPhoneTextField = new JTextField(10);
        queryByNameButton = new JButton("Search");
        queryByPhoneButton = new JButton("Search");

        queryByRegoLabel = new JLabel("Rego:");
        queryByRegoTextField = new JTextField(10);
        queryByRegoButton = new JButton("Search");

        queryLeftPanel.add(queryByPhoneLabel);
        queryLeftPanel.add(queryByPhoneTextField);
        queryLeftPanel.add(queryByPhoneButton);

        queryRightPanel.add(queryByNameLabel);
        queryRightPanel.add(queryByNameTextField);
        queryRightPanel.add(queryByNameButton);

        queryRightRightPanel.add(queryByRegoLabel);
        queryRightRightPanel.add(queryByRegoTextField);
        queryRightRightPanel.add(queryByRegoButton);

        queryPanel.add(queryLeftPanel);
        queryPanel.add(queryRightPanel);
        queryPanel.add(queryRightRightPanel);

        //Statistics Page ELEMENTS
        fxPanel.setScene(new Scene(testPane, 600, 400));

        displayMinMaxTable = new JTable();
        DefaultTableModel myAvgModel = new DefaultTableModel(new Object[][]{}, new String[]{"Min", "Max", "Average"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        displayMakeTable = new JTable();
        DefaultTableModel myMakeModel = new DefaultTableModel(new Object[][]{}, new String[]{"Make", "Number of services"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        displayMinMaxTable.setModel(myAvgModel);
        displayMinMaxTable.setPreferredScrollableViewportSize(new Dimension(300, 50));
        displayMinMaxTable.setFillsViewportHeight(true);
        displayMinMaxTable.getTableHeader().setReorderingAllowed(false);
        displayMinMaxTable.getTableHeader().setEnabled(false);
        displayMinMaxTable.setAutoCreateRowSorter(false);

        displayMakeTable.setModel(myMakeModel);
        displayMakeTable.setPreferredScrollableViewportSize(new Dimension(250, 200));
        displayMakeTable.setFillsViewportHeight(true);
        displayMakeTable.getTableHeader().setReorderingAllowed(false);
        displayMakeTable.getTableHeader().setEnabled(false);
        displayMakeTable.setAutoCreateRowSorter(false);

        scrollPane4 = new JScrollPane(displayMinMaxTable);
        scrollPane5 = new JScrollPane(displayMakeTable);

        statMainPanel = new JPanel();
        statLeftPanel = new JPanel();
        statRightPanel = new JPanel();
        statLeftPanel.add(scrollPane4);
        statRightPanel.add(scrollPane5);
        statMainPanel.add(statLeftPanel);
        statMainPanel.add(statRightPanel);

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

        mainPanel.add(displayCard);
        mainPanel.add(tableButtonPanel);
        mainPanel.add(queryPanel);

        mainPanel.add(bottomPanel);
        TitledBorder dataTitleMain;
        dataTitleMain = BorderFactory.createTitledBorder("Data Entry, Browse & Query");

        mainFramePanel.setBorder(dataTitleMain);
        mainPanelGraphs.setLayout(new BoxLayout(mainPanelGraphs, BoxLayout.PAGE_AXIS));
        mainFramePanel.add(Box.createHorizontalStrut(40));
        mainFramePanel.add(mainPanel);
        //scrollPane1 = new JScrollPane(mainPanelGraphs);
        mainPanelGraphs.add(fxPanel);
        mainPanelGraphs.add(statMainPanel);
        mainPanelGraphs.add(backButton);
        mainFramePanel.add(Box.createHorizontalStrut(40));
        mainPanelCard.add(mainFramePanel, "a");
        mainPanelCard.add(mainPanelGraphs, "b");
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);

        servYearTextField.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if (servYearTextField.getText().trim().length() > 0) {
                    if (servYearTextField.getText().trim().matches("\\d+")) {
                        if (checkYear(servYearTextField.getText().trim(), 0)) {

                            setDayNum(monthCombo.getSelectedIndex(), Integer.parseInt(servYearTextField.getText().trim()));
                        }

                    }
                }
            }

            public void focusGained(FocusEvent e) {

            }
        });

        //opening state of fields
        disableVehicleField();
        disableServiceField();
        updateCustButton.setEnabled(false);
        updateVehButton.setEnabled(false);
        deleteServButton.setEnabled(false);
        servIdTextField.setEditable(false);
        disableCustomerField();

        add(mainPanelCard);

        setLayout(
                new FlowLayout(FlowLayout.CENTER, 10, 10));
        setSize(
                1200, 855);
        setResizable(
                true);
        setVisible(
                true);

        //ACTION LISTENERS
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                presenter.exitWindow();

            } // end method windowClosing
        } // end anonymous inner class
        ); // end call to addWindowListener

        //button listeners
        tableButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        testButtonActionPerformed(e);
                    }
                });
            }
        });
        exitButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                exitButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        backButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        tableButton1.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                viewCustButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        tableButton11.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                viewVehicleButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        tableButton111.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                viewServButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        tableButton3.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                testingMatchButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        addCustButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                addCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        //save customer button
        saveCustButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                saveCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        updateCustButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                updateCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        cancelCustButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                cancelCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        queryByNameButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                nameQueryCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        queryByPhoneButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                phoneQueryCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        queryByRegoButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                regoQueryCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        cancelVehButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                cancelVehicleButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        cancelServButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                cancelServiceButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        saveCustUpdButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                saveUpdateCustomerButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        updateVehButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                updateVehicleButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        saveVehUpdButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                saveUpdateVehicleButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        deleteServButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                deleteServiceButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        addVehButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                vehAddButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        //end button
        saveVehButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                vehSaveButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        //end button
        addServButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                servAddButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        //end button
        saveServButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                servSaveButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        //end button
        vehNextButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                vehNextButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        //save customer button
        vehPrevButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                vehPrevButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        servNextButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                nextServiceButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        //save customer button
        servPrevButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                prevServiceButtonActionPerformed(evt);
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener
        monthCombo.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (servYearTextField.getText().trim().length() > 0 && checkYear(servYearTextField.getText().trim(), 1)) {

                    setDayNum(monthCombo.getSelectedIndex(), Integer.parseInt(servYearTextField.getText().trim()));
                } else {
                    servYearTextField.requestFocus();
                }
            } // end method actionPerformed
        } // end anonymous inner class
        ); // end call to addActionListener

        //table listeners
        displayCustTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && displayCustTable.getSelectedRow() != -1) {
                    tableEventListened(event, displayCustTable);
                }

            }
        });
        displayVehTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && displayVehTable.getSelectedRow() != -1) {
                    tableEventListened(event, displayVehTable);
                }

            }
        });
        displayServTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && displayServTable.getSelectedRow() != -1) {
                    tableEventListened(event, displayServTable);
                }

            }
        });
    }

    private void tableEventListened(ListSelectionEvent evt, JTable table) {
        int column = 0;
        int row = table.getSelectedRow();
        int cid = 0;
        int column2 = 4;

        if (table.equals(displayCustTable)) {
            if (table.getValueAt(row, column) instanceof Integer) {
                cid = (Integer) table.getValueAt(row, column);
                presenter.getCustomerVehicles(cid, "", -1);

            }

        }
        if (table.equals(displayVehTable)) {
            if (table.getValueAt(row, column2) instanceof String) {
                int tempId = (Integer) table.getValueAt(row, 0);
                String tempString = (String) table.getValueAt(row, 2);
                presenter.getCustomerVehicles(tempId, tempString, -1);

            }

        }
        if (table.equals(displayServTable)) {
            if (table.getValueAt(row, column2) instanceof String) {
                int tempCId = (Integer) table.getValueAt(row, 0);
                //lastNameTextField.setText(String.valueOf(tempId));
                String tempString = (String) table.getValueAt(row, 4);
                int tempSId = (Integer) table.getValueAt(row, 1);
                //String[] stringarray = tempId.split(":");
                //pid = Integer.parseInt(stringarray[0]);

                firstNameTextField.setText(tempString);
                presenter.getCustomerVehicles(tempCId, tempString, tempSId);

            }

        }
        //patientIdTextField.setText(String.valueOf(who));

    }

    private void exitButtonActionPerformed(ActionEvent evt) {
        presenter.exitWindow();
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        CardLayout cL1 = (CardLayout) mainPanelCard.getLayout();
        cL1.show(mainPanelCard, "a");
    }

    private void vehNextButtonActionPerformed(ActionEvent evt) {
        presenter.showNext();
    }

    private void vehPrevButtonActionPerformed(ActionEvent evt) {
        presenter.showPrevious();
    }

    private void nextServiceButtonActionPerformed(ActionEvent evt) {
        presenter.servShowNext();
    }

    private void prevServiceButtonActionPerformed(ActionEvent evt) {
        presenter.servPrevious();
    }

    private void vehAddButtonActionPerformed(ActionEvent evt) {
        CardLayout cL1 = (CardLayout) vehButtonCard.getLayout();
        cL1.show(vehButtonCard, "b");
        clearVehicleField();
        enableVehicleField();

    }

    private void vehSaveButtonActionPerformed(ActionEvent evt) {
        if (entryVehicleFields()) {
            if (vehicleEntryValidation()) {
                clearVehicleField();
                presenter.addVehicle(rego, make, model, year, odometer, Integer.parseInt(custIdTextField.getText()));
                CardLayout cL1 = (CardLayout) vehButtonCard.getLayout();
                cL1.show(vehButtonCard, "a");
            }
        }

    }

    private void servAddButtonActionPerformed(ActionEvent evt) {
        CardLayout cL1 = (CardLayout) servButtonCard.getLayout();
        cL1.show(servButtonCard, "b");
        clearServiceField();
        enableServiceField();

    }

    private void servSaveButtonActionPerformed(ActionEvent evt) {
        if (entryServiceFields()) {
            if (serviceEntryValidation()) {
                clearServiceField();

                presenter.addService(serviceYear, serviceMonth, serviceDay, servicePrice, serviceDesc, regoTextField.getText(), Integer.parseInt(custIdTextField.getText()));
                CardLayout cL1 = (CardLayout) servButtonCard.getLayout();
                cL1.show(servButtonCard, "a");
            }
        }

    }

    private void updateCustomerButtonActionPerformed(ActionEvent evt) {

        CardLayout cL1 = (CardLayout) custButtonCard.getLayout();
        cL1.show(custButtonCard, "c");
        disableCustomerField();
        phoneTextField.setEditable(true);
        addressTextField.setEditable(true);
        updateCustButton.setEnabled(false);
        entryCustomerFields();

    }

    private void saveUpdateCustomerButtonActionPerformed(ActionEvent evt) {

        if (checkUpdate() == true) {
            entryCustomerFields();
            if (customerEntryValidation()) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to update customer details?", "Update Confirmation", JOptionPane.YES_NO_OPTION);
                if (reply == 0) {
                    presenter.updateCustomer(Integer.parseInt(custIdTextField.getText()), phone, address);
                    CardLayout cL1 = (CardLayout) custButtonCard.getLayout();
                    cL1.show(custButtonCard, "a");
                    updateCustButton.setEnabled(true);
                    phoneTextField.setEditable(false);
                    addressTextField.setEditable(false);
                }
            }
        } else {
            displayMessage("No changes");
            CardLayout cL1 = (CardLayout) custButtonCard.getLayout();
            cL1.show(custButtonCard, "a");
            updateCustButton.setEnabled(true);
            phoneTextField.setEditable(false);
            addressTextField.setEditable(false);

        }

    }

    private void saveUpdateVehicleButtonActionPerformed(ActionEvent evt) {
        if (isValidOdo(vehOdometerTextField.getText())) {
            if (checkVehUpdate() == true) {
                entryVehicleFields();
                if (vehicleEntryValidation()) {
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to update vehicle odometer details?", "Update Confirmation", JOptionPane.YES_NO_OPTION);
                    if (reply == 0) {
                        presenter.updateVehicle(rego, odometer);
                        CardLayout cL1 = (CardLayout) vehButtonCard.getLayout();
                        cL1.show(vehButtonCard, "a");
                        updateVehButton.setEnabled(true);
                        vehOdometerTextField.setEditable(false);
                    }
                }

            } else {
                displayMessage("No changes");
                CardLayout cL1 = (CardLayout) vehButtonCard.getLayout();
                cL1.show(vehButtonCard, "a");
                updateVehButton.setEnabled(true);
                vehOdometerTextField.setEditable(false);

            }
        }

    }

    private void updateVehicleButtonActionPerformed(ActionEvent evt) {

        CardLayout cL1 = (CardLayout) vehButtonCard.getLayout();
        cL1.show(vehButtonCard, "c");
        disableVehicleField();
        updateVehButton.setEnabled(false);
        vehOdometerTextField.setEditable(true);
        entryVehicleFields();

    }

    private void deleteServiceButtonActionPerformed(ActionEvent evt) {

        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the current service", "Service Deletion Confirmation", JOptionPane.YES_NO_OPTION);
        if (reply == 0) {
            int id = Integer.parseInt(servIdTextField.getText());
            presenter.deleteService(id);
            deleteServButton.setEnabled(true);
            int tempCId = Integer.parseInt(custIdTextField.getText());
            String tempRego = regoTextField.getText();
            int tempSId = 0;

            presenter.getCustomerVehicles(tempCId, tempRego, tempSId);

        }
    }

    private void nameQueryCustomerButtonActionPerformed(ActionEvent evt) {
        if (nameQueryCheck()) {
            presenter.getCustomersByName(lName);
            queryByNameTextField.setText("");
        }
    }

    private void regoQueryCustomerButtonActionPerformed(ActionEvent evt) {
        if (regoQueryCheck()) {
            presenter.getServiceByRego(rego);
            queryByRegoTextField.setText("");
        }
    }

    private void phoneQueryCustomerButtonActionPerformed(ActionEvent evt) {
        if (phoneQueryCheck()) {
            presenter.getCustomersByPhone(phone);
            queryByPhoneTextField.setText("");
        }
    }

    private void testingMatchButtonActionPerformed(ActionEvent evt) {
        clearCustomerField();
        disableCustomerField();
        clearVehicleField();
        disableVehicleField();
        clearServiceField();
        disableServiceField();
    }
    //temp button to test opening graph on new panel

    private void testButtonActionPerformed(ActionEvent evt) {
        presenter.testingCount();
        presenter.getMakeService();
        presenter.getMinMaxService();

        CardLayout cL1 = (CardLayout) mainPanelCard.getLayout();
        cL1.show(mainPanelCard, "b");
    }

    //temp button to show get all data with sql joins
    private void viewAllButtonActionPerformed(ActionEvent evt) {
        presenter.getServicesList();
    }

    private void cancelCustomerButtonActionPerformed(ActionEvent evt) {
        clearCustomerField();
        disableCustomerField();
        updateCustButton.setEnabled(false);
        CardLayout cL1 = (CardLayout) custButtonCard.getLayout();
        cL1.show(custButtonCard, "a");
    }

    private void cancelVehicleButtonActionPerformed(ActionEvent evt) {
        clearVehicleField();
        disableVehicleField();
        setVehicleBrowsing(false);
        updateVehButton.setEnabled(false);
        CardLayout cL1 = (CardLayout) vehButtonCard.getLayout();
        cL1.show(vehButtonCard, "a");
    }

    private void cancelServiceButtonActionPerformed(ActionEvent evt) {
        clearServiceField();
        disableServiceField();
        setServiceBrowsing(false);
        deleteServButton.setEnabled(false);
        CardLayout cL1 = (CardLayout) servButtonCard.getLayout();
        cL1.show(servButtonCard, "a");
    }

    private void addCustomerButtonActionPerformed(ActionEvent evt) {
        clearCustomerField();
        enableCustomerField();
        updateCustButton.setEnabled(false);
        firstNameTextField.requestFocus();
        CardLayout cL1 = (CardLayout) custButtonCard.getLayout();
        cL1.show(custButtonCard, "b");
    }

    private void saveCustomerButtonActionPerformed(ActionEvent evt) {
        entryCustomerFields();
        if (customerEntryValidation()) {
            clearCustomerField();
            presenter.addCustomer(fName, lName, phone, address);
            disableCustomerField();
            CardLayout cL1 = (CardLayout) custButtonCard.getLayout();
            cL1.show(custButtonCard, "a");
        }
    }

    private void viewCustButtonActionPerformed(ActionEvent evt) {
        CardLayout cL1 = (CardLayout) displayCard.getLayout();
        cL1.show(displayCard, "a");
        presenter.getAllCustomers();
    }

    private void viewVehicleButtonActionPerformed(ActionEvent evt) {
        CardLayout cL1 = (CardLayout) displayCard.getLayout();
        cL1.show(displayCard, "b");
        presenter.getAllVehicles();
    }

    private void viewServButtonActionPerformed(ActionEvent evt) {
        CardLayout cL1 = (CardLayout) displayCard.getLayout();
        cL1.show(displayCard, "c");
        presenter.getServicesList();
    }

    public void bind(VSMSPresenter p) {
        presenter = p;
    }

    public void displayMessage(String m) {
        JOptionPane.showMessageDialog(this, m);
    }

    //displaying customers on the table
    public void displayCustomersTable(List<Customer> c) {

        DefaultTableModel currentModel = (DefaultTableModel) this.displayCustTable.getModel();

        CardLayout cL1 = (CardLayout) displayCard.getLayout();
        cL1.show(displayCard, "a");

        int size = c.size();

        //adding data from presenter to the table   
        for (int i = 0; i < size; i++) {
            Object[] data = new Object[5];
            data[0] = c.get(i).getId();
            data[1] = c.get(i).getFirstName();
            data[2] = c.get(i).getLastName();
            data[3] = c.get(i).getPhone();
            data[4] = c.get(i).getAddress();
            currentModel.setRowCount(i);
            currentModel.addRow(data);
        }

    }

    //displaying vehicles on the table
    public void displayVehiclesTable(List<Vehicle> v) {

        DefaultTableModel currentModel = (DefaultTableModel) this.displayVehTable.getModel();

        CardLayout cL1 = (CardLayout) displayCard.getLayout();
        cL1.show(displayCard, "b");
        int size = v.size();
        //HUH

        for (int i = 0; i < size; i++) {
            Object[] data = new Object[7];
            data[0] = v.get(i).getCustomer().getId();
            data[1] = v.get(i).getCustomer().getFirstName() + " " + v.get(i).getCustomer().getLastName();
            data[2] = v.get(i).getRegistration();
            data[3] = v.get(i).getMake();
            data[4] = v.get(i).getModel();
            data[5] = v.get(i).getYear();
            data[6] = v.get(i).getOdometer();
            currentModel.setRowCount(i);
            currentModel.addRow(data);
        }
    }

    //displaying services on the table
    public void displayServicesTable(List<Service> c) {

        DefaultTableModel currentModel = (DefaultTableModel) this.displayServTable.getModel();

        CardLayout cL1 = (CardLayout) displayCard.getLayout();
        cL1.show(displayCard, "c");
        int size = c.size();

        for (int i = 0; i < size; i++) {
            Object[] data = new Object[7];
            data[0] = c.get(i).getCarRego().getCustomer().getId();
            data[1] = c.get(i).getId();
            data[2] = c.get(i).getCarRego().getCustomer().getFirstName() + " " + c.get(i).getCarRego().getCustomer().getLastName();
            data[3] = c.get(i).getDate();
            data[4] = c.get(i).getCarRego().getRegistration();
            data[5] = c.get(i).getDescription();
            data[6] = "$" + String.format("%.2f", c.get(i).getPrice());
            currentModel.setRowCount(i);
            currentModel.addRow(data);
        }

    }
    //displaying services on the table

    public void displayMinMaxTable(List<Double> d) {

        DefaultTableModel currentModel = (DefaultTableModel) this.displayMinMaxTable.getModel();

        Object[] data = new Object[3];
        data[0] = "$" + String.format("%.2f", d.get(0));
        data[1] = "$" + String.format("%.2f", d.get(1));
        data[2] = "$" + String.format("%.2f", d.get(2));

        currentModel.setRowCount(0);
        currentModel.addRow(data);

    }

    public void displayMakeTable(List<String> s) {

        DefaultTableModel currentModel = (DefaultTableModel) this.displayMakeTable.getModel();
        int size = s.size();

        for (int i = 0; i < size; i++) {
            String[] split = s.get(i).toString().split(":", -1);
            String makeOne = split[0];
            int countOne = Integer.parseInt(split[1]);

            Object[] data = new Object[2];
            data[0] = makeOne;
            data[1] = countOne;

            currentModel.setRowCount(i);
            currentModel.addRow(data);
        }

    }

    public void displayCustomers(Customer c) {
        custIdTextField.setText(String.valueOf(c.getId()));
        firstNameTextField.setText(c.getFirstName());
        lastNameTextField.setText(c.getLastName());
        phoneTextField.setText(c.getPhone());
        addressTextField.setText(c.getAddress());
        addVehButton.setEnabled(true);
        disableCustomerField();
        CardLayout cL1 = (CardLayout) custButtonCard.getLayout();
        cL1.show(custButtonCard, "a");

    }

    public void displayVehicles(Vehicle v) {
        regoTextField.setText(v.getRegistration());
        makeTextField.setText(v.getMake());
        modelTextField.setText(v.getModel());
        carYearTextField.setText(String.valueOf(v.getYear()));
        vehOdometerTextField.setText(String.valueOf(v.getOdometer()));
        CardLayout cL1 = (CardLayout) vehButtonCard.getLayout();
        cL1.show(vehButtonCard, "a");
        addServButton.setEnabled(true);
    }

    public void displayServices(Service s) {
        Calendar c = Calendar.getInstance();
        String temp = String.format("%.2f", s.getPrice());
        servIdTextField.setText(String.valueOf(s.getId()));
        servIdTextField.setText(String.valueOf(s.getId()));
        servPriceTextField.setText("$" + temp);
        servDescTextField.setText(s.getDescription());
        servIdTextField.setText(String.valueOf(s.getId()));
        c.setTime(s.getDate());

        servYearTextField.setText(String.valueOf(c.get(Calendar.YEAR)));
        servYearTextField.setEditable(false);
        monthCombo.setSelectedIndex(c.get(Calendar.MONTH));

        setDayNum(c.get(Calendar.MONTH), c.get(Calendar.YEAR));
        dayCombo.setSelectedIndex(c.get(Calendar.DAY_OF_MONTH) - 1);

        CardLayout cL1 = (CardLayout) servButtonCard.getLayout();
        cL1.show(servButtonCard, "a");

    }

    public void vehicleMaxAndCurrent(int m, int c) {
        vehDenTextField.setText("" + m);
        vehNumTextField.setText("" + (c + 1));
    }

    public void serviceMaxAndCurrent(int m, int c) {
        servDenTextField.setText("" + m);
        servNumTextField.setText("" + (c + 1));
    }

    public void enableCustomerField() {
        firstNameTextField.setEditable(true);
        lastNameTextField.setEditable(true);
        phoneTextField.setEditable(true);
        addressTextField.setEditable(true);

    }

    public void disableCustomerField() {
        firstNameTextField.setEditable(false);
        lastNameTextField.setEditable(false);
        phoneTextField.setEditable(false);
        addressTextField.setEditable(false);

    }

    public void disableVehicleField() {

        regoTextField.setEditable(false);
        makeTextField.setEditable(false);
        modelTextField.setEditable(false);
        carYearTextField.setEditable(false);
        vehOdometerTextField.setEditable(false);

    }

    public void enableVehicleField() {
        regoTextField.setEditable(true);
        makeTextField.setEditable(true);
        modelTextField.setEditable(true);
        carYearTextField.setEditable(true);
        vehOdometerTextField.setEditable(true);
    }

    public void enableServiceField() {

        servYearTextField.setEditable(true);
        dayCombo.setEnabled(true);
        monthCombo.setEnabled(true);
        servPriceTextField.setEditable(true);
        servDescTextField.setEditable(true);
    }

    public void disableServiceField() {
        servIdTextField.setEditable(false);
        servYearTextField.setEditable(false);
        dayCombo.setEnabled(false);
        monthCombo.setEnabled(false);
        servPriceTextField.setEditable(false);
        servDescTextField.setEditable(false);
    }

    public void setCustomerUpdate(boolean tf) {
        updateCustButton.setEnabled(tf);
    }

    public void setVehicleUpdate(boolean tf) {
        updateVehButton.setEnabled(tf);
    }

    public void setServiceUpdate(boolean tf) {
        deleteServButton.setEnabled(tf);
    }

    public void clearCustomerField() {
        custIdTextField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        phoneTextField.setText("");
        addressTextField.setText("");
    }

    public void clearVehicleField() {
        regoTextField.setText("");
        makeTextField.setText("");
        modelTextField.setText("");
        carYearTextField.setText("");
        vehOdometerTextField.setText("");
        vehNumTextField.setText("");
        vehDenTextField.setText("");

    }

    public void clearServiceField() {
        servIdTextField.setText("");
        servYearTextField.setText("");
        dayCombo.setSelectedIndex(-1);
        monthCombo.setSelectedIndex(-1);
        servPriceTextField.setText("");
        servDescTextField.setText("");
        servNumTextField.setText("");
        servDenTextField.setText("");
    }

    public void entryCustomerFields() {
        if (firstNameTextField.getText().trim().length() != 0) {
            String cap = firstNameTextField.getText().trim().toLowerCase().substring(0, 1).toUpperCase() + firstNameTextField.getText().trim().toLowerCase().substring(1);
            fName = cap;
        }
        if (lastNameTextField.getText().trim().length() != 0) {
            String cap2 = lastNameTextField.getText().trim().toLowerCase().substring(0, 1).toUpperCase() + lastNameTextField.getText().trim().toLowerCase().substring(1);
            lName = cap2;
        }
        phone = phoneTextField.getText();
        address = addressTextField.getText();
    }

//validating customer entry fields
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
        } else if (phone.length() != 10) {
            displayMessage("Phone number must be 10 digits");
            phoneTextField.requestFocus();
            return test;
        } else if (!isValidAddress(address)) {
            displayMessage("Address must contain a street number and street name");
            addressTextField.requestFocus();
            return test;
        } else {
            test = true;
        }
        return test;
    }

    public Boolean entryVehicleFields() {
        boolean test = false;
        rego = regoTextField.getText().trim().toUpperCase();
        String cap = makeTextField.getText().trim().toLowerCase().substring(0, 1).toUpperCase() + makeTextField.getText().trim().toLowerCase().substring(1);
        String cap2 = modelTextField.getText().trim().toLowerCase().substring(0, 1).toUpperCase() + modelTextField.getText().trim().toLowerCase().substring(1);
        make = cap;
        model = cap2;
        String tempYear = carYearTextField.getText().trim();
        String tempOdo = vehOdometerTextField.getText().trim();
        if (tempYear.matches("")) {
            displayMessage("Year field can't be empty.");
            carYearTextField.requestFocus();
            return test;
        } else if (!tempYear.matches("\\d+")) {
            displayMessage("Year must be digits only.");
            carYearTextField.requestFocus();
            return test;
        } else if (tempOdo.matches("")) {
            displayMessage("Odometer field can't be empty.");
            vehOdometerTextField.requestFocus();
            return test;
        } else if (!tempOdo.matches("\\d+")) {
            displayMessage("Odometer must be digits only.");
            vehOdometerTextField.requestFocus();
            return test;
        } else {
            year = Integer.parseInt(tempYear);
            odometer = Integer.parseInt(tempOdo);
            test = true;
            return test;
        }

    }

    //validating customer entry fields
    public Boolean vehicleEntryValidation() {
        boolean test = false;
        Calendar tempCal = Calendar.getInstance();
        int cyear = tempCal.get(Calendar.YEAR);
        if (rego.length() == 0 || make.length() == 0 || model.length() == 0 || year == 0 || odometer == 0) {
            displayMessage("Please fill all customer fields.");
            return test;
        } else if (rego.length() > 7 || rego.length() < 3) {
            displayMessage("Registration must be between 3 and 7 characters long.");
            regoTextField.requestFocus();
            return test;
        } else if (!make.matches("\\D+")) {
            displayMessage("Vehicle make must not contain digits.");
            makeTextField.requestFocus();
            return test;
        } else if (String.valueOf(year).length() != 4) {
            displayMessage("Vehicle year must be 4 digits.");
            carYearTextField.requestFocus();
            return test;
        } else if (year > cyear + 2) {
            displayMessage("Vehicle year must not be more than 1 year passed current year.");
            carYearTextField.requestFocus();
            return test;
        } else {
            test = true;
        }
        return test;
    }

    public Boolean entryServiceFields() {
        boolean test = false;
        String n = ".*[0-9].*";

        String tempPrice = servPriceTextField.getText().trim();
        int count = tempPrice.length() - tempPrice.replace(".", "").length();
        if (servYearTextField.getText().trim().length() == 0) {
            displayMessage("Please choose a service date.");
        } else if (!servYearTextField.getText().trim().matches("\\d+")) {
            displayMessage("Service year can be digits only.");
            servYearTextField.requestFocus();
            return test;
        } else if (tempPrice.matches("")) {
            displayMessage("Price field can't be empty.");
            servPriceTextField.requestFocus();
            return test;
        } else if (!tempPrice.matches(n) && tempPrice.contains(".")) {
            displayMessage("Price must be digits only and no spaces.");
            servPriceTextField.requestFocus();
            return test;
        } else if (tempPrice.contains(" ")) {
            displayMessage("Price field cannot contain spaces.");
            servPriceTextField.requestFocus();
            return test;
        } else if (count > 1) {
            displayMessage("Price can have only one decimal.");
            servPriceTextField.requestFocus();
            return test;
        } else {
            serviceYear = Integer.parseInt(servYearTextField.getText().trim());

            serviceMonth = monthCombo.getSelectedIndex();
            serviceDay = dayCombo.getSelectedIndex() + 1;
            serviceDesc = servDescTextField.getText();
            servicePrice = Double.parseDouble(tempPrice);
            test = true;
        }
        return test;
    }

    public Boolean serviceEntryValidation() {
        String testYear = servYearTextField.getText().trim();
        //checkYear(testYear)

        if (monthCombo.getSelectedIndex() == -1) {
            displayMessage("Please choose a service month.");
            return false;
        } else if (dayCombo.getSelectedIndex() == -1) {
            displayMessage("Please choose a service day.");
            return false;
        } else if (monthCombo.getSelectedIndex() == -1) {
            displayMessage("Please choose a service month.");
            return false;
        } else if (servicePrice < 0) {
            displayMessage("Please provide positive service price.");
            return false;
        } else if (serviceDesc.length() == 0) {
            displayMessage("Please enter a short service description.");
            return false;
        } else {
            return true;
        }
    }

    public void setVehicleBrowsing(boolean tf) {
        vehNextButton.setEnabled(tf);
        vehPrevButton.setEnabled(tf);
    }

    public void setServiceBrowsing(boolean tf) {
        servNextButton.setEnabled(tf);
        servPrevButton.setEnabled(tf);
    }

    public boolean checkYear(String tempYear, int i) {
        Calendar tempCal = Calendar.getInstance();
        int cyear = tempCal.get(Calendar.YEAR);

        if (tempYear.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please Enter a year");
            servYearTextField.requestFocus();
            return false;
        } else if (!tempYear.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please enter only digits for year value.");
            servYearTextField.requestFocus();
            return false;
        } else if (tempYear.length() > 4) {
            JOptionPane.showMessageDialog(null, "Year must be four digits");
            servYearTextField.requestFocus();
            return false;
        }
        return true;
    }

    public void setDayNum(int m, int y) {
        //int m = monthCombo.getSelectedIndex() + 1;
        //int y;
        m = m + 1;
        int days;
        int testing;
        if (servYearTextField.getText().trim().length() == 0) {
            servYearTextField.requestFocus();
        } else if (checkYear(String.valueOf(y), 0) == true && monthCombo.getSelectedIndex() != -1) {
            y = Integer.parseInt(servYearTextField.getText().trim());
            YearMonth yearMonth = YearMonth.of(y, m);
            days = yearMonth.lengthOfMonth();

            testing = dayCombo.getSelectedIndex();
            dayCombo.removeAllItems();
            for (int i = 1; i < days + 1; i++) {
                dayCombo.addItem(i);
            }
            dayCombo.setEnabled(true);
            dayCombo.setSelectedIndex(-1);
            dayCombo.setSelectedItem(testing + 1);
        }

    }

    public boolean isValidOdo(String s) {

        if (!s.matches("\\d+")) {
            displayMessage("Odometer must be digits only.");
            vehOdometerTextField.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidAddress(String s) {
        String n = ".*[0-9].*";
        String a = ".*[a-zA-Z].*";
        String b = " ";
        return s.matches(n) && s.matches(a);
    }

    public boolean checkUpdate() {
        boolean change = true;
        if (phone.equals(phoneTextField.getText()) && address.equals(addressTextField.getText())) {
            change = false;
        }
        return change;
    }

    public boolean checkVehUpdate() {
        boolean change = true;
        String tempOdo = vehOdometerTextField.getText();

        if (!tempOdo.matches("\\d+")) {
            displayMessage("Odometer must be digits only.");
            vehOdometerTextField.requestFocus();
            change = false;
        } else if (odometer == Integer.parseInt(vehOdometerTextField.getText())) {
            change = false;
        }
        return change;
    }

    public boolean nameQueryCheck() {
        boolean test = false;
        lName = queryByNameTextField.getText();
        if (lName.length() == 0) {
            displayMessage("Please enter a last name to search.");
            queryByNameTextField.requestFocus();
            return test;
        } else if (!lName.matches("\\D+")) {
            displayMessage("Names must not contain digits");
            queryByNameTextField.requestFocus();
            return test;
        } else {
            test = true;
        }
        return test;
    }

    public boolean phoneQueryCheck() {
        boolean test = false;
        phone = queryByPhoneTextField.getText();
        if (phone.length() == 0) {
            displayMessage("Please enter a phone number to search.");
            return test;
        } else if (!phone.matches("\\d+")) {
            displayMessage("Phone number must contain only digits");
            return test;
        } else if (phone.length() != 10) {
            displayMessage("Phone number must be 10 digits.");
            return test;
        } else {
            test = true;
        }
        return test;
    }

    public boolean regoQueryCheck() {
        boolean test = false;
        rego = queryByRegoTextField.getText();
        if (rego.length() == 0) {
            displayMessage("Please enter a rego number to search.");
            return test;
        } else if (rego.length() < 3 || rego.length() > 7) {
            displayMessage("Rego number must be between 3 and service characters.");
            return test;
        } else {
            test = true;
        }
        return test;
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
        }

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Vehicle Make");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number Of Services");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series data = new XYChart.Series();
        data.setName("Cars Serviced by Make");

        data.getData().add(new XYChart.Data(makeOne, countOne));
        data.getData().add(new XYChart.Data(makeTwo, countTwo));
        data.getData().add(new XYChart.Data(makeThree, countThree));
        barChart.getData().add(data);
        testPane.setCenter(barChart);

    }

    public static void failedConnect(String m) {
        JOptionPane.showMessageDialog(null, "Connection to database failed: " + m);
    }
}
