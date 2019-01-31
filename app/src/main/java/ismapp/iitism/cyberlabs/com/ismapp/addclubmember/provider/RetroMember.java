package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider;

import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.api.api;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.member;
import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.api.Api;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroMember implements memberprointer {
    Call<member> call;
    @Override
    public void getresponse(String token, int clubid, String emailid, Boolean isadmin, final PresenterCallback presenterCallback) {
        api api = ApiClient.getRetrofit().create(ismapp.iitism.cyberlabs.com.ismapp.addclubmember.api.api.class);
        api.getresult(token,clubid,emailid,isadmin);
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
