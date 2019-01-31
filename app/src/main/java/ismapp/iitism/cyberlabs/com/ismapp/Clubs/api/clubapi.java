package ismapp.iitism.cyberlabs.com.ismapp.Clubs.api;

import android.content.Context;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface clubapi   {


    @GET(Urls.clublist)
    Call<ClubsList> getclublist(@Header("Authorization") String Token_access_token);
}
