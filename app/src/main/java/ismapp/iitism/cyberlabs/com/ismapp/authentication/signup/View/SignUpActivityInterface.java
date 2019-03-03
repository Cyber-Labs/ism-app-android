package ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.View;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Model.SignUpResponseModel;

public interface SignUpActivityInterface {
    void showProgressBar(boolean show);
    void setIntent(SignUpResponseModel signUpResponseModel);

}
