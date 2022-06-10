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
    List<Customer> customers;
    //List<V> results;
    //Taxpayer currentEntry;

    public VSMSPresenter(IVSMSView ivv, IVSMSModel ivm) {
        view = ivv;
        model = ivm;
        currentIndexNumber = 0;
        numberOfEntries = 0;
        customers = null;

    }

    public void addCustomer(String fName, String lName, String phone, String address) {
        int o = model.insertCustomer(fName, lName, phone, address);

        if (o == 1) {
            view.displayMessage("Customer " + fName + " was added.");
        } else {
            view.displayMessage("Customer was not added.");
        }
    }
    public void testingCount(){
        List test = new ArrayList<String>();
        test = model.countMake();
        view.testing(test);
        
    }
    public void getServices() {
        try {
            ResultSet rs = null;
            List<Service> testing = model.allCustomer();
            populateServiceTables(testing);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllVehicles() {
        try {
            ResultSet rs = null;
            List<Vehicle> testing = model.getAllVehicles();
            populateVehcileTables(testing);
            //rs = model.allCustomer();
            /*numberOfEntries = customers.size();
            if (numberOfEntries == 0) {
                view.displayMessage("No patient records to browse");
            }
            if (numberOfEntries != 0) {
                populateTable();
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateVehcileTables(List<Vehicle> testing) {
        view.displayVehicles(testing);
    }

    public void populateServiceTables(List<Service> testing) {
        view.displayServices(testing);
    }

    public void populateCustomerTable(List<Customer> testing) {
        view.displayCustomers(testing);
    }

    public void exitWindow() {
        //model.close();
        System.exit(0);
    }
}
