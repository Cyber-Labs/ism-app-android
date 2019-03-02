package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.presenter;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.provider.ClubListProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view.ClubListFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubListPresenterImpl implements ClubListPresenterInterface {
    ClubListFragmentInterface clubListFragmentInterface;
    ClubListProviderInterface clubListProviderInterface;

    public ClubListPresenterImpl(ClubListFragmentInterface clubListFragmentInterface, ClubListProviderInterface clubListProviderInterface) {
        this.clubListFragmentInterface = clubListFragmentInterface;
        this.clubListProviderInterface = clubListProviderInterface;
    }


    @Override
    public void requestclublist(String access_token) {
        clubListFragmentInterface.showProgressBar(true);
        clubListProviderInterface.requestclubslist( access_token,new PresenterCallback() {

            @Override
            public void onSuccess(Object o) {
                clubListFragmentInterface.showProgressBar(false);

                ClubListResponse clubListResponse = (ClubListResponse) o;
               List<ClubDetails> clubDetails = clubListResponse.getClubsNameList();
               clubListFragmentInterface.getList(clubDetails);
            }

            @Override
            public void OnFailure(String msg) {
                clubListFragmentInterface.showMessage(msg);
                clubListFragmentInterface.showProgressBar(false);


            }
        });
    }



}
