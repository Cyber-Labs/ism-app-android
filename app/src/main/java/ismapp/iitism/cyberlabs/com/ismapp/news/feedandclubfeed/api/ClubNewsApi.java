package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.api;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsRemoveResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClubNewsApi {
    @GET(Urls.SUB_URL_NEWS_LIST)
    Call<NewsListModel> getNewsList(@Header("Authorization") String Token_access_token);

    @GET(Urls.SUB_URL_CLUB_NEWS_LIST)
    Call<NewsListModel> getClubNewsList(@Header("Authorization") String Token_access_token, @Query("club_id") int club_id);

    @GET(Urls.SUB_URL_DELETE_NEWS)
    Call<NewsRemoveResponse> getNewsDeleteResponse(@Header("Authorization") String Token_access_token, @Query("news_id") int newsId);
}
