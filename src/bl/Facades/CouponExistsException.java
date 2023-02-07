package bl.Facades;

public class CouponExistsException extends Throwable {
    public CouponExistsException(String this_coupon_has_been_bought) {
        super("There is A Coupon With This Id Located At Your Data Base");
    }
}
