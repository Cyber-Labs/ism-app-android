package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Model.LoginModel;


public interface LoginCallBack {
    void getVerification(LoginModel loginModel);
    void onFailure();
}
