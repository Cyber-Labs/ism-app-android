package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.api;

import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.DeleteEventResponse;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static ismapp.iitism.cyberlabs.com.ismapp.helper.Urls.SUB_URL_CLUB_EVENT_LIST;
import static ismapp.iitism.cyberlabs.com.ismapp.helper.Urls.SUB_URL_DELETE_EVENT;
import static ismapp.iitism.cyberlabs.com.ismapp.helper.Urls.SUB_URL_EVENT_LIST;

public interface api {
    @GET(SUB_URL_CLUB_EVENT_LIST)
    Call<EventListResponse> getEventList(@Header("Authorization") String Token_access_token,@Query("club_id") int club_id);
    @FormUrlEncoded
    @GET(SUB_URL_DELETE_EVENT)
    Call<DeleteEventResponse> deleteEvent(@Header("Authorization") String access_token, @Query("event_id") int event_id);
}
