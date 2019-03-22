package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface AdmintEventListProviderInterface {
    void requestEventList(String access_token, PresenterCallback presenterCallback,int club_id);
    void requestDeleteEvent(String access_token, PresenterCallback presenterCallback,int event_id );
}
