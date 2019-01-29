package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Model.LoginModel;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Provider.LoginProvider;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.View.LoginView;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

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
        loginProvider.getLoginResponse(email, password, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                loginView.showProgressBar(false);
                loginView.setIntent((LoginModel)o);
            }

            @Override
            public void OnFailure(String msg) {
                loginView.showProgressBar(false);
                loginView.setIntent(new LoginModel(false,"Unable to Connect Server!!!"));
            }
        });

    }
}
