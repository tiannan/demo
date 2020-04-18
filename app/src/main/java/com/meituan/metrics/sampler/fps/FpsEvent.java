package com.meituan.metrics.sampler.fps;

import android.text.TextUtils;
import com.meituan.metrics.common.Constants;
import com.meituan.metrics.sampler.AbstractSampleEvent;
import com.meituan.metrics.util.JSONUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class FpsEvent extends AbstractSampleEvent {
    public static final String TYPE_SCROLL_AUTO = "auto";
    public static final String TYPE_SCROLL_CUSTOM = "custom";
    private double avgFps;
    private double countedFrames;
    public long frameTotalCostTime;
    public int frameTotalCount;
    private long lastFrameTotalCostTime;
    private int lastFrameTotalCount;
    public double minFps;
    private final String name;
    public boolean sampleUpdateEnabled;
    public String scrollType;
    private String type;

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public double getAvgFps() {
        return this.avgFps;
    }

    public double getMinFps() {
        return this.minFps;
    }

    public FpsEvent(String str, String str2) {

        this.avgFps = 0.0d;
        this.minFps = 2.147483647E9d;
        this.scrollType = "auto";
        this.countedFrames = 0.0d;
        this.name = str2;
        this.type = str;
        setConfigFrom();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0061  */
    private void setConfigFrom() {
        /*
        r11 = this;
        r0 = 0;
        r8 = new java.lang.Object[r0];
        r9 = changeQuickRedirect;
        r10 = "78b4ee710563f6d6517574d209979883";
        r4 = 0;
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r1 = r8;
        r2 = r11;
        r3 = r9;
        r5 = r10;
        r1 = com.meituan.robust.PatchProxy.isSupport(r1, r2, r3, r4, r5, r6);
        if (r1 == 0) goto L_0x0018;
    L_0x0014:
        com.meituan.robust.PatchProxy.accessDispatch(r8, r11, r9, r0, r10);
        return;
    L_0x0018:
        r1 = r11.type;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x0088;
    L_0x0020:
        r1 = r11.name;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x0088;
    L_0x0028:
        r1 = r11.type;
        r2 = -1;
        r3 = r1.hashCode();
        r4 = -1349088399; // 0xffffffffaf968b71 float:-2.738392E-10 double:NaN;
        if (r3 == r4) goto L_0x0052;
    L_0x0034:
        r4 = -907680051; // 0xffffffffc9e5e6cd float:-1883353.6 double:NaN;
        if (r3 == r4) goto L_0x0048;
    L_0x0039:
        r4 = 3433103; // 0x34628f float:4.810802E-39 double:1.6961783E-317;
        if (r3 == r4) goto L_0x003f;
    L_0x003e:
        goto L_0x005c;
    L_0x003f:
        r3 = "page";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x005c;
    L_0x0047:
        goto L_0x005d;
    L_0x0048:
        r0 = "scroll";
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x005c;
    L_0x0050:
        r0 = 1;
        goto L_0x005d;
    L_0x0052:
        r0 = "custom";
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x005c;
    L_0x005a:
        r0 = 2;
        goto L_0x005d;
    L_0x005c:
        r0 = -1;
    L_0x005d:
        switch(r0) {
            case 0: goto L_0x007b;
            case 1: goto L_0x006e;
            case 2: goto L_0x0061;
            default: goto L_0x0060;
        };
    L_0x0060:
        goto L_0x0088;
    L_0x0061:
        r0 = com.meituan.metrics.config.MetricsRemoteConfigManager.getInstance();
        r1 = r11.name;
        r0 = r0.getFpsCustomConfig(r1);
        r11.configFrom = r0;
        goto L_0x0088;
    L_0x006e:
        r0 = com.meituan.metrics.config.MetricsRemoteConfigManager.getInstance();
        r1 = r11.name;
        r0 = r0.getFpsScrollConfig(r1);
        r11.configFrom = r0;
        return;
    L_0x007b:
        r0 = com.meituan.metrics.config.MetricsRemoteConfigManager.getInstance();
        r1 = r11.name;
        r0 = r0.getFpsPageConfig(r1);
        r11.configFrom = r0;
        return;
    L_0x0088:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.metrics.sampler.fps.FpsEvent.setConfigFrom():void");
    }

    public void computeAvgFps(long j, int i) {

        j -= this.frameTotalCostTime;
        i -= this.frameTotalCount;
        if (j > 0 && i > 0) {
            this.avgFps = (((double) i) * 1.0E9d) / ((double) j);
        }
    }

    public void computeScrollAvgFps() {

        if (this.lastFrameTotalCostTime > 0 && this.lastFrameTotalCount > 0) {
            this.avgFps = (((double) this.lastFrameTotalCount) * 1.0E9d) / ((double) this.lastFrameTotalCostTime);
        }
    }

    public void computeLastTimeAndCount(long j, int i) {

        j -= this.frameTotalCostTime;
        i -= this.frameTotalCount;
        if (j > 0 && i > 0) {
            this.lastFrameTotalCostTime += j;
            this.lastFrameTotalCount += i;
        }
    }

    public void reset() {

        this.lastFrameTotalCostTime = 0;
        this.lastFrameTotalCount = 0;
    }

    public boolean isValid() {
        return this.avgFps > 0.0d && this.minFps != 2.147483647E9d;
    }

    public void onFrame(double d) {

        this.countedFrames += 1.0d;
        if (d < this.minFps) {
            this.minFps = d;
        }
        if (this.countedFrames > 1.0d) {
            this.avgFps = (((this.countedFrames - 1.0d) * this.avgFps) + d) / this.countedFrames;
        }
    }

    public double getCountedFrames() {
        return this.countedFrames;
    }

    public void merge(FpsEvent fpsEvent) {
      if (fpsEvent != null && fpsEvent.countedFrames >= 1.0d) {
            this.countedFrames += fpsEvent.countedFrames;
            if (fpsEvent.minFps < this.minFps) {
                this.minFps = fpsEvent.minFps;
            }
            this.avgFps = (((this.countedFrames - fpsEvent.countedFrames) * this.avgFps) + (fpsEvent.avgFps * fpsEvent.countedFrames)) / this.countedFrames;
        }
    }

    public String getPageName() {
        return this.name;
    }

    public String getLocalEventType() {

        if (TextUtils.equals(Constants.FPS_TYPE_PAGE, this.type)) {
            return Constants.FPS_PAGE_AVG;
        }
        if (TextUtils.equals(Constants.FPS_TYPE_SCROLL, this.type)) {
            return Constants.FPS_SCROLL_AVG;
        }
        if (!TextUtils.equals("custom", this.type) || TextUtils.isEmpty(this.name)) {
            return super.getEventType();
        }
        return Constants.FPS_CUSTOM_AVG;
    }

    public double getMetricValue() {
        if (this.avgFps > 60.0d) {
            this.avgFps = 60.0d;
        }
        return this.avgFps;
    }

    public void convertToJson(JSONObject jSONObject) throws JSONException {

        if (this.avgFps > 60.0d) {
            this.avgFps = 60.0d;
        }
        if (this.avgFps > 0.0d && this.minFps > this.avgFps) {
            this.minFps = this.avgFps;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        if (TextUtils.equals(Constants.FPS_TYPE_PAGE, this.type)) {
            jSONObject2.put("pageName", this.name);
            jSONArray.put(JSONUtils.buildLogUnit(Constants.FPS_PAGE_AVG, df.format(this.avgFps), jSONObject2, this.ts));
            jSONArray.put(JSONUtils.buildLogUnit(Constants.FPS_PAGE_MIN, df.format(this.minFps), jSONObject2, this.ts));
        } else if (TextUtils.equals(Constants.FPS_TYPE_SCROLL, this.type)) {
            jSONObject2.put("pageName", this.name);
            jSONArray.put(JSONUtils.buildLogUnit(Constants.FPS_SCROLL_AVG, df.format(this.avgFps), jSONObject2, this.ts));
            jSONArray.put(JSONUtils.buildLogUnit(Constants.FPS_SCROLL_MIN, df.format(this.minFps), jSONObject2, this.ts));
        } else if (TextUtils.equals("custom", this.type) && !TextUtils.isEmpty(this.name)) {
            jSONObject2.put("key", this.name);
            jSONArray.put(JSONUtils.buildLogUnit(Constants.FPS_CUSTOM_AVG, df.format(this.avgFps), jSONObject2, this.ts));
            jSONArray.put(JSONUtils.buildLogUnit(Constants.FPS_CUSTOM_MIN, df.format(this.minFps), jSONObject2, this.ts));
        }
        jSONObject.put(Constants.METRICS, jSONArray);
    }
}