package com.meituan.metrics.sampler.memory;

import com.meituan.metrics.common.Constants;
import com.meituan.metrics.sampler.AbstractSampleEvent;
import com.meituan.metrics.util.JSONUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MemoryEvent extends AbstractSampleEvent {
    private double avgMemoryUsage;
    private int codeAvg;
    private int codeIncrease;
    private int codeInit;
    private int codeMax;
    private int dalvikHeapAvg;
    private int dalvikHeapIncrease;
    private int dalvikHeapInit;
    private int dalvikHeapMax;
    public int dalvikMax;
    private int graphicsAvg;
    private int graphicsIncrease;
    private int graphicsInit;
    private int graphicsMax;
    private int javaHeapAvg;
    private int javaHeapIncrease;
    private int javaHeapInit;
    private int javaHeapMax;
    private double maxMemoryUsage;
    private int nativeHeapAvg;
    private int nativeHeapIncrease;
    private int nativeHeapInit;
    private int nativeHeapMax;
    private int oldSampleCount;
    private final String pageName;
    private int pssMemoryAvg;
    private int pssMemoryIncrease;
    private int pssMemoryInit;
    private int pssMemoryMax;
    private int sampleCount;
    private int stackAvg;
    private int stackIncrease;
    private int stackInit;
    private int stackMax;

    public String getLocalEventType() {
        return Constants.MEMORY_V2;
    }

    public String getOldEventType() {
        return Constants.MEMORY_AVG;
    }

    public MemoryEvent(String str, int i) {

        this.pssMemoryIncrease = Integer.MIN_VALUE;
        this.javaHeapIncrease = Integer.MIN_VALUE;
        this.nativeHeapIncrease = Integer.MIN_VALUE;
        this.codeIncrease = Integer.MIN_VALUE;
        this.stackIncrease = Integer.MIN_VALUE;
        this.graphicsIncrease = Integer.MIN_VALUE;
        this.dalvikHeapIncrease = Integer.MIN_VALUE;
        this.pageName = str;
        this.dalvikMax = i;
    }

    public void computeAvg(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i;
        int i9 = i2;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        int i13 = i6;
        int i14 = i7;

        int i15;
        int i16;
        if (this.sampleCount == 0) {
            this.pssMemoryInit = i8;
            this.javaHeapInit = i9;
            this.nativeHeapInit = i10;
            this.codeInit = i11;
            this.stackInit = i12;
            i15 = i6;
            this.graphicsInit = i15;
            i16 = i7;
            this.dalvikHeapInit = i16;
        } else {
            i15 = i6;
            i16 = i7;
        }
        if (i8 > 0) {
            this.pssMemoryAvg = (int) ((((double) ((this.pssMemoryAvg * this.sampleCount) + i8)) * 1.0d) / ((double) (this.sampleCount + 1)));
            if (this.pssMemoryMax < i8) {
                this.pssMemoryMax = i8;
            }
            if (this.sampleCount != 0 && this.pssMemoryInit > 0) {
                this.pssMemoryIncrease = i8 - this.pssMemoryInit;
            }
        }
        if (i9 > 0) {
            this.javaHeapAvg = (int) ((((double) ((this.javaHeapAvg * this.sampleCount) + i9)) * 1.0d) / ((double) (this.sampleCount + 1)));
            if (this.javaHeapMax < i9) {
                this.javaHeapMax = i9;
            }
            if (this.sampleCount != 0 && this.javaHeapInit > 0) {
                this.javaHeapIncrease = i9 - this.javaHeapInit;
            }
        }
        if (i10 > 0) {
            this.nativeHeapAvg = (int) ((((double) ((this.nativeHeapAvg * this.sampleCount) + i10)) * 1.0d) / ((double) (this.sampleCount + 1)));
            if (this.nativeHeapMax < i10) {
                this.nativeHeapMax = i10;
            }
            if (this.sampleCount != 0 && this.nativeHeapInit > 0) {
                this.nativeHeapIncrease = i10 - this.nativeHeapInit;
            }
        }
        if (i11 > 0) {
            this.codeAvg = (int) ((((double) ((this.codeAvg * this.sampleCount) + i11)) * 1.0d) / ((double) (this.sampleCount + 1)));
            if (this.codeMax < i11) {
                this.codeMax = i11;
            }
            if (this.sampleCount != 0 && this.codeInit > 0) {
                this.codeIncrease = i11 - this.codeInit;
            }
        }
        if (i12 > 0) {
            this.stackAvg = (int) ((((double) ((this.stackAvg * this.sampleCount) + i12)) * 1.0d) / ((double) (this.sampleCount + 1)));
            if (this.stackMax < i12) {
                this.stackMax = i12;
            }
            if (this.sampleCount != 0 && this.stackInit > 0) {
                this.stackIncrease = i12 - this.stackInit;
            }
        }
        if (i15 > 0) {
            this.graphicsAvg = (int) ((((double) ((this.graphicsAvg * this.sampleCount) + i15)) * 1.0d) / ((double) (this.sampleCount + 1)));
            if (this.graphicsMax < i15) {
                this.graphicsMax = i15;
            }
            if (this.sampleCount != 0 && this.graphicsInit > 0) {
                this.graphicsIncrease = i15 - this.graphicsInit;
            }
        }
        if (i16 > 0) {
            this.dalvikHeapAvg = (int) ((((double) ((this.dalvikHeapAvg * this.sampleCount) + i16)) * 1.0d) / ((double) (this.sampleCount + 1)));
            if (this.dalvikHeapMax < i16) {
                this.dalvikHeapMax = i16;
            }
            if (this.sampleCount != 0 && this.dalvikHeapInit > 0) {
                this.dalvikHeapIncrease = i16 - this.dalvikHeapInit;
            }
        }
        this.sampleCount++;
    }

    public Map<String, Object> getDetails() {

        HashMap hashMap = new HashMap();
        if (this.pssMemoryMax > 0) {
            hashMap.put("memoryMax", Integer.valueOf(this.pssMemoryMax));
        }
        if (this.pssMemoryIncrease > Integer.MIN_VALUE) {
            hashMap.put("memoryIncrease", Integer.valueOf(this.pssMemoryIncrease));
        }
        if (this.javaHeapAvg > 0) {
            hashMap.put("javaHeapAvg", Integer.valueOf(this.javaHeapAvg));
        }
        if (this.javaHeapMax > 0) {
            hashMap.put("javaHeapMax", Integer.valueOf(this.javaHeapMax));
        }
        if (this.javaHeapIncrease > Integer.MIN_VALUE) {
            hashMap.put("javaHeapIncrease", Integer.valueOf(this.javaHeapIncrease));
        }
        if (this.nativeHeapAvg > 0) {
            hashMap.put("nativeHeapAvg", Integer.valueOf(this.nativeHeapAvg));
        }
        if (this.nativeHeapMax > 0) {
            hashMap.put("nativeHeapMax", Integer.valueOf(this.nativeHeapMax));
        }
        if (this.nativeHeapIncrease > Integer.MIN_VALUE) {
            hashMap.put("nativeHeapIncrease", Integer.valueOf(this.nativeHeapIncrease));
        }
        if (this.codeAvg > 0) {
            hashMap.put("codeAvg", Integer.valueOf(this.codeAvg));
        }
        if (this.codeMax > 0) {
            hashMap.put("codeMax", Integer.valueOf(this.codeMax));
        }
        if (this.codeIncrease > Integer.MIN_VALUE) {
            hashMap.put("codeIncrease", Integer.valueOf(this.codeIncrease));
        }
        if (this.stackAvg > 0) {
            hashMap.put("stackAvg", Integer.valueOf(this.stackAvg));
        }
        if (this.stackMax > 0) {
            hashMap.put("stackMax", Integer.valueOf(this.stackMax));
        }
        if (this.stackIncrease > Integer.MIN_VALUE) {
            hashMap.put("stackIncrease", Integer.valueOf(this.stackIncrease));
        }
        if (this.graphicsAvg > 0) {
            hashMap.put("graphicsAvg", Integer.valueOf(this.graphicsAvg));
        }
        if (this.graphicsMax > 0) {
            hashMap.put("graphicsMax", Integer.valueOf(this.graphicsMax));
        }
        if (this.graphicsIncrease > Integer.MIN_VALUE) {
            hashMap.put("graphicsIncrease", Integer.valueOf(this.graphicsIncrease));
        }
        if (this.dalvikHeapAvg > 0) {
            hashMap.put("dalvikHeapAvg", Integer.valueOf(this.dalvikHeapAvg));
        }
        if (this.dalvikHeapMax > 0) {
            hashMap.put("dalvikHeapMax", Integer.valueOf(this.dalvikHeapMax));
        }
        if (this.dalvikHeapIncrease > Integer.MIN_VALUE) {
            hashMap.put("dalvikHeapIncrease", Integer.valueOf(this.dalvikHeapIncrease));
        }
        return hashMap;
    }

    public void computeAvg(long j) {

        double d = (double) j;
        this.avgMemoryUsage = (((this.avgMemoryUsage * ((double) this.oldSampleCount)) + d) * 1.0d) / ((double) (this.oldSampleCount + 1));
        if (this.maxMemoryUsage < d) {
            this.maxMemoryUsage = d;
        }
        this.oldSampleCount++;
    }

    public double getAvgMemoryUsage() {
        return this.avgMemoryUsage;
    }

    public double getMaxMemoryUsage() {
        return this.maxMemoryUsage;
    }

    public int getSampleCount() {
        return this.oldSampleCount;
    }

    public String getPageName() {
        return this.pageName;
    }

    public double getMetricValue() {
        return (double) this.pssMemoryAvg;
    }

    public double getOldMetricValue() {
        return this.avgMemoryUsage;
    }

    public void convertToJson(JSONObject jSONObject) throws JSONException {

        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("pageName", this.pageName);
        jSONArray.put(JSONUtils.buildLogUnit(Constants.MEMORY_AVG, df.format(this.avgMemoryUsage), jSONObject2, this.ts));
        jSONArray.put(JSONUtils.buildLogUnit(Constants.MEMORY_MAX, df.format(this.maxMemoryUsage), jSONObject2, this.ts));
        jSONObject.put(Constants.METRICS, jSONArray);
    }
}