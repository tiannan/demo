package com.meituan.metrics.cache;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.meituan.android.cipstorage.d;
import com.meituan.android.common.badge.log.Logger;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.cache.db.PageReportCountDao;
import com.meituan.metrics.config.MetricsRemoteConfig;
import com.meituan.metrics.config.MetricsRemoteConfig.NormalRanges;
import com.meituan.metrics.config.MetricsRemoteConfigV2;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleCallback;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.lifecycle.MetricsAppMonitorCallback;
import com.meituan.metrics.model.AbstractEvent;
import com.meituan.metrics.net.report.MetricsReportManager;
import com.meituan.metrics.sampler.cpu.CpuEvent;
import com.meituan.metrics.sampler.memory.MemoryEvent;
import com.meituan.metrics.util.thread.Task;
import com.meituan.metrics.util.thread.ThreadManager;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.utils.RobustBitConfig;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: ProGuard */
public class MetricsCacheManager implements MetricsActivityLifecycleCallback, MetricsAppMonitorCallback {
    private static MetricsCacheManager sInstance;
    private d cacheStorage;
    private MetricsRemoteConfig config;
    private int currentDayReportCount;
    private int currentDayReportCountV2;
    private int maxCacheSize;
    private BlockingQueue<AbstractEvent> memoryCache;
    private boolean needCheck;
    private Map<String, Integer> pageCountCache;
    private Map<String, Integer> pageCountCacheV2;

    @Override
    public void onActivityCreated(Activity activity) {

    }
    @Override

    public void onActivityPaused(Activity activity) {
    }
    @Override

    public void onActivityResumed(Activity activity) {
    }
    @Override

    public void onBackground() {
    }

