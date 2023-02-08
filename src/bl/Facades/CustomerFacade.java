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

    private int customerID;

    public CustomerFacade() throws SQLException{};
    
//saving the list in order to use numeros times
    List<Coupon> allCoupons= couponDao.getAllCoupons();
    
    @Override
    public boolean login(String email, String password) throws SQLException {
        customerID = customersDao.isCustomerExist(email, password);
        return customerID != -1;
    }

    public List<Coupon> getAllCoupons() throws SQLException {
       return allCoupons;
    }

    public void purchaseCoupon(Coupon coupon) throws SQLException {
        couponDao.addCouponPurchase(customerID, coupon.getId());
        int count = coupon.getAmount();
        if (allCoupons.contains(coupon) && coupon.getPrice() == 0) {
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

    public void DeleteCustomerCoupons() throws SQLException {
        for (int i = 0; i < allCoupons.size(); i++) {
            if (!allCoupons.contains(null)) {
                customersDao.deleteCustomersCoupons(customerID);
            } else
                System.out.println("Nope");
        }
        System.out.println("Successfully Deleted All of Your Coupons");
    }

    public List<Coupon> getCustomerCouponsByCategory(Category category) throws SQLException {
        List<Coupon> allCustomerCoupons = allCoupons;
        for (Coupon allCustomerCoupon : allCustomerCoupons) {
            if (category == allCustomerCoupon.getCategory()) {
                customersDao.deleteCustomersCoupons(customerID);
                System.out.println("Successfully Deleted All of Your Coupons By Category");
            } else
                System.out.println("Nope");

        }
        return allCustomerCoupons;
    }


    public List<Coupon> getCustomerCouponsByPrice(double maxPrice) throws SQLException {
        List<Coupon> allCustomerCoupons= allCoupons;
        for (Coupon allCustomerCoupon : allCoupons) {
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
