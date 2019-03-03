package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.api;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.model.ResetPasswordModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ResetPasswordApi {
    @POST(Urls.resetpassord)
    Call<ResetPasswordModel> getresponse(@Field("email") String email, @Field("new_password") String password, @Field("otp") int otp);
}
