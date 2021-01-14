package com.example.activity.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Administrator
 */
public class CameraUtils {

    private static final String TAG = "CameraUtils";

    private CameraUtils() {
    }

    private static final CameraUtils INSTANCE = new CameraUtils();

    public static CameraUtils getInstance() {
        return INSTANCE;
    }

    @SuppressLint("QueryPermissionsNeeded")
    public String openCamera(Activity activity) {
        Log.d(TAG, "相机启动");
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = Environment.getExternalStorageDirectory();
        File tempFile = new File(storageDir + "/DCIM/Camera", imageName + ".jpg");
        Uri uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileProvider", tempFile);
        String mCameraImagePath = tempFile.getAbsolutePath();
        // 判断是否有相机
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        activity.startActivityForResult(captureIntent, 2);
        Log.d(TAG, "Uri:" + uri + "  mCameraImagePath:" + mCameraImagePath);
        return mCameraImagePath;
    }

}
