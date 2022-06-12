/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vsms.model;

import java.sql.Date;
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
    public Vehicle getVehicle(String rego);

    public List countMake();

    public int insertCustomer(String fName, String lName, String phone, String address);
    public int insertVehicle(String rego, String make, String model, int year, int odometer, int id);
    public int insertService(Date date, double price, String desc, String rego);

    public List<Vehicle> getCustomerVehicles(int id);

    public String testCustomer(String fName, String lName, String phone, String address);
    public Customer getCustomer(int id);

    public void close();
}
