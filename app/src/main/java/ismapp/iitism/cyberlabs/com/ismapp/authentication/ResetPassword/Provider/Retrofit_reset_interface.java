package ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface Retrofit_reset_interface {
    void getSuccessResponse(String email, String Password, int otp, PresenterCallback callback);
    void onDestroy();
}
