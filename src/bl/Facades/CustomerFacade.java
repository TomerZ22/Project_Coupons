package bl.Facades;

import JavaBeans.Coupon;
import JavaBeans.Customer;
import enums.Category;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CustomerFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws SQLException {
        customerID = customersDao.isCustomerExist(email, password);
        return customerID != -1;

    }

    private int customerID;

    public void purchaseCoupon(Coupon coupon) throws SQLException {
        List<Coupon> coupons = couponDao.getAllCoupons();
        couponDao.addCouponPurchase(customerID, coupon.getId());
        int count = coupon.getAmount();
        if (coupons.contains(coupon) && coupon.getPrice() == 0) {
            if (!Objects.equals(coupon.getEndDate(), Date.valueOf(LocalDate.now()))) {
                couponDao.addCoupon(coupon);
                System.out.println("Nice Buy");
                count--;
            } else {
                System.out.println("Sorry The Coupon Isn't Available");
            }
            coupon.setAmount(count);
        }
    }

    public List<Coupon> getCustomerCoupons() throws SQLException {
        List<Coupon> allCustomerCoupons = couponDao.getAllCoupons();
        for (int i = 0; i < allCustomerCoupons.size(); i++) {
            if (!allCustomerCoupons.contains(null)) {
                customersDao.deleteCustomersCoupons(customerID);
                System.out.println("Successfully Deleted All of Your Coupons");
            } else
                System.out.println("Nope");

        }
        return allCustomerCoupons;
    }

    public List<Coupon> getCustomerCoupons(Category category) throws SQLException {
        List<Coupon> allCustomerCoupons = couponDao.getAllCoupons();
        for (Coupon allCustomerCoupon : allCustomerCoupons) {
            if (category == allCustomerCoupon.getCategory()) {
                customersDao.deleteCustomersCoupons(customerID);
                System.out.println("Successfully Deleted All of Your Coupons By Category");
            } else
                System.out.println("Nope");

        }
        return allCustomerCoupons;
    }


    public List<Coupon> getCustomerCoupons(double maxPrice) throws SQLException {
        List<Coupon> allCustomerCoupons = couponDao.getAllCoupons();
        for (Coupon allCustomerCoupon : allCustomerCoupons) {
            if (maxPrice == allCustomerCoupon.getPrice()) {
                customersDao.deleteCustomersCoupons(customerID);
                System.out.println("Successfully Deleted All of Your Coupons By MaxPrice");
            } else
                System.out.println("Nope");
        }
        return allCustomerCoupons;
    }


    public Customer getCustomerDetails() throws SQLException {
        return customersDao.getOneCustomer(customerID);
    }
}
