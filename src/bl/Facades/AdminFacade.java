package bl.Facades;

import Exception.CustomerExistsException;
import JavaBeans.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import Exception.CompanyExistsException;
import JavaBeans.Customer;
import bl.ClientFacade;
import db.ConnectionPool;

public class AdminFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public void addCompany(Company company) throws CompanyExistsException, SQLException {
        //Check if company.name and company.email do not exist!
        if (!companyDao.isCompanyExistByName_Email(company.getName(), company.getEmail()))
            companyDao.addCompany(company);

    }

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

    public void deleteCompany(Company company) throws SQLException {
        companyDao.deleteCompanyCoupons(company.getId());
        companyDao.deleteCustomerPurchaseHistory(company.getId());
        companyDao.deleteCompany(company.getId());
    }

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

    public void updateCustomer(Customer customer) throws SQLException {
        //Cant update customer ID
        customerDao.updateCustomers(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException {
        customerDao.deleteCustomersCoupons(customerID);
        customerDao.deleteCustomer(customerID);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerByID(int id) throws SQLException {
        return customerDao.getOneCustomer(id);
    }


}
