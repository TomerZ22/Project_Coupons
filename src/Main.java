import JavaBeans.Company;
import dao.CompaniesDao;
import dao.CompaniesDaoImp;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Company company1 = new Company("Microsoft","Microsoft@outlook.com","1234");
        CompaniesDaoImp com = new CompaniesDaoImp();
        try {
            com.addCompany(company1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
       }
        company1.setName("Amir");
        com.updateCompany(company1);
        com.isCompanyExists("Microsoft@outlook.com","1234");
        com.deleteCompany(1);

    }

}