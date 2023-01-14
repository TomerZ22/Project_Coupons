package dao;

import JavaBeans.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompaniesDao {
    boolean isCompanyExists(String email, String password);
    void addCompany(Company company) throws SQLException;
    void updateCompany(Company company) throws SQLException;
    void deleteCompany(int companyId);
    Company getOneCompany(int companyId);
    List<Company> getAllCompanies();
}
