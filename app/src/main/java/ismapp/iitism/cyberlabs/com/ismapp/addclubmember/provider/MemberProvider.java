package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface MemberProvider {
    void getMemberResponse(String token, int clubid, String emailid, Boolean isadmin, PresenterCallback presenterCallback);
}
