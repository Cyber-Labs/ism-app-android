package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.provider;

import android.util.Log;

import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.api.EventListApi;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListProviderImp implements EventListProviderInterface {

    Call<EventListResponse> eventListResponseCall;
    @Override
    public void requestEventList(String access_token, final PresenterCallback presenterCallback) {
        EventListApi eventListApi= ApiClient.getRetrofit().create(EventListApi.class);
        eventListResponseCall=eventListApi.getEventList(access_token);
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
}
