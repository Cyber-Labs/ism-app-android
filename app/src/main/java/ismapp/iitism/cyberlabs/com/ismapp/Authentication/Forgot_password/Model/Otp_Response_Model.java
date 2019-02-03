package ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Model;

public class Otp_Response_Model  {


    private boolean success;

    public Otp_Response_Model(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    private String message;


}
