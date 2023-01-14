package dao;

import JavaBeans.Company;

import java.util.List;

public interface CompaniesDao {
    boolean isCompanyExists(String email, String password);
    void addCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(int companyId);
    Company getOneCompany(int companyId);
    List<Company> getAllCompanies();
}
