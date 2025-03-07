/*
 * Copyright JiongBull 2016
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jiongbull.jlog;

import com.jiongbull.jlog.constant.LogLevel;
import com.jiongbull.jlog.printer.DefaultPrinter;
import com.jiongbull.jlog.printer.JsonPrinter;
import com.jiongbull.jlog.printer.Printer;
import com.jiongbull.jlog.util.LogUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * JLog是一个简单的日志工具.
 */
public class JLog {

    /** 日志类名. */
    private static final String LOG_CLASS_NAME = JLog.class.getName();
    /** 日志的打印方法名. */
    private static final String LOG_PRINT_METHOD_NAME = "printLog";

    private static DefaultPrinter sDefaultPrinter;
    private static JsonPrinter sJsonPrinter;

    private static Settings sSettings;

    public static Settings init(@NonNull Context context) {
        sDefaultPrinter = new DefaultPrinter();
        sJsonPrinter = new JsonPrinter();
        sSettings = new Settings();
        return sSettings.setContext(context);
    }

    public static Settings getSettings() {
        return sSettings;
    }

    public static void setSettings(@NonNull Settings settings) {
        sSettings = settings;
    }

    /**
     * 记录“verbose”类型的日志.
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void v(String tag, @NonNull String message) {
        printLog(LogLevel.VERBOSE, tag, null, message);
    }

    /**
     * 记录“verbose”类型的日志（自动生成标签）.
     *
     * @param message 信息
     */
    public static void v(@NonNull String message) {
        printLog(LogLevel.VERBOSE, null, null, message);
    }

    /**
     * 记录“debug”类型的日志.
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void d(String tag, @NonNull String message) {
        printLog(LogLevel.DEBUG, tag, null, message);
    }

    /**
     * 记录“debug”类型的日志（自动生成标签）.
     *
     * @param message 信息
     */
    public static void d(@NonNull String message) {
        printLog(LogLevel.DEBUG, null, null, message);
    }

    /**
     * 记录“info”类型的日志.
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void i(String tag, @NonNull String message) {
        printLog(LogLevel.INFO, tag, null, message);
    }

    /**
     * 记录“info”类型的日志（自动生成标签）.
     *
     * @param message 信息
     */
    public static void i(@NonNull String message) {
        printLog(LogLevel.INFO, null, null, message);
    }

    /**
     * 记录“warn”类型的日志.
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void w(String tag, @NonNull String message) {
        printLog(LogLevel.WARN, tag, null, message);
    }

    /**
     * 记录“warn”类型的日志（自动生成标签）.
     *
     * @param message 信息
     */
    public static void w(@NonNull String message) {
        printLog(LogLevel.WARN, null, null, message);
    }

    /**
     * 记录“error”类型的日志.
     *
     * @param tag     标签
     * @param t       {@link Throwable}
     * @param message 信息
     */
    public static void e(String tag, Throwable t, String message) {
        printLog(LogLevel.ERROR, tag, t, message);
    }

    /**
     * 记录“error”类型的日志（自动生成标签）.
     *
     * @param t       {@link Throwable}
     * @param message 信息
     */
    public static void e(Throwable t, String message) {
        printLog(LogLevel.ERROR, null, t, message);
    }

    /**
     * 记录“error”类型的日志.
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void e(String tag, @NonNull String message) {
        printLog(LogLevel.ERROR, tag, null, message);
    }

    /**
     * 记录“error”类型的日志（自动生成标签）.
     *
     * @param message 信息
     */
    public static void e(@NonNull String message) {
        printLog(LogLevel.ERROR, null, null, message);
    }

    /**
     * 记录“error”类型的日志.
     *
     * @param tag 标签
     * @param t   {@link Throwable}
     */
    public static void e(String tag, @NonNull Throwable t) {
        printLog(LogLevel.ERROR, tag, t, null);
    }

    /**
     * 记录“error”类型的日志（自动生成标签）.
     *
     * @param t {@link Throwable}
     */
    public static void e(@NonNull Throwable t) {
        printLog(LogLevel.ERROR, null, t, null);
    }

    /**
     * 记录“wtf”类型的日志.
     *
     * @param tag     标签
     * @param t       {@link Throwable}
     * @param message 信息
     */
    public static void wtf(String tag, Throwable t, String message) {
        printLog(LogLevel.WTF, tag, t, message);
    }

