package bl.Facades;

import Exceptions.CouponAlreadyBoughtException;
import Exceptions.CouponDoesntExistException;
import Exceptions.CouponPriceDoesntExist;
import Exceptions.CustomerExistsException;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import enums.Category;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerFacade extends ClientFacade {

    //while logging in customer id will be recognized with the specific customer
    private int customerID = -1;

    /**
     * This method allows access to the customer facade throw log in validation
     *
     * @param email
     * @param password
     * @throws SQLException
     * @retur true if email and password are correct or false if it isn't found in the db
     */
    @Override
    public boolean login(String email, String password) throws SQLException {
        customerID = customersDao.isCustomerExist(email, password);
        if (customerID != -1) {
            System.out.println("Logged in");
            return true;
        } else
            System.out.println("Customer doesn't exist");
        return false;

    }

    /**
     * This method allows a customer to view all the coupons
     *
     * @return List od all coupons
     * @throws SQLException
     */
    public List<Coupon> getAllCoupons() throws SQLException, CouponDoesntExistException {

        return couponDao.getAllCoupons() != null ? couponDao.getAllCoupons() : new ArrayList<>();
    }

    /**
     * This method allow acustomer to purchase a coupon and adding it to the db
     *
     * @param coupon we wish to purchase
     * @throws SQLException
     * @throws CouponDoesntExistException   in case that the given coupon doesn't for sale ( doesn't exist in the db )
     * @throws CouponAlreadyBoughtException appears in case this specific customer already bought this specific coupon
     */
    public void purchaseCoupon(Coupon coupon) throws SQLException, CouponDoesntExistException, CouponAlreadyBoughtException {
        List<Coupon> customerCoupons = getCustomersCoupons();
        if (customerCoupons.size() == 0) {
            couponDao.addCouponPurchase(customerID, coupon.getId());
            return;
        }
        for (Coupon customerCoupon : customerCoupons) {
            if (coupon.getTitle().equals(customerCoupon.getTitle()) || coupon.getAmount() <= 0 ||
                    Objects.equals(coupon.getEndDate(), Date.valueOf(LocalDate.now()))) {
                throw new CouponAlreadyBoughtException();
            }
            if (couponDao.didCouponAlreadyPurchased(coupon.getId(), customerID)) {
                throw new CouponAlreadyBoughtException();
            }
        }
        couponDao.addCouponPurchase(customerID, coupon.getId());
        System.out.println("Nice Buy");
    }

    /**
     * This method returns all coupons that bought by this customer
     *
     * @return list of coupons bought by this customer
     * @throws SQLException
     */
    public List<Coupon> getCustomersCoupons() throws SQLException, CouponDoesntExistException {
        List<Coupon> allCoupons = couponDao.getAllCoupons();
        List<Coupon> customerCoupons = new ArrayList<>();
        if (allCoupons.size() > 0) {
            for (Coupon newCoupon : allCoupons) {
                if (couponDao.didCouponAlreadyPurchased(newCoupon.getId(), customerID)) {
                    customerCoupons.add(newCoupon);
                }
            }
        } else
            throw new CouponDoesntExistException();
        return customerCoupons;
    }


    /**
     * This method returns all the customer coupons related to the given category
     *
     * @param category the category of the coupon
     * @return list of coupons filtered by the given category
     * @throws SQLException
     * @throws CouponDoesntExistException in case this customer didn't purchase coupons from the given category
     */
    public List<Coupon> getCustomerCouponsByCategory(Category category) throws SQLException, CouponDoesntExistException {
        List<Coupon> customerCoupons = getCustomersCoupons();
        List<Coupon> allCustomersCouponsByCategory = new ArrayList<>();
        if (customerCoupons.size() > 0) {
            for (Coupon customerCoupon : customerCoupons) {
                if (customerCoupon.getCategory().name().equals(category.name())) {
                    allCustomersCouponsByCategory.add(customerCoupon);
                }
            }
        } else {
            throw new CouponDoesntExistException();
        }
        return customerCoupons;
    }

    /**
     * This method returns coupons filtered by maximum price
     *
     * @param maxPrice the max price of the coupons
     * @return list of coupon filtered by maximum price
     * @throws SQLException
     * @throws CouponPriceDoesntExist ic case the maximum price is lower than the cheapest coupon
     */
    public List<Coupon> getCustomerCouponsUpToPrice(double maxPrice) throws SQLException, CouponPriceDoesntExist, CouponDoesntExistException {
        List<Coupon> CustomerCoupons = getCustomersCoupons();
        List<Coupon> allCustomersCouponsByPrice = new ArrayList<>();
        for (Coupon customerCoupon : CustomerCoupons) {
            if (maxPrice + 1 > customerCoupon.getPrice()) {
                allCustomersCouponsByPrice.add(customerCoupon);
            }
        }
        if (allCustomersCouponsByPrice.size() == 0) {
            throw new CouponPriceDoesntExist();
        }
        return allCustomersCouponsByPrice;
    }

    public Customer getCustomerDetails() throws SQLException, CustomerExistsException {
        return customersDao.getOneCustomer(customerID);
    }
}
