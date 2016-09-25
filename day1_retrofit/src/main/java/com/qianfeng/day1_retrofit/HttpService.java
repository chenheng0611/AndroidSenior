package com.qianfeng.day1_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by xray on 16/9/25.
 */

public interface HttpService {

    @GET("/web/MyServlet")
    Call<String> getName(@Query("name")String name);

    @POST("/web/MyServlet")
    Call<String> postName(@Query("name")String name);
}
