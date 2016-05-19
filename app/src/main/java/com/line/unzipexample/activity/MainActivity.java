package com.line.unzipexample.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.line.unzipexample.R;
import com.line.unzipexample.util.UnzipUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 在sd卡中放入相应的zip压缩文件
        String zipFile = Environment.getExternalStorageDirectory() + "/testzip.zip";
        String extractDirectory = Environment.getExternalStorageDirectory() + "/test";
        UnzipUtil.unzip(zipFile, extractDirectory);

    }
}
