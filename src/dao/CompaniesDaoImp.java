package dao;

import JavaBeans.Company;
import dao.daoInterfaces.CompaniesDao;
import db.ConnectionPool;
import Exceptions.CompanyExistsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CompaniesDaoImp implements CompaniesDao {
    private ConnectionPool pool = ConnectionPool.getInstance();


    /**
     * This method is called only when we delete a company, hence we delete all the history of customers purchase.
     *
     * @param id - company id
     * @throws SQLException - If failed to connect or detect in the SQL.
     */
    public void deleteCustomerPurchaseHistory(int id) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM coupons_vs_customers WHERE coupon_id = " + id);
            ps.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * This method if for the AdminFacade, when we delete the company, then we delete all the coupons of that company.
     *
     * @param id - The id of the company we want to delete
     * @throws SQLException - If failed to connect or detect in the SQL.
     */
    public void deleteCompanyCoupons(int id) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM coupons.coupons WHERE company_id= " + id);
            ps.execute();
            System.out.println("Coupons deleted successfully");
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * This method is to check for company existence in order to add company from the BL AdminFacade
     *
     * @param name  - company name
     * @param email - company email
     * @return - false if company does not exist
     * @throws CompanyExistsException - Throws exception if company exists.
     */
    public boolean isCompanyExistByName_Email(String name, String email) throws CompanyExistsException, SQLException {
        ArrayList<Company> companies = (ArrayList<Company>) getAllCompanies();
        for (Company company : companies) {
            if (company.getName().equals(name) || company.getEmail().equals(email))
                throw new CompanyExistsException("Sorry the Name or Email already exists, try different");
        }
        return false;
    }

    /**
     * Method That Check If The Company Exists In The DataBase
     *
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public int isCompanyExists(String email, String password) throws SQLException {
        Connection con = pool.getConnection();
        ;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from companies");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (Objects.equals(rs.getString(3), email) && Objects.equals(rs.getString(4), password)) {
                    int companyId = rs.getInt(1);
                    preparedStatement.execute();
                    System.out.println("Company Exist");
                    return companyId;
                }
            }
            System.out.println("Company doesn't exist");
            return -1;
        } finally {
            pool.restoreConnection(con);
        }
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
        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into companies(name,email,password) values(?,?,?)");
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getEmail());
            preparedStatement.setString(3, company.getPassword());
            preparedStatement.execute();
            System.out.println("Company added Successfully");
        } finally {
            pool.restoreConnection(con);
        }
    }
    /**
     * This Method Update The Company in the Data Base
     *
     * @param company
     * @throws SQLException
     */
//    @Override
//    public void updateCompany(Company company) throws SQLException {
//        Connection con = pool.getConnection();
//        try {
//            PreparedStatement ps = con.prepareStatement("UPDATE companies SET name = ?, email = ?, password = ? where id =" + company.getId());
//            ps.setString(1, company.getName());
//            ps.setString(2, company.getEmail());
//            ps.setString(3, company.getPassword());
//            ps.execute();
//        }finally {
//            pool.restoreConnection(con);
//        }
//    }

    /**
     * This Method Deletes Company By ID
     *
     * @param companyId
     * @throws SQLException
     */
    @Override
    public void deleteCompany(int companyId) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(" DELETE FROM companies WHERE id =?");
            preparedStatement.setInt(1, companyId);
            preparedStatement.execute();
            System.out.println("Company was Successfully Deleted");
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * This Method Get One Company From The DataBase
     *
     * @param companyId
     * @return
     * @throws SQLException
     */
    @Override
    public Company getOneCompany(int companyId) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement gettingCustomer = con.prepareStatement("SELECT * FROM companies where id=" + companyId);
            ResultSet rs = gettingCustomer.executeQuery();
            if (rs.next()) {
                return new Company(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
            }
            return null;
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * This Method Gets All The Companies From The DataBase
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Company> getAllCompanies() throws SQLException {
        ArrayList<Company> companies = new ArrayList<>();
        Connection con = pool.getConnection();
        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM customers");
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                companies.add(new Company(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }
            if (companies.size() == 0) {
                return null;
            }
            return companies;
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * This method is used to update the company information in the database and check if the name didn't change
     *
     * @param company - The company to update.
     * @throws CompanyExistsException - If the company name changed throw an error
     * @throws SQLException           - If an error occurs during the connection.
     */
    public void updateCompanyAdminFacade(Company company) throws CompanyExistsException, SQLException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM company WHERE id = " + company.getId());
            ResultSet rs = ps.executeQuery();
            if (!company.getName().equals(rs.getString(2))) {
                pool.restoreConnection(conn);
                throw new CompanyExistsException("You cannot update company name!");
            }
            ps.setString(3, company.getEmail());
            ps.setString(4, company.getPassword());
            ps.execute();
        } finally {
            pool.restoreConnection(conn);
        }

    }
}
