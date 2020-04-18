package com.meituan.metrics.sampler.cpu;

import android.app.Activity;
import com.meituan.crashreporter.a;
import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.common.Constants;
import com.meituan.metrics.config.MetricsLocalSwitchConfigManager;
import com.meituan.metrics.config.MetricsRemoteConfig;
import com.meituan.metrics.config.MetricsRemoteConfigManager;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.sampler.MetricsSampler;
import com.meituan.metrics.util.AppUtils;

import java.io.FileNotFoundException;

/* compiled from: ProGuard */
public class MetricsCpuSampler implements MetricsSampler {
    private CpuEvent event;
    private double lastCpuUsage;
    private ICpuUsageProvider provider;
    private boolean statFileBroke;

    public MetricsCpuSampler(MetricsRemoteConfig metricsRemoteConfig) {

        this.statFileBroke = false;
        this.provider = CpuUsageProviderFactory.create(metricsRemoteConfig);
    }

    public void pageEnter(Activity activity) {
        String pageName = AppUtils.getPageName(activity, MetricsActivityLifecycleManager.currentActivity);
        int cpuConfig = MetricsRemoteConfigManager.getInstance().getCpuConfig(pageName);
        if (MetricsLocalSwitchConfigManager.getInstance().getCPUSw(pageName)) {
            if (cpuConfig != -1) {
                this.event = new CpuEvent(pageName);
                this.event.configFrom = cpuConfig;
                return;
            }
        }
        this.lastCpuUsage = 0.0d;
    }

    public void pageExit(Activity activity) {

        if (this.event != null) {
            this.event.optionTags = AppUtils.getCustomTags(activity, Constants.CPU);
            MetricsCacheManager.getInstance().addToCache(this.event);
            this.event = null;
        }
    }

    public void doSample() {
        if (!this.statFileBroke && this.event != null && this.provider != null) {
            try {
                double cpuUsagePercent = this.provider.getCpuUsagePercent();
                if (cpuUsagePercent < 0.0d) {
                    this.statFileBroke = true;
                    return;
                }
                this.lastCpuUsage = cpuUsagePercent;
                if (this.event != null) {
                    this.event.computeAvg(this.lastCpuUsage);
                }
            } catch (Throwable th) {
                if (th instanceof FileNotFoundException) {
                    this.statFileBroke = true;
                }
                a.a(th, 1, "metrics_cpu_sampler", false);
            }
        }
    }

    public double getRealTimeValue() {
        return this.lastCpuUsage;
    }
}