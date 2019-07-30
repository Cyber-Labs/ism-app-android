package ismapp.iitism.cyberlabs.com.ismapp.events.createevent.view;

import ismapp.iitism.cyberlabs.com.ismapp.events.createevent.model.CreateEventModel;

public interface CreateEventFragmentInterface {
    void showProgressBar(Boolean show);
    void showResponse(CreateEventModel createEventModel);
    void showButtonClickable(Boolean showButton);
    void showMessage(String message);
    void getUserResponse();

}
