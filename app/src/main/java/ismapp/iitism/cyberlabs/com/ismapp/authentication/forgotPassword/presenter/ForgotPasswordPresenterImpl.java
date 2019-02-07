package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.model.ForgotPasswordResponse;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.provider.ForgotPasswordProvider;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.view.ForgotPasswordView;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter {
private ForgotPasswordProvider forgotPasswordProvider_;
private ForgotPasswordView forgotPasswordView_;

    public ForgotPasswordPresenterImpl(ForgotPasswordProvider forgotPasswordProvider_, ForgotPasswordView forgotPasswordView_) {
        this.forgotPasswordProvider_ = forgotPasswordProvider_;
        this.forgotPasswordView_ = forgotPasswordView_;
    }

    @Override
    public void getResponse(String email) {
          forgotPasswordView_.showProgressBar(true);
          forgotPasswordProvider_.getOtpResponse(email, new PresenterCallback() {
              @Override
              public void onSuccess(Object o) {
                  forgotPasswordView_.showOtpResponse((ForgotPasswordResponse) o);
                  forgotPasswordView_.showProgressBar(false);
              }

              @Override
              public void OnFailure(String msg) {
                  forgotPasswordView_.showError(msg);
                  forgotPasswordView_.showProgressBar(false);
                  forgotPasswordView_.checkConnection();
              }
          }

          );
    }
}
