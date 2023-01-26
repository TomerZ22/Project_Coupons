package bl.Facades;

import Exception.CompanyExistsException;
import Exception.CustomerExistsException;
import JavaBeans.Company;
import JavaBeans.Customer;
import db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public void addCompany(Company company) throws CompanyExistsException, SQLException {
        //Check if company.name and company.email do not exist!
        if (companyDao.getAllCompanies() == null || !companyDao.isCompanyExistByName_Email(company.getName(), company.getEmail()))
            companyDao.addCompany(company);

    }

    //Fix this method the connection should be in the DAO!
    public void updateCompany(Company company) throws SQLException, CompanyExistsException {
        //Cant update company ID - we don't have the SETTER for id so not need to check.
        //Cant update company name - Check with the id if the name is still the same at the database
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM company WHERE id = " + company.getId());
        ResultSet rs = ps.executeQuery();
        if (!company.getName().equals(rs.getString(2))) {
            pool.restoreConnection(conn);
            throw new CompanyExistsException("You cannot update company name!");
        }
        ps.setString(3, company.getEmail());
        ps.setString(4, company.getPassword());
        ps.execute();

        pool.restoreConnection(conn);
    }

    /**
     * This method is called to delete a company, its coupons, and its customers
     * purchases history from the database.
     *
     * @param company - The company to delete.
     * @throws SQLException - If an error occurs during connection to the database.
     */
    public void deleteCompany(Company company) throws SQLException {
        companyDao.deleteCompanyCoupons(company.getId());
        companyDao.deleteCustomerPurchaseHistory(company.getId());
        companyDao.deleteCompany(company.getId());
    }

    /**
     * This method returns in a List, all the companies that are in the DB table.
     *
     * @return - An List of companies.
     * @throws SQLException - If an error occurs during the operation with the DB.
     */
    public List<Company> getAllCompanies() throws SQLException {
        return companyDao.getAllCompanies();
    }

    public Company getCompanyById(int id) throws SQLException {
        return companyDao.getOneCompany(id);
    }

    public void addNewCustomer(Customer customer) throws CustomerExistsException, SQLException {
        if (!customerDao.isCustomerEmailExists(customer))
            customerDao.addCustomer(customer);
    }

    /**
     * This method updates the customer, but you CANNOT update the customer ID!.
     *
     * @param customer - The customer to update.
     * @throws SQLException - If something goes wrong during the connection to the DB.
     */
    public void updateCustomer(Customer customer) throws SQLException {
        //Cant update customer ID
        customerDao.updateCustomers(customer);
    }

    /**
     * The method deletes the customer coupons and then customer itself from the database.
     *
     * @param customerID - Customer ID to be deleted.
     * @throws SQLException - If there was an error deleting the customer or connecting to the DB.
     */
    public void deleteCustomer(int customerID) throws SQLException {
        customerDao.deleteCustomersCoupons(customerID);
        customerDao.deleteCustomer(customerID);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDao.getAllCustomers();
    }

    /**
     * The method returns a Customer from the database by its ID.
     *
     * @param id - the ID of the Customer.
     * @return - the Customer.
     * @throws SQLException - if an error occurs while retrieving the Customer or
     *                      connecting to the database is encountered.
     */
    public Customer getCustomerByID(int id) throws SQLException {
        return customerDao.getOneCustomer(id);
    }

}
