package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.api;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface clubapi   {


    @GET(Urls.SUB_URL_CLUB_LIST)
    Call<ClubListResponse> getclublist(@Header("Authorization") String Token_access_token);
}
