package bl.Facades;

import JavaBeans.Coupon;
import JavaBeans.Customer;
import enums.Category;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFacade extends ClientFacade {
    public CustomerFacade(int customerID) {
        this.customerID = customerID;
    }
    @Override
    public boolean login(String email, String password) throws SQLException {
//        if(customerDao.getOneCustomer("")==true)
        {

        }

    }
    private int customerID;
    public void purchaseCoupon(Coupon coupon)
    {

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
