package ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Model.SignUpResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Provider.SignUpProvider;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.view.SignUpActivityInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class SignUpPresenterImp implements SignUpPresenter{
    private final SignUpActivityInterface signUpActivityInterface;
    private final SignUpProvider signUpProvider;
    private final String email;
    private final String name;
    private final String pass;

    public SignUpPresenterImp(SignUpActivityInterface signUpActivityInterface, SignUpProvider signUpProvider, String email, String name, String pass) {
        this.signUpActivityInterface = signUpActivityInterface;
        this.signUpProvider = signUpProvider;
        this.email = email;
        this.name = name;
        this.pass = pass;
    }

    @Override
    public void getSignUpResponse() {
        signUpActivityInterface.showProgressBar(true);
        signUpProvider.getSignUpResponse(email, name, pass, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                signUpActivityInterface.showProgressBar(false);
               SignUpResponseModel signUpResponseModel = (SignUpResponseModel)o;
                if(signUpResponseModel==null)
                    signUpActivityInterface.setIntent(new SignUpResponseModel(false,"Unable to Connect Server!!!"));
                else
                    signUpActivityInterface.setIntent(signUpResponseModel);

            }

            @Override
            public void OnFailure(String msg) {

            }
        });
            }


}
