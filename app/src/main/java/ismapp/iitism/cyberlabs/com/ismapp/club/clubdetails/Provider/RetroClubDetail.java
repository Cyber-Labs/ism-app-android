package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.api.Api;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroClubDetail implements ClubDetailInterface {
    Call<ClubDetails> call;


    @Override
    public void getclubdetailresponse(String access_token, int id, final PresenterCallback presenterCallback) {
        Api api = ApiClient.getRetrofit().create(Api.class);
        call = api.getdetails(access_token,id);
        call.enqueue(new Callback<ClubDetails>() {
            @Override
            public void onResponse(Call<ClubDetails> call, Response<ClubDetails> response) {
                ClubDetails c=(ClubDetails)response.body();

                presenterCallback.onSuccess((ClubDetails)response.body());
            }

            @Override
            public void onFailure(Call<ClubDetails> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });

    }
}
