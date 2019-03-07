package ismapp.iitism.cyberlabs.com.ismapp.createevent.provider;

import ismapp.iitism.cyberlabs.com.ismapp.createevent.api.CreateEventApi;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.model.CreateEventModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventProviderImplementation implements CreateEventProviderInterface {
     Call<CreateEventApi> createEventApiCall;
    @Override
    public void getCreateEventResponse(String access_token, int club_id, String title, String short_description, String description, String venue, String event_start_date, String event_end_date, MultipartBody.Part image, final PresenterCallback presenterCallback) {
      CreateEventApi createEventApi = ApiClient.getRetrofit().create(CreateEventApi.class);

       createEventApiCall = createEventApi.getcreateventresponse(access_token,club_id,title,short_description,description,venue,event_start_date,event_end_date,image);
       createEventApiCall.enqueue(new Callback<CreateEventApi>() {
           @Override
           public void onResponse(Call<CreateEventApi> call, Response<CreateEventApi> response) {
               presenterCallback.onSuccess((CreateEventModel)response.body());
           }

           @Override
           public void onFailure(Call<CreateEventApi> call, Throwable t) {
               presenterCallback.OnFailure(t.getMessage());
           }
       });
    }

}
