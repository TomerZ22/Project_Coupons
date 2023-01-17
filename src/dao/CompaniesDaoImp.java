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

public class CompaniesDaoImp implements CompaniesDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    /**
     * Method That Check If The Company Exists In The DataBase
     *
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("select * from companies");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())
            if (Objects.equals(rs.getString(3), email) && Objects.equals(rs.getString(4), password)) {
                preparedStatement.execute();
                System.out.println("Company Exist");
                pool.restoreConnection(con);
            }
        return false;
    }


    /**
     * This Method Adds a new Company To The Data Base
     *
     * @param company
     * @throws SQLException
     */
    @Override
    public void addCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("insert into companies(name,email,password) values(?,?,?)");
        preparedStatement.setString(1, company.getName());
        preparedStatement.setString(2, company.getEmail());
        preparedStatement.setString(3, company.getPassword());
        preparedStatement.execute();
        System.out.println("Company was Successfully Added");
        pool.restoreConnection(con);
    }


    /**
     * This Method Update The Company in the Data Base
     *
     * @param company
     * @throws SQLException
     */
    @Override
    public void updateCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from companies");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString(3).equals(company.getEmail())&&rs.getString(4).equals(company.getPassword())) {
                PreparedStatement update = con.prepareStatement("UPDATE company SET first_name= ?, last_name= ?, email= ?, password= ? WHERE id= " + rs.getInt(1));
                update.setString(1, company.getName());
                update.setString(2, company.getEmail());
                update.setString(3, company.getPassword());
                update.setInt(4, rs.getInt(1));
                update.execute();
                System.out.println("Company was Successfully Update");
                pool.restoreConnection(con);
                return;
            }
        }
    }

    @Override
    public void deleteCompany(int companyId) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(" DELETE FROM companies WHERE id =?");

                preparedStatement.setInt(1,companyId);
                preparedStatement.execute();
                System.out.println("Company was Successfully Deleted");
                pool.restoreConnection(con);
                return;
            }



        @Override
        public Company getOneCompany ( int companyId){
            return null;
        }

        @Override
        public List<Company> getAllCompanies ()
        {
            return null;
        }
    }
