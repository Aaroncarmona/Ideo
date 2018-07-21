package ideo.com.ideo.oi;

import java.util.concurrent.TimeUnit;

import ideo.com.ideo.constant.Constant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IdeeoApiAdapter {

    private static IdeeoApiService API_SERVICE;

    public static IdeeoApiService getApiService(){
        if ( API_SERVICE == null ){
            Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiConstants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client( new OkHttpClient.Builder().connectTimeout(Constant.TIMER_OUT , TimeUnit.SECONDS).readTimeout(Constant.TIMER_OUT , TimeUnit.SECONDS).build())
            .build();

            API_SERVICE = retrofit.create(IdeeoApiService.class);
        }
        return API_SERVICE;
    }
}
