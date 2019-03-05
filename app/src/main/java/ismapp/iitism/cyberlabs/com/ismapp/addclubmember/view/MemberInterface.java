package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.view;

import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.member;

public interface MemberInterface {
    void showProgressbar(Boolean show);
    void getResult(member member);
    void buttonClick(Boolean clickable);
    void showMessage(String msg);
}
