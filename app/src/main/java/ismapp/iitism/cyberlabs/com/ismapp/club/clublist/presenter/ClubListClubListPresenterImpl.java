package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.presenter;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.provider.ClubListProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view.ClubFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubListClubListPresenterImpl implements ClubListPresenterInterface {
    ClubFragmentInterface clubFragmentInterface;
    ClubListProviderInterface clubListProviderInterface;

    public ClubListClubListPresenterImpl(ClubFragmentInterface clubFragmentInterface, ClubListProviderInterface clubListProviderInterface) {
        this.clubFragmentInterface = clubFragmentInterface;
        this.clubListProviderInterface = clubListProviderInterface;
    }


    @Override
    public void requestclublist(String access_token) {
        clubFragmentInterface.showProgressBar(true);
        clubListProviderInterface.requestclubslist( access_token,new PresenterCallback() {

            @Override
            public void onSuccess(Object o) {
                clubFragmentInterface.showProgressBar(false);

                ClubListResponse clubListResponse = (ClubListResponse) o;
               List<ClubDetails> clubDetails = clubListResponse.getClubsNameList();
               clubFragmentInterface.getList(clubDetails);
            }

            @Override
            public void OnFailure(String msg) {
                clubFragmentInterface.showMessage(msg);
                clubFragmentInterface.showProgressBar(false);


            }
        });
    }



}
