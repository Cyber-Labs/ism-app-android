package ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Model.VerifyOtpModel;

public interface VerifyOtpCallback<T>  {
    void getVerification(T t);
    void onFailure();
}
