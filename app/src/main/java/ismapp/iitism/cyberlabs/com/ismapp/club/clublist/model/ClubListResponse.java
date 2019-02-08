package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.Member;

public class ClubListResponse {
    public  boolean success;
    public String message;
    public List<ClubDetails> club_list;




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
