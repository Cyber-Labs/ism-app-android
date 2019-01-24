package ismapp.iitism.cyberlabs.com.ismapp.Authentication.Forgot_password.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Api  {


    @POST("/email")
    Call<Otp_Response_Model> getResponse(@Field("email") String email);
}
