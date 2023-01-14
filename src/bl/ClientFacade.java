package bl;

import dao.CompaniesDao;
import dao.CompaniesDaoImp;

public abstract class ClientFacade {

    protected CompaniesDao companyDao = new CompaniesDaoImpl();

    public abstract boolean login(String email, String password);

}
