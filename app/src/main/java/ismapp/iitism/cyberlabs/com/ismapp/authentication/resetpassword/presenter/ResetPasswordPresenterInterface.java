package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.presenter;

public interface ResetPasswordPresenterInterface {
    void sendResponse(String email, String Password, int otp) ;
    void onDestroy();

}
