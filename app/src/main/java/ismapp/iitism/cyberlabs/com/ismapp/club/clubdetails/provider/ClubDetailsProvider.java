package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface ClubDetailsProvider {
    void getClubDetails(String access_token, int id, PresenterCallback presenterCallback);
    void requestMemberList(String access_token, int id, PresenterCallback presenterCallback);
}
