package dao.daoInterfaces;

import Exceptions.CustomerExistsException;
import JavaBeans.Customer;
import Exceptions.CustomerExistsException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDao {

    void deleteCustomersCoupons(int customerId) throws SQLException; //This method is for the AdminFacade when we delete an customer.
    boolean isCustomerEmailExists(Customer customer) throws CustomerExistsException, SQLException; //This method if for the AdminFacade.
    int isCustomerExist(String email, String password) throws SQLException;
    void addCustomer(Customer customer) throws SQLException;
    void updateCustomers(Customer customer) throws SQLException;
    void deleteCustomer(int customerId) throws SQLException;
    ArrayList<Customer> getAllCustomers() throws SQLException;
    Customer getOneCustomer(int customerId) throws SQLException;

}
