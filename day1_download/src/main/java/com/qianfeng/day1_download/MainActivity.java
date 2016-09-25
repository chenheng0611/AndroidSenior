package com.qianfeng.day1_download;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://p.gdown.baidu.com/6ad96599d98ea31c287f9beb6d01cff3240db0a0ddb18a2b7ed4e70d04fa010c5359f07a8e38cfdec752bace3121f52110f211598134f59e82db34ebfc23b509078c8e9c168f37397e29aeb3e22652988a1e79292d7d0b5781ba43877545e8c6d1a96ff1fc4aecc6f2b64e8a7696bd379c24be51f6b6df15d573fb5fe02335ac6ef655d074b9885d454821ded87e68a4c5d48aa2d42406d6b8f70ed6c1c9d49f54607afe082c1f57115f7a63174f68daeeec15fcd1df68ba3edfc0cb389cd2b69a79709db105aecc141cf0cf4368df614d537a17043389f3ca950afe980050a9ed9e9688711144961becc40719c782009554a7c437aea6ee50cf51a8742ded361b3fb5c65edad880b7dd66d50bcdc4b0ea9ad6f1a6f54d8e";
    private static final String TAG = "xray";
    public static final int TAG_DOWNLOAD = 100;
    public static final int TAG_FINISH = 200;
    private ProgressBar mProgressBar;
    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar)findViewById(R.id.download_progress_pb);
        mClient = new OkHttpClient();
    }

    public void onStartDownload(View view) {
        Request request = new Request.Builder().url(URL).get().build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: "+Thread.currentThread().getName());
                Log.i(TAG, "onResponse: "+response.body().contentLength());
                download(response);
            }
        });
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TAG_DOWNLOAD:
                    Log.i(TAG, "handleMessage: "+msg.arg1);
                    mProgressBar.setProgress(msg.arg1);
                    break;
                case TAG_FINISH:
                    mProgressBar.setProgress(0);
                    Toast.makeText(MainActivity.this, "Download finished!", Toast.LENGTH_SHORT).show();
                    installApk((File)msg.obj);
                    break;
            }
        }
    };

    private void installApk(File file){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void download(Response response){
        long length = response.body().contentLength();
        InputStream inputStream = response.body().byteStream();
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "download_dir";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file = new File(dir, System.currentTimeMillis() + ".apk");
        try {
            OutputStream os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            int total = 0;
            long start = System.currentTimeMillis();
            while((len = inputStream.read(buffer)) != -1){
                os.write(buffer,0,len);
                total += len;
                int progress = (int) ((1.0 * total / length) * 100);
                long end = System.currentTimeMillis();
                if(end - start > 500){
                    mHandler.obtainMessage(TAG_DOWNLOAD,progress,0).sendToTarget();
                    start = end;
                    Log.i(TAG, "download: "+total+","+length+","+progress);
                }
            }
            mHandler.obtainMessage(TAG_FINISH,file).sendToTarget();
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
