package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.model;

public class ResetPassword {

    private boolean success;
    private String message;
    public ResetPassword(boolean success, String message) {
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
