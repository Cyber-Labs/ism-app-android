package ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Api.Api;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.CallBack;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Model.Otp_Response_Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofit_forgot_implementaion implements Provider_interface {
     private Call<Otp_Response_Model> call;



    @Override
    public void getOtpResponse(String email, final CallBack callBack) {
       Api api = ApiClient.getRetrofit().create(Api.class);
       call = api.getResponse(email);
       call.enqueue(new Callback<Otp_Response_Model>() {
           @Override
           public void onResponse(Call<Otp_Response_Model> call, Response<Otp_Response_Model> response) {
               callBack.getVerification(response.body());
           }

           @Override
           public void onFailure(Call<Otp_Response_Model> call, Throwable t) {
               callBack.onFailure(t.toString());
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

