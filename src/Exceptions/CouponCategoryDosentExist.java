package Exceptions;

public class CouponCategoryDosentExist extends Exception {
    public CouponCategoryDosentExist(String this_coupon_Categroy_isnt_available) {
        super("This Coupon Category Isn't Available");
    }
}
