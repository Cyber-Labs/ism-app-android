package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventListResponse {
    private String message;
    private boolean success;
    @SerializedName("club_list")
    private List<EventListModel> event_list;

    public EventListResponse(String message, boolean success, List<EventListModel> event_list) {
        this.message = message;
        this.success = success;
        this.event_list = event_list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<EventListModel> getEvent_list() {
        return event_list;
    }

    public void setEvent_list(List<EventListModel> event_list) {
        this.event_list = event_list;
    }
}
