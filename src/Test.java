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
        Coupon coupon = new Coupon(8, Category.SPORT, "Private workouts", "workouts",
                Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                30, 30, " ");

        Company company = new Company("Sport for you", "Sport@sport", "9999");
        try {
            CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("Sport@sport",
                    "9999", ClientType.Company);

//            System.out.println(companyFacade.getCompanyCoupons());
            Coupon coupon1 = new Coupon(8, Category.SPORT, "Private workouts pro", "workouts for pro's",
                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                    30, 30, " ");

            Coupon coupon2 = new Coupon((8), Category.TOURISM, "Private workouts while touring", "workouts for travelers",
                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                    30, 30, " ");

            Coupon coupon3 = new Coupon((8), Category.TOURISM, "Private workouts while touring for pro's", "workouts for travelers",
                    Date.valueOf("2023-09-09"), Date.valueOf("2023-10-10"),
                    30, 70, " ");

//            companyFacade.addCoupons(coupon3);
//            System.out.println(companyFacade.getCompanyCouponsUpToPrice(80));

//            System.out.println(companyFacade.getCompanyCouponsByCategory(JavaBeans.Category.TOURISM));

//            System.out.println(companyFacade.getCompanyCoupons());
//            companyFacade.deleteAllCompanyCoupons(8);
//            Coupon coupon2=companyFacade.getOneCompanyCouponById(13);
//            coupon2.setDescription("High level training");
//            System.out.println(companyFacade.getCompanyDetails());

//            Coupon coupon4 = companyFacade.getOneCompanyCouponById(16);
//            companyFacade.deleteAllCompanyCoupons(6);
//                        System.out.println(coupon4);
//            companyFacade.updateCoupons(coupon2);
//            companyFacade.deleteSingularCoupon(coupon3);

        } catch (LoginErrorException e) {
            System.out.println(e.getMessage());
        }


    }

}






