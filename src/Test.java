import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import bl.Facades.CompanyFacade;
import dao.CompaniesDaoImp;
import dao.CustomersDaoImp;
import enums.Category;

import java.sql.Date;
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
//        Coupon cop = new Coupon(1, Category.SPORT,"NBA","2K22", Date.valueOf("2023-01-01"),Date.valueOf("2022-01-01"),1,25,"");
//        customersDaoImp.updateCustomers(customer1);



    }

}