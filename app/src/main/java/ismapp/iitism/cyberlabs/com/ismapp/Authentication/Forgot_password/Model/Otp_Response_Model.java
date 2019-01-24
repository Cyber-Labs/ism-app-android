package ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Model;

public class Otp_Response_Model  {

    public Otp_Response_Model(String success, String message) {
        this.success = success;
        this.message = message;
    }

    private String success;
    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
