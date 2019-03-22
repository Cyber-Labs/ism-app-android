package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.provider;

import java.util.HashMap;
import java.util.Map;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.api.CreateNewsApi;
import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.model.CreateNewsResponseModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewsProviderImplementation implements CreateNewsProviderInterface {
    Call<CreateNewsResponseModel> createNewsApiCall;
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
                 presenterCallback.OnFailure(t.getMessage().toString());
            }
        });
    }
}