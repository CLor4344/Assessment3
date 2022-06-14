/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsms.presenter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
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
    Service currentFieldServ;
    Vehicle currentVeh;
    Service currentServ;
    Customer currentCustomer;
    List<Customer> customerResults;
    List<Vehicle> vehicleResults;
    List<Service> serviceResults;
    List<Service> serviceFieldResults;

    public VSMSPresenter(IVSMSView ivv, IVSMSModel ivm) {
        view = ivv;
        model = ivm;
        currentIndexNumber = 0;
        numberOfEntries = 0;
        currentVeh = null;
        customerResults = null;
        vehicleResults = null;
        serviceResults = null;
        serviceFieldResults = null;
        currentFieldServ = null;
        currentServIndexNumber = 0;
        numberOfServEntries = 0;

    }

    public void addCustomer(String fName, String lName, String phone, String address) {
        String test = model.testCustomer(fName, lName, phone, address);
        int testNum = 0;
        int cId = 0;
        if (test.matches("0")) {
            int o = model.insertCustomer(fName, lName, phone, address);

            if (o == 1) {
                view.displayMessage("Customer " + fName + " was added.");

                test = model.testCustomer(fName, lName, phone, address);
                String[] split = test.split(":", -1);
                testNum = Integer.parseInt(split[0].toString());
                cId = Integer.parseInt(split[1].toString());

                currentCustomer = model.getCustomer(cId);
                populateCustomerFields();
            } else {
                view.displayMessage("Customer was not added.");

            }
        } else {
            String[] split = test.split(":", -1);
            testNum = Integer.parseInt(split[0].toString());
            cId = Integer.parseInt(split[1].toString());
            view.displayMessage("Customer " + fName + " already exists.");
            currentCustomer = model.getCustomer(cId);
            populateCustomerFields();
        }

    }

    public void addVehicle(String rego, String make, String mod, int year, int odometer, int cid) {
        int testing = model.insertVehicle(rego, make, mod, year, odometer, cid);
        int fakeint = -1;
        if (testing == 0) {
            view.displayMessage("Vehcile " + rego + " was not added.");
        } else {
            view.displayMessage("Vehcile " + rego + " was added.");
            getCustomerVehicles(cid, rego, fakeint);
        }

    }

    public void addService(int year, int month, int day, double price, String desc, String rego, int cid) {

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        java.sql.Date date = new java.sql.Date(c.getTimeInMillis());//converting date obect to sql.Date object
        int fakeint = -1;

        int testing = model.insertService(date, price, desc, rego);
        if (testing == 0) {
            view.displayMessage("Vehcile " + rego + " was not added.");
        } else {
            view.displayMessage("Service was added.");
            getServices(cid, rego, fakeint);
        }
    }

    public void updateCustomer(int id, String phone, String address) {
        int test = model.updateCustomer(id, phone, address);
        if (test == 0) {
            view.displayMessage("No updates were made.");
        } else {
            view.displayMessage("Customer has been updated.");
        }

    }

    public void updateVehicle(String rego, int odometer) {
        int test = model.updateVehicle(rego, odometer);
        if (test == 0) {
            view.displayMessage("No updates were made.");
        } else {
            view.displayMessage("Customer has been updated.");
        }

    }

    public void deleteService(int id) {
        int test = model.removeSerive(id);
        if (test == 0) {
            view.displayMessage("No services were deleted.");
        } else {
            view.displayMessage("Service has been removed.");
            getServicesList();
        }
    }

    public void testingCount() {
        List test = new ArrayList<String>();
        test = model.countMake();
        view.testing(test);

    }

    public void getMinMaxService() {
        List test = new ArrayList<String>();
        test = model.getMinMax();
        populateMinMaxTable(test);

    }

    public void getMakeService() {
        List test = new ArrayList<String>();
        test = model.getMakeInfo();
        populateMakeTable(test);

    }

    public void getServicesList() {
        try {
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

            customerResults = model.getAllCustomers();
            populateCustomerTable(customerResults);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCustomersByName(String name) {

        customerResults = model.getCustomerByName(name);
        int search = customerResults.size();
        if (search == 0) {
            view.displayMessage("No results matching " + name);
        } else {
            view.displayMessage(search + " Matches Found!");
            populateCustomerTable(customerResults);
        }

    }


    public void getCustomersByPhone(String phone) {

        customerResults = model.getCustomerByPhone(phone);
        int search = customerResults.size();
        if (search == 0) {
            view.displayMessage("No results matching " + phone);
        } else {
            view.displayMessage("Match Found!");
            populateCustomerTable(customerResults);
        }

    }

    public void getServiceByRego(String rego) {

        serviceResults = model.getServiceByRego(rego);
        int search = serviceResults.size();
        if (search == 0) {
            view.displayMessage("No results matching " + rego);
        } else {
            view.displayMessage("Match Found!");
            populateServiceTables(serviceResults);
        }

    }

    public void getCustomerVehicles(int id, String rego, int exclude) {

        vehicleResults = model.getCustomerVehicles(id, rego);
        numberOfEntries = vehicleResults.size();
        currentCustomer = model.getCustomer(id);

        if (numberOfEntries == 0) {
            populateCustomerFields();
            //clear vehicle and services
            view.clearVehicleField();
            view.disableVehicleField();
            view.setVehicleUpdate(false);
            view.clearServiceField();
            view.disableServiceField();
            view.setServiceBrowsing(false);
            view.setServiceUpdate(false);
        } else if (numberOfEntries == 1) {
            currentIndexNumber = 0;
            currentVeh = vehicleResults.get(currentIndexNumber);
            populateCustomerFields();
            populateVehicleFields();
            view.setVehicleBrowsing(false);
            rego = currentVeh.getRegistration();

            serviceResults = model.serviceByCIdRego(id, rego, exclude);
            numberOfServEntries = serviceResults.size();
            currentServIndexNumber = 0;
            if (numberOfServEntries == 0) {
                view.clearServiceField();
                view.disableServiceField();
                view.setServiceBrowsing(false);
                view.setServiceUpdate(false);
            } else if (numberOfServEntries == 1) {
                currentServ = serviceResults.get(currentServIndexNumber);
                populateServiceFields();
                view.setServiceBrowsing(false);
            } else {
                currentServ = serviceResults.get(currentServIndexNumber);
                populateServiceFields();
                view.setServiceBrowsing(true);
            }
        } else {
            currentIndexNumber = 0;
            currentServIndexNumber = 0;

            currentVeh = vehicleResults.get(currentIndexNumber);
            populateVehicleFields();
            populateCustomerFields();
            view.setVehicleBrowsing(true);
            rego = currentVeh.getRegistration();
            getServices(id, rego, exclude);
        }

    }

    private void getServices(int id, String rego, int exclude) {
        //rego = currentVeh.getRegistration();
        serviceResults = model.serviceByCIdRego(id, rego, exclude);
        numberOfServEntries = serviceResults.size();

        if (numberOfServEntries == 0) {
            //clear sevice fields and disable fields
            view.clearServiceField();
            view.disableServiceField();
            view.setServiceBrowsing(false);
            view.setServiceUpdate(false);

        } else if (numberOfServEntries == 1) {
            currentServ = serviceResults.get(currentServIndexNumber);
            view.clearServiceField();
            view.disableServiceField();
            view.setServiceUpdate(true);
            populateServiceFields();
            view.setServiceBrowsing(false);
        } else {
            currentServ = serviceResults.get(currentServIndexNumber);
            view.clearServiceField();
            view.disableServiceField();
            view.setServiceUpdate(true);
            populateServiceFields();
            view.setServiceBrowsing(true);
        }
    }

    public void showNext() {
        currentIndexNumber++;
        int fakeint = -1;
        if (currentIndexNumber >= numberOfEntries) {
            currentIndexNumber = 0;
        }
        currentVeh = vehicleResults.get(currentIndexNumber);
        populateVehicleFields();
        getServices(currentVeh.getCustomer().getId(), currentVeh.getRegistration(), fakeint);

    }

    public void showPrevious() {
        currentIndexNumber--;
        int fakeint = -1;
        if (currentIndexNumber < 0) {
            currentIndexNumber = numberOfEntries - 1;
        }
        currentVeh = vehicleResults.get(currentIndexNumber);
        populateVehicleFields();
        getServices(currentVeh.getCustomer().getId(), currentVeh.getRegistration(), fakeint);
    }

    public void servShowNext() {
        currentServIndexNumber++;
        if (currentServIndexNumber >= numberOfServEntries) {
            currentServIndexNumber = 0;
        }
        currentServ = serviceResults.get(currentServIndexNumber);
        populateServiceFields();

    }

    public void servPrevious() {
        currentServIndexNumber--;
        if (currentServIndexNumber < 0) {
            currentServIndexNumber = numberOfServEntries - 1;
        }
        currentServ = serviceResults.get(currentServIndexNumber);
        populateServiceFields();
    }

    public void populateCustomerFields() {
        view.displayCustomers(currentCustomer);
        view.setCustomerUpdate(true);

    }

    public void populateVehicleFields() {
        view.displayVehicles(currentVeh);
        view.vehicleMaxAndCurrent(numberOfEntries, currentIndexNumber);
        view.setVehicleUpdate(true);
    }

    public void newVehicleFields() {
        view.displayVehicles(currentVeh);
    }

    public void populateServiceFields() {

        view.displayServices(currentServ);
        view.serviceMaxAndCurrent(numberOfServEntries, currentServIndexNumber);
        view.setServiceUpdate(true);
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

    public void populateMinMaxTable(List<Double> testing) {
        view.displayMinMaxTable(testing);
    }

    public void populateMakeTable(List<String> testing) {
        view.displayMakeTable(testing);
    }

    public void exitWindow() {
        model.close();
        System.exit(0);
    }
}
