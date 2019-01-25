package ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Provider;


import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.VerifyOtpCallback;

public interface VerifyOtpProvider {
    void getOtpVerificationResponse(String email, int otp, VerifyOtpCallback verifyOtpCallback);
}
