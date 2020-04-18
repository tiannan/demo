package com.meituan.metrics.config;

import android.text.TextUtils;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.util.AppUtils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Keep
/* compiled from: ProGuard */
public class MetricsRemoteConfigV2 {
    public static final String MATCH_ALL = "*";
    public static final double RATE = Math.random();
    public float anr;
    @SerializedName("big_image")
    public float bigImage;
    @SerializedName("big_image_threshold")
    public int bigImageThreshold;
    public PagesConfig cpu;
    @SerializedName("day_limit")
    public int dayLimit;
    @SerializedName("day_limit_per_page")
    public int dayLimitPerPage;
    @SerializedName("fps_custom")
    public KeysConfig fpsCustom;
    @SerializedName("fps_page")
    public PagesConfig fpsPage;
    @SerializedName("fps_scroll")
    public PagesConfig fpsScroll;
    public float lag;
    @SerializedName("lag_threshold")
    public int lagThreshold;
    @SerializedName("load_custom")
    public KeysConfig loadCustom;
    @SerializedName("load_homepage")
    public float loadHomepage;
    @SerializedName("load_page")
    public PagesConfig loadPage;
    @SerializedName("load_page_auto")
    public PagesConfig loadPageAuto;
    @SerializedName("max_report_callstack_times")
    public int maxReportCallstackTimes;
    public PagesConfig memory;
    @SerializedName("traffic_daily_total")
    public float trafficDailyTotal;
    @SerializedName("versions_sample_ratio")
    public Map<String, Float> versionSampleRatio;

    @Keep
    /* compiled from: ProGuard */
    public static class KeysConfig {
        public Map<String, Float> keys;

