package ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgot_password.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;


public interface Provider_interface  {
    void getOtpResponse(String email, PresenterCallback presenterCallback);
    void OnDestroy();
}
