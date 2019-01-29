package ismapp.iitism.cyberlabs.com.ismapp.Clubs.api;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

public interface clubapi  {
    @GET(Urls.clublist)
    Call<ClubsList> getclublist();
}
