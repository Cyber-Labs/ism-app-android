package ismapp.iitism.cyberlabs.com.ismapp.createevent.model;

public class CreateEventModel {
    private final String message;
    private final Boolean success;

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
