package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.presenter;

import android.content.Context;
import android.util.Log;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.RemoveMember;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.provider.ManageMemberListProvider;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.view.ManageMemberViewInterface;

public class ManageMemberPresenter implements ManageMemberPresenterInterface {
  private final ManageMemberViewInterface manageMemberViewInterface;
   private final ManageMemberListProvider memberListProvider;
   private final Context context;

    public ManageMemberPresenter(Context context ,ManageMemberViewInterface manageMemberViewInterface, ManageMemberListProvider memberListProvider) {
        this.manageMemberViewInterface = manageMemberViewInterface;
        this.memberListProvider = memberListProvider;
        this.context = context;
    }

    @Override
    public void getMemberResponse(String access_token, int club_id) {
        manageMemberViewInterface.showProgressBar(true);
        memberListProvider.requestMemberList(access_token, club_id, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
               MemberListResponse memberListResponse = ((MemberListResponse)o);
                Log.e("perul", "onSuccess: " + memberListResponse.toString() );
                manageMemberViewInterface.getMemberList((MemberListResponse)o);
                manageMemberViewInterface.showProgressBar(false);
            }

            @Override
            public void OnFailure(String msg) {
                manageMemberViewInterface.showProgressBar(false);
                ViewUtils.showToast(context,msg);
            }
        });

    }

    @Override
    public void getRemoveResponse(String access_token, int club_id, String email_id) {
        memberListProvider.requestDeleteMember(access_token, club_id, email_id, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                RemoveMember removeMember = (RemoveMember)o;
                ViewUtils.showToast(context,removeMember.getMessage());
            }

            @Override
            public void OnFailure(String message) {
                ViewUtils.showToast(context,message);
            }
        });
    }
}
