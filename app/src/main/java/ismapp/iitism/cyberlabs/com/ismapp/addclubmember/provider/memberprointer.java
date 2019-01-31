package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface memberprointer {
    void getresponse(String token, int clubid, String emailid, Boolean isadmin, PresenterCallback presenterCallback);
}
