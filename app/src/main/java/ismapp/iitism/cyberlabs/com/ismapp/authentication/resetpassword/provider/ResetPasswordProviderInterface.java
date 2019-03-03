package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface ResetPasswordProviderInterface {
    void getSuccessResponse(String email, String Password, int otp, PresenterCallback callback);
    void onDestroy();
}
