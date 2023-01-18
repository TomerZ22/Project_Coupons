import JavaBeans.Company;
import JavaBeans.Customer;
import bl.AdminFacade;
import dao.CompaniesDaoImp;
import dao.CustomersDaoImp;

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

        Customer customer1 = new Customer("asdas", "asda", "asd","asd");
        customer1.setFirstName("John");
        customer1.setPassword("lol");
        customer1 = new Customer(1, "john", "aaa", "bbb","asd");
        System.out.println(customer1);
        CustomersDaoImp c = new CustomersDaoImp();


        company1.setName("Amir");
        com.updateCompany(company1);

    }

}