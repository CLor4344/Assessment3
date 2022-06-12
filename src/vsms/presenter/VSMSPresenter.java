/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsms.presenter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vsms.model.Customer;
import vsms.model.IVSMSModel;
import vsms.model.Service;
import vsms.model.Vehicle;
import vsms.view.IVSMSView;

/**
 *
 * @author Colbey
 */
public class VSMSPresenter {

    IVSMSView view;
    IVSMSModel model;
    int currentIndexNumber;
    int numberOfEntries;
    int currentServIndexNumber;
    int numberOfServEntries;
    Vehicle currentVeh;
    Service currentServ;
    Customer currentCustomer;
    List<Customer> customerResults;
    List<Vehicle> vehicleResults;
    List<Service> serviceResults;

    public VSMSPresenter(IVSMSView ivv, IVSMSModel ivm) {
        view = ivv;
        model = ivm;
        currentIndexNumber = 0;
        numberOfEntries = 0;
        currentVeh = null;
        customerResults = null;
        vehicleResults = null;
        serviceResults = null;
        currentServIndexNumber = 0;
        numberOfServEntries = 0;

    }

    public void addCustomer(String fName, String lName, String phone, String address) {
        String test = model.testCustomer(fName, lName, phone, address);
        String[] split = test.split(":", -1);
        int testNum = Integer.parseInt(split[0].toString());
        int cId = Integer.parseInt(split[1].toString());
        view.displayMessage(String.valueOf(cId) + String.valueOf(testNum));

        if (testNum == 0) {
            int o = model.insertCustomer(fName, lName, phone, address);

            if (o == 1) {
                view.displayMessage("Customer " + fName + " was added.");
            } else {
                view.displayMessage("Customer was not added.");
            }
        } else {
            view.displayMessage("Customer " + fName + " already exists.");
            currentCustomer = model.getCustomer(cId);
            populateCustomerFields();
        }

    }

    public void testingCount() {
        List test = new ArrayList<String>();
        test = model.countMake();
        view.testing(test);

    }

    public void getServicesList() {
        try {
            ResultSet rs = null;
            List<Service> testing = model.allServices();
            populateServiceTables(testing);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllFields(int id) {
        try {
            ResultSet rs = null;
            //List<Service> testing = model(id);
            //populateServiceTables(testing);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllVehicles() {
        try {
            ResultSet rs = null;
            List<Vehicle> testing = model.getAllVehicles();
            populateVehcileTables(testing);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllCustomers() {
        try {
            ResultSet rs = null;
            List<Customer> testing = model.getAllCustomers();
            populateCustomerTable(testing);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCustomerVehicles(int id, String rego) {
        serviceResults = model.serviceByCIdRego(id, rego);
        numberOfServEntries = serviceResults.size();
        vehicleResults = model.getCustomerVehicles(id);
        numberOfEntries = vehicleResults.size();
        currentCustomer = model.getCustomer(id);

        if (numberOfEntries == 0) {
            view.displayMessage("The customer has no entries.");
        }
        if (numberOfEntries == 1) {
            currentIndexNumber = 0;
            currentServIndexNumber = 0;
            currentVeh = vehicleResults.get(currentIndexNumber);
            populateVehicleFields();
            populateCustomerFields();
            view.displayMessage("the current service entries are:" + String.valueOf(numberOfServEntries));
            if (numberOfServEntries != 0) {
                currentServ = serviceResults.get(currentServIndexNumber);
                populateServiceFields();

            }
            view.setVehicleBrowsing(false);
        }
        if (numberOfEntries > 1) {
            currentIndexNumber = 0;
            currentServIndexNumber = 0;

            currentVeh = vehicleResults.get(currentIndexNumber);

            populateVehicleFields();
            populateCustomerFields();
            view.displayMessage("the current service entries are:" + String.valueOf(numberOfServEntries));
            if (numberOfServEntries != 0) {
                currentServ = serviceResults.get(currentServIndexNumber);
                populateServiceFields();

            }

            view.setVehicleBrowsing(true);
        }
        /*
        if (option == 1) {
            vehicleResults = model.getCustomerVehicles(id);
            numberOfEntries = vehicleResults.size();
            currentCustomer = model.getCustomer(id);

            if (numberOfEntries == 0) {
                view.displayMessage("The customer has no vehicles registered.");
            }
            if (numberOfEntries == 1) {
                currentIndexNumber = 0;
                numberOfServEntries = 0;
                currentVeh = vehicleResults.get(currentIndexNumber);
                populateVehicleFields();
                populateCustomerFields();

                view.setVehicleBrowsing(false);
            }
            if (numberOfEntries > 1) {
                currentIndexNumber = 0;
                currentServIndexNumber = 0;

                currentVeh = vehicleResults.get(currentIndexNumber);

                populateVehicleFields();
                populateCustomerFields();

                view.setVehicleBrowsing(true);
            }
        }
        if (option == 2) {

            serviceResults = model.serviceByCIdRego(id, rego);

            numberOfServEntries = serviceResults.size();
            vehicleResults = model.getCustomerVehicles(id);
            numberOfEntries = vehicleResults.size();
            currentCustomer = model.getCustomer(id);

            if (numberOfEntries == 0 || numberOfServEntries == 0) {
                view.displayMessage("The customer has no entries.");
            }
            if (numberOfEntries == 1) {
                currentIndexNumber = 0;
                numberOfServEntries = 0;
                currentVeh = vehicleResults.get(currentIndexNumber);
                populateVehicleFields();
                populateCustomerFields();
                populateServiceFields();
                view.setVehicleBrowsing(false);
            }
            if (numberOfEntries > 1) {
                currentIndexNumber = 0;
                currentServIndexNumber = 0;

                currentVeh = vehicleResults.get(currentIndexNumber);
                currentServ = serviceResults.get(currentServIndexNumber);
                populateVehicleFields();
                populateCustomerFields();
                populateServiceFields();
                view.setVehicleBrowsing(true);
            }
        }*/

    }

    /* public int matchCustomer(String fName, String lName, String phone, String address) {
        int test = 0;
        view.displayMessage(String.valueOf(test));
        test = model.testCustomer(fName, lName, phone, address);
        view.displayMessage(String.valueOf(test));
        return test;

    }*/
    public void showNext() {
        currentIndexNumber++;
        if (currentIndexNumber >= numberOfEntries) {
            currentIndexNumber = 0;
        }
        currentVeh = vehicleResults.get(currentIndexNumber);
        populateVehicleFields();

    }

    public void showPrevious() {
        currentIndexNumber--;
        if (currentIndexNumber < 0) {
            currentIndexNumber = numberOfEntries - 1;
        }
        currentVeh = vehicleResults.get(currentIndexNumber);
        populateVehicleFields();
    }

    public void populateCustomerFields() {
        view.displayCustomers(currentCustomer);
    }

    public void populateVehicleFields() {
        view.displayVehicles(currentVeh);
        view.vehicleMaxAndCurrent(numberOfEntries, currentIndexNumber);
    }

    public void populateServiceFields() {
        view.displayServices(currentServ);
    }

    public void populateVehcileTables(List<Vehicle> testing) {
        view.displayVehiclesTable(testing);
    }

    public void populateServiceTables(List<Service> testing) {
        view.displayServicesTable(testing);
    }

    public void populateCustomerTable(List<Customer> testing) {
        view.displayCustomersTable(testing);
    }

    public void exitWindow() {
        model.close();
        System.exit(0);
    }
}
