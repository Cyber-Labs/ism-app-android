package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model;

public class ClubDetails {
    String success ;
    String message ;
    int id ;
    String name ;
    String image_url ;
    String tagline ;
    String fb_link;
    String description;
    Boolean is_admin;

    public ClubDetails(String success, String message, int id, String name, String image_url, String tagline, String fb_link, String description, Boolean is_admin) {
        this.success = success;
        this.message = message;
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.tagline = tagline;
        this.fb_link = fb_link;
        this.description = description;
        this.is_admin = is_admin;
    }

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
