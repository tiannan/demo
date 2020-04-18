package com.meituan.metrics.laggy;

import androidx.annotation.NonNull;

import com.meituan.metrics.common.Constants;
import com.meituan.metrics.model.AbstractEvent;
import com.meituan.metrics.util.ThreadStackUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class LaggyEvent extends AbstractEvent {
    private final long duration;
    public String guid;
    private final String page;
    private List<ThreadStackEntity> stackEntities;
    private final List<StackTraceElement[]> stackTraceElements;
    private final long threshold;
    public long timestamp;
    private String unionStackTrace;

    public String getEventType() {
        return "lag_log";
    }

    public String getLocalEventType() {
        return "lag_log";
    }

    public double getMetricValue() {
        return 0.0d;
    }

    public LaggyEvent(String str, long j, long j2, @NonNull List<ThreadStackEntity> list) {
        List<ThreadStackEntity> list2 = list;

        this.page = r8;
        this.duration = r9;
        this.threshold = r11;
        this.stackEntities = list;
        this.stackTraceElements = new ArrayList();
        for (ThreadStackEntity threadStackEntity : list) {
            this.stackTraceElements.add(threadStackEntity.stackTraceElements);
        }
    }

    public String getPage() {
        return this.page;
    }

    public long getDuration() {
        return this.duration;
    }

    public long getThreshold() {
        return this.threshold;
    }

    public List<StackTraceElement[]> getStackTraceElements() {
        return this.stackTraceElements;
    }

    public String resolveUnionStack() {

        if (this.unionStackTrace != null) {
            return this.unionStackTrace;
        }
        this.unionStackTrace = ThreadStackUtils.resolveUnionStack(this.stackTraceElements);
        return this.unionStackTrace;
    }

    public String getAllSatck() {

        return  ThreadStackUtils.getAllStackTrace(this.stackEntities);
    }

    public String getPageName() {
        return this.page;
    }

    public void convertToJson(JSONObject jSONObject) throws JSONException {

        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("pageName", this.page);
        jSONObject2.put("event_type", "stack");
        jSONObject2.put("lag_log", resolveUnionStack());
        jSONObject2.put("threshold", this.threshold);
        jSONArray.put(jSONObject2);
        jSONObject.put(Constants.METRICS, jSONArray);
    }
}