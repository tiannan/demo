package com.meituan.metrics.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.meituan.android.common.statistics.Constants.EventType;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.config.MetricsLocalSwitchConfigManager;
import com.meituan.metrics.sampler.MetricSampleManager;
import com.meituan.metrics.sys.SysStatisticsManager;
import com.meituan.metrics.util.AppUtils;
import com.meituan.metrics.util.TimeUtil;
import com.meituan.metrics.util.thread.Task;
import com.meituan.metrics.util.thread.ThreadManager;
import com.meituan.robust.common.CommonConstant.Symbol;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: ProGuard */
public class MetricsActivityLifecycleManager implements ActivityLifecycleCallbacks {
    private static LinkedList<String> actionTrack = new LinkedList();
    private static final int actionTrackMaxCount = 20;
    public static String currentActivity = "";
    private static MetricsActivityLifecycleManager sInstance;
    private final List<MetricsAppMonitorCallback> appMonitorCallbacks;
    private WeakReference<Object> currentFragmentRef;
    private final List<MetricsActivityLifecycleCallback> mCallbacks;
    private WeakReference<Activity> topActivity;

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public static MetricsActivityLifecycleManager getInstance() {

        if (sInstance == null) {
            synchronized (MetricsActivityLifecycleManager.class) {
                if (sInstance == null) {
                    sInstance = new MetricsActivityLifecycleManager();
                }
            }
        }
        return sInstance;
    }

