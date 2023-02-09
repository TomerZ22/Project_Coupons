import Exceptions.*;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import bl.Facades.CustomerFacade;
import bl.Facades.login.CouponExpires;
import bl.Facades.login.CouponNotInStock;
import bl.Facades.login.CouponPriceDosentExist;
import bl.Facades.login.LoginManager;
import enums.Category;
import enums.ClientType;

import java.sql.Date;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws CouponNotInStock, LoginErrorException, SQLException, CouponExpires, CustomerExistsException, CouponDoesntExistException, CouponPriceDosentExist, CouponTitleExistsException, NoCouponsToDeleteException {
        Customer customer = new Customer("Tomer", "Tzigel", "Zigletomer@yahoo.com", "1912");
        Company company = new Company("workout with us", "Sport@sport", "9999");
        Coupon coupon = new Coupon(8, Category.SPORT, "Private workouts", "workouts",
                Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                30, 30, " ");
        Coupon coupon1 = new Coupon(7, Category.SPORT, "Gym", "workouts",
                Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                30, 30, " ");
        CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("Zigletomer@yahoo.com", "1912", ClientType.Customer);
        System.out.println(coupon1);
        customerFacade.purchaseCoupon(coupon1);
        customerFacade.getCustomerCouponsByCategory(JavaBeans.Category.SPORT);
        customerFacade.getCustomerCouponsUpToPrice(30);
        System.out.println(customerFacade);
        System.out.println(customerFacade.getCustomerCoupons());
        System.out.println(customerFacade.getCustomerDetails());
        //logging in
//            CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("Sport@sport",
//                    "9999", ClientType.Company);
//
//            //creating the company's coupons
//            Coupon coupon2 = new Coupon(8, Category.SPORT, "Private workouts pro", "workouts for pro's",
//                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
//                    30, 30, " ");
//
//            Coupon coupon3 = new Coupon((8), Category.TOURISM, "Private workouts while touring", "workouts for travelers",
//                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
//                    30, 30, " ");
//
//            Coupon coupon4 = new Coupon((8), Category.TOURISM, "Private workouts while touring for pro's", "workouts for travelers",
//                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
//                    30, 70, " ");
//
//            //adding a coupons to the db
//            companyFacade.addCoupons(coupon1);
//
//            //receiving coupon from the db
////            Coupon coupon5 = companyFacade.getOneCompanyCouponById();
//            //updating coupon
//            coupon4.setDescription("High level training");
//            companyFacade.updateCoupons(coupon4);
//
//            //delete all company's coupons
//            companyFacade.deleteAllCompanyCoupons(8);
//            //delete singular coupon
//            companyFacade.deleteSingularCoupon(coupon4);
//
//            //get all company's coupons
////            companyFacade.getCompanyCoupons();
//
//            //get company coupon based on category
////            companyFacade.getCompanyCouponsByCategory(JavaBeans.Category.TOURISM);
//
//            //get company's coupon up to price
////            companyFacade.getCompanyCouponsUpToPrice(80);
//
//            //get the company's details
////            companyFacade.getCompanyDetails();
//
//            testAdminFacade();
//
//
//        } catch (LoginErrorException | SQLException | CustomerExistsException | CompanyExistsException e) {
//            System.out.println(e.getMessage());
//        }


    }
}


//    public static void testAdminFacade() throws LoginErrorException, SQLException, CompanyExistsException, CustomerExistsException {
//        //Logging in
//
//        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.Administrator);
//
//        //Companies Methods:
//        Company c1 = new Company("Amdocs", "amD0cs@gmail.co.il", "asdacvnh21  213-sdfg!@#as");
////        adminFacade.addCompany(c1);

//
//        c1.setEmail("topGelonX@TSLA.com");
//        c1.setPassword("IDK123!");
//        adminFacade.updateCompany(c1); //Update

//        adminFacade.deleteCompany(c1); //Delete

//        ArrayList<Company> companies = (ArrayList<Company>) adminFacade.getAllCompanies(); //List of companies
//        for (Company c : companies) {
//            System.out.println(c);
//        }
//
//        Company company = adminFacade.getCompanyById(1); //One company from the DB
//        System.out.println(company);
//        //----------------------------------------------------------------//
//
//        //Customers Methods:
//        Customer customer = new Customer("Jeff", "Bezos", "I'mrichhahaha@amazon.org.com", "pimpxD");
//
//        adminFacade.addNewCustomer(customer);
//////
////        customer.setLastName("Bezossss");
////        customer.setFirstName("My name is Jeff");
////        customer.setPassword("JeffmyNameis");
////        adminFacade.updateCustomer(customer); //Update
////
////        adminFacade.deleteCustomer(1);//Delete
////
////        ArrayList<Customer> customers = (ArrayList<Customer>) adminFacade.getAllCustomers();//All customers
////
////        Customer customer2 = adminFacade.getCustomerByID(1);//Get customer
//
//    }







