package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface ClubDetailInterface {
    void getclubdetailresponse(String access_token, int id, PresenterCallback presenterCallback);
}
