package com.absurd.exception;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * Author: mr-absurd
 * Github: http://github.com/mr-absurd
 * Data: 2017/8/15.
 */

public class SystemUtils {


    /**
     * @Author:mr-absurd
     * @Depict: get System Current Language
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * @Author:mr-absurd
     * @Depict: get System Language List
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * @Author:mr-absurd
     * @Depict: get System Version
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }
    /**
    * @Author:mr-absurd
    * @Depict: get system api level
    */
    public static int getSystemLevel(){
        return Build.VERSION.SDK_INT;
    }

    /**
     * @Author:mr-absurd
     * @Depict: get System Model
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }


    /**
     * @Author:mr-absurd
     * @Depict: get Device Brand
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }


    /**
     * @Author:mr-absurd
     * @Depict: get imei of device,and need android.permission.READ_PHONE_STATE
     */
    public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }


    /**
     * @Author:mr-absurd
     * @Depict: get VersionCode of Application
     */
    public static String getApplicationVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            return pi.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Author:mr-absurd
     * @Depict: get VersionName of Application
     */
    public static String getApplicationVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            return pi.versionName == null ? "null" : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
