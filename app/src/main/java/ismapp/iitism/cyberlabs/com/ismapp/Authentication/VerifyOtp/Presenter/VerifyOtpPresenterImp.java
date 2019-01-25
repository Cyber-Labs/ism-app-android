package ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Model.VerifyOtpModel;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Provider.VerifyOtpProvider;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.VerifyOtpCallback;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.View.VerifiOtpView;

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
        verifyOtpProvider.getOtpVerificationResponse(email, otp, new VerifyOtpCallback() {
            @Override
            public void getVerification(VerifyOtpModel verifyOtpModel) {
                verifiOtpView.showProgressBar(false);
                verifiOtpView.setIntent(verifyOtpModel);
            }

            @Override
            public void onFailure() {
                verifiOtpView.setIntent(new VerifyOtpModel("Unable to Connect Server!!!",false));

            }
        });


    }
}
