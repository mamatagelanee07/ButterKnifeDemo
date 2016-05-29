package com.andyland.butterknifedemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andyland.butterknifedemo.R;

public class NonBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_binding);
    }
}
