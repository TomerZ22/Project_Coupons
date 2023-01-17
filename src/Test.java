import JavaBeans.Company;
import dao.CompaniesDaoImp;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {

        Company company1 = new Company("Microsoft","Microsoft@outlook.com","1234");
        CompaniesDaoImp com = new CompaniesDaoImp();
//        try {
//            com.addCompany(company1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        company1.setName("Amir");
        com.updateCompany(company1);

    }

}