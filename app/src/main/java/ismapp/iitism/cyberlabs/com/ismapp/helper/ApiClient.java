package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.app.Application;
import android.content.Context;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient extends Application {
    public static  final String Base_Url = "https://ismapp.herokuapp.com/";
    public static Retrofit retrofit=null;


    public static Retrofit getRetrofit(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(Base_Url).
                    addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

  return retrofit;
    }


}
