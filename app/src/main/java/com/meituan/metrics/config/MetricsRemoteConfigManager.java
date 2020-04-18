package com.meituan.metrics.config;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.meituan.android.common.horn.Horn;
import com.meituan.android.common.horn.HornCallback;
import com.meituan.metrics.Environment;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.common.Constants;
import com.meituan.metrics.config.MetricsRemoteConfig.MetricsSwitches;
import com.meituan.metrics.util.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.HashMap;

/* compiled from: ProGuard */
public class MetricsRemoteConfigManager {
    public static final int DISABLE = -1;
    public static final int ENABLE_BOTH = 3;
    public static final int ENABLE_NEW = 2;
    public static final int ENABLE_OLD = 1;
    private static final String METRICS_REMOTE_CONFIG_FILE_NAME = "metrics_remote_config";
    private static final String METRICS_REMOTE_CONFIG_V2_FILE_NAME = "metrics_remote_config_v2";
    private static final String TAG = "MetricsRemoteConfigManager";
    private static volatile MetricsRemoteConfigManager instance;
    private MetricsRemoteConfig config;
    private MetricsRemoteConfigV2 configV2;
    private Gson gson;

    public MetricsRemoteConfigManager() {
        this.gson = new Gson();
    }

    public static MetricsRemoteConfigManager getInstance() {

        if (instance == null) {
            synchronized (MetricsRemoteConfigManager.class) {
                if (instance == null) {
                    instance = new MetricsRemoteConfigManager();
                }
            }
        }
        return instance;
    }

