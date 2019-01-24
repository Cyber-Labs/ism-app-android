package ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Provider;

import android.telecom.Call;

import ismapp.iitism.cyberlabs.com.ismapp.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Api.api;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Model.NewPassword;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.resetcallback;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit_reset_imple implements Retrofit_reset_interface {
   private retrofit2.Call<NewPassword> call;
    @Override
    public void getSuccessResponse(String email, String Password, int otp, final resetcallback resetcallback) {

     api api = ApiClient.getRetrofit().create(ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Api.api.class);
     retrofit2.Call call = api.getresponse(email,Password,otp);
     call.enqueue(new Callback() {
      @Override
      public void onResponse(retrofit2.Call call, Response response) {
          resetcallback.getResponse((NewPassword) response.body());
      }

      @Override
      public void onFailure(retrofit2.Call call, Throwable t) {
              resetcallback.OnFailure(t.getMessage().toString());
      }
     });

    }

    @Override
    public void onDestroy() {
     if(call != null){
      call.cancel();
     }
    }
}
