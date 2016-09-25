package com.qianfeng.day1_myknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mytext)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyKnife.bind(this);

        mTextView.setText("I used myknife!");
    }

    @OnClick(R.id.mytext)
    void click(){
        Toast.makeText(this, "I click this TextView", Toast.LENGTH_SHORT).show();
    }
}
