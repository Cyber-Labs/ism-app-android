package ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Model;

public class VerifyOtpModel {
    private boolean success;
    private String message;


    public VerifyOtpModel(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
