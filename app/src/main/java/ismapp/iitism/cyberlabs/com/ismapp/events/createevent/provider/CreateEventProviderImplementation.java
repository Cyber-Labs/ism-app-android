package ismapp.iitism.cyberlabs.com.ismapp.events.createevent.provider;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ismapp.iitism.cyberlabs.com.ismapp.events.createevent.api.CreateEventApi;
import ismapp.iitism.cyberlabs.com.ismapp.events.createevent.model.CreateEventModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventProviderImplementation implements CreateEventProviderInterface {
     private Call<CreateEventModel> createEventApiCall;
    @Override
    public void getCreateEventResponse(String access_token, int club_id, String title,  String description, String venue, String event_start_date, String event_end_date, MultipartBody.Part image, final PresenterCallback presenterCallback,int event_id) {
      CreateEventApi createEventApi = ApiClient.getRetrofit().create(CreateEventApi.class);
        Map<String, String> token = new HashMap<>();
        token.put("Authorization", access_token);


       createEventApiCall = createEventApi.getcreateventresponse(token,club_id,title,"",description,venue,event_start_date,event_end_date,image);

        String StartDate = "";
        for(int i=0;i<event_start_date.length();i++)
        {
            if(event_start_date.charAt(i) == '/'){
                StartDate+='-';
            }
            else StartDate+=event_start_date.charAt(i);
        }


        String EndDate="" ;
         if(event_end_date.isEmpty())
             EndDate=StartDate;
         else
             for(int i=0;i<event_end_date.length();i++)
             {
                 if(event_end_date.charAt(i) == '/'){
                     EndDate+='-';
                 }
                 else EndDate+=event_end_date.charAt(i);
             }
        Log.e("aman", "getCreateEventResponse: "+event_id+description+StartDate+EndDate );
             if(event_id==0)
       createEventApiCall = createEventApi.getcreateventresponse(token,club_id,title,"",description,venue,StartDate,EndDate,image);

            else
             createEventApiCall = createEventApi.getediteventresponse(token,event_id,club_id,title,"",description,venue,StartDate,EndDate,image);

       createEventApiCall.enqueue(new Callback<CreateEventModel>() {
           @Override
           public void onResponse(Call<CreateEventModel> call, Response<CreateEventModel> response) {
               presenterCallback.onSuccess((CreateEventModel)response.body());
           }

           @Override
           public void onFailure(Call<CreateEventModel> call, Throwable t) {
               presenterCallback.OnFailure(t.getMessage());
           }
       });
    }

}
