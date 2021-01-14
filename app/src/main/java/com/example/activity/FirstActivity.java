package com.example.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.activity.utils.CameraUtils;

/**
 * @author Du
 */
public class FirstActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "FirstActivity";

    private static final int PERMISSION_CAMERA_REQUEST_CODE = 0x00000012;

    private ImageView ivPhoto;

    /**
     * 检查权限并拍照。
     * 调用相机前先检查权限。
     */
    private void checkPermissionAndCamera() {
        int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(),
                Manifest.permission.CAMERA);
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
            CameraUtils.getInstance().openCamera(FirstActivity.this);
        } else {
            Log.d(TAG, "申请权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CAMERA_REQUEST_CODE);
        }
    }

    /**
     * 处理权限申请的回调。
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //允许权限，有调起相机拍照。
                Log.d(TAG, "拿到权限");
                CameraUtils.getInstance().openCamera(FirstActivity.this);
            } else {
                //拒绝权限，弹出提示框。
                Toast.makeText(this, R.string.camera_permission_refused, Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate execute");
        setContentView(R.layout.first_layout);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, tempData);
        }

        Button btnOne = findViewById(R.id.btn_one);
        btnOne.setOnClickListener(this);

        Button btnDial = findViewById(R.id.btn_dial);
        btnDial.setOnClickListener(this);

        Button btnMessage = findViewById(R.id.btn_message);
        btnMessage.setOnClickListener(this);

        Button btnCamera = findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(this);

        Button btnStartNormalActivity = findViewById(R.id.btn_start_NormalActivity);
        btnStartNormalActivity.setOnClickListener(this);

        Button btnStartDialogActivity = findViewById(R.id.btn_start_DialogActivity);
        btnStartDialogActivity.setOnClickListener(this);

        ivPhoto = findViewById(R.id.ivPhoto);
        ivPhoto.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.btn_one:
                Toast.makeText(FirstActivity.this, "you click button1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_start_DialogActivity:
                intent.setClass(FirstActivity.this, DialogActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_start_NormalActivity:
                intent.setClass(FirstActivity.this, NormalActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_dial:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
                break;

            case R.id.btn_message:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:1086"));
                startActivity(intent);
                break;

            case R.id.btn_camera:
                checkPermissionAndCamera();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "resultCode:" + resultCode + " requestCode:" + requestCode);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                ivPhoto.setImageBitmap(BitmapFactory.decodeFile(CameraUtils.getInstance().openCamera(FirstActivity.this)));
            } else {
                Toast.makeText(this, "取消", Toast.LENGTH_LONG).show();
            }
        }
    }
}
