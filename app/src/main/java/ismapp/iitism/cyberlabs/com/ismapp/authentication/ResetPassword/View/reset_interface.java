package ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.View;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Model.NewPassword;

public interface reset_interface {

    void showProgressbar( boolean check);
    void checkConnection();
    void showResponse(NewPassword newPassword);
    void messagerror(String msg);
    void verifyBtnClickable();
}
