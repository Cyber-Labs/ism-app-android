package ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Presenter;

public interface reset_presenter_interface {
    void sendResponse(String email, String Password, int otp) ;
    void onDestroy();

}
