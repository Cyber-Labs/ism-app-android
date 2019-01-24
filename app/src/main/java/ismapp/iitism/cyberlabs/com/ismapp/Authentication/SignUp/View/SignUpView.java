package ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.View;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Model.SignUpResponseModel;

public interface SignUpView {
    void showProgressBar(boolean show);
    void setIntent(SignUpResponseModel signUpResponseModel);

}
