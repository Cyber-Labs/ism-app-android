package ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Api;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Model.NewPassword;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface api {
    @POST("reset_password/")
    Call<NewPassword> getresponse(@Field("email") String email, @Field("new_password") String password, @Field("otp") int otp);
}
