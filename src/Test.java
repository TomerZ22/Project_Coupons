import Exceptions.*;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import bl.Facades.AdminFacade;
import bl.Facades.CompanyFacade;
import bl.Facades.CustomerFacade;
import bl.Facades.login.LoginManager;
import enums.Category;
import enums.ClientType;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        try {

//            //Sergey
//            testAdminFacade();
//            //Amir
//            testCompanyFacade();


            CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("Orel@Orel", "1910", ClientType.Customer);

           Coupon coupon= new Coupon(1,Category.SPORT,"bla sport","only bla people",
                   Date.valueOf("2023-01-01"),Date.valueOf("2023-05-01"),35,35," ");

Coupon coupon1=customerFacade.getAllCoupons().get(13);

//            customerFacade.purchaseCoupon(coupon1);
//            System.out.println( customerFacade.getAllCoupons());
//            System.out.println(customerFacade.getCustomerDetails());
//            System.out.println(customerFacade.getCustomersCoupons());


//            System.out.println(customerFacade.getCustomersCoupons());


//            System.out.println(customerFacade.getCustomerCouponsByCategory(JavaBeans.Category.SPORT));
//            System.out.println(customerFacade.getCustomerCouponsUpToPrice(40));
//            System.out.println(customerFacade);
//            System.out.println(customerFacade.getCustomersCoupons());
//            System.out.println(customerFacade.getCustomerDetails());
//
//
//        } catch (LoginErrorException | SQLException | CustomerExistsException | CompanyExistsException |
//                 CouponTitleExistsException | CouponDoesntExistException | EmptyCartException | CouponPriceDoesntExist |
//                 NoCouponsToDeleteException e) {
//            System.out.println(e.getMessage());
//        }

        } catch (SQLException | LoginErrorException e){
            System.out.println(e.getMessage());
        }
    }


    public static void testAdminFacade() {
        //Logging in

        AdminFacade adminFacade = null;
        try {
            adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.Administrator);
        } catch (LoginErrorException | SQLException e) {
            System.out.println(e.getMessage());
        }

        //Companies Methods:
        Company c1 = new Company("Elon Musk", "spaceXYZ@TSLA.com", "asdacvnh21  213-sdfg!@#as");
        Company c2 = new Company("Amdocs", "amD0cs@gmail.co.il", "123!@#456$$%$^");
//        adminFacade.addCompany(c2);
//        adminFacade.addCompany(c1);


        c1.setEmail("topGelonX@TSLA.com");
        c1.setPassword("IDK123!");
        try {
            adminFacade.updateCompany(c1); //Update
        } catch (SQLException | CompanyExistsException e) {
            System.out.println(e.getMessage());
        }

        try {
            adminFacade.deleteCompany(c1); //Delete
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        ArrayList<Company> companies = null; //List of companies
        try {
            companies = (ArrayList<Company>) adminFacade.getAllCompanies();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (Company c : companies) {
            System.out.println(c);
        }

        Company company = null; //One company from the DB
        try {
            company = adminFacade.getCompanyById(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(company);
        //----------------------------------------------------------------//

        //Customers Methods:
        Customer customer = new Customer("Jeff", "Bezos", "I'mrichhahaha@amazon.org.com", "pimpxD");
        try {
            adminFacade.addNewCustomer(customer);
        } catch (CustomerExistsException | SQLException e) {
            System.out.println(e.getMessage());
        }

//        customer.setLastName("Bezossss");
//        customer.setFirstName("My name is Jeff");
//        customer.setPassword("JeffmyNameis");
//        adminFacade.updateCustomer(customer); //Update
//
//        adminFacade.deleteCustomer(1);//Delete
//
//        ArrayList<Customer> customers = (ArrayList<Customer>) adminFacade.getAllCustomers();//All customers
//
//        Customer customer2 = adminFacade.getCustomerByID(1);//Get customer

    }

    public static void testCompanyFacade() {
        //Logging in
        CompanyFacade companyFacade = null;
        try {
            companyFacade = (CompanyFacade) LoginManager.getInstance().login("Sport@sport",
                    "9999", ClientType.Company);
        } catch (LoginErrorException | SQLException e) {
            System.out.println(e.getMessage());
        }

        //creating the company's coupons
        Coupon coupon1 = new Coupon(7, Category.SPORT, "Gym", "workouts",
                Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                30, 30, " ");
        Coupon coupon2 = new Coupon(8, Category.SPORT, "Private workouts pro", "workouts for pro's",
                Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                30, 30, " ");

        Coupon coupon3 = new Coupon((8), Category.TOURISM, "Private workouts while touring", "workouts for travelers",
                Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                30, 30, " ");

        Coupon coupon4 = new Coupon((8), Category.TOURISM, "Private workouts while touring for pro's", "workouts for travelers",
                Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                30, 70, " ");

        //adding a coupons to the db
        try {
            companyFacade.addCoupons(coupon1);
        } catch (SQLException | CouponTitleExistsException e) {
            System.out.println(e.getMessage());
        }

        //receiving coupon from the db
//            Coupon coupon5 = companyFacade.getOneCompanyCouponById();
        //updating coupon
        coupon4.setDescription("High level training");
        try {
            companyFacade.updateCoupons(coupon4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //delete all company's coupons
        try {
            companyFacade.deleteAllCompanyCoupons(8);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //delete singular coupon
        try {
            companyFacade.deleteSingularCoupon(coupon4);
        } catch (SQLException | NoCouponsToDeleteException e) {
            System.out.println(e.getMessage());
        }

        //get all company's coupons
//            companyFacade.getCompanyCoupons();

        //get company coupon based on category
//            companyFacade.getCompanyCouponsByCategory(JavaBeans.Category.TOURISM);

        //get company's coupon up to price
//            companyFacade.getCompanyCouponsUpToPrice(80);

        //get the company's details
//            companyFacade.getCompanyDetails();
    }

}





