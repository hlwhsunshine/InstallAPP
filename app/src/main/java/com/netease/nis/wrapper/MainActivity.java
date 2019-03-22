package com.netease.nis.wrapper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import net.com.shuame.rootgeniusauuk.R;

import java.io.DataOutputStream;
import java.io.File;

import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.netease.nis.wrapper.ApkController.CMD_EXCEPTION;
import static com.netease.nis.wrapper.ApkController.INPUT_OR_OUTPUT_ERROR;
import static com.netease.nis.wrapper.ApkController.INTERRUPTED_SYSTEM_CALL;
import static com.netease.nis.wrapper.ApkController.NO_ROOT_PERMISSION;
import static com.netease.nis.wrapper.ApkController.NO_SUCH_DEVICE_OR_ADDRESS;
import static com.netease.nis.wrapper.ApkController.NO_SUCH_FILE_OR_DIRECTORY;
import static com.netease.nis.wrapper.ApkController.NO_SUCH_PROCESS;
import static com.netease.nis.wrapper.ApkController.OPERATION_NOT_PERMITTED;
import static com.netease.nis.wrapper.ApkController.SUCCESS;

import org.xmlpull.v1.XmlSerializer;

public class MainActivity extends AppCompatActivity {
    Writer stringWriter = new StringWriter();
    XmlSerializer newSerializer = Xml.newSerializer();
    private ArrayList<String> filePaths;

    ExecutorService cachedThreadPool;

    private ProgressBar progressBar;

    private Button bt;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressBar.setVisibility(View.GONE);
            bt.setEnabled(true);
            switch (msg.what) {
                case -3:
                    progressBar.setVisibility(View.GONE);
                    showToast("没有找到ecalendar.apk");
                    break;
                case CMD_EXCEPTION:
                    showToast("执行静默安装命令时发生异常");
                    break;

                case NO_ROOT_PERMISSION:
                    showToast("没有root权限");
                    break;

                case SUCCESS:
                    showToast("安装成功,process返回值："+msg.what);
                    break;

                case OPERATION_NOT_PERMITTED:
                    showToast("安装失败，操作系统代码1：操作不允许,process返回值："+msg.what);
                    break;
                case NO_SUCH_FILE_OR_DIRECTORY:
                    showToast("安装失败，操作系统代码2：没有这样的文件或目录,process返回值："+msg.what);
                    break;
                case NO_SUCH_PROCESS:
                    showToast("安装失败，操作系统代码3：没有这样的过程,process返回值："+msg.what);
                    break;

                case INTERRUPTED_SYSTEM_CALL:
                    showToast("安装失败，操作系统代码4：中断的系统调用,process返回值："+msg.what);
                    break;

                case INPUT_OR_OUTPUT_ERROR:
                    showToast("安装失败，操作系统代码5：输入/输出错误,process返回值："+msg.what);
                    break;
                case NO_SUCH_DEVICE_OR_ADDRESS:
                    showToast("安装失败，操作系统代码6：没有这样的设备或地址,process返回值："+msg.what);
                    break;
                default:
                    showToast("安装失败，process返回值："+msg.what+"请上网查阅");
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        String s = new ProtoData.ActExit().toString();
        Log.e("MainActivty",getPackageName());
        //com.annotion.ruiyi.testcsocket.Util.run(this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                org.liux.android.demo.tit.CallTools.getInstance().run(MainActivity.this);
                /*if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                } else {
                    bt.setEnabled(false);
                    cachedThreadPool.execute(new MyRunnable());
                    progressBar.setVisibility(View.VISIBLE);
                }*/
            }
        });

    }





    private void init() {
        cachedThreadPool = Executors.newCachedThreadPool();
        progressBar = findViewById(R.id.pb);
        Log.e("+++++++",Environment.getExternalStorageDirectory().getPath());
        bt = findViewById(R.id.bt);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }

    }

    private void showToast(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            filePaths = new ArrayList<>();
            String path = Environment.getExternalStorageDirectory().getPath();
            Log.e("------根目录：", path);
            File[] files = new File(path).listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile() && files[i].getName().contains(".apk")) {
                    String filePath = files[i].getPath();
                    filePaths.add(filePath);
                    Log.e("------文件为：",filePath);
                }
            }

            try {
                File f = new File(path + "/ecalendar.apk");
                if (!f.exists()) {
                    handler.sendEmptyMessage(-3);
                    return;
                }
            } catch (Exception e) {
                handler.sendEmptyMessage(-3);
                return;
            }
//            if (filePaths.size() != 0) {
            ApkController.install(path + "/ecalendar.apk", new ApkController.InstallListener() {
                @Override
                public void rootResult(int resultCode) {
                    handler.sendEmptyMessage(resultCode);

                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(MainActivity.this);
    }
}
