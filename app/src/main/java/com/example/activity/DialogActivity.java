package com.example.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author Du
 */
public class DialogActivity extends BaseActivity {

    private static final String TAG = "DialogActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
    }
}
