package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient extends Application {
    private static  final String Base_Url = "https://ismapp.herokuapp.com/";
    private static Retrofit retrofit=null;


    public static Retrofit getRetrofit(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(Base_Url).
                    addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

  return retrofit;
    }


}
