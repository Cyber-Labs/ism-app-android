package ismapp.iitism.cyberlabs.com.ismapp.Clubs.model;

public class ClubsName {
    String clubid;
    String name;
    String imageurl;
    String tagline;

    public ClubsName(String clubid, String name, String imageurl, String tagline) {
        this.clubid = clubid;
        this.name = name;
        this.imageurl = imageurl;
        this.tagline = tagline;
    }

    public String getClubid() {
        return clubid;
    }

    public String getName() {
        return name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getTagline() {
        return tagline;
    }
}
