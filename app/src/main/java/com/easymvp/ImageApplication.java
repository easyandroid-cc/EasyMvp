package com.easymvp;

import android.app.Application;
import android.content.Context;


/**
 * app
 */
public class ImageApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //这里必须在super.onCreate方法之后，顺序不能变


    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
