package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.api;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsRemoveResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClubNewsApi {
    @GET(Urls.SUB_URL_NEWS_LIST)
    Call<NewsListModel> getNewsList();

    @GET(Urls.SUB_URL_CLUB_NEWS_LIST)
    Call<NewsListModel> getClubNewsList(@Query("club_id") int club_id);

    @GET(Urls.SUB_URL_DELETE_NEWS)
    Call<NewsRemoveResponse> getNewsDeleteResponse(@Query("news_id") int newsId);
}