    public MetricsActivityLifecycleManager() {

        this.topActivity = new WeakReference(null);
        this.mCallbacks = new CopyOnWriteArrayList();
        this.appMonitorCallbacks = new CopyOnWriteArrayList();
        Context context = Metrics.getInstance().getContext();
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(this);
            return;
        }
        throw new IllegalArgumentException("context参数必须是Application类型");
    }

    public void register(MetricsActivityLifecycleCallback metricsActivityLifecycleCallback) {
        if (metricsActivityLifecycleCallback != null) {
            this.mCallbacks.add(metricsActivityLifecycleCallback);
        }
    }

    public void registerAppMonitor(MetricsAppMonitorCallback metricsAppMonitorCallback) {
        if (metricsAppMonitorCallback != null) {
            this.appMonitorCallbacks.add(metricsAppMonitorCallback);
        }
    }

    public void unRegister(MetricsActivityLifecycleCallback metricsActivityLifecycleCallback) {

            this.mCallbacks.remove(metricsActivityLifecycleCallback);

    }

    public void unRegisterAppMonitor(MetricsAppMonitorCallback metricsAppMonitorCallback) {

            this.appMonitorCallbacks.remove(metricsAppMonitorCallback);

    }

    public void setCurrentFragment(Fragment fragment) {

            this.currentFragmentRef = new WeakReference(fragment);

    }

    public void setCurrentFragment(android.support.v4.app.Fragment fragment) {

            this.currentFragmentRef = new WeakReference(fragment);

    }

    public void setFragment(Object obj) {
       if (obj instanceof Fragment) {
            setCurrentFragment((Fragment) obj);
        } else {
            if (obj instanceof android.support.v4.app.Fragment) {
                setCurrentFragment((android.support.v4.app.Fragment) obj);
            }
        }
    }

    public void clearCurrentFragment() {
        this.currentFragmentRef = null;
    }

    public Object getCurrentFragment() {

        if (this.currentFragmentRef == null) {
            return null;
        }
        return this.currentFragmentRef.get();
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {

        StringBuilder stringBuilder = new StringBuilder("create");
        Intent intent = activity.getIntent();
        CharSequence charSequence = "";
        try {
            charSequence = intent.getData().toString();
        } catch (Throwable unused) {
        }
        if (!TextUtils.isEmpty(charSequence)) {
            stringBuilder.append("{data=");
            stringBuilder.append(charSequence);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                for (String str2 : extras.keySet()) {
                    stringBuilder2.append(str2);
                    stringBuilder2.append(Symbol.COLON);
                    stringBuilder2.append(extras.get(str2));
                    stringBuilder2.append(Symbol.COMMA);
                }
            }
        } catch (Throwable unused2) {
        }
        String stringBuilder3 = stringBuilder2.toString();
        if (!TextUtils.isEmpty(stringBuilder3)) {
            stringBuilder.append("(extras=");
            stringBuilder.append(stringBuilder3);
            stringBuilder.append(Symbol.BRACKET_RIGHT);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            stringBuilder.append(", flags=0x");
            stringBuilder.append(Integer.toHexString(intent.getFlags()));
            stringBuilder.append(Symbol.BIG_BRACKET_RIGHT);
        }
        logAction(activity, stringBuilder.toString());
        if (SysStatisticsManager.getInstance().isReported()) {
            ThreadManager.getInstance().closeReport();
        } else {
            final Context applicationContext = activity.getApplicationContext();
            ThreadManager.getInstance().postReport(new Task() {

                public void schedule() {

                        SysStatisticsManager.getInstance().reportSysDataOnce(applicationContext);
                }

            });
        }
        if (MetricsLocalSwitchConfigManager.getInstance().getLocalSwitchConfig(AppUtils.getPageName(activity)).getSampleSw()) {
            MetricSampleManager.getInstance().startSample();
        } else {
            MetricSampleManager.getInstance().stopSample();
        }
    }

    public void onActivityStarted(Activity activity) {

        logAction(activity, EventType.START);
        if (this.topActivity == null) {
            onForeground();
        }
        this.topActivity = new WeakReference(activity);
    }

    public void onActivityResumed(Activity activity) {

        logAction(activity, "resume");
        currentActivity = AppUtils.getPageName(activity);
        if (this.mCallbacks != null && this.mCallbacks.size() > 0) {
            for (MetricsActivityLifecycleCallback onActivityResumed : this.mCallbacks) {
                onActivityResumed.onActivityResumed(activity);
            }
        }
    }

    public void onActivityPaused(Activity activity) {

        logAction(activity, "pause");
        if (this.mCallbacks != null && this.mCallbacks.size() > 0) {
            for (MetricsActivityLifecycleCallback onActivityPaused : this.mCallbacks) {
                onActivityPaused.onActivityPaused(activity);
            }
        }
    }

    public void onActivityStopped(Activity activity) {

        logAction(activity, "stop");
        if (this.mCallbacks != null && this.mCallbacks.size() > 0) {
            for (MetricsActivityLifecycleCallback onActivityStopped : this.mCallbacks) {
                onActivityStopped.onActivityStopped(activity);
            }
        }
        if (this.topActivity != null && this.topActivity.get() == activity) {
            onBackground();
            this.topActivity = null;
        }
    }

    public void onActivityDestroyed(Activity activity) {

            logAction(activity, "destroy");

    }

    private void onForeground() {

        for (MetricsAppMonitorCallback onForeground : this.appMonitorCallbacks) {
            onForeground.onForeground();
        }
    }

    private void onBackground() {

        for (MetricsAppMonitorCallback onBackground : this.appMonitorCallbacks) {
            onBackground.onBackground();
        }
    }

    private static void logAction(Activity activity, String str) {

        if (activity != null) {
            String formatDateTime = TimeUtil.formatDateTime(TimeUtil.currentTimeMillis());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(formatDateTime);
            stringBuilder.append(StringUtil.SPACE);
            stringBuilder.append(activity.getClass().getName());
            stringBuilder.append(Symbol.AT);
            stringBuilder.append(activity.hashCode());
            stringBuilder.append("_");
            stringBuilder.append(str);
            logAction(stringBuilder.toString());
        }
    }

    public static void logAction(String str) {

        synchronized (MetricsActivityLifecycleManager.class) {
            while (actionTrack.size() >= 20) {
                actionTrack.poll();
            }
            actionTrack.offer(str);
        }
    }

    public static String getActions() {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            ArrayList arrayList;
            synchronized (MetricsActivityLifecycleManager.class) {
                arrayList = new ArrayList(actionTrack);
            }
            Collections.reverse(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuilder.append((String) it.next());
                stringBuilder.append(";");
            }
            return stringBuilder.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}