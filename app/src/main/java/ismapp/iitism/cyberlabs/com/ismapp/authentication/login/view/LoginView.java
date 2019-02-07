package ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.model.LoginModel;

public interface LoginView {
    void showProgressBar(boolean show);
    void setIntent(LoginModel loginModel);
}
