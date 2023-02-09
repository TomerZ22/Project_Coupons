package dao.daoInterfaces;

import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface CouponsDao {
    void addCoupon(Coupon coupon) throws SQLException;
    void updateCoupon(Coupon coupon) throws SQLException;
    void deleteCoupon(int couponId) throws SQLException;
    List<Coupon> getAllCoupons() throws SQLException;
    Coupon getOneCoupon(int couponId) throws SQLException;
    void addCouponPurchase(int customerID, int couponID) throws SQLException;
    void deleteCouponPurchase(int customerID, int couponID) throws SQLException;

    void deleteCouponPurchaseHistory(int id) throws SQLException;

    boolean didCouponAlreadyPurchased(int id, int customerID);
}
