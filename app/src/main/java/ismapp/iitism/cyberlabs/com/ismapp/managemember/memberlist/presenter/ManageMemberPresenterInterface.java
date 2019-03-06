package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.presenter;

public interface ManageMemberPresenterInterface {
     void getMemberResponse(String access_token, int club_id);
     void getRemoveResponse(String access_token, int club_id,String email_id);
}
