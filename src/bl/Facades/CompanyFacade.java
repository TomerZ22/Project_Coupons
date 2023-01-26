package bl.Facades;

import JavaBeans.Company;
import JavaBeans.Coupon;
import bl.login.ClientFacade;
import db.ConnectionPool;
import enums.Category;
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
    public CompanyFacade() {}

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


    public void updateCoupons(Coupon coupon) throws SQLException, CouponExistsException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * FROM coupons WHERE id = " + coupon.getId());
        ResultSet rs = ps.executeQuery();
        if(!coupon.getTitle().equals(rs.getString(2)));
        ps.setString(5,coupon.getDescription());
        ps.setDate(6,coupon.getStartDate());
        ps.setDate(7,coupon.getEndDate());
        ps.setInt(8,coupon.getAmount());
        ps.setDouble(9,coupon.getPrice());
        ps.setString(10,coupon.getImage());
        ps.execute();
        pool.restoreConnection(con);
    }
    public void deleteCoupons(Coupon coupon) throws SQLException {
        couponDao.deleteCoupon(coupon.getId());
    }

    public List<Coupon> getCompanyCoupons () throws SQLException
    {
        return couponDao.getAllCoupons();
    }
    public List<Coupon> getCompanyCoupons (Category category) throws SQLException
    {
        if (category==null)
        {
            return getCompanyCoupons();
        }
        else
        return null;
    }
    public List<Coupon> getCompanyCoupons (double maxPrice) throws SQLException
    {
      ;
        return couponDao.getAllCoupons();
    }
    public Company getCompanyDetails() throws SQLException {
        return companyDao.getOneCompany(getCompanyDetails().getId());
    }

}
