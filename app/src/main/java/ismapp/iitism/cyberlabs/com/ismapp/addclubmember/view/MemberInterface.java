package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.view;

import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.Member;

public interface MemberInterface {
    void showProgressbar(Boolean show);
    void getResult(Member member);
    void buttonclick(Boolean clickable);
    void showmessage(String msg);
}
