package ismapp.iitism.cyberlabs.com.ismapp.authentication.VerifyOtp;

public interface VerifyOtpCallback<T>  {
    void getVerification(T t);
    void onFailure();
}
