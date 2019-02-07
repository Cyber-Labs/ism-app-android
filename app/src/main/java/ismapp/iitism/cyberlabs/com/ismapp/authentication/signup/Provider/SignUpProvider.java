package ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface SignUpProvider {
    void getSignUpResponse(String email, String name, String password, PresenterCallback callback);

}
