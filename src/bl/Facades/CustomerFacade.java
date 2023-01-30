package bl.Facades;

import JavaBeans.Coupon;
import JavaBeans.Customer;
import dao.CustomersDaoImp;
import dao.daoInterfaces.CustomersDao;
import enums.Category;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws SQLException {
        customerID = customersDao.isCustomerExist(email,password);
        return customerID != -1;

    }

    private int customerID;
    public void purchaseCoupon(Coupon coupon) throws SQLException {
     List<Coupon> coupons =couponDao.getAllCoupons();
     couponDao.addCouponPurchase(customerID, coupon.getId());
     int count = coupon.getAmount();
        if (coupons.contains(coupon) && coupon.getPrice() == 0)
        {
            if (!Objects.equals(coupon.getEndDate(), Date.valueOf(LocalDate.now())))
            {
                couponDao.addCoupon(coupon);
                System.out.println("Nice Buy");
                count--;
            }
            else
            {
                System.out.println("Sorry The Coupon Isn't Available");
            }
            coupon.setAmount(count);
        }

    public ArrayList<Coupon> getCustomerCoupons(Coupon coupon)
    {

    }
    public ArrayList<Coupon> getCustomerCoupons(Category category)
    {

    }
    public ArrayList<Coupon> getCustomerCoupons(double maxPrice)
    {

    }
    public Customer getCustomerDetails()
    {
        
    }
}
