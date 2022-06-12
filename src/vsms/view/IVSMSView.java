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

    void displayCustomersTable(List<Customer> c);

    void displayServicesTable(List<Service> s);

    void displayVehiclesTable(List<Vehicle> v);

    void displayCustomers(Customer c);

    void displayServices(Service c);

    void displayVehicles(Vehicle v);
    void vehicleMaxAndCurrent(int m, int c);
    void serviceMaxAndCurrent(int m, int c);

    void testing(List<String> ls);

    void setVehicleBrowsing(boolean tf);

    void setServiceBrowsing(boolean tf);
    
    //field clearing and enable/disable
    void disableServiceField();
    void enableServiceField();
    void clearServiceField();
    void clearVehicleField();
    void enableVehicleField();
    void disableVehicleField();

}
