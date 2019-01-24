package ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Model.Otp_Response_Model;

public interface CallBack {
    //callback method from getting response from server
    void getVerification(Otp_Response_Model otp_response_model);
    void onFailure(String msg);


}
