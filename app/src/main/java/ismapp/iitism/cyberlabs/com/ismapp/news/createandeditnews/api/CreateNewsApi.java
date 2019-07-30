package ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.api;

import java.util.Map;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.model.CreateNewsResponseModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CreateNewsApi {
    @Multipart
    @POST(Urls.SUB_URL_CREATE_NEWS)
    Call<CreateNewsResponseModel> getCreateNewsResponse(@HeaderMap Map<String, String> token,
                                                        @Part("club_id") int clubId,
                                                        @Part("description") String description,
                                                        @Part MultipartBody.Part image);

    @Multipart
    @POST(Urls.SUB_URL_EDIT_NEWS)
    Call<CreateNewsResponseModel> getEditNewsResponse(@HeaderMap Map<String, String> token,
                                                      @Part("news_id") int newsId,
                                                      @Part("club_id") int clubId,
                                                      @Part("description") String description,
                                                      @Part MultipartBody.Part image);
}