    /**
     * 记录“wtf”类型的日志（自动生成标签）.
     *
     * @param t       {@link Throwable}
     * @param message 信息
     */
    public static void wtf(Throwable t, String message) {
        printLog(LogLevel.WTF, null, t, message);
    }

    /**
     * 记录“wtf”类型的日志.
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void wtf(String tag, @NonNull String message) {
        printLog(LogLevel.WTF, tag, null, message);
    }

    /**
     * 记录“wtf”类型的日志（自动生成标签）.
     *
     * @param message 信息
     */
    public static void wtf(@NonNull String message) {
        printLog(LogLevel.WTF, null, null, message);
    }

    /**
     * 记录“wtf”类型的日志.
     *
     * @param tag 标签
     * @param t   {@link Throwable}
     */
    public static void wtf(String tag, @NonNull Throwable t) {
        printLog(LogLevel.WTF, tag, t, null);
    }

    /**
     * 记录“wtf”类型的日志（自动生成标签）.
     *
     * @param t {@link Throwable}
     */
    public static void wtf(@NonNull Throwable t) {
        printLog(LogLevel.WTF, null, t, null);
    }

    /**
     * 记录“json”类型的日志.
     *
     * @param tag  标签
     * @param json json
     */
    public static void json(String tag, @NonNull String json) {
        printLog(LogLevel.JSON, tag, null, json);
    }

    /**
     * 记录“json”类型的日志（自动生成标签）.
     *
     * @param json 信息
     */
    public static void json(@NonNull String json) {
        printLog(LogLevel.JSON, null, null, json);
    }

    /**
     * 打印日志.
     *
     * @param level   {@link LogLevel}，日志级别
     * @param tag     标签
     * @param t       {@link Throwable}
     * @param message 信息
     */
    private static void printLog(@NonNull LogLevel level, String tag, Throwable t, String message) {
        if (TextUtils.isEmpty(message)) {
            message = null;
        }
        if (message == null) {
            if (t == null) {
                return; // 不记录没有信息和异常的日志
            }
            message = Log.getStackTraceString(t);
        } else {
            if (t != null) {
                message += Printer.LINE_SEPARATOR + getStackTraceString(t);
            }
        }
        StackTraceElement[] elements = new Throwable().getStackTrace();
        int index = getStackIndex(elements);
        if (index == -1) {
            throw new IllegalStateException("set -keep class com.jiongbull.jlog.** { *; } in your proguard config file");
        }
        StackTraceElement element = elements[index];
        if (TextUtils.isEmpty(tag)) {
            tag = getTag(element);
        }
        Settings settings = JLog.getSettings();
        boolean isOutputToConsole = settings.isDebug();
        boolean isOutputToFile = settings.isWriteToFile() && settings.getLogLevelsForFile().contains(level);
        switch (level) {
            case VERBOSE:
            case DEBUG:
            case INFO:
            case WARN:
            case ERROR:
            case WTF:
                if (isOutputToConsole) {
                    sDefaultPrinter.printConsole(level, tag, message, element);
                }
                if (isOutputToFile) {
                    sDefaultPrinter.printFile(level, tag, message, element);
                }
                break;
            case JSON:
                if (isOutputToConsole) {
                    sJsonPrinter.printConsole(level, tag, message, element);
                }
                if (isOutputToFile) {
                    sJsonPrinter.printFile(level, tag, message, element);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取TAG。
     *
     * @param element 堆栈元素
     * @return TAG
     */
    private static String getTag(@NonNull StackTraceElement element) {
        return LogUtils.getSimpleClassName(element.getClassName());
    }

    /**
     * 获取调用日志类输出方法的堆栈元素索引.
     *
     * @param elements 堆栈元素
     * @return 索引位置，-1 - 不可用
     */
    private static int getStackIndex(@NonNull StackTraceElement[] elements) {
        boolean isChecked = false;
        StackTraceElement element;
        for (int i = 0; i < elements.length; i++) {
            element = elements[i];
            if (LOG_CLASS_NAME.equals(element.getClassName())
                    && LOG_PRINT_METHOD_NAME.equals(element.getMethodName())) {
                isChecked = true;
            }
            if (isChecked) {
                int index = i + 2 + getSettings().getPackagedLevel();
                if (index < elements.length) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * 获取异常栈信息，不同于Log.getStackTraceString()，该方法不会过滤掉UnknownHostException.
     *
     * @param t {@link Throwable}
     * @return 异常栈里的信息
     */
    private static String getStackTraceString(@NonNull Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}