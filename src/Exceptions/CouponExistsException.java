package Exceptions;

public class CouponAlreadyBoughtException extends Exception {
    public CouponAlreadyBoughtException() {
        super("Sorry, you cant buy coupon more than once");
    }
}
