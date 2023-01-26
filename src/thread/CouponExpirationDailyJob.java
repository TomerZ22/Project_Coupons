package thread;

import JavaBeans.Coupon;
import dao.CouponsDaoImp;
import dao.daoInterfaces.CouponsDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDao couponsDao;

    private boolean quit;

    public CouponExpirationDailyJob() {
        couponsDao = new CouponsDaoImp();
    }

    @Override
    public void run() {
        while (!quit) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            try {
                ArrayList<Coupon> coupons = (ArrayList<Coupon>) couponsDao.getAllCoupons();
                for (Coupon coupon : coupons) {
                    if (date == coupon.getEndDate()) {
                        couponsDao.deleteCouponPurchaseHistory(coupon.getId());
                        couponsDao.deleteCoupon(coupon.getId());
                    }
                }
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException ignored) {
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stop() {
        quit = true;
    }
}
