package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modal.Customer;
import utility.DatabaseConnection;

/**
 *
 * @author Ebin
 */
public class CustomerDAO {

    public ArrayList<Customer> getAllCustomers() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Customer> lstCustomers = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "SELECT * FROM customers");

            rs = ps.executeQuery();
            while (rs.next()) {
                Customer objCustomer = new Customer(
                        rs.getString("id"),
                        rs.getString("firstName"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("visitTimeDate"),
                        rs.getString("postalCode"),
                        rs.getString("nationalId"),
                        rs.getString("lastName"));

                lstCustomers.add(objCustomer);
            }

        } catch (SQLException sqle) {
            System.err.println("SQLException in getAllCustomers()");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lstCustomers;

    }

    public void deleteCustomers(List<String> customerIds) {
        for (String id : customerIds) {
            deleteCustomerById(id);
        }
    }

    public Customer getCustomerById(String id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Customer objCustomer = null;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "SELECT * FROM customers WHERE id = " + id);

            rs = ps.executeQuery();

            while (rs.next()) {
                objCustomer = new Customer(
                        rs.getString("id"),
                        rs.getString("firstName"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("visitTimeDate"),
                        rs.getString("postalCode"),
                        rs.getString("nationalId"),
                        rs.getString("lastName"));

            }

        } catch (SQLException sqle) {
            System.err.println("SQLException in getCustomerById()");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return objCustomer;

    }

    public void deleteCustomerById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "DELETE FROM customers WHERE id = " + id);

            ps.execute();

        } catch (SQLException sqle) {
            System.err.println("SQLException in deleteCustomerById()");
            sqle.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int addCustomer(Customer objCustomer) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "INSERT into customers (firstName, country, city, visitTimeDate, postalCode, nationalId, lastName) "
                    + "VALUES (?,?,?,?,?,?,?)");

            ps.setString(1, objCustomer.getFirstName());
            ps.setString(2, objCustomer.getCountry());
            ps.setString(3, objCustomer.getCity());
            ps.setString(4, objCustomer.getVisitTimeDate());
            ps.setString(5, objCustomer.getPostalCode());
            ps.setString(6, objCustomer.getNationalId());
            ps.setString(7, objCustomer.getLastName());
            ps.execute();
        } catch (SQLException sqle) {
            System.err.println("SQLException in addCustomer()");
            sqle.printStackTrace();
            return -1;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 1;
    }

    public int updateCustomer(Customer objCustomer) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "UPDATE customers "
                    + "SET firstName=?, country=?, city=?, postalCode=?, nationalId=?, lastName=? "
                    + "WHERE id=?");

            ps.setString(1, objCustomer.getFirstName());
            ps.setString(2, objCustomer.getCountry());
            ps.setString(3, objCustomer.getCity());
            ps.setString(4, objCustomer.getPostalCode());
            ps.setString(5, objCustomer.getNationalId());
            ps.setString(6, objCustomer.getLastName());
            ps.setString(7, objCustomer.getId());

            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("SQLException in updateCustomer()");
            sqle.printStackTrace();
            return -1;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 1;
    }

}