        public String toString() {

            StringBuilder stringBuilder = new StringBuilder("KeysConfig{keys=");
            stringBuilder.append(this.keys);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    @Keep
    /* compiled from: ProGuard */
    public static class PagesConfig {
        public Map<String, Float> pages;

        public String toString() {

            StringBuilder stringBuilder = new StringBuilder("PagesConfig{pages=");
            stringBuilder.append(this.pages);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    public MetricsRemoteConfigV2() {

        this.anr = 1.0f;
        this.trafficDailyTotal = 1.0f;
    }

    public boolean isAppStartupEnable() {
        boolean z = false;

        if (RATE < ((double) getSampleRate(this.loadHomepage))) {
            z = true;
        }
        return z;
    }

    public boolean isAnrEnable() {
        boolean z = false;

        if (RATE < ((double) getSampleRate(this.anr))) {
            z = true;
        }
        return z;
    }

    public boolean isLagEnable() {
        boolean z = false;

        if (RATE < ((double) getSampleRate(this.lag))) {
            z = true;
        }
        return z;
    }

    public boolean isFpsPageEnable(String str) {

        if (!TextUtils.isEmpty(str)) {
            if (this.fpsPage != null) {
                return isPageOrKeyEnable(this.fpsPage.pages, str);
            }
        }
        return false;
    }

    public boolean isFpsPageEnable() {

        if (this.fpsPage == null) {
            return false;
        }
        return isPageOrKeyEnable(this.fpsPage.pages);
    }

    public boolean isFpsScrollEnable(String str) {

        if (!TextUtils.isEmpty(str)) {
            if (this.fpsScroll != null) {
                return isPageOrKeyEnable(this.fpsScroll.pages, str);
            }
        }
        return false;
    }

    public boolean isFpsScrollEnable() {

        if (this.fpsScroll == null) {
            return false;
        }
        return isPageOrKeyEnable(this.fpsScroll.pages);
    }

    public boolean isFpsCustomEnable(String str) {

        if (!TextUtils.isEmpty(str)) {
            if (this.fpsCustom != null) {
                return isPageOrKeyEnable(this.fpsCustom.keys, str);
            }
        }
        return false;
    }

    public boolean isFpsCustomEnable() {

        if (this.fpsCustom == null) {
            return false;
        }
        return isPageOrKeyEnable(this.fpsCustom.keys);
    }

    public boolean isLoadPageEnable(String str) {

        if (!TextUtils.isEmpty(str)) {
            if (this.loadPage != null) {
                return isPageOrKeyEnable(this.loadPage.pages, str);
            }
        }
        return false;
    }

    public boolean isLoadPageAutoEnable(String str) {

        if (!TextUtils.isEmpty(str)) {
            if (this.loadPageAuto != null) {
                return isPageOrKeyEnable(this.loadPageAuto.pages, str);
            }
        }
        return false;
    }

    public boolean isLoadPageEnable() {

        if (this.loadPage == null) {
            return false;
        }
        return isPageOrKeyEnable(this.loadPage.pages);
    }

    public boolean isLoadPageCustom(String str) {

        if (!TextUtils.isEmpty(str)) {
            if (this.loadCustom != null) {
                return isPageOrKeyEnable(this.loadCustom.keys, str);
            }
        }
        return false;
    }

    public boolean isLoadPageCustom() {

        if (this.loadCustom == null) {
            return false;
        }
        return isPageOrKeyEnable(this.loadCustom.keys);
    }

    public boolean isMemoryEnable() {

        if (this.memory == null) {
            return false;
        }
        return isPageOrKeyEnable(this.memory.pages);
    }

    public boolean isMemoryEnable(String str) {

        if (this.memory == null) {
            return false;
        }
        return isPageOrKeyEnable(this.memory.pages, str);
    }

    public boolean isCpuEnable() {

        if (this.memory == null) {
            return false;
        }
        return isPageOrKeyEnable(this.memory.pages);
    }

    public boolean isCpuEnable(String str) {

        if (this.cpu == null) {
            return false;
        }
        return isPageOrKeyEnable(this.cpu.pages, str);
    }

    public boolean isTrafficDailyTotalEnable() {
        boolean z = false;

        if (RATE < ((double) getSampleRate(this.trafficDailyTotal))) {
            z = true;
        }
        return z;
    }

    private boolean isPageOrKeyEnable(Map<String, Float> map) {

        if (map != null) {
            if (map.size() > 0) {
                Set<Entry> entrySet = map.entrySet();
                if (entrySet.size() > 0) {
                    for (Entry entry : entrySet) {
                        String str2 = (String) entry.getKey();
                        Float f = (Float) entry.getValue();
                        if (!TextUtils.isEmpty(str2) && RATE < ((double) getSampleRate(f.floatValue()))) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }

    private boolean isPageOrKeyEnable(Map<String, Float> map, String str) {

        if (map != null) {
            if (map.size() > 0) {
                Set<Entry> entrySet = map.entrySet();
                if (entrySet.size() > 0) {
                    String str3;
                    Float f;
                    for (Entry entry : entrySet) {
                        str3 = (String) entry.getKey();
                        f = (Float) entry.getValue();
                        if (!TextUtils.isEmpty(str3) && !TextUtils.equals(str3, "*") && !str3.endsWith("*") && TextUtils.equals(str3, str)) {
                            return RATE < ((double) getSampleRate(f.floatValue()));
                        }
                    }
                    for (Entry entry2 : entrySet) {
                        str3 = (String) entry2.getKey();
                        f = (Float) entry2.getValue();
                        if (!TextUtils.isEmpty(str3) && str3.endsWith("*") && str.startsWith(str3.substring(0, str3.length() - 1))) {
                            return RATE < ((double) getSampleRate(f.floatValue()));
                        }
                    }
                    for (Entry entry3 : entrySet) {
                        String str4 = (String) entry3.getKey();
                        Float f2 = (Float) entry3.getValue();
                        if (TextUtils.equals(str4, "*")) {
                            return RATE < ((double) getSampleRate(f2.floatValue()));
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }

    private float getSampleRate(float f) {

        if (this.versionSampleRatio != null) {
            if (this.versionSampleRatio.size() > 0) {
                Float f2 = (Float) this.versionSampleRatio.get(AppUtils.getVersionName(Metrics.getInstance().getContext()));
                return f2 != null ? Math.max(f, f2.floatValue()) : f;
            }
        }
        return f;
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("MetricsRemoteConfigV2{RATE=");
        stringBuilder.append(RATE);
        stringBuilder.append(", loadHomepage=");
        stringBuilder.append(this.loadHomepage);
        stringBuilder.append(", bigImage=");
        stringBuilder.append(this.bigImage);
        stringBuilder.append(", lag=");
        stringBuilder.append(this.lag);
        stringBuilder.append(", anr=");
        stringBuilder.append(this.anr);
        stringBuilder.append(", fpsPage=");
        stringBuilder.append(this.fpsPage);
        stringBuilder.append(", fpsScroll=");
        stringBuilder.append(this.fpsScroll);
        stringBuilder.append(", fpsCustom=");
        stringBuilder.append(this.fpsCustom);
        stringBuilder.append(", memory=");
        stringBuilder.append(this.memory);
        stringBuilder.append(", cpu=");
        stringBuilder.append(this.cpu);
        stringBuilder.append(", loadPage=");
        stringBuilder.append(this.loadPage);
        stringBuilder.append(", loadPageAuto=");
        stringBuilder.append(this.loadPageAuto);
        stringBuilder.append(", loadCustom=");
        stringBuilder.append(this.loadCustom);
        stringBuilder.append(", dayLimit=");
        stringBuilder.append(this.dayLimit);
        stringBuilder.append(", dayLimitPerPage=");
        stringBuilder.append(this.dayLimitPerPage);
        stringBuilder.append(", lagThreshold=");
        stringBuilder.append(this.lagThreshold);
        stringBuilder.append(", maxReportCallstackTimes=");
        stringBuilder.append(this.maxReportCallstackTimes);
        stringBuilder.append(", bigImageThreshold=");
        stringBuilder.append(this.bigImageThreshold);
        stringBuilder.append(", versionSampleRatio=");
        stringBuilder.append(this.versionSampleRatio);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}