    public static MetricsCacheManager getInstance() {

        if (sInstance == null) {
            synchronized (MetricsCacheManager.class) {
                if (sInstance == null) {
                    sInstance = new MetricsCacheManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {

        this.cacheStorage = d.a(context, "metrics_cache", 2);
        ThreadManager.getInstance().postIO(new Task() {
            public static ChangeQuickRedirect changeQuickRedirect;

            public void schedule() {

                MetricsCacheManager.this.getPageCountCache();
                PageReportCountDao.removeInvalidPageCountRecord(MetricsCacheManager.this.cacheStorage);
            }
        });
    }

    private void clearAllPageCount() {

            this.pageCountCache.clear();

    }

    public void setConfig(MetricsRemoteConfig metricsRemoteConfig, MetricsRemoteConfigV2 metricsRemoteConfigV2) {

        this.config = metricsRemoteConfig;
        if (metricsRemoteConfig == null && metricsRemoteConfigV2 == null) {
            this.needCheck = false;
        } else if (metricsRemoteConfig == null && metricsRemoteConfigV2.dayLimitPerPage <= 0 && metricsRemoteConfigV2.dayLimit <= 0) {
            this.needCheck = false;
        } else if (metricsRemoteConfigV2 == null && metricsRemoteConfig.dayLimitPerPage <= 0 && metricsRemoteConfig.dayLimit <= 0) {
            this.needCheck = false;
        } else if (metricsRemoteConfig == null || metricsRemoteConfig.dayLimitPerPage > 0 || metricsRemoteConfig.dayLimit > 0 || metricsRemoteConfigV2 == null || metricsRemoteConfigV2.dayLimitPerPage > 0 || metricsRemoteConfigV2.dayLimit > 0) {
            this.needCheck = true;
        } else {
            this.needCheck = false;
        }
    }

    private void getPageCountCache() {

            ThreadManager.getInstance().postIO(new Task() {

                public void schedule() {

                    MetricsCacheManager.this.pageCountCache = PageReportCountDao.getCurrentDayReportCount(PageReportCountDao.REPORT_RECORD, MetricsCacheManager.this.cacheStorage, MetricsCacheManager.this.pageCountCache);
                    MetricsCacheManager.this.currentDayReportCount = PageReportCountDao.getCurrentDayCount(PageReportCountDao.REPORT_COUNT, MetricsCacheManager.this.cacheStorage);
                    MetricsCacheManager.this.pageCountCacheV2 = PageReportCountDao.getCurrentDayReportCount(PageReportCountDao.REPORT_RECORD_V2, MetricsCacheManager.this.cacheStorage, MetricsCacheManager.this.pageCountCacheV2);
                    MetricsCacheManager.this.currentDayReportCountV2 = PageReportCountDao.getCurrentDayCount(PageReportCountDao.REPORT_COUNT_V2, MetricsCacheManager.this.cacheStorage);
                }
            });

    }

    private void onNewEvent(AbstractEvent abstractEvent) {
        Metrics.getInstance().getInterceptorChain().onNewEvent(abstractEvent);
    }

    public MetricsCacheManager() {

        this.maxCacheSize = Logger.LEVEL_NONE;
        this.pageCountCache = new ConcurrentHashMap();
        this.pageCountCacheV2 = new ConcurrentHashMap();
        this.currentDayReportCount = 0;
        this.currentDayReportCountV2 = 0;
        this.needCheck = false;
        this.memoryCache = new LinkedBlockingQueue();
        MetricsActivityLifecycleManager.getInstance().register(this);
        MetricsActivityLifecycleManager.getInstance().registerAppMonitor(this);
    }

    public void addToCache(AbstractEvent abstractEvent) {

        if (abstractEvent != null) {
            if (abstractEvent.isValid()) {
                if (this.needCheck) {
                    addCacheWithLimit(abstractEvent);
                } else {
                    addCache(abstractEvent);
                }
            }
        }
    }

    private void addCache(AbstractEvent abstractEvent) {

        if (abstractEvent != null) {
            if (abstractEvent.configFrom != -1) {
                if (abstractEvent.configFrom == 1) {
                    addToCacheReal(abstractEvent);
                } else if (abstractEvent.configFrom == 2) {
                    MetricsReportManager.getInstance().reportByBabel(abstractEvent);
                } else {
                    if (abstractEvent.configFrom == 3) {
                        addToCacheReal(abstractEvent);
                        MetricsReportManager.getInstance().reportByBabel(abstractEvent);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be  */
    private void addCacheWithLimit(com.meituan.metrics.model.AbstractEvent r13) {
        /*
        r12 = this;
        r7 = 1;
        r8 = new java.lang.Object[r7];
        r9 = 0;
        r8[r9] = r13;
        r10 = changeQuickRedirect;
        r11 = "b7b5453a52ce5a5c69b9fd3e6eab03b9";
        r3 = 0;
        r5 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r0 = r8;
        r1 = r12;
        r2 = r10;
        r4 = r11;
        r0 = com.meituan.robust.PatchProxy.isSupport(r0, r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x001b;
    L_0x0017:
        com.meituan.robust.PatchProxy.accessDispatch(r8, r12, r10, r9, r11);
        return;
    L_0x001b:
        r0 = r13.configFrom;
        r1 = -1;
        if (r0 != r1) goto L_0x0021;
    L_0x0020:
        return;
    L_0x0021:
        r0 = r13.configFrom;
        if (r0 != r7) goto L_0x0044;
    L_0x0025:
        r0 = com.meituan.metrics.config.MetricsRemoteConfigManager.getInstance();
        r0 = r0.getRemoteConfig();
        if (r0 == 0) goto L_0x0068;
    L_0x002f:
        r2 = r0.dayLimit;
        r3 = r0.dayLimitPerPage;
        r4 = r12.currentDayReportCount;
        r5 = r12.pageCountCache;
        r6 = r0.normalRanges;
        r0 = r12;
        r1 = r13;
        r0 = r0.checkConfigLimit(r1, r2, r3, r4, r5, r6);
        if (r0 == 0) goto L_0x0068;
    L_0x0041:
        r0 = 1;
        goto L_0x00a6;
    L_0x0044:
        r0 = r13.configFrom;
        r1 = 2;
        if (r0 != r1) goto L_0x006a;
    L_0x0049:
        r0 = com.meituan.metrics.config.MetricsRemoteConfigManager.getInstance();
        r0 = r0.getRemoteConfigV2();
        if (r0 == 0) goto L_0x0066;
    L_0x0053:
        r2 = r0.dayLimit;
        r3 = r0.dayLimitPerPage;
        r4 = r12.currentDayReportCount;
        r5 = r12.pageCountCache;
        r6 = 0;
        r0 = r12;
        r1 = r13;
        r0 = r0.checkConfigLimit(r1, r2, r3, r4, r5, r6);
        if (r0 == 0) goto L_0x0066;
    L_0x0064:
        r0 = 1;
        goto L_0x0067;
    L_0x0066:
        r0 = 0;
    L_0x0067:
        r9 = r0;
    L_0x0068:
        r0 = 0;
        goto L_0x00a6;
    L_0x006a:
        r0 = com.meituan.metrics.config.MetricsRemoteConfigManager.getInstance();
        r0 = r0.getRemoteConfig();
        r1 = com.meituan.metrics.config.MetricsRemoteConfigManager.getInstance();
        r8 = r1.getRemoteConfigV2();
        if (r0 == 0) goto L_0x0090;
    L_0x007c:
        r2 = r0.dayLimit;
        r3 = r0.dayLimitPerPage;
        r4 = r12.currentDayReportCount;
        r5 = r12.pageCountCache;
        r6 = r0.normalRanges;
        r0 = r12;
        r1 = r13;
        r0 = r0.checkConfigLimit(r1, r2, r3, r4, r5, r6);
        if (r0 == 0) goto L_0x0090;
    L_0x008e:
        r10 = 1;
        goto L_0x0091;
    L_0x0090:
        r10 = 0;
    L_0x0091:
        if (r8 == 0) goto L_0x00a5;
    L_0x0093:
        r2 = r8.dayLimit;
        r3 = r8.dayLimitPerPage;
        r4 = r12.currentDayReportCountV2;
        r5 = r12.pageCountCache;
        r6 = 0;
        r0 = r12;
        r1 = r13;
        r0 = r0.checkConfigLimit(r1, r2, r3, r4, r5, r6);
        if (r0 == 0) goto L_0x00a5;
    L_0x00a4:
        r9 = 1;
    L_0x00a5:
        r0 = r10;
    L_0x00a6:
        if (r0 == 0) goto L_0x00bc;
    L_0x00a8:
        r0 = r12.addToCacheReal(r13);
        if (r0 == 0) goto L_0x00bc;
    L_0x00ae:
        r0 = r12.currentDayReportCount;
        r0 = r0 + r7;
        r12.currentDayReportCount = r0;
        r0 = r12.pageCountCache;
        r1 = r13.getPageName();
        r12.upDatePageCountCache(r0, r1);
    L_0x00bc:
        if (r9 == 0) goto L_0x00d3;
    L_0x00be:
        r0 = com.meituan.metrics.net.report.MetricsReportManager.getInstance();
        r0.reportByBabel(r13);
        r0 = r12.currentDayReportCountV2;
        r0 = r0 + r7;
        r12.currentDayReportCountV2 = r0;
        r0 = r12.pageCountCacheV2;
        r1 = r13.getPageName();
        r12.upDatePageCountCache(r0, r1);
    L_0x00d3:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.metrics.cache.MetricsCacheManager.addCacheWithLimit(com.meituan.metrics.model.AbstractEvent):void");
    }

    private void upDatePageCountCache(Map<String, Integer> map, String str) {

        if (!TextUtils.isEmpty(str)) {
            if (map != null) {
                int pageReportCount = getPageReportCount(map, str);
                if (pageReportCount >= 0) {
                    map.put(str, Integer.valueOf(pageReportCount + 1));
                } else {
                    map.put(str, Integer.valueOf(1));
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x007d  */
    private boolean checkConfigLimit(com.meituan.metrics.model.AbstractEvent r20, int r21, int r22, int r23, java.util.Map<java.lang.String, java.lang.Integer> r24, java.util.List<com.meituan.metrics.config.MetricsRemoteConfig.NormalRanges> r25) {
        /*
        r19 = this;
        r7 = r19;
        r8 = r20;
        r9 = r21;
        r10 = r22;
        r11 = r23;
        r12 = r24;
        r13 = r25;
        r0 = 6;
        r14 = new java.lang.Object[r0];
        r15 = 0;
        r14[r15] = r8;
        r0 = java.lang.Integer.valueOf(r21);
        r16 = 1;
        r14[r16] = r0;
        r0 = java.lang.Integer.valueOf(r22);
        r1 = 2;
        r14[r1] = r0;
        r0 = java.lang.Integer.valueOf(r23);
        r1 = 3;
        r14[r1] = r0;
        r0 = 4;
        r14[r0] = r12;
        r0 = 5;
        r14[r0] = r13;
        r5 = changeQuickRedirect;
        r6 = "c92fe92ec9b40d2e1be73bb03a74f5ff";
        r3 = 0;
        r17 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r0 = r14;
        r1 = r19;
        r2 = r5;
        r4 = r6;
        r15 = r5;
        r8 = r6;
        r5 = r17;
        r0 = com.meituan.robust.PatchProxy.isSupport(r0, r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x0052;
    L_0x0046:
        r0 = 0;
        r0 = com.meituan.robust.PatchProxy.accessDispatch(r14, r7, r15, r0, r8);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        return r0;
    L_0x0052:
        r0 = r20.getPageName();
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0093;
    L_0x005c:
        r1 = "default";
        r1 = android.text.TextUtils.equals(r0, r1);
        if (r1 != 0) goto L_0x0093;
    L_0x0064:
        if (r10 == 0) goto L_0x0093;
    L_0x0066:
        if (r12 == 0) goto L_0x0093;
    L_0x0068:
        r1 = r24.size();
        if (r1 > 0) goto L_0x006f;
    L_0x006e:
        goto L_0x0093;
    L_0x006f:
        r0 = r7.getPageReportCount(r12, r0);
        if (r0 < 0) goto L_0x007a;
    L_0x0075:
        if (r0 >= r10) goto L_0x0078;
    L_0x0077:
        goto L_0x007a;
    L_0x0078:
        r0 = 0;
        goto L_0x007b;
    L_0x007a:
        r0 = 1;
    L_0x007b:
        if (r9 <= 0) goto L_0x0084;
    L_0x007d:
        if (r0 == 0) goto L_0x0083;
    L_0x007f:
        if (r11 >= r9) goto L_0x0083;
    L_0x0081:
        r0 = 1;
        goto L_0x0084;
    L_0x0083:
        r0 = 0;
    L_0x0084:
        if (r0 != 0) goto L_0x0092;
    L_0x0086:
        r1 = com.sankuai.common.utils.CollectionUtils.isEmpty(r25);
        if (r1 != 0) goto L_0x0092;
    L_0x008c:
        r1 = r20;
        r0 = r7.checkDataWithRange(r1, r13);
    L_0x0092:
        return r0;
    L_0x0093:
        if (r9 > 0) goto L_0x0096;
    L_0x0095:
        return r16;
    L_0x0096:
        if (r11 >= r9) goto L_0x0099;
    L_0x0098:
        return r16;
    L_0x0099:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.metrics.cache.MetricsCacheManager.checkConfigLimit(com.meituan.metrics.model.AbstractEvent, int, int, int, java.util.Map, java.util.List):boolean");
    }

    private int getPageReportCount(Map<String, Integer> map, String str) {

        Object obj = map.get(str);
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        return 0;
    }

    private boolean addToCacheReal(AbstractEvent abstractEvent) {

        boolean offer = this.memoryCache.offer(abstractEvent);
        if (offer) {
            onNewEvent(abstractEvent);
        }
        return offer;
    }

    private boolean checkDataWithRange(AbstractEvent abstractEvent, List<NormalRanges> list) {

        CharSequence localEventType = abstractEvent.getLocalEventType();
        boolean z = abstractEvent instanceof MemoryEvent;
        if (z) {
            localEventType = ((MemoryEvent) abstractEvent).getOldEventType();
        } else if (abstractEvent instanceof CpuEvent) {
            localEventType = ((CpuEvent) abstractEvent).getOldLocalEventType();
        }
        if (TextUtils.isEmpty(localEventType)) {
            return false;
        }
        for (NormalRanges normalRanges : list) {
            double metricValue = abstractEvent.getMetricValue();
            if (z) {
                metricValue = ((MemoryEvent) abstractEvent).getOldMetricValue();
            }
            if (normalRanges.getRange(localEventType, metricValue) == 0) {
                return true;
            }
        }
        return false;
    }

    public void getAllData(Collection<? super AbstractEvent> collection) {

            this.memoryCache.drainTo(collection);

    }

    @Override
    public void onActivityStopped(Activity activity) {
       if (this.needCheck) {
            if (this.pageCountCache != null && this.pageCountCache.size() > 0) {
                ThreadManager.getInstance().postIO(new Task() {

                    public void schedule() {

                        PageReportCountDao.addPageCountRecord(PageReportCountDao.REPORT_RECORD, MetricsCacheManager.this.pageCountCache, MetricsCacheManager.this.cacheStorage);
                        PageReportCountDao.setCurrentDayLimit(PageReportCountDao.REPORT_COUNT, MetricsCacheManager.this.currentDayReportCount, MetricsCacheManager.this.cacheStorage);
                    }
                });
            }
            if (this.pageCountCacheV2 != null && this.pageCountCacheV2.size() > 0) {
                ThreadManager.getInstance().postIO(new Task() {
                    public void schedule() {

                        PageReportCountDao.addPageCountRecord(PageReportCountDao.REPORT_RECORD_V2, MetricsCacheManager.this.pageCountCacheV2, MetricsCacheManager.this.cacheStorage);
                        PageReportCountDao.setCurrentDayLimit(PageReportCountDao.REPORT_COUNT_V2, MetricsCacheManager.this.currentDayReportCountV2, MetricsCacheManager.this.cacheStorage);
                    }
                });
            }
        }
    }

    public void onForeground() {
        if (this.needCheck) {
            if (this.pageCountCache == null || this.pageCountCache.size() == 0) {
                getPageCountCache();
            }
        }
    }
}