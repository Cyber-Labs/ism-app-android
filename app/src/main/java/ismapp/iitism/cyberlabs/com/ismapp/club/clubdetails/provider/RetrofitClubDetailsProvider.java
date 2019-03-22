package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider;

import android.util.Log;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.api.ClubDetailsApi;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClubDetailsProvider implements ClubDetailsProvider {
    Call<ClubDetailsModel> call;
    Call<MemberListResponse> memberListResponseCall;


    @Override
    public void getClubDetails(String access_token, int id, final PresenterCallback presenterCallback) {
        ClubDetailsApi clubDetailsApi = ApiClient.getRetrofit().create(ClubDetailsApi.class);
        call = clubDetailsApi.getdetails(access_token,id);
        call.enqueue(new Callback<ClubDetailsModel>() {
            @Override
            public void onResponse(Call<ClubDetailsModel> call, Response<ClubDetailsModel> response) {
                ClubDetailsModel c=(ClubDetailsModel)response.body();
                presenterCallback.onSuccess((ClubDetailsModel)response.body());
            }

            @Override
            public void onFailure(Call<ClubDetailsModel> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });

    }

    @Override
    public void requestMemberList(String access_token, int id, final PresenterCallback presenterCallback) {
        ClubDetailsApi clubDetailsApi = ApiClient.getRetrofit().create(ClubDetailsApi.class);
        memberListResponseCall = clubDetailsApi.getMembList(access_token,id);
        memberListResponseCall.enqueue(new Callback<MemberListResponse>() {
            @Override
            public void onResponse(Call<MemberListResponse> call, Response<MemberListResponse> response) {
                MemberListResponse c=(MemberListResponse) response.body();
                presenterCallback.onSuccess((MemberListResponse)response.body());
                Log.e("sujal", "onResponse: "+response.body().toString() );
            }

            @Override
            public void onFailure(Call<MemberListResponse> call, Throwable t) {
                presenterCallback.OnFailure(t.getMessage());
            }
        });

    }
}
