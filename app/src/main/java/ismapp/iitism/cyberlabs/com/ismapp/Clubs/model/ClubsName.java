package ismapp.iitism.cyberlabs.com.ismapp.Clubs.model;

public class ClubsName {
    String clubid;
    String name;
    String imageurl;
    String tagline;
    String id;

    public ClubsName(String clubid, String name, String imageurl, String tagline, String id) {
        this.clubid = clubid;
        this.name = name;
        this.imageurl = imageurl;
        this.tagline = tagline;
        this.id = id;
    }

    public String getClubid() {
        return clubid;
    }

    public void setClubid(String clubid) {
        this.clubid = clubid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
