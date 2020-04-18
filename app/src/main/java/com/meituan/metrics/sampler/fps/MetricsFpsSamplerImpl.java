package com.meituan.metrics.sampler.fps;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;

import androidx.annotation.UiThread;

import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.common.Constants;
import com.meituan.metrics.config.MetricsLocalSwitchConfigManager;
import com.meituan.metrics.config.MetricsRemoteConfigManager;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.util.AppUtils;
import com.meituan.metrics.util.LogUtil;
import com.meituan.metrics.util.TimeUtil;
import com.meituan.metrics.util.thread.ThreadManager;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@TargetApi(16)
/* compiled from: ProGuard */
public class MetricsFpsSamplerImpl implements FrameCallback, MetricsFpsSampler {
    private static final int MAX_SCROLL_GAP_MS = 80;
    private static final String TAG = "metrics FpsSampler";
    private Runnable cancelScrollTask;
    private long currentFrameTotalCostTime;
    private int currentFrameTotalCount;
    private Map<String, FpsEvent> customEvents;
    private boolean customScrolling;
    private long frameStartTime;
    private Choreographer mChoreographer;
    private final Handler mainHandler;
    private double nowFPS;
    private FpsEvent pageFpsEvent;
    private boolean recording;
    private long sampleTimeInNs;
    private Handler samplerHandler;
    private final OnScrollChangedListener scrollChangedListener;
    private boolean scrollFpsEnabled;
    private FpsEvent scrollFpsEvent;
    private volatile boolean scrollListenerRegistered;
    private int startSampleFrameCount;
    private long startSampleTimeInNs;
    private Runnable startScrollTask;
    private Runnable stopScrollTask;

    class FpsScrollChangeListener implements OnScrollChangedListener {
        private static final int MIN_SCROLLING_STEPS = 5;
        private long scrollStartStamp;
        private Runnable scrollStopped;
        public boolean scrolling;
        private int scrollingCount;

        public FpsScrollChangeListener() {

            this.scrollingCount = 0;
            this.scrollStopped = new Runnable() {

                public void run() {

                    FpsScrollChangeListener.this.scrolling = false;
                    if (TimeUtil.elapsedTimeMillis() - FpsScrollChangeListener.this.scrollStartStamp > 160 && FpsScrollChangeListener.this.scrollingCount >= 5) {
                        MetricsFpsSamplerImpl.this.samplerHandler.post(MetricsFpsSamplerImpl.this.stopScrollTask);
                        LogUtil.d(MetricsFpsSamplerImpl.TAG, "stopScrollTask------");
                    } else if (TimeUtil.elapsedTimeMillis() - FpsScrollChangeListener.this.scrollStartStamp <= 80 || FpsScrollChangeListener.this.scrollingCount <= 2) {
                        MetricsFpsSamplerImpl.this.samplerHandler.post(MetricsFpsSamplerImpl.this.cancelScrollTask);
                        LogUtil.d(MetricsFpsSamplerImpl.TAG, "cancelScrollTask------");
                    } else {
                        MetricsFpsSamplerImpl.this.samplerHandler.post(MetricsFpsSamplerImpl.this.stopScrollTask);
                        LogUtil.d(MetricsFpsSamplerImpl.TAG, "test------");
                    }
                }
            };
            this.scrolling = false;
        }

        public /* synthetic */ FpsScrollChangeListener(MetricsFpsSamplerImpl metricsFpsSamplerImpl, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onScrollChanged() {

            MetricsFpsSamplerImpl.this.mainHandler.removeCallbacks(this.scrollStopped);
            if (!this.scrolling) {
                this.scrolling = true;
                this.scrollingCount = 0;
                this.scrollStartStamp = TimeUtil.elapsedTimeMillis();
                MetricsFpsSamplerImpl.this.samplerHandler.post(MetricsFpsSamplerImpl.this.startScrollTask);
            }
            MetricsFpsSamplerImpl.this.mainHandler.postDelayed(this.scrollStopped, 80);
            this.scrollingCount++;
            LogUtil.d(MetricsFpsSamplerImpl.TAG, "scrollingCount------", Integer.valueOf(this.scrollingCount));
        }
    }

