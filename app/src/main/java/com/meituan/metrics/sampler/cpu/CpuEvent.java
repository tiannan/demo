package com.meituan.metrics.sampler.cpu;

import com.meituan.metrics.common.Constants;
import com.meituan.metrics.sampler.AbstractSampleEvent;
import com.meituan.metrics.util.JSONUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class CpuEvent extends AbstractSampleEvent {
     private double avgAppCpuRate;
    private double maxAppCpuRate;
    private final String pageName;
    private int sampleCount;

    public String getLocalEventType() {
        return Constants.CPU_V2;
    }

    public String getOldLocalEventType() {
        return Constants.CPU_AVG;
    }

    public CpuEvent(String str) {

            this.pageName = str;

    }

    public void computeAvg(double d) {

        this.avgAppCpuRate = ((this.avgAppCpuRate * ((double) this.sampleCount)) + d) / ((double) (this.sampleCount + 1));
        if (this.maxAppCpuRate < d) {
            this.maxAppCpuRate = d;
        }
        this.sampleCount++;
    }

    public double getAvgAppCpuRate() {
        return this.avgAppCpuRate;
    }

    public int getSampleCount() {
        return this.sampleCount;
    }

    public double getMaxAppCpuRate() {
        return this.maxAppCpuRate;
    }

    public String getPageName() {
        return this.pageName;
    }

    public double getMetricValue() {
        return this.avgAppCpuRate;
    }

    public void convertToJson(JSONObject jSONObject) throws JSONException {

        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("pageName", this.pageName);
        jSONArray.put(JSONUtils.buildLogUnit(Constants.CPU_AVG, df.format(this.avgAppCpuRate), jSONObject2, this.ts));
        jSONArray.put(JSONUtils.buildLogUnit(Constants.CPU_MAX, df.format(this.maxAppCpuRate), jSONObject2, this.ts));
        jSONObject.put(Constants.METRICS, jSONArray);
    }
}