package ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Api;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Model.VerifyOtpModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VerifyOtpApi {
    @POST("verify_otp/")
    @FormUrlEncoded
    Call<VerifyOtpModel> getResponse(@Field("email") String email,
                                     @Field("otp") int otp
    );
}
