package ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.CallBack;


public interface Provider_interface  {
    void getOtpResponse(String email, CallBack callBack);
    void OnDestroy();
}
