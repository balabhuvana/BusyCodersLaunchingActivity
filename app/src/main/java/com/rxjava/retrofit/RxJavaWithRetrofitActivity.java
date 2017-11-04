package com.rxjava.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bala.test.com.busycoders_launchingactivity.R;

public class RxJavaWithRetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_with_retrofit);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutRxJavaRetrofit, RxJavaWithRetrofitFragment.newInstance("", "")).commit();
    }

}
