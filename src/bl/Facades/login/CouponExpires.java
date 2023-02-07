package bl.Facades.login;

public class CouponExpires extends Throwable
{
    public CouponExpires(String this_coupon_has_expires) {
        super("This Coupon Has Passed it EndDay, And Expires");
    }
}
