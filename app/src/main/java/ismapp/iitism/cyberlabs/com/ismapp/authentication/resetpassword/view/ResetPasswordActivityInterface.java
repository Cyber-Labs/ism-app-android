package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.view;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.model.ResetPasswordModel;

public interface ResetPasswordActivityInterface {

    void showProgressbar( boolean check);
    void checkConnection();
    void showResponse(ResetPasswordModel resetPasswordModel);
    void messagerror(String msg);
    void verifyBtnClickable();
}
