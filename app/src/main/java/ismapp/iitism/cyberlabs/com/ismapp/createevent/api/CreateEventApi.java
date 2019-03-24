package ismapp.iitism.cyberlabs.com.ismapp.createevent.api;

import java.util.Map;

import ismapp.iitism.cyberlabs.com.ismapp.createevent.model.CreateEventModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CreateEventApi {
    @Multipart
    @POST(Urls.SUB_URL_CREATE_EVENT)
    Call<CreateEventModel> getcreateventresponse(@HeaderMap Map<String, String> token,
                                                 @Part("club_id") int clubid, @Part("title") String title,
                                                 @Part("short_desc")  String short_description,
                                                 @Part("description" )String description,
                                                 @Part("venue" ) String venue,
                                                 @Part("event_start_date") String event_start_date,
                                                 @Part("event_end_date") String event_end_date,
                                                 @Part MultipartBody.Part image);
    @Multipart
    @POST(Urls.SUB_URL_EDIT_EVENT)
    Call<CreateEventModel> getediteventresponse(@HeaderMap Map<String, String> token,
                                               @Part("event_id") int event_id,
                                               @Part("club_id") int clubid,
                                               @Part("title") String title,
                                              @Part("short_desc")  String short_description,
                                               @Part("description" )String description,
                                               @Part("venue" ) String venue,
                                               @Part("event_start_date") String event_start_date,
                                               @Part("event_end_date") String event_end_date,
                                               @Part MultipartBody.Part image);

}
