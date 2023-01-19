package bl;

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

}
