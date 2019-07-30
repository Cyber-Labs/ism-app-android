package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.api.ClubNewsApi;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsRemoveResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListProviderImplementation implements NewsListProviderInterface {

    @Override
    public void newsListResponse(String accessToken, PresenterCallback presenterCallback) {
        ClubNewsApi clubNewsApi = ApiClient.getRetrofit().create(ClubNewsApi.class);
        Call<NewsListModel> newsListApiCall = clubNewsApi.getNewsList();
        newsListApiCall.enqueue(new Callback<NewsListModel>() {
            @Override
            public void onResponse(Call<NewsListModel> call, Response<NewsListModel> response) {
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<NewsListModel> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });
    }

    @Override
    public void clubNewsListResponse(String accessToken, int clubId, PresenterCallback presenterCallback) {
        ClubNewsApi clubNewsApi = ApiClient.getRetrofit().create(ClubNewsApi.class);
        Call<NewsListModel> clubNewsListModelCall = clubNewsApi.getClubNewsList(clubId);
        clubNewsListModelCall.enqueue(new Callback<NewsListModel>() {
            @Override
            public void onResponse(Call<NewsListModel> call, Response<NewsListModel> response) {
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<NewsListModel> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });
    }

    @Override
    public void removeEventResponse(String accessToken, int news_id, PresenterCallback presenterCallback) {
        ClubNewsApi clubNewsApi = ApiClient.getRetrofit().create(ClubNewsApi.class);
        Call<NewsRemoveResponse> newsRemoveResponseCall = clubNewsApi.getNewsDeleteResponse(news_id);
        newsRemoveResponseCall.enqueue(new Callback<NewsRemoveResponse>() {
            @Override
            public void onResponse(Call<NewsRemoveResponse> call, Response<NewsRemoveResponse> response) {
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<NewsRemoveResponse> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });
    }
}
