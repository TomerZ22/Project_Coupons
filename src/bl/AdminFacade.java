package bl;
import Exception.CustomerExistsException;
import JavaBeans.Company;
import java.sql.SQLException;
import java.util.List;
import Exception.CompanyExistsException;
import JavaBeans.Customer;

public class AdminFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password){
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public void addCompany(Company company) throws CompanyExistsException, SQLException {
        //Check if company.name and company.email do not exist!
        if(!companyDao.isCompanyExistByName_Email(company.getName(), company.getEmail()))
            companyDao.addCompany(company);

    }

    public void updateCompany(Company company) throws SQLException{

    }

    public void deleteCompany(Company company) throws SQLException{
        companyDao.deleteCompanyCoupons(company.getId());
        companyDao.deleteCustomerBuyHistory(company.getId());
        companyDao.deleteCompany(company.getId());
    }

    public List<Company> companies() throws SQLException {
        return companyDao.getAllCompanies();
    }

    public Company getCompanyById(int id) throws SQLException {
        return companyDao.getOneCompany(id);
    }

    public void addNewCustomer(Customer customer) throws CustomerExistsException, SQLException {
        if(!customerDao.isCustomerEmailExists(customer))
            customerDao.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException{

    }

    public void deleteCustomer(int customerID) throws SQLException {
        customerDao.deleteCustomersCoupons(customerID);
        customerDao.deleteCustomer(customerID);
    }

    public List<Customer> getAllCustomers() throws SQLException{
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerByID(int id) throws SQLException {
        return customerDao.getOneCustomer(id);
    }


}
