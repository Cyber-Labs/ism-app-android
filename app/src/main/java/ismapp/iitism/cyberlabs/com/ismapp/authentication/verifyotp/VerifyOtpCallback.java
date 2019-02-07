package ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp;

public interface VerifyOtpCallback<T>  {
    void getVerification(T t);
    void onFailure();
}
