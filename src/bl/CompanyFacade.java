package bl;

import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void addCoupons(Coupon coupon) throws SQLException {

    }

    public void updateCoupons(Coupon coupon) throws SQLException {

    }
    public void deleteCoupons(Coupon coupon) throws SQLException {
        companyDao.deleteCompanyCoupons(coupon.getId());
    }
    public List<Coupon> getCompanyCoupons () throws SQLException
    {

    }

    public List<Coupon> getCompanyCoupons (Category category) throws SQLException
    {

    }
    public List<Coupon> getCompanyCoupons (double maxPrice) throws SQLException
    {


    }
    public Company getCompanyDetails() throws SQLException {
        return companyDao.getOneCompany(companyID);
    }

}
