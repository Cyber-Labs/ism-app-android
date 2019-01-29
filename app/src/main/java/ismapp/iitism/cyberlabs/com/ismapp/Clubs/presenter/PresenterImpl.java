package ismapp.iitism.cyberlabs.com.ismapp.Clubs.presenter;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.Provider.ProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.view.ClubInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class PresenterImpl implements PresenterInterface {
    ClubInterface clubInterface;
    ProviderInterface providerInterface;

    public PresenterImpl(ClubInterface clubInterface, ProviderInterface providerInterface) {
        this.clubInterface = clubInterface;
        this.providerInterface = providerInterface;
    }

    @Override
    public void requestclublist() {
        clubInterface.ShowProgressBar(true);
        providerInterface.requestclubslist(new PresenterCallback() {

            @Override
            public void onSuccess(Object o) {
                clubInterface.ShowProgressBar(false);
                clubInterface.getlist((List<ClubsList>) o);
            }

            @Override
            public void OnFailure(String msg) {
                clubInterface.showMessage(msg);
            }
        });
    }
}
