package ismapp.iitism.cyberlabs.com.ismapp.authentication.login.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.api.LoginApi;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.model.LoginModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginProviderImp implements LoginProvider {
    private final LoginApi loginApi;
    private Call<LoginModel> call;

    public LoginProviderImp() {
        loginApi=ApiClient.getRetrofit().create(LoginApi.class);
    }



    @Override
    public void getLoginResponse(String email, String password, final PresenterCallback callback) {
        call=loginApi.getResponse(email,password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                callback.OnFailure(t.toString());


            }
        });
    }
}
