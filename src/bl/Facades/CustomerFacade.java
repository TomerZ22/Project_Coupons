package bl.Facades;

import Exceptions.CouponDoesntExistException;
import Exceptions.CustomerExistsException;
import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import bl.Facades.login.CouponPriceDosentExist;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerFacade extends ClientFacade {
    
    
    @Override
    public boolean login(String email, String password) throws SQLException {
        customerID = customersDao.isCustomerExist(email, password);
        System.out.println("Logged In");
        return customerID != -1;

    }

    private int customerID;

    public void purchaseCoupon(Coupon coupon) throws SQLException, CouponDoesntExistException {
        List<Coupon> CustomerCoupons = getCustomerCoupons();
        for (int i = 0; i < CustomerCoupons.size(); i++) {
            if (coupon.getTitle().equals(CustomerCoupons.get(i).getTitle())
                    || coupon.getAmount() <= 0 || Objects.equals(coupon.getEndDate(), Date.valueOf(LocalDate.now()))) {

                throw new CouponDoesntExistException("The Coupon doesn't Exist");
            }
            CustomerCoupons.add(coupon);
            System.out.println("Nice Buy");
        }
    }
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

    public List<Coupon> getCustomerCouponsByCategory(Category category) throws SQLException, CouponDoesntExistException {
            List<Coupon> CustomerCoupons = getCustomerCoupons();
            List<Coupon> allCustomersCouponsByCategory = new ArrayList<>();
        for (Coupon customerCoupon : CustomerCoupons) {
            if (category.name().equals(customerCoupon.getCategory().name())) {
                allCustomersCouponsByCategory.add(customerCoupon);
                System.out.println("Successfully Add Coupon By Category");
            }
                throw new CouponDoesntExistException("Invalid Category");
        }
        return CustomerCoupons;
    }

    public List<Coupon> getCustomerCouponsUpToPrice(double maxPrice) throws SQLException, CouponDoesntExistException, CouponPriceDosentExist {
        List<Coupon> CustomerCoupons = getCustomerCoupons();
        List<Coupon> allCustomersCouponsByPrice = new ArrayList<>();
       for (int i = 0; i <CustomerCoupons.size() ; i++)
            {
            if (maxPrice > CustomerCoupons.get(i).getPrice()) {
                allCustomersCouponsByPrice.add(CustomerCoupons.get(i));
                System.out.println("Successfully Add Coupon By MaxPrice");
            } else
                throw new CouponPriceDosentExist("The Coupon Price isn't Valid");
        }
        return allCustomersCouponsByPrice;
    }

    public Customer getCustomerDetails() throws SQLException, CustomerExistsException {
        return customersDao.getOneCustomer(customerID);
    }
}
