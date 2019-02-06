package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.view;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.model.ForgotPasswordResponse;

public interface ForgotPasswordView {
    void showProgressBar(boolean check);
    void showOtpResponse(ForgotPasswordResponse forgotPasswordResponse);
    void checkConnection();
    void showError(String message);



}
