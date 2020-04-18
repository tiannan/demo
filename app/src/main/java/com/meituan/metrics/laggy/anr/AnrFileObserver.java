package com.meituan.metrics.laggy.anr;

import android.os.FileObserver;

import androidx.annotation.Nullable;

import com.meituan.metrics.util.LogUtil;
import com.meituan.metrics.util.TimeUtil;


public class AnrFileObserver extends FileObserver {
    private AnrCallback anrCallback;

    public AnrFileObserver(String str, AnrCallback anrCallback) {
        super(str, 8);
        Object[] objArr = new Object[]{str, anrCallback};

        this.anrCallback = anrCallback;

    }

    public void onEvent(int i, @Nullable String str) {

        str = resolvePath(str);
        LogUtil.d(getClass().getSimpleName(), "path:", str);
        if (this.anrCallback != null) {
            System.out.println("AnrFileObserver onAnrEvent");
            this.anrCallback.onAnrEvent(TimeUtil.currentTimeMillis(), str, null);
        }
    }

    private String resolvePath(String str) {

        if (str == null || "binderinfo".equals(str.toLowerCase())) {
            str = "traces.txt";
        }
        return str;
    }
}