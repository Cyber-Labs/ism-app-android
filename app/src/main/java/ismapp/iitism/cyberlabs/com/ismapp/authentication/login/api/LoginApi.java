package ismapp.iitism.cyberlabs.com.ismapp.authentication.login.api;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.model.LoginModel;
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
