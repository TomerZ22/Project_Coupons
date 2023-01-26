package bl.login;

import bl.Facades.AdminFacade;
import bl.Facades.ClientFacade;
import bl.Facades.CompanyFacade;
import bl.Facades.CustomerFacade;
import enums.ClientType;

public class LoginManager {
    private static LoginManager instance;

    private LoginManager() {}

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientFacade Login(String email, String password, ClientType clientType) {
        //Administrator
        AdminFacade admin = new AdminFacade();
        if (clientType == ClientType.Administrator && admin.login(email, password)) {
//            System.out.println("Logged in");
            return admin;
        }

        //Company
        CompanyFacade company = new CompanyFacade();
//        if(clientType == ClientType.Company && company.isCompanyExists(email, password)){
//            return company;
//        }

        //Customer
        CustomerFacade customer = new CustomerFacade();
//        if(clientType == ClientType.Customer && customer.isCustomerExists(email, password)){
//            return customer;
//        }

        return null;//If wrong input
    }

}


