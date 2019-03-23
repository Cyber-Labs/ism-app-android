package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.api;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ClubDetailsApi {

    @GET(Urls.SUB_URL_CLUB_DETAILS)
    Call<ClubDetailsModel> getdetails(@Header("Authorization") String Token_access_token, @Query("club_id") int id);
    @GET(Urls.SUB_URL_MEMBERS_LIST)
    Call<MemberListResponse> getMembList(@Header("Authorization") String Token_access_token, @Query("club_id") int id);

}
