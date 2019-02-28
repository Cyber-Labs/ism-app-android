package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.presenter;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.provider.ProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view.ClubInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class PresenterImpl implements PresenterInterface {
    ClubInterface clubInterface;
    ProviderInterface providerInterface;

    public PresenterImpl(ClubInterface clubInterface, ProviderInterface providerInterface) {
        this.clubInterface = clubInterface;
        this.providerInterface = providerInterface;
    }


    @Override
    public void requestclublist(String access_token) {
        clubInterface.showProgressBar(true);
        providerInterface.requestclubslist( access_token,new PresenterCallback() {

            @Override
            public void onSuccess(Object o) {
                clubInterface.showProgressBar(false);

                ClubListResponse clubListResponse = (ClubListResponse) o;
               List<ClubDetails> clubDetails = clubListResponse.getClubsNameList();
               clubInterface.getList(clubDetails);
            }

            @Override
            public void OnFailure(String msg) {
                clubInterface.showMessage(msg);
                clubInterface.showProgressBar(false);


            }
        });
    }



}
