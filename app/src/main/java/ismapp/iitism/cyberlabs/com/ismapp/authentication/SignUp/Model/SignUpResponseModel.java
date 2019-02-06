package ismapp.iitism.cyberlabs.com.ismapp.authentication.SignUp.Model;

public class SignUpResponseModel {


    private boolean success;
    private String message;
    public SignUpResponseModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
