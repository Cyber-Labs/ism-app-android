package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.DeleteEventResponse;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Provider.AdmintEventListProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.View.AdminEventListFragmentInterface;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class AdmintEventListPresenterImp  implements AdmintEventListPresenterInterface{
    AdminEventListFragmentInterface adminEventListFragmentInterface;
    AdmintEventListProviderInterface admintEventListProviderInterface;
    int club_id;
    int event_id;

    public AdmintEventListPresenterImp(AdminEventListFragmentInterface adminEventListFragmentInterface, AdmintEventListProviderInterface admintEventListProviderInterface,int club_id,int event_id) {
        this.adminEventListFragmentInterface = adminEventListFragmentInterface;
        this.admintEventListProviderInterface = admintEventListProviderInterface;
        this.club_id=club_id;
        this.event_id=event_id;
    }

    @Override
    public void reqEventList(String access_token) {
        adminEventListFragmentInterface.showProgressBar(true);
        if(event_id==-1){
        admintEventListProviderInterface.requestEventList(access_token, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                adminEventListFragmentInterface.showProgressBar(false);
                adminEventListFragmentInterface.getList(((EventListResponse)o).getEvent_list());
            }

            @Override
            public void OnFailure(String msg) {
                adminEventListFragmentInterface.showProgressBar(false);
                adminEventListFragmentInterface.showMessage(msg);
            }
        },club_id);}
        else {
          admintEventListProviderInterface.requestDeleteEvent(access_token, new PresenterCallback() {
              @Override
              public void onSuccess(Object o) {
                  adminEventListFragmentInterface.showProgressBar(false);
                  adminEventListFragmentInterface.showMessage(((DeleteEventResponse)o).getMessage());
              }

              @Override
              public void OnFailure(String msg) {
                  adminEventListFragmentInterface.showProgressBar(false);
                  adminEventListFragmentInterface.showMessage(msg);
              }
          },event_id);
        }


    }




}
