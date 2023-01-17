package dao;

import JavaBeans.Coupon;

import java.util.ArrayList;

public interface CouponsDao {
    void addCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(int couponId);
    ArrayList<Coupon> getAllCoupons();
    Coupon getOneCoupon(int couponId);
    void addCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchase(int customerID, int couponID);
}
