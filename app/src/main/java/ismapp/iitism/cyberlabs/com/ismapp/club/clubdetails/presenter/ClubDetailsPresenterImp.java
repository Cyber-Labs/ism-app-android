package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.ClubDetailsProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view.ClubDetailsFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubDetailsPresenterImp implements ClubDetailsPresenterInterface {
    ClubDetailsFragmentInterface clubDetailsFragmentInterface;

    public ClubDetailsPresenterImp(ClubDetailsFragmentInterface clubDetailsFragmentInterface, ClubDetailsProviderImp clubDetailInterfaces) {
        this.clubDetailsFragmentInterface = clubDetailsFragmentInterface;
        this.clubDetailInterfaces = clubDetailInterfaces;
    }

    ClubDetailsProviderImp clubDetailInterfaces;

    @Override
    public void getclubdetail(String access_token, int id) {
        clubDetailsFragmentInterface.showProgressbar(true);
        clubDetailInterfaces.getclubdetailresponse(access_token, id, new PresenterCallback() {
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
    public void requestmemblist(String access_token,int id) {
        clubDetailsFragmentInterface.showProgressbar(true);
        clubDetailInterfaces.requestmemblist(access_token, id, new PresenterCallback() {
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
