package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;

public interface ClubDetailInterface {
    void showProgressbar(boolean show);
    void showmodel(ClubDetails clubDetails);
    void showMessage(String msg);
    void showMembList(MemberListResponse memberListResponse);
}
