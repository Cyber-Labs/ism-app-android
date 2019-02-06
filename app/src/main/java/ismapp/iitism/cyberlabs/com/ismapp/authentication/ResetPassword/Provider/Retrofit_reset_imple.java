package ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Api.api;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Model.NewPassword;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit_reset_imple implements Retrofit_reset_interface {
   private retrofit2.Call<NewPassword> call;



 @Override
 public void getSuccessResponse(String email, String Password, int otp, final PresenterCallback callback) {
  api api = ApiClient.getRetrofit().create(ismapp.iitism.cyberlabs.com.ismapp.authentication.ResetPassword.Api.api.class);
  retrofit2.Call call = api.getresponse(email,Password,otp);
  call.enqueue(new Callback() {
   @Override
   public void onResponse(retrofit2.Call call, Response response) {
    callback.onSuccess((NewPassword)response.body());
   }

   @Override
   public void onFailure(retrofit2.Call call, Throwable t) {
    callback.OnFailure(t.toString());
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
