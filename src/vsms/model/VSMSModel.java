/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsms.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colbey
 */
public class VSMSModel implements IVSMSModel {

    private static final String URL = "jdbc:mysql://localhost:3306/carservicedb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pollop";

    private PreparedStatement selectAllCustomers = null;
    private PreparedStatement selectCustomerByFields = null;
    private PreparedStatement selectCustomerById = null;
    private PreparedStatement selectCustomerVehicles = null;
    private PreparedStatement insertNewCustomer = null;
    private PreparedStatement updateCustomer = null;
    private PreparedStatement selectAllJoin = null;
    private PreparedStatement selectAllVehicles = null;
    private PreparedStatement selectAllServices = null;
    private PreparedStatement countMakeQuery = null;
    private PreparedStatement selectAllJoinByCID = null;

    private Connection connection = null;

    public VSMSModel() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            selectAllCustomers = connection.prepareStatement("SELECT * FROM customers");
            selectCustomerById = connection.prepareStatement("SELECT * FROM customers WHERE customerid=?");
            selectCustomerByFields = connection.prepareStatement("SELECT * FROM customers WHERE firstname=? and lastname=? and phone=? and address=?");
            selectCustomerVehicles = connection.prepareStatement("SELECT * FROM vehicles as V Inner Join Customers as C on V.customerid=C.customerid WHERE c.customerid=?");
            insertNewCustomer = connection.prepareStatement("INSERT INTO Customers" + "(firstname, lastname, phone, address)" + "VALUES(?, ?, ?, ?)");
            selectAllVehicles = connection.prepareStatement("SELECT * FROM vehicles as V Inner Join Customers as C on V.customerid=C.customerid");
            selectAllServices = connection.prepareStatement("SELECT * FROM services");
            selectAllJoin = connection.prepareStatement("SELECT *\n"
                    + "FROM services As S \n"
                    + "Inner Join vehicles As V on V.registration=S.vehiclerego \n"
                    + "Inner Join customers As C on V.customerid=C.customerID");
            selectAllJoinByCID = connection.prepareStatement("SELECT *\n"
                    + "FROM services As S \n"
                    + "Inner Join vehicles As V on V.registration=S.vehiclerego \n"
                    + "Inner Join customers As C on V.customerid=C.customerID\n"
                    + "where c.customerid=? AND S.VehicleRego=?");
            countMakeQuery = connection.prepareStatement("SELECT v.make,  Count(v.make)\n"
                    + "FROM services As S \n"
                    + "Inner Join vehicles As V on V.registration=S.vehiclerego\n"
                    + "group by v.make\n"
                    + "order by count(v.make) DESC\n"
                    + "LIMIT 3;");

        } catch (SQLException sqlException) {
            vsms.view.VSMSView.failedConnect(sqlException.toString());
            System.exit(1);
        }
    }

    public List<String> countMake() {
        ResultSet rs = null;
        String name = "";
        String num = "";
        List test = new ArrayList<String>();

        try {
            rs = countMakeQuery.executeQuery();
            while (rs.next()) {
                name = rs.getString("make");
                num = String.valueOf(rs.getByte(2));
                test.add(name + ":" + num);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return test;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> c = null;
        ResultSet rs = null;
        try {
            rs = selectAllCustomers.executeQuery();
            c = fillCustomer(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return c;
    }

    public int insertCustomer(String fName, String lName, String phone, String address) {
        int outcome = 0;
        try {
            insertNewCustomer.setString(1, fName);
            insertNewCustomer.setString(2, lName);
            insertNewCustomer.setString(3, phone);
            insertNewCustomer.setString(4, address);

            outcome = insertNewCustomer.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return outcome;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> v = null;
        ResultSet rs = null;
        try {
            rs = selectAllVehicles.executeQuery();
            v = fillVehicles(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return v;
    }

    public List<Service> allServices() {
        ResultSet rs = null;
        List<Service> results = null;
        try {
            results = new ArrayList<Service>();
            rs = selectAllJoin.executeQuery();
            results = fillServices(rs);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return results;
    }

    public List<Service> serviceByCIdRego(int id, String rego) {
        ResultSet rs = null;
        List<Service> results = null;
        try {
            results = new ArrayList<Service>();
            selectAllJoinByCID.setInt(1, id);
            selectAllJoinByCID.setString(2, rego);
            rs = selectAllJoinByCID.executeQuery();
            results = fillServices(rs);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return results;
    }

    public List<Vehicle> getCustomerVehicles(int id) {
        List<Vehicle> vl = null;
        ResultSet rs = null;
        try {
            selectCustomerVehicles.setInt(1, id);
            rs = selectCustomerVehicles.executeQuery();
            vl = new ArrayList<Vehicle>();

            vl = fillVehicles(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                rs.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally 
        return vl;
    }

    public Customer getCustomer(int id) {
        ResultSet rs = null;
        Customer c = new Customer();
        try {
            selectCustomerById.setInt(1, id);
            rs = selectCustomerById.executeQuery();
            if (rs.next() == true) {
                c = returnCustomer(rs);
            }

        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return c;
    }

    private List<Service> fillServices(ResultSet rs) {
        List<Service> results = null;
        try {
            results = new ArrayList<Service>();
            while (rs.next()) {

                Customer c = new Customer(rs.getInt("customerid"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
                Vehicle v = new Vehicle(
                        rs.getString("registration"),
                        rs.getString("Make"),
                        rs.getString("Model"),
                        rs.getInt("year"),
                        rs.getInt("odometer"),
                        c
                );
                results.add(new Service(
                        rs.getInt("ServiceID"),
                        rs.getDate("ServiceDate"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        v
                ));
            } // end while
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return results;
    }

    private List<Customer> fillCustomer(ResultSet rs) {
        List<Customer> results = null;
        try {
            results = new ArrayList<Customer>();
            while (rs.next()) {
                results.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Phone"),
                        rs.getString("address")
                ));
            } // end while

        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return results;
    }

    private List<Vehicle> fillVehicles(ResultSet rs) {
        List<Vehicle> results = null;
        Customer c = new Customer();
        try {
            results = new ArrayList<Vehicle>();
            while (rs.next()) {
                c = returnCustomer(rs);
                results.add(new Vehicle(
                        rs.getString("Registration"),
                        rs.getString("Make"),
                        rs.getString("Model"),
                        rs.getInt("Year"),
                        rs.getInt("odometer"),
                        c
                ));
            } // end while

        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return results;
    }

    public String testCustomer(String fName, String lName, String phone, String address) {
        String resultFound = "";
        ResultSet rs = null;
        try {
            selectCustomerByFields.setString(1, fName);
            selectCustomerByFields.setString(2, lName);
            selectCustomerByFields.setString(3, phone);
            selectCustomerByFields.setString(4, address);
            rs = selectCustomerByFields.executeQuery();
            if (rs.next() == false) {
                resultFound = "0";
            } else {
                resultFound = "1:" + rs.getString("customerid");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return resultFound;
    }

    public Customer returnCustomer(ResultSet rs) {
        Customer c = new Customer();

        try {
            c.setId(rs.getInt("customerid"));
            c.setFirstName(rs.getString("firstname"));
            c.setLastName(rs.getString("lastname"));
            c.setPhone(rs.getString("phone"));
            c.setAddress(rs.getString("address"));

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return c;

    }

    public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close
}
