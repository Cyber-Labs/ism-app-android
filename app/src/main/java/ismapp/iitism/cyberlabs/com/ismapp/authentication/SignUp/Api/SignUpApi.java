package ismapp.iitism.cyberlabs.com.ismapp.authentication.SignUp.Api;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.SignUp.Model.SignUpResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignUpApi {
    @POST("signup/")
    @FormUrlEncoded
    Call<SignUpResponseModel> getResponse(@Field("email") String email,
                                          @Field("name") String name,
                                          @Field("password") String password
                                          );
}
