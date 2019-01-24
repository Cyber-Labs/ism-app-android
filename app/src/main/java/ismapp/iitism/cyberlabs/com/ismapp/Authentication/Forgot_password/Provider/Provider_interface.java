package ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.SignUpCallBack;

public interface Provider_interface  {
    void getOtpResponse(String email, SignUpCallBack signUpCallBack);
    void OnDestroy();
}
