/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vsms.view;

import java.util.List;
import vsms.model.Customer;
import vsms.model.Service;
import vsms.model.Vehicle;
import vsms.presenter.VSMSPresenter;

/**
 *
 * @author Colbey
 */
public interface IVSMSView {
    void bind(VSMSPresenter p);
    void displayMessage(String m);
    void displayCustomers(List<Customer> c);
    void displayServices(List<Service> c);
    void displayVehicles(List<Vehicle> v);
    void testing(List<String> ls);
    
}
