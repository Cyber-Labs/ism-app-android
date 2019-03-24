package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.api;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ClubNewsApi {
    @POST(Urls.SUB_URL_NEWS_LIST)
    Call<NewsListModel> getNewsList(@Header("Authorization") String Token_access_token);
    @POST(Urls.SUB_URL_CLUB_NEWS_LIST)
    Call<NewsListModel> getClubNewsList(@Header("Authorization") String Token_access_token, @Field("club_id") int club_id);
}
