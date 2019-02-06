package ismapp.iitism.cyberlabs.com.ismapp.authentication.LogIn.View;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.LogIn.Model.LoginModel;

public interface LoginView {
    void showProgressBar(boolean show);
    void setIntent(LoginModel loginModel);
}
