package com.kia.betterlog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Log {
    public static String frameworkTag = "";
    public static boolean enable = true;

    private static final int DEBUG = 0;
    private static final int INFORMATION = 1;
    private static final int VERBOSE = 2;
    private static final int WARNING = 3;
    private static final int ERROR = 4;

    private static void writeLog(int level, List<String> labels, String message, Throwable tr) {
        if (!enable) return;
        if (message == null) message = "";
        if (labels == null) labels = new ArrayList<>();
        List<String> lstAllLabel = new ArrayList<>();

        String logMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            boolean foundMe = false;
            for (int i = 0; i < stackTrace.length; i++) {
                StackTraceElement e = stackTrace[i];
                String methodName = e.getMethodName();
                if (foundMe) {
                    if (!methodName.startsWith("access$")) {
                        // Add method name that call log as a label
                        lstAllLabel.add(e.getClassName() + "." + methodName);

                        // Add user defined labels
                        lstAllLabel.addAll(labels);

                        // Convert label list to string and add to message
                        message = createLabel(lstAllLabel) + message;

                        // Write log
                        switch (level) {
                            case DEBUG:
                                android.util.Log.d(frameworkTag, message, tr);
                                break;
                            case INFORMATION:
                                android.util.Log.i(frameworkTag, message, tr);
                                break;
                            case VERBOSE:
                                android.util.Log.v(frameworkTag, message, tr);
                                break;
                            case WARNING:
                                android.util.Log.w(frameworkTag, message, tr);
                                break;
                            case ERROR:
                                android.util.Log.e(frameworkTag, message, tr);
                                break;
                            default:
                                throw new Exception("Invalid level.");
                        }
                        return;
                    }
                } else {
                    if (Log.class.getCanonicalName().equals(e.getClassName())) {

                        //Escape log method name
                        if (i + 1 < stackTrace.length && stackTrace[i + 1].getClassName().equals(Log.class.getCanonicalName()))
                            continue;

                        foundMe = true;
                    }
                }
            }

            android.util.Log.e(frameworkTag, createLabel(logMethodName) + message);
        } catch (Exception ex) {
            android.util.Log.e(frameworkTag, createLabel(logMethodName) + "Can't log. More detail:" + ex);
        }
    }

    private static String createLabel(String label) {
        return "[" + label + "]";
    }

    private static String createLabel(List<String> labels) {
        String allLabels = "";
        for (String label : labels) {
            allLabels += createLabel(label);
        }
        return allLabels;
    }

    //region Debug
    public static void d(String message) {
        writeLog(DEBUG, null, message, null);
    }

    public static void d(String label, String message) {
        writeLog(DEBUG, Collections.singletonList(label), message, null);
    }

    public static void d(List<String> labels, String message) {
        writeLog(DEBUG, labels, message, null);
    }
    //endregion

    //region Information
    public static void i(String message) {
        writeLog(INFORMATION, null, message, null);
    }

    public static void i(String label, String message) {
        writeLog(INFORMATION, Collections.singletonList(label), message, null);
    }

    public static void i(List<String> labels, String message) {
        writeLog(INFORMATION, labels, message, null);
    }
    //endregion

    //region Verbose
    public static void v(String message) {
        writeLog(VERBOSE, null, message, null);
    }

    public static void v(String label, String message) {
        writeLog(VERBOSE, Collections.singletonList(label), message, null);
    }

    public static void v(List<String> labels, String message) {
        writeLog(VERBOSE, labels, message, null);
    }
    //endregion

    //region Warning
    public static void w(String message) {
        writeLog(WARNING, null, message, null);
    }

    public static void w(String label, String message) {
        writeLog(WARNING, Collections.singletonList(label), message, null);
    }

    public static void w(List<String> labels, String message) {
        writeLog(WARNING, labels, message, null);
    }
    //endregion

    //region Error
    public static void e(Throwable tr) {
        writeLog(ERROR, null, tr.toString(), tr);
    }

    public static void e(String message) {
        writeLog(ERROR, null, message, null);
    }

    public static void e(String message, Throwable tr) {
        writeLog(ERROR, null, message, tr);
    }

    public static void e(String label, String message) {
        writeLog(ERROR, Collections.singletonList(label), message, null);
    }

    public static void e(String label, String message, Throwable tr) {
        writeLog(ERROR, Collections.singletonList(label), message, tr);
    }

    public static void e(List<String> labels, String message) {
        writeLog(ERROR, labels, message, null);
    }

    public static void e(List<String> labels, String message, Throwable tr) {
        writeLog(ERROR, labels, message, tr);
    }
    //endregion

}

