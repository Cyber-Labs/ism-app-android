package ismapp.iitism.cyberlabs.com.ismapp.club.addclubmember.presenter;

public interface MemberPresenter{
        void getMemberResponse(String token, int clubid, String emailid, Boolean isadmin);
}
