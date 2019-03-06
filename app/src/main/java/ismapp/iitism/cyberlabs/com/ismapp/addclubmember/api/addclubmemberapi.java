package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.api;

import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.Member;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface addclubmemberapi {
   @GET(Urls.addclubmember)
    Call<Member>  getresult(@Header("Authorization") String Token_access_token, @Query("club_id") int club_id,
                            @Query("email_id") String email_id, @Query("is_admin") boolean is_admin);
}
