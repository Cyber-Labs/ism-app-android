package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.app.Application;

import java.net.InetAddress;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient extends Application {
    private static final String Base_Url = "https://ismapp.herokuapp.com/";
    private static Retrofit retrofit=null;


    public static Retrofit getRetrofit(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(Base_Url).
                    addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
    public  static Retrofit getRetrofitWithCache(Cache cache){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(chain -> {
                        Request request = chain.request();
                        if (!isNetworkAvailable()) {
                            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale \
                            request = request
                                    .newBuilder()
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .build();
                        }
                        return chain.proceed(request);
                    })
                    .build();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

            return builder.build();

    }
    public static boolean isNetworkAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}
