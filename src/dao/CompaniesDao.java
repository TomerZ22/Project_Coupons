package dao;

import JavaBeans.Company;
import Exception.CompanyExistsException;
import java.sql.SQLException;
import java.util.List;

public interface CompaniesDao {

    void deleteCustomerBuyHistory(int id) throws SQLException; // This method is when we delete a company.
    void deleteCompanyCoupons(int id) throws SQLException; // This method is when we delete a company.
    boolean isCompanyExistByName_Email(String name, String email) throws CompanyExistsException; //This method is for the AdminFacade check.

    boolean isCompanyExists(String email, String password) throws SQLException;
    void addCompany(Company company) throws SQLException;
    void updateCompany(Company company) throws SQLException;
    void deleteCompany(int companyId) throws SQLException;
    Company getOneCompany(int companyId);
    List<Company> getAllCompanies();
}
