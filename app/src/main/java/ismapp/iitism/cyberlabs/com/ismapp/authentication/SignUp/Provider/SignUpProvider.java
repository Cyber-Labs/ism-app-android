package ismapp.iitism.cyberlabs.com.ismapp.authentication.SignUp.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface SignUpProvider {
    void getSignUpResponse(String email, String name, String password, PresenterCallback callback);

}
