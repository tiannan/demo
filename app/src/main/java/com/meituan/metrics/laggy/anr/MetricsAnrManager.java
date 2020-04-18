package com.meituan.metrics.laggy.anr;

import android.app.ActivityManager;
import android.app.ActivityManager.ProcessErrorStateInfo;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Looper;
import android.text.TextUtils;
import com.meituan.android.common.babel.Babel;
import com.meituan.android.common.kitefly.Log.Builder;
import com.meituan.android.common.locate.locator.GearsLocator;
import com.meituan.android.common.unionid.oneid.util.DeviceInfo;
import com.meituan.android.pike.bean.proto.ProtoConstant;
import com.meituan.crashreporter.crash.CrashExtraInfo;
import com.meituan.metrics.Environment;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.common.Constants;
import com.meituan.metrics.laggy.ThreadStackEntity;
import com.meituan.metrics.util.LogUtil;
import com.meituan.metrics.util.ThreadStackUtils;
import com.meituan.metrics.util.TimeUtil;
import com.meituan.metrics.util.thread.Task;
import com.meituan.metrics.util.thread.ThreadManager;

import com.meituan.snare.d;
import com.meituan.snare.d.a;
import com.meituan.snare.f;
import com.meituan.snare.g;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class MetricsAnrManager implements AnrCallback {
    public static final long ANR_THRESHOLD = 5000;
    public static final String TAG = "MetricsAnrManager";
    private static final String TRACE_PATH = "/data/anr/";
    private static final String TRACE_PATH_FILE = "/data/anr/traces.txt";
    private static final long VALIDATE_TIME = 3600000;
    private static MetricsAnrManager sInstance = new MetricsAnrManager();
    private String anrPath;
    private Context context;
    private d exceptionHandler;
    private AnrFileObserver fileObserver;
    private boolean init;
    private volatile boolean lastAnrHasTrace;
    private volatile String lastAnrInfo;
    private volatile long lastAnrTime;
    private volatile boolean needBreak;

    public static MetricsAnrManager getInstance() {
        return sInstance;
    }

    public MetricsAnrManager() {

        this.init = false;
        this.needBreak = false;
    }

    public void init(Context context) {

        if (!this.init) {
            if (context != null) {
                this.context = context;
                a aVar = new a(context, new g() {

                    public void report(f fVar) {
                    }
                });
                aVar.b = Constants.METRICS;
                aVar.c = Metrics.getInstance().getAppConfig().getReportStrategy();
                this.exceptionHandler = aVar.a();
                this.init = true;
                if (this.fileObserver == null) {
                    fetchObserverPath();
                    this.fileObserver = new AnrFileObserver(this.anrPath, this);
                    this.fileObserver.startWatching();
                }
            }
        }
    }

    private void fetchObserverPath() {
       if (VERSION.SDK_INT >= 23) {
            this.anrPath = TRACE_PATH_FILE;
        } else {
            if (VERSION.SDK_INT >= 21) {
                File[] listFiles = new File("/proc/").listFiles(new FilenameFilter() {

                    public boolean accept(File file, String str) {
                        return  str.toLowerCase().startsWith("mtk_");
                    }
                });
                if (listFiles == null || listFiles.length <= 0) {
                    this.anrPath = TRACE_PATH_FILE;
                    return;
                }
                this.anrPath = TRACE_PATH;
            } else {
                this.anrPath = TRACE_PATH;
            }
        }
    }

    public void onAnrEvent(long j, String str, List<ThreadStackEntity> list) {

        this.needBreak = TextUtils.isEmpty(str) ^ 1;
        LogUtil.d(TAG, "onAnrEvent", str);
        final long j2 = j;
        final String str4 = str;
        final List<ThreadStackEntity> list2 = list;
        ThreadManager.getInstance().postIO(new Task() {

            public void schedule() {

                MetricsAnrManager.this.collectAnr(j2, str4, list2);

            }
        });
    }

    /* JADX WARNING: Missing block: B:54:0x0172, code:
            com.meituan.metrics.util.LogUtil.d(TAG, "AnrEvent", r4);
            r0 = com.meituan.metrics.Metrics.getInstance().getAppConfig().getReportStrategy();
            r1 = android.os.Looper.getMainLooper().getThread();
            r2 = new java.lang.Throwable(r4.getMainThread());
     */
    /* JADX WARNING: Missing block: B:55:0x019c, code:
            if (r0 == null) goto L_0x01b4;
     */
    /* JADX WARNING: Missing block: B:57:0x01a5, code:
            if (r0.a(3, r1, r2, r8.exceptionHandler) != false) goto L_0x01b4;
     */
    /* JADX WARNING: Missing block: B:58:0x01a7, code:
            com.meituan.metrics.util.LogUtil.d(TAG, "strategy not needReport");
     */
    /* JADX WARNING: Missing block: B:59:0x01b3, code:
            return;
     */
    /* JADX WARNING: Missing block: B:60:0x01b4, code:
            com.meituan.metrics.util.LogUtil.d(TAG, "reportAnr");
            reportAnr(r4);
     */
    /* JADX WARNING: Missing block: B:61:0x01c3, code:
            return;
     */
    private void collectAnr(long r20, java.lang.String r22, java.util.List<com.meituan.metrics.laggy.ThreadStackEntity> r23) {
        /*
        r19 = this;
        r8 = r19;
        r9 = r20;
        r0 = r22;
        r11 = r23;
        r12 = 3;
        r13 = new java.lang.Object[r12];
        r1 = new java.lang.Long;
        r1.<init>(r9);
        r14 = 0;
        r13[r14] = r1;
        r15 = 1;
        r13[r15] = r0;
        r6 = 2;
        r13[r6] = r11;
        r7 = changeQuickRedirect;
        r5 = "0cb1e47f4f750bb510122e1cb2ff81a1";
        r4 = 0;
        r16 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r1 = r13;
        r2 = r19;
        r3 = r7;
        r18 = r5;
        r12 = r7;
        r15 = 2;
        r6 = r16;
        r1 = com.meituan.robust.PatchProxy.isSupport(r1, r2, r3, r4, r5, r6);
        if (r1 == 0) goto L_0x0036;
    L_0x0030:
        r1 = r18;
        com.meituan.robust.PatchProxy.accessDispatch(r13, r8, r12, r14, r1);
        return;
    L_0x0036:
        r8.needBreak = r14;
        r1 = com.meituan.metrics.Metrics.getEnvironment();
        r2 = r19.getProcessErrorStateInfo();
        r3 = r8.context;
        if (r3 == 0) goto L_0x01cc;
    L_0x0044:
        if (r1 == 0) goto L_0x01cc;
    L_0x0046:
        if (r2 == 0) goto L_0x01cc;
    L_0x0048:
        r3 = r2.size();
        if (r3 <= 0) goto L_0x01cc;
    L_0x004e:
        r2 = r2.iterator();
    L_0x0052:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x01cc;
    L_0x0058:
        r3 = r2.next();
        r3 = (android.app.ActivityManager.ProcessErrorStateInfo) r3;
        if (r3 == 0) goto L_0x01c8;
    L_0x0060:
        r4 = r3.condition;
        if (r4 != r15) goto L_0x01c8;
    L_0x0064:
        r2 = r8.context;
        r2 = r2.getPackageName();
        r4 = r3.longMsg;
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 != 0) goto L_0x01c7;
    L_0x0072:
        r4 = android.text.TextUtils.isEmpty(r2);
        if (r4 != 0) goto L_0x01c7;
    L_0x0078:
        r4 = r3.pid;
        r5 = android.os.Process.myPid();
        if (r4 == r5) goto L_0x0082;
    L_0x0080:
        goto L_0x01c7;
    L_0x0082:
        r4 = r3.longMsg;
        r5 = "ANR in ";
        r4 = r4.contains(r5);
        if (r4 != 0) goto L_0x008d;
    L_0x008c:
        return;
    L_0x008d:
        r4 = new com.meituan.metrics.laggy.anr.AnrEvent;
        r4.<init>();
        r5 = java.util.UUID.randomUUID();
        r5 = r5.toString();
        r4.setGuid(r5);
        r5 = r1.getAppVersion();
        r4.setAnrVersion(r5);
        r5 = r3.tag;
        r4.setActivity(r5);
        r4.setTimestamp(r9);
        r5 = com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager.getActions();
        r4.setcActivity(r5);
        r5 = r3.longMsg;
        r4.setErrorMsg(r5);
        r3 = r3.shortMsg;
        r4.setShortMst(r3);
        r3 = r1.getCh();
        r4.setCh(r3);
        r3 = r1.getNet();
        r4.setNet(r3);
        r5 = r1.getCityId();
        r4.setCity(r5);
        r3 = r1.getApkHash();
        r4.setApkHash(r3);
        r1 = r1.getUuid();
        r4.setUuid(r1);
        r8.setStackTrace(r4, r11);
        monitor-enter(r19);
        r5 = r8.lastAnrTime;	 Catch:{ all -> 0x01c4 }
        r1 = 0;
        r5 = r9 - r5;
        r5 = java.lang.Math.abs(r5);	 Catch:{ all -> 0x01c4 }
        r11 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r1 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1));
        if (r1 >= 0) goto L_0x0101;
    L_0x00f3:
        r0 = "MetricsAnrManager";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x01c4 }
        r2 = "Anr时间间隔错误";
        r1[r14] = r2;	 Catch:{ all -> 0x01c4 }
        com.meituan.metrics.util.LogUtil.d(r0, r1);	 Catch:{ all -> 0x01c4 }
        monitor-exit(r19);	 Catch:{ all -> 0x01c4 }
        return;
    L_0x0101:
        r8.lastAnrTime = r9;	 Catch:{ all -> 0x01c4 }
        r1 = android.text.TextUtils.isEmpty(r22);	 Catch:{ all -> 0x01c4 }
        if (r1 != 0) goto L_0x010c;
    L_0x0109:
        r8.parseTraceFile(r0, r4, r2);	 Catch:{ all -> 0x01c4 }
    L_0x010c:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01c4 }
        r1.<init>();	 Catch:{ all -> 0x01c4 }
        r2 = r4.getMainThread();	 Catch:{ all -> 0x01c4 }
        r1.append(r2);	 Catch:{ all -> 0x01c4 }
        r2 = r4.getShortMst();	 Catch:{ all -> 0x01c4 }
        r1.append(r2);	 Catch:{ all -> 0x01c4 }
        r1 = r1.toString();	 Catch:{ all -> 0x01c4 }
        r2 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x014a;
    L_0x012a:
        r2 = r8.lastAnrInfo;	 Catch:{ all -> 0x01c4 }
        r2 = android.text.TextUtils.equals(r2, r1);	 Catch:{ all -> 0x01c4 }
        if (r2 == 0) goto L_0x014a;
    L_0x0132:
        r2 = r8.lastAnrHasTrace;	 Catch:{ all -> 0x01c4 }
        if (r2 != 0) goto L_0x013c;
    L_0x0136:
        r2 = android.text.TextUtils.isEmpty(r22);	 Catch:{ all -> 0x01c4 }
        if (r2 == 0) goto L_0x014a;
    L_0x013c:
        r0 = "MetricsAnrManager";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x01c4 }
        r2 = "相同anr，过滤";
        r1[r14] = r2;	 Catch:{ all -> 0x01c4 }
        com.meituan.metrics.util.LogUtil.d(r0, r1);	 Catch:{ all -> 0x01c4 }
        monitor-exit(r19);	 Catch:{ all -> 0x01c4 }
        return;
    L_0x014a:
        r2 = "MetricsAnrManager";
        r3 = 4;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x01c4 }
        r5 = "tempAnrInfo";
        r3[r14] = r5;	 Catch:{ all -> 0x01c4 }
        r5 = 1;
        r3[r5] = r1;	 Catch:{ all -> 0x01c4 }
        r5 = "lastAnrInfo";
        r3[r15] = r5;	 Catch:{ all -> 0x01c4 }
        r5 = r8.lastAnrInfo;	 Catch:{ all -> 0x01c4 }
        r6 = 3;
        r3[r6] = r5;	 Catch:{ all -> 0x01c4 }
        com.meituan.metrics.util.LogUtil.d(r2, r3);	 Catch:{ all -> 0x01c4 }
        r8.lastAnrInfo = r1;	 Catch:{ all -> 0x01c4 }
        r0 = android.text.TextUtils.isEmpty(r22);	 Catch:{ all -> 0x01c4 }
        if (r0 == 0) goto L_0x016e;
    L_0x016a:
        r8.lastAnrHasTrace = r14;	 Catch:{ all -> 0x01c4 }
        r0 = 1;
        goto L_0x0171;
    L_0x016e:
        r0 = 1;
        r8.lastAnrHasTrace = r0;	 Catch:{ all -> 0x01c4 }
    L_0x0171:
        monitor-exit(r19);	 Catch:{ all -> 0x01c4 }
        r1 = "MetricsAnrManager";
        r2 = new java.lang.Object[r15];
        r3 = "AnrEvent";
        r2[r14] = r3;
        r2[r0] = r4;
        com.meituan.metrics.util.LogUtil.d(r1, r2);
        r0 = com.meituan.metrics.Metrics.getInstance();
        r0 = r0.getAppConfig();
        r0 = r0.getReportStrategy();
        r1 = android.os.Looper.getMainLooper();
        r1 = r1.getThread();
        r2 = new java.lang.Throwable;
        r3 = r4.getMainThread();
        r2.<init>(r3);
        if (r0 == 0) goto L_0x01b4;
    L_0x019e:
        r3 = r8.exceptionHandler;
        r5 = 3;
        r0 = r0.a(r5, r1, r2, r3);
        if (r0 != 0) goto L_0x01b4;
    L_0x01a7:
        r0 = "MetricsAnrManager";
        r3 = 1;
        r1 = new java.lang.Object[r3];
        r2 = "strategy not needReport";
        r1[r14] = r2;
        com.meituan.metrics.util.LogUtil.d(r0, r1);
        return;
    L_0x01b4:
        r3 = 1;
        r0 = "MetricsAnrManager";
        r1 = new java.lang.Object[r3];
        r2 = "reportAnr";
        r1[r14] = r2;
        com.meituan.metrics.util.LogUtil.d(r0, r1);
        r8.reportAnr(r4);
        return;
    L_0x01c4:
        r0 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x01c4 }
        throw r0;
    L_0x01c7:
        return;
    L_0x01c8:
        r3 = 1;
        r5 = 3;
        goto L_0x0052;
    L_0x01cc:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.metrics.laggy.anr.MetricsAnrManager.collectAnr(long, java.lang.String, java.util.List):void");
    }

    private void reportAnr(AnrEvent anrEvent) {

        Environment environment = Metrics.getEnvironment();
        if (environment != null) {
            String token = environment.getToken();
            Builder builder = new Builder(getLog(anrEvent));
            builder.tag(Constants.ANR);
            builder.reportChannel("fe_perf_babel_public");
            builder.ts(anrEvent.getTimestamp());
            HashMap hashMap = new HashMap();
            hashMap.put("type", Constants.ANR);
            hashMap.put(ProtoConstant.TOKEN, token);
            hashMap.put("platform", environment.os);
            hashMap.put("appVersion", anrEvent.getAnrVersion());
            hashMap.put(com.meituan.android.common.statistics.Constants.Environment.KEY_OS, environment.os);
            hashMap.put(DeviceInfo.OS_VERSION, environment.osVersion);
            hashMap.put(DeviceInfo.SDK_VERSION, environment.sdkVersion);
            hashMap.put("apkHash", anrEvent.getApkHash());
            hashMap.put("occurTime", TimeUtil.formatDateTime(anrEvent.getTimestamp()));
            hashMap.put("uploadTime", TimeUtil.formatDateTime(TimeUtil.currentTimeMillis()));
            hashMap.put("guid", anrEvent.getGuid());
            hashMap.put("lastPage", anrEvent.getActivity());
            hashMap.put("pageStack", anrEvent.getcActivity());
            hashMap.put("appStore", anrEvent.getCh());
            hashMap.put(GearsLocator.CITY, String.valueOf(anrEvent.getCity()));
            hashMap.put("network", anrEvent.getNet());
            hashMap.put("carrier", environment.getMccmnc());
            hashMap.put("uuid", environment.getUuid());
            hashMap.put("deviceId", environment.getDeviceId());
            hashMap.put("userInfo", getUserInfo());
            LogUtil.d(TAG, "Babel map", hashMap);
            builder.optional(hashMap);
            builder.token(token);
            Babel.logUrgent(builder.build());
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder("Anr GUID: ");
            stringBuilder.append(anrEvent.getGuid());
            printStream.println(stringBuilder.toString());
        }
    }

    private String getLog(AnrEvent anrEvent) {

        if (anrEvent == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(anrEvent.getMainThread())) {
            stringBuilder.append(anrEvent.getMainThread());
            stringBuilder.append("\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
        }
        if (!TextUtils.isEmpty(anrEvent.getShortMst())) {
            stringBuilder.append("ShortMst\n");
            stringBuilder.append(anrEvent.getShortMst());
            stringBuilder.append("\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
        }
        if (!TextUtils.isEmpty(anrEvent.getErrorMsg())) {
            stringBuilder.append("ErrorMsg\n");
            stringBuilder.append(anrEvent.getErrorMsg());
            stringBuilder.append("\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
        }
        if (!TextUtils.isEmpty(anrEvent.getTraceFile())) {
            stringBuilder.append("TracesInfo\n");
            stringBuilder.append(anrEvent.getTraceFile());
            stringBuilder.append("\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
        }
        if (!TextUtils.isEmpty(anrEvent.allMainThreadStack)) {
            stringBuilder.append("AllMainThreadStack\n");
            stringBuilder.append(anrEvent.allMainThreadStack);
            stringBuilder.append("\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
        }
        if (!TextUtils.isEmpty(anrEvent.getOtherThread())) {
            stringBuilder.append("OtherThread\n");
            stringBuilder.append(anrEvent.getOtherThread());
            stringBuilder.append("\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
        }
        String logcat = CrashExtraInfo.getLogcat(102400);
        if (!TextUtils.isEmpty(logcat)) {
            stringBuilder.append("logcat\n");
            stringBuilder.append(logcat);
        }
        return stringBuilder.toString();
    }

    private String getUserInfo() {

        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PushConstants.EXTRA, Metrics.getInstance().getAppConfig().getAnrOption());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private void setStackTrace(AnrEvent anrEvent, List<ThreadStackEntity> list) {

        try {
            long currentTimeMillis = TimeUtil.currentTimeMillis();
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            if (list == null) {
                System.out.println("MetricsAnrManager sampledStacktrace==null");
                List list2 = new ArrayList();
            }
            list2.add(new ThreadStackEntity(currentTimeMillis, stackTrace));
            ArrayList arrayList = new ArrayList();
            for (ThreadStackEntity threadStackEntity : list2) {
                arrayList.add(threadStackEntity.stackTraceElements);
            }
            anrEvent.setMainThread(ThreadStackUtils.resolveUnionStack(arrayList));
            anrEvent.allMainThreadStack = ThreadStackUtils.getAllStackTrace(list2);
        } catch (Throwable unused) {
            System.out.println("MetricsAnrManager setStackTrace Error");
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : Thread.getAllStackTraces().entrySet()) {
                if (entry.getKey() != Looper.getMainLooper().getThread() && ((StackTraceElement[]) entry.getValue()).length > 0) {
                    stringBuilder.append("#Thread ");
                    stringBuilder.append(((Thread) entry.getKey()).getName());
                    stringBuilder.append(Constants.SPACE);
                    stringBuilder.append(((Thread) entry.getKey()).getId());
                    stringBuilder.append(10);
                    StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) entry.getValue();
                    if (stackTraceElementArr != null && stackTraceElementArr.length > 0) {
                        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                            if (stackTraceElement != null) {
                                stringBuilder.append("at ");
                                stringBuilder.append(stackTraceElement.toString());
                                stringBuilder.append(10);
                            }
                        }
                    }
                }
            }
            anrEvent.setOtherThread(stringBuilder.toString());
        } catch (Throwable unused2) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x0152 A:{SYNTHETIC, Splitter: B:66:0x0152} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x013b A:{SYNTHETIC, Splitter: B:59:0x013b} */
    private void parseTraceFile(java.lang.String r17, com.meituan.metrics.laggy.anr.AnrEvent r18, java.lang.String r19) {
        /*
        r16 = this;
        r0 = r17;
        r1 = r18;
        r2 = r19;
        r3 = 3;
        r3 = new java.lang.Object[r3];
        r11 = 0;
        r3[r11] = r0;
        r12 = 1;
        r3[r12] = r1;
        r13 = 2;
        r3[r13] = r2;
        r14 = changeQuickRedirect;
        r15 = "39c1973f567753fe04acf72ea9f13dc5";
        r7 = 0;
        r9 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r4 = r3;
        r5 = r16;
        r6 = r14;
        r8 = r15;
        r4 = com.meituan.robust.PatchProxy.isSupport(r4, r5, r6, r7, r8, r9);
        if (r4 == 0) goto L_0x002a;
    L_0x0024:
        r4 = r16;
        com.meituan.robust.PatchProxy.accessDispatch(r3, r4, r14, r11, r15);
        return;
    L_0x002a:
        r4 = r16;
        r3 = 0;
        r5 = "..";
        r5 = r0.contains(r5);	 Catch:{ Throwable -> 0x012b }
        if (r5 != 0) goto L_0x011a;
    L_0x0035:
        r5 = "/data/anr/";
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x012b }
        r6.<init>();	 Catch:{ Throwable -> 0x012b }
        r6.append(r5);	 Catch:{ Throwable -> 0x012b }
        r6.append(r0);	 Catch:{ Throwable -> 0x012b }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x012b }
        r7 = new java.io.File;	 Catch:{ Throwable -> 0x012b }
        r7.<init>(r6);	 Catch:{ Throwable -> 0x012b }
        r6 = r7.getAbsolutePath();	 Catch:{ Throwable -> 0x012b }
        r5 = r6.startsWith(r5);	 Catch:{ Throwable -> 0x012b }
        if (r5 == 0) goto L_0x010c;
    L_0x0055:
        r5 = r7.exists();	 Catch:{ Throwable -> 0x012b }
        if (r5 == 0) goto L_0x010c;
    L_0x005b:
        r5 = r7.canRead();	 Catch:{ Throwable -> 0x012b }
        if (r5 != 0) goto L_0x0063;
    L_0x0061:
        goto L_0x010c;
    L_0x0063:
        r5 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x012b }
        r0 = new java.io.FileReader;	 Catch:{ Throwable -> 0x012b }
        r0.<init>(r7);	 Catch:{ Throwable -> 0x012b }
        r5.<init>(r0);	 Catch:{ Throwable -> 0x012b }
        r0 = "^\"main\" .*$";
        r0 = java.util.regex.Pattern.compile(r0);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r6 = 1;
    L_0x0074:
        r7 = r5.readLine();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        if (r7 == 0) goto L_0x00e4;
    L_0x007a:
        r8 = r7.toLowerCase();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r9 = "cmd line: ";
        r8 = r8.contains(r9);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        if (r8 == 0) goto L_0x009e;
    L_0x0086:
        r8 = r7.toLowerCase();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r10 = "cmd line: ";
        r9.<init>(r10);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r9.append(r2);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r8 = r8.contains(r9);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        if (r8 == 0) goto L_0x00e4;
    L_0x009e:
        r8 = "----- end";
        r8 = r7.contains(r8);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        if (r8 != 0) goto L_0x00e4;
    L_0x00a6:
        r8 = 10;
        if (r3 != 0) goto L_0x00c0;
    L_0x00aa:
        r9 = r0.matcher(r7);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r9 = r9.matches();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        if (r9 == 0) goto L_0x0074;
    L_0x00b4:
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r3.<init>();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r3.append(r7);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r3.append(r8);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        goto L_0x0074;
    L_0x00c0:
        r9 = "";
        r9 = r9.equals(r7);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        if (r9 != 0) goto L_0x00cf;
    L_0x00c8:
        r3.append(r7);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r3.append(r8);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        goto L_0x0074;
    L_0x00cf:
        if (r6 == 0) goto L_0x0074;
    L_0x00d1:
        r6 = r3.toString();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r7 = "at ";
        r7 = r6.indexOf(r7);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r6 = r6.substring(r7);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r1.setMainThread(r6);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r6 = 0;
        goto L_0x0074;
    L_0x00e4:
        if (r3 == 0) goto L_0x00f1;
    L_0x00e6:
        r0 = r3.toString();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r0 = r0.trim();	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
        r1.setTraceFile(r0);	 Catch:{ Throwable -> 0x0109, all -> 0x0105 }
    L_0x00f1:
        r5.close();	 Catch:{ Throwable -> 0x00f5 }
        return;
    L_0x00f5:
        r0 = move-exception;
        r1 = r0;
        r0 = "MetricsAnrManager";
        r2 = new java.lang.Object[r12];
        r1 = r1.getMessage();
        r2[r11] = r1;
        com.meituan.metrics.util.LogUtil.e(r0, r2);
        return;
    L_0x0105:
        r0 = move-exception;
        r1 = r0;
        r3 = r5;
        goto L_0x0150;
    L_0x0109:
        r0 = move-exception;
        r3 = r5;
        goto L_0x012c;
    L_0x010c:
        r1 = "MetricsAnrManager";
        r2 = new java.lang.Object[r13];	 Catch:{ Throwable -> 0x012b }
        r5 = "invalid path:";
        r2[r11] = r5;	 Catch:{ Throwable -> 0x012b }
        r2[r12] = r0;	 Catch:{ Throwable -> 0x012b }
        com.meituan.metrics.util.LogUtil.d(r1, r2);	 Catch:{ Throwable -> 0x012b }
        return;
    L_0x011a:
        r1 = "MetricsAnrManager";
        r2 = new java.lang.Object[r13];	 Catch:{ Throwable -> 0x012b }
        r5 = "invalid path:";
        r2[r11] = r5;	 Catch:{ Throwable -> 0x012b }
        r2[r12] = r0;	 Catch:{ Throwable -> 0x012b }
        com.meituan.metrics.util.LogUtil.d(r1, r2);	 Catch:{ Throwable -> 0x012b }
        return;
    L_0x0128:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0150;
    L_0x012b:
        r0 = move-exception;
    L_0x012c:
        r1 = "MetricsAnrManager";
        r2 = new java.lang.Object[r12];	 Catch:{ all -> 0x0128 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0128 }
        r2[r11] = r0;	 Catch:{ all -> 0x0128 }
        com.meituan.metrics.util.LogUtil.e(r1, r2);	 Catch:{ all -> 0x0128 }
        if (r3 == 0) goto L_0x014f;
    L_0x013b:
        r3.close();	 Catch:{ Throwable -> 0x013f }
        goto L_0x014f;
    L_0x013f:
        r0 = move-exception;
        r1 = r0;
        r0 = "MetricsAnrManager";
        r2 = new java.lang.Object[r12];
        r1 = r1.getMessage();
        r2[r11] = r1;
        com.meituan.metrics.util.LogUtil.e(r0, r2);
        return;
    L_0x014f:
        return;
    L_0x0150:
        if (r3 == 0) goto L_0x0165;
    L_0x0152:
        r3.close();	 Catch:{ Throwable -> 0x0156 }
        goto L_0x0165;
    L_0x0156:
        r0 = move-exception;
        r2 = r0;
        r0 = new java.lang.Object[r12];
        r2 = r2.getMessage();
        r0[r11] = r2;
        r2 = "MetricsAnrManager";
        com.meituan.metrics.util.LogUtil.e(r2, r0);
    L_0x0165:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.metrics.laggy.anr.MetricsAnrManager.parseTraceFile(java.lang.String, com.meituan.metrics.laggy.anr.AnrEvent, java.lang.String):void");
    }

    private List<ProcessErrorStateInfo> getProcessErrorStateInfo() {

        ActivityManager activityManager = (ActivityManager) this.context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
        if (activityManager == null) {
            LogUtil.d(TAG, "failed to get ActivityManager");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        List<ProcessErrorStateInfo> list = null;
        while (list == null) {
            if (this.needBreak) {
                LogUtil.d(TAG, "新的anr来了，处理新的！！");
                return null;
            }
            try {
                Thread.sleep(200);
                list = activityManager.getProcessesInErrorState();
            } catch (InterruptedException e) {
                LogUtil.d(TAG, "InterruptedException", e.getMessage());
            } catch (Throwable unused) {
                return null;
            }
            if (System.currentTimeMillis() - currentTimeMillis > ANR_THRESHOLD) {
                LogUtil.d(TAG, "get processInfo 超时");
                return null;
            }
        }
        return list;
    }
}