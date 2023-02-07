package bl.Facades.login;

public class CouponPriceDosentExist extends Throwable
{

    public CouponPriceDosentExist(String this_coupon_Categroy_isnt_available)
    {
        super("The Coupon Price Isn't Match The Coupon You Are Seeking");
    }
}
