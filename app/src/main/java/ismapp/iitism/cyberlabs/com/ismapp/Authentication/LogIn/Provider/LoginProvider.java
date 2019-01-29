package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface LoginProvider {
    void getLoginResponse(String email, String password, PresenterCallback callback);
}
