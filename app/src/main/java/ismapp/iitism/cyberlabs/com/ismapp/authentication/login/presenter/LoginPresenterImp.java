package ismapp.iitism.cyberlabs.com.ismapp.authentication.login.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.model.LoginModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.provider.LoginProvider;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view.LoginView;
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
