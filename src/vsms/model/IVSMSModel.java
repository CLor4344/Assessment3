/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vsms.model;

import java.util.List;

/**
 *
 * @author Colbey
 */
public interface IVSMSModel {

    public List<Service> serviceByCIdRego(int id, String rego);
    public List<Service> allServices();

    public List<Vehicle> getAllVehicles();
    public List<Customer> getAllCustomers();

    public List countMake();

    public int insertCustomer(String fName, String lName, String phone, String address);

    public List<Vehicle> getCustomerVehicles(int id);

    public String testCustomer(String fName, String lName, String phone, String address);
    public Customer getCustomer(int id);

    public void close();
}
