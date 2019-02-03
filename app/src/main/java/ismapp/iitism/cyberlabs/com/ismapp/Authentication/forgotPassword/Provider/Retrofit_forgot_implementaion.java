package ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Api.Api;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Model.Otp_Response_Model;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit_forgot_implementaion implements Provider_interface {
     private Call<Otp_Response_Model> call;





    @Override
    public void getOtpResponse(String email, final PresenterCallback presenterCallback) {
        Api api = ApiClient.getRetrofit().create(Api.class);
        call = api.getResponse(email);
        call.enqueue(new Callback<Otp_Response_Model>() {
            @Override
            public void onResponse(Call<Otp_Response_Model> call, Response<Otp_Response_Model> response) {
                presenterCallback.onSuccess((Otp_Response_Model)response.body());
            }

            @Override
            public void onFailure(Call<Otp_Response_Model> call, Throwable t) {
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

