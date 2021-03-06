package ismapp.iitism.cyberlabs.com.ismapp.events.createevent.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.events.createevent.model.CreateEventModel;
import ismapp.iitism.cyberlabs.com.ismapp.events.createevent.provider.CreateEventProviderImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.events.createevent.view.CreateEventFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import okhttp3.MultipartBody;

public class CreateEventPresenterImplementation implements CreateEventPresenterInterface {
    private final CreateEventFragmentInterface createEventFragmentInterface;
    private final CreateEventProviderImplementation createEventProviderImplementation;

    public CreateEventPresenterImplementation(CreateEventFragmentInterface createEventFragmentInterface, CreateEventProviderImplementation createEventProviderImplementation) {
        this.createEventFragmentInterface = createEventFragmentInterface;
        this.createEventProviderImplementation = createEventProviderImplementation;
    }

    @Override
    public void getCreateEventRequest(String access_token, int club_id, String title,  String description, String venue, String event_start_date, String event_end_date, MultipartBody.Part image,int event_id) {
        createEventFragmentInterface.showProgressBar(true);
        createEventFragmentInterface.showProgressBar(true);
        createEventProviderImplementation.getCreateEventResponse(access_token, club_id,title,  description, venue, event_start_date, event_end_date, image, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                createEventFragmentInterface.showProgressBar(false);
                createEventFragmentInterface.showMessage(((CreateEventModel)o).getMessage());
            }

            @Override
            public void OnFailure(String msg) {
             createEventFragmentInterface.showMessage(msg);
             createEventFragmentInterface.showProgressBar(false);
            }
        },event_id);
    }
}
