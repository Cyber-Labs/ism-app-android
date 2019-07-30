package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.model;

public class DeleteEventResponse {
    private boolean success;
    private String message;

    public DeleteEventResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public boolean isSuccess() {
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
