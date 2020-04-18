package com.meituan.metrics.net.report;

import android.text.TextUtils;
import com.meituan.android.common.babel.MQC;
import com.meituan.android.common.badge.Strategy;
import com.meituan.metrics.Environment;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.common.Constants;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.lifecycle.MetricsAppMonitorCallback;
import com.meituan.metrics.model.AbstractEvent;
import com.meituan.metrics.net.retrofit.MetricsRetrofit;
import com.meituan.metrics.sampler.cpu.CpuEvent;
import com.meituan.metrics.sampler.memory.MemoryEvent;
import com.meituan.metrics.speedmeter.SpeedMeterEvent;
import com.meituan.metrics.traffic.TrafficEvent;
import com.meituan.metrics.util.LogUtil;
import com.meituan.metrics.util.NetUtils;
import com.meituan.metrics.util.thread.Task;
import com.meituan.metrics.util.thread.ThreadManager;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.common.CommonConstant.Symbol;
import com.meituan.robust.utils.RobustBitConfig;
import com.sankuai.meituan.retrofit2.RequestBody;
import com.sankuai.meituan.retrofit2.RequestBodyBuilder;
import com.sankuai.meituan.retrofit2.Response;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class MetricsReportManager implements MetricsAppMonitorCallback {
    public static ChangeQuickRedirect changeQuickRedirect;
    private static MetricsReportManager sInstance;
    private String categoryStr;
    private boolean isStarted;

    public void onForeground() {
    }

    public MetricsReportManager() {
        this.categoryStr = "met_metrics_mobile";
    }

    public static MetricsReportManager getInstance() {

        if (sInstance == null) {
            synchronized (MetricsReportManager.class) {
                if (sInstance == null) {
                    sInstance = new MetricsReportManager();
                    MetricsActivityLifecycleManager.getInstance().registerAppMonitor(sInstance);
                }
            }
        }
        return sInstance;
    }

    public void startReportRegular() {
      if (!this.isStarted) {
            LogUtil.d("开始定期60s上报");
            int i = Strategy.DEFAULT_BASE_AUTO_SYNC_INTERVAL;
            if (Metrics.debug) {
                i = 5000;
            }
            long j = (long) i;
            ThreadManager.getInstance().scheduleAtFixedRate(new Task() {

                public void schedule() {

                        MetricsReportManager.this.reportCachedData();

                }
            }, j, j);
            this.isStarted = true;
        }
    }

    public void reportImmediately(final String str) {

            ThreadManager.getInstance().postNet(new Task() {
                public static ChangeQuickRedirect changeQuickRedirect;

                public void schedule() {

                        MetricsReportManager.this.report(str);

                }
            });

    }

    public void onBackground() {

            ThreadManager.getInstance().postNet(new Task() {
                public static ChangeQuickRedirect changeQuickRedirect;

                public void schedule() {

                        MetricsReportManager.this.reportCachedData();

                }
            });

    }

    private void reportCachedData() {

        ArrayList<AbstractEvent> arrayList = new ArrayList();
        MetricsCacheManager.getInstance().getAllData(arrayList);
        if (arrayList.size() > 0) {
            Environment environment = Metrics.getEnvironment();
            if (environment != null) {
                JSONObject toJson = environment.toJson();
                if (toJson != null) {
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    try {
                        jSONObject.put("category", "metrics_mobile");
                        jSONObject.put("env", toJson);
                        for (AbstractEvent abstractEvent : arrayList) {
                            if (abstractEvent != null) {
                                Object obj = abstractEvent.toJson().get(Constants.METRICS);
                                if (!(obj instanceof JSONObject) && (obj instanceof JSONArray)) {
                                    JSONArray jSONArray2 = (JSONArray) obj;
                                    for (int i = 0; i < jSONArray2.length(); i++) {
                                        jSONArray.put(jSONArray2.get(i));
                                    }
                                    Metrics.getInstance().getInterceptorChain().onReportEvent(abstractEvent);
                                }
                            }
                        }
                        if (jSONArray.length() > 0) {
                            jSONObject.put("logs", jSONArray);
                            JSONArray jSONArray3 = new JSONArray();
                            jSONArray3.put(jSONObject);
                            report(jSONArray3.toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void reportByBabel(AbstractEvent abstractEvent) {
        AbstractEvent abstractEvent2 = abstractEvent;

        Environment environment = Metrics.getEnvironment();
        if (environment != null) {
            str = environment.getToken();
            String localEventType = abstractEvent.getLocalEventType();
            double metricValue = abstractEvent.getMetricValue();
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(localEventType))) {
                if (metricValue > 0.0d) {
                    HashMap hashMap = new HashMap();
                    Object obj = -1;
                    switch (localEventType.hashCode()) {
                        case -1792322499:
                            if (localEventType.equals(Constants.MEMORY_V2)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -1225808762:
                            if (localEventType.equals(Constants.FPS_PAGE_AVG)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -1213322918:
                            if (localEventType.equals(Constants.PAGE_LOAD_METER)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -824151218:
                            if (localEventType.equals(Constants.CPU_V2)) {
                                obj = 5;
                                break;
                            }
                            break;
                        case -43454776:
                            if (localEventType.equals(Constants.FPS_SCROLL_AVG)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 515410340:
                            if (localEventType.equals(Constants.FPS_CUSTOM_AVG)) {
                                obj = 7;
                                break;
                            }
                            break;
                        case 660945209:
                            if (localEventType.equals(Constants.COLD_LAUNCH_METER)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1174807922:
                            if (localEventType.equals(Constants.TRAFFIC_DAILY_TOTAL_STREAM)) {
                                obj = 9;
                                break;
                            }
                            break;
                        case 1874447228:
                            if (localEventType.equals(Constants.CUSTOM_SPEED_METER)) {
                                obj = 8;
                                break;
                            }
                            break;
                        case 2014164419:
                            if (localEventType.equals(Constants.PAGE_LOAD_AUTO)) {
                                obj = 6;
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                    switch (obj) {
                        case null:
                            hashMap.put(SocialConstants.PARAM_SOURCE, Constants.METRICS);
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            hashMap.put("pageName", abstractEvent.getPageName());
                            break;
                        case 7:
                        case 8:
                            hashMap.put("key", abstractEvent.getPageName());
                            break;
                        case 9:
                            break;
                        default:
                            return;
                    }
                    if (abstractEvent2.optionTags != null) {
                        hashMap.putAll(abstractEvent2.optionTags);
                    }
                    HashMap hashMap2 = null;
                    Map steps;
                    if (abstractEvent2 instanceof SpeedMeterEvent) {
                        steps = ((SpeedMeterEvent) abstractEvent2).getSteps();
                        if (steps != null) {
                            hashMap2 = new HashMap(steps);
                        }
                    } else {
                        if (abstractEvent2 instanceof MemoryEvent) {
                            MemoryEvent memoryEvent = (MemoryEvent) abstractEvent2;
                            steps = memoryEvent.getDetails();
                            hashMap.put("dalvikMax", Integer.valueOf(memoryEvent.dalvikMax));
                        } else if (abstractEvent2 instanceof CpuEvent) {
                            hashMap2 = new HashMap();
                            hashMap2.put(Constants.CPU_MAX_V2, Double.valueOf(((CpuEvent) abstractEvent2).getMaxAppCpuRate()));
                        } else if (abstractEvent2 instanceof TrafficEvent) {
                            TrafficEvent trafficEvent = (TrafficEvent) abstractEvent2;
                            steps = trafficEvent.getDetails();
                            hashMap.put(Constants.TRAFFIC_TAG_DATE, trafficEvent.getDate().replace(Symbol.MINUS, "/"));
                        }
                        hashMap2 = steps;
                    }
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("metricsSdkVersion", environment.sdkVersion);
                    hashMap3.put(com.meituan.android.common.statistics.Constants.Environment.KEY_CH, environment.getCh());
                    LogUtil.d("NewEvent", str, localEventType, Double.valueOf(metricValue), hashMap2, abstractEvent2.raw, hashMap, hashMap3);
                    MQC.report(this.categoryStr, str, localEventType, Double.valueOf(metricValue), hashMap2, abstractEvent2.raw, hashMap, hashMap3);
                }
            }
        }
    }

    private void report(String str) {

        RequestBody requestBody = null;
        byte[] stringToGzipData = NetUtils.stringToGzipData(str);
        if (stringToGzipData != null) {
            requestBody = RequestBodyBuilder.build(stringToGzipData, "application/json");
        }
        if (requestBody != null) {
            try {
                int i = -1;
                Response execute = MetricsRetrofit.getInstance().postMetricsData(requestBody).execute();
                if (execute != null) {
                    i = execute.code();
                }
                if (Metrics.debug && i == 200) {
                    LogUtil.d(str);
                    LogUtil.d("report done");
                }
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder("report failure: ");
                stringBuilder.append(e.getMessage());
                LogUtil.d(stringBuilder.toString());
            }
        }
    }

    public void setCategory(String str) {
        this.categoryStr = str;
    }

    public String getCategory() {
        return this.categoryStr;
    }
}