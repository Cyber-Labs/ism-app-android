package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.api.NewsDetailsApi;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.DeleteNewsResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.NewsDetailsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsProviderImplementation implements NewsDetailsProviderInterface{
    Call<NewsDetailsModel>newsDetailsModelCall;
    @Override
    public void getNewsResponse(String accessToken, int newsId, PresenterCallback presenterCallback) {
        NewsDetailsApi newsDetailsApi = ApiClient.getRetrofit().create(NewsDetailsApi.class);
        newsDetailsModelCall = newsDetailsApi.getNewsDetails(accessToken,newsId);
        newsDetailsModelCall.enqueue(new Callback<NewsDetailsModel>() {
            @Override
            public void onResponse(Call<NewsDetailsModel> call, Response<NewsDetailsModel> response) {
                presenterCallback.onSuccess((NewsDetailsModel)response.body());
            }

            @Override
            public void onFailure(Call<NewsDetailsModel> call, Throwable t) {
             presenterCallback.OnFailure(t.getMessage().toString());
            }
        });
    }
  Call<DeleteNewsResponseModel>deleteNewsResponseModelCall;
    @Override
    public void getDeleteNewsResponse(String accessToken, int newsId, PresenterCallback presenterCallback) {
        NewsDetailsApi newsDetailsApi = ApiClient.getRetrofit().create(NewsDetailsApi.class);
        deleteNewsResponseModelCall = newsDetailsApi.getDeleteNewsResponse(accessToken,newsId);
        deleteNewsResponseModelCall.enqueue(new Callback<DeleteNewsResponseModel>() {
            @Override
            public void onResponse(Call<DeleteNewsResponseModel> call, Response<DeleteNewsResponseModel> response) {
                presenterCallback.onSuccess((DeleteNewsResponseModel)response.body());
            }

            @Override
            public void onFailure(Call<DeleteNewsResponseModel> call, Throwable t) {
             presenterCallback.OnFailure(t.getMessage().toString());
            }
        });
    }


}
