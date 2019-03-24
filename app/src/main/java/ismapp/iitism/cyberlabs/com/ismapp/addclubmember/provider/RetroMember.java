package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider;

import android.util.Log;

import java.util.Objects;

import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.api.addclubmemberapi;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.Member;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroMember implements MemberProvider {
    private Call<Member> call;

    @Override
    public void getMemberResponse(String token, int clubid, String emailid, Boolean isadmin, final PresenterCallback presenterCallback) {
        addclubmemberapi api = ApiClient.getRetrofit().create(ismapp.iitism.cyberlabs.com.ismapp.addclubmember.api.addclubmemberapi.class);
        call = api.getresult(token,clubid,emailid,isadmin);
        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Log.d("hello", ((Member) Objects.requireNonNull(response.body())).toString());

                presenterCallback.onSuccess((Member)response.body());
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });
    }
}
