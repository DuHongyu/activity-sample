package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    private String[] data = {"张三", "李四", "王五", "赵六", "张四", "张五"};
    private ArrayAdapter<String> adapter;
    private ListView listView;

    private static final String TAG = "ListViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);
        Log.d(TAG, "onCreate execute");

        listView = findViewById(R.id.lv_one);

        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, data);
        listView.setAdapter(adapter);
    }
}