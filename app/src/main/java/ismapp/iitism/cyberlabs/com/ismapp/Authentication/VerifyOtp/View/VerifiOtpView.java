package ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.View;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Model.VerifyOtpModel;

public interface VerifiOtpView {
    void showProgressBar(boolean show);
    void setIntent(VerifyOtpModel verifyOtpModel);
}
