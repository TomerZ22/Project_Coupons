package dao;

import JavaBeans.Company;
import db.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CompaniesDaoImp implements CompaniesDao{
    private ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public boolean isCompanyExists(String email, String password)
    {
        Connection con = pool.getConnection();

        return false;
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("insert into companies(name,email,password) values(?,?,?)");
        preparedStatement.setString(1,company.getName());
        preparedStatement.setString(2,company.getEmail());
        preparedStatement.setString(3,company.getPassword());
        preparedStatement.execute();
        System.out.println("Company was Successfully Added");
        pool.restoreConnection(con);
    }

    @Override
    public void updateCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement
                (
                "UPDATE companies SET name=? , email=? , password =? WHERE id=?");
        PreparedStatement ps = con.prepareStatement("select * from companies");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            if(Objects.equals(rs.getString(3), company.getEmail()) && Objects.equals(rs.getString(4), company.getPassword()))
            {
                preparedStatement.setString(1,company.getName());
                preparedStatement.setString(2,company.getEmail());
                preparedStatement.setString(3,company.getPassword());
                preparedStatement.setInt(4,rs.getInt(1));
                preparedStatement.execute();
                System.out.println("Company was Successfully Update");
                pool.restoreConnection(con);
                return;
            }
        }
    }

    @Override
    public void deleteCompany(int companyId) {
    }

    @Override
    public Company getOneCompany(int companyId) {
        return null;
    }

    @Override
    public List<Company> getAllCompanies()
    {
        return null;
    }
}
