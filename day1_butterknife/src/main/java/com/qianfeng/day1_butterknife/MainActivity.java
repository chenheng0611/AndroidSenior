package com.qianfeng.day1_butterknife;

import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import butterknife.BindBitmap;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_home)
    RadioButton mTabHome;
    @BindView(R.id.tab_news)
    RadioButton mTabNews;
    @BindView(R.id.tab_me)
    RadioButton mTabMe;
    private BlankFragment mFragmentHome;
    private BlankFragment mFragmentNews;
    private BlankFragment mFragmentMe;
    private Fragment mCurrentFrag;

    @BindBitmap(R.mipmap.ic_launcher)
    Bitmap mBmpIc;
    @BindView(R.id.image_view)
    ImageView mImageView;

    @BindString(R.string.home)
    String mStrHome;
    @BindString(R.string.news)
    String mStrNews;
    @BindString(R.string.me)
    String mStrMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViews();


    }

    private void initViews() {
        mImageView.setImageBitmap(mBmpIc);

        Bundle bundle = new Bundle();
        mFragmentHome = new BlankFragment();
        bundle.putCharSequence("tag","home");
        mFragmentHome.setArguments(bundle);

        mFragmentNews = new BlankFragment();
        bundle = new Bundle();
        bundle.putCharSequence("tag","news");
        mFragmentNews.setArguments(bundle);
        mFragmentMe = new BlankFragment();
        bundle = new Bundle();
        bundle.putCharSequence("tag","me");
        mFragmentMe.setArguments(bundle);

        addFirstFragment();

    }

    private void addFirstFragment() {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.content_layout,mFragmentHome).commit();
        mCurrentFrag = mFragmentHome;
    }

    private void showFragment(Fragment fragment){
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        if(fragment.isAdded()){
            trans.hide(mCurrentFrag).show(fragment).commit();
        }else{
            trans.hide(mCurrentFrag).add(R.id.content_layout,fragment).commit();
        }
        mCurrentFrag = fragment;
    }

    @OnClick(R.id.tab_home)
    void onTabHomeClick(){
        showFragment(mFragmentHome);
        Toast.makeText(MainActivity.this, mStrHome, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tab_news)
    void onTabNewsClick(){
        showFragment(mFragmentNews);
        Toast.makeText(MainActivity.this, mStrNews, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tab_me)
    void onTabMeClick(){
        showFragment(mFragmentMe);
        Toast.makeText(MainActivity.this, mStrMe, Toast.LENGTH_SHORT).show();
    }
}
