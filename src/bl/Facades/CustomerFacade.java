package bl.Facades;

import Exceptions.*;
import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerFacade extends ClientFacade {

//while logging in customer id will be recognized with the specific customer
    private int customerID;

    /**
     * This method allows access to the customer facade throw log in validation
     * @param email
     * @param password
     * @retur true if email and password are correct or false if it isn't found in the db
     * @throws SQLException
     */
    @Override
    public boolean login(String email, String password) throws SQLException {
        customerID = customersDao.isCustomerExist(email, password);
        System.out.println("Logged In");
        return customerID != -1;

    }

    /**
     * This method allows a customer to view all the coupons
     * @return List od all coupons
     * @throws SQLException
     */
    public List<Coupon> getAllCoupons() throws SQLException {
        return couponDao.getAllCoupons();
    }

    /**
     * This method allow acustomer to purchase a coupon and adding it to the db
     * @param coupon we wish to purchase
     * @throws SQLException
     * @throws CouponDoesntExistException in case that the given coupon doesn't for sale ( doesn't exist in the db )
     * @throws CouponAlreadyBoughtException appears in case this specific customer already bought this specific coupon
     */
    public void purchaseCoupon(Coupon coupon) throws SQLException,
            CouponDoesntExistException, CouponAlreadyBoughtException {
        List<Coupon> customerCoupons = getCustomersCoupons();
        if (customerCoupons.size()==0){
            customerCoupons.add(coupon);
            couponDao.addCouponPurchase(coupon.getId(),customerID);
            return;
        } for (int i = 0; i < customerCoupons.size(); i++) {
            if (coupon.getTitle().equals(customerCoupons.get(i).getTitle())|| coupon.getAmount() <= 0||
                    Objects.equals(coupon.getEndDate(), Date.valueOf(LocalDate.now()))){
                    throw new CouponDoesntExistException();
        }
            if (couponDao.didCouponAlreadyPurchased(coupon.getId(), customerID)) {
                        throw new CouponAlreadyBoughtException();
                  }
    }
        customerCoupons.add(coupon);
        couponDao.addCouponPurchase(customerID,coupon.getId());
            System.out.println("Nice Buy");
        }

    /**
     * This method returns all coupons that bought by this customer
     * @return list of coupons bought by this customer
     * @throws SQLException
     */
    public List<Coupon> getCustomersCoupons()throws SQLException{
        List<Coupon> allCoupons = couponDao.getAllCoupons();
        List<Coupon> customerCoupons = new ArrayList<>();
        for (Coupon newCoupon : allCoupons) {
            if (couponDao.didCouponAlreadyPurchased(newCoupon.getId(), customerID)) {
                customerCoupons.add(newCoupon);
            }
        }
        return customerCoupons;
    }

    /**
     * This method returns all the customer coupons related to the given category
     * @param category the category of the coupon
     * @return list of coupons filtered by the given category
     * @throws SQLException
     * @throws CouponDoesntExistException in case this customer didn't purchase coupons from the given category
     */
    public List<Coupon> getCustomerCouponsByCategory(Category category) throws SQLException, CouponDoesntExistException {
            List<Coupon> customerCoupons = getCustomersCoupons();
            List<Coupon> allCustomersCouponsByCategory = new ArrayList<>();
        for (int i = 0; i <customerCoupons.size() ; i++) {
            if (customerCoupons.get(i).getCategory().name().equals(category.name())){
                allCustomersCouponsByCategory.add(customerCoupons.get(i));
        }
        }
        if (allCustomersCouponsByCategory.size()==0){
            throw new CouponDoesntExistException();
        }
        System.out.println("Successfully Added Coupons By Category");
        return customerCoupons;
    }

    /**
     * This method returns coupons filtered by maximum price
     * @param maxPrice the max price of the coupons
     * @return list of coupon filtered by maximum price
     * @throws SQLException
     * @throws CouponPriceDoesntExist ic case the maximum price is lower than the cheapest coupon
     */
    public List<Coupon> getCustomerCouponsUpToPrice(double maxPrice) throws SQLException, CouponPriceDoesntExist {
        List<Coupon> CustomerCoupons = getCustomersCoupons();
        List<Coupon> allCustomersCouponsByPrice = new ArrayList<>();
       for (int i = 0; i <CustomerCoupons.size() ; i++) {
            if (maxPrice > CustomerCoupons.get(i).getPrice()) {
                allCustomersCouponsByPrice.add(CustomerCoupons.get(i));
            }
        }
       if (allCustomersCouponsByPrice.size()==0){
           throw new CouponPriceDoesntExist();
       }
        return allCustomersCouponsByPrice;
    }

    public Customer getCustomerDetails() throws SQLException, CustomerExistsException {
        return customersDao.getOneCustomer(customerID);
    }
}
