package Exceptions;

public class CouponExpires extends Exception {
    public CouponExpires(String this_coupon_has_expires) {
        super("This Coupon Has Passed it EndDay, And Expires");
    }
}
