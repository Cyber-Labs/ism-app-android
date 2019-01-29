package ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.SignUpCallBack;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface SignUpProvider {
    void getSignUpResponse(String email, String name, String password, PresenterCallback callback);

}
