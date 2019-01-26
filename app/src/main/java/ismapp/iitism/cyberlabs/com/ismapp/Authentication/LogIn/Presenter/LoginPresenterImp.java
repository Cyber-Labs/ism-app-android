package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.LoginCallBack;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Model.LoginModel;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Provider.LoginProvider;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.View.LoginView;

public class LoginPresenterImp implements LoginPresenter {
  private   LoginView loginView;
   private LoginProvider loginProvider;

    public LoginPresenterImp(LoginView loginView, LoginProvider loginProvider) {
        this.loginView = loginView;
        this.loginProvider = loginProvider;
    }

    @Override
    public void getLoginResponse(String email, String password) {
        loginView.showProgressBar(true);
        loginProvider.getLoginResponse(email, password, new LoginCallBack() {
            @Override
            public void getVerification(LoginModel loginModel) {
                loginView.showProgressBar(false);
                loginView.setIntent(loginModel);
            }

            @Override
            public void onFailure() {
                loginView.showProgressBar(false);
                loginView.setIntent(new LoginModel(false,"Unable to Connect Server!!!"));

            }
        });

    }
}
