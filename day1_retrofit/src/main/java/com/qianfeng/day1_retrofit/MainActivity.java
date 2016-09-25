package com.qianfeng.day1_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "xray";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        HttpUtils.getHttpService().getName("chenheng")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        Log.i(TAG, "onResponse: "+body);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

    public void onPostClick(View view) {
        HttpUtils.getHttpService().postName("xray")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        Log.i(TAG, "onResponse: "+body);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

    public void onGiftClick(View view) {
        HttpUtils.getGiftListService()
                .getGiftList(1)
                .enqueue(new Callback<GiftListBean>() {
                    @Override
                    public void onResponse(Call<GiftListBean> call, Response<GiftListBean> response) {
                        GiftListBean bean = response.body();
                        Log.i(TAG, "onResponse: "+bean.getPageno()+","+bean.getAd().size());
                    }

                    @Override
                    public void onFailure(Call<GiftListBean> call, Throwable t) {

                    }
                });
    }
}
