package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface EventListProviderInterface {
    void requestEventList(String access_token, PresenterCallback presenterCallback);
}
