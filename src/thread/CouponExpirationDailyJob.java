package thread;

import Exceptions.CouponDoesntExistException;
import JavaBeans.Coupon;
import dao.CouponsDaoImp;
import dao.daoInterfaces.CouponsDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDao couponsDao;
    private boolean quit;

    private Thread thread = new Thread(this, "dailyJob");

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
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException | SQLException | CouponDoesntExistException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void start(){
        this.thread.start();
        quit=false;
    }

    public void stoppy() {
        this.thread.interrupt();
        quit = true;

    }
}
