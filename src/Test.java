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
import thread.CouponExpirationDailyJob;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        try {
        CouponExpirationDailyJob job = new CouponExpirationDailyJob();
        job.start();

            testAdminFacade();
            testCompanyFacade();
            testCustomerFacade();

            job.stoppy();

        } catch (SQLException | LoginErrorException | CouponDoesntExistException | CustomerExistsException |
                 CouponPriceDoesntExist | CouponAlreadyBoughtException | CompanyExistsException e) {
            System.out.println(e.getMessage());
        }

        //Latest checking
//        try{
//            CompaniesDaoImp companiesDaoImp = new CompaniesDaoImp();
//            Company company = companiesDaoImp.getOneCompany(1);
//            System.out.println(company);
//            System.out.println("--------------------------------");
//            ArrayList<Company> companies = (ArrayList<Company>) companiesDaoImp.getAllCompanies();
//            for (Company c : companies) {
//                System.out.println(c);
//            }
//            System.out.println("--------------------------------");
//            testAdminFacade();
//        } catch (SQLException | CustomerExistsException | LoginErrorException | CompanyExistsException e) {
//            System.out.println(e.getMessage());
//        }
    }


    public static void testAdminFacade() throws SQLException, CompanyExistsException, LoginErrorException, CustomerExistsException {
        //Logging in


        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.Administrator);


        //Companies Methods:
        Company c1 = new Company("Elon Musk", "spaceXYZ@TSLA.com", "asdacvnh21  213-sdfg!@#as");
        Company c2 = new Company("Amdocs", "amD0cs@gmail.co.il", "123!@#456$$%$^");
//        adminFacade.addCompany(c2);
//        adminFacade.addCompany(c1);


        c1.setEmail("topGelonX@TSLA.com");
        c1.setPassword("IDK123!");

        adminFacade.updateCompany(c1); //Update

        adminFacade.deleteCompany(c1); //Delete

        System.out.println("All Companies");
        //List of companies
        ArrayList<Company> companies = (ArrayList<Company>) adminFacade.getAllCompanies();
        for (Company c : companies) {
            System.out.println(c);
        }
        System.out.println("One company");
        //One company from the DB
        Company company = adminFacade.getCompanyById(1);
        System.out.println(company);
        //----------------------------------------------------------------//

        //Customers Methods:
        Customer customer = new Customer("Jeff", "Bezos", "I'mrichhahaha@amazon.org.com", "pimpxD");
        try {
            adminFacade.addNewCustomer(customer);
        } catch (CustomerExistsException | SQLException e) {
            System.out.println(e.getMessage());
        }

        customer.setLastName("Bezossss");
        customer.setFirstName("My name is Jeff");
        customer.setPassword("JeffmyNameis");
        adminFacade.updateCustomer(customer); //Update
//
        adminFacade.deleteCustomer(1);//Delete

        System.out.println("All customers");
        ArrayList<Customer> customers = (ArrayList<Customer>) adminFacade.getAllCustomers();//All customers
        for (Customer c : customers) {
            System.out.println(c);
        }
        System.out.println("One customer");
        Customer customer2 = adminFacade.getCustomerByID(1);//Get customer
        System.out.println(customer2);

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
        } catch (SQLException | CouponTitleExistsException | CouponDoesntExistException e) {
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

    public static void testCustomerFacade() throws CustomerExistsException, SQLException, LoginErrorException, CouponDoesntExistException, CouponPriceDoesntExist, CouponAlreadyBoughtException {
        CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("abc", "123", ClientType.Customer);
        System.out.println(customerFacade.getCustomerDetails());

        Coupon coupon = new Coupon(2, 2, Category.SPORT, "asd", "asd",
                Date.valueOf("2023-01-01"), Date.valueOf("2023-12-12"), 500, 500, "");
        Coupon coupon2 = new Coupon(4, 2, Category.SPORT, "abc", "def",
                Date.valueOf("2023-01-01"), Date.valueOf("2023-12-12"), 12, 12, "");
        Coupon coupon3 = new Coupon(5, 1, Category.SPA, "asd", "asd",
                Date.valueOf("2023-01-01"), Date.valueOf("2023-12-12"), 1000, 1231, "");
        customerFacade.purchaseCoupon(coupon);
        customerFacade.purchaseCoupon(coupon2);
        customerFacade.purchaseCoupon(coupon3);

        ArrayList<Coupon> coupons = (ArrayList<Coupon>) customerFacade.getCustomerCouponsByCategory(Category.SPORT);
        System.out.println(coupons);
        System.out.println("--------------------------------");
        ArrayList<Coupon> customerCoupons = (ArrayList<Coupon>) customerFacade.getCustomersCoupons();
        System.out.println(customerCoupons);
        System.out.println("--------------------------------");
        ArrayList<Coupon> orderByPriceCoupons = (ArrayList<Coupon>) customerFacade.getCustomerCouponsUpToPrice(12.0);
        System.out.println(orderByPriceCoupons);
        System.out.println("--------------------------------");
        ArrayList<Coupon> allCoupons = (ArrayList<Coupon>) customerFacade.getAllCoupons();
        System.out.println(allCoupons);
    }

}