    public MetricsFpsSamplerImpl(Handler handler) {

        this.frameStartTime = 0;
        this.customEvents = new ConcurrentHashMap();
        this.stopScrollTask = new Runnable() {

            public void run() {
                MetricsFpsSamplerImpl.this.stopScrollFPS();

            }
        };
        this.cancelScrollTask = new Runnable() {

            public void run() {

                    MetricsFpsSamplerImpl.this.cancelScrollFPS();

            }
        };
        this.startScrollTask = new Runnable() {

            public void run() {

                    MetricsFpsSamplerImpl.this.startScrollFPS();

            }
        };
        this.sampleTimeInNs = TimeUnit.NANOSECONDS.convert(1000, TimeUnit.MILLISECONDS);
        this.samplerHandler = handler;
        this.scrollChangedListener = new FpsScrollChangeListener(this, null);
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public void setScrollEntityCustom(final Activity activity) {

        if (this.scrollListenerRegistered) {
            ThreadManager.getInstance().runOnUiThread(new Callable<Void>() {

                public Void call() throws Exception {

                    MetricsFpsSamplerImpl.this.unRegisterGlobalScrollCallback(activity);
                    return null;
                }
            });
        }
        if (this.scrollFpsEvent != null && TextUtils.equals(AppUtils.getPageName(activity), this.scrollFpsEvent.getName())) {
            this.scrollFpsEvent.scrollType = "custom";
        }
    }

    public void pageEnter(final Activity activity) {
         if (MetricsLocalSwitchConfigManager.getInstance().getFPSSw(AppUtils.getPageName(activity))) {
            if (!(this.mChoreographer == null || this.recording)) {
                this.mChoreographer.postFrameCallback(this);
                this.recording = true;
            }
            if (this.mChoreographer == null) {
                this.mChoreographer = (Choreographer) ThreadManager.getInstance().runOnUiThread(new Callable<Choreographer>() {

                    public Choreographer call() throws Exception {
                       return  Choreographer.getInstance();
                    }
                });
                if (this.mChoreographer != null) {
                    this.mChoreographer.postFrameCallback(this);
                    this.recording = true;
                } else {
                    return;
                }
            }
            if (!this.recording) {
                this.mChoreographer.postFrameCallback(this);
                this.recording = true;
            }
            String pageName = AppUtils.getPageName(activity, MetricsActivityLifecycleManager.currentActivity);
            if (MetricsRemoteConfigManager.getInstance().getFpsPageConfig(pageName) != -1) {
                startRecordPageFps(pageName);
            }
            if (MetricsRemoteConfigManager.getInstance().getFpsScrollConfig(pageName) != -1) {
                this.scrollFpsEvent = new FpsEvent(Constants.FPS_TYPE_SCROLL, pageName);
                ThreadManager.getInstance().runOnUiThread(new Callable<Void>() {

                    public Void call() {

                        MetricsFpsSamplerImpl.this.registerGlobalScrollCallback(activity);
                        return null;
                    }
                });
            }
        } else {
            reset();
            this.scrollFpsEvent = null;
            this.scrollFpsEnabled = false;
        }
    }

    @UiThread
    private void registerGlobalScrollCallback(Activity activity) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (window != null) {
                try {
                    window.getDecorView().getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
                    this.scrollListenerRegistered = true;
                } catch (Exception e) {
                    LogUtil.i(TAG, "register global scroll listener failed", e);
                }
            }
        }
    }

    public void pageExit(final Activity activity) {

        stopRecordPageFps(activity, MetricsActivityLifecycleManager.getInstance().getCurrentFragment());
        MetricsActivityLifecycleManager.getInstance().clearCurrentFragment();
        ThreadManager.getInstance().runOnUiThread(new Callable<Void>() {

            public Void call() throws Exception {

                MetricsFpsSamplerImpl.this.unRegisterGlobalScrollCallback(activity);
                return null;
            }
        });
    }

