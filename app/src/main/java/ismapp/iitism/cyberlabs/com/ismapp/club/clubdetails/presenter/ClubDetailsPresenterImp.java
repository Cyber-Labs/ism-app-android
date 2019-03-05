package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.ClubDetailsProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view.ClubDetailsFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubDetailsPresenterImp implements ClubDetailsPresenterInterface {
    private ClubDetailsFragmentInterface clubDetailsFragmentInterface;

    public ClubDetailsPresenterImp(ClubDetailsFragmentInterface clubDetailsFragmentInterface, ClubDetailsProviderImp clubDetailsProviderImp) {
        this.clubDetailsFragmentInterface = clubDetailsFragmentInterface;
        this.clubDetailsProviderImp = clubDetailsProviderImp;
    }

    private ClubDetailsProviderImp clubDetailsProviderImp;

    @Override
    public void getclubdetail(String access_token, int id) {
        clubDetailsFragmentInterface.showProgressbar(true);
        clubDetailsProviderImp.getclubdetailresponse(access_token, id, new PresenterCallback() {
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
        clubDetailsProviderImp.requestmemblist(access_token, id, new PresenterCallback() {
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
