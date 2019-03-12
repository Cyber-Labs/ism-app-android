package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.api;

import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListResponse;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;

import static ismapp.iitism.cyberlabs.com.ismapp.helper.Urls.SUB_URL_EVENT_LIST;

public interface EventListApi {
    @FormUrlEncoded
    @GET(SUB_URL_EVENT_LIST)
    Call<EventListResponse> getEventList(@Header("Authorization") String Token_access_token);
}
