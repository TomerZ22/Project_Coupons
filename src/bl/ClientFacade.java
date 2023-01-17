package bl;

import dao.*;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CompaniesDao companyDao = new CompaniesDaoImp();
    protected CustomersDao customerDao = new CustomersDaoImp();
    protected CouponsDao couponDao = new CouponsDaoImp();

    public abstract boolean login(String email, String password) throws SQLException;
}
