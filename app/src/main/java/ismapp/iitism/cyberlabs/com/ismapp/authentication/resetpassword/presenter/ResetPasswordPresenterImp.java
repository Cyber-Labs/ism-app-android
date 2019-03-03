package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.model.ResetPasswordModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.provider.ResetPasswordProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.view.ResetPasswordActivityInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ResetPasswordPresenterImp implements ResetPasswordPresenterInterface {
    ResetPasswordActivityInterface ResetPasswordActivityInterface;
    ResetPasswordProviderInterface resetPasswordProviderInterface;

    public ResetPasswordPresenterImp(ResetPasswordActivityInterface ResetPasswordActivityInterface, ResetPasswordProviderInterface resetPasswordProviderInterface) {
        this.ResetPasswordActivityInterface = ResetPasswordActivityInterface;
        this.resetPasswordProviderInterface = resetPasswordProviderInterface;
    }



    @Override
    public void sendResponse(String email, String Password, int otp) {
       ResetPasswordActivityInterface.showProgressbar(true);
       resetPasswordProviderInterface.getSuccessResponse(email, Password, otp, new PresenterCallback() {
           @Override
           public void onSuccess(Object o) {
               ResetPasswordModel resetPasswordModel = (ResetPasswordModel)o;
               ResetPasswordActivityInterface.showProgressbar(false);
               ResetPasswordActivityInterface.showResponse(resetPasswordModel);
           }

           @Override
           public void OnFailure(String msg) {
               ResetPasswordActivityInterface.messagerror(msg);
               ResetPasswordActivityInterface.checkConnection();
           }
       } );
    }

    @Override
    public void onDestroy() {

    }
}
