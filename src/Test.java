import JavaBeans.Company;
import JavaBeans.Customer;
import dao.CompaniesDaoImp;
import dao.CustomersDaoImp;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException{

        Company company1 = new Company("Microsoft","Microsoft@outlook.com","1234");
        Customer customer1 = new Customer("Zuckerberg ", "Thief", "zuki@meta.com","Lolipop123");
        CompaniesDaoImp com = new CompaniesDaoImp();
        CustomersDaoImp customersDaoImp = new CustomersDaoImp();



//        LoginManager loginManager = LoginManager.getInstance();
//        AdminFacade admin = (AdminFacade) loginManager.Login("admin@admin.com", "admin", ClientType.Administrator);
//        ArrayList<Company> companies = (ArrayList<Company>) admin.getAllCompanies();
//        System.out.println(companies);

//


    }

}