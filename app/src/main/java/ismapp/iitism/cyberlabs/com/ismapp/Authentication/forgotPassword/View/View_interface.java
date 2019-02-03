package ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.View;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Model.Otp_Response_Model;

public interface View_interface {
    void showProgressBar(boolean check);
    void showOtpResponse(Otp_Response_Model Otp_Response_Model);
    void checkConnection();
    void showError(String msg);



}
