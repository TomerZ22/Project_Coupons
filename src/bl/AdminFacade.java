package bl;
import JavaBeans.Company;
import java.sql.SQLException;
import java.util.List;
import Exception.CompanyExistsException;

public class AdminFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws SQLException {
        return companyDao.isCompanyExists(email, password);
    }

    public void addCompany(Company company) throws SQLException {
        //Check if company.name and company.email do not exist!

    }



}
