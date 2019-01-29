package ismapp.iitism.cyberlabs.com.ismapp.Clubs.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.api.clubapi;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClubListImpl implements ProviderInterface {

    private Call<ClubsList> clubsListCall;

    @Override
    public void requestclubslist(final PresenterCallback presenterCallback) {
        clubapi clubapi = ApiClient.getRetrofit().create(ismapp.iitism.cyberlabs.com.ismapp.Clubs.api.clubapi.class);
        clubsListCall = clubapi.getclublist();
        clubsListCall.enqueue(new Callback<ClubsList>() {
            @Override
            public void onResponse(Call<ClubsList> call, Response<ClubsList> response) {
                presenterCallback.onSuccess((ClubsList)response.body());
            }

            @Override
            public void onFailure(Call<ClubsList> call, Throwable t) {
             presenterCallback.OnFailure(t.toString());
            }
        });

    }
}
