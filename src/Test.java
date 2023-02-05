import Exceptions.LoginErrorException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import bl.Facades.CompanyFacade;
import bl.Facades.login.LoginManager;
import enums.Category;
import enums.ClientType;

import java.sql.Date;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        Company company= new Company("workout with us","Sport@sport","9999");

        Coupon coupon = new Coupon(8, Category.SPORT, "Private workouts", "workouts",
                Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                30, 30, " ");
        try {

            //logging in
            CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("Sport@sport",
                    "9999", ClientType.Company);

            //creating the company's coupons
            Coupon coupon1 = new Coupon(8, Category.SPORT, "Private workouts pro", "workouts for pro's",
                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                    30, 30, " ");

            Coupon coupon2 = new Coupon((8), Category.TOURISM, "Private workouts while touring", "workouts for travelers",
                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                    30, 30, " ");

            Coupon coupon3 = new Coupon((8), Category.TOURISM, "Private workouts while touring for pro's", "workouts for travelers",
                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                    30, 70, " ");

            //adding a coupons to the db
//            companyFacade.addCoupons(coupon1);

            //receiving coupon from the db
//            Coupon coupon4=companyFacade.getOneCompanyCouponById();

            //updating coupon
//            coupon4.setDescription("High level training");
//            companyFacade.updateCoupons(coupon4);

            //delete all company's coupons
//            companyFacade.deleteAllCompanyCoupons(8);

            //delete singular coupon
//            companyFacade.deleteSingularCoupon(coupon4);

            //get all company's coupons
//            companyFacade.getCompanyCoupons();

            //get company coupon based on category
//            companyFacade.getCompanyCouponsByCategory(JavaBeans.Category.TOURISM);

            //get company's coupon up to price
//            companyFacade.getCompanyCouponsUpToPrice(80);

            //get the company's details
//            companyFacade.getCompanyDetails();

        } catch (LoginErrorException e) {
            System.out.println(e.getMessage());
        }


    }

}






