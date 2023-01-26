package bl;

import enums.ClientType;

public class LoginManager {
    private LoginManager instance;

    private LoginManager(){

    }

    public LoginManager getInstance(){
        if(instance == null){
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientFacade Login(String email, String password, ClientType clientType){

        return null;//If wrong
    }

}
