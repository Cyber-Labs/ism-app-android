package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.api;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.DeleteNewsResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.NewsDetailsModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NewsDetailsApi {
    @POST(Urls.SUB_URL_NEWS_DETAILS)
    Call<NewsDetailsModel> getNewsDetails(@Field("news_id") int news_id);

    @GET(Urls.SUB_URL_DELETE_NEWS)
    Call<DeleteNewsResponseModel> getDeleteNewsResponse(@Query("news_id") int news_id);

}
