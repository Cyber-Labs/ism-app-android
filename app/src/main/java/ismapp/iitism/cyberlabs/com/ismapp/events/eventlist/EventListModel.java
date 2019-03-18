package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist;

public class EventListModel {

       private  int id;
       private  String club_name;
       private  String title;
       private  String short_desc;
       private  String description;
       private  String venue;
       private  String event_pic_url;
       private  String event_start_date;
       private  String event_end_date;

    public EventListModel(int id, String club_name, String title, String short_desc, String description, String venue, String event_pic_url, String event_start_date, String event_end_date) {
        this.id = id;
        this.club_name = club_name;
        this.title = title;
        this.short_desc = short_desc;
        this.description = description;
        this.venue = venue;
        this.event_pic_url = event_pic_url;
        this.event_start_date = event_start_date;
        this.event_end_date = event_end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEvent_pic_url() {
        return event_pic_url;
    }

    public void setEvent_pic_url(String event_pic_url) {
        this.event_pic_url = event_pic_url;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(String event_end_date) {
        this.event_end_date = event_end_date;
    }
}
