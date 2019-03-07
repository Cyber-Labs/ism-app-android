package ismapp.iitism.cyberlabs.com.ismapp.createevent.model;

public class CreateEventModel {
    private String message;
    private Boolean success;

    public CreateEventModel(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }
}
