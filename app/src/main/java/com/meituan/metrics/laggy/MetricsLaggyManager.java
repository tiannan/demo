package com.meituan.metrics.laggy;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.meituan.android.common.babel.Babel;
import com.meituan.android.common.kitefly.Log.Builder;
import com.meituan.android.common.locate.locator.GearsLocator;
import com.meituan.android.common.statistics.Constants;
import com.meituan.android.common.unionid.oneid.util.DeviceInfo;
import com.meituan.android.pike.bean.proto.ProtoConstant;
import com.meituan.metrics.Environment;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.util.LogUtil;
import com.meituan.metrics.util.TimeUtil;
import com.meituan.metrics.util.thread.Task;
import com.meituan.metrics.util.thread.ThreadManager;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class MetricsLaggyManager implements LaggyCallback {
    private static final String LAGGY_DURATION = "duration";
    public static final String MAIN = "main";
    private static final String RN_THREAD_NAME = "rn_thread_name";
    private static final String TAG = "MetricsLaggyManager";
    private static volatile MetricsLaggyManager sInstance;
    private Handler handler;
    private boolean isLagEnable;
    private int mLagThreshold;
    private int mMaxReportTimes;
    private final Map<String, LaggyLooperPrinter> mPrinterMap;
    private final Map<String, Integer> mRemainReportCnt;
    private LaggyLooperPrinter mainPrinter;

    public MetricsLaggyManager() {

        this.mMaxReportTimes = 0;
        this.mRemainReportCnt = new HashMap();
        this.mPrinterMap = new HashMap();
    }

    public static MetricsLaggyManager getInstance() {

        if (sInstance == null) {
            synchronized (MetricsLaggyManager.class) {
                if (sInstance == null) {
                    sInstance = new MetricsLaggyManager();
                }
            }
        }
        return sInstance;
    }

    /* JADX WARNING: Missing block: B:20:0x0091, code:
            return;
     */
    public synchronized void init(boolean r12, int r13, int r14, boolean r15) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = 4;
        r0 = new java.lang.Object[r0];	 Catch:{ all -> 0x0092 }
        r1 = java.lang.Byte.valueOf(r12);	 Catch:{ all -> 0x0092 }
        r8 = 0;
        r0[r8] = r1;	 Catch:{ all -> 0x0092 }
        r1 = 1;
        r2 = java.lang.Integer.valueOf(r13);	 Catch:{ all -> 0x0092 }
        r0[r1] = r2;	 Catch:{ all -> 0x0092 }
        r1 = 2;
        r2 = java.lang.Integer.valueOf(r14);	 Catch:{ all -> 0x0092 }
        r0[r1] = r2;	 Catch:{ all -> 0x0092 }
        r1 = 3;
        r2 = java.lang.Byte.valueOf(r15);	 Catch:{ all -> 0x0092 }
        r0[r1] = r2;	 Catch:{ all -> 0x0092 }
        r9 = changeQuickRedirect;	 Catch:{ all -> 0x0092 }
        r10 = "db2f6043ae9250102eb635539861a1a0";
        r4 = 0;
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r1 = r0;
        r2 = r11;
        r3 = r9;
        r5 = r10;
        r1 = com.meituan.robust.PatchProxy.isSupport(r1, r2, r3, r4, r5, r6);	 Catch:{ all -> 0x0092 }
        if (r1 == 0) goto L_0x0036;
    L_0x0031:
        com.meituan.robust.PatchProxy.accessDispatch(r0, r11, r9, r8, r10);	 Catch:{ all -> 0x0092 }
        monitor-exit(r11);
        return;
    L_0x0036:
        r0 = com.meituan.metrics.Metrics.debug;	 Catch:{ all -> 0x0092 }
        if (r0 == 0) goto L_0x0046;
    L_0x003a:
        r14 = com.meituan.metrics.Metrics.getInstance();	 Catch:{ all -> 0x0092 }
        r14 = r14.getAppConfig();	 Catch:{ all -> 0x0092 }
        r14 = r14.getDebugMaxLaggyReportTimes();	 Catch:{ all -> 0x0092 }
    L_0x0046:
        r11.isLagEnable = r12;	 Catch:{ all -> 0x0092 }
        r14 = java.lang.Math.max(r8, r14);	 Catch:{ all -> 0x0092 }
        r11.mMaxReportTimes = r14;	 Catch:{ all -> 0x0092 }
        r14 = java.lang.Math.max(r8, r13);	 Catch:{ all -> 0x0092 }
        r11.mLagThreshold = r14;	 Catch:{ all -> 0x0092 }
        if (r12 == 0) goto L_0x0058;
    L_0x0056:
        if (r13 > 0) goto L_0x005a;
    L_0x0058:
        if (r15 == 0) goto L_0x0082;
    L_0x005a:
        r13 = r11.mLagThreshold;	 Catch:{ all -> 0x0092 }
        r13 = (long) r13;	 Catch:{ all -> 0x0092 }
        r12 = com.meituan.metrics.laggy.LaggyLooperPrinter.newMaiLooperPrinter(r12, r13, r15);	 Catch:{ all -> 0x0092 }
        r11.mainPrinter = r12;	 Catch:{ all -> 0x0092 }
        r12 = r11.mPrinterMap;	 Catch:{ all -> 0x0092 }
        r13 = r11.mainPrinter;	 Catch:{ all -> 0x0092 }
        r13 = r13.targetThreadName;	 Catch:{ all -> 0x0092 }
        r14 = r11.mainPrinter;	 Catch:{ all -> 0x0092 }
        r12.put(r13, r14);	 Catch:{ all -> 0x0092 }
        r12 = r11.mRemainReportCnt;	 Catch:{ all -> 0x0092 }
        r13 = r11.mainPrinter;	 Catch:{ all -> 0x0092 }
        r13 = r13.targetThreadName;	 Catch:{ all -> 0x0092 }
        r14 = r11.mMaxReportTimes;	 Catch:{ all -> 0x0092 }
        r14 = java.lang.Integer.valueOf(r14);	 Catch:{ all -> 0x0092 }
        r12.put(r13, r14);	 Catch:{ all -> 0x0092 }
        r12 = r11.mainPrinter;	 Catch:{ all -> 0x0092 }
        r12.register();	 Catch:{ all -> 0x0092 }
    L_0x0082:
        if (r15 == 0) goto L_0x0090;
    L_0x0084:
        r12 = com.meituan.metrics.util.thread.ThreadManager.getInstance();	 Catch:{ all -> 0x0092 }
        r13 = new com.meituan.metrics.laggy.MetricsLaggyManager$1;	 Catch:{ all -> 0x0092 }
        r13.<init>();	 Catch:{ all -> 0x0092 }
        r12.post(r13);	 Catch:{ all -> 0x0092 }
    L_0x0090:
        monitor-exit(r11);
        return;
    L_0x0092:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.metrics.laggy.MetricsLaggyManager.init(boolean, int, int, boolean):void");
    }

    public void setLagConfig(boolean z, int i, int i2) {
        this.isLagEnable = z;
        this.mLagThreshold = Math.max(0, i);
        this.mMaxReportTimes = Math.max(0, i2);
    }

    /* JADX WARNING: Missing block: B:34:0x0064, code:
            return;
     */
    /* JADX WARNING: Missing block: B:40:0x0089, code:
            return;
     */
    /* JADX WARNING: Missing block: B:42:0x008b, code:
            return;
     */
    public synchronized void addLaggyLopperPrinter(android.os.Looper r12, java.lang.String r13) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = 2;
        r0 = new java.lang.Object[r0];	 Catch:{ all -> 0x008c }
        r8 = 0;
        r0[r8] = r12;	 Catch:{ all -> 0x008c }
        r1 = 1;
        r0[r1] = r13;	 Catch:{ all -> 0x008c }
        r9 = changeQuickRedirect;	 Catch:{ all -> 0x008c }
        r10 = "bd10621a7e94ae86047b8f88dfe54800";
        r4 = 0;
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r1 = r0;
        r2 = r11;
        r3 = r9;
        r5 = r10;
        r1 = com.meituan.robust.PatchProxy.isSupport(r1, r2, r3, r4, r5, r6);	 Catch:{ all -> 0x008c }
        if (r1 == 0) goto L_0x0020;
    L_0x001b:
        com.meituan.robust.PatchProxy.accessDispatch(r0, r11, r9, r8, r10);	 Catch:{ all -> 0x008c }
        monitor-exit(r11);
        return;
    L_0x0020:
        r0 = r11.isLagEnable;	 Catch:{ all -> 0x008c }
        if (r0 == 0) goto L_0x008a;
    L_0x0024:
        if (r12 != 0) goto L_0x0027;
    L_0x0026:
        goto L_0x008a;
    L_0x0027:
        r0 = android.os.Looper.getMainLooper();	 Catch:{ all -> 0x008c }
        if (r12 != r0) goto L_0x002f;
    L_0x002d:
        monitor-exit(r11);
        return;
    L_0x002f:
        r0 = r11.mPrinterMap;	 Catch:{ all -> 0x008c }
        r0 = r0.containsKey(r13);	 Catch:{ all -> 0x008c }
        if (r0 == 0) goto L_0x0039;
    L_0x0037:
        monitor-exit(r11);
        return;
    L_0x0039:
        r0 = r11.mLagThreshold;	 Catch:{ all -> 0x008c }
        if (r0 != 0) goto L_0x0065;
    L_0x003d:
        r0 = r11.mMaxReportTimes;	 Catch:{ all -> 0x008c }
        if (r0 != 0) goto L_0x0065;
    L_0x0041:
        r0 = com.meituan.metrics.config.MetricsRemoteConfigManager.getInstance();	 Catch:{ all -> 0x008c }
        r0 = r0.getRemoteConfigV2();	 Catch:{ all -> 0x008c }
        if (r0 == 0) goto L_0x0063;
    L_0x004b:
        r1 = r0.isLagEnable();	 Catch:{ all -> 0x008c }
        if (r1 != 0) goto L_0x0052;
    L_0x0051:
        goto L_0x0063;
    L_0x0052:
        r1 = r0.lagThreshold;	 Catch:{ all -> 0x008c }
        r1 = java.lang.Math.max(r8, r1);	 Catch:{ all -> 0x008c }
        r11.mLagThreshold = r1;	 Catch:{ all -> 0x008c }
        r0 = r0.maxReportCallstackTimes;	 Catch:{ all -> 0x008c }
        r0 = java.lang.Math.max(r8, r0);	 Catch:{ all -> 0x008c }
        r11.mMaxReportTimes = r0;	 Catch:{ all -> 0x008c }
        goto L_0x0065;
    L_0x0063:
        monitor-exit(r11);
        return;
    L_0x0065:
        r0 = r11.mLagThreshold;	 Catch:{ all -> 0x008c }
        if (r0 <= 0) goto L_0x0088;
    L_0x0069:
        r0 = new com.meituan.metrics.laggy.LaggyLooperPrinter;	 Catch:{ all -> 0x008c }
        r1 = r11.mLagThreshold;	 Catch:{ all -> 0x008c }
        r1 = (long) r1;	 Catch:{ all -> 0x008c }
        r0.<init>(r1, r12, r13);	 Catch:{ all -> 0x008c }
        r12 = r11.mPrinterMap;	 Catch:{ all -> 0x008c }
        r13 = r0.targetThreadName;	 Catch:{ all -> 0x008c }
        r12.put(r13, r0);	 Catch:{ all -> 0x008c }
        r12 = r11.mRemainReportCnt;	 Catch:{ all -> 0x008c }
        r13 = r0.targetThreadName;	 Catch:{ all -> 0x008c }
        r1 = r11.mMaxReportTimes;	 Catch:{ all -> 0x008c }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x008c }
        r12.put(r13, r1);	 Catch:{ all -> 0x008c }
        r0.register();	 Catch:{ all -> 0x008c }
    L_0x0088:
        monitor-exit(r11);
        return;
    L_0x008a:
        monitor-exit(r11);
        return;
    L_0x008c:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.metrics.laggy.MetricsLaggyManager.addLaggyLopperPrinter(android.os.Looper, java.lang.String):void");
    }

    public Handler createLaggyDetectHandler() {
        Object[] objArr = new Object[0];

        if (this.handler == null) {
            HandlerThread handlerThread = new HandlerThread("Metrics Laggy Thread");
            handlerThread.start();
            this.handler = new Handler(handlerThread.getLooper());
        }
        return this.handler;
    }

    public void onLaggyEvent(long j, String str, List<ThreadStackEntity> list) {
        final String str2 = str;

        if (list != null) {
            if (!list.isEmpty()) {
                final LaggyEvent laggyEvent = new LaggyEvent(MetricsActivityLifecycleManager.currentActivity, j, (long) this.mLagThreshold, list);
                laggyEvent.timestamp = TimeUtil.currentTimeMillis();
                laggyEvent.guid = UUID.randomUUID().toString();
                Metrics.getInstance().getInterceptorChain().onNewEvent(laggyEvent);
                Integer num = (Integer) this.mRemainReportCnt.get(str2);
                if (num != null) {
                    Integer valueOf = Integer.valueOf(num.intValue() - 1);
                    if (num.intValue() > 0) {
                        this.mRemainReportCnt.put(str2, valueOf);
                        ThreadManager.getInstance().postNet(new Task() {

                            public void schedule() {

                                MetricsLaggyManager.this.reportLagLog(laggyEvent, str2);
                                PrintStream printStream = System.out;
                                StringBuilder stringBuilder = new StringBuilder("LagLog GUID: ");
                                stringBuilder.append(laggyEvent.guid);
                                printStream.println(stringBuilder.toString());
                                Metrics.getInstance().getInterceptorChain().onReportEvent(laggyEvent);
                            }
                        });
                        return;
                    }
                }
                LaggyLooperPrinter laggyLooperPrinter = (LaggyLooperPrinter) this.mPrinterMap.get(str2);
                if (laggyLooperPrinter != null) {
                    laggyLooperPrinter.unregister();
                }
            }
        }
    }

    private void reportLagLog(LaggyEvent laggyEvent, String str) {
       if (laggyEvent != null) {
            Environment environment = Metrics.getEnvironment();
            if (environment != null) {
                if (!TextUtils.isEmpty(environment.getToken())) {
                    StringBuilder stringBuilder = new StringBuilder(laggyEvent.resolveUnionStack());
                    String allSatck = laggyEvent.getAllSatck();
                    if (!TextUtils.isEmpty(allSatck)) {
                        stringBuilder.append("\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\nAllMainThreadStack");
                        stringBuilder.append(10);
                        stringBuilder.append(allSatck);
                        stringBuilder.append(10);
                    }
                    Builder builder = new Builder(stringBuilder.toString());
                    builder.tag("lag_log");
                    builder.reportChannel("fe_perf_babel_public");
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", "lag_log");
                    hashMap.put("threshold", Long.valueOf(laggyEvent.getThreshold()));
                    hashMap.put(ProtoConstant.TOKEN, environment.getToken());
                    hashMap.put("platform", environment.os);
                    hashMap.put("appVersion", environment.getAppVersion());
                    hashMap.put(Constants.Environment.KEY_OS, environment.os);
                    hashMap.put(DeviceInfo.OS_VERSION, environment.osVersion);
                    hashMap.put(DeviceInfo.SDK_VERSION, environment.sdkVersion);
                    hashMap.put("apkHash", environment.getApkHash());
                    hashMap.put("occurTime", TimeUtil.formatDateTime(laggyEvent.timestamp));
                    hashMap.put("uploadTime", TimeUtil.formatDateTime(TimeUtil.currentTimeMillis()));
                    hashMap.put("guid", laggyEvent.guid);
                    hashMap.put("lastPage", laggyEvent.getPage());
                    hashMap.put("pageStack", MetricsActivityLifecycleManager.getActions());
                    hashMap.put("appStore", environment.getCh());
                    hashMap.put(GearsLocator.CITY, String.valueOf(environment.getCityId()));
                    hashMap.put("network", environment.getNet());
                    hashMap.put("carrier", environment.getMccmnc());
                    hashMap.put("uuid", environment.getUuid());
                    hashMap.put("deviceId", environment.getDeviceId());
                    hashMap.put("userInfo", getUserInfo(laggyEvent, str));
                    LogUtil.d("", "Babel map", hashMap);
                    builder.optional(hashMap);
                    builder.token(environment.getToken());
                    Babel.logRT(builder.build());
                }
            }
        }
    }

    private String getUserInfo(LaggyEvent laggyEvent, String str) {

        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(RN_THREAD_NAME, str);
            jSONObject.put("duration", laggyEvent.getDuration());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}