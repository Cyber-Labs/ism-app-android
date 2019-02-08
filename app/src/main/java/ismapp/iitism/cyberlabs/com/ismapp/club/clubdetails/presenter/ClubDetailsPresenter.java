package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.Member;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view.ClubDetailInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubDetailsPresenter implements ClubPresenInter {
    ClubDetailInterface clubDetailInterface;

    public ClubDetailsPresenter(ClubDetailInterface clubDetailInterface, ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.ClubDetailInterface clubDetailInterfaces) {
        this.clubDetailInterface = clubDetailInterface;
        this.clubDetailInterfaces = clubDetailInterfaces;
    }

    ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.ClubDetailInterface clubDetailInterfaces;

    @Override
    public void getclubdetail(String access_token, int id) {
        clubDetailInterface.showProgressbar(true);
        clubDetailInterfaces.getclubdetailresponse(access_token, id, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                clubDetailInterface.showProgressbar(false);
                clubDetailInterface.showmodel((ClubDetails) o);


            }

            @Override
            public void OnFailure(String msg) {
                clubDetailInterface.showProgressbar(false);
                clubDetailInterface.showMessage(msg);
            }
        });
    }

    @Override
    public void requestmemblist(String access_token,int id) {
        clubDetailInterface.showProgressbar(true);
        clubDetailInterfaces.requestmemblist(access_token, id, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                MemberListResponse memberListResponse = (MemberListResponse) o;
                clubDetailInterface.showMembList(memberListResponse);
            }

            @Override
            public void OnFailure(String msg) {
                clubDetailInterface.showProgressbar(false);
                clubDetailInterface.showMessage(msg);


            }
        });
    }
}
