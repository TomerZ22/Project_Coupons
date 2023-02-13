package Exceptions;

public class CouponDoesntExistException extends Exception{
    public CouponDoesntExistException() {
        super("There is no coupon with this id located at your data base");
    }
}
