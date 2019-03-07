package ismapp.iitism.cyberlabs.com.ismapp.createevent.api;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CreateEventApi {
    @POST(Urls.SUB_URL_CREATE_EVENT)
    Call<CreateEventApi> getcreateventresponse(@Header("Authorization") String Token_access_token,@Field("club_id") int clubid,@Field("title") String title,
                             @Field("short_desc")  String short_description,
                                                       @Field("description" )String description,
                                                       @Field("venue" ) String venue,
                                                      @Field("event_start_date") String event_start_date,
                                                       @Field("event_end_date") String event_end_date,
                                                        @Part MultipartBody.Part image);

}
