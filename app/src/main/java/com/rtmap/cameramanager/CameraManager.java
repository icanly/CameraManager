package com.rtmap.cameramanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.Stack;

/**
 * 相机管理类
 * Created by sky on 15/7/6.
 * Weibo: http://weibo.com/2030683111
 * Email: 1132234509@qq.com
 */
public class CameraManager {


    private static CameraManager mInstance;
    private ICameraListener mCameraListener;
    private Stack<Activity> cameras = new Stack<Activity>();
    private Context context;

    public static CameraManager getInst() {
        if (mInstance == null) {
            synchronized (CameraManager.class) {
                if (mInstance == null)
                    mInstance = new CameraManager();
            }
        }
        return mInstance;
    }

    //打开照相界面
    public void openCamera(Context context, ICameraListener listener) {
        this.context = context;
        this.mCameraListener = listener;
        Intent intent = new Intent(context, CameraActivity.class);
        context.startActivity(intent);
    }


    //判断图片是否需要裁剪
    public void processPhotoItem(Uri photoUri) {
        if(mCameraListener!=null){
            mCameraListener.takePhotoSuccess(photoUri);
            close();
        }
    }


    public void close() {
        for (Activity act : cameras) {
            try {
                act.finish();
            } catch (Exception e) {

            }
        }
        cameras.clear();
    }

    public void addActivity(Activity act) {
        cameras.add(act);
    }

    public void removeActivity(Activity act) {
        cameras.remove(act);
    }



}
