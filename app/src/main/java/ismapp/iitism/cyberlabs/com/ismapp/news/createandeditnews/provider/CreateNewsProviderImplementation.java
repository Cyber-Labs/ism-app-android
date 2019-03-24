package ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.provider;

import java.util.HashMap;
import java.util.Map;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.api.CreateNewsApi;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.model.CreateNewsResponseModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewsProviderImplementation implements CreateNewsProviderInterface {
    private Call<CreateNewsResponseModel> createNewsApiCall;
    @Override
    public void getCreateNewsResponse(String access_token,int clubid, String description, MultipartBody.Part image, PresenterCallback presenterCallback) {
        CreateNewsApi createNewsApi = ApiClient.getRetrofit().create(CreateNewsApi.class);
        Map<String, String> token = new HashMap<>();
        token.put("Authorization", access_token);
        createNewsApiCall = createNewsApi.getCreateNewsResponse(token,clubid,description,image);
        createNewsApiCall.enqueue(new Callback<CreateNewsResponseModel>() {
            @Override
            public void onResponse(Call<CreateNewsResponseModel> call, Response<CreateNewsResponseModel> response) {
                presenterCallback.onSuccess((CreateNewsResponseModel)response.body());
            }

            @Override
            public void onFailure(Call<CreateNewsResponseModel> call, Throwable t) {
                 presenterCallback.OnFailure(t.getMessage());
            }
        });
    }
    private Call<CreateNewsResponseModel> editNewsApiCall;

    @Override
    public void getEditNewsResponse(String accessToken, int newsId, int clubId, String description, MultipartBody.Part image, PresenterCallback presenterCallback) {
        CreateNewsApi editNewsApi = ApiClient.getRetrofit().create(CreateNewsApi.class);
        Map<String, String> token = new HashMap<>();
        token.put("Authorization", accessToken);
        editNewsApiCall = editNewsApi.getEditNewsResponse(token,newsId,clubId,description,image);
        editNewsApiCall.enqueue(new Callback<CreateNewsResponseModel>() {
            @Override
            public void onResponse(Call<CreateNewsResponseModel> call, Response<CreateNewsResponseModel> response) {
                presenterCallback.onSuccess((CreateNewsResponseModel)response.body());
            }

            @Override
            public void onFailure(Call<CreateNewsResponseModel> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });


    }
}
