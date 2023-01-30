package bl;

import Exception.CouponTitleExistsException;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import bl.Facades.ClientFacade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyFacade extends ClientFacade
{
    private int companyID;

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
     * @param coupon
     * @throws SQLException
     * @throws CouponTitleExistsException
     */
    public void addCoupons(Coupon coupon) throws SQLException, CouponTitleExistsException {
        List<Coupon> coupons=couponDao.getAllCoupons();
        boolean titleExist=false;
        for (int i = 0; i < coupons.size(); i++) {
            if (coupon.getTitle().equals(coupons.get(i).getTitle())){
                throw new CouponTitleExistsException("You already published a coupon with the same title, please change the title");
            }
        }
        couponDao.addCoupon(coupon);
    }

    /**
     * This method updates coupon except to coupon id and company id
     * @param coupon
     * @throws SQLException
     */
    public void updateCoupons(Coupon coupon) throws SQLException {
        couponDao.updateCoupon(coupon);
    }

    /**
     * This method deletes a coupon based on it's id
     * @param coupon
     * @throws SQLException
     */
    public void deleteCoupons(Coupon coupon) throws SQLException {
        companyDao.deleteCompanyCoupons(coupon.getId());
    }

    /**
     * This Method return all the coupon published by the company
     * @return list of coupons
     * @throws SQLException
     */
    public List<Coupon> getCompanyCoupons () throws SQLException {
        List<Coupon>allCoupons= couponDao.getAllCoupons();
        List<Coupon>companyCoupons=new ArrayList<>();
        for (int i = 0; i < allCoupons.size(); i++) {
          if (companyID==allCoupons.get(i).getCompanyId())
            companyCoupons.add(allCoupons.get(i));
        }
        return companyCoupons;
    }

    /**
     * This method returns coupons owned by company based on a given category
     * @param category
     * @returnlist of coupons from a given category
     * @throws SQLException
     */
    public List<Coupon> getCompanyCouponsByCategory (Category category) throws SQLException {
        List<Coupon>getCompanyCouponsByCategory=new ArrayList<>();
        List<Coupon>companyCoupons=getCompanyCoupons();
        for (int i = 0; i < companyCoupons.size(); i++) {
            if (category==companyCoupons.get(i).getCategory())
                getCompanyCouponsByCategory.add(companyCoupons.get(i));
        }
        return getCompanyCouponsByCategory;
    }

    /**
     * This method returns coupons of a company up to a given price
     * @param maxPrice
     * @return list of coupons
     * @throws SQLException
     */
    public List<Coupon> getCompanyCouponsUpToPrice (double maxPrice) throws SQLException {
        List<Coupon>allCompanyCoupons=getCompanyCoupons();
        List<Coupon>companyCouponsUpToPrice=new ArrayList<>();
        for (int i = 0; i < allCompanyCoupons.size(); i++) {
            if (maxPrice>allCompanyCoupons.get(i).getPrice()){
                companyCouponsUpToPrice.add(allCompanyCoupons.get(i));
            }
        }
        return companyCouponsUpToPrice;
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
