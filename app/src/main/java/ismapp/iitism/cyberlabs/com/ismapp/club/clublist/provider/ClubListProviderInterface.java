package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.provider;

import android.content.Context;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface ClubListProviderInterface {
    void requestclubslist(Context mcontext, String access_token, PresenterCallback presenterCallback);

}
