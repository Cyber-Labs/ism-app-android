package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.view;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.model.ForgotPasswordResponse;

public interface ForgotPasswordView {
    void showProgressBar(boolean check);
    void showOtpResponse(ForgotPasswordResponse forgotPasswordResponse);
    void checkConnection();
    void showError(String message);



}
