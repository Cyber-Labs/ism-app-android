package ismapp.iitism.cyberlabs.com.ismapp.club.addclubmember.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

interface MemberProvider {
    void getMemberResponse(String token, int clubid, String emailid, Boolean isadmin, PresenterCallback presenterCallback);
}
