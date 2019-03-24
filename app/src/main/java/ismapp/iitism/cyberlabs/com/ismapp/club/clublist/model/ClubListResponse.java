package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model;

import java.util.List;

public class ClubListResponse {
    private boolean success;
    private String message;
    private List<ClubDetails> club_list;




    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ClubDetails> getClubsNameList() {
        return club_list;
    }
}
