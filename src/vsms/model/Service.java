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
public class Service {
    private int id;
    private Date date;
    private String description;
    private double price;
    private Vehicle carRego;

    public Service() {
    }

    public Service(int id, Date date, String description, double price, Vehicle carRego) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
        this.carRego = carRego;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Vehicle getCarRego() {
        return carRego;
    }

    public void setCarRego(Vehicle carRego) {
        this.carRego = carRego;
    }
    
    
}
