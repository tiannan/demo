package com.meituan.metrics.sampler.fps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.FrameMetrics;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.Window.OnFrameMetricsAvailableListener;

import androidx.annotation.RequiresApi;

import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.common.Constants;
import com.meituan.metrics.config.MetricsLocalSwitchConfigManager;
import com.meituan.metrics.config.MetricsRemoteConfigManager;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.util.AppUtils;
import com.meituan.metrics.util.TimeUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiresApi(24)
/* compiled from: ProGuard */
public class MetricsFpsSamplerNougatImpl implements MetricsFpsSampler {
    private static final double MAX_FPS = 60.0d;
    private static final int MAX_SCROLL_GAP_MS = 80;
    private static final double MIN_FRAME_DURATION = 16.666666666666668d;
    private Runnable cancelScrollFPS;
    private Map<String, FpsEvent> customEntities;
    private FpsEvent eachScrollFpsEvent;
    private final boolean fpsCustomEnable;
    private final boolean fpsPageEnable;
    private final OnScrollChangedListener fpsScrollChangeListener;
    private final boolean fpsScrollEnable;
    private final OnFrameMetricsAvailableListener frameMetricsAvailableListener;
    private double lastFpsValue;
    private Handler mainHandler;
    private FpsEvent pageFpsEvent;
    private FpsEvent pageScrollFpsEvent;
    private final Handler samplingThreadHandler;
    private long scrollStartStamp;
    private Runnable scrollStopped;
    private boolean scrolling;
    private Runnable startScrollFPS;
    private Runnable stopScrollFPS;

    public void doSample() {
    }

    public MetricsFpsSamplerNougatImpl(Handler handler) {

        this.customEntities = new ConcurrentHashMap();
        this.scrolling = false;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.frameMetricsAvailableListener = new OnFrameMetricsAvailableListener() {

            public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {

                double metric = (double) (frameMetrics.getMetric(8) / 1000000);
                metric = metric < MetricsFpsSamplerNougatImpl.MIN_FRAME_DURATION ? MetricsFpsSamplerNougatImpl.MAX_FPS : 1000.0d / metric;
                if (!(MetricsFpsSamplerNougatImpl.this.customEntities == null || MetricsFpsSamplerNougatImpl.this.customEntities.isEmpty())) {
                    for (FpsEvent onFrame : MetricsFpsSamplerNougatImpl.this.customEntities.values()) {
                        onFrame.onFrame(metric);
                    }
                }
                if (MetricsFpsSamplerNougatImpl.this.pageFpsEvent != null) {
                    MetricsFpsSamplerNougatImpl.this.pageFpsEvent.onFrame(metric);
                }
                if (MetricsFpsSamplerNougatImpl.this.eachScrollFpsEvent != null) {
                    MetricsFpsSamplerNougatImpl.this.eachScrollFpsEvent.onFrame(metric);
                }
                MetricsFpsSamplerNougatImpl.this.lastFpsValue = metric;
            }
        };
        this.scrollStopped = new Runnable() {

            public void run() {

                MetricsFpsSamplerNougatImpl.this.scrolling = false;
                if (TimeUtil.elapsedTimeMillis() - MetricsFpsSamplerNougatImpl.this.scrollStartStamp > 160) {
                    MetricsFpsSamplerNougatImpl.this.samplingThreadHandler.post(MetricsFpsSamplerNougatImpl.this.stopScrollFPS);
                } else {
                    MetricsFpsSamplerNougatImpl.this.samplingThreadHandler.post(MetricsFpsSamplerNougatImpl.this.cancelScrollFPS);
                }
            }
        };
        this.fpsScrollChangeListener = new OnScrollChangedListener() {

            public void onScrollChanged() {

                MetricsFpsSamplerNougatImpl.this.mainHandler.removeCallbacks(MetricsFpsSamplerNougatImpl.this.scrollStopped);
                if (!MetricsFpsSamplerNougatImpl.this.scrolling) {
                    MetricsFpsSamplerNougatImpl.this.scrolling = true;
                    MetricsFpsSamplerNougatImpl.this.scrollStartStamp = TimeUtil.elapsedTimeMillis();
                    MetricsFpsSamplerNougatImpl.this.samplingThreadHandler.post(MetricsFpsSamplerNougatImpl.this.startScrollFPS);
                }
                MetricsFpsSamplerNougatImpl.this.mainHandler.postDelayed(MetricsFpsSamplerNougatImpl.this.scrollStopped, 80);
            }
        };
        this.startScrollFPS = new Runnable() {

            public void run() {

                    MetricsFpsSamplerNougatImpl.this.eachScrollFpsEvent = new FpsEvent(Constants.FPS_TYPE_SCROLL, MetricsActivityLifecycleManager.currentActivity);

            }
        };
        this.cancelScrollFPS = new Runnable() {

            public void run() {

                    MetricsFpsSamplerNougatImpl.this.eachScrollFpsEvent = null;

            }
        };
        this.stopScrollFPS = new Runnable() {

            public void run() {

                if (MetricsFpsSamplerNougatImpl.this.pageScrollFpsEvent != null) {
                    MetricsFpsSamplerNougatImpl.this.pageScrollFpsEvent.merge(MetricsFpsSamplerNougatImpl.this.eachScrollFpsEvent);
                } else if (MetricsFpsSamplerNougatImpl.this.eachScrollFpsEvent != null) {
                    MetricsFpsSamplerNougatImpl.this.pageScrollFpsEvent = MetricsFpsSamplerNougatImpl.this.eachScrollFpsEvent;
                }
                MetricsFpsSamplerNougatImpl.this.eachScrollFpsEvent = null;
            }
        };
        this.samplingThreadHandler = handler;
        this.fpsPageEnable = MetricsRemoteConfigManager.getInstance().isFpsPageEnable();
        this.fpsCustomEnable = MetricsRemoteConfigManager.getInstance().isFpsCustomEnable();
        this.fpsScrollEnable = MetricsRemoteConfigManager.getInstance().isFpsScrollEnable();
    }

