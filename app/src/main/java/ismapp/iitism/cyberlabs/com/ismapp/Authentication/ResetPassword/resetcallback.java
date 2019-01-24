package ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Model.NewPassword;

public interface resetcallback {
    void getResponse(NewPassword newPassword);
    void OnFailure(String msg);
}
