package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view.ClubDetailsView;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubDetailsPresenter implements ClubPresenInter {
    ClubDetailsView clubDetailsView;

    public ClubDetailsPresenter(ClubDetailsView clubDetailsView, ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.ClubDetailInterface clubDetailInterfaces) {
        this.clubDetailsView = clubDetailsView;
        this.clubDetailInterfaces = clubDetailInterfaces;
    }

    ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.ClubDetailInterface clubDetailInterfaces;

    @Override
    public void getclubdetail(String access_token, int id) {
        clubDetailsView.showProgressbar(true);
        clubDetailInterfaces.getclubdetailresponse(access_token, id, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                clubDetailsView.showProgressbar(false);
                clubDetailsView.showmodel((ClubDetails) o);


            }

            @Override
            public void OnFailure(String msg) {
                clubDetailsView.showProgressbar(false);
                clubDetailsView.showMessage(msg);
            }
        });
    }

    @Override
    public void requestmemblist(String access_token,int id) {
        clubDetailsView.showProgressbar(true);
        clubDetailInterfaces.requestmemblist(access_token, id, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                MemberListResponse memberListResponse = (MemberListResponse) o;
                clubDetailsView.showMemberList(memberListResponse);
            }

            @Override
            public void OnFailure(String msg) {
                clubDetailsView.showProgressbar(false);
                clubDetailsView.showMessage(msg);


            }
        });
    }
}
