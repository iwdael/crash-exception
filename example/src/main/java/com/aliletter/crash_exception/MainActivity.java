package com.aliletter.crash_exception;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.aliletter.exception.CrashException;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_bug) {
            Toast.makeText(this, "" + 10 / 0, Toast.LENGTH_SHORT).show();
        } else {
        File file= CrashException.getInstance().getRecentExceptionFile();
            Log.v("TAG","---------->>"+file.getAbsolutePath());
        }
    }
}
