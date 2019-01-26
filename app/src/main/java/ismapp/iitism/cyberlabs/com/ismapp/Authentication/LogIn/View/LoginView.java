package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.View;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Model.LoginModel;

public interface LoginView {
    void showProgressBar(boolean show);
    void setIntent(LoginModel loginModel);
}
