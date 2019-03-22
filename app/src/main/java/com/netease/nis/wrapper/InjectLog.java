package com.netease.nis.wrapper;

import android.util.Log;

/**
 * Function:
 * Project:InstallAPP
 * Date:2018/11/5
 * Created by xiaojun .
 */

public class InjectLog {
    public static void PrintFunc() {
        Thread cur_thread = Thread.currentThread();
        StackTraceElement stack[] = cur_thread.getStackTrace();
        Log.d("InjectLog", stack[3].toString() + "[" + cur_thread.getId() + "]");
    }
}
