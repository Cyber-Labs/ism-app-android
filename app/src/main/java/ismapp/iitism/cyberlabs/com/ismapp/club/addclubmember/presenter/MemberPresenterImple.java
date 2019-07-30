package ismapp.iitism.cyberlabs.com.ismapp.club.addclubmember.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.club.addclubmember.model.Member;
import ismapp.iitism.cyberlabs.com.ismapp.club.addclubmember.provider.RetroMember;
import ismapp.iitism.cyberlabs.com.ismapp.club.addclubmember.view.MemberInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class MemberPresenterImple implements MemberPresenter {
    private final MemberInterface memberInterface;
    private final RetroMember retroMember;

    public MemberPresenterImple(MemberInterface memberInterface, RetroMember retroMember) {
        this.memberInterface = memberInterface;
        this.retroMember = retroMember;
    }




    @Override
    public void getMemberResponse(String token, int clubid, String emailid, Boolean isadmin) {
        retroMember.getMemberResponse(token, clubid, emailid, isadmin, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                memberInterface.getResult((Member)o);
            }

            @Override
            public void OnFailure(String msg) {
                memberInterface.showmessage(msg);
            }
        });

    }
}
