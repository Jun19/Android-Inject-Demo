package com.jun.annotion_invoke_dynamic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jun.annotation.OnClick;
import com.jun.annotation.OnLongClick;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //kt注入
        EventKt.INSTANCE.inject(this);
        //java注入
//        EventUtils.inject(this);
    }

    @OnClick(ids = {R.id.btn, R.id.btn2})
    public void OnClick(View view) {
        Log.d(TAG, "OnClick: " + view.getId());
    }

    @OnLongClick(ids = {R.id.btn, R.id.btn2})
    public boolean OnLongClick(View view) {
        Log.d(TAG, "OnLongClick: " + view.getId());
        return false;
    }

}