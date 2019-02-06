package ismapp.iitism.cyberlabs.com.ismapp.authentication.SignUp;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.SignUp.Model.SignUpResponseModel;

public interface SignUpCallBack {
    //callback method from getting response from server
    void getVerification(SignUpResponseModel signUpResponseModel);


}


