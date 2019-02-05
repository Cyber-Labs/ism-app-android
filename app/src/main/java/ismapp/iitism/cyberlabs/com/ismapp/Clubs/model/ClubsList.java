package ismapp.iitism.cyberlabs.com.ismapp.Clubs.model;

import java.util.List;

public class ClubsList {
    public  boolean success;
    public String message;
    public List<ClubsName> club_list;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ClubsName> getClubsNameList() {
        return club_list;
    }
}
