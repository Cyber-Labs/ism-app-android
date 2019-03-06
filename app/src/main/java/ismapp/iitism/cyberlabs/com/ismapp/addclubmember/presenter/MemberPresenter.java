package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface MemberPresenter{
        void getMemberResponse(String token, int clubid, String emailid, Boolean isadmin);
}
