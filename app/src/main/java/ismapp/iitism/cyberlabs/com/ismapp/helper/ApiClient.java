package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.content.Context;

import java.net.InetAddress;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient  {
    private static final String BASE_URL = "https://ismapp.herokuapp.com/";
    private static Retrofit retrofit=null;

    public static void instantiateRetrofitWithAccessToken(Context context,String accessToken){
        Cache cache = new Cache(context.getCacheDir(),5*1024*1024);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (!isNetworkAvailable()) {
                        int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale \
                        request = request
                                .newBuilder()
                                .header("Authorization",accessToken)
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .build();
                    }
                    else
                    {
                        request = request
                                .newBuilder()
                                .header("Authorization",accessToken)
                                .header("Cache-Control","public, max-age="+20)      //20 secs old data will be accessed
                                .build();
                    }
                    return chain.proceed(request);
                })
                .addInterceptor(interceptor)
                .build();
                retrofit =  new Retrofit.Builder().baseUrl(BASE_URL).
                        addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
    }

    public static Retrofit getRetrofit(){
        if(retrofit == null)
        {
            retrofit =  new Retrofit.Builder().baseUrl(BASE_URL).
                     addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
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
