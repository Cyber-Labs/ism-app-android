package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Api;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Model.LoginModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("login/")
    @FormUrlEncoded
    Call<LoginModel> getResponse(@Field("email") String email,
                                 @Field("password") String password
    );
}
