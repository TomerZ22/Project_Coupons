package dao;

import JavaBeans.Coupon;
import JavaBeans.Customer;
import dao.daoInterfaces.CustomersDao;
import db.ConnectionPool;
import Exceptions.CustomerExistsException;
import enums.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            PreparedStatement ps = con.prepareStatement("DELETE FROM coupons_vs_customers WHERE customer_id=" + customerId);
            ps.execute();
        } finally {
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
                if (rs.getString("email").equals(customer.getEmail())) {
                    throw new CustomerExistsException("Customer already exists");
                }
            }
            return false;
        } finally {
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
                    int customerID = rs.getInt(1);
                    return customerID;
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
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public void updateCustomers(Customer customer) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement update = con.prepareStatement("UPDATE customers SET first_name= ?, last_name= ?, email= ?, password= ? WHERE id= " + customer.getId());
            prepareStatement(update, customer);
        } finally {
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
        } finally {
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
            PreparedStatement query = con.prepareStatement("SELECT * FROM coupons.customers");
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                costumers.add(new Customer(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)));
                if (costumers.size() == 0) {
                    return null;
                }
            }
            this.getCustomersCoupons(costumers);
            return costumers;
        } finally {
            pool.restoreConnection(con);
        }
    }

    private void getCustomersCoupons(List<Customer> customers) throws SQLException {
        Connection conn = pool.getConnection();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT coupons.coupons_vs_customers.customer_id, coupons.coupons.id, coupons.coupons.company_id, coupons.coupons.category_id, coupons.coupons.title, coupons.coupons.description, coupons.coupons.start_date, coupons.coupons.end_date, coupons.coupons.amount, coupons.coupons.price, coupons.coupons.image\n" +
                    "FROM coupons.coupons, coupons.coupons_vs_customers, coupons.customers\n" +
                    "WHERE coupons.coupons_vs_customers.customer_id = coupons.customers.id and coupons.coupons_vs_customers.coupon_id = coupons.coupons.id");
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                for (Customer customer : customers) {
                    if (customer.getId() == rs.getInt(1))
                        customer.getCoupons().add(new Coupon(rs.getInt(2), rs.getInt(3),
                                (Category.values()[rs.getInt(4) - 1]), rs.getString(5), rs.getString(6), rs.getDate(7),
                                rs.getDate(8), rs.getInt(9), rs.getDouble(10),
                                rs.getString(11)));
                }
            }
        } finally {
            pool.restoreConnection(conn);
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
    public Customer getOneCustomer(int customerId) throws SQLException, CustomerExistsException {
        Connection con = pool.getConnection();
        Customer customer;
        try {
            PreparedStatement gettingCustomer = con.prepareStatement("SELECT * FROM coupons.customers where id=" + customerId);
            ResultSet rs = gettingCustomer.executeQuery();
            if (rs.next()) {
//               return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            } else
                throw new CustomerExistsException("The customer does not exist");

            this.settingCustomerCoupon(customer);
            return customer;


        } finally {
            pool.restoreConnection(con);
        }
    }

    private void settingCustomerCoupon(Customer customer) throws SQLException {
        Connection conn = pool.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT coupons.coupons_vs_customers.customer_id, coupons.coupons.id, coupons.coupons.company_id, coupons.coupons.category_id, coupons.coupons.title, coupons.coupons.description, coupons.coupons.start_date, coupons.coupons.end_date, coupons.coupons.amount, coupons.coupons.price, coupons.coupons.image FROM coupons.coupons, coupons.coupons_vs_customers, coupons.customers WHERE coupons.coupons_vs_customers.customer_id = coupons.customers.id and coupons.coupons_vs_customers.coupon_id = coupons.coupons.id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (customer.getId() == rs.getInt(1))
                    customer.getCoupons().add(new Coupon(rs.getInt(2), rs.getInt(3),
                            (Category.values()[rs.getInt(4) - 1]), rs.getString(5), rs.getString(6), rs.getDate(7),
                            rs.getDate(8), rs.getInt(9), rs.getDouble(10),
                            rs.getString(11)));
            }
        } finally {
            pool.restoreConnection(conn);
        }
    }
}
