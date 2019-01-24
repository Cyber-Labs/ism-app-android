package ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Api;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Model.SignUpResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignUpApi {
    @POST("/signup")
    @FormUrlEncoded
    Call<SignUpResponseModel> getResponse(@Field("email") String email,
                                          @Field("email") String name,
                                          @Field("email") String password
                                          );
}
