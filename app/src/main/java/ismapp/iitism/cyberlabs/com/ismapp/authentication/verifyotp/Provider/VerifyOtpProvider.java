package ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Provider;


import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface VerifyOtpProvider {
    void getOtpVerificationResponse(String email, int otp, PresenterCallback callback);
}
