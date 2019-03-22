package ismapp.iitism.cyberlabs.com.ismapp.news.newslist.api;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.news.newslist.model.NewsListModel;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NewsListApi {
    @POST(Urls.SUB_URL_NEWS_LIST)
    Call<NewsListModel> getNewsList(@Header("Authorization") String Token_access_token);
}
