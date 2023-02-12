package Exceptions;

public class CouponNotInStock  extends Exception{
    public CouponNotInStock(String this_coupon_isnt_available)
    {
        super("There isn't stock Available From This Coupon");
    }
}