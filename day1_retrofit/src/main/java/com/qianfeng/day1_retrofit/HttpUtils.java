package com.qianfeng.day1_retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by xray on 16/9/25.
 */

public class HttpUtils {

    private static final String URL = "http://192.168.0.103:8080";
    public static final String GIFT_URL = "http://www.1688wan.com";
    private static HttpService sHttpService = null;
    private static GiftListService sGiftListService = null;

    public static HttpService getHttpService(){
        if(sHttpService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            sHttpService = retrofit.create(HttpService.class);
        }
        return sHttpService;
    }

    public static GiftListService getGiftListService(){
        if(sGiftListService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GIFT_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sGiftListService = retrofit.create(GiftListService.class);
        }
        return sGiftListService;
    }
}
