package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;

public interface ClubDetailsFragmentInterface {
    void showProgressbar(boolean show);
    void showmodel(ClubDetailsModel clubDetailsModel);
    void showMessage(String msg);
    void showMemberList(MemberListResponse memberListResponse);
}
