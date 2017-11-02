package com.aliletter.exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Author: aliletter
 * Github: http://github.com/aliletter
 * Data: 2017/8/15.
 */

public class CrashException implements UncaughtExceptionHandler {
    private static volatile CrashException instance = null;
    private static final String TAG = "CrashException";

    //异常保存的文件夹
    private String mCrashExceptionDir;
    private String mPromptContent = "很抱歉,程序出现异常,即将退出.";


    //系统默认的UncaughtException处理类
    private UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例

    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new LinkedHashMap<>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashException(Context context, String dir) {
        mCrashExceptionDir = dir;
        init(context);
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashException getInstance() {
        if (instance == null) {
            synchronized (CrashException.class) {
                if (instance == null)
                    throw new RuntimeException("You must first implement two parameter constructor before you use CrashException !");
            }
        }
        return instance;
    }

    public static CrashException getInstance(Context context, String dir) {
        if (instance == null) {
            synchronized (CrashException.class) {
                if (instance == null)
                    instance = new CrashException(context, dir);
            }
        }
        return instance;
    }


    /**
     * 初始化
     *
     * @param context
     */
    private CrashException init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        return instance;
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                if (!mPromptContent.equalsIgnoreCase(""))
                    Toast.makeText(mContext, mPromptContent, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(mContext, ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    private void collectDeviceInfo(Context ctx) {

        infos.put("versionCode", SystemUtils.getApplicationVersionCode(ctx));
        infos.put("systemModel", SystemUtils.getSystemModel());
        infos.put("deviceBrand", SystemUtils.getDeviceBrand());
        infos.put("systemLevel", SystemUtils.getSystemLevel() + "");
        infos.put("systemVersion", SystemUtils.getSystemVersion());

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Context context, Throwable ex) {
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        try {
            long timestamp = System.currentTimeMillis();
            String fileName = "crash-" + SystemUtils.getSystemModel() + "-" + SystemUtils.getSystemVersion() + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(mCrashExceptionDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(mCrashExceptionDir + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }

    public String getCrashExceptionDir() {
        return mCrashExceptionDir;
    }


    public void setPromptContent(String mPromptContent) {
        this.mPromptContent = mPromptContent;
    }

    public String getPromptContent() {
        return mPromptContent;
    }

    public File getRecentExceptionFile() {
        File dir = new File(mCrashExceptionDir);
        File[] crashs = dir.listFiles();
        File result = null;
        long recent = 0;
        if (crashs == null) return null;
        for (File crash : crashs) {
            String name = crash.getName();
            if (!name.contains("crash")) continue;
            int sdex = name.lastIndexOf('-');
            int edex = name.lastIndexOf('.');
            long stamp = Long.parseLong(name.substring(sdex + 1, edex));
            if (stamp > recent) {
                recent = stamp;
                result = crash;
            }
        }
        return result;
    }

    public void clearExceptionFile() {
        File dir = new File(mCrashExceptionDir);
        File[] crashs = dir.listFiles();
        for (File crash : crashs) {
            crash.delete();
        }
    }
}
