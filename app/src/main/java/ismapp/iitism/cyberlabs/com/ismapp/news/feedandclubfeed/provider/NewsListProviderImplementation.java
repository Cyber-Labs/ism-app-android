package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.provider;

import android.content.Context;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.api.ClubNewsApi;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsRemoveResponse;
import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListProviderImplementation implements NewsListProviderInterface {
    private Call<NewsListModel> newsListApiCall;
    private Call<NewsRemoveResponse> newsRemoveResponseCall;

    @Override
    public void newsListResponse(Context mcontext, String accessToken, PresenterCallback presenterCallback) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(mcontext.getCacheDir(), cacheSize);
        ClubNewsApi  clubNewsApi = ApiClient.getRetrofitWithCache(cache).create(ClubNewsApi.class);
//        ClubNewsApi clubNewsApi = ApiClient.getRetrofit().create(ClubNewsApi.class);
        newsListApiCall = clubNewsApi.getNewsList(accessToken);
        newsListApiCall.enqueue(new Callback<NewsListModel>() {
            @Override
            public void onResponse(Call<NewsListModel> call, Response<NewsListModel> response) {
                presenterCallback.onSuccess((NewsListModel)response.body());
                //Log.e("aman", "onResponse: "+ response );
            }

            @Override
            public void onFailure(Call<NewsListModel> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });
    }
    private Call<NewsListModel> clubNewsListModelCall;
    @Override
    public void clubNewsListResponse(String accessToken, int clubId, PresenterCallback presenterCallback) {
        ClubNewsApi clubNewsApi = ApiClient.getRetrofit().create(ClubNewsApi.class);
        clubNewsListModelCall = clubNewsApi.getClubNewsList(accessToken,clubId);
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
        newsRemoveResponseCall=clubNewsApi.getNewsDeleteResponse(accessToken,news_id);
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
