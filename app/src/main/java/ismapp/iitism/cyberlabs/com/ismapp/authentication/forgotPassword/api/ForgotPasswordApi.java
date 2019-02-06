package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.api;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.model.ForgotPasswordResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ForgotPasswordApi {


    @POST("forgot_password/")
    @FormUrlEncoded
    Call<ForgotPasswordResponse> getResponse(@Field("email") String email);
}
