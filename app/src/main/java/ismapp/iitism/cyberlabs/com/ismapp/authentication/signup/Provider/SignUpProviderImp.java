package ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Api.SignUpApi;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Model.SignUpResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpProviderImp implements SignUpProvider {
    SignUpApi signUpApi;

    public SignUpProviderImp() {

        signUpApi= ApiClient.getRetrofit().create(SignUpApi.class);
    }



    @Override
    public void getSignUpResponse(String email, String name, String password, final PresenterCallback callback) {
        Call<SignUpResponseModel> call=signUpApi.getResponse(email,name,password);
        call.enqueue(new Callback<SignUpResponseModel>() {
            @Override
            public void onResponse(Call<SignUpResponseModel> call, Response<SignUpResponseModel> response) {
                callback.onSuccess((SignUpResponseModel)response.body());
            }

            @Override
            public void onFailure(Call<SignUpResponseModel> call, Throwable t) {
                callback.OnFailure(t.toString());

            }
        });
    }
}