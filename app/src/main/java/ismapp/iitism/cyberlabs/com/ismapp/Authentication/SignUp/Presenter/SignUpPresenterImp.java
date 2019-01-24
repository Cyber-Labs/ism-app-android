package ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Model.SignUpResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Provider.SignUpProvider;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.SignUpCallBack;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.View.SignUpView;

public class SignUpPresenterImp implements SignUpPresenter{
    private SignUpView signUpView;
    private SignUpProvider signUpProvider;
    private String email;
    private String name;
    private String pass;

    public SignUpPresenterImp(SignUpView signUpView, SignUpProvider signUpProvider, String email, String name, String pass) {
        this.signUpView = signUpView;
        this.signUpProvider = signUpProvider;
        this.email = email;
        this.name = name;
        this.pass = pass;
    }

    @Override
    public void getSignUpResponse() {
        signUpView.showProgressBar(true);
        signUpProvider.getSignUpResponse(email, name, pass, new SignUpCallBack() {
            @Override
            public void getVerification(SignUpResponseModel signUpResponseModel) {
                signUpView.showProgressBar(false);
                if(signUpResponseModel==null)
                    signUpView.setIntent(new SignUpResponseModel(false,"Unable to Connect Server!!!"));
                else
                    signUpView.setIntent(signUpResponseModel);

            }
        });

    }
}
