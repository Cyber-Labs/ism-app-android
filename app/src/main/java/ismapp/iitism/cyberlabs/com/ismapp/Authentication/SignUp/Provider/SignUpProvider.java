package ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.SignUpCallBack;

public interface SignUpProvider {
    void getSignUpResponse(String email, String name,String password,SignUpCallBack signUpCallBack);

}
