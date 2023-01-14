import JavaBeans.Company;
import JavaBeans.Customer;
import dao.CompaniesDao;
import dao.CompaniesDaoImp;
import dao.CustomersDaoImp;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {

//        Company company1 = new Company("Microsoft","Microsoft@outlook.com","1234");
//        CompaniesDaoImp com = new CompaniesDaoImp();
//        try {
//            com.addCompany(company1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        company1.setName("Amir");
//        com.updateCompany(company1);

        CustomersDaoImp cus = new CustomersDaoImp();
        System.out.println(cus.isCustomerExist("aaa@gmail.com", "1234"));
        System.out.println("--------------------------------");
        Customer customer = new Customer("lalala", "lilili", "lali@gmail.com", "123321");
//        cus.addCustomer(customer);
        Customer customer1 = cus.getOneCustomer(2);
        System.out.println(customer1);
        ArrayList<Customer> customers = cus.getAllCustomers();
        System.out.println(customers);
    }

}