    @UiThread
    private void unRegisterGlobalScrollCallback(Activity activity) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (window != null) {
                try {
                    window.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(this.scrollChangedListener);
                    this.scrollListenerRegistered = false;
                } catch (Exception e) {
                    LogUtil.i(TAG, "unregister global scroll listener failed", e);
                }
            }
        }
    }

    public void changeToFragment(Object obj) {
        int i = 1;
        Activity activity = null;
        if (obj instanceof Fragment) {
            activity = ((Fragment) obj).getActivity();
        } else if (obj instanceof android.support.v4.app.Fragment) {
            activity = ((android.support.v4.app.Fragment) obj).n();
        } else {
            i = 0;
        }
        if (activity == null) {
            if (i != 0) {
                MetricsActivityLifecycleManager.getInstance().setFragment(obj);
            }
            return;
        }
        Object currentFragment = MetricsActivityLifecycleManager.getInstance().getCurrentFragment();
        if (currentFragment != obj) {
            if (currentFragment == null) {
                MetricsActivityLifecycleManager.getInstance().setFragment(obj);
                return;
            }
            stopRecordPageFps(activity, currentFragment);
            ThreadManager.getInstance().runOnUiThread(new Callable<Void>() {

                public Void call() throws Exception {

                    MetricsFpsSamplerImpl.this.unRegisterGlobalScrollCallback(activity);
                    return null;
                }
            });
            MetricsActivityLifecycleManager.getInstance().setFragment(obj);
            pageEnter(activity);
        }
    }

    public void doSample() {
        if (this.nowFPS > 0.0d) {
            if (this.pageFpsEvent != null && this.pageFpsEvent.sampleUpdateEnabled && this.pageFpsEvent.minFps > this.nowFPS) {
                this.pageFpsEvent.minFps = this.nowFPS;
            }
            if (this.scrollFpsEvent != null && this.scrollFpsEnabled && this.scrollFpsEvent.sampleUpdateEnabled && this.scrollFpsEvent.minFps > this.nowFPS) {
                this.scrollFpsEvent.minFps = this.nowFPS;
            }
            for (FpsEvent fpsEvent : this.customEvents.values()) {
                if (fpsEvent != null && fpsEvent.sampleUpdateEnabled && fpsEvent.minFps > this.nowFPS && this.nowFPS > 0.0d) {
                    fpsEvent.minFps = this.nowFPS;
                }
            }
        }
    }

    public double getRealTimeValue() {
        return this.nowFPS;
    }

    private void startRecordPageFps(String str) {

        this.pageFpsEvent = new FpsEvent(Constants.FPS_TYPE_PAGE, str);
        this.pageFpsEvent.sampleUpdateEnabled = true;
        this.pageFpsEvent.frameTotalCostTime = this.currentFrameTotalCostTime;
        this.pageFpsEvent.frameTotalCount = this.currentFrameTotalCount;
    }

    private void stopRecordPageFps(Activity activity, Object obj) {

        String pageName = AppUtils.getPageName(activity, MetricsActivityLifecycleManager.currentActivity);
        if (!(MetricsRemoteConfigManager.getInstance().getFpsPageConfig(pageName) == -1 || this.pageFpsEvent == null)) {
            this.pageFpsEvent.computeAvgFps(this.currentFrameTotalCostTime, this.currentFrameTotalCount);
            this.pageFpsEvent.sampleUpdateEnabled = false;
            if (this.pageFpsEvent.isValid()) {
                this.pageFpsEvent.optionTags = AppUtils.getCustomTags(activity, obj, Constants.FPS_PAGE);
                MetricsCacheManager.getInstance().addToCache(this.pageFpsEvent);
            }
            this.pageFpsEvent = null;
        }
        if (!(MetricsRemoteConfigManager.getInstance().getFpsScrollConfig(pageName) == -1 || this.scrollFpsEvent == null || !this.scrollFpsEnabled)) {
            this.scrollFpsEvent.computeScrollAvgFps();
            this.scrollFpsEvent.sampleUpdateEnabled = false;
            LogUtil.d(TAG, "stopRecordPageFps===", this.scrollFpsEvent);
            if (this.scrollFpsEvent.isValid()) {
                LogUtil.d(TAG, "addToCache__________scrollfps");
                this.scrollFpsEvent.optionTags = AppUtils.getCustomTags(activity, obj, Constants.FPS_SCROLL);
                MetricsCacheManager.getInstance().addToCache(this.scrollFpsEvent);
            }
            this.scrollFpsEvent = null;
            this.scrollFpsEnabled = false;
        }
    }

    public void startScrollFPS() {
         if (this.scrollFpsEvent != null) {
            LogUtil.d(TAG, "scroll started");
            if (!TextUtils.equals(this.scrollFpsEvent.scrollType, "custom")) {
                this.scrollFpsEvent.sampleUpdateEnabled = true;
                this.scrollFpsEnabled = true;
                this.scrollFpsEvent.frameTotalCostTime = this.currentFrameTotalCostTime;
                this.scrollFpsEvent.frameTotalCount = this.currentFrameTotalCount;
            }
        }
    }

    public void stopScrollFPS() {

        if (this.scrollFpsEvent != null && this.scrollFpsEnabled && TextUtils.equals(this.scrollFpsEvent.scrollType, "auto")) {
            this.scrollFpsEvent.computeLastTimeAndCount(this.currentFrameTotalCostTime, this.currentFrameTotalCount);
            this.scrollFpsEvent.sampleUpdateEnabled = false;
            LogUtil.d(TAG, "scroll stopped");
            LogUtil.d(TAG, "stopScrollFPS===", this.scrollFpsEvent);
        }
    }

    public void startCustomScrollFPS(final Activity activity) {
     if (this.scrollFpsEvent != null) {
            if (this.scrollListenerRegistered) {
                ThreadManager.getInstance().runOnUiThread(new Callable<Void>() {

                    public Void call() throws Exception {

                        MetricsFpsSamplerImpl.this.unRegisterGlobalScrollCallback(activity);
                        return null;
                    }
                });
            }
            if (TextUtils.equals(this.scrollFpsEvent.scrollType, "auto")) {
                this.scrollFpsEvent.reset();
                this.scrollFpsEvent.scrollType = "custom";
            } else if (this.customScrolling && this.scrollFpsEnabled) {
                return;
            }
            LogUtil.d(TAG, "scroll started new");
            this.scrollFpsEvent.sampleUpdateEnabled = true;
            this.scrollFpsEnabled = true;
            this.scrollFpsEvent.frameTotalCostTime = this.currentFrameTotalCostTime;
            this.scrollFpsEvent.frameTotalCount = this.currentFrameTotalCount;
            this.customScrolling = true;
        }
    }

    public void stopCustomScrollFPS(Activity activity) {

        if (this.scrollFpsEvent != null && this.scrollFpsEnabled && this.customScrolling && TextUtils.equals(this.scrollFpsEvent.scrollType, "custom")) {
            this.scrollFpsEvent.computeLastTimeAndCount(this.currentFrameTotalCostTime, this.currentFrameTotalCount);
            this.scrollFpsEvent.sampleUpdateEnabled = false;
            LogUtil.d(TAG, "scroll stopped new ");
        }
        this.customScrolling = false;
    }

    private void cancelScrollFPS() {

        if (this.scrollFpsEvent != null) {
            this.scrollFpsEvent.sampleUpdateEnabled = false;
            LogUtil.d(TAG, "ignore scroll event");
        }
    }

    public void startCustomRecordFps(String str) {

        if (!TextUtils.isEmpty(str)) {
            FpsEvent fpsEvent = new FpsEvent("custom", str);
            fpsEvent.sampleUpdateEnabled = true;
            fpsEvent.frameTotalCostTime = this.currentFrameTotalCostTime;
            fpsEvent.frameTotalCount = this.currentFrameTotalCount;
            this.customEvents.put(str, fpsEvent);
        }
    }

    public void stopCustomRecordFps(String str, Map<String, Object> map) {

        if (!TextUtils.isEmpty(str)) {
            FpsEvent fpsEvent = (FpsEvent) this.customEvents.get(str);
            if (fpsEvent != null) {
                fpsEvent.computeAvgFps(this.currentFrameTotalCostTime, this.currentFrameTotalCount);
                fpsEvent.sampleUpdateEnabled = false;
                if (fpsEvent.isValid()) {
                    fpsEvent.optionTags = map;
                    MetricsCacheManager.getInstance().addToCache(fpsEvent);
                }
                this.customEvents.remove(str);
            }
        }
    }

    public void reset() {

        LogUtil.d(TAG, "reset=============");
        this.frameStartTime = 0;
        this.currentFrameTotalCostTime = 0;
        this.currentFrameTotalCount = 0;
        this.startSampleTimeInNs = 0;
        this.startSampleFrameCount = 0;
        this.nowFPS = 0.0d;
        if (this.mChoreographer != null) {
            this.mChoreographer.removeFrameCallback(this);
            this.recording = false;
        }
    }

    public void doFrame(long j) {

        if (this.frameStartTime > 0) {
            this.currentFrameTotalCostTime += j - this.frameStartTime;
            this.currentFrameTotalCount++;
        }
        this.frameStartTime = j;
        if (this.startSampleTimeInNs == 0) {
            this.startSampleTimeInNs = j;
            this.startSampleFrameCount = 0;
        } else if (j - this.startSampleTimeInNs >= this.sampleTimeInNs) {
            this.nowFPS = (double) this.startSampleFrameCount;
            if (this.nowFPS > 60.0d) {
                this.nowFPS = 60.0d;
            }
            this.samplerHandler.sendEmptyMessage(2);
            this.startSampleTimeInNs = j;
            this.startSampleFrameCount = 0;
        } else {
            this.startSampleFrameCount++;
        }
        this.mChoreographer.postFrameCallback(this);
    }
}