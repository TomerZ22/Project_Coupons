package bl.Facades.login;

public class CouponNotInStock  extends Throwable{
    public CouponNotInStock(String this_coupon_isnt_available)
    {
        super("There isn't stock Available From This Coupon");
    }
}