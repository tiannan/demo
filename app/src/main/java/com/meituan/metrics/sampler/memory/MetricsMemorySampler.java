// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sampler.memory;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Process;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.config.MetricsLocalSwitchConfigManager;
import com.meituan.metrics.config.MetricsRemoteConfigManager;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.sampler.MetricsSampler;
import com.meituan.metrics.util.AppUtils;
import com.meituan.metrics.util.TimeUtil;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.sankuai.common.utils.NumberUtils;

// Referenced classes of package com.meituan.metrics.sampler.memory:
//            MemoryEvent

public class MetricsMemorySampler
    implements MetricsSampler
{

    public MetricsMemorySampler()
    {
    }

    public void doSample()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "56c181d137803fbb708f33918d9cb2a4", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "56c181d137803fbb708f33918d9cb2a4");
            return;
        }
        if(event == null)
            return;
        if(event.configFrom == 1 || event.configFrom == 3)
        {
            usedMem = Debug.getPss() << 10;
            event.computeAvg(usedMem);
        }
        pageSampleCount = pageSampleCount + 1;
        if(pageSampleCount >= 0x7fffffff)
            pageSampleCount = 0;
        if((pageSampleCount & 1) != 1 || event.configFrom != 2 && event.configFrom != 3) goto _L2; else goto _L1
_L1:
        long l1;
        l1 = TimeUtil.elapsedTimeMillis();
        if(l1 - lastSampleTime < 1000L)
            return;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        android.os.Debug.MemoryInfo memoryinfo;
        Object obj;
        android.os.Debug.MemoryInfo amemoryinfo[];
        try
        {
            lastSampleTime = l1;
            k1 = (int)(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory() >> 10);
            obj = Metrics.getInstance().getContext();
        }
        catch(Exception exception)
        {
            return;
        }
        if(obj == null) goto _L4; else goto _L3
_L3:
        memoryinfo = null;
        obj = (ActivityManager)((Context) (obj)).getSystemService("activity");
        if(obj == null) goto _L4; else goto _L5
_L5:
        amemoryinfo = ((ActivityManager) (obj)).getProcessMemoryInfo(new int[] {
            Process.myPid()
        });
        if(amemoryinfo.length > 0)
            memoryinfo = amemoryinfo[0];
        if(memoryinfo == null) goto _L4; else goto _L6
_L6:
        if(android.os.Build.VERSION.SDK_INT < 23) goto _L8; else goto _L7
_L7:
        i = NumberUtils.parseInt(memoryinfo.getMemoryStat("summary.total-pss"), -1);
        k = NumberUtils.parseInt(memoryinfo.getMemoryStat("summary.java-heap"), -1);
        l = NumberUtils.parseInt(memoryinfo.getMemoryStat("summary.native-heap"), -1);
        i1 = NumberUtils.parseInt(memoryinfo.getMemoryStat("summary.code"), -1);
        j1 = NumberUtils.parseInt(memoryinfo.getMemoryStat("summary.stack"), -1);
        j = NumberUtils.parseInt(memoryinfo.getMemoryStat("summary.graphics"), -1);
          goto _L9
_L8:
        i = memoryinfo.getTotalPss();
          goto _L10
_L9:
        event.computeAvg(i, k, l, i1, j1, j, k1);
_L2:
        return;
_L4:
        i = -1;
_L10:
        k = -1;
        l = -1;
        i1 = -1;
        j1 = -1;
        j = -1;
        if(true) goto _L9; else goto _L11
_L11:
    }

    public double getRealTimeValue()
    {
        return (double)usedMem;
    }

    public void pageEnter(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4c4f57b0a0408ec7df5cdeb40e3985a0", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4c4f57b0a0408ec7df5cdeb40e3985a0");
            return;
        }
        activity = AppUtils.getPageName(activity, MetricsActivityLifecycleManager.currentActivity);
        int i = MetricsRemoteConfigManager.getInstance().getMemoryConfig(activity);
        if(MetricsLocalSwitchConfigManager.getInstance().getMemSw(activity) && i != -1)
        {
            if(dalvikMax <= 0)
                dalvikMax = (int)(Runtime.getRuntime().maxMemory() >> 10);
            event = new MemoryEvent(activity, dalvikMax);
            event.configFrom = i;
            return;
        } else
        {
            usedMem = 0L;
            event = null;
            return;
        }
    }

    public void pageExit(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c65cd21b7b1a86f82bd1acee1441a2a2", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c65cd21b7b1a86f82bd1acee1441a2a2");
            return;
        }
        pageSampleCount = 0;
        if(event != null)
        {
            doSample();
            event.optionTags = AppUtils.getCustomTags(activity, "memory");
            MetricsCacheManager.getInstance().addToCache(event);
            event = null;
            lastSampleTime = 0L;
        }
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private int dalvikMax;
    private MemoryEvent event;
    private long lastSampleTime;
    private int pageSampleCount;
    private long usedMem;
}
