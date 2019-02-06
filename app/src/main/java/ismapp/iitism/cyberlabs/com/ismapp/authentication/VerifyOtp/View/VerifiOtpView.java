package ismapp.iitism.cyberlabs.com.ismapp.authentication.VerifyOtp.View;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.VerifyOtp.Model.VerifyOtpModel;

public interface VerifiOtpView {
    void showProgressBar(boolean show);
    void setIntent(VerifyOtpModel verifyOtpModel);
}
