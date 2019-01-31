package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model;

public class member {
    public Boolean success;
    public String message;

    public member(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
