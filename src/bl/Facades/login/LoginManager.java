package bl.Facades.login;

import Exceptions.LoginErrorException;
import bl.Facades.AdminFacade;
import bl.Facades.ClientFacade;
import enums.ClientType;

import java.sql.SQLException;

public class LoginManager {

    private static LoginManager instance;

    private LoginManager() {}

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType type) throws LoginErrorException, SQLException {
        switch (type) {

            case Administrator:
                AdminFacade adminFacade = new AdminFacade();
                if (adminFacade.login(email, password))
                    return adminFacade;
                else
                    throw new LoginErrorException("Your email or password isn't valid");

            case Company:
                bl.CompanyFacade companyFacade= new bl.CompanyFacade();
                if (companyFacade.login(email,password))
                    return companyFacade;
                else throw new LoginErrorException("Your email or password isn't valid");

            case Customer:
                bl.Facades.CustomerFacade customerFacade= new bl.Facades.CustomerFacade();
                if (customerFacade.login(email,password))
                    return customerFacade;
                else
                    throw new LoginErrorException("Your email or password isn't valid");

        }
        return null;
    }
}