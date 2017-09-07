package com.absurd.crash_exception;

import android.app.Application;

import com.absurd.exception.CrashException;


/**
 * Author: mr-absurd
 * Github: http://github.com/mr-absurd
 * Data: 2017/8/15.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashException.getInstance().init(getApplicationContext()).setCrashExceptionDir("/sdcard/Music/");
    }
}
