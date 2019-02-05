package ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Model.Otp_Response_Model;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Provider.Provider_interface;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.View.View_interface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class presenter_imple implements Presenter_Interface {
private Provider_interface provider_interface;
private View_interface view_interface;

    public presenter_imple(Provider_interface provider_interface, View_interface view_interface) {
        this.provider_interface = provider_interface;
        this.view_interface = view_interface;
    }

    @Override
    public void getResponse(String email) {
          view_interface.showProgressBar(true);
          provider_interface.getOtpResponse(email, new PresenterCallback() {
              @Override
              public void onSuccess(Object o) {
                  view_interface.showOtpResponse((Otp_Response_Model) o);
                  view_interface.showProgressBar(false);
              }

              @Override
              public void OnFailure(String msg) {
                  view_interface.showError(msg);
                  view_interface.showProgressBar(false);
                  view_interface.checkConnection();
              }
          }

          );
    }
}