package ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Api;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.forgotPassword.Model.Otp_Response_Model;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Api  {


    @POST("/email")
    Call<Otp_Response_Model> getResponse(@Field("email") String email);
}
