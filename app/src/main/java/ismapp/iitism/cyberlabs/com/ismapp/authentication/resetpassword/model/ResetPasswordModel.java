package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.model;

public class ResetPasswordModel {

    private boolean success;
    private String message;
    public ResetPasswordModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }


}
