package ismapp.iitism.cyberlabs.com.ismapp.clubdetails.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface ClubDetailInterface {
    void getclubdetailresponse(String access_token, String id, PresenterCallback presenterCallback);
}
