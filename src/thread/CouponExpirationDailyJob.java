package thread;

import dao.daoInterfaces.CouponsDao;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDao coupon;
    private boolean quit;

    public CouponExpirationDailyJob(CouponsDao coupon) {
        this.coupon = coupon;
    }

    @Override
    public void run() {

    }

    public void stop() {

    }
}
