package ismapp.iitism.cyberlabs.com.ismapp.authentication.SignUp.View;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.SignUp.Model.SignUpResponseModel;

public interface SignUpView {
    void showProgressBar(boolean show);
    void setIntent(SignUpResponseModel signUpResponseModel);

}