    public void pageEnter(Activity activity) {

        Window window;
       if (MetricsLocalSwitchConfigManager.getInstance().getFPSSw(AppUtils.getPageName(activity))) {
            window = activity.getWindow();
            if (this.fpsPageEnable) {
                this.pageFpsEvent = new FpsEvent(Constants.FPS_TYPE_PAGE, MetricsActivityLifecycleManager.currentActivity);
            }
            if (this.fpsScrollEnable) {
                window.getDecorView().getViewTreeObserver().addOnScrollChangedListener(this.fpsScrollChangeListener);
            }
            window.addOnFrameMetricsAvailableListener(this.frameMetricsAvailableListener, this.samplingThreadHandler);
        } else {
            this.lastFpsValue = 0.0d;
            if (this.fpsPageEnable) {
                this.pageFpsEvent = null;
            }
            if (this.fpsScrollEnable) {
                this.pageScrollFpsEvent = null;
            }
            window = activity.getWindow();
            if (!(window == null || this.frameMetricsAvailableListener == null)) {
                window.removeOnFrameMetricsAvailableListener(this.frameMetricsAvailableListener);
            }
        }
    }

    public void pageExit(Activity activity) {
        Object[] objArr = new Object[]{activity};

        recordFps(activity, MetricsActivityLifecycleManager.getInstance().getCurrentFragment());
        MetricsActivityLifecycleManager.getInstance().clearCurrentFragment();
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
            recordFps(activity, currentFragment);
            MetricsActivityLifecycleManager.getInstance().setFragment(obj);
            pageEnter(activity);
        }
    }

    private void recordFps(Activity activity, Object obj) {

        Window window = activity.getWindow();
        window.removeOnFrameMetricsAvailableListener(this.frameMetricsAvailableListener);
        if (this.fpsPageEnable) {
            this.pageFpsEvent.optionTags = AppUtils.getCustomTags(activity, obj, Constants.FPS_PAGE);
            reportFpsEventIfNeeded(this.pageFpsEvent);
            this.pageFpsEvent = null;
        }
        if (this.fpsScrollEnable) {
            this.pageScrollFpsEvent.optionTags = AppUtils.getCustomTags(activity, obj, Constants.FPS_SCROLL);
            reportFpsEventIfNeeded(this.pageScrollFpsEvent);
            this.pageScrollFpsEvent = null;
            this.mainHandler.removeCallbacks(this.scrollStopped);
            window.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(this.fpsScrollChangeListener);
        }
    }

    private void reportFpsEventIfNeeded(FpsEvent fpsEvent) {

        if (fpsEvent != null && fpsEvent.getCountedFrames() > 0.0d) {
            MetricsCacheManager.getInstance().addToCache(fpsEvent);
        }
    }

    public double getRealTimeValue() {
        return this.lastFpsValue;
    }

    public void startCustomRecordFps(String str) {
        if (this.fpsCustomEnable) {
            this.customEntities.put(str, new FpsEvent("custom", str));
        }
    }

    public void stopCustomRecordFps(String str, Map<String, Object> map) {
       if (this.fpsCustomEnable) {
            FpsEvent fpsEvent = (FpsEvent) this.customEntities.get(str);
            if (fpsEvent != null) {
                fpsEvent.optionTags = map;
                reportFpsEventIfNeeded(fpsEvent);
                this.customEntities.remove(str);
            }
        }
    }
}