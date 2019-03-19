package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.api;

import java.util.Map;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.model.CreateNewsResponseModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CreateNewsApi {
    @POST(Urls.SUB_URL_CREATE_NEWS)
    Call<CreateNewsResponseModel> getCreateNewsResponse(@HeaderMap Map<String, String> token,
                                                        @Part("club_id") int clubid,
                                                        @Part("description") String description,
                                                        @Part MultipartBody.Part image);
}
