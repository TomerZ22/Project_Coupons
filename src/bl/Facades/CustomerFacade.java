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

    public void purchaseCoupon(Coupon coupon) throws SQLException, CouponAlreadyBoughtException {
        for (int i = 0; i < couponsDao.getAllCoupons().size(); i++) {
            if (couponsDao.didCouponAlreadyPurchased(coupon.getId(), customerID)) {
                throw new CouponAlreadyBoughtException();
            }
        }
            couponsDao.addCouponPurchase(customerID, coupon.getId());
            int count = coupon.getAmount();
            if (allCoupons.contains(coupon) && coupon.getPrice() == 0) {
                if (!Objects.equals(coupon.getEndDate(), Date.valueOf(LocalDate.now()))) {
                    couponsDao.addCoupon(coupon);
                    System.out.println("Nice Buy");
                    count--;
                } else {
                    System.out.println("Sorry The Coupon Isn't Available");
                }
                coupon.setAmount(count);
            }
        }
    
    
      /**
     * This method will return all coupons that bought by the customer that logged in
     * @return list of customer coupon
     * @throws SQLException,EmptyCartException in case of sql failure or empty list of bought coupons
     */
    public List<Coupon> getCustomerCoupons() throws SQLException, EmptyCartException {
        List<Coupon> customerCoupons = new ArrayList<>();
        for (int i = 0; i < allCoupons.size(); i++) {
            if (couponsDao.didCouponAlreadyPurchased(allCoupons.get(i).getId(), customerID)) {
                customerCoupons.add(allCoupons.get(i));
            }
        }
        if (customerCoupons.size()==0){
           throw new EmptyCartException();
        }
        return customerCoupons;
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
