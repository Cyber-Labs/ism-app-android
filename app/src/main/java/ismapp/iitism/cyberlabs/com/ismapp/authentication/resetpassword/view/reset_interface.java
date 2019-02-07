package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.view;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.model.ResetPassword;

public interface reset_interface {

    void showProgressbar( boolean check);
    void checkConnection();
    void showResponse(ResetPassword resetPassword);
    void messagerror(String msg);
    void verifyBtnClickable();
}
