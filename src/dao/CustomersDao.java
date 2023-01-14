package dao;

import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDao {
    boolean isCustomerExist(String email, String password) throws SQLException;
    void addCustomer(Customer customer) throws SQLException;
    void updateCustomers(Customer customer) throws SQLException;
    void deleteCustomer(int customerId) throws SQLException;
    ArrayList<Customer> getAllCustomers() throws SQLException;
    Customer getOneCustomer(int customerId) throws SQLException;

}
