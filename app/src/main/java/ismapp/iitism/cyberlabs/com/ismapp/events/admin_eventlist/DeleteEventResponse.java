package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist;

public class DeleteEventResponse {
    String success;
    String message;

    public DeleteEventResponse(String success, String message) {
        this.success = success;
        this.message = message;
    }

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
