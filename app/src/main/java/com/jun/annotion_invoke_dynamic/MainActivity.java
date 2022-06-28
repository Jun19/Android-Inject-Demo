package com.jun.annotion_invoke_dynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jun.annotation.OnClick;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(ids = {R.id.btn})
    public void OnClick(View view) {
        Log.d(TAG, "OnClick: ");
    }

//    @LongClick({R.id.btn})
//    public void OnLongClick(View view) {
//        Log.d(TAG, "OnLongClick: ");
//
//    }

}