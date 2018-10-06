package com.hacknife.crash_exception;

import android.app.Application;

import com.hacknife.exception.CrashException;


/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project :
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashException.getInstance(getApplicationContext(), "/sdcard/Music/");
    }
}