    public int getAppStartupConfig() {
        int i = 0;

        Object obj = (this.config == null || !this.config.isAppStartupTimerEnabled()) ? null : 1;
        if (this.configV2 != null && this.configV2.isAppStartupEnable()) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public boolean isAppStartupEnable() {
        boolean z = false;

        if (getAppStartupConfig() != -1) {
            z = true;
        }
        return z;
    }

    public boolean isFpsEnable() {
        boolean z = false;

        if ((this.config != null && (this.config.isFpsPageEnable() || this.config.isFpsScrollEnable() || this.config.isFpsCustomEnable())) || (this.configV2 != null && (this.configV2.isFpsPageEnable() || this.configV2.isFpsScrollEnable() || this.configV2.isFpsCustomEnable()))) {
            z = true;
        }
        return z;
    }

    public boolean isFpsPageEnable() {
        boolean z = false;

        if (getFpsPageConfig() != -1) {
            z = true;
        }
        return z;
    }

    public int getFpsPageConfig() {
        int i = 0;

        Object obj = (this.config == null || !this.config.isFpsPageEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isFpsPageEnable()) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public int getFpsPageConfig(String str) {
        int i = 0;

        Object obj = (this.config == null || !this.config.isFpsPageEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isFpsPageEnable(str)) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public boolean isFpsScrollEnable() {
        boolean z = false;

        if (getFpsScrollConfig() != -1) {
            z = true;
        }
        return z;
    }

    public int getFpsScrollConfig() {
        int i = 0;

        Object obj = (this.config == null || !this.config.isFpsScrollEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isFpsScrollEnable()) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public int getFpsScrollConfig(String str) {
        int i = 0;
        Object obj = (this.config == null || !this.config.isFpsScrollEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isFpsScrollEnable(str)) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public boolean isFpsCustomEnable() {
        boolean z = false;

        if (getFpsCustomConfig() != -1) {
            z = true;
        }
        return z;
    }

    public int getFpsCustomConfig() {
        int i = 0;

        Object obj = (this.config == null || !this.config.isFpsCustomEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isFpsCustomEnable()) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public int getFpsCustomConfig(String str) {
        int i = 0;
        Object obj = (this.config == null || !this.config.isFpsCustomEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isFpsCustomEnable(str)) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public int getLoadPageConfig(String str) {
        int i = 0;

        Object obj = (this.config == null || !this.config.isLoadPageEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isLoadPageEnable(str)) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public int getLoadCustomConfig(String str) {
        int i = 0;

        Object obj = (this.config == null || !this.config.isLoadCustom()) ? null : 1;
        if (this.configV2 != null && this.configV2.isLoadPageCustom(str)) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public int getMemoryConfig(String str) {
        int i = 0;

        Object obj = (this.config == null || !this.config.isMemoryEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isMemoryEnable(str)) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public int getCpuConfig(String str) {
        int i = 0;

        Object obj = (this.config == null || !this.config.isCpuEnable()) ? null : 1;
        if (this.configV2 != null && this.configV2.isCpuEnable(str)) {
            i = 1;
        }
        if (obj != null && i != 0) {
            return 3;
        }
        if (obj != null) {
            return 1;
        }
        return i != 0 ? 2 : -1;
    }

    public MetricsRemoteConfig getRemoteConfig() {

        Context context = Metrics.getInstance().getContext();
        if (context == null) {
            return null;
        }
        if (this.config == null) {
            File file = new File(context.getFilesDir(), METRICS_REMOTE_CONFIG_FILE_NAME);
            if (file.exists()) {
                try {
                    Reader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    try {
                        this.config = (MetricsRemoteConfig) this.gson.fromJson(bufferedReader, MetricsRemoteConfig.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (Metrics.debug) {
                MetricsSwitches metricsSwitches = new MetricsSwitches();
                metricsSwitches.anr = 1;
                metricsSwitches.fpsPage = 1;
                metricsSwitches.fpsScroll = 1;
                metricsSwitches.fpsCustom = 1;
                metricsSwitches.cpu = 1;
                metricsSwitches.memory = 1;
                metricsSwitches.lag = 1;
                metricsSwitches.loadHomepage = 1;
                metricsSwitches.loadPage = 1;
                metricsSwitches.loadCustom = 1;
                metricsSwitches.bigImage = 1;
                if (this.config == null) {
                    this.config = new MetricsRemoteConfig();
                }
                this.config.switches = metricsSwitches;
                this.config.lagThreshold = 2500;
            }
            LogUtil.d(TAG, "config", this.config);
            Environment environment = Metrics.getEnvironment();
            if (environment != null) {
                AnonymousClass1 anonymousClass1 = new HornCallback() {

                    public void onChanged(boolean z, String str) {

                        File file = new File(Metrics.getInstance().getContext().getFilesDir(), MetricsRemoteConfigManager.METRICS_REMOTE_CONFIG_FILE_NAME);
                        try {
                            MetricsRemoteConfigManager.this.gson.fromJson(str, MetricsRemoteConfig.class);
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                            bufferedWriter.write(str);
                            bufferedWriter.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        } catch (JsonSyntaxException e3) {
                            e3.printStackTrace();
                        }
                    }
                };
                HashMap hashMap = new HashMap();
                hashMap.put("metricsToken", environment.getToken());
                hashMap.put("metricsSdkVersion", environment.sdkVersion);
                if (Metrics.debug) {
                    hashMap.put("dev", Boolean.TRUE);
                }
                Horn.register(Constants.METRICS, anonymousClass1, hashMap);
            }
        }
        if (this.config == null) {
            this.config = new MetricsRemoteConfig();
        }
        return this.config;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01ad  */
    public com.meituan.metrics.config.MetricsRemoteConfigV2 getRemoteConfigV2() {
        /*
        r11 = this;
        r0 = 0;
        r8 = new java.lang.Object[r0];
        r9 = changeQuickRedirect;
        r10 = "124eda0bc45329b81839ebaf45d808b4";
        r4 = 0;
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r1 = r8;
        r2 = r11;
        r3 = r9;
        r5 = r10;
        r1 = com.meituan.robust.PatchProxy.isSupport(r1, r2, r3, r4, r5, r6);
        if (r1 == 0) goto L_0x001b;
    L_0x0014:
        r0 = com.meituan.robust.PatchProxy.accessDispatch(r8, r11, r9, r0, r10);
        r0 = (com.meituan.metrics.config.MetricsRemoteConfigV2) r0;
        return r0;
    L_0x001b:
        r1 = com.meituan.metrics.Metrics.getInstance();
        r1 = r1.getContext();
        r2 = 0;
        if (r1 != 0) goto L_0x0027;
    L_0x0026:
        return r2;
    L_0x0027:
        r3 = r11.configV2;
        if (r3 != 0) goto L_0x01a9;
    L_0x002b:
        r3 = new java.io.File;
        r4 = r1.getFilesDir();
        r5 = "metrics_remote_config_v2";
        r3.<init>(r4, r5);
        r4 = r3.exists();
        if (r4 == 0) goto L_0x0071;
    L_0x003c:
        r4 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x0062, all -> 0x005f }
        r5 = new java.io.InputStreamReader;	 Catch:{ FileNotFoundException -> 0x0062, all -> 0x005f }
        r6 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0062, all -> 0x005f }
        r6.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0062, all -> 0x005f }
        r5.<init>(r6);	 Catch:{ FileNotFoundException -> 0x0062, all -> 0x005f }
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0062, all -> 0x005f }
        r2 = r11.gson;	 Catch:{ Exception -> 0x005a }
        r3 = com.meituan.metrics.config.MetricsRemoteConfigV2.class;
        r2 = r2.fromJson(r4, r3);	 Catch:{ Exception -> 0x005a }
        r2 = (com.meituan.metrics.config.MetricsRemoteConfigV2) r2;	 Catch:{ Exception -> 0x005a }
        r11.configV2 = r2;	 Catch:{ Exception -> 0x005a }
        goto L_0x0068;
    L_0x0058:
        r2 = move-exception;
        goto L_0x0065;
    L_0x005a:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ FileNotFoundException -> 0x0058 }
        goto L_0x0068;
    L_0x005f:
        r0 = move-exception;
        r4 = r2;
        goto L_0x006d;
    L_0x0062:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
    L_0x0065:
        r2.printStackTrace();	 Catch:{ all -> 0x006c }
    L_0x0068:
        com.sankuai.common.utils.IOUtils.close(r4);
        goto L_0x0071;
    L_0x006c:
        r0 = move-exception;
    L_0x006d:
        com.sankuai.common.utils.IOUtils.close(r4);
        throw r0;
    L_0x0071:
        r2 = com.meituan.metrics.Metrics.debug;
        if (r2 == 0) goto L_0x0168;
    L_0x0075:
        r2 = new com.meituan.metrics.config.MetricsRemoteConfigV2;
        r2.<init>();
        r11.configV2 = r2;
        r2 = r11.configV2;
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2.loadHomepage = r3;
        r2 = r11.configV2;
        r2.anr = r3;
        r2 = r11.configV2;
        r2.lag = r3;
        r2 = r11.configV2;
        r4 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r2.lagThreshold = r4;
        r2 = r11.configV2;
        r4 = 10;
        r2.maxReportCallstackTimes = r4;
        r2 = r11.configV2;
        r4 = new com.meituan.metrics.config.MetricsRemoteConfigV2$PagesConfig;
        r4.<init>();
        r2.fpsScroll = r4;
        r2 = r11.configV2;
        r2 = r2.fpsScroll;
        r4 = new java.util.HashMap;
        r4.<init>();
        r2.pages = r4;
        r2 = r11.configV2;
        r2 = r2.fpsScroll;
        r2 = r2.pages;
        r4 = "*";
        r5 = java.lang.Float.valueOf(r3);
        r2.put(r4, r5);
        r2 = r11.configV2;
        r4 = new com.meituan.metrics.config.MetricsRemoteConfigV2$KeysConfig;
        r4.<init>();
        r2.fpsCustom = r4;
        r2 = r11.configV2;
        r2 = r2.fpsCustom;
        r4 = new java.util.HashMap;
        r4.<init>();
        r2.keys = r4;
        r2 = r11.configV2;
        r2 = r2.fpsCustom;
        r2 = r2.keys;
        r4 = "*";
        r5 = java.lang.Float.valueOf(r3);
        r2.put(r4, r5);
        r2 = r11.configV2;
        r4 = new com.meituan.metrics.config.MetricsRemoteConfigV2$PagesConfig;
        r4.<init>();
        r2.loadPage = r4;
        r2 = r11.configV2;
        r2 = r2.loadPage;
        r4 = new java.util.HashMap;
        r4.<init>();
        r2.pages = r4;
        r2 = r11.configV2;
        r2 = r2.loadPage;
        r2 = r2.pages;
        r4 = "*";
        r5 = java.lang.Float.valueOf(r3);
        r2.put(r4, r5);
        r2 = r11.configV2;
        r4 = new com.meituan.metrics.config.MetricsRemoteConfigV2$KeysConfig;
        r4.<init>();
        r2.loadCustom = r4;
        r2 = r11.configV2;
        r2 = r2.loadCustom;
        r4 = new java.util.HashMap;
        r4.<init>();
        r2.keys = r4;
        r2 = r11.configV2;
        r2 = r2.loadCustom;
        r2 = r2.keys;
        r4 = "*";
        r5 = java.lang.Float.valueOf(r3);
        r2.put(r4, r5);
        r2 = r11.configV2;
        r4 = new com.meituan.metrics.config.MetricsRemoteConfigV2$PagesConfig;
        r4.<init>();
        r2.memory = r4;
        r2 = r11.configV2;
        r2 = r2.memory;
        r4 = new java.util.HashMap;
        r4.<init>();
        r2.pages = r4;
        r2 = r11.configV2;
        r2 = r2.memory;
        r2 = r2.pages;
        r4 = "*";
        r5 = java.lang.Float.valueOf(r3);
        r2.put(r4, r5);
        r2 = r11.configV2;
        r4 = new com.meituan.metrics.config.MetricsRemoteConfigV2$PagesConfig;
        r4.<init>();
        r2.cpu = r4;
        r2 = r11.configV2;
        r2 = r2.cpu;
        r4 = new java.util.HashMap;
        r4.<init>();
        r2.pages = r4;
        r2 = r11.configV2;
        r2 = r2.cpu;
        r2 = r2.pages;
        r4 = "*";
        r3 = java.lang.Float.valueOf(r3);
        r2.put(r4, r3);
    L_0x0168:
        r2 = "MetricsRemoteConfigManager";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r4 = "configV2";
        r3[r0] = r4;
        r0 = 1;
        r4 = r11.configV2;
        r3[r0] = r4;
        com.meituan.metrics.util.LogUtil.d(r2, r3);
        r0 = com.meituan.metrics.Metrics.getEnvironment();
        if (r0 == 0) goto L_0x01a9;
    L_0x017f:
        r2 = new com.meituan.metrics.config.MetricsRemoteConfigManager$2;
        r2.<init>(r1);
        r1 = new java.util.HashMap;
        r1.<init>();
        r3 = "metricsToken";
        r4 = r0.getToken();
        r1.put(r3, r4);
        r3 = "metricsSdkVersion";
        r0 = r0.sdkVersion;
        r1.put(r3, r0);
        r0 = com.meituan.metrics.Metrics.debug;
        if (r0 == 0) goto L_0x01a4;
    L_0x019d:
        r0 = "dev";
        r3 = java.lang.Boolean.TRUE;
        r1.put(r0, r3);
    L_0x01a4:
        r0 = "metrics_config";
        com.meituan.android.common.horn.Horn.register(r0, r2, r1);
    L_0x01a9:
        r0 = r11.configV2;
        if (r0 != 0) goto L_0x01b4;
    L_0x01ad:
        r0 = new com.meituan.metrics.config.MetricsRemoteConfigV2;
        r0.<init>();
        r11.configV2 = r0;
    L_0x01b4:
        r0 = r11.configV2;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.metrics.config.MetricsRemoteConfigManager.getRemoteConfigV2():com.meituan.metrics.config.MetricsRemoteConfigV2");
    }
}