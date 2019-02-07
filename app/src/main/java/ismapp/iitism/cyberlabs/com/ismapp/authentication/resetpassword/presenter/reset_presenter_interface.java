package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.presenter;

public interface reset_presenter_interface {
    void sendResponse(String email, String Password, int otp) ;
    void onDestroy();

}
