package com.example.taobao.utils;

import android.util.Log;

public class LogUtils {
    // 控制日志开关，控制不同级别的日志
    private static int LOG_LEVEL = 0;  // 0 - VERBOSE, 1 - DEBUG, 2 - INFO, 3 - WARN, 4 - ERROR

    // VERBOSE
    private static final int VERBOSE = 0;
    // DEBUG
    private static final int DEBUG = 1;
    // INFO
    private static final int INFO = 2;
    // WARN
    private static final int WARN = 3;
    // ERROR
    private static final int ERROR = 4;

    // 打印调试日志
    public static void d(Class c, String log) {
        if (LOG_LEVEL <= DEBUG) {  // 判断当前日志等级是否允许输出调试日志
            Log.d(c.getSimpleName(), log);
        }
    }

    // 打印信息日志
    public static void i(Class c, String log) {
        if (LOG_LEVEL <= INFO) {  // 判断当前日志等级是否允许输出信息日志
            Log.i(c.getSimpleName(), log);
        }
    }

    // 打印警告日志
    public static void w(Class c, String log) {
        if (LOG_LEVEL <= WARN) {  // 判断当前日志等级是否允许输出警告日志
            Log.w(c.getSimpleName(), log);
        }
    }

    // 打印错误日志
    public static void e(Class c, String log) {
        if (LOG_LEVEL <= ERROR) {  // 判断当前日志等级是否允许输出错误日志
            Log.e(c.getSimpleName(), log);
        }
    }

    // 设置日志等级
    public static void setLogLevel(int level) {
        LOG_LEVEL = level;
    }

    // 获取当前日志等级
    public static int getLogLevel() {
        return LOG_LEVEL;
    }
}
