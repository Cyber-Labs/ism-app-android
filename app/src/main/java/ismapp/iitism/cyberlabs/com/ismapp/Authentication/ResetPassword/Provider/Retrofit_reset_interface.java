package ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.resetcallback;

public interface Retrofit_reset_interface {
    void getSuccessResponse(String email, String Password, int otp, resetcallback resetcallback);
    void onDestroy();
}
