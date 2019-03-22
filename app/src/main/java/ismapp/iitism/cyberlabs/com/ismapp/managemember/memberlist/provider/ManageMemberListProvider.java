package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.provider;

import android.util.Log;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.api.ManageMemberListApi;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.RemoveMember;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageMemberListProvider implements ManageMemberProviderInterface {
   private Call<MemberListResponse> memberListResponseCall;
  private     Call<RemoveMember> removeMemberCall;

    @Override
    public void requestMemberList(String access_token, int club_id, final PresenterCallback presenterCallback) {
        ManageMemberListApi manageMemberListApi = ApiClient.getRetrofit().create(ManageMemberListApi.class);
       memberListResponseCall =  manageMemberListApi.getMemberListResponse(access_token,club_id);

        memberListResponseCall.enqueue(new Callback<MemberListResponse>() {
            @Override
            public void onResponse(Call<MemberListResponse> call, Response<MemberListResponse> response) {
                presenterCallback.onSuccess((MemberListResponse)response.body());
                Log.e("periul", "onResponse: "+ response.body().toString());

            }

            @Override
            public void onFailure(Call<MemberListResponse> call, Throwable t) {
                    presenterCallback.OnFailure(t.getMessage().toString());
            }
        });
    }

    @Override
    public void requestDeleteMember(String access_token, int club_id, String email_id, final PresenterCallback presenterCallback) {
        ManageMemberListApi manageMemberListApi = ApiClient.getRetrofit().create(ManageMemberListApi.class);
        removeMemberCall  = manageMemberListApi.getRemoveMemberResponse(access_token,club_id,email_id);
        removeMemberCall.enqueue(new Callback<RemoveMember>() {
            @Override
            public void onResponse(Call<RemoveMember> call, Response<RemoveMember> response) {
                presenterCallback.onSuccess((RemoveMember)response.body());
            }

            @Override
            public void onFailure(Call<RemoveMember> call, Throwable t) {
                 presenterCallback.OnFailure(t.getMessage().toString());
            }
        });
    }
}
