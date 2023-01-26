package thread;

import dao.daoInterfaces.CouponsDao;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDao coupon;
    private boolean quit = false;

    public CouponExpirationDailyJob(CouponsDao coupon) {
        this.coupon = coupon;
    }

    @Override
    public void run() {
        while (!quit) {
            try {




                Thread.sleep(1000*60*60*24);
            }catch (InterruptedException ignored){}
        }
    }

    public void stop() {
        quit = true;
    }
}
