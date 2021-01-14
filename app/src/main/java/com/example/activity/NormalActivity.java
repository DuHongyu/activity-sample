package com.example.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.activity.utils.ActivityCollector;

/**
 * @author Du
 */
public class NormalActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "NormalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate execute");
        setContentView(R.layout.normal_layout);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart execute");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume execute");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause execute");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop execute");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy execute");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart execute");
    }

    @Override
    public void onClick(View v) {
        ActivityCollector.getInstance().finishActivity();
    }
}