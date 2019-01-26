package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Api.LoginApi;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.LoginCallBack;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Model.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginProviderImp implements LoginProvider {
    private LoginApi loginApi;
    private Call<LoginModel> call;

    public LoginProviderImp() {
        loginApi=ApiClient.getRetrofit().create(LoginApi.class);
    }

    @Override
    public void getLoginResponse(String email, String password, final LoginCallBack loginCallBack) {
        call=loginApi.getResponse(email,password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                loginCallBack.getVerification(response.body());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                loginCallBack.onFailure();


            }
        });
    }
}
