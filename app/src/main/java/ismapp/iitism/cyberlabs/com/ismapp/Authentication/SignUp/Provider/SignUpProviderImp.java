package ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Provider;

import ismapp.iitism.cyberlabs.com.ismapp.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Api.SignUpApi;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Model.SignUpResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.SignUpCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpProviderImp implements SignUpProvider {
    SignUpApi signUpApi;

    public SignUpProviderImp() {

        signUpApi= ApiClient.getRetrofit().create(SignUpApi.class);
    }

    @Override
    public void getSignUpResponse(String email, String name, String password, final SignUpCallBack signUpCallBack) {
        Call<SignUpResponseModel> call=signUpApi.getResponse(email,name,password);
        call.enqueue(new Callback<SignUpResponseModel>() {
            @Override
            public void onResponse(Call<SignUpResponseModel> call, Response<SignUpResponseModel> response) {
                signUpCallBack.getVerification(response.body());
            }

            @Override
            public void onFailure(Call<SignUpResponseModel> call, Throwable t) {
                signUpCallBack.getVerification(null);

            }
        });
    }
}