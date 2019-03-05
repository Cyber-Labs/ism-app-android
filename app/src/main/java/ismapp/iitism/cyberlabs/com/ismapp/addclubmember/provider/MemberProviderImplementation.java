package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider;

import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.api.addclubmemberapi;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.member;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberProviderImplementation implements MemberProvider {
    private Call<member> call;
    @Override
    public void getMemberResponse(String token, int clubid, String emailid, Boolean isadmin, final PresenterCallback presenterCallback) {
        addclubmemberapi addclubmemberapi = ApiClient.getRetrofit().create(addclubmemberapi.class);
        addclubmemberapi.getresult(token,clubid,emailid,isadmin);
        call.enqueue(new Callback<member>() {
            @Override
            public void onResponse(Call<member> call, Response<member> response) {
                presenterCallback.onSuccess((member)response.body());
            }

            @Override
            public void onFailure(Call<member> call, Throwable t) {
        presenterCallback.OnFailure(t.getMessage());
            }
        });
    }
}
