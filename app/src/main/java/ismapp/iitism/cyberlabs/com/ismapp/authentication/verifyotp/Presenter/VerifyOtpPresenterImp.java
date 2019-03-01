package ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Model.VerifyOtpModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Provider.VerifyOtpProvider;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.View.VerifyOtpActivityInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class VerifyOtpPresenterImp implements VerifyOtpPresenter {


    VerifyOtpActivityInterface verifyOtpActivityInterface;
    VerifyOtpProvider verifyOtpProvider;

    public VerifyOtpPresenterImp(VerifyOtpActivityInterface verifyOtpActivityInterface, VerifyOtpProvider verifyOtpProvider) {
        this.verifyOtpActivityInterface = verifyOtpActivityInterface;
        this.verifyOtpProvider = verifyOtpProvider;
    }

    @Override
    public void getOtpVerificationResponse(String email, int otp) {
        verifyOtpActivityInterface.showProgressBar(true);
        verifyOtpProvider.getOtpVerificationResponse(email, otp, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                verifyOtpActivityInterface.showProgressBar(false);
                verifyOtpActivityInterface.setIntent((VerifyOtpModel) o);
            }

            @Override
            public void OnFailure(String msg) {
                verifyOtpActivityInterface.setIntent(new VerifyOtpModel("Unable to Connect Server!!!",false));
            }
        } );


    }
}
