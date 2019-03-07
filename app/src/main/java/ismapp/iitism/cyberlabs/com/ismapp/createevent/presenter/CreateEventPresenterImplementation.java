package ismapp.iitism.cyberlabs.com.ismapp.createevent.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.createevent.provider.CreateEventProviderImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.view.CreateEventFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import okhttp3.MultipartBody;

public class CreateEventPresenterImplementation implements CreateEventPresenterInterface {
    CreateEventFragmentInterface createEventFragmentInterface;
    CreateEventProviderImplementation createEventProviderImplementation;

    public CreateEventPresenterImplementation(CreateEventFragmentInterface createEventFragmentInterface, CreateEventProviderImplementation createEventProviderImplementation) {
        this.createEventFragmentInterface = createEventFragmentInterface;
        this.createEventProviderImplementation = createEventProviderImplementation;
    }

    @Override
    public void getCreateEventRequest(String access_token, int club_id, String title, String short_description, String description, String venue, String event_start_date, String event_end_date, MultipartBody.Part image) {
        createEventFragmentInterface.showProgressBar(true);
        createEventProviderImplementation.getCreateEventResponse(access_token, club_id,title, short_description, description, venue, event_start_date, event_end_date, image, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                createEventFragmentInterface.showProgressBar(false);
            }

            @Override
            public void OnFailure(String msg) {
             createEventFragmentInterface.showMessage(msg);
            }
        });
    }
}
