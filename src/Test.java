import JavaBeans.Company;
import bl.Facades.AdminFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws SQLException{

//        Company company1 = new Company("Microsoft","Microsoft@outlook.com","1234");
//        Customer customer1 = new Customer("Zuckerberg ", "Thief", "zuki@meta.com","Lolipop123");
//        CompaniesDaoImp com = new CompaniesDaoImp();
//        CustomersDaoImp customersDaoImp = new CustomersDaoImp();

//        customersDaoImp.addCustomer(customer1);
//        customer1.setFirstName("Mark");
//        customer1.setLastName("Zuckerberg");
//        System.out.println(customer1.getId());
//        customersDaoImp.updateCustomers(customer1);

        AdminFacade admin = new AdminFacade();
//        Company amazon = new Company("Amazon", "mynameisJeff@jeff.com", "1234");
//        try {
//            admin.addCompany(amazon);
//        } catch (CompanyExistsException | SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        admin.deleteCompany(amazon);
        ArrayList<Company> companies = (ArrayList<Company>) admin.getAllCompanies();
        for(Company company : companies){
            admin.deleteCompany(company);
        }
//        System.out.println(companies == null ? "Empty" : companies);


    }

}