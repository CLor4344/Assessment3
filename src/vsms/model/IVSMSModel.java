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
    public List<Service> allCustomer();
    public List<Vehicle> getAllVehicles();
    public int insertCustomer(String fName, String lName, String phone, String address);
}
