package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter;

public interface ClubDetailsPresenterInterface {

    void getclubdetail(String access_token, int id);
    void requestMemberList(String access_token, int id);
}
