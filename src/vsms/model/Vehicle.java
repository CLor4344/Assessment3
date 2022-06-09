/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsms.model;

import java.util.Date;

/**
 *
 * @author Colbey
 */
public class Vehicle {
    private String registration;
    private String make;
    private String model;
    private int odometer;
    private int year;
    private Customer customer;
    
    public Vehicle(){
        
    }

    public Vehicle(String registration, String make, String model, int odometer, int date, Customer customerId) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.odometer = odometer;
        
        this.year = year;
        this.customer = customerId;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getDate() {
        return year;
    }

    public void setDate(int year) {
        this.year = year;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}
