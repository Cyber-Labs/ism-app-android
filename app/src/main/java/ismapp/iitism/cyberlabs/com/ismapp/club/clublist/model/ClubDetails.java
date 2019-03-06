package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model;

public class ClubDetails {
   private int club_id;
   private String name;
   private String image_url;
    private String _tag_line;
    private int id;
    private  boolean is_admin;

    public ClubDetails(int club_id, String name, String image_url, String _tag_line, int id, boolean is_admin) {
        this.club_id = club_id;
        this.name = name;
        this.image_url = image_url;
        this._tag_line = _tag_line;
        this.id = id;
        this.is_admin = is_admin;
    }

    public int getClub_id() {
        return club_id;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String get_tag_line() {
        return _tag_line;
    }

    public int getId() {
        return id;
    }

    public boolean isIs_admin() {
        return is_admin;
    }
}
