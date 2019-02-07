package ismapp.iitism.cyberlabs.com.ismapp.authentication.signup;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Model.SignUpResponseModel;

public interface SignUpCallBack {
    //callback method from getting response from server
    void getVerification(SignUpResponseModel signUpResponseModel);


}


