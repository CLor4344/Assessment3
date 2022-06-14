/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsms.model;

import java.sql.Connection;
import java.sql.Date;
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

    private static final String URL = "jdbc:mysql://localhost:3306/carservicedbtesti";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pollop";

    private PreparedStatement selectAllCustomers = null;
    private PreparedStatement selectCustomerByFields = null;
    private PreparedStatement selectCustomerById = null;
    private PreparedStatement selectCustomerVehicles = null;
    private PreparedStatement insertNewCustomer = null;
    private PreparedStatement updateCustomer = null;
    private PreparedStatement queryCustomerByName = null;
    private PreparedStatement queryCustomerByPhone = null;
    private PreparedStatement queryServiceByRego = null;

    private PreparedStatement insertNewVehicle = null;
    private PreparedStatement insertNewService = null;
    private PreparedStatement updateVehicle = null;
    private PreparedStatement selectAllJoin = null;
    private PreparedStatement selectAllVehicles = null;
    private PreparedStatement selectAllServices = null;
    private PreparedStatement countMakeQuery = null;
    private PreparedStatement selectAllJoinByCID = null;
    private PreparedStatement selectAllJoinByCIDExclude = null;
    private PreparedStatement selectCustomerVehiclesExclude = null;
    private PreparedStatement serviceCalculations = null;
    private PreparedStatement serviceMakeInformation = null;

    private PreparedStatement deleteSelectedService = null;

    private PreparedStatement selectVehicleById = null;

    private Connection connection = null;

    public VSMSModel() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            selectAllCustomers = connection.prepareStatement("SELECT * FROM customers");
            selectCustomerById = connection.prepareStatement("SELECT * FROM customers WHERE customerid=?");
            queryCustomerByName = connection.prepareStatement("SELECT * FROM customers WHERE lastname LIKE ?");
            queryCustomerByPhone = connection.prepareStatement("SELECT * FROM customers WHERE phone=?");
            queryServiceByRego = connection.prepareStatement("SELECT * FROM services As S \n"
                    + "Inner Join vehicles As V on V.registration=S.vehiclerego\n"
                    + "Inner Join customers As C on V.customerid=C.customerID\n"
                    + "where S.VehicleRego=?\n"
                    + "ORDER BY price");
            updateCustomer = connection.prepareStatement("UPDATE customers SET phone = ?, address = ? WHERE customerid=?");

            serviceCalculations = connection.prepareStatement("SELECT MIN(price), MAX(price), AVG(price) FROM services");
            serviceMakeInformation = connection.prepareStatement("select make, count(make) FROM services\n"
                    + "inner join vehicles on registration=vehiclerego\n"
                    + "group by MAKE");
            updateVehicle = connection.prepareStatement("UPDATE vehicles SET odometer = ? WHERE registration=?");
            selectVehicleById = connection.prepareStatement("SELECT * FROM vehicles as V INNER JOIN customers as C on v.customerid=c.customerid WHERE v.registration=?");
            selectCustomerByFields = connection.prepareStatement("SELECT * FROM customers WHERE firstname=? and lastname=? and phone=? and address=?");
            selectCustomerVehicles = connection.prepareStatement("SELECT * FROM vehicles as V Inner Join Customers as C on V.customerid=C.customerid WHERE c.customerid=? AND V.registration=?");
            selectCustomerVehiclesExclude = connection.prepareStatement("SELECT * FROM vehicles as V Inner Join Customers as C on V.customerid=C.customerid WHERE c.customerid=? AND V.registration!=?");
            insertNewCustomer = connection.prepareStatement("INSERT INTO Customers" + "(firstname, lastname, phone, address)" + "VALUES(?, ?, ?, ?)");
            selectAllVehicles = connection.prepareStatement("SELECT * FROM vehicles as V Inner Join Customers as C on V.customerid=C.customerid");
            selectAllServices = connection.prepareStatement("SELECT * FROM services");

            //query for service table
            selectAllJoin = connection.prepareStatement("SELECT *\n"
                    + "FROM services As S \n"
                    + "Inner Join vehicles As V on V.registration=S.vehiclerego \n"
                    + "Inner Join customers As C on V.customerid=C.customerID \n"
                    + "ORDER BY price");
            //selecting service from table
            selectAllJoinByCID = connection.prepareStatement("SELECT *\n"
                    + "FROM services As S \n"
                    + "Inner Join vehicles As V on V.registration=S.vehiclerego \n"
                    + "Inner Join customers As C on V.customerid=C.customerID\n"
                    + "where c.customerid=? AND S.VehicleRego=? AND S.serviceid=? \n"
                    + "ORDER BY price");
            selectAllJoinByCIDExclude = connection.prepareStatement("SELECT *\n"
                    + "FROM services As S \n"
                    + "Inner Join vehicles As V on V.registration=S.vehiclerego \n"
                    + "Inner Join customers As C on V.customerid=C.customerID\n"
                    + "where c.customerid=? AND S.VehicleRego=? AND S.serviceid!=? \n"
                    + "ORDER BY price");
            countMakeQuery = connection.prepareStatement("SELECT v.make, Count(v.make)\n"
                    + "FROM services As S \n"
                    + "Inner Join vehicles As V on V.registration=S.vehiclerego\n"
                    + "group by v.make\n"
                    + "order by count(v.make) DESC\n"
                    + "LIMIT 3;");
            insertNewVehicle = connection.prepareStatement("INSERT INTO vehicles" + "(registration, make, model, year, odometer, customerid)" + "VALUES(?, ?, ?, ?, ?, ?)");
            insertNewService = connection.prepareStatement("INSERT INTO services" + "(servicedate, price, description, vehiclerego)" + "VALUES(?, ?, ?, ?)");
            deleteSelectedService = connection.prepareStatement("DELETE FROM services where serviceid=?");
        } catch (SQLException sqlException) {
            vsms.view.VSMSView.failedConnect(sqlException.toString());
            System.exit(1);
        }
    }

    public List<String> getMakeInfo() {
        ResultSet rs = null;
        String name = "";
        String num = "";
        List test = new ArrayList<String>();
        try {
            rs = serviceMakeInformation.executeQuery();
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

    public List<Double> getMinMax() {
        ResultSet rs = null;
        double min = 0;
        double max = 0;
        double avg= 0;
        List test = new ArrayList<Double>();
        try {
            rs = serviceCalculations.executeQuery();
            while (rs.next()) {
                min = rs.getDouble(1);
                max = rs.getDouble(2);
                avg = rs.getDouble(3);
                test.add(min);
                test.add(max);
                test.add(avg);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return test;
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

    public int insertVehicle(String rego, String make, String model, int year, int odometer, int id) {
        int outcome = 0;
        try {
            insertNewVehicle.setString(1, rego);
            insertNewVehicle.setString(2, make);
            insertNewVehicle.setString(3, model);
            insertNewVehicle.setInt(4, year);
            insertNewVehicle.setInt(5, odometer);
            insertNewVehicle.setInt(6, id);

            outcome = insertNewVehicle.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return outcome;
    }

    public int insertService(Date date, double price, String desc, String rego) {
        int outcome = 0;
        try {
            insertNewService.setDate(1, date);
            insertNewService.setDouble(2, price);
            insertNewService.setString(3, desc);
            insertNewService.setString(4, rego);
            outcome = insertNewService.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return outcome;
    }

    public int updateCustomer(int id, String phone, String address) {
        int result = 0;
        try {

            updateCustomer.setString(1, phone);
            updateCustomer.setString(2, address);
            updateCustomer.setInt(3, id);

            //returns # of rows updated
            result = updateCustomer.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return result;
    }

    public int updateVehicle(String rego, int odometer) {
        int result = 0;
        try {
            updateVehicle.setInt(1, odometer);
            updateVehicle.setString(2, rego);

            //returns # of rows updated
            result = updateVehicle.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return result;
    }

    public int removeSerive(int id) {

        int result = 0;
        try {
            deleteSelectedService.setInt(1, id);

            //returns # of rows updated
            result = deleteSelectedService.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return result;
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

    public List<Service> getServiceByRego(String rego) {
        List<Service> results = null;
        ResultSet rs = null;
        try {
            results = new ArrayList<Service>();
            queryServiceByRego.setString(1, rego);
            rs = queryServiceByRego.executeQuery();
            results = fillServices(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return results;
    }

    public List<Service> serviceByCIdRego(int id, String rego, int exclude) {
        ResultSet rs = null;
        ResultSet rs2 = null;
        List<Service> results = null;
        List<Service> r1 = null;
        List<Service> r2 = null;
        try {
            results = new ArrayList<Service>();
            r1 = new ArrayList<Service>();
            r2 = new ArrayList<Service>();
            selectAllJoinByCID.setInt(1, id);
            selectAllJoinByCID.setString(2, rego);
            selectAllJoinByCID.setInt(3, exclude);
            rs = selectAllJoinByCID.executeQuery();
            selectAllJoinByCIDExclude.setInt(1, id);
            selectAllJoinByCIDExclude.setString(2, rego);
            selectAllJoinByCIDExclude.setInt(3, exclude);
            rs2 = selectAllJoinByCIDExclude.executeQuery();
            r1 = fillServices(rs);
            r2 = fillServices(rs2);
            results.addAll(r1);
            results.addAll(r2);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return results;
    }

    public List<Vehicle> getCustomerVehicles(int id, String rego) {
        List<Vehicle> vResult = null;
        List<Vehicle> v1 = null;
        List<Vehicle> v2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            selectCustomerVehicles.setInt(1, id);
            selectCustomerVehicles.setString(2, rego);
            rs = selectCustomerVehicles.executeQuery();
            selectCustomerVehiclesExclude.setInt(1, id);
            selectCustomerVehiclesExclude.setString(2, rego);
            rs2 = selectCustomerVehiclesExclude.executeQuery();

            vResult = new ArrayList<Vehicle>();
            v1 = new ArrayList<Vehicle>();
            v2 = new ArrayList<Vehicle>();

            v1 = fillVehicles(rs);
            v2 = fillVehicles(rs2);
            vResult.addAll(v1);
            vResult.addAll(v2);

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
        return vResult;
    }

    public List<Customer> getCustomerByName(String name) {
        List<Customer> results = null;
        ResultSet rs = null;

        try {
            // executeQuery returns ResultSet containing matching entries
            queryCustomerByName.setString(1, "%" + name + "%");// specify name
            rs = queryCustomerByName.executeQuery();
            results = fillCustomer(rs);

        } // end try
        catch (SQLException sqlException) {
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

        return results;
    }

    public List<Customer> getCustomerByPhone(String phone) {
        List<Customer> results = null;
        ResultSet rs = null;

        try {
            // executeQuery returns ResultSet containing matching entries
            queryCustomerByPhone.setString(1, phone);// specify name
            rs = queryCustomerByPhone.executeQuery();
            results = fillCustomer(rs);

        } // end try
        catch (SQLException sqlException) {
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

        return results;
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

    public Vehicle getVehicle(String rego) {
        ResultSet rs = null;
        Vehicle v = new Vehicle();
        Customer c = new Customer();
        try {
            selectVehicleById.setString(1, rego);
            rs = selectVehicleById.executeQuery();
            if (rs.next() == true) {
                c = returnCustomer(rs);
                v.setRegistration(rs.getString("registration"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setYear(rs.getInt("year"));
                v.setOdometer(rs.getInt("odometer"));
                v.setCustomer(c);
            }

        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch
        return v;
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
                        rs.getDate("Servicedate"),
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
