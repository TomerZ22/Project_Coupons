package thread;

import JavaBeans.Coupon;
import dao.CouponsDaoImp;
import dao.daoInterfaces.CouponsDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDao couponsDao;


    private boolean quit;

    public CouponExpirationDailyJob() {
        quit = false;
        couponsDao = new CouponsDaoImp();
    }

    @Override
    public void run() {
        while (!quit) {
            long dateMillis = System.currentTimeMillis();
            Date date = new Date(dateMillis);

            try {
                ArrayList<Coupon> coupons = (ArrayList<Coupon>) couponsDao.getAllCoupons();
                for (Coupon coupon : coupons) {
                    if (coupon.getEndDate().before(date)) {
                        couponsDao.deleteCouponPurchaseHistory(coupon.getId());
                        couponsDao.deleteCoupon(coupon.getId());
                    }
                }
                Thread.sleep(1000);
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
