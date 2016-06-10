/**
 *
 *
 */

package com.andyland.firebasedemo.common.util;

import android.support.v4.util.LogWriter;
import android.util.Log;

/**
 * This is a wrapper of default Log. This class give flexibility to enable/disable logging. By default logging
 * is enable but consumer can disable it by changing the value of <b>LOG_ENABLE</b> variable.
 *
 * @author Mamata Gelanee <br>
 *         {@link LogWriter}
 */
public class Logger {
    /**
     * Set the log enable or disable before using the class. By default logging is enabled. If the value is true then
     * logging is enabled and if the value is false then logging is disabled.
     */
    public static boolean LOG_ENABLE = true;

    public static void d(String tag, String msg) {

        if (LOG_ENABLE) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {

        if (LOG_ENABLE) {
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {

        if (LOG_ENABLE) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {

        if (LOG_ENABLE) {
            Log.i(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {

        if (LOG_ENABLE) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {

        if (LOG_ENABLE) {
            Log.e(tag, msg, tr);
        }
    }

    public static void v(String tag, String msg) {

        if (LOG_ENABLE) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {

        if (LOG_ENABLE) {
            Log.v(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {

        if (LOG_ENABLE) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {

        if (LOG_ENABLE) {
            Log.w(tag, msg, tr);
        }
    }
}
