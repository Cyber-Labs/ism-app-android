package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.member;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider.memberprointer;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.view.MemberInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class MemberPresenterImple implements MemberPresenter {
    MemberInterface memberInterface;
    memberprointer memberprointer;
    @Override
    public void getresponse(String token, int clubid, String emailid, Boolean isadmin) {
        memberprointer.getresponse(token, clubid, emailid, isadmin, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                memberInterface.getResult((member)o);
            }

            @Override
            public void OnFailure(String msg) {
                 memberInterface.showmessage(msg);
            }
        });
    }
}
