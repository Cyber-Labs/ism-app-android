package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.provider.EventListProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view.EventListFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class EventListPresenterImp implements EventListPresenterInterface {
    private final EventListFragmentInterface eventListFragmentInterface;
    private final EventListProviderInterface eventListProviderInterface;

    public EventListPresenterImp(EventListFragmentInterface eventListFragmentInterface, EventListProviderInterface eventListProviderInterface) {
        this.eventListFragmentInterface = eventListFragmentInterface;
        this.eventListProviderInterface = eventListProviderInterface;
    }

    @Override
    public void reqEventList(String access_token) {
        eventListFragmentInterface.showProgressBar(true);
        eventListProviderInterface.requestEventList(access_token, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                eventListFragmentInterface.showProgressBar(false);
                eventListFragmentInterface.getList(((EventListResponse)o).getEvent_list());
            }

            @Override
            public void OnFailure(String msg) {
                eventListFragmentInterface.showProgressBar(false);
                eventListFragmentInterface.showMessage(msg);
            }
        });

    }
}
