import JavaBeans.Coupon;
import dao.CouponsDaoImp;
import thread.CouponExpirationDailyJob;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws SQLException{

//        Company company1 = new Company("Microsoft","Microsoft@outlook.com","1234");
//        Customer customer1 = new Customer("Zuckerberg ", "Thief", "zuki@meta.com","Lolipop123");
//        CompaniesDaoImp com = new CompaniesDaoImp();
//        CustomersDaoImp customersDaoImp = new CustomersDaoImp();



//        LoginManager loginManager = LoginManager.getInstance();
//        AdminFacade admin = (AdminFacade) loginManager.Login("admin@admin.com", "admin", ClientType.Administrator);
//        ArrayList<Company> companies = (ArrayList<Company>) admin.getAllCompanies();
//        System.out.println(companies);

//
        Thread t = new Thread(new CouponExpirationDailyJob());
        t.start();

        CouponsDaoImp couponsDaoImp = new CouponsDaoImp();
        ArrayList<Coupon> coupons = (ArrayList<Coupon>) couponsDaoImp.getAllCoupons();
//        Coupon coupon = couponsDaoImp.getOneCoupon(1);
        long dateMillis = System.currentTimeMillis();
        Date date = new Date(dateMillis);
        System.out.println(coupons.get(0).getEndDate().before(date));
//        couponsDaoImp.deleteCouponPurchaseHistory(1);
//        couponsDaoImp.deleteCoupon(1);
    }

}