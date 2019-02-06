package ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Model.NewPassword;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Provider.Retrofit_reset_interface;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.View.reset_interface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class reset_presenter_impl implements reset_presenter_interface {
    reset_interface reset_interface;
    Retrofit_reset_interface retrofit_reset_interface;

    public reset_presenter_impl(ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.View.reset_interface reset_interface, Retrofit_reset_interface retrofit_reset_interface) {
        this.reset_interface = reset_interface;
        this.retrofit_reset_interface = retrofit_reset_interface;
    }



    @Override
    public void sendResponse(String email, String Password, int otp) {
       reset_interface.showProgressbar(true);
       retrofit_reset_interface.getSuccessResponse(email, Password, otp, new PresenterCallback() {
           @Override
           public void onSuccess(Object o) {
               NewPassword newPassword = (NewPassword)o;
               reset_interface.showProgressbar(false);
               reset_interface.showResponse(newPassword);
           }

           @Override
           public void OnFailure(String msg) {
               reset_interface.messagerror(msg);
               reset_interface.checkConnection();
           }
       } );
    }

    @Override
    public void onDestroy() {

    }
}
