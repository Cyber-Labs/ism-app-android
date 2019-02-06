package ismapp.iitism.cyberlabs.com.ismapp.Clubs.model;

public class ClubsName {
    int clubid;
    String name;
    String imageurl;
    String tagline;
    int id;

    public ClubsName(int clubid, String name, String imageurl, String tagline, int id) {
        this.clubid = clubid;
        this.name = name;
        this.imageurl = imageurl;
        this.tagline = tagline;
        this.id = id;
    }

    public int getClubid() {
        return clubid;
    }

    public void setClubid(int clubid) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
