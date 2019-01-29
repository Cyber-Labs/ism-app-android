package ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Provider;


import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.VerifyOtpCallback;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface VerifyOtpProvider {
    void getOtpVerificationResponse(String email, int otp, PresenterCallback callback);
}
