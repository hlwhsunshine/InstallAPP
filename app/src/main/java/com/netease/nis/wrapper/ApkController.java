package com.netease.nis.wrapper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Function:
 * Project:InstallAPP
 * Date:2018/11/2
 * Created by xiaojun .
 */

public class ApkController {

    public static final int CMD_EXCEPTION = -2;//执行命令发生异常
    public static final int NO_ROOT_PERMISSION = -1;//没有root权限
    public static final int SUCCESS = 0;//操作系统代码0：成功
    public static final int OPERATION_NOT_PERMITTED = 1;//操作系统代码1：操作不允许
    public static final int NO_SUCH_FILE_OR_DIRECTORY = 2;//操作系统代码2：没有这样的文件或目录
    public static final int NO_SUCH_PROCESS = 3;//操作系统代码3：没有这样的过程
    public static final int INTERRUPTED_SYSTEM_CALL = 4;//操作系统代码4：中断的系统调用
    public static final int INPUT_OR_OUTPUT_ERROR = 5;//操作系统代码5：输入/输出错误
    public static final int NO_SUCH_DEVICE_OR_ADDRESS = 6;//操作系统代码6：没有这样的设备或地址
    //其余错误代码自行上网查阅


    /**
     * 开始静默安装
     * @param s 文件路径
     * @param listener 执行结果
     */
    public static void install(String s, InstallListener listener) {
        Process process = null;
        OutputStream out = null;
        if (!isDeviceRooted()) {
            listener.rootResult(NO_ROOT_PERMISSION);
            return;
        }
        try {
            process = Runtime.getRuntime().exec("su");
            out = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            // 获取文件所有权限
            dataOutputStream.writeBytes("chmod 777 " + s
                    + "\n");
            // 进行静默安装命令
            dataOutputStream
                    .writeBytes("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install -r "
                            + s);
            dataOutputStream.flush();
            // 关闭流操作
            dataOutputStream.close();
            out.close();
            int value = process.waitFor();
            Log.e("--value--", "" + value);
            //回调执行结果
            listener.rootResult(value);
        } catch (Exception e) {
            //发生异常
            listener.rootResult(CMD_EXCEPTION);
        }

    }


    /**
     * 静默卸载
     */
    public static boolean clientUninstall(String packageName) {
        PrintWriter PrintWriter = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.println("LD_LIBRARY_PATH=/vendor/lib:/system/lib ");
            PrintWriter.println("pm uninstall " + packageName);
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            return returnResult(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    private static boolean returnResult(int value) {

        // 代表成功
        if (value == 0) {
            return true;
        } else if (value == 1) { // 失败
            return false;
        } else { // 未知情况
            return false;
        }
    }


    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    private static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        String[] paths = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }

    private static boolean checkRootMethod3() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }


    interface InstallListener {
        void rootResult(int resultCode);
    }


}