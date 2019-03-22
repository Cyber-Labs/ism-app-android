package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.api;

import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.RemoveMember;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ManageMemberListApi {
    @GET(Urls.SUB_URL_MEMBERS_LIST)
    Call<MemberListResponse> getMemberListResponse(@Header("Authorization") String Token_access_token, @Query("club_id") int id);
    @GET(Urls.SUB_URL_REMOVE_MEMBER)
    Call<RemoveMember> getRemoveMemberResponse(@Header("Authorization") String Token_access_token, @Query("club_id") int id,@Query("email_id") String email_id);
}
