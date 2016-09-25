package com.qianfeng.day1_okhttp;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://192.168.0.103:8080/web/MyServlet";
    private OkHttpClient mClient ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = new OkHttpClient();
    }

    public void onClickGet(View view) {
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
                Request request = new Request.Builder()
                        .url(URL + "?name=xray")
                        .get()
                        .build();
                Response response = null;
                try {
                    response = mClient.newCall(request).execute();
                    String s = response.body().string();
                    return s;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public void onClickPost(View view) {
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
                FormBody body = new FormBody.Builder()
                        .add("name", "micheal")
                        .build();
                Request request = new Request.Builder()
                        .url(URL)
                        .post(body)
                        .build();
                Response response = null;
                try {
                    response = mClient.newCall(request).execute();
                    String s = response.body().string();
                    return s;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
