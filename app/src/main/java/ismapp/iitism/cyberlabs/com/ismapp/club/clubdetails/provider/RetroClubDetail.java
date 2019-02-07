package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.api.ClubDetailsApi;
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
        ClubDetailsApi clubDetailsApi = ApiClient.getRetrofit().create(ClubDetailsApi.class);
        call = clubDetailsApi.getdetails(access_token,id);
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
