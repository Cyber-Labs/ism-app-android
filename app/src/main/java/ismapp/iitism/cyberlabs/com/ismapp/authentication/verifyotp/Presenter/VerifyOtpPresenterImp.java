package ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Model.VerifyOtpModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Provider.VerifyOtpProvider;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.View.VerifiOtpView;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class VerifyOtpPresenterImp implements VerifyOtpPresenter {


    VerifiOtpView verifiOtpView;
    VerifyOtpProvider verifyOtpProvider;

    public VerifyOtpPresenterImp(VerifiOtpView verifiOtpView, VerifyOtpProvider verifyOtpProvider) {
        this.verifiOtpView = verifiOtpView;
        this.verifyOtpProvider = verifyOtpProvider;
    }

    @Override
    public void getOtpVerificationResponse(String email, int otp) {
        verifiOtpView.showProgressBar(true);
        verifyOtpProvider.getOtpVerificationResponse(email, otp, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                verifiOtpView.showProgressBar(false);
                verifiOtpView.setIntent((VerifyOtpModel) o);
            }

            @Override
            public void OnFailure(String msg) {
                verifiOtpView.setIntent(new VerifyOtpModel("Unable to Connect Server!!!",false));
            }
        } );


    }
}
