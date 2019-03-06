package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface ManageMemberProviderInterface {
     void requestMemberList(String access_token,int club_id, PresenterCallback presenterCallback);
     void requestDeleteMember(String access_token,int club_id,String email_id,PresenterCallback presenterCallback);
}
