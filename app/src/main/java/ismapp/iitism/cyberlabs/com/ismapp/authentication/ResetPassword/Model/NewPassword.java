package ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Model;

public class NewPassword {

    private boolean success;
    private String message;
    public NewPassword(boolean success, String message) {
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
