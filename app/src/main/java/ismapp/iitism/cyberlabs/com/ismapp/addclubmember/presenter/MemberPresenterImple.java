package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.member;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider.MemberProviderImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.view.MemberInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class MemberPresenterImple implements MemberPresenter {
  private   MemberInterface memberInterface;
    private MemberProviderImplementation memberProviderImplementation;

    public MemberPresenterImple(MemberInterface memberInterface, MemberProviderImplementation memberProviderImplementation) {
        this.memberInterface = memberInterface;
        this.memberProviderImplementation = memberProviderImplementation;
    }

    @Override
    public void getMemberResponse(String token, int clubid, String emailid, Boolean isadmin) {
        memberProviderImplementation.getMemberResponse(token, clubid, emailid, isadmin, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                memberInterface.getResult((member)o);
            }

            @Override
            public void OnFailure(String msg) {
                 memberInterface.showMessage(msg);
            }
        });
    }
}
