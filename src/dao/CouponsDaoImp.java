package dao;

import JavaBeans.Coupon;
import db.ConnectionPool;

import java.util.ArrayList;

public class CouponsDaoImp implements CouponsDao{
    private ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public void addCoupon(Coupon coupon) {

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int couponId) {

    }

    @Override
    public ArrayList<Coupon> getAllCoupons() {
        return null;
    }

    @Override
    public Coupon getOneCoupon(int couponId) {
        return null;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {

    }
}
