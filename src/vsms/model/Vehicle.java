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
    private int year;
    private int odometer;
    private Customer customer;

    public Vehicle() {

    }

    public Vehicle(String registration, String make, String model, int year, int odometer, Customer customerId) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer = odometer;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
