package bl.Facades;

import dao.*;
import dao.daoInterfaces.CompaniesDao;
import dao.daoInterfaces.CouponsDao;
import dao.daoInterfaces.CustomersDao;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CompaniesDao companyDao = new CompaniesDaoImp();
    protected CustomersDao customersDao= new CustomersDaoImp();
    protected CouponsDao couponDao = new CouponsDaoImp();

    public abstract boolean login(String email, String password) throws SQLException;
}
