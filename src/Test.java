import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import bl.AdminFacade;
import bl.CompanyFacade;
import dao.CompaniesDaoImp;
import dao.CustomersDaoImp;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException{

        Company company1 = new Company("Microsoft","Microsoft@outlook.com","1234");
        CompaniesDaoImp com = new CompaniesDaoImp();
//        try {
//            com.addCompany(company1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        Customer customer1 = new Customer("asdas", "asda", "asd","asd");
//        CustomersDaoImp customersDaoImp = new CustomersDaoImp();
//        customersDaoImp.addCustomer(customer1);
//        System.out.println(customer1.getId());
        CompanyFacade com1 = new CompanyFacade(6);

//        customer1.setFirstName("John");
//        customer1.setPassword("lol");
//        customer1 = new Customer(1, "john", "aaa", "bbb","asd");
//        System.out.println(customer1);
//        CustomersDaoImp c = new CustomersDaoImp();
//
//
//        company1.setName("Amir");
//        com.isCompanyExists("Microsoft@outlook.com","1234");
////        com.updateCompany(company1);
//        com.getOneCompany(1);
//        List<Company> companies = com.getAllCompanies();
//        System.out.println(companies);
//        com.getOneCompany(1);
    }

}