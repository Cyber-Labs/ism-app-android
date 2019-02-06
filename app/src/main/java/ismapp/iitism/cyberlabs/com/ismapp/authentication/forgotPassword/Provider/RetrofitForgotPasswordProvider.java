package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.api.ForgotPasswordApi;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.model.ForgotPasswordResponse;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitForgotPasswordProvider implements ForgotPasswordProvider {
     private Call<ForgotPasswordResponse> call;

    @Override
    public void getOtpResponse(String email, final PresenterCallback presenterCallback) {
        ForgotPasswordApi forgotPasswordApi = ApiClient.getRetrofit().create(ForgotPasswordApi.class);
        call = forgotPasswordApi.getResponse(email);
        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                presenterCallback.onSuccess((ForgotPasswordResponse)response.body());
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                presenterCallback.OnFailure(t.toString());
            }
        });
    }

    @Override
    public void OnDestroy() {
    if(call != null){
        call.cancel();
    }
    }
}

