package com.rtmap.test;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rtmap.cameramanager.CameraManager;
import com.rtmap.cameramanager.ICameraListener;
import com.rtmap.cameramanager.R;
import com.rtmap.photocrop.Crop;

import java.io.File;

/**
 * Created by frankqianghe on 16/2/7.
 */
public class TestActivity extends FragmentActivity implements ICameraListener {

    private ImageView testIv;
    private Button takepicture;
    private TestActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        instance=this;
        testIv = (ImageView)this.findViewById(R.id.testIv);
        takepicture = (Button)this.findViewById(R.id.takepicture);
        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraManager.getInst().openCamera(TestActivity.this, instance);
            }
        });

    }


    @Override
    public void takePhotoSuccess(Uri photoUri) {
        testIv.setImageDrawable(null);
        testIv.setImageURI(photoUri);
    }

    @Override
    public void takePhotoFailed() {

    }
}
