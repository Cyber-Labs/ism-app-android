package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.api.ResetPasswordApi;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.model.ResetPasswordModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordProviderImp implements ResetPasswordProviderInterface {
   private retrofit2.Call<ResetPasswordModel> call;



 @Override
 public void getSuccessResponse(String email, String Password, int otp, final PresenterCallback callback) {
  ResetPasswordApi ResetPasswordApi = ApiClient.getRetrofit().create(ResetPasswordApi.class);
  retrofit2.Call call = ResetPasswordApi.getresponse(email,Password,otp);
  call.enqueue(new Callback() {
   @Override
   public void onResponse(retrofit2.Call call, Response response) {
    callback.onSuccess((ResetPasswordModel)response.body());
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
