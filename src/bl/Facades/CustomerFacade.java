package bl.Facades;

import bl.login.ClientFacade;

import java.sql.SQLException;

public class CustomerFacade extends ClientFacade {

    @Override
    public boolean login(String email, String password) throws SQLException {
        return false;
    }
}
