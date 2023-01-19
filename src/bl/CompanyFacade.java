package bl;

import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends ClientFacade
{
    public CompanyFacade(int customerID) {
        this.customerID = customerID;
    }

    private int customerID;
    @Override
    public boolean login(String email, String password) throws SQLException {
        return false;
    }

    public void purchaseCoupon(Coupon coupon) throws SQLException {

    }

    public List<Coupon> getCustomerCoupon(Coupon coupon)
    {

    }

    public List<Coupon> getCustomerCoupons (Category category) throws SQLException
    {

    }
    public List<Coupon> getCustomerCoupons (double maxPrice) throws SQLException
    {

    }
    public Customer getCustomerDetails() throws SQLException {
        return customerDao.getOneCustomer(customerID);
    }

}
