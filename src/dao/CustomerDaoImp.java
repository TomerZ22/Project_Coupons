package dao;

import JavaBeans.Customer;
import dao.daoInterfaces.CustomersDao;
import db.ConnectionPool;
import Exceptions.CustomerExistsException;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDaoImp implements CustomersDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    /**
     * This method is called in the AdminFacade, in order to delete all coupons of a specific customer.
     *
     * @param customerId - the customer identifier to delete.
     * @throws SQLException - If the connection fails or didn't found the DB table.
     */
    public void deleteCustomersCoupons(int customerId) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM coupons_vs_customers WHERE custumers_id=" + customerId);
            ps.execute();
        }finally {
            pool.restoreConnection(con);
        }
    }
    /**
     * This method is to check if the customer email is already exist in the database.
     *
     * @param customer - The customer to check
     * @return - false if the customer is does not exist in the database.
     * @throws CustomerExistsException - If the customer does exist
     * @throws SQLException            - If the connection fails or didn't found the DB table.
     */
    public boolean isCustomerEmailExists(Customer customer) throws CustomerExistsException, SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT email FROM customers");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(4).equals(customer.getEmail())) {
                    pool.restoreConnection(con);
                    throw new CustomerExistsException("Customer already exists");
                }
            }
            return false;
        }finally {
            pool.restoreConnection(con);
        }
    }
    /**
     * This method returns a boolean if the email address and password are the same in the database.
     *
     * @param email    - the email address to check against the email address in the database
     * @param password - the password to check against the password in the database
     * @return - boolean if the email address and password are the same in the database
     * @throws SQLException - if an error occurs during the connection pool initialization to the database.
     */
    @Override
    public int isCustomerExist(String email, String password) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM customers");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(4).equals(email) && rs.getString(5).equals(password)) {
                    return 1;
                }
            }
            return -1;
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * For compatibility and write less code.
     *
     * @param ps       - The prepared statement
     * @param customer - The customer
     * @throws SQLException - If error accour during the connection to the DB
     */
    private void prepareStatement(PreparedStatement ps, Customer customer) throws SQLException {
        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getEmail());
        ps.setString(4, customer.getPassword());
        ps.execute();
    }

    /**
     * This method Adds a new customer to the database.
     *
     * @param customer - the customer to add to the database.
     * @throws SQLException - throws an exception if there were error during the connection to SQL
     */
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO customers(first_name, last_name, email, password) "
                    + "values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //DRY
            prepareStatement(ps, customer);
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            customer.setId(id);
        }finally {
            pool.restoreConnection(con);
        }
    }
    @Override
    public void updateCustomers(Customer customer) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement update = con.prepareStatement("UPDATE customers SET first_name= ?, last_name= ?, email= ?, password= ? WHERE id= " + customer.getId());
            prepareStatement(update, customer);
        }finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * This method deletes the customer from the database by its ID.
     *
     * @param customerId - ID of the customer to be deleted from the database.
     * @throws SQLException - throws an exception during the connection to the SQL.
     */
    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement delete = con.prepareStatement("DELETE FROM customers WHERE id= " + customerId);
            delete.execute();
        }finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * This method return an array list of the costumers in the database.
     *
     * @return - An array list of costumers or null if it's empty.
     * @throws SQLException - throws an exception if there was an error during the connection to the SQL.
     */
    @Override
   public ArrayList<Customer> getAllCustomers() throws SQLException {
       ArrayList<Customer> costumers = new ArrayList<>();
       Connection con = pool.getConnection();
       try {
           PreparedStatement query = con.prepareStatement("SELECT * FROM customers");
           ResultSet rs = query.executeQuery();
           while (rs.next()) {
               costumers.add(new Customer(rs.getInt(1), rs.getString(2),
                       rs.getString(3), rs.getString(4), rs.getString(5)));
               if (costumers.size() == 0) {
                   return null;
               }
           }
           return costumers;
       }finally {
           pool.restoreConnection(con);
       }
   }

    /**
     * This method returns you a costumer by its ID.
     *
     * @param customerId - The ID of the customer to retrieve.
     * @return - The costumer by its ID or null if there is no such id in the DB.
     * @throws SQLException - Throws an exception if there was an error during the connection to the SQL.
     */
   @Override
    public Customer getOneCustomer(int customerId) throws SQLException {
       Connection con = pool.getConnection();
       try {
           PreparedStatement gettingCustomer = con.prepareStatement("SELECT * FROM customers where id=" + customerId);
           ResultSet rs = gettingCustomer.executeQuery();
           if (rs.next()) {
               return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
           }
           return null;
       }finally {
           pool.restoreConnection(con);
       }
    }
}
