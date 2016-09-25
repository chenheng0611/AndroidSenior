package com.qianfeng.day1_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xray on 16/9/25.
 */

public interface GiftListService {

    @GET("/majax.action?method=getGiftList")
    Call<GiftListBean> getGiftList(@Query("pageno")int pageno);
}
