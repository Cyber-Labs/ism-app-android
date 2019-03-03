package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;


public interface ForgotPasswordProvider {
    void getOtpResponse(String email, PresenterCallback presenterCallback);
    void OnDestroy();
}
