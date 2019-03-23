package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model;

public class ClubDetailsModel {
    private String success ;
    private String message ;
    private int id ;
    private String name ;
    private String image_url ;
    private String tagline ;
    private String description;
    private String fb_link;
    private Boolean is_admin;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getTagline() {
        return tagline;
    }

    public String getFb_link() {
        return fb_link;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }
}
