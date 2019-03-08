package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.provider;

import android.util.Log;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.api.ClubListApi;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubListProviderImp implements ClubListProviderInterface {

    private Call<ClubListResponse> clubsListCall;



    @Override
    public void requestclubslist(String access_token, final PresenterCallback presenterCallback) {
        ClubListApi ClubListApi = ApiClient.getRetrofit().create(ClubListApi.class);
        clubsListCall = ClubListApi.getclublist(access_token);
        clubsListCall.enqueue(new Callback<ClubListResponse>() {
            @Override
            public void onResponse(Call<ClubListResponse> call, Response<ClubListResponse> response) {
               // Log.e("TAG", "onResponse: "+response.body().getClubsNameList().size() );
                presenterCallback.onSuccess((ClubListResponse)response.body());

            }

            @Override
            public void onFailure(Call<ClubListResponse> call, Throwable t) {
                presenterCallback.OnFailure(t.toString());

            }
        });
    }
}
