package bl.Facades;

import JavaBeans.Coupon;
import JavaBeans.Customer;
import bl.Facades.login.CouponCategoryDosentExist;
import bl.Facades.login.CouponExpires;
import bl.Facades.login.CouponNotInStock;
import bl.Facades.login.CouponPriceDosentExist;
import enums.Category;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CustomerFacade extends ClientFacade {
    /**
     * This method allows access to the facade throw log in validation
     * @param email
     * @param password
     * @retur true if email and password are correct or false if it isn't found in the db
     * @throws SQLException
     */
    private int customerID;

    @Override
    public boolean login(String email, String password) throws SQLException {
        customerID = customersDao.isCustomerExist(email,password);
        return customerID != -1;
    }

    public void purchaseCoupon(Coupon coupon) throws SQLException, CouponExistsException, CouponNotInStock, CouponExpires {
        List<Coupon> coupons = couponDao.getAllCoupons();
        int count = coupon.getAmount();
        for (Coupon value : coupons)
        {
            if (coupon.getTitle().equals(value.getTitle()))
                throw new CouponExistsException("This Coupon Has Been Bought");
            if (coupon.getAmount() == 0) {
                throw new CouponNotInStock("There ain't  any Left");
            }
            if (Objects.equals(coupon.getEndDate(), Date.valueOf(LocalDate.now())))
            {
                throw new CouponExpires("This Coupon Expires");
            }
            else
                System.out.println("Nice Buy");
                couponDao.addCoupon(coupon);
            count--;
            count=coupon.getAmount();
        }

    }

    public List<Coupon> getCustomerCoupons() throws SQLException, CouponDoesntExistException {
        List<Coupon> allCustomerCoupons = couponDao.getAllCoupons();
        for (int i = 0; i < allCustomerCoupons.size(); i++) {
            if (allCustomerCoupons.contains(null)) {
                throw new CouponDoesntExistException("There Aren't any Coupon");
            }
                customersDao.deleteCustomersCoupons(customerID);
                System.out.println("Successfully Deleted All of Your Coupons");

        }
        return allCustomerCoupons;
    }

    public List<Coupon> getCustomerCoupons(Category category) throws SQLException, CouponCategoryDosentExist {
        List<Coupon> allCustomerCoupons = couponDao.getAllCoupons();
        for (Coupon allCustomerCoupon : allCustomerCoupons) {

            if (category.name().equals(allCustomerCoupon.getCategory().name())) {
                customersDao.deleteCustomersCoupons(customerID);
                System.out.println("Successfully Deleted All of Your Coupons By Category");
            } else
                throw  new CouponCategoryDosentExist("This Coupon Category isn't Available");
                System.out.println("Nope");

        }
        return allCustomerCoupons;
    }

    public List<Coupon> getCustomerCoupons(double maxPrice) throws SQLException, CouponPriceDosentExist {
        List<Coupon> allCustomerCoupons = getCustomerDetails().getCoupons();
        for (int i = 0; i<allCustomerCoupons.size();i++) {
            if (maxPrice>=allCustomerCoupons.get(i).getPrice()) {
                allCustomerCoupons.remove(allCustomerCoupons.get(i));
                System.out.println("Successfully Deleted All of Your Coupons By MaxPrice");
            }
                throw new CouponPriceDosentExist("The Coupon Price Doesn't Match The Price Your Are Seeking");
        }
        return allCustomerCoupons;
    }

    public Customer getCustomerDetails() throws SQLException {
        return customersDao.getOneCustomer(customerID);
    }
}
