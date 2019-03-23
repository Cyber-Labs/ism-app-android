package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Provider;

import android.support.v7.widget.CardView;
import android.util.Log;

import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.DeleteEventResponse;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.api.api;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.api.EventListApi;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdmintEventListProviderImp implements AdmintEventListProviderInterface {
    Call<EventListResponse> eventListResponseCall;
    Call<DeleteEventResponse> deleteEventResponseCall;


    @Override
    public void requestEventList(String access_token, PresenterCallback presenterCallback, int club_id) {
        api api= ApiClient.getRetrofit().create(api.class);
        eventListResponseCall=api.getEventList(access_token,club_id);
        eventListResponseCall.enqueue(new Callback<EventListResponse>() {
            @Override
            public void onResponse(Call<EventListResponse> call, Response<EventListResponse> response) {

                presenterCallback.onSuccess((EventListResponse)response.body());
            }

            @Override
            public void onFailure(Call<EventListResponse> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());

            }
        });

    }

    @Override
    public void requestDeleteEvent(String access_token, PresenterCallback presenterCallback, int event_id) {
        api api= ApiClient.getRetrofit().create(api.class);
        deleteEventResponseCall=api.deleteEvent(access_token,event_id);
        deleteEventResponseCall.enqueue(new Callback<DeleteEventResponse>() {
            @Override
            public void onResponse(Call<DeleteEventResponse> call, Response<DeleteEventResponse> response) {
               presenterCallback.onSuccess((DeleteEventResponse)response.body());


            }

            @Override
            public void onFailure(Call<DeleteEventResponse> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });

    }
}
