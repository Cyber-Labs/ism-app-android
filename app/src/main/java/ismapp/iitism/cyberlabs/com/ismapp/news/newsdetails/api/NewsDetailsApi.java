package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.api;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.NewsDetailsModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NewsDetailsApi {
@POST(Urls.SUB_URL_NEWS_DETAILS)
    Call<NewsDetailsModel>getNewsDetails(@Header("Authorization") String Token_access_token, @Field("news_id") int news_id);
}
