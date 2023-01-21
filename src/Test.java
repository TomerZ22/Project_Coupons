import JavaBeans.Company;
import JavaBeans.Customer;
import bl.Facades.CompanyFacade;
import dao.CompaniesDaoImp;
import dao.CustomersDaoImp;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException{

        Company company1 = new Company("Microsoft","Microsoft@outlook.com","1234");
        Customer customer1 = new Customer("Zuckerberg ", "Thief", "zuki@meta.com","Lolipop123");
        CompaniesDaoImp com = new CompaniesDaoImp();
        CustomersDaoImp customersDaoImp = new CustomersDaoImp();

//        customersDaoImp.addCustomer(customer1);
        customer1.setFirstName("Mark");
        customer1.setLastName("Zuckerberg");
        System.out.println(customer1.getId());
//        customersDaoImp.updateCustomers(customer1);



    }

}