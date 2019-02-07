package ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.View;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Model.VerifyOtpModel;

public interface VerifiOtpView {
    void showProgressBar(boolean show);
    void setIntent(VerifyOtpModel verifyOtpModel);
}
