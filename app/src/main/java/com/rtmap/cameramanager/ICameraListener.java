package com.rtmap.cameramanager;

import android.net.Uri;

/**
 * Created by frankqianghe on 16/2/7.
 */
public interface ICameraListener {

    public void takePhotoSuccess(Uri photoUri);
    public void takePhotoFailed();

}
