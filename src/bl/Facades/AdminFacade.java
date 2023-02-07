package bl.Facades;

import Exceptions.CompanyExistsException;
import Exceptions.CustomerExistsException;
import Exceptions.CompanyExistsException;
import Exceptions.CustomerExistsException;
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

    /**
     * This method adds a company to the database after checking if the name and email doesn't already exist
     *
     * @param company - The company to be added to the database.
     * @throws CompanyExistsException - If the company already exists throw an exception
     * @throws SQLException           - If an error occurs during the connection
     */
    public void addCompany(Company company) throws SQLException, CompanyExistsException {
        //Check if company.name and company.email do not exist!
        if (companyDao.getAllCompanies() == null || !companyDao.isCompanyExistByName_Email(company.getName(), company.getEmail())) {
            companyDao.addCompany(company);
        }
    }

    /**
     * This method updates a company to the database after checking if the name didn't change.
     *
     * @param company - The company to be updated.
     * @throws SQLException           - If an error occurs during the connection.
     * @throws CompanyExistsException - If the company name changed throw an error.
     */
    public void updateCompany(Company company) throws SQLException, CompanyExistsException {
        //Cant update company ID
        //Cant update company name - Check with the id if the name is still the same at the database
        companyDao.updateCompanyAdminFacade(company);
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

    /**
     * This method returns a company by its identifier(ID).
     *
     * @param id - The identifier of the company to retrieve.
     * @return - The company if exist.
     * @throws SQLException - If an error occurs during the connection.
     */
    public Company getCompanyById(int id) throws SQLException {
        return companyDao.getOneCompany(id);
    }

    /**
     * This method adds a Costumer to the DB table.
     *
     * @param customer - The new customer to add to the DB table.
     * @throws CustomerExistsException - If a customer email is already exists.
     * @throws SQLException            - If an error occurs during the connection.
     */
    public void addNewCustomer(Customer customer) throws CustomerExistsException, SQLException {
        if (!customersDao.isCustomerEmailExists(customer))
            customersDao.addCustomer(customer);
    }

    /**
     * This method updates the customer, but you CANNOT update the customer ID!.
     *
     * @param customer - The customer to update.
     * @throws SQLException - If something goes wrong during the connection to the DB.
     */
    public void updateCustomer(Customer customer) throws SQLException {
        //Cant update customer ID
        customersDao.updateCustomers(customer);
    }

    /**
     * The method deletes the customer coupons and then customer itself from the database.
     *
     * @param customerID - Customer ID to be deleted.
     * @throws SQLException - If there was an error deleting the customer or connecting to the DB.
     */
    public void deleteCustomer(int customerID) throws SQLException {
        customersDao.deleteCustomersCoupons(customerID);
        customersDao.deleteCustomer(customerID);
    }

    /**
     * This method returns all the customers.
     *
     * @return - All the customers.
     * @throws SQLException - If something went wrong during the connection with the DB.
     */
    public List<Customer> getAllCustomers() throws SQLException {
        return customersDao.getAllCustomers();
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
        return customersDao.getOneCustomer(id);
    }

}
