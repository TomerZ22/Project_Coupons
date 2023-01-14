package dao;

import JavaBeans.Company;
import java.util.List;

public class CompaniesDaoImp implements CompaniesDao{
    @Override
    public boolean isCompanyExists(String email, String password) {
        return false;
    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(int companyId) {

    }

    @Override
    public Company getOneCompany(int companyId) {
        return null;
    }

    @Override
    public List<Company> getAllCompanies() {
        return null;
    }
}
