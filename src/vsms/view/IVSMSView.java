/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vsms.view;

import java.util.List;
import vsms.model.Customer;
import vsms.model.Service;
import vsms.presenter.VSMSPresenter;

/**
 *
 * @author Colbey
 */
public interface IVSMSView {
    void bind(VSMSPresenter p);
    void displayMessage(String m);
    //void displayCustomers(List<Customer> c);
    void displayCustomers(List<Service> c);
    
}
