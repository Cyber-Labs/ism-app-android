package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.LoginCallBack;

public interface LoginProvider {
    void getLoginResponse(String email, String password, LoginCallBack loginCallBack);
}
