package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.api.ClubNewsApi;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListProviderImplementation implements NewsListProviderInterface {
    Call<NewsListModel> newsListApiCall;
    @Override
    public void newsListResponse(String accessToken, PresenterCallback presenterCallback) {
        ClubNewsApi clubNewsApi = ApiClient.getRetrofit().create(ClubNewsApi.class);
        newsListApiCall = clubNewsApi.getNewsList(accessToken);
        newsListApiCall.enqueue(new Callback<NewsListModel>() {
            @Override
            public void onResponse(Call<NewsListModel> call, Response<NewsListModel> response) {
                presenterCallback.onSuccess((NewsListModel)response.body());
            }

            @Override
            public void onFailure(Call<NewsListModel> call, Throwable t) {
             presenterCallback.OnFailure(t.getMessage().toString());
            }
        });
    }
    Call<NewsListModel> clubNewsListModelCall;
    @Override
    public void clubNewsListResponse(String accessToken, int clubId, PresenterCallback presenterCallback) {
        ClubNewsApi clubNewsApi = ApiClient.getRetrofit().create(ClubNewsApi.class);
        clubNewsListModelCall = clubNewsApi.getClubNewsList(accessToken,clubId);
        clubNewsListModelCall.enqueue(new Callback<NewsListModel>() {
            @Override
            public void onResponse(Call<NewsListModel> call, Response<NewsListModel> response) {
                presenterCallback.onSuccess((NewsListModel)response.body());
            }

            @Override
            public void onFailure(Call<NewsListModel> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage().toString());
            }
        });
    }
}
