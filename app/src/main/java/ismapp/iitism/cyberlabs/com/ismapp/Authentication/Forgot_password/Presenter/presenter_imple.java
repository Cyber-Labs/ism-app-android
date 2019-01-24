package ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.CallBack;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Model.Otp_Response_Model;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Provider.Provider_interface;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Provider.Retrofit_forgot_implementaion;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.View.View_interface;

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
          provider_interface.getOtpResponse(email, new CallBack() {
              @Override
              public void getVerification(Otp_Response_Model otp_response_model) {
                  view_interface.showOtpResponse(otp_response_model);
                  view_interface.showProgressBar(false);
              }

              @Override
              public void onFailure(String msg) {
                   view_interface.showError(msg);
                   view_interface.showProgressBar(false);
                   view_interface.checkConnection();
              }
          });
    }
}
