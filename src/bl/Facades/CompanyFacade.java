package bl.Facades;

import Exceptions.CouponDoesntExistException;
import Exceptions.CouponTitleExistsException;
import Exceptions.NoCouponsToDeleteException;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyFacade extends ClientFacade {
    private int companyID;

    public CompanyFacade() throws SQLException {}

    /**
     * This method allows access to the facade throw log in validation
     * @param email
     * @param password
     * @retur true if email and password are correct or false if it isn't found in the db
     * @throws SQLException
     */
    @Override
    public boolean login(String email, String password) throws SQLException {
        companyID = companyDao.isCompanyExists(email,password);
        return companyID != -1;
    }

    /**
     * This method allows company to add (publish) new coupon
     * @param coupon the coupon we will publish
     * @throws SQLException
     * @throws CouponTitleExistsException
     */
    public void addCoupons(Coupon coupon) throws SQLException, CouponTitleExistsException {
        List<Coupon> companyCoupons = getCompanyCoupons();
        if (companyCoupons.size()==0){
            couponDao.addCoupon(coupon);
            return;
        }
        for (int i = 0; i < companyCoupons.size(); i++) {
            if (coupon.getTitle().equals(companyCoupons.get(i).getTitle())) {
                throw new CouponTitleExistsException("You already published a coupon with the same title, please change the title");
            }
        }
        couponDao.addCoupon(coupon);
    }

    /**
     * This method updates coupon except to coupon id and company id
     * @param coupon the coupon to update
     * @throws SQLException
     */
    public void updateCoupons(Coupon coupon) throws SQLException {
        couponDao.updateCoupon(coupon);
    }

    /**
     * This method deletes all the coupons of a company based on it's id
     * @param companyID
     * @throws SQLException
     */
    public void deleteAllCompanyCoupons(int companyID) throws SQLException {
        companyDao.deleteCompanyCoupons(companyID);
    }

    /**
     * This method allows companies to delete a singular coupon
     * @param coupon the coupon you wish to delete
     * @throws SQLException
     * @throws NoCouponsToDeleteException in case the coupon isn't related to the company or not found in the db
     */
    public void deleteSingularCoupon(Coupon coupon) throws SQLException, NoCouponsToDeleteException {
        if (coupon.getCompanyId()==companyID)
        couponDao.deleteCoupon(coupon.getId());
        else
            throw new NoCouponsToDeleteException();
    }

    /**
     * This Method return all the coupon published by the company
     * @return list of coupons
     * @throws SQLException
     */
    public List<Coupon> getCompanyCoupons () throws SQLException {
        List<Coupon>allCoupons=couponDao.getAllCoupons();
        List<Coupon>companyCoupons=new ArrayList<>();
        for (int i = 0; i <allCoupons.size(); i++) {
          if (companyID==allCoupons.get(i).getCompanyId())
            companyCoupons.add(allCoupons.get(i));
        }
        return companyCoupons;
    }

    /**
     * This method returns coupons from a certain category owned by the company
     * @param category the field that the given coupons will be related to
     * @returnlist of the company's coupons from a given category
     * @throws SQLException
     */
    public List<Coupon> getCompanyCouponsByCategory (Category category) throws SQLException {
        List<Coupon> companyCoupons= getCompanyCoupons();
        List<Coupon>getCompanyCouponsByCategory=new ArrayList<>();
        for (int i = 0; i < companyCoupons.size(); i++) {
            if (category.name()==companyCoupons.get(i).getCategory().name())
                getCompanyCouponsByCategory.add(companyCoupons.get(i));
        }
        return getCompanyCouponsByCategory;
    }

    /**
     * This method returns coupons of a company up to a given price
     * @param maxPrice receives a double that set the maximum price
     * @return list of coupons
     * @throws SQLException
     */
    public List<Coupon> getCompanyCouponsUpToPrice (double maxPrice) throws SQLException {
        List<Coupon> companyCoupons= getCompanyCoupons();
        List<Coupon>companyCouponsUpToPrice=new ArrayList<>();
        for (int i = 0; i < companyCoupons.size(); i++) {
            if (maxPrice>companyCoupons.get(i).getPrice()){
                companyCouponsUpToPrice.add(companyCoupons.get(i));
            }
        }
        return companyCouponsUpToPrice;
    }

    /**
     *      * Added a method that return a singular coupon based on its id
     *      * @return the coupon identified with the given id
     * @param id of the coupon
     * @throws SQLException
     * @throws NoCouponsToDeleteException
     */
    public Coupon getOneCompanyCouponById(int id) throws SQLException, CouponDoesntExistException {
        List<Coupon> companyCoupons= getCompanyCoupons();
        for (Coupon coupon: companyCoupons){
            if (coupon.getId()==id)
                return coupon;
        }
      throw new CouponDoesntExistException();
    }

    /**
     * This method returns all the data of the company
     * @return data of the company
     * @throws SQLException
     */
    public Company getCompanyDetails() throws SQLException {
        return companyDao.getOneCompany(companyID);
    }
}
