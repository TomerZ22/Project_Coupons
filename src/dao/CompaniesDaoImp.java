package dao;

import JavaBeans.Company;
import db.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CompaniesDaoImp implements CompaniesDao{
    private ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public boolean isCompanyExists(String email, String password)
    {

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
        pool.restoreConnection(con);
    }

    @Override
    public void updateCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement
                (
                "UPDATE companies" +
                "SET name= , email= ?, password = ?," +
                "WHERE id =  ; ");
        preparedStatement.execute();
        pool.restoreConnection(con);
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
