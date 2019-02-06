package ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Presenter;

public interface reset_presenter_interface {
    void sendResponse(String email, String Password, int otp) ;
    void onDestroy();

}
