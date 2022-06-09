/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsms.presenter;

import java.sql.ResultSet;
import java.util.List;
import vsms.model.Customer;
import vsms.model.IVSMSModel;
import vsms.model.Service;
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

    public void getCustomers() {
        try {
            ResultSet rs = null;
            List<Service> testing = model.allCustomer();
            populateTable(testing);
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
    public void populateTable(List<Service> testing){
        view.displayCustomers(testing);
    }
    public void exitWindow() {
        //model.close();
        System.exit(0);
    }
}
