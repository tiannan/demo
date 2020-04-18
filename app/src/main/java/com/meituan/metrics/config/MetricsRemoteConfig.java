package com.meituan.metrics.config;

import android.text.TextUtils;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;
import com.meituan.metrics.util.CommandExecution;
import java.util.List;

@Keep
/* compiled from: ProGuard */
public class MetricsRemoteConfig {
    @SerializedName("big_image_threshold")
    public int bigImageThreshold;
    @SerializedName("day_limit")
    public int dayLimit;
    @SerializedName("day_limit_per_page")
    public int dayLimitPerPage;
    @SerializedName("global")
    public Global global;
    @SerializedName("lag_threshold")
    public int lagThreshold;
    @SerializedName("max_report_callstack_times")
    public int maxReportCallstackTimes;
    @SerializedName("normal_ranges")
    public List<NormalRanges> normalRanges;
    public MetricsSwitches switches;

    @Keep
    public static class Global {
        public int android_cpu_innovation;
    }

    @Keep
    public static class MetricsSwitches {
        public int anr;
        @SerializedName("big_image")
        public int bigImage;
        public int cpu;
        @SerializedName("fps.custom")
        public int fpsCustom;
        @SerializedName("fps.page")
        public int fpsPage;
        @SerializedName("fps.scroll")
        public int fpsScroll;
        public int lag;
        @SerializedName("load.custom")
        public int loadCustom;
        @SerializedName("load.homepage")
        public int loadHomepage;
        @SerializedName("load.page")
        public int loadPage;
        public int memory;

        public MetricsSwitches() {

            this.fpsPage = -1;
            this.fpsScroll = -1;
            this.fpsCustom = -1;
            this.cpu = -1;
            this.memory = -1;
            this.lag = -1;
            this.anr = -1;
            this.loadHomepage = -1;
            this.loadPage = -1;
            this.loadCustom = -1;
            this.bigImage = -1;
        }

        public boolean isValid() {
            return (this.fpsPage == -1 || this.fpsScroll == -1 || this.fpsCustom == -1 || this.cpu == -1 || this.memory == -1 || this.lag == -1 || this.anr == -1 || this.loadHomepage == -1 || this.loadPage == -1 || this.loadCustom == -1 || this.bigImage == -1) ? false : true;
        }

        public String toString() {

            StringBuilder stringBuilder = new StringBuilder("MetricsSwitches\nfpsPage=");
            stringBuilder.append(this.fpsPage);
            stringBuilder.append("\nfpsScroll=");
            stringBuilder.append(this.fpsScroll);
            stringBuilder.append("\nfpsCustom=");
            stringBuilder.append(this.fpsCustom);
            stringBuilder.append("\ncpu=");
            stringBuilder.append(this.cpu);
            stringBuilder.append("\nmemory=");
            stringBuilder.append(this.memory);
            stringBuilder.append("\nlag=");
            stringBuilder.append(this.lag);
            stringBuilder.append("\nanr=");
            stringBuilder.append(this.anr);
            stringBuilder.append("\nloadHomepage=");
            stringBuilder.append(this.loadHomepage);
            stringBuilder.append("\nloadPage=");
            stringBuilder.append(this.loadPage);
            stringBuilder.append("\nloadCustom=");
            stringBuilder.append(this.loadCustom);
            stringBuilder.append("\nbigImage=");
            stringBuilder.append(this.bigImage);
            stringBuilder.append(CommandExecution.COMMAND_LINE_END);
            return stringBuilder.toString();
        }
    }

    @Keep
    /* compiled from: ProGuard */
    public static class NormalRanges {
        public String metric;
        public List<Range> ranges;

        public int getRange(String str, double d) {

            if (!(TextUtils.isEmpty(str) || !str.equals(this.metric) || this.ranges == null)) {
                if (!this.ranges.isEmpty()) {
                    Object obj;
                    loop0:
                    while (true) {
                        obj = 1;
                        for (Range range : this.ranges) {
                            if (range != null) {
                                boolean range2 = range.getRange(d);
                                if (obj == null || !range2) {
                                    obj = null;
                                }
                            }
                        }
                        break loop0;
                    }
                    return obj != null ? 1 : 0;
                }
            }
            return -1;
        }
    }

    @Keep
    public static class Range {
        public int eq;
        public int gt;
        public int gte;
        public int lt;
        public int lte;

        public Range() {

            this.gt = -100;
            this.gte = -100;
            this.eq = -100;
            this.lte = -100;
            this.lt = -100;
        }

        public boolean getRange(double d) {
            boolean z = true;

            if (this.gt != -100) {
                if (d <= ((double) this.gt)) {
                    z = false;
                }
            }
            if (this.gte != -100) {
                if (d < ((double) this.gte)) {
                    z = false;
                }
            }
            if (this.eq != -100) {
                if (d != ((double) this.eq)) {
                    z = false;
                }
            }
            if (this.lte != -100) {
                if (d > ((double) this.lte)) {
                    z = false;
                }
            }
            if (this.lt != -100) {
                if (d >= ((double) this.lt)) {
                    z = false;
                }
            }
            return z;
        }
    }

    public boolean isFpsPageEnable() {
        return this.switches != null && this.switches.fpsPage == 1;
    }

    public boolean isFpsScrollEnable() {
        return this.switches != null && this.switches.fpsScroll == 1;
    }

    public boolean isFpsCustomEnable() {
        return this.switches != null && this.switches.fpsCustom == 1;
    }

    public boolean isCpuEnable() {
        return this.switches != null && this.switches.cpu == 1;
    }

    public boolean isCpuInnovationEnable() {
        return this.global != null && this.global.android_cpu_innovation == 1;
    }

    public boolean isMemoryEnable() {
        return this.switches != null && this.switches.memory == 1;
    }

    public boolean isLagEnable() {
        return this.switches != null && this.switches.lag == 1;
    }

    public boolean isAnrEnable() {
        return this.switches != null && this.switches.anr == 1;
    }

    public boolean isAppStartupTimerEnabled() {
        return this.switches != null && this.switches.loadHomepage == 1;
    }

    public boolean isLoadPageEnable() {
        return this.switches != null && this.switches.loadPage == 1;
    }

    public boolean isLoadCustom() {
        return this.switches != null && this.switches.loadCustom == 1;
    }

    public boolean isBigImageEnable() {
        return this.switches != null && this.switches.bigImage == 1;
    }

    public boolean isValid() {
        boolean z = false;

        if (this.bigImageThreshold > 0 && this.lagThreshold > 0 && this.maxReportCallstackTimes > 0 && this.switches != null && this.switches.isValid()) {
            z = true;
        }
        return z;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("MetricsRemoteConfig{bigImageThreshold=");
        stringBuilder.append(this.bigImageThreshold);
        stringBuilder.append(", lagThreshold=");
        stringBuilder.append(this.lagThreshold);
        stringBuilder.append(", maxReportCallstackTimes=");
        stringBuilder.append(this.maxReportCallstackTimes);
        stringBuilder.append(", switches=");
        stringBuilder.append(this.switches);
        stringBuilder.append(", dayLimit=");
        stringBuilder.append(this.dayLimit);
        stringBuilder.append(", dayLimitPerPage=");
        stringBuilder.append(this.dayLimitPerPage);
        stringBuilder.append(", normalRanges=");
        stringBuilder.append(this.normalRanges);
        stringBuilder.append(", global=");
        stringBuilder.append(this.global);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}