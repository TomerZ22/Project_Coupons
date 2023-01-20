package bl.Facades;

import enums.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import bl.ClientFacade;

import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends ClientFacade
{

    public CompanyFacade(int companyID) {
        this.companyID = companyID;
    }

    private int companyID;
    @Override
    public boolean login(String email, String password) throws SQLException {
        return false;
    }

    public void addCoupons(Coupon coupon)throws SQLException {
        if (!companyDao.equals(coupon.getTitle()));
        {
        System.out.println("The Operation was A Success");
            couponDao.addCoupon(coupon);
        }
        System.out.println("We Didn't Got It");
    }


    public void updateCoupons(Coupon coupon) throws SQLException
    {

    }
    public void deleteCoupons(Coupon coupon) throws SQLException {
        companyDao.deleteCompanyCoupons(coupon.getId());
    }
    public List<Coupon> getCompanyCoupons () throws SQLException
    {
        return null;
    }

    public List<Coupon> getCompanyCoupons (Category category) throws SQLException
    {
        return null;
    }
    public List<Coupon> getCompanyCoupons (double maxPrice) throws SQLException
    {
        return null;
    }
    public Company getCompanyDetails() throws SQLException {
        return companyDao.getOneCompany(companyID);
    }

}