package bl.Facades.login;

public class CouponCategoryDosentExist extends Throwable
{

        public CouponCategoryDosentExist(String this_coupon_Categroy_isnt_available)
        {
            super("This Coupon Category Isn't Available");
        }
    }
