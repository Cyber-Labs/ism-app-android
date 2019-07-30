package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.model.EventListModel;

public interface EventListFragmentInterface {
    void getList(List<EventListModel> eventListModels);
    void showMessage(String message);
    void showProgressBar(boolean show);
}
