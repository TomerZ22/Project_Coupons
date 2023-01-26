package dao;

import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import db.ConnectionPool;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CouponsDaoImp implements CouponsDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    /**
     * This method adds coupon to the db
     * @param coupon
     * @throws SQLException
     */

    @Override
    public void addCoupon(Coupon coupon) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("insert into coupons (company_id, category_id, title, description, start_date, end_date, amount, price, image)" +
                "values(?,?,?,?,?,?,?,?,?)");
        statement.setInt(1, coupon.getCompanyId());
        statement.setInt(2, coupon.getCategory().ordinal()+1);
        statement.setString(3, coupon.getTitle());
        statement.setString(4, coupon.getDescription());
        statement.setDate(5, coupon.getStartDate());
        statement.setDate(6, coupon.getEndDate());
        statement.setInt(7, coupon.getAmount());
        statement.setDouble(8, coupon.getPrice());
        statement.setString(9, " ");
        statement.execute();
        System.out.println("coupon added successfully");
        pool.restoreConnection(con);
    }

    /**
     * This method updates coupon at the db
     * @param coupon
     * @throws SQLException
     */
    @Override
    public void updateCoupon(Coupon coupon) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement(
                "update coupons set title=?, description=?, start_date=?, end_date=?, amount=?, price=?" +
                        ", image=? where id="+coupon.getId());
        statement.setString(1, coupon.getTitle());
        statement.setString(2, coupon.getDescription());
        statement.setDate(3, coupon.getStartDate());
        statement.setDate(4, coupon.getEndDate());
        statement.setInt(5, coupon.getAmount());
        statement.setDouble(6, coupon.getPrice());
        statement.setString(7, coupon.getImage());
        statement.execute();
        System.out.println("coupon updated successfully");
        pool.restoreConnection(con);
    }

    /**
     * This method delete coupon from the db
     * @param couponId
     * @throws SQLException
     */
    @Override
    public void deleteCoupon(int couponId) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("delete from coupons where id=" + couponId);
        statement.execute();
        System.out.println("coupon deleted successfully");
        pool.restoreConnection(con);
    }

    /**
     * This method returns all the coupons from the db
     * @return </list> of all coupons
     * @throws SQLException
     */
    @Override
    public List<Coupon> getAllCoupons() throws SQLException {
        List<Coupon> coupons = new ArrayList<>();
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("select * from coupons");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            coupons.add(new Coupon(rs.getInt(1), rs.getInt(2), Category.values()[rs.getInt(3)],
                    rs.getString(4), rs.getString(5), rs.getDate(6),
                    rs.getDate(7), rs.getInt(8), rs.getDouble(8),
                    rs.getString(9)));
        }
        pool.restoreConnection(con);
        return coupons;
    }

    /**
     * This method returns from the db one coupon based on given coupon id
     * @param couponId
     * @return a selected coupon from the db
     * @throws SQLException
     */
    @Override
    public Coupon getOneCoupon(int couponId) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("select * from coupons where id=" + couponId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Coupon coupon = new Coupon(rs.getInt(1), rs.getInt(2),
                     (Category.values()[rs.getInt(3)-1]), rs.getString(4), rs.getString(5), rs.getDate(6),
                    rs.getDate(7), rs.getInt(8), rs.getDouble(8),
                    rs.getString(9));
            pool.restoreConnection(con);
            return coupon;
        }
        return null;
    }

    /**
     * This method connects a customer to his coupon based on customer id and coupon id at the table customers vs coupons
     * @param customerID
     * @param couponID
     * @throws SQLException
     */
    @Override
    public void addCouponPurchase(int customerID, int couponID) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("insert into coupons_vs_customers values(?,?)");
        statement.setInt(1,customerID);
        statement.setInt(2,couponID);
        statement.execute();
        System.out.println("coupon purchased successfully");
        pool.restoreConnection(con);
    }

    /**
     * This method delete from the table customers vs coupons a given purchase that we want to undo
     * @param customerID
     * @param couponID
     * @throws SQLException
     */
    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement= con.prepareStatement("delete from coupons_vs_customers where customer_id="+customerID+" and coupons_id="+couponID);
        statement.execute();
        System.out.println("coupon purchase deleted successfully");
        pool.restoreConnection(con);
    }
}
