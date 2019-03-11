package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.ClubDetailsProvider;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view.ClubDetailsFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubDetailsPresenterImp implements ClubDetailsPresenterInterface {
    private ClubDetailsFragmentInterface clubDetailsFragmentInterface;

    public ClubDetailsPresenterImp(ClubDetailsFragmentInterface clubDetailsFragmentInterface, ClubDetailsProvider clubDetailsProvider) {
        this.clubDetailsFragmentInterface = clubDetailsFragmentInterface;
        this.clubDetailsProvider = clubDetailsProvider;
    }

    private ClubDetailsProvider clubDetailsProvider;

    @Override
    public void getclubdetail(String access_token, int id) {
        clubDetailsFragmentInterface.showProgressbar(true);
        clubDetailsProvider.getClubDetails(access_token, id, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                clubDetailsFragmentInterface.showProgressbar(false);
                clubDetailsFragmentInterface.showmodel((ClubDetailsModel) o);


            }

            @Override
            public void OnFailure(String msg) {
                clubDetailsFragmentInterface.showProgressbar(false);
                clubDetailsFragmentInterface.showMessage(msg);
            }
        });
    }

    @Override
    public void requestMemberList(String access_token, int id) {
        clubDetailsFragmentInterface.showProgressbar(true);
        clubDetailsProvider.requestMemberList(access_token, id, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                MemberListResponse memberListResponse = (MemberListResponse) o;
                clubDetailsFragmentInterface.showMemberList(memberListResponse);
            }

            @Override
            public void OnFailure(String msg) {
                clubDetailsFragmentInterface.showProgressbar(false);
                clubDetailsFragmentInterface.showMessage(msg);


            }
        });
    }
}
