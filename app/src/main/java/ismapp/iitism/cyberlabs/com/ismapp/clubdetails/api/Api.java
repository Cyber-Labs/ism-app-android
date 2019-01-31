package ismapp.iitism.cyberlabs.com.ismapp.clubdetails.api;

import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Api {
    @GET(Urls.clubdetails)
    Call<ClubDetails> getdetails(@Header("Authorization") String Token_access_token, @Field("club_id") int id);
}
