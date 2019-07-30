package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.View;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.model.EventListModel;

public interface AdminEventListFragmentInterface {
    void getList(List<EventListModel> eventListModels);
    void showMessage(String message);
    void showProgressBar(boolean show);
}
