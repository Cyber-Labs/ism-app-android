package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.view;

import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.RemoveMember;

public interface ManageMemberViewInterface {
    void showProgressBar(boolean show);
    void getMemberList(MemberListResponse memberListResponse);
    void getRemoveMemberResponse(RemoveMember removeMember);
